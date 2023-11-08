# Importar módulos
import socket
import argparse

# Instanciar la clase socket
mi_socket = socket.socket()

#instanciamos las banderas sujeridas en el protocolo
flag = argparse.ArgumentParser()
# La primera bandera hara referencia al redactor del mesaje
flag.add_argument("-r", "--subject", required = True)
# La segunda bandera hara referencia al mensaje
flag.add_argument("-m", "--email", required = True)
# Instanciamos el contenido de las banderas para su uso 
args = vars(flag.parse_args())

if args["email"] and args["subject"]:
    # Nos conectamos al socket
    host = socket.gethostbyname(socket.gethostname())
    # Para conexiones locales usar localhost, 127.0.0.1
    mi_socket.connect(('127.0.0.1', 10000))
    #mi_socket.connect((host, 10000))

    # Enviamos un mensaje de entrada
    mensaje = "HELO Servidor"
    mi_socket.send(mensaje.encode('utf-8'))
    while True:
        respuesta = mi_socket.recv(1024)

        # if para checar si hay respuesta del servidor
        if respuesta.decode() != "":
            print(respuesta.decode())

            # Validacion de codigo
            if '250' in respuesta.decode():
                mensaje = "MAIL FROM: " + args["email"]
                mi_socket.send(mensaje.encode('utf-8'))
                respuesta = mi_socket.recv(1024)
                print(respuesta.decode())

                # Validacion de codigo
                if respuesta.decode() == '250 OK':
                    mensaje = "RCPT TO: " + args["email"]
                    mi_socket.send(mensaje.encode('utf-8'))
                    respuesta = mi_socket.recv(1024)
                    print(respuesta.decode())

                    # Validacion de codigo para empesar la transferencia de datos
                    if respuesta.decode() == '250 OK':
                        mensaje = "DATA"
                        mi_socket.send(mensaje.encode('utf-8'))
                        respuesta = mi_socket.recv(1024)
                        print(respuesta.decode())
                        # Validacion de codigo
                        if '354' not in respuesta.decode():
                            print(respuesta.decode())
                            mensaje = args["subject"] + "\n.\n"
                            mi_socket.send(mensaje.encode('utf-8'))
                            respuesta = mi_socket.recv(1024)

                        # Validacion de codigo
                        if '250' in respuesta.decode():
                            print(respuesta.decode())
                            mensaje = "QUIT"
                            mi_socket.send(mensaje.encode('utf-8'))
                            respuesta = mi_socket.recv(1024)

                            # Validacion de codigo
                            if respuesta.decode() == '221 BYE':
                                print(respuesta.decode())
                                print("Email enviado con exito, adios.")
                                mi_socket.close()
                                
        # Si no hay respuesta imprimimos un error
        print("No se pudo enviar el email.")

    # Cerramos la conexion
    mi_socket.close()