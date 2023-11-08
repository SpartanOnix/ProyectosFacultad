<?php

//se requiere para realizar la conexion con la base de datos
include_once 'likeComent.php';

/**
 * Esta clase obtiene los comentarios y likes que un usuario da y los actualiza en la base de datos.
 *
 *
 * @author anonimo
 */
class likes extends likeComent
{

    /**
     *inicializa la conexion con la base de datos,se  asignan el  key y id correspondientes,
     * si la vatiable text no se paso como parametro se inicializa como " " por defecto.
     *
     * @access public
     */
    public function __construct($key, $dog_id)
    {
         //Contructor de la clase padre
        parent::__construct($key, $dog_id);
    }


    /**
     * obtiene un dato de la base de datos dados los siguientes parametros
     *
     * @param  string $table tabla a consultar
     * @param  string $cond campo que se desea consultar
     * @param  array  $param condicion para obtenr el dato
     * @param  array  $info  dato que se desea obtener
     *
     * @return  $data dato que se obtuvo
     * @access private
     */
    private function getData($table, $cond, $param, $info)
    {
        $sql    = "SELECT * FROM $table WHERE $cond='$param'";
        $result = $this->mysqli->query($sql);
        if ($result->num_rows > 0) {
            while ($row = $result->fetch_assoc()) {
                $data = $row[$info];
                return $data;
            }
        }
    }



    /**
     *Elimina una relacion entre una imagen y un usuario
     *
     * Elimina la relacion entre un usuario y la imagen ala que ya le dio like
     * modifica el contador de likes de la imagen, decrementandolo en 1
     *
     * @access private
     */
    private function dislike()
    {
        $id  = $this->getData("usuarios", "userkey", $this->key, "user_id");
        $sql = "DELETE FROM `likes` WHERE `likes`.`perro_id` = '$this->dog_id' AND `likes`.`user_id`='$id'";
        if ($result = $this->mysqli->query($sql)) {
            $no_likes = $this->getData("perros", "perro_id", $this->dog_id, "no_likes") - 1;
            $sql      = "UPDATE `perros` SET `no_likes` = '$no_likes' WHERE `perros`.`perro_id` = $this->dog_id";
            if ($result = $this->mysqli->query($sql)) {
                echo "realizado se elimino un like";
            } else {
                echo "ocurrio un error al eliminar el numero de  likes ";
            }
        } else {
            echo "ERROR:No se puede ejecutar la sentencia: $sql. ";
        }
    }



    /**
     * Verifica si un usuario ya le dio  like a una imagen
     *
     * Si el usuario ya le sio like a una imagen, se manda a llamar al metodo dislike
     * en donde se elimina la relacion y el like de la base de datos, de otro modo se
     *  aumenta el numero de likes de la imagen y se hace la relacion.
     *
     *
     * @access public
      */

    public function likeDislike()
    {
        $id  = $this->getData("usuarios", "userkey", $this->key, "user_id");
        $sql = "SELECT * FROM likes  WHERE user_id='$id' AND perro_id='$this->dog_id'";
        if ($result = $this->mysqli->query($sql)) {
            if ($result->num_rows > 0) {
                $this->dislike();
            } else {
                $this->agregaLike();
            }
        } else {
            echo "error";
        }

    }

    /**
     * Agrega un like al id del perro que se recibio
     *
     *Se anexa la relacion entre el usuario que dio el like y
     * la imagen que lo recibio.
     *check uniuqie
     *in
     *
     * @access private
      */

    private function agregaLike()
    {
        $id  = $this->getData("usuarios", "userkey", $this->key, "user_id");
        $sql = "INSERT INTO `likes` (`like_id`, `user_id`, `perro_id`) VALUES (NULL, '$id', '$this->dog_id')";
        if ($result = $this->mysqli->query($sql)) {
            $no_likes = $this->getData("perros", "perro_id", $this->dog_id, "no_likes") + 1;
            $sql      = "UPDATE `perros` SET `no_likes` = '$no_likes' WHERE `perros`.`perro_id` = $this->dog_id";
            if ($result = $this->mysqli->query($sql)) {
                echo "realizado se inserto in like";
            } else {
                echo "ocurrio un error al aumentar el numero de  likes ";
            }
        } else {
            echo "ERROR:No se puede ejecutar la sentencia: $sql. ";
        }
    }

}

?>
