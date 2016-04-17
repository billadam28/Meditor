/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  adamopoulo
 * Created: Apr 17, 2016
 */

CREATE TABLE Visitor (
  id           int NOT NULL AUTO_INCREMENT,
  firstname    varchar(50) NOT NULL,
  surname      varchar(50) NOT NULL,
  email        varchar(50) NOT NULL,
  username     varchar(50) NOT NULL,
  clearance    varchar(50) NOT NULL CHECK (clearance=`trainee` OR clearance=`senior`),
  passwd       varchar(50) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (email)
);

CREATE TABLE `Admin` (
  id           int NOT NULL AUTO_INCREMENT,
  firstname    varchar(50) NOT NULL,
  surname      varchar(50) NOT NULL,
  email        varchar(50) NOT NULL, 
  username     varchar(50) NOT NULL,
  passwd       varchar(50) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (email)
) ;

CREATE TABLE `User` (
  id         int NOT NULL AUTO_INCREMENT,
  vst_email  varchar(50),
  adm_email  varchar(50),  
  user_type  varchar(50) NOT NULL CHECK (user_type = 'admin' OR user_type = 'visitor'),
  PRIMARY KEY (id),
  constraint fk_vstemail FOREIGN KEY (vst_email) REFERENCES Visitor (email) 
  ON DELETE CASCADE ON UPDATE CASCADE,
  constraint fk_admemail FOREIGN KEY (adm_email) REFERENCES `Admin` (email) 
  ON DELETE CASCADE ON UPDATE CASCADE
) ;

INSERT INTO `Admin`
(firstname, surname, email, username, passwd)
VALUES ('Tom', 'Tsontas','tomtsontas@deemail.com','tomtsont','1233456');

INSERT INTO `Visitor`
(firstname, surname, email, username, passwd, clearance)
VALUES ('Mitsos', 'Papadopoulos','mitspap@deemail.com','mpap','1233456', 'senior');

INSERT INTO `Visitor`
(firstname, surname, email, username, passwd, clearance)
VALUES ('Kitsos', 'Avramidis', 'kitsavram@deemail.com', 'kavram', '1233456', 'senior');

INSERT INTO `Visitor`
(firstname, surname, email, username, passwd, clearance)
VALUES ('John', 'Dallas', 'jdallas@deemail.com','jdal','1233456', 'trainee');

INSERT INTO User (vst_email, adm_email, user_type) values 
(null, 'tomtsontas@deemail.com', 'admin');

INSERT INTO User (vst_email, adm_email, user_type) values 
('mitspap@deemail.com', null, 'visitor');

INSERT INTO User (vst_email, adm_email, user_type) values 
('kitsavram@deemail.com', null, 'visitor');

INSERT INTO User (vst_email, adm_email, user_type) values 
('jdallas@deemail.com', null, 'visitor');