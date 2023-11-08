<?php
/**
 * Funcion que hace una llamada a 'Dog_api'
 * 
 * La unica finalidad es obtener una url de forma aleatoria
 * de la imagen de un perro
 *
 * @return string Una url de la imagen de un perro
 */
function dog_api_call()
{
  $url = "https://dog.ceo/api/breeds/image/random";
  
  // Curl sirve para hacer peticiones a apis (entre otras cosas)
  $curl = curl_init();
  curl_setopt($curl, CURLOPT_URL, $url);
  curl_setopt($curl, CURLOPT_RETURNTRANSFER, true); // permite que lo que se regresa se guarde en formato json
  $result = curl_exec($curl);
  curl_close($curl);
  $response = json_decode($result);
  return $response->{'message'};
}
?>




