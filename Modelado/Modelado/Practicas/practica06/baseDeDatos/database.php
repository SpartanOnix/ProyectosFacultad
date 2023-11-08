<?php

/**
* Clase que se encarga de realizar la conexion con la base de datos
*
*/
class baseDeDatos{

 public $mysqli;
  /**
  * Devuelve la conexion con la base de datos
  *
  * @access public
  * @param  array  $row  arreglo que contiene la informacion del perro
  * @return $data
  */
    public function __construct(){
        try{
          //creo la conexion con la base de datos
          //EStos datos de conexion son los que tenemos en el host
         $this->mysqli = new mysqli("localhost","id11690803_root","modelado","id11690803_perros");
          //si ocurre un error lanzamos un mensaje y teminamos
        }catch(mysqli_sql_exception $e){
            die("ERROR: Could not connect. " . $mysqli->connect_error);
        }
    }
}
?>
