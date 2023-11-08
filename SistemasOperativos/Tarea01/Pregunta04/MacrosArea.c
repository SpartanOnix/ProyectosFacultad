//Bibliotecas
#include <string.h>
#include <stdlib.h>
#include <stdio.h>

//Correccion de sintaxis
#define SINTAXIS printf("Sintaxis erronea\n");
#define SINTAXISCIRCULO printf("Parametros incorrectos, es nesesario solo el radio\n");
#define SINTAXISTRIANGULO printf("Parametros incorrectos, es nesesario la base y la altura\n");
#define SINTAXISCUADRADO printf("Parametros incorrectos, es nesesario solo el valor de un lado\n");
#define SINTAXISRECTANGULO printf("Parametros incorrectos, es nesesario la base y la altura\n");
#define SINTAXISFIGURA printf("Coloca una figura valida, solo se aceptan -c, -t, -dc y -dr\n");

//Macros del area de las figuras
#define PI 3.1416
#define AREACIRCULO(radio) (PI*(radio*radio))
#define AREATRIANGULO(baze, haltura) ((baze*haltura)/2)
#define AREACUADRADO(lado) lado*lado
#define AREARECTANGULO(base, altura) base*altura
