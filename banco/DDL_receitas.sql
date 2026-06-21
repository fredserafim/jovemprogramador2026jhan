-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema caderno_receita
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema caderno_receita
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `caderno_receita` DEFAULT CHARACTER SET utf8 ;
USE `caderno_receita` ;

-- -----------------------------------------------------
-- Table `caderno_receita`.`ingredientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caderno_receita`.`ingredientes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ingrediente` VARCHAR(45) NOT NULL,
  `unidade_medida` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `caderno_receita`.`preparo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caderno_receita`.`preparo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `modo_preparo` VARCHAR(2000) NOT NULL,
  `tempo_preparo` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `caderno_receita`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caderno_receita`.`usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `senha` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `caderno_receita`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caderno_receita`.`categoria` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `classificacao` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `caderno_receita`.`complexidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caderno_receita`.`complexidade` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `dificuldade` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `caderno_receita`.`receita`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caderno_receita`.`receita` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(45) NOT NULL,
  `tempo` INT NOT NULL,
  `complexidade_id` INT NOT NULL,
  `porcoes` INT NOT NULL,
  `categoria_id` INT NOT NULL,
  `preparo_id` INT NOT NULL,
  `usuario_id` INT NOT NULL,
  `data_criacao` DATE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_receita_preparo1_idx` (`preparo_id` ASC) ,
  INDEX `fk_receita_usuario1_idx` (`usuario_id` ASC) ,
  INDEX `fk_receita_categoria1_idx` (`categoria_id` ASC) ,
  INDEX `fk_receita_complexidade1_idx` (`complexidade_id` ASC) ,
  CONSTRAINT `fk_receita_preparo1`
    FOREIGN KEY (`preparo_id`)
    REFERENCES `caderno_receita`.`preparo` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_receita_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `caderno_receita`.`usuario` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_receita_categoria1`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `caderno_receita`.`categoria` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_receita_complexidade1`
    FOREIGN KEY (`complexidade_id`)
    REFERENCES `caderno_receita`.`complexidade` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `caderno_receita`.`ferramentas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caderno_receita`.`ferramentas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `utensilios` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `utensilios_UNIQUE` (`utensilios` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `caderno_receita`.`ingrediente_receita`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caderno_receita`.`ingrediente_receita` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `receita_id` INT NOT NULL,
  `ingredientes_id` INT NOT NULL,
  `quantidade` DECIMAL(10,2) NOT NULL,
  `observacao` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ingrediente_receita_receita1_idx` (`receita_id` ASC) ,
  INDEX `fk_ingrediente_receita_ingredientes1_idx` (`ingredientes_id` ASC) ,
  CONSTRAINT `fk_ingrediente_receita_receita1`
    FOREIGN KEY (`receita_id`)
    REFERENCES `caderno_receita`.`receita` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_ingrediente_receita_ingredientes1`
    FOREIGN KEY (`ingredientes_id`)
    REFERENCES `caderno_receita`.`ingredientes` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `caderno_receita`.`ferramenta_receita`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caderno_receita`.`ferramenta_receita` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ferramentas_id` INT NOT NULL,
  `receita_id` INT NOT NULL,
  INDEX `fk_Ferramentas_receitas_ferramentas1_idx` (`ferramentas_id` ASC) ,
  INDEX `fk_Ferramentas_receitas_receita1_idx` (`receita_id` ASC) ,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Ferramentas_receitas_ferramentas1`
    FOREIGN KEY (`ferramentas_id`)
    REFERENCES `caderno_receita`.`ferramentas` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Ferramentas_receitas_receita1`
    FOREIGN KEY (`receita_id`)
    REFERENCES `caderno_receita`.`receita` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
