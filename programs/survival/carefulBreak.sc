__config() -> {'stay_loaded' -> true};

__command()->(
	player= player();
	print('loaded');
);

enable() -> (
	player= player();

	if(query(player,'has_scoreboard_tag','sneak_careful_break'), 
	(print('You have already enabled sneak mode. Disable first to enable non sneak mode')),
	modify(player,'tag','careful_break');
	);

);

disable() -> (
	player= player();
	modify(player, 'clear_tag', 'careful_break');
	modify(player, 'clear_tag', 'sneak_careful_break');
);

sneak() -> (
	player=player();

	if(query(player,'has_scoreboard_tag','careful_break'), 
	(print('You have already enabled non sneak mode. Disable first to enable sneak mode')),
	modify(player,'tag','sneak_careful_break');
	);

	
);

__on_player_breaks_block(player, block) -> (	

	if(query(player, 'has_scoreboard_tag','careful_break'),
		schedule(0,'_move_items_to_inventory', player, pos(block));
	);

	if(query(player,'sneaking') && query(player,'has_scoreboard_tag', 'sneak_careful_break'), 
		schedule(0,'_move_items_to_inventory', player, pos(block));
	);

);

_move_items_to_inventory(player, coords) ->
(
	selector = str('@e[type=item,x=%d,y=%d,z=%d,dx=1,dy=1,dz=1]',coords);
	for (filter(entity_selector(selector), _~'pickup_delay' == 10 ),
		current_entity_item = _ ;		
		try
		(
			l(item_name, count, item_nbt) = (current_entity_item ~ 'item');
			slot = -1;
			while( (slot = inventory_find(player, item_name, slot+1)) != null, 41,
				current = inventory_get(player, slot);
				if ( current:1+count <= stack_limit(item_name) && current:2 == item_nbt,
					inventory_set(player, slot, count+current:1);
					throw()
				)			
			);
			slot = inventory_find(player, null);
			if (slot != null && slot < 36, // skip #40, like in vanilla
				inventory_set(player, slot, count, item_name, item_nbt);
				throw()
			)
		,
			modify(current_entity_item, 'remove')
		)
	)
)
