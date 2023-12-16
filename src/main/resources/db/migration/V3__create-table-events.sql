create table events(

    id bigint not null auto_increment,
    titulo varchar(100) not null,
    descricao varchar(100) not null unique,
    data timestamp not null unique,
    max_participantes int unsigned,
    logradouro varchar(100) not null,
    bairro varchar(100) not null,
    cep varchar(8) not null,
    complemento varchar(100),
    numero varchar(20),
    uf char(2) not null,
    cidade varchar(100) not null,
    categorias varchar(20),
    excluido tinyint not null,

    primary key(id)
);