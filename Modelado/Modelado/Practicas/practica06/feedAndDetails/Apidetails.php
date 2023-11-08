<?php
include_once 'commentsAndLikes/likeComent.php';


/*
*Clase  para manejar los detalles de un perro y sus comentarios
*/
class Detalles extends likeComent{

public function __construct($key,$dog_id){
     //Contructor de la clase padre
     parent::__construct($key,$dog_id);
}

/**
* Devuelve la información del perro
*
* @access private
* @param  array  $row  arreglo que contiene la informacion del perro
* @return $data  $dato deseado
*/

private function getData ($row){
      $data=array(
      "dog_id"=>$this->dog_id,
      "nombre"=>"$row->nombre",
      "imagen"=>"$row->imagen",
      "no_likes"=>"$row->no_likes"
      );
      return $data ;
  }
    /**
    * Devuelve la información del perro junto con sus comentarios
    *
    *  El metodo devuelve la informacion de un perro, junto con
    *  todos sus comentarios en formato json
    *
    * @access public

    */
  public function details(){
    $sql = "SELECT * FROM comentarios NATURAL JOIN perros WHERE perro_id='$this->dog_id'";
    $list = [];
    $comentarios=[];
    if($result =$this->mysqli->query($sql)){
        if($result->num_rows > 0){
            while($row = $result->fetch_object()){
            $list=$this->getData($row);
              $data=array(
                "fecha"=>$row->fecha,
                "user_id"=>"$row->user_id",
                "text"=>"$row->texto"
                );
               array_push($comentarios,$data);
            }
            $result->free();
            echo json_encode(array("perro" => $list, "comentarios" =>$comentarios));
        } else{
            $sql = "SELECT * FROM perros WHERE perro_id='$this->dog_id'";
          if($result =$this->mysqli->query($sql))
                if($result->num_rows > 0){
                        $row =$result->fetch_object();
                        $list=$this->getData($row);
                      }
                      echo json_encode(array("perro" => $list, "comentarios" =>$comentarios));
        }
    } else{
      echo "ERROR:No se puede ejecutar la sentencia: $sql. ";
    }

    }

}
?>
