create table usuarios (

    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    senha varchar(255) not null unique,
    telefone varchar(20),

    cargo varchar(100) not null,
    role varchar(20) not null,

    created_at datetime not null,
    updated_at datetime not null,

    instituicao_id bigint not null,

    ativo tinyint(1) not null,

    primary key(id),

    constraint fk_usuarios_instituicao foreign key(instituicao_id) references instituicoes(id)

);