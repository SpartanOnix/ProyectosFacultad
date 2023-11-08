# Practica 01 - Introduccion sockets

### Dante Jusepee Sienncio Granados - 316246019

## Ejecucion:
Compilacion:
```
gcc servidor.c -o servidor
gcc cliente.c -o cliente
```

Ejecucion para los archivos de C:
```
./servidor 1000
./cliente 127.0.0.1 1000
```

Ejecucion para el archivo de python:
```
python lee_passwd.py
```


## Observaciones:
-De preferencia ejecutenlos como con los ejemplos ya que se pueden crear errores si se intenta con un IPv4 o puerto diferente

-Como soy de windows tuve algunos problemas con el compilador gcc, al probarlo en linux si funciono con esté, pero en windows para que me compilara tuve que usar g++, el principal problema fue que no me detectaba las bibliotecas de sockets al compilar con gcc.