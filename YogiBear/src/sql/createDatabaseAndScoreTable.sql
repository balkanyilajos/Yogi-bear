CREATE DATABASE IF NOT EXISTS `yogi-bear`;
USE `yogi-bear`;

CREATE TABLE IF NOT EXISTS score(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    point INT NOT NULL,
    time VARCHAR(10) NOT NULL
);