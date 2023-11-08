<?php

//se necesita para crear la conecion con la base de datos
include_once 'Users.php';

/**
 * Esta clase inicia secion con los datos de usuario y contraseña recibidos.
 *
 * @author anonimo
 */
class login extends Users
{

    /**
     * inicializa la conexion con la base de datos,se  asignan el usuario y contraseña recibidos,
     *
     * @access public
     */
    public function __construct($user, $pass)
    {
           //Contructor de la clase padre
        parent::__construct($user, $pass);

    }

    /**
     * Devuelve la información del usuario
     *
     *devuelve cierta informacion de un usuari especificada en el
     * parametro $camp
     * @access private
     * @return $data  $dato deseado
     **in pare revisar usuarios
     */
    private function getData($camp)
    {
        $sql    = "SELECT * FROM usuarios WHERE user='$this->user'";
        $result = $this->mysqli->query($sql);
        if ($result->num_rows > 0) {
            while ($row = $result->fetch_assoc()) {
                $data = $row["$camp"];
                return $data;
            }
        }
    }

    /**
     * Devuelve una respuesta valida si el usuario se encuentra registadoen
     *la base de datos , o una respuesta erronea en otro caso, ambas en
     *formato json
     *
     * @access public
     */
    public function comprueba()
    {
        if ($this->Exist() == 0) {
            echo json_encode(array(
                "status" => "failed",
                "Message" => "the username doesnt exist"
            ));
        } else {
            $hashpass = $this->getData("pass");
            if (password_verify($this->pass, $hashpass)) {
                $key = $this->getData('userkey');
                echo json_encode(array(
                    "status" => "ok",
                    "Message" => "$key"
                ));
            } else {
                echo json_encode(array(
                    "status" => "failed",
                    "Message" => "Password is incorrect"
                ));
            }
        }
    }
}

?>
