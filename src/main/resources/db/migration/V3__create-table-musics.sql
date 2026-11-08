create table musics (

    id bigint not null auto_increment,
    name varchar(100) not null,
    composer varchar(100) not null,
    tone varchar(10) not null,

    link_youtube varchar(255),
    link_cifra_club varchar(255),


    created_at timestamp not null,
    updated_at timestamp not null,

    primary key(id)
);