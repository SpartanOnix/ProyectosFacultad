# Importar módulos
import socket

# Obtener la dirección IP del equipo (conexiones externas)
# Para conexiones locales usar localhost, 127.0.0.1
host = socket.gethostbyname(socket.gethostname())

# Definir el puerto a la escucha
port = 8000

# Instanciar la clase socket
mi_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
#mi_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
# Enlazar el socket a la interfaz (configuración)
mi_socket.bind((host, port))
print('Listening on port {0} : {1}  ...'.format(host, port))
# Establecemos el modo escucha
mi_socket.listen(1)
while True:
    # Indicamos que aceptamos conexiones del mundo exterior,
    # y esperamos conexiones entrantes
    (newcliente, addr) = mi_socket.accept()

    # Mostramos en terminal que hemos recibido una nueva conexión
    print("Nueva conexion entrante...")
    print(addr)

    # Una vez que se lleva a cabo una conexión
    # preparamos el buffer de datos para recibir información
    peticion = newcliente.recv(1024)
    print("Petición: " + peticion.decode())
    # Enviando respuesta HTTP al cliente
    html_string = '<html lang="en"><head><meta charset ="UTF-8"/><meta http-equiv="X-UA-Compatible" content="IE=edge" /><meta name="viewport" content="width=device-width, initial-scale=1.0" /><title>Prueba</title></head><body><h1>Hola Mundo</h1></body></html>'
    response = 'HTTP/1.0 200 OK\n\n' + html_string
    newcliente.sendall(response.encode())
    newcliente.close()
