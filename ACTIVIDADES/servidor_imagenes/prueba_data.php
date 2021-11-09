<?php
$meta = exif_read_data('test.jpeg');

echo 'Camara: <b>' . $meta['Model'] . '</b><br>';
echo 'Alto y ancho: <b>' . $meta['COMPUTED']['Height'] . 'x' . $meta['COMPUTED']['Width'] . '</b><br>';
echo 'Fecha: <b>' . $meta['DateTimeOriginal'] . '</b><br>';
 ?>