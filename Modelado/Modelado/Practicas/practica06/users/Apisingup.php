<?php
//se necesita para crear la conecion con la base de datos
include_once 'Users.php';

/**
 * Esta clase da de alta aun usuario nuevo en la base de datos
 *
 * @author anonimo
 */
class signup extends Users
{


    /**
     * inicializa la conexion con la base de datos,se  asignan el  usuario y contraseña recibidos,
     *
     * @access public
     */
    public function __construct($user, $pass)
    {
           //Contructor de la clase padre
        parent::__construct($user, $pass);
    }

    /**
     * genera el key de un usuario con el usuario y contraseña recibidos
     *
     *@return $userkey key del usuario generada
     *
     * @access private
     */
    private function Key()
    {
        $userkey = hash('sha256', $this->user . $this->pass);
        return $userkey;
    }


    /**
     *agrega a un usuario nuevo en la base de datoss
     *
     *da de alta aun usuario en la base de datos siempre y cuando este no se encuentre ya contenido en ella
     *
     * @access public
     */
    public function agrega()
    {
        //hash de la contraseña
        $pass = password_hash($this->pass, PASSWORD_BCRYPT);
        //key del usuario, metodo  sha256
        $key  = $this->key();
        $sql  = "INSERT INTO `usuarios` (`user_id`, `user`, `pass`, `userkey`) VALUES (NULL, '$this->user','$pass','$key');";
        if ($this->Exist() == 1) {
            echo json_encode(array(
                "status" => "failed",
                "message" => "Este nombre de usuario no esta diponible"
            ));
        } else {
            if ($this->mysqli->query($sql) === true) {
                echo json_encode(array(
                    "status" => "ok"
                ));
            } else {
                echo json_encode(array(
                    "status" => "failed",
                    "message" => "ERROR: Could not able to execute $sql " . $this->mysqli->error
                ));
            }
        }
    }

}

?>
