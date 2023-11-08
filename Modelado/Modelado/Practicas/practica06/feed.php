<?php
include_once 'feedAndDetails/Apifeed.php';

if (isset($_GET['key'])) {
    $key = $_GET['key'];
    $feed=new Feed($key);
    if ($feed->validaKey() == 1)
        $feed->getPerros();
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
