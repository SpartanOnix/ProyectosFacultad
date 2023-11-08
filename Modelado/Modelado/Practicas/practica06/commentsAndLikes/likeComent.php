<?php
include_once 'baseDeDatos/database.php';


/*
Clase abstracta que extiende de la clase baseDeDatos
*las clase que extiendan de ella podran hacer uso de una conexion a  la base de datos y del metodo validaKey
*/
class likeComent extends baseDeDatos
{

   //id del perro
   public $dog_id;
   //key del usuario que dio el like
   public $key;


   public function __construct($key, $dog_id)
   {
       //Contructor de la clase padre
       parent::__construct();
       $this->dog_id = $dog_id;
       $this->key    = $key;
   }

   //Valida que la key introducida sea valida
   public function validaKey()
   {
       $sql = "SELECT * FROM usuarios WHERE userkey IN ('$this->key')";
       if ($result = $this->mysqli->query($sql)) {
           if ($result->num_rows > 0) {
               return 1;
           }
       } else {
           return 0;
       }

   }

}

?>
