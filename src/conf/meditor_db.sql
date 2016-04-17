/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  adamopoulo
 * Created: Apr 17, 2016
 */

CREATE TABLE `Visitor` (
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
  email      varchar(50) NOT NULL,
  visit_id   int,
  admin_id   int, 
  user_type  varchar(50) NOT NULL,
  PRIMARY KEY (`email`, user_type),
  constraint fk_vstemail FOREIGN KEY (`email`) REFERENCES `Visitor` (email) 
  ON DELETE CASCADE ON UPDATE CASCADE,
  constraint fk_admemail FOREIGN KEY (`email`) REFERENCES `Admin` (email) 
  ON DELETE CASCADE ON UPDATE CASCADE,
  constraint fk_vstid FOREIGN KEY (visit_id) REFERENCES Visitor (id) ON DELETE CASCADE ON UPDATE CASCADE,
  constraint fk_admid FOREIGN KEY (admin_id) REFERENCES `Admin` (id) ON DELETE CASCADE ON UPDATE CASCADE
) ;