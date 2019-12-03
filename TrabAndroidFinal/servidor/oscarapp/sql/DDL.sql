DROP DATABASE [IF EXISTS] oscarapp;
CREATE DATABASE oscarapp;
use oscarapp;

create table usuario(
login varchar(100) primary key,
senha varchar(100),
voto varchar(10)
);

create table token(
idToken int not null auto_increment,
token int not null,
dtToken datetime,
login varchar(100),
token_status varchar(100),
primary key (idToken),
foreign key (login) references usuario(login)
);

create table votofilme(
id int not null auto_increment,
nome varchar(100),
login varchar(100),
primary key (id),
foreign key (login) references usuario(login)
);

create table votodiretor(
id int not null auto_increment,
nome varchar(100),
login varchar(100),
primary key(id),
foreign key (login) references usuario(login)
);
create table votodiretor(
);