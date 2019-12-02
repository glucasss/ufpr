DROP DATABASE IF EXISTS android;

CREATE DATABASE android;

USE android;

CREATE TABLE usuario(
    id_usuario int not null auto_increment primary key,
    login_usuario varchar(50) unique,
    senha_usuario varchar(50)
);