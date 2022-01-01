__config()-> {
    'stay_loaded' -> true   
};

global_status=0;


__command()->(

player=player();
global_status+=1;
if(global_status%2==0, print(player,format('e Enabled.')));
if(global_status%2!=0, print(player,format('n Disabled')));
);


entity_load_handler('phantom',_(e,new) -> if(new && global_status%2==0,{

    if(get_mob_counts('monster'):0 > get_mob_counts('monster'):1+50,schedule(0, _(outer(e)) -> modify(e, 'remove')));

}));