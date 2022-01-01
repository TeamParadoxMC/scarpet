__config() -> {'stay_loaded' -> true};

__command()->(
    _switch();
 );

_spectator()->(
    player=player();

    global_x=query(player,'x');
    global_y=query(player,'y');
    global_z=query(player,'z');
    global_pitch=query(player,'pitch');
    global_yaw=query(player,'yaw');
    global_dim=query(player,'dimension');

    run('gamemode spectator '+player);

    return()
);

_survival()->(
    player=player();

    run('gamemode survival '+player);

    run('execute in '+global_dim+' run tp '+player+' '+global_x+' '+global_y+' '+global_z+' '+global_yaw+' '+global_pitch);

    return()
);

_switch()->(
    player=player();
    if
    (query(player,'gamemode_id')==3,
        (
            _survival()
        ),
        //else if
        query(player,'gamemode_id')==0,
        (
            _spectator()
        )
    )
);