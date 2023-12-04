create table users(

    id bigint not null auto_increment,
    role varchar(20) not null,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    cpf varchar(9) not null unique,
    senha varchar(255) not null,
    telefone varchar(15) not null unique,
    logradouro varchar(100) not null,
    bairro varchar(100) not null,
    cep varchar(8) not null,
    complemento varchar(100),
    numero varchar(20),
    uf char(2) not null,
    cidade varchar(100) not null,

    primary key(id)
);