import socket
# Documentación: https: // docs.python.org/3/library/socket.html

# Instanciar la clase socket
mi_socket = socket.socket()

# Nos conectamos al socket
host = socket.gethostbyname(socket.gethostname())
mi_socket.connect((host, 10000))
# mi_socket.connect(('127.0.0.1', 10000))

# Enviamos un mensaje, transformando el string en un objeto de tipo bytes
mensaje = "Hola Servidor"
mi_socket.send(mensaje.encode('utf-8'))

respuesta = mi_socket.recv(1024)
print(respuesta.decode())
mi_socket.close()
