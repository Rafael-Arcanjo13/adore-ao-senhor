create table institutions (

    id bigint not null auto_increment,
    name varchar(100) not null,
    slug varchar(100) not null,

    created_at timestamp not null,
    updated_at timestamp not null,

    primary key(id)

);