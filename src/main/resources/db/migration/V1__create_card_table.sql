CREATE DATABASE IF NOT EXISTS `my_schema`;
USE `my_schema`;

CREATE TABLE IF NOT EXISTS `card_table` (
  `id_card` int NOT NULL AUTO_INCREMENT,
  `card_name` varchar(100) NOT NULL,
  `price` double NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`id_card`)
);

