create table usuarios (

    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    telefone varchar(20),
    cargo varchar(100) not null,
    data_cadastro datetime not null,
    ativo tinyint(1),

    primary key(id)

);