DROP DATABASE  IF EXISTS `dashboard_datasus`;

CREATE DATABASE IF NOT EXISTS `dashboard_datasus` CHARSET utf8;

USE `dashboard_datasus`;

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuarios`(
	`idUsuario` INT NOT NULL AUTO_INCREMENT,
	`email` VARCHAR(100) NOT NULL UNIQUE,
	`senha` VARCHAR(255) NOT NULL,
	`nome` VARCHAR(90) NOT NULL,
	`cpf` VARCHAR(14) NOT NULL,
	`endereco` VARCHAR(50) DEFAULT NULL,
	`bairro` VARCHAR(30) DEFAULT NULL,
	`cidade` VARCHAR(50) DEFAULT NULL,
	`uf` VARCHAR(2) DEFAULT NULL,
	`cep` VARCHAR(9) DEFAULT NULL,
	`telefone` VARCHAR(15) NOT NULL,
	PRIMARY KEY (`idUsuario`)
);

select * from usuarios;