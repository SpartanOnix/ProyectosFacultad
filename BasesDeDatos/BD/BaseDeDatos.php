<?php
/**
 * Archivo para conectar con la base de datos
 */
$mysqli = new mysqli("localhost", "root", "", "perros");
if ($mysqli === false) {
    die("ERROR: No se pudo conectar. " . $mysqli->connect_error);
}
?>