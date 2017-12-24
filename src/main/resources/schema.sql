create database RentBike_DB;
use RentBike_DB;
CREATE TABLE `Users` (
  `id` INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `firstName` varchar(20) NOT NULL,
  `lastName` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL UNIQUE,
  `password` varchar(20) NOT NULL);
  
INSERT INTO `Users` (`firstName`,`lastName`,`email`,`password`) VALUES ('Denis','Girich','Denis@tut.by','root');  
INSERT INTO `Users` (`firstName`,`lastName`,`email`,`password`) VALUES ('Petr','Petrov','Petr@tut.by','root');  
INSERT INTO `Users` (`firstName`,`lastName`,`email`,`password`) VALUES ('Ivan','Ivanov','Ivan@tut.by','root');  

CREATE TABLE `Roles` (
  `id` INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `roleName` VARCHAR(20) NOT NULL UNIQUE
);

INSERT INTO `Roles` (`roleName`) VALUES ('admin');  
INSERT INTO `Roles` (`roleName`) VALUES ('support'); 
INSERT INTO `Roles` (`roleName`) VALUES ('client'); 

CREATE TABLE `Users_to_Roles` (
  `id` INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `fk_role_id` INTEGER NOT NULL,
  `fk_user_id` INTEGER NOT NULL,

  CONSTRAINT `fk_to_role` FOREIGN KEY (`fk_role_id`) REFERENCES `Roles` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,

  CONSTRAINT `fk_to_user` FOREIGN KEY (`fk_user_id`) REFERENCES `Users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    
  CONSTRAINT `unique_role_user` UNIQUE (fk_role_id, fk_user_id)
);

INSERT INTO `Users_to_Roles` (`fk_user_id`, `fk_role_id`) VALUES ('1','1');
INSERT INTO `Users_to_Roles` (`fk_user_id`, `fk_role_id`) VALUES ('2','2');
INSERT INTO `Users_to_Roles` (`fk_user_id`, `fk_role_id`) VALUES ('2','3');
INSERT INTO `Users_to_Roles` (`fk_user_id`, `fk_role_id`) VALUES ('3','3');


CREATE TABLE `Parking` (
  `id` INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `street` varchar(20) NOT NULL UNIQUE
);
  
INSERT INTO `Parking` (`street`) VALUES ('Cобинова, 11');  
INSERT INTO `Parking` (`street`) VALUES ('Волгоградская, 45');
INSERT INTO `Parking` (`street`) VALUES ('Шишкина, 33');
INSERT INTO `Parking` (`street`) VALUES ('Серова, 96');
INSERT INTO `Parking` (`street`) VALUES ('Шаранговича, 17');
INSERT INTO `Parking` (`street`) VALUES ('Пушкина, 45');

CREATE TABLE `Bikes` (
  `id` INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `type` varchar(20) NOT NULL,
  `model` varchar(20) NOT NULL,
  `size` varchar(20) NOT NULL,
   `isAvailable` BOOLEAN ,
  `fk_parking_id` INTEGER NOT NULL,
  
   CONSTRAINT `fk_Bikes_to_Parking` FOREIGN KEY (`fk_parking_id`) REFERENCES `Parking` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
  );
INSERT INTO `Bikes` (`type`, `model`,`size`,`isAvailable`,`fk_Parking_id`) VALUES ('мужской', 'stels','20"','1','1');
INSERT INTO `Bikes` (`type`, `model`,`size`,`isAvailable`,`fk_Parking_id`) VALUES ('женский', 'cube','16"','0','2');
INSERT INTO `Bikes` (`type`, `model`,`size`,`isAvailable`,`fk_Parking_id`) VALUES ('мужской', 'smart','19"','1','3');
INSERT INTO `Bikes` (`type`, `model`,`size`,`isAvailable`,`fk_Parking_id`) VALUES ('мужской', 'forward','18"','1','1');
INSERT INTO `Bikes` (`type`, `model`,`size`,`isAvailable`,`fk_Parking_id`) VALUES ('женский', 'cube','19"','0','2');
INSERT INTO `Bikes` (`type`, `model`,`size`,`isAvailable`,`fk_Parking_id`) VALUES ('мужской', 'smart','14"','1','4');

CREATE TABLE `SupportItem` (
  `id` INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `fk_bikes_id` INTEGER NOT NULL,
  `description` VARCHAR(55) NOT NULL,
  `status` BOOLEAN NOT NULL,
  
  CONSTRAINT `fk_Support_to_Bikes` FOREIGN KEY (`fk_bikes_id`) REFERENCES `Bikes` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
  
);

INSERT INTO `SupportItem` (`fk_Bikes_id`,`description`,`status`) VALUES ('1','пробито колесо','1'); 
INSERT INTO `SupportItem` (`fk_Bikes_id`,`description`,`status`) VALUES ('2','сломана педаль','0');


CREATE TABLE `RentItem` (
  `id` INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `fk_bikes_id` INTEGER NOT NULL,
  `fk_users_id` INTEGER NOT NULL,
  `date` TIMESTAMP NOT NULL,
  `status` BOOLEAN NOT NULL,
  
  CONSTRAINT `fk_RentItem_to_Bikes` FOREIGN KEY (`fk_bikes_id`) REFERENCES `Bikes` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    
  CONSTRAINT `fk_RentItem_to_Users` FOREIGN KEY (`fk_users_id`) REFERENCES `Users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
  
);
  

