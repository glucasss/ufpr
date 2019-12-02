DROP DATABASE [IF EXISTS] oscarapp;
CREATE DATABASE oscarapp;
use oscarapp;

create table usuario(
login varchar(100) primary key,
senha varchar(100),
voto varchar(10)
);