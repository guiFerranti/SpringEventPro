alter table users add ativo tinyint not null;
update users set ativo = 1;