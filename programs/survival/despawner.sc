__config() -> {
  'stay_loaded' -> true,
  'scope' -> 'global'
  };   

global_items={'egg','arrow','rotten_flesh','bone','string','gunpowder','dirt','cobblestone','grass','warped_nylium','gravel','stone','endstone','wheat_seed','warped_wart_block','warped_stem'};

__command()->(print(format('r Please select sub-category.')));

zombies()->(
  zombies = entity_selector('@e[type=minecraft:zombie]');
  for(zombies,
  item=query(_,'holds'):0;

  if(has(global_items,item), modify(_,'kill'););
  
   );
);

emen() -> (
  emen= entity_selector('@e[type=minecraft:enderman]');
  for(emen,
    item=query(_,'holds'):0;
    if(has(global_items,item), modify(_,'kill'));
    );
);