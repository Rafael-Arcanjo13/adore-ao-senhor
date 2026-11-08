create table setlists (
    id bigint not null auto_increment,
    name varchar(100) not null,
    description varchar(100) not null,

    created_at timestamp not null,
    updated_at timestamp not null,

    active boolean not null,

    primary key (id)
);