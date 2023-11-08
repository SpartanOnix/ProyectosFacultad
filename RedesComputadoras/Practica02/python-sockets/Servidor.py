
# Importar módulos
import socket

# Obtener la dirección IP del equipo (conexiones externas)
# Para conexiones locales usar localhost, 127.0.0.1
host = socket.gethostbyname(socket.gethostname())

# Definir el puerto a la escucha
port = 10000

# Instanciar la clase socket
mi_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# Enlazar el socket a la interfaz (configuración)
mi_socket.bind((host, port))

# Establecemos el modo escucha
mi_socket.listen(5)
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
    # Enviamos un mensaje de bienvenida al cliente
    mensaje = "Bienvenido al servidor de Redes\n"
    newcliente.send(mensaje.encode('utf-8'))
    newcliente.close()
