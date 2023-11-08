<?php
include_once 'baseDeDatos/database.php';

/*
Clase abstracta que extiende de la clase baseDeDatos
*las clase que extiendan de ella podran hacer uso de una conexion a  la base de datos y del metodo Exist
*/

class Users extends baseDeDatos
{

    //usuario recibidos
    public $user;
    //contraseña recibida
    public $pass;



    /**
     * inicializa la conexion con la base de datos,se  asignan el  usuario y contraseña recibidos,
     *
     * @access public
     */
    public function __construct($user, $pass)
    {
        parent::__construct();
        $this->user = $user;
        $this->pass = $pass;
    }

    /**
     * revisa si el usuario recibido de encuentra en la base de datos
     *
     *@return 1 si el usuario se encuentra en la base de datos
     *@return 0 en otro caso
     *
     * @access public
     */
    public function Exist()
    {
        $sql = "SELECT * FROM usuarios WHERE user='$this->user'";
        if ($result = $this->mysqli->query($sql)) {
            if ($result->num_rows > 0) {
                return 1;
            }
            return 0;
        }
    }

}

?>
