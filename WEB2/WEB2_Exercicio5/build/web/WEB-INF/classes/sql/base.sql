/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  lucas
 * Created: 25/08/2019
 */

DROP DATABASE IF EXISTS web2_ex5;

CREATE DATABASE web2_ex5;

USE web2_ex5;

create table tb_usuario(
id_usuario int not null auto_increment primary key,
login_usuario varchar(50),
senha_usuario varchar(50),
nome_usuario varchar(100));

create table tb_cliente(
id_cliente int not null auto_increment primary key,
cpf_cliente varchar(100),
nome_cliente varchar(100),
email_cliente varchar(100),
data_cliente date(date),
rua_cliente varchar(100),
nr_cliente int,
cep_cliente varchar(8),
cidade_cliente varchar(100),
uf_cliente varchar(2));