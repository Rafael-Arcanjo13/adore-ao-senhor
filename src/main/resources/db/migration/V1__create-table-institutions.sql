create table institutions (

    id bigint not null auto_increment,
    name varchar(100) not null,
    slug varchar(100) not null,

    created_at datetime not null,
    updated_at datetime not null,

    primary key(id)

);