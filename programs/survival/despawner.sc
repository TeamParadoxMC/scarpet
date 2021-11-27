__config() -> {'stay_loaded' -> true};   

global_items={'egg', 'arrow', 'rotten_flesh', 'bone', 'gunpowder','dirt', 'cobblestone'};
    
__command()->(
_kill();
);

_kill()->(
  zombies = entity_selector('@e[type=minecraft:zombie]');
  for(zombies,
  item=query(_,'holds'):0;

  if(has(global_items,item), modify(_,'kill'););
  
   );
);
