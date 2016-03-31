DROP DATABASE IF EXISTS `test`;
CREATE DATABASE `test` CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `test`;

CREATE TABLE `user` (
  `id` INT(8) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(25) NOT NULL DEFAULT 'unknownUser',
  `age` INT(11) UNSIGNED NOT NULL DEFAULT '1',
  `isAdmin` BIT NOT NULL DEFAULT FALSE,
  `createdDate` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO `user` (`name`,age,isAdmin) VALUES ('Anton','26',TRUE);
INSERT INTO `user` (`name`,age,isAdmin) VALUES ('Vasya','22',TRUE);
INSERT INTO `user` (`name`,age,isAdmin) VALUES ('Petya','12',FALSE);
INSERT INTO `user` (`name`,age,isAdmin) VALUES ('Inokentii','23',FALSE);
INSERT INTO `user` (`name`,age,isAdmin) VALUES ('Юля','34',FALSE);
INSERT INTO `user` (`name`,age,isAdmin) VALUES ('Лена','32',FALSE);
INSERT INTO `user` (`name`,age,isAdmin) VALUES ('Игорь','27',TRUE);
INSERT INTO `user` (`name`,age,isAdmin) VALUES ('Jack','18',FALSE);
INSERT INTO `user` (`name`,age,isAdmin) VALUES ('Brandon','54',FALSE);
INSERT INTO `user` (`name`,age,isAdmin) VALUES ('Руслан','22',FALSE);
INSERT INTO `user` (`name`,age,isAdmin) VALUES ('Григорий','33',FALSE);
COMMIT;