create table setlists_musics (
    id bigint not null auto_increment,

    setlist_id bigint not null,
    music_id bigint not null,

    execution_order integer not null,
    observation varchar(255),

    created_at timestamp not null,
    updated_at timestamp not null,

    active boolean not null,

    primary key (id),

    constraint uk_setlist_music
        unique (setlist_id, music_id),

    constraint fk_setlist_musics_setlist
        foreign key (setlist_id)
        references setlists (id)
        on delete cascade,

    constraint fk_setlist_musics_music
            foreign key (music_id)
            references musics (id)
            on delete cascade
);