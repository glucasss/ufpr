/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  lucas
 * Created: 25/08/2019
 */

DROP DATABASE IF EXISTS web2_ex3;

CREATE DATABASE web2_ex3;

USE web2_ex3;

create table tb_usuario(
id_usuario int not null auto_increment primary key,
login_usuario varchar(50),
senha_usuario varchar(50),
nome_usuario varchar(100));