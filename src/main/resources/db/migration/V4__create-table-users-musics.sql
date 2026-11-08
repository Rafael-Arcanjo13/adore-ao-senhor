create table users_musics (
    id bigint not null auto_increment,

    user_id bigint not null,
    music_id bigint not null,

    tone_of_user varchar(10) not null,

    created_at timestamp not null,
    updated_at timestamp not null,

    active boolean not null,

    primary key (id),

    constraint uk_user_music
        unique (user_id, music_id),

    constraint fk_user_musics_user
        foreign key (user_id)
        references users (id)
        on delete cascade,

    constraint fk_user_musics_music
            foreign key (music_id)
            references musics (id)
            on delete cascade
);