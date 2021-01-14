create database if not exists JavaLabProject;

create table if not exists Persons(
  id int not null auto_increment primary key,
  firstName varchar (100) not null
  lastName varchar (100) not null,
  age int not null,
  taskId int not null ,
  FOREIGN KEY (taskId) REFERENCES Tasks(id)
);

create table if not exists Tasks(
 id int not null auto_increment primary key,
 name varchar (100) not null,
 description varchar (100);
 priority varchar (100) not null;
 status varchar (100) not null;
 startDate date not null;
 endDate date not null;
);