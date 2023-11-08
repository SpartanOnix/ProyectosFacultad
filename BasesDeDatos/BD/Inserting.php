<?php
    
    require "BaseDeDatos.php";
    require "imageDog_api.php";
    require "nameDog_api.php";

    $file = fopen("perros.txt","a")
    or die("Problemas al crear archivo");

    $id = 1;
    while($id < 5){
        $imagen = dog_api_call();
        $nombre = nameDog_api_call();
        $likes = 0;
        
        $sql = "INSERT INTO perros (perro_id, nombre, imagen, no_likes) VALUES ('$id', '$nombre', '$imagen', '$likes')";

        if (mysqli_query($mysqli, $sql)) {
            echo "Nuevo registro creado.\n";
            fwrite($file, "\n$sql;");
        } else {
                echo "Error: " . $sql . "<br>" . mysqli_error($mysqli);
        }
        $id += 1;
        
    }
    fclose($file);

?>