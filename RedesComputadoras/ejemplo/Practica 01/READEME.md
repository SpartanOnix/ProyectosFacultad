# Redes de Computadoras

## Introduccion GitLab Socket

### Observaciones:
El socket servidor se crea la direccion IPv4 127.0.0.1 dado 
que en Mac y en algunos S.O. si se personaliza la IP y el puerto
se podría intentar levantar una conexión en un puerto restringido. Por lo tanto para una correcta ejecución
del programa recomiendo que la IPv4 seleccionada para el cliente
siempre sea 127.0.0.1.
Para terminar la ejecución del programa presionar la combinacion contro + c

### Ejecución
Para compilar
```bash
gcc servidor.c -o servidor
gcc cliente.c -o cliente
```
Ejemplo de ejecución:
```bash
./servidor 1234
./cliente 127.0.0.1 1234
```

## Lectura del archivo /etc/passwd
### Ejecución
```Python3
python3 lee_passwd.py
```