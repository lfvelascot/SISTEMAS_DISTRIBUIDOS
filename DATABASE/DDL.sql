CREATE SCHEMA IF NOT EXISTS `bancapp_db` DEFAULT CHARACTER SET utf8 ;
USE `bancapp_db` ;

-- -----------------------------------------------------
-- Table `bancapp_db`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancapp_db`.`user` (
  `num_doc` VARCHAR(100) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `apellido` VARCHAR(45) NOT NULL,
  `fecha_nam` DATE NOT NULL,
  `correo_electronico` VARCHAR(100) NOT NULL,
  `telefono` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`num_doc`))
ENGINE = InnoDB;

--------------------------------------------------------
-- Table `bancapp_db`.`rol`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancapp_db`.`rol` (
  `nombre_rom` VARCHAR(45) NOT NULL,
  `descrip` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`nombre_rom`))
ENGINE = InnoDB;

INSERT INTO `bancapp_db`.`rol` (`nombre_rom`,`descrip`) VALUES ("ADMINISTRADOR","");
INSERT INTO `bancapp_db`.`rol` (`nombre_rom`,`descrip`) VALUES ("USUARIO","");


-- -----------------------------------------------------
-- Table `bancapp_db`.`estado_cuenta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancapp_db`.`estado_cuenta` (
  `nombre` VARCHAR(45) NOT NULL,
  `descrip` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`nombre`))
ENGINE = InnoDB;

INSERT INTO `bancapp_db`.`estado_cuenta` (`nombre`,`descrip`) VALUES ("ACTIVA","");
INSERT INTO `bancapp_db`.`estado_cuenta` (`nombre`,`descrip`) VALUES ("BLOQUEADA","");
INSERT INTO `bancapp_db`.`estado_cuenta` (`nombre`,`descrip`) VALUES ("SUSPENDIDA","");

-- -----------------------------------------------------
-- Table `bancapp_db`.`tipo_cuenta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancapp_db`.`tipo_cuenta` (
  `nombre` VARCHAR(45) NOT NULL,
  `descrip` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`nombre`))
ENGINE = InnoDB;

INSERT INTO `bancapp_db`.`tipo_cuenta` (`nombre`,`descrip`) VALUES ("NORMAL","");

-- -----------------------------------------------------
-- Table `bancapp_db`.`cuenta_bancaria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancapp_db`.`cuenta_bancaria` (
  `num` VARCHAR(100) NOT NULL,
  `clave` VARCHAR(100) NOT NULL,
  `fecha_inicio` VARCHAR(45) NOT NULL,
  `fecha_fin` VARCHAR(45) NULL,
  `saldo` DOUBLE NOT NULL,
  `estado_cuenta` VARCHAR(45) NOT NULL,
  `tipo_cuenta` VARCHAR(45) NOT NULL,
  `usuario` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`num`),
  INDEX `fk_cuenta_bancaria_estado_cuenta1_idx` (`estado_cuenta` ASC)  ,
  INDEX `fk_cuenta_bancaria_tipo_cuenta1_idx` (`tipo_cuenta` ASC)  ,
  INDEX `fk_cuenta_bancaria_user1_idx` (`usuario` ASC)  ,
  UNIQUE INDEX `usuario_UNIQUE` (`usuario` ASC)  ,
  CONSTRAINT `fk_cuenta_bancaria_estado_cuenta1`
    FOREIGN KEY (`estado_cuenta`)
    REFERENCES `bancapp_db`.`estado_cuenta` (`nombre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cuenta_bancaria_tipo_cuenta1`
    FOREIGN KEY (`tipo_cuenta`)
    REFERENCES `bancapp_db`.`tipo_cuenta` (`nombre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cuenta_bancaria_user1`
    FOREIGN KEY (`usuario`)
    REFERENCES `bancapp_db`.`user` (`num_doc`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bancapp_db`.`cuenta_app`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancapp_db`.`cuenta_app` (
  `user_num_doc` VARCHAR(100) NOT NULL,
  `contrasena` VARCHAR(100) NOT NULL,
  `ultimo_ingreso` DATETIME NOT NULL,
  `estado_cuenta_nombre` VARCHAR(45) NOT NULL,
  `rol_nombre_rom` VARCHAR(45) NOT NULL,
  INDEX `fk_cuenta_app_estado_cuenta1_idx` (`estado_cuenta_nombre` ASC)  ,
  INDEX `fk_cuenta_app_rol1_idx` (`rol_nombre_rom` ASC)  ,
  INDEX `fk_cuenta_app_user1_idx` (`user_num_doc` ASC)  ,
  PRIMARY KEY (`user_num_doc`),
  CONSTRAINT `fk_cuenta_app_estado_cuenta1`
    FOREIGN KEY (`estado_cuenta_nombre`)
    REFERENCES `bancapp_db`.`estado_cuenta` (`nombre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cuenta_app_rol1`
    FOREIGN KEY (`rol_nombre_rom`)
    REFERENCES `bancapp_db`.`rol` (`nombre_rom`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cuenta_app_user1`
    FOREIGN KEY (`user_num_doc`)
    REFERENCES `bancapp_db`.`user` (`num_doc`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bancapp_db`.`transferencia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancapp_db`.`transferencia` (
  `id_trans` VARCHAR(100) NOT NULL,
  `valor` DOUBLE NOT NULL,
  `cuenta_origen` VARCHAR(100) NOT NULL,
  `cuenta_destino` VARCHAR(100) NOT NULL,
  `fecha` DATETIME NOT NULL,
  PRIMARY KEY (`id_trans`),
  INDEX `fk_transferencia_cuenta_bancaria_idx` (`cuenta_origen` ASC)  ,
  INDEX `fk_transferencia_cuenta_bancaria1_idx` (`cuenta_destino` ASC)  ,
  CONSTRAINT `fk_transferencia_cuenta_bancaria`
    FOREIGN KEY (`cuenta_origen`)
    REFERENCES `bancapp_db`.`cuenta_bancaria` (`num`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_transferencia_cuenta_bancaria1`
    FOREIGN KEY (`cuenta_destino`)
    REFERENCES `bancapp_db`.`cuenta_bancaria` (`num`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bancapp_db`.`accion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancapp_db`.`accion` (
  `nombre` VARCHAR(45) NOT NULL,
  `descrip` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`nombre`))
ENGINE = InnoDB;

INSERT INTO `bancapp_db`.`accion`(`nombre`,`descrip`) VALUES("Login exitoso","");
INSERT INTO `bancapp_db`.`accion`(`nombre`,`descrip`) VALUES("Login fallido","");
INSERT INTO `bancapp_db`.`accion`(`nombre`,`descrip`) VALUES("Logout exitoso","");
INSERT INTO `bancapp_db`.`accion`(`nombre`,`descrip`) VALUES("Logout fallido","");
INSERT INTO `bancapp_db`.`accion`(`nombre`,`descrip`) VALUES("Ver estado Cuenta exitoso","");
INSERT INTO `bancapp_db`.`accion`(`nombre`,`descrip`) VALUES("Ver estado Cuenta fallido","");
INSERT INTO `bancapp_db`.`accion`(`nombre`,`descrip`) VALUES("Transferencia exitosa","");
INSERT INTO `bancapp_db`.`accion`(`nombre`,`descrip`) VALUES("Transferencia fallida","");
INSERT INTO `bancapp_db`.`accion`(`nombre`,`descrip`) VALUES("Ver historial transacciones exitoso","");
INSERT INTO `bancapp_db`.`accion`(`nombre`,`descrip`) VALUES("Ver historial transacciones fallido","");
-- -----------------------------------------------------
-- Table `bancapp_db`.`log`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancapp_db`.`log` (
  `fecha` INT NOT NULL,
  `descrip` VARCHAR(45) NOT NULL,
  `cuenta` VARCHAR(100) NOT NULL,
  `accion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`fecha`),
  INDEX `fk_log_cuenta_app1_idx` (`cuenta` ASC)  ,
  INDEX `fk_log_accion1_idx` (`accion` ASC)  ,
  CONSTRAINT `fk_log_cuenta_app1`
    FOREIGN KEY (`cuenta`)
    REFERENCES `bancapp_db`.`cuenta_app` (`user_num_doc`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_log_accion1`
    FOREIGN KEY (`accion`)
    REFERENCES `bancapp_db`.`accion` (`nombre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bancapp_db`.`rol_accion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancapp_db`.`rol_accion` (
  `accion` VARCHAR(45) NOT NULL,
  `rol` VARCHAR(45) NOT NULL,
  INDEX `fk_rol_accion_accion1_idx` (`accion` ASC)  ,
  INDEX `fk_rol_accion_rol1_idx` (`rol` ASC)  ,
  CONSTRAINT `fk_rol_accion_accion1`
    FOREIGN KEY (`accion`)
    REFERENCES `bancapp_db`.`accion` (`nombre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_rol_accion_rol1`
    FOREIGN KEY (`rol`)
    REFERENCES `bancapp_db`.`rol` (`nombre_rom`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
