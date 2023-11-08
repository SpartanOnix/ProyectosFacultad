import sys

list_data = []
info = """
Nombre de usuario: {usuario}
Contraseña: {contraseña}
UID: {uid}
GID: {gid}
GECOS: {gecos}
Home: {home}
Shell: {path}
"""
const_password = "Contraseña en archivo /etc/passwd"

def parser_line(line):
    if (line[0] != "#"):
        list_data.append(parser_user(line))

def parser_user(line):
    user_properties = line.split(':')
    user_name=user_properties[0]
    password=user_properties[1]
    password = const_password if password == "*" or password == "x" else password
    uid=user_properties[2]
    gid=user_properties[3]
    gecos=user_properties[4]
    home=user_properties[5]
    path=user_properties[6]
    return info.format(usuario=user_name, contraseña=password, uid=uid, gid=gid, gecos=gecos, home=home, path=path)


while True:
    file = open("/etc/passwd", "r")

    except OSError:
        print("No se pudo leer el archivo: /etc/passwd")
        print("Quizas el usuario de la teminal donde corriste")
        print("este programa no tenga acceso al directorio etc/")
        sys.exit()
    with file:
        caracter = file.readline()
        while caracter != "":
            #print(caracter)
            parser_line(caracter)
            caracter = file.readline()
        file.close()
        for user in list_data:
            print(user)