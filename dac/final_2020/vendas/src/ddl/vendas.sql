/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  lucas
 * Created: 29/07/2020
 */

create database vendas;

use vendas;

create table clientes (
    id int not null auto_increment,
    cpf_cliente varchar(11) not null,
    nome_cliente varchar(100) not null,
    email_cliente varchar(100) not null,
    primary key(id)
);

insert into clientes values (null, '08927068998', 'Lucas Siqueira', 'lg.siqueira1@gmail.com');

create table vendas (
    id int not null auto_increment,
    id_cliente int not null,
    id_produto int not null,
    data date,
    qtd_produto int not null,
    primary key(id),
    foreign key (id_cliente) references clientes(id)
);

insert into vendas values (null, 1, 1, '2020-07-29', 1);