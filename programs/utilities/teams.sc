__config() -> {
    'scope' -> 'global' ,
    'stay_loaded' -> true
};

__on_player_connects(player) -> (

    t= query(player,'player_type');
    team= query(player,'team');
    if(t=='fake' && team==null, team_add('Bot',player);,
    team==null, team_add('players'player);
    );
);