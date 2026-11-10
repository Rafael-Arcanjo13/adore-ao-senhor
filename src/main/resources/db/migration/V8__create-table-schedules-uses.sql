create table schedules_users (
    id bigint not null auto_increment,

    schedule_id bigint not null,
    user_id bigint not null,

    confirmation_status varchar(10) not null,

    created_at timestamp not null,
    updated_at timestamp not null,

    active boolean not null,

    primary key (id),

    constraint uk_schedule_user
        unique (schedule_id, user_id),

    constraint fk_schedule_users_schedule
        foreign key (schedule_id)
        references schedules (id)
        on delete cascade,

    constraint fk_schedule_users_user
            foreign key (user_id)
            references users (id)
            on delete cascade

);