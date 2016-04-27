/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  adamopoulo
 * Created: Apr 17, 2016
 */

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
  PRIMARY KEY (id),
  UNIQUE (user_id),
  constraint fk_userid FOREIGN KEY (user_id) REFERENCES User (id) 
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


INSERT INTO User_Type
(usr_type) VALUES ('admin');

INSERT INTO User_Type
(usr_type) VALUES ('visitor');


INSERT INTO User
(firstname, surname, email, username, passwd, user_type)
VALUES ('Vassilis', 'Adamopoulos','bill@bill.com','bill',SHA('123'), 1);

INSERT INTO User
(firstname, surname, email, username, passwd, user_type)
VALUES ('George', 'Lalas','george@george.com','george',SHA1('123'), 2);
/*SECURITY test */

INSERT INTO User
(firstname, surname, email, username, passwd, user_type)
VALUES ('Tom', 'Tsontas','tomtsontas@tom.com','tomtsontas', SHA1('123'), 1);
/* ******* END test */


INSERT INTO `Visitor`
(user_id, visitor_level)
VALUES (2, 'senior');

INSERT INTO `Admin`
(user_id, access_level)
VALUES (1, 1);

