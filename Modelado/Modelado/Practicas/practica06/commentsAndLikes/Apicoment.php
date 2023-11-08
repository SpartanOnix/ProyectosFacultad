<?php

//se requiere para realizar la conexion con la base de datos
include_once 'likeComent.php';

/**
 * Esta clase obtiene los comentarios y likes que un usuario envia y los actualiza en la base de datos.
 *
 * @author  anonimo
 */
class Comentarios extends likeComent
{

private $text;
    /**
     *inicializa la conexion con la base de datos,se  asignan el  key y id correspondientes,
     * si la vatiable text no se paso como parametro se inicializa como " " por defecto.
     *
     * @access public
     */
    public function __construct($key, $dog_id,$text)
    {
        parent::__construct($key, $dog_id);
          $this->text = $text;
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
     * Agrega un comentario a la base de datos
     *
     *agrega un comentario al id del perro que se recibio
     *
     * @access public
     */
    public function agregaComent()
    {
        $id  = $this->getData("usuarios", "userkey", $this->key, "user_id");
        $sql = "INSERT INTO `comentarios` (`coment_id`, `user_id`, `perro_id`, `texto`) VALUES (NULL, '$id', '$this->dog_id', '$this->text');";
        if ($result = $this->mysqli->query($sql)) {
            echo "realizado se inserto el comentario";
        } else {
            echo "ERROR:No se puede ejecutar la sentencia: $sql. ";
        }
    }

}

?>
