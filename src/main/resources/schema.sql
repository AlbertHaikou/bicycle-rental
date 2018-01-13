CREATE DATABASE bicycle_rent;
USE bicycle_rent;
CREATE TABLE `User` (
  `id`        INTEGER     NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `firstName` VARCHAR(20) NOT NULL,
  `lastName`  VARCHAR(20) NOT NULL,
  `email`     VARCHAR(20) NOT NULL UNIQUE,
  `password`  VARCHAR(20) NOT NULL
);

INSERT INTO `User` (`firstName`, `lastName`, `email`, `password`, `role`)
VALUES ('Albert', 'Haikou', 'gaolbertas@gmail.com', 'root', 'ADMINISTRATOR');
INSERT INTO `User` (`firstName`, `lastName`, `email`, `password`, `role`)
VALUES ('Petr', 'Petrov', 'Petr@tut.by', 'root', 'MANAGER');
INSERT INTO `User` (`firstName`, `lastName`, `email`, `password`, `role`)
VALUES ('Ivan', 'Ivanov', 'Ivan@tut.by', 'root', 'CLIENT');

CREATE TABLE `Parking` (
  `id`     INTEGER     NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `street` VARCHAR(20) NOT NULL UNIQUE
);

INSERT INTO `Parking` (`street`) VALUES ('Cобинова, 11');
INSERT INTO `Parking` (`street`) VALUES ('Волгоградская, 45');
INSERT INTO `Parking` (`street`) VALUES ('Шишкина, 33');
INSERT INTO `Parking` (`street`) VALUES ('Серова, 96');
INSERT INTO `Parking` (`street`) VALUES ('Шаранговича, 17');
INSERT INTO `Parking` (`street`) VALUES ('Пушкина, 45');

CREATE TABLE `Bikes` (
  `id`            INTEGER     NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `type`          VARCHAR(20) NOT NULL,
  `model`         VARCHAR(20) NOT NULL,
  `size`          VARCHAR(20) NOT NULL,
  `isAvailable`   BOOLEAN,
  `fk_parking_id` INTEGER     NOT NULL,

  CONSTRAINT `fk_Bikes_to_Parking` FOREIGN KEY (`fk_parking_id`) REFERENCES `Parking` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);
INSERT INTO `Bikes` (`type`, `model`, `size`, `isAvailable`, `fk_Parking_id`)
VALUES ('мужской', 'stels', '20"', '1', '1');
INSERT INTO `Bikes` (`type`, `model`, `size`, `isAvailable`, `fk_Parking_id`)
VALUES ('женский', 'cube', '16"', '0', '2');
INSERT INTO `Bikes` (`type`, `model`, `size`, `isAvailable`, `fk_Parking_id`)
VALUES ('мужской', 'smart', '19"', '1', '3');
INSERT INTO `Bikes` (`type`, `model`, `size`, `isAvailable`, `fk_Parking_id`)
VALUES ('мужской', 'forward', '18"', '1', '1');
INSERT INTO `Bikes` (`type`, `model`, `size`, `isAvailable`, `fk_Parking_id`)
VALUES ('женский', 'cube', '19"', '0', '2');
INSERT INTO `Bikes` (`type`, `model`, `size`, `isAvailable`, `fk_Parking_id`)
VALUES ('мужской', 'smart', '14"', '1', '4');

CREATE TABLE `SupportItem` (
  `id`          INTEGER     NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `fk_bikes_id` INTEGER     NOT NULL,
  `description` VARCHAR(55) NOT NULL,
  `status`      BOOLEAN     NOT NULL,

  CONSTRAINT `fk_Support_to_Bikes` FOREIGN KEY (`fk_bikes_id`) REFERENCES `Bikes` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE

);

INSERT INTO `SupportItem` (`fk_Bikes_id`, `description`, `status`) VALUES ('1', 'пробито колесо', '1');
INSERT INTO `SupportItem` (`fk_Bikes_id`, `description`, `status`) VALUES ('2', 'сломана педаль', '0');


CREATE TABLE `RentItem` (
  `id`          INTEGER   NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `fk_bikes_id` INTEGER   NOT NULL,
  `fk_users_id` INTEGER   NOT NULL,
  `fromDate`        TIMESTAMP NOT NULL,
  `status`      BOOLEAN   NOT NULL,

  CONSTRAINT `fk_RentItem_to_Bikes` FOREIGN KEY (`fk_bikes_id`) REFERENCES `Bikes` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,

  CONSTRAINT `fk_RentItem_to_Users` FOREIGN KEY (`fk_users_id`) REFERENCES `Users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE

);
  

