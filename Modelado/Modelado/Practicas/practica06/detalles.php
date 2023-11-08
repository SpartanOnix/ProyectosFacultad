<?php
include_once 'feedAndDetails/Apidetails.php';

if (isset($_GET['key']) && isset($_GET['dog_id'])) {
    $key = $_GET['key'];
    $dog_id = $_GET['dog_id'];
    $detalles=new Detalles($key, $dog_id);
    if ($detalles->validaKey() == 1)
        $detalles->details();
        else {
          echo json_encode(array(
              "status" => "failed",
              "message" => "ERROR: Key incorrecta"
          ));    }
    }else{
      echo json_encode(array(
          "status" => "failed",
          "message" => "ERROR:parametros incorrectos"
      ));
    }
?>
