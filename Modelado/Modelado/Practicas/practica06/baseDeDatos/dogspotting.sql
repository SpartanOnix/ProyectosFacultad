CREATE DATABASE perros;

CREATE TABLE `perros`.`usuarios` ( `user_id` INT NOT NULL AUTO_INCREMENT , `user` VARCHAR(100) NOT NULL , `pass` VARCHAR(100) NOT NULL , `userkey` VARCHAR(100) NOT NULL , PRIMARY KEY (`user_id`)) ENGINE = InnoDB;

CREATE TABLE `perros`.`likes` ( `like_id` INT NOT NULL AUTO_INCREMENT , `user_id` INT NOT NULL , `perro_id` INT NOT NULL , PRIMARY KEY (`like_id`)) ENGINE = InnoDB;

CREATE TABLE `perros`.`comentarios` ( `coment_id` INT NOT NULL AUTO_INCREMENT , `user_id` INT NOT NULL , `perro_id` INT NOT NULL , `texto` TEXT NOT NULL,`date` DATE NOT NULL DEFAULT CURRENT_TIMESTAMP , PRIMARY KEY (`coment_id`)) ENGINE = InnoDB;

ALTER TABLE `comentarios` ADD `fecha` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ;

CREATE TABLE `perros`.`perros` ( `perro_id` INT NOT NULL AUTO_INCREMENT , `nombre` VARCHAR(100) NOT NULL, `imagen` TEXT NOT NULL , `no_likes` INT NOT NULL , PRIMARY KEY (`perro_id`)) ENGINE = InnoDB;

ALTER TABLE `comentarios` ADD CONSTRAINT `user-coment` FOREIGN KEY (`user_id`) REFERENCES `usuarios`(`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `comentarios` ADD CONSTRAINT `perro-coment` FOREIGN KEY (`perro_id`) REFERENCES `perros`(`perro_id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `likes` ADD CONSTRAINT `user-like` FOREIGN KEY (`user_id`) REFERENCES `usuarios`(`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `likes` ADD CONSTRAINT `perro-like` FOREIGN KEY (`perro_id`) REFERENCES `perros`(`perro_id`) ON DELETE CASCADE ON UPDATE CASCADE;
