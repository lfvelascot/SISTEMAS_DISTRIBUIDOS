
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
