/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  adamopoulo
 * Created: Apr 17, 2016
 */

drop table Group_Member;
drop table `Group`;
drop table Extra_Visit;
drop table Visit;
drop table Scheduled_Doctor;
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
  PRIMARY KEY (id),
  constraint fk_usertype FOREIGN KEY (user_type) REFERENCES User_Type (id) 
  ON DELETE CASCADE ON UPDATE CASCADE
) ;

CREATE TABLE Visitor (
  id               int NOT NULL AUTO_INCREMENT,
  user_id          int NOT NULL,
  visitor_level    varchar(50) NOT NULL CHECK (visitor_level= 'trainee' OR visitor_level= 'senior'),
  superior_id      int,
  PRIMARY KEY (id),
  UNIQUE (user_id),
  constraint fk_userid FOREIGN KEY (user_id) REFERENCES User (id),
  constraint fk_supid FOREIGN KEY (superior_id) REFERENCES Visitor (id) 
  ON DELETE CASCADE ON UPDATE CASCADE
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
  geo_name         varchar(50),
  PRIMARY KEY (id)
) ;

CREATE TABLE `Specialty` (
  id               int NOT NULL AUTO_INCREMENT,
  specialty_name   varchar(50),
  PRIMARY KEY (id) 
) ;

CREATE TABLE `City` (
  id               int NOT NULL AUTO_INCREMENT,
  city_name        varchar(50),
  geo_id           int,
  PRIMARY KEY (id),
  constraint fk_geo_id FOREIGN KEY (geo_id) REFERENCES Geographical_Area (id)
  ON DELETE CASCADE ON UPDATE CASCADE
) ;

CREATE TABLE `Institution` (
  id               int NOT NULL AUTO_INCREMENT,
  institution_name varchar(50),
  city_id          int,
  PRIMARY KEY (id),
  constraint fk_city_id FOREIGN KEY (city_id) REFERENCES City (id)
  ON DELETE CASCADE ON UPDATE CASCADE
) ;

CREATE TABLE Doctor (
  id               int NOT NULL AUTO_INCREMENT,
  assigned_vst_id  int,
  created_from     int,
  name             varchar(50),
  specialty_id     int,
  address          varchar(50),
  phone            varchar(50),
  institution_id   int,
  position         varchar(30),
  PRIMARY KEY (id),
  constraint fk_assigned_vst_id FOREIGN KEY (assigned_vst_id) REFERENCES Visitor (id),
   constraint fk_created_from FOREIGN KEY (created_from) REFERENCES Visitor (id),
  constraint fk_specialty_id FOREIGN KEY (specialty_id) REFERENCES Specialty (id),
  constraint fk_institution_id FOREIGN KEY (institution_id) REFERENCES Institution (id)
  ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE `Cycle` (
  id               int NOT NULL AUTO_INCREMENT,
  cycle            varchar(50),
  PRIMARY KEY (id)
) ;

/*CREATE TABLE Scheduled_Doctor (
  id               int NOT NULL AUTO_INCREMENT,
  doctor_id        int,
  cycle_id         int,
  number_of_apnts  varchar(50),
  PRIMARY KEY (id),
  constraint fk_doctor_id FOREIGN KEY (doctor_id) REFERENCES Doctor (id),
  constraint fk_cycle_id FOREIGN KEY (cycle_id) REFERENCES Cycle (id)
  ON DELETE CASCADE ON UPDATE CASCADE
); */

CREATE TABLE Visit (
  id               int NOT NULL AUTO_INCREMENT,
  doctor_id        int,
  visit_offset     int,
  status           varchar(10),
  date             date,
  cycle_id         int,
  PRIMARY KEY (id),
  constraint fk_vst_doctor_id FOREIGN KEY (doctor_id) REFERENCES Doctor (id),
  constraint fk_vst_cycle_id FOREIGN KEY (cycle_id) REFERENCES Cycle (id)
  ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Visit_Visitor_Lnk (
  visitor_id int not null,
  visit_id   int not null,
  PRIMARY  KEY (visitor_id, visit_id),
  constraint fk_lnk_visit_id FOREIGN KEY (visit_id) REFERENCES Visit (id),
  constraint fk_lnk_visitor_id FOREIGN KEY (visitor_id) REFERENCES Visitor (id)
  ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Extra_Visit (
  id               int NOT NULL AUTO_INCREMENT,
  doctor_id        int,
  visit_offset     int,
  status           varchar(10),
  date             date,
  cycle_id         int,
  PRIMARY KEY (id),
  constraint fk_xvst_doctor_id FOREIGN KEY (doctor_id) REFERENCES Doctor (id),
  constraint fk_xvst_cycle_id FOREIGN KEY (cycle_id) REFERENCES Cycle (id)
  ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Extra_Visit_Visitor_Lnk (
  visitor_id int not null,
  visit_id   int not null,
  PRIMARY  KEY (visitor_id, visit_id),
  constraint fk_xt_lnk_visit_id FOREIGN KEY (visit_id) REFERENCES Visit (id),
  constraint fk_xt_lnk_visitor_id FOREIGN KEY (visitor_id) REFERENCES Visitor (id)
  ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `Group` (
  id               int NOT NULL AUTO_INCREMENT,
  parent_group_id  int,
  name             varchar(50),
  leader_id        int,
  PRIMARY KEY (id),
  constraint fk_parent_group_id FOREIGN KEY (parent_group_id) REFERENCES `Group` (id),
  constraint fk_leader_id FOREIGN KEY (leader_id) REFERENCES Visitor (id)
  ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Group_Member (
  id               int NOT NULL AUTO_INCREMENT, 
  group_id         int NOT NULL,
  member_id        int NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (member_id),
  constraint fk_mbr_group_id FOREIGN KEY (group_id) REFERENCES `Group` (id),
  constraint fk_member_id FOREIGN KEY (member_id) REFERENCES Visitor (id)
  ON DELETE CASCADE ON UPDATE CASCADE
);


INSERT INTO User_Type
(usr_type) VALUES ('admin');

INSERT INTO User_Type
(usr_type) VALUES ('visitor');

INSERT INTO User
(firstname, surname, email, username, passwd, user_type)
VALUES ('Vassilis', 'Adamopoulos','bill@bill.com','bill',SHA1('123'), 1);

INSERT INTO User
(firstname, surname, email, username, passwd, user_type)
VALUES ('George', 'Lalas','george@george.com','george',SHA1('1234'), 2);

INSERT INTO `Visitor`
(user_id, visitor_level)
VALUES (2, 'senior');

INSERT INTO `Admin`
(user_id, access_level)
VALUES (1, 1);

