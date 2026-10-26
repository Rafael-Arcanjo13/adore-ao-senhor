create table users (

    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique,
    password varchar(255) not null,
    telephone varchar(20),

    position varchar(100) not null,
    role varchar(20) not null,

    created_at timestamp not null,
    updated_at timestamp not null,

    institution_id bigint not null,

    verification_code varchar(255),

    enable boolean not null,

    active boolean not null,

    primary key(id),

    constraint fk_users_institution foreign key(institution_id) references institutions(id)

);