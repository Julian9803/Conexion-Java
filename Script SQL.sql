-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema Biblioteca
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Biblioteca
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Biblioteca` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `Biblioteca` ;

-- -----------------------------------------------------
-- Table `Biblioteca`.`tipolibro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Biblioteca`.`tipolibro` (
  `codigo` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `TipoLibro` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`codigo`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Biblioteca`.`libro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Biblioteca`.`libro` (
  `codigo` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `librocol` VARCHAR(45) NOT NULL COMMENT '',
  `nombre` VARCHAR(45) NOT NULL COMMENT '',
  `anio` INT NOT NULL COMMENT '',
  `estado` VARCHAR(45) NOT NULL COMMENT '',
  `tipolibro` INT NOT NULL COMMENT '',
  PRIMARY KEY (`codigo`)  COMMENT '',
  INDEX `fk_libro_tipolibro_idx` (`tipolibro` ASC)  COMMENT '',
  CONSTRAINT `fk_libro_tipolibro`
    FOREIGN KEY (`tipolibro`)
    REFERENCES `Biblioteca`.`tipolibro` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Biblioteca`.`tipousuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Biblioteca`.`tipousuario` (
  `codigo` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `TipoUsuario` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`codigo`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Biblioteca`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Biblioteca`.`usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `documento` BIGINT NOT NULL COMMENT '',
  `nombre` VARCHAR(45) NOT NULL COMMENT '',
  `direccion` VARCHAR(45) NOT NULL COMMENT '',
  `telefono` BIGINT NULL COMMENT '',
  `TipoUsuario` INT NOT NULL COMMENT '',
  INDEX `fk_usuario_tipousuario1_idx` (`TipoUsuario` ASC)  COMMENT '',
  PRIMARY KEY (`idUsuario`)  COMMENT '',
  CONSTRAINT `fk_usuario_tipousuario1`
    FOREIGN KEY (`TipoUsuario`)
    REFERENCES `Biblioteca`.`tipousuario` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Biblioteca`.`prestamo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Biblioteca`.`prestamo` (
  `libro` INT NOT NULL COMMENT '',
  `usuario` INT NOT NULL COMMENT '',
  `fechaprestamo` VARCHAR(45) NOT NULL COMMENT '',
  `fechadevolucion` VARCHAR(45) NOT NULL COMMENT '',
  INDEX `fk_prestamo_libro1_idx` (`libro` ASC)  COMMENT '',
  INDEX `fk_prestamo_usuario1_idx` (`usuario` ASC)  COMMENT '',
  CONSTRAINT `fk_prestamo_libro1`
    FOREIGN KEY (`libro`)
    REFERENCES `Biblioteca`.`libro` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_prestamo_usuario1`
    FOREIGN KEY (`usuario`)
    REFERENCES `Biblioteca`.`usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
