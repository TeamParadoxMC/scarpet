__config() -> {
    'scope' -> 'global'
};

global_TOTAL_LIMIT = 15;
global_fake_players = 0;

__on_player_connects(player) -> (
    if(player ~ 'player_type' != 'fake', return());
    global_fake_players += 1;
    if(global_fake_players > global_TOTAL_LIMIT,
        run('player ' + player + ' kill');
        broadcast_to_all(format('r Bots limit exceeded. Cannot place more bots.'));
    );
);

__on_player_disconnects(player,reason) -> (
    if(player ~ 'player_type' == 'fake', global_fake_players = max(0,global_fake_players - 1));
);

broadcast_to_all(message) -> (
    for(filter(player('*'), player_type != 'fake'),
        print(_, message);
    );
);
