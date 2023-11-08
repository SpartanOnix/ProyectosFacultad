<?php
include_once 'commentsAndLikes/Apilikes.php';

if (isset($_GET['key']) && isset($_GET['dog_id'])) {
    $key = $_GET['key'];
    $dog_id = $_GET['dog_id'];
    $like=new likes($key, $dog_id);
    if ($like->validaKey() == 1)
        $like->likeDislike();
    else {
      echo json_encode(array(
          "status" => "failed",
          "message" => "ERROR: Key incorrecta"
      ));
    }
}else{
  echo json_encode(array(
      "status" => "failed",
      "message" => "ERROR:parametros incorrectos"
  ));
}
?>
