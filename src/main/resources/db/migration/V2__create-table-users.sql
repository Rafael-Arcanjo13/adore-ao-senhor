create table users (

    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique,
    password varchar(255) not null unique,
    telephone varchar(20),

    position varchar(100) not null,
    role varchar(20) not null,

    created_at datetime not null,
    updated_at datetime not null,

    institutions_id bigint not null,

    active tinyint(1) not null,

    primary key(id),

    constraint fk_users_institutions foreign key(institutions_id) references institutions(id)

);