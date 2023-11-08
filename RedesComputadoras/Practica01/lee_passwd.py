info = """
Nombre de usuario: {usuario}
Contraseña: {contrasena}
UID: {uid}
GID: {gid}
GECOS: {gecos}
Home: {home}
Shell: {path}
"""

def parser_line(line):
    if line[0] != "#":
        archivo.append(parser_user(line))

def ver_pass(password):
    if password == "*" or password == "x":
        password = "Contraseña en archivo /etc/passwd"  
    return password

def parser_user(line):
    parametros = line.split(':')
    usuario_aux = parametros[0]
    password = parametros[1]
    contrasena_aux = ver_pass(password)
    uid_aux = parametros[2]
    gid_aux = parametros[3]
    gecos_aux = parametros[4]
    home_aux = parametros[5]
    path_aux = parametros[6]
    return info.format(usuario = usuario_aux, 
                        contrasena = contrasena_aux, 
                        uid = uid_aux, 
                        gid = gid_aux, 
                        gecos = gecos_aux, 
                        home = home_aux, 
                        path = path_aux)

while True:
    archivo = []
    datos = open("/etc/passwd", "r")
    with datos:
        linea = datos.readline()
        while linea != "":
            parser_line(linea)
            linea = datos.readline()
        datos.close()
        for formato in archivo:
            print(formato)