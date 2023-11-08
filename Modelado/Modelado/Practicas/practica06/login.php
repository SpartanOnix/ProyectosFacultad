<?php
include_once 'users/Apilogin.php';

if (isset($_GET['user']) && isset($_GET['pass'])) {
    $user = $_GET['user'];
    $pass = $_GET['pass'];
    $login= new login($user, $pass);
    $login->comprueba();
  }else{
  echo json_encode(array(
      "status" => "failed",
      "message" => "ERROR:parametros incorrectos"
  ));
}
?>
