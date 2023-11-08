<?php
//se necesita para crear la conecion con la base de datos
include_once 'commentsAndLikes/likeComent.php';

/**
 * Esta clase obtiene perros aleatorios contenidos en la base de datos.
 *
 * @author anonimo
 */
class Feed extends likeComent
{
    /**
    * inicializa la conexion con la base de datos
    *
    * @access public
    */
    public function __construct($key)
    {
    //Contructor de la clase padre
    parent::__construct($key, " ");

    }

    /**
    * obtiene datos de un perro aleatorio  en formato json
    *
    *El metodo da como respuesta datos de 2000 perros obtenidos aleatoriamente
    * contenidos en la base de datos
    *
    *
    * @access public
    */
    public function getPerros()
    {
        $sql = "SELECT* FROM perros ORDER BY RAND() LIMIT 2000";
        $list=[];
        if ($result = $this->mysqli->query($sql)) {
            if ($result->num_rows > 0) {
                while ($row = $result->fetch_object()) {
                    array_push($list, $row);
                }
                $result->free();
            } else {
                echo json_encode($list);
            }
        } else {
              echo "ERROR:No se puede ejecutar la sentencia: $sql. ";
        }
        echo json_encode($list);
    }
}

?>
