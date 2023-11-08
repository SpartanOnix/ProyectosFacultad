# Importar módulos
import socket
import os

# Obtener la dirección IP del equipo (conexiones externas)
# Para conexiones locales usar localhost, 127.0.0.1
host = socket.gethostbyname(socket.gethostname())

# Definir el puerto a la escucha
port = 10000

# Instanciar la clase socket
mi_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
# Enlazar el socket a la interfaz (configuración)
mi_socket.bind(('127.0.0.1', port))
#mi_socket.bind((host, port))

# Establecemos el modo escucha
mi_socket.listen(5)

# Ruta donde se gusrdaran los registros
ruta = str(os.getcwd()) + "/.mailbox_redes"
while True:
    # Indicamos que aceptamos conexiones del mundo exterior y esperamos conexiones entrantes
    (newcliente, addr) = mi_socket.accept()

    # Mostramos en terminal que hemos recibido una nueva conexión
    print("Nueva conexion entrante...")
    print(addr)

    # Una vez que se lleva a cabo una conexión preparamos el buffer de datos para recibir información
    peticion = newcliente.recv(1024)
    print("Petición: " + peticion.decode())

    if peticion.decode() != "":

        # Codigo para guardar los registros en un archivo de texto
        with open(ruta, "a") as step:
            step.write(peticion.decode() + '\n')

        # Validacion de codigo
        if "HELO" in peticion.decode():
            newcliente.send(f"250 HELO {addr[0]}:{addr[1]}")
            peticion = newcliente.recv(1024)
            print("Petición: " + peticion.decode() + "\n250 HELO")
            with open(ruta, "a") as step:
                step.write(peticion.decode() + '\n')

            # Validacion de codigo
            if "MAIL FROM:" in peticion.decode():
                newcliente.send("250 OK")
                peticion = newcliente.recv(1024)
                print("Petición: " + peticion.decode() + "\n250 OK")
                with open(ruta, "a") as step:
                    step.write(peticion.decode() + '\n')

                # Validacion de codigo
                if "RCPT TO:" in peticion.decode():
                    newcliente.send("250 OK")
                    peticion = newcliente.recv(1024)
                    print("Petición: " + peticion.decode() + "\n250 OK")
                    with open(ruta, "a") as step:
                        step.write(peticion.decode() + '\n')

                    # Validacion de codigo
                    if "DATA" in peticion.decode():
                        newcliente.send("354 End data")
                        peticion = newcliente.recv(1024)
                        print("Petición: " + peticion.decode() + "\n354")
                        with open(ruta, "a") as step:
                            step.write(peticion.decode() + '\n')

                        # Validacion de codigo
                        if "\n.\n" in peticion.decode():
                            newcliente.send("250 OK, mensaje en condiciones de envio")
                            peticion = newcliente.recv(1024)
                            print("Petición: " + peticion.decode() + "\n250 OK")
                            with open(ruta, "a") as step:
                                step.write(peticion.decode() + '\n')

                            # Validacion de codigo
                            if "QUIT" == peticion.decode():
                                newcliente.send("221 BYE")
                                peticion = newcliente.recv(1024)
                                print("Petición: " + peticion.decode() + "\n221 BYE")
                                with open(ruta, "a") as step:
                                    step.write(peticion.decode() + '\n')
                                newcliente.close()
                                
    # Si hay algun problema con el protocolo de identificacion
    print("Error en la identificacion de protocolos")
    # Cerramos la conexion
    newcliente.close()