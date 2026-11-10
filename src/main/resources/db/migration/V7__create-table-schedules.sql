create table schedules (
    id bigint not null auto_increment,
    name varchar(100) not null,
    date_event timestamp not null,
    setlist_id bigint,


    created_at timestamp not null,
    updated_at timestamp not null,

    active boolean not null,

    primary key (id),

    constraint fk_schedules_setlist
        foreign key (setlist_id)
        references setlists (id)
);