<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css"> BODY{ font-family:Arial, Helvetica, sans-serif } </style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Cargar datos Carnet - Sistemas Distribuidos</title>
<style type="text/css">
#apDiv1 {
	position: absolute;
	left: 0px;
	top: 0px;
	width: 1399px;
	height: 1048px;
	z-index: 1;
}
#apDiv2 {
	position: absolute;
	left: 77px;
	top: 158px;
	width: 603px;
	height: 532px;
	z-index: 2;
}
#apDiv3 {
	position: absolute;
	left: 867px;
	top: 141px;
	width: 481px;
	height: 536px;
	z-index: 3;
}
#apDiv4 {
	position: absolute;
	left: 71px;
	top: 829px;
	width: 1274px;
	height: 101px;
	z-index: 4;
}
#apDiv5 {
	position: absolute;
	left: 69px;
	top: 40px;
	width: 1299px;
	height: 55px;
	z-index: 5;
}
</style>
<link href="estilo1.css" rel="stylesheet" type="text/css" />
</head>
<body>
<?php
include_once("conexion.php");
$con=mysqli_connect($host,$usuario,$clave,$bd) or die("ERROR");
mysqli_set_charset($con,"utf8");
?>
<div id="apDiv1" class="principal">
<div id="apDiv2">
  <h2 align="center">Datos personales</h2>
  <form method="post" enctype="multipart/form-data">
<table width="598" height="274" border="0">
  <tr>
    <td width="235"><p>Número de identificación: </p></td>
    <td width="353"><input type="text"  value ="" name="cnumdoc" class="normal"  required/></td>
    </tr>
     <tr>
     <td width="235"><p>Nombre completo:</p></td>
     <td width="353"><input type="text" name="cnom" class="nombre"/></td>
     </tr> 
     <tr>
          <td width="235"><p>Edad:</p></td> 
     <td width="353"><input type="number" name="cedad" class="normal" min="18" max="99"/></td>
     </tr> 
     <tr>
     <td width="235"><p>Género: </p></td>
    <td width="353"><select class="normal" name="cgenero">
	<option selected value="0">Seleccionar</option>
    <option value="Masculino">Masculino</option>
    <option value="Femenino">Femenino</option>
    <option value="Otro">Otro</option>
</select></td>
     </tr>   
     <tr>
     <td width="235"> <p>Foto para el documento:</p></td>
     <td width="353"><input type="file" name="f" id="seleccionArchivos" class="file" accept="image/*"></td>
     </tr>     
</table>
<p align="center"><button type="submit" name="addf">Enviar datos</button>
</form>
<?php
if (isset($_POST['addf'])){
	$nd = $_POST['cnumdoc'];
	$nom = $_POST['cnom'];
	$edad = $_POST['cedad'];
	$genero = $_POST['cgenero'];
	$file = $_FILES['f']['name'];
	$extensiones = array(0=>'image/jpg',1=>'image/jpeg',2=>'image/png');
	$max_tamanyo = 1024 * 1024 * 8;
	if ($nd != "" && is_numeric($nd) && $nom != "" && $edad != "" && is_numeric($edad) && $genero != "" && ctype_alpha($genero) && isset($file) && $file != "") {
		if ( in_array($_FILES['f']['type'], $extensiones) ) {
			if ( $_FILES['f']['size']< $max_tamanyo ) {
				$image = $_FILES['f']['tmp_name'];
				$peso = $_FILES['f']['size'] / 1024; // Bytes to Kilobytes
				$image_info = getimagesize($_FILES["f"]["tmp_name"]);
				$ancho = $image_info[0];
				$alto = $image_info[1];
				$aux = explode("/", $_FILES['f']['type']);
				$formato = $aux[1];
				$nomf = "$nd-$nom";
				include_once ("mcript.php");
				$nomf = $encriptar($nomf);
				$nomf = str_ireplace("/","-",$nomf);
				$ruta_index = dirname(realpath(__FILE__));
				$nf = "$nomf.$formato";
				$ruta = "images\ ";
				$ruta = str_ireplace(" ",$nf,$ruta);
				//$imgContent = addslashes(file_get_contents($image));
				//$path = 'images/$nf';
				//$url = "http://192.168.0.14:80/servidor_imagenes/$path";
				//file_put_contents($ruta, base64_decode($imgContent));
				if(copy ($_FILES['f']['tmp_name'], $ruta) ) {
					echo 'Fichero guardado con éxito';
					include_once ("mcript.php");
					$ruta = $encriptar($ruta);
					include_once("date.php");
					$inserta = "INSERT INTO $bd.imagen VALUES ('$nf','$f','$f','$formato','$alto','$ancho','$peso','$ruta');";
					mysqli_query($con, $inserta) or die (mysqli_error());
					include_once ("mcript.php");
					$nd = $encriptar($nd);
					$inserta = "INSERT INTO $bd.persona VALUES ('$nd','$nom','$edad','$genero','$nf');";
					mysqli_query($con, $inserta) or die (mysqli_error());
					mysqli_close($con);
					include_once ("mcript.php");
					$ruta = $desencriptar($ruta);
					$nd = $desencriptar($nd);
					$m = "Datos e imagen guardados correctamente (ID Persona = - ID foto = $ruta";
					echo "<script type='text/javascript'>alert('$m'); window.location= 'cargar.php'</script>";
				}
				} else {
					mysqli_close($con);
					$m = "El tamaño de la imagen excede el permitido (1MB)";
					echo "<script type='text/javascript'>alert('$m'); window.location= 'cargar.php'</script>";
				}
		} else {
			mysqli_close($con); $m = "El formato de la imagen no es valido (solo se reciben imagenes en formato jpg, jpeg y png)";
			echo "<script type='text/javascript'>alert('$m');
			window.location= 'cargar.php'</script>";
		}
	} else {
		mysqli_close($con);
		$m = "Todos los campos deben estar llenos";
		echo "<script type='text/javascript'>alert('$m'); window.location= 'cargar.php'</script>";
	}
}
?>
</div>
<div id="apDiv3">
<h2 align="center">Previsualización imagen</h2>
      <img width="480" height="494" style="border:medium" id="imagenPrevisualizacion" src="images/sample.png"> <script src="show-images.js"></script>
</div>
<div id="apDiv4" align="center">
<table width="600" height="100" border="0">
<td width="295" align="center">
  <button style="width:200px"  type="submit" value="buscar" onclick = "location='buscar.php'">Buscar</button></td>
   <td width="293" align="center">
   <button style="width:200px"  type="submit" value="buscar" onclick = "location='/servidor_imagenes'">Inicio</button></td>
  </table>
</div>
<div id="apDiv5" align="center">
<h2 align="center" class="title">Carga de datos para carnet</h2>
</div>
</div>
</body>
</html>