create database if not exists JavaLabProject;

create table if not exists Persons(
  id int not null auto_increment primary key,
  firstName varchar (100) not null,
  lastName varchar (100) not null,
  age int not null,
  roleId int not null,
   FOREIGN key (roleId) references Roles(id)
);

drop table tasks;
create table if not exists Tasks(
 id int not null auto_increment primary key,
 name varchar (100) not null,
 description varchar (100),
 priorityId int not null,
 status varchar (100) not null,
 startDate date not null,
 endDate date not null,
 personId int ,
 FOREIGN key (personId) references Persons(id),
 FOREIGN key (priorityId) references Priority(id)
);

create table if not exists Roles(
 id int not null auto_increment primary key,
 name varchar (100) not null,
 description varchar (100)
);

create table if not exists Priority(
 id int not null auto_increment primary key,
 name varchar (100) not null,
 priorityNumber int not null
);
