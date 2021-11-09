<?php
//Configuración del algoritmo de encriptación

//Debes cambiar esta cadena, debe ser larga y unica
//nadie mas debe conocerla
$clave  = 'appausa usbbog';

//Metodo de encriptación
$method = 'aes-256-cbc';

// Puedes generar una diferente usando la funcion $getIV()
$iv = base64_decode("C9fBxl1AgWcxY1/M8jfstw==");

 /*
 Encripta el contenido de la variable, enviada como parametro.
  */
$encriptar = function ($valor) use ($method, $clave, $iv) {
     return openssl_encrypt ($valor, $method, $clave, false, $iv);
 };
 
$telencriptar = function($num){
	$cad = strval($num);
	if (strlen($cad) == 10){
		$mitad1 = substr($cad, 0, -5);
		$mitad2 = substr($cad, -5, 6);
		return  $mitad1[4].$mitad1[2].$mitad1[0].$mitad1[1].$mitad1[3].$mitad2[4].$mitad2[2].$mitad2[0].$mitad2[1].$mitad2[3];
	} elseif(strlen($cad) == 7){
		return $cad[3].$cad[6].$cad[5].$cad[0].$cad[2].$cad[1].$cad[4];
	} 
 };
 
 $teldesencriptar = function($num){
	$cad = strval($num);
	if (strlen($cad) == 10){
		$mitad1 = substr($cad, 0, -5);
		$mitad2 = substr($cad, -5, 6);
		return  $mitad1[2].$mitad1[3].$mitad1[1].$mitad1[4].$mitad1[0].$mitad2[2].$mitad2[3].$mitad2[1].$mitad2[4].$mitad2[0];
	} elseif(strlen($cad) == 7){
		return $cad[3].$cad[5].$cad[4].$cad[0].$cad[6].$cad[2].$cad[1];
	}

 };

 /*
 Desencripta el texto recibido
 */
 $desencriptar = function ($valor) use ($method, $clave, $iv) {
     $encrypted_data = base64_decode($valor);
     return openssl_decrypt($valor, $method, $clave, false, $iv);
 };

 /*
 Genera un valor para IV
 */
 $getIV = function () use ($method) {
     return base64_encode(openssl_random_pseudo_bytes(openssl_cipher_iv_length($method)));
 };
 ?>