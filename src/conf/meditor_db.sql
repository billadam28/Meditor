/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  adamopoulo
 * Created: Apr 17, 2016
 */

set foreign_key_checks = 0;
drop table `Group`;
drop table Visit_Visitor_Lnk;
drop table Visit;
drop table Doctor;
drop table Visitor;
drop table Admin;
drop table User;
drop table User_Type;
drop table Institution;
drop table City;
drop table Specialty;
drop table Geographical_Area;
drop table Cycle;

set foreign_key_checks= 1;

CREATE TABLE User_Type (
  id           int NOT NULL AUTO_INCREMENT,
  usr_type     varchar(20) NOT NULL CHECK (usr_type='admin' OR usr_type= 'visitor'),
  PRIMARY KEY (id)
);

CREATE TABLE `User` (
  id           int NOT NULL AUTO_INCREMENT,
  firstname    varchar(50) NOT NULL,
  surname      varchar(50) NOT NULL,
  email        varchar(50) NOT NULL,
  username     varchar(50) NOT NULL,
  passwd       varchar(50) NOT NULL,
  user_type    int NOT NULL,
  unique (email),
  unique(username),
  PRIMARY KEY (id),
  constraint fk_usertype FOREIGN KEY (user_type) REFERENCES User_Type (id) 
  ON DELETE CASCADE ON UPDATE CASCADE
) ;

CREATE TABLE Visitor (
  user_id          int NOT NULL,
  visitor_level    varchar(50) NOT NULL CHECK (visitor_level= 'trainee' OR visitor_level= 'senior'),
  superior_id      int,
  group_id         int,
  PRIMARY KEY (user_id),
  constraint fk_userid FOREIGN KEY (user_id) REFERENCES `User` (id)
  ON DELETE CASCADE ON UPDATE CASCADE,
  constraint fk_supid FOREIGN KEY (superior_id) REFERENCES Visitor (user_id)
);

CREATE TABLE `Admin` (
  id               int NOT NULL AUTO_INCREMENT,
  user_id          int NOT NULL,
  access_level     int NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (user_id),
  constraint fk_userid_adm FOREIGN KEY (user_id) REFERENCES User (id) 
  ON DELETE CASCADE ON UPDATE CASCADE
) ;

CREATE TABLE `Geographical_Area` (
  id               int NOT NULL AUTO_INCREMENT,
  geo_name         varchar(50) not null,
  PRIMARY KEY (id)
) ;

CREATE TABLE `Specialty` (
  id               int NOT NULL AUTO_INCREMENT,
  specialty_name   varchar(50) not null,
  PRIMARY KEY (id) 
) ;

CREATE TABLE `City` (
  id               int NOT NULL AUTO_INCREMENT,
  city_name        varchar(50) not null,
  geo_id           int not null,
  PRIMARY KEY (id),
  constraint fk_geo_id FOREIGN KEY (geo_id) REFERENCES Geographical_Area (id)
) ;

CREATE TABLE `Institution` (
  id               int NOT NULL AUTO_INCREMENT,
  institution_name varchar(50) not null,
  city_id          int not null,
  PRIMARY KEY (id),
  constraint fk_city_id FOREIGN KEY (city_id) REFERENCES City (id)
) ;

CREATE TABLE Doctor (
  id               int NOT NULL AUTO_INCREMENT,
  assigned_vst_id  int,
  created_from     int not null,
  name             varchar(50) not null,
  specialty_id     int not null,
  address          varchar(50) not null,
  phone            varchar(50) not null,
  institution_id   int not null,
  position         varchar(30) not null,
  PRIMARY KEY (id),
  constraint fk_assigned_vst_id FOREIGN KEY (assigned_vst_id) REFERENCES Visitor (user_id),
   constraint fk_created_from FOREIGN KEY (created_from) REFERENCES Visitor (user_id),
  constraint fk_specialty_id FOREIGN KEY (specialty_id) REFERENCES Specialty (id),
  constraint fk_institution_id FOREIGN KEY (institution_id) REFERENCES Institution (id)
);


CREATE TABLE `Cycle` (
  id               int NOT NULL AUTO_INCREMENT,
  cycle            varchar(50) not null,
  PRIMARY KEY (id)
) ;

CREATE TABLE Visit (
  id               int NOT NULL AUTO_INCREMENT,
  doctor_id        int not null,
  visit_offset     int not null,
  status           varchar(12),
  date             date,
  cycle_id         int not null,
  extra_visit      bit,
  comments         varchar(250),
  PRIMARY KEY (id),
  constraint fk_vst_doctor_id FOREIGN KEY (doctor_id) REFERENCES Doctor (id) 
  ON DELETE CASCADE ON UPDATE CASCADE,
  constraint fk_vst_cycle_id FOREIGN KEY (cycle_id) REFERENCES Cycle (id)
);

CREATE TABLE Visit_Visitor_Lnk (
  visitor_id int not null,
  visit_id   int not null,
  PRIMARY  KEY (visitor_id, visit_id),
  constraint fk_lnk_visit_id FOREIGN KEY (visit_id) REFERENCES Visit (id)
  ON DELETE CASCADE ON UPDATE CASCADE,
  constraint fk_lnk_visitor_id FOREIGN KEY (visitor_id) REFERENCES Visitor (user_id)
  ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `Group` (
  id               int NOT NULL AUTO_INCREMENT,
  parent_group_id  int,
  `name`           varchar(50) not null,
  description      varchar(250),
  leader_id        int,
  PRIMARY KEY (id),
  unique(`name`),
  constraint fk_parent_group_id FOREIGN KEY (parent_group_id) REFERENCES `Group` (id),
  constraint fk_leader_id FOREIGN KEY (leader_id) REFERENCES Visitor (user_id)
);

ALTER TABLE Visitor
ADD CONSTRAINT fk_groupid
FOREIGN KEY (group_id)
REFERENCES `Group` (id);


INSERT INTO User_Type
(usr_type) VALUES ('admin');

INSERT INTO User_Type
(usr_type) VALUES ('visitor');

INSERT INTO User
(firstname, surname, email, username, passwd, user_type)
VALUES ('Vassilis', 'Adamopoulos','bill@bill.com','bill',SHA1('123'), 1);

INSERT INTO User
(firstname, surname, email, username, passwd, user_type)
VALUES ('George', 'Lalas','george@george.com','george',SHA1('1234'), 1);

INSERT INTO User
(firstname, surname, email, username, passwd, user_type)
VALUES ('Thodoris', 'Efstathiou','teo@teo.com','teo',SHA1('123'), 1);

INSERT INTO `Admin`
(user_id, access_level)
VALUES (1, 1);

INSERT INTO `Admin`
(user_id, access_level)
VALUES (2, 1);
INSERT INTO `Admin`
(user_id, access_level)
VALUES (3, 1);

INSERT INTO User
(firstname, surname, email, username, passwd, user_type)
VALUES ('Paul', 'Papadopoulos','paul@paul.com','paul',SHA1('1234'), 2);

INSERT INTO User
(firstname, surname, email, username, passwd, user_type)
VALUES ('John', 'Johnie','john@john.com','john',SHA1('1234'), 2);

INSERT INTO User
(firstname, surname, email, username, passwd, user_type)
VALUES ('Kostas', 'Konstantinou','kostas@kostas.com','kostas',SHA1('1234'), 2);

INSERT INTO User
(firstname, surname, email, username, passwd, user_type)
VALUES ('Spiros', 'Spirou','spiros@spiros.com','spiros',SHA1('1234'), 2);

INSERT INTO User
(firstname, surname, email, username, passwd, user_type)
VALUES ('Mitsos', 'Mitsou','mitsos@mitsos.com','mitsos',SHA1('1234'), 2);

INSERT INTO User
(firstname, surname, email, username, passwd, user_type)
VALUES ('John', 'Rambo','jb@jb.com','jrambo',SHA1('1234'), 2);

INSERT INTO `Visitor`
(user_id, visitor_level)
VALUES (4, 'senior');

INSERT INTO `Visitor`
(user_id, visitor_level)
VALUES (5, 'senior');

INSERT INTO `Visitor`
(user_id, visitor_level)
VALUES (6, 'senior');

INSERT INTO `Visitor`
(user_id, visitor_level, superior_id)
VALUES (7, 'trainee', 4);
INSERT INTO `Visitor`
(user_id, visitor_level, superior_id)
VALUES (8, 'trainee', 5);
INSERT INTO `Visitor`
(user_id, visitor_level, superior_id)
VALUES (9, 'trainee', 6);

insert into geographical_area (geo_name)values ('N. Attikis');
insert into geographical_area (geo_name)values ('N. Lakonias');
insert into geographical_area (geo_name)values ('N. Larisas');
insert into geographical_area (geo_name)values ('N. Xaniwn');
insert into geographical_area (geo_name)values ('N. Axaias');

insert into city (city_name, geo_id) values ('Marousi', 1);
insert into city (city_name, geo_id) values ('Voula', 1);
insert into city (city_name, geo_id) values ('Penteli', 1);
insert into city (city_name, geo_id) values ('Faliro', 1);
insert into city (city_name, geo_id) values ('Sparti', 2);
insert into city (city_name, geo_id) values ('Mani', 2);
insert into city (city_name, geo_id) values ('Larisa', 3);
insert into city (city_name, geo_id) values ('Farsala', 3);
insert into city (city_name, geo_id) values ('Xania', 4);
insert into city (city_name, geo_id) values ('Sfakia',4);
insert into city (city_name, geo_id) values ('Patra', 5);
insert into city (city_name, geo_id) values ('Aigio', 5);
insert into city (city_name, geo_id) values ('Rio',5);

insert into specialty (specialty_name) values ('Dentist');
insert into specialty (specialty_name) values ('Cardiologist');
insert into specialty (specialty_name) values ('Gastroenterologist');
insert into specialty (specialty_name) values ('Pathologist');
insert into specialty (specialty_name) values ('Dermatologist');
insert into specialty (specialty_name) values ('Microbiologist');
insert into specialty (specialty_name) values ('Pediatrician');

insert into institution (institution_name, city_id) values ('Ygeia', 1);
insert into institution (institution_name, city_id) values ('Asklipeio Voulas', 2);
insert into institution (institution_name, city_id) values ('Paidwn Pentelis', 3);
insert into institution (institution_name, city_id) values ('Metropolitan', 4);
insert into institution (institution_name, city_id) values ('Sparti General Hospital', 5);
insert into institution (institution_name, city_id) values ('Mani General Hospital', 6);
insert into institution (institution_name, city_id) values ('Larisa General Hospital', 7);
insert into institution (institution_name, city_id) values ('Kentro Ygeias Farsalwn', 8);
insert into institution (institution_name, city_id) values ('Agios Georgios General Hospital', 9);
insert into institution (institution_name, city_id) values ('Kentro Ygeias Sfakiwn', 10);
insert into institution (institution_name, city_id) values ('Patra General Hospital', 11);
insert into institution (institution_name, city_id) values ('Nosileutiki Monada Aigiou', 12);
insert into institution (institution_name, city_id) values ('Patra General Hospital', 13);

INSERT INTO `Cycle`
(cycle)
VALUES ('01-03');

INSERT INTO `Cycle`
(cycle)
VALUES ('04-06');

INSERT INTO `Cycle`
(cycle)
VALUES ('07-09');

INSERT INTO `Cycle`
(cycle)
VALUES ('10-12');
