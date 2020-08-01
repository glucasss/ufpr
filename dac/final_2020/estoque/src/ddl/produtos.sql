/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  lucas
 * Created: 29/07/2020
 */
create database estoque;

use estoque;

CREATE TABLE produtos (
	id integer NOT NULL,
	nome varchar(100) not null,
	dsc varchar(150) not null,
	qtdEstoque integer not null
);

CREATE SEQUENCE produtos_id_seq
	START 1
	INCREMENT 1
	MINVALUE 1
	OWNED BY produtos.id;
