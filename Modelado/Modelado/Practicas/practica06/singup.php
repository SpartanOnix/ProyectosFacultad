<?php

include_once 'users/Apisignup.php';

if (isset($_GET['user']) && isset($_GET['pass'])) {
  $user = $_GET['user'];
  $pass = $_GET['pass'];
  $s    = new signup($user, $pass);
  $s->agrega();
  }else{
  echo json_encode(array(
      "status" => "failed",
      "message" => "ERROR:parametros incorrectos"
  ));
}
?>
