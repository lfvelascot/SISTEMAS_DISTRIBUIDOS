
CREATE SCHEMA IF NOT EXISTS `bancapp_db` DEFAULT CHARACTER SET utf8 ;
USE `bancapp_db` ;

CREATE USER 'bancapp_user'@'%' IDENTIFIED BY 'B4nc4pP2021';
GRANT ALL PRIVILEGES ON bancapp_db.* TO 'bancapp_user'@'%';
flush privileges;

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

-- -----------------------------------------------------
-- Table `bancapp_db`.`estado_cuenta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancapp_db`.`estado_cuenta` (
  `nombre` VARCHAR(45) NOT NULL,
  `descrip` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`nombre`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `bancapp_db`.`tipo_cuenta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancapp_db`.`tipo_cuenta` (
  `nombre` VARCHAR(45) NOT NULL,
  `descrip` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`nombre`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `bancapp_db`.`cuenta_bancaria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancapp_db`.`cuenta_bancaria` (
  `num` VARCHAR(100) NOT NULL,
  `clave` VARCHAR(100) NOT NULL,
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
  `rol_nombre_rom` VARCHAR (45) NOT NULL,
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

-- -----------------------------------------------------
-- Table `bancapp_db`.`log`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancapp_db`.`log` (
  `fecha` DATETIME NOT NULL,
  `descrip` VARCHAR(45) NOT NULL,
  `cuenta` VARCHAR(100) NOT NULL,
  `accion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`fecha`,`cuenta`,`accion`),
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
  PRIMARY KEY (`accion`,`rol`),
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



INSERT INTO `accion` (`nombre`, `descrip`) VALUES
('Login exitoso', ''),
('Login fallido', ''),
('Logout exitoso', ''),
('Logout fallido', ''),
('Transferencia exitosa', ''),
('Transferencia fallida', ''),
('Ver estado Cuenta exitoso', ''),
('Ver estado Cuenta fallido', ''),
('Ver historial transacciones exitoso', ''),
('Ver historial transacciones fallido', '');

--
-- Volcado de datos para la tabla `estado_cuenta`
--

INSERT INTO `estado_cuenta` (`nombre`, `descrip`) VALUES
('ACTIVA', ''),
('BLOQUEADA', ''),
('SUSPENDIDA', '');

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`nombre_rom`, `descrip`) VALUES
('ADMINISTRADOR', ''),
('USUARIO', '');

--
-- Volcado de datos para la tabla `tipo_cuenta`
--

INSERT INTO `tipo_cuenta` (`nombre`, `descrip`) VALUES
('CORRIENTE', ''),
('DE AHORROS', '');


--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`num_doc`, `nombre`, `apellido`, `fecha_nam`, `correo_electronico`, `telefono`) VALUES
('2BD17CE7ECE96E1E3AE6E7F9870BEB47', 'Jose', 'Paez', '1997-09-01', '7C811645FD4E8E9C390936CB775199524F51526DC6F631B1119E482C6FFBB7AC', '44CDA2E7AA5F861852BCFB87A889BACE'),
('B2599E66FE460BEE65E41A5CFBF43675', 'Pepito', 'Perez', '2000-10-01', '5791E871E7E8EA699AE4950B9ACEC92B86069087D1CF9713131C87457768E3B4', '3DFC242C4B09CA28FA5125431C342203');
--
-- Volcado de datos para la tabla `cuenta_app`
--

INSERT INTO `cuenta_app` (`user_num_doc`, `contrasena`, `ultimo_ingreso`, `estado_cuenta_nombre`, `rol_nombre_rom`) VALUES
('2BD17CE7ECE96E1E3AE6E7F9870BEB47', '5305DD8B416641356E2396F899339206', '2021-10-13 19:33:02', 'ACTIVA', 'USUARIO'),
('B2599E66FE460BEE65E41A5CFBF43675', '5305DD8B416641356E2396F899339206', '2021-10-13 23:48:31', 'ACTIVA', 'USUARIO');

--
-- Volcado de datos para la tabla `cuenta_bancaria`
--

INSERT INTO `cuenta_bancaria` (`num`, `clave`, `saldo`, `estado_cuenta`, `tipo_cuenta`, `usuario`) VALUES
('AE4517D40DE1AADBD23E64B2EDCEBBB1', 'AE4517D40DE1AADBD23E64B2EDCEBBB1', 90000, 'ACTIVA', 'CORRIENTE', '2BD17CE7ECE96E1E3AE6E7F9870BEB47'),
('B726C5BAAEB81B014754FCA568312471', 'AE4517D40DE1AADBD23E64B2EDCEBBB1', 110000, 'ACTIVA', 'CORRIENTE', 'B2599E66FE460BEE65E41A5CFBF43675');

--
-- Volcado de datos para la tabla `log`
--

INSERT INTO `log` (`fecha`, `descrip`, `cuenta`, `accion`) VALUES
('2021-10-13 18:29:19', 'Sin problemas', '2BD17CE7ECE96E1E3AE6E7F9870BEB47', 'Login exitoso'),
('2021-10-13 19:04:11', 'Salida Normal', '2BD17CE7ECE96E1E3AE6E7F9870BEB47', 'Logout Exitoso'),
('2021-10-13 19:12:00', 'Sin problemas', '2BD17CE7ECE96E1E3AE6E7F9870BEB47', 'Login exitoso'),
('2021-10-13 19:16:32', 'Sin problemas', '2BD17CE7ECE96E1E3AE6E7F9870BEB47', 'Login exitoso'),
('2021-10-13 19:33:02', 'Sin problemas', '2BD17CE7ECE96E1E3AE6E7F9870BEB47', 'Login exitoso'),
('2021-10-13 20:03:19', 'Sin problemas', '2BD17CE7ECE96E1E3AE6E7F9870BEB47', 'Transferencia exitosa');


--
-- Volcado de datos para la tabla `transferencia`
--

INSERT INTO `transferencia` (`id_trans`, `valor`, `cuenta_origen`, `cuenta_destino`, `fecha`) VALUES
('B06EBF6565659C493A87CD2B8B7192CAE764841E593B22DBEF7AF8BFFCFD223C86069087D1CF9713131C87457768E3B4', 10000, 'AE4517D40DE1AADBD23E64B2EDCEBBB1', 'B726C5BAAEB81B014754FCA568312471', '2021-10-13 20:03:19');
