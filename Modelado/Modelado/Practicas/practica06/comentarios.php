<?php
include_once 'commentsAndLikes/Apicoment.php';

if (isset($_GET['key']) && isset($_GET['dog_id']) && isset($_POST['text'])) {
    $key = $_GET['key'];
    $dog_id = $_GET['dog_id'];
    $text = $_POST['text'];
    $comentario=new Comentarios($key, $dog_id,$text);
    if ($comentario->validaKey() == 1)
        $comentario->agregaComent();
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
