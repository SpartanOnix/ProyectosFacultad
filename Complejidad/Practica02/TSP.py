import os 

# Ruta para acceder a los certificados
certificados_1 = os.listdir("./ejemplar_1/certificados/")
certificados_2 = os.listdir("./ejemplar_2/certificados/")
certificados_3 = os.listdir("./ejemplar_3/certificados/")

# Variable auxiliar para los print 
i = 1

# Variables auxiliares para ir guardando los elementos de los .txt
lineas = ""
cert = ""

# Metodo para obtener el numero de vertices en las graficas
def numVertices(graf):
    lin = ""
    vertices = 0
    for n in graf:
        lin = n.split()
        if (lin[0] < lin[2]):
            vertices += 1
    return vertices

# Metodo para obtener el numero de aristas en las graficas
def numAristas(graf):
    aristas = 0
    for n in graf:
        aristas += 1
    return aristas

# Metodo para obtener el valor del umbral en los certificados
def getUmbral(cert):
    umbral = cert[1]
    return umbral

# Metodo para obtener el costo total del recorrido del certificado
def costo(cert):
    m = 0
    peso = 0
    recorrido = cert[0].split()
    for rec in recorrido:
        for lin in lineas:
            l = lin.split()
            if l[0] == rec and l[2] == recorrido[0 if m == len(recorrido)-1 else m+1]:
                peso = peso + int(l[1])
        m += 1
    return peso

# Metodo para saber si se cumple la condicion de pertenencia
def pertenencia(cert):
    if costo(cert) <= int(getUmbral(cert)) and costo(cert) > 0:
        return True
    else:
        return False

# Ejecucion para los ejemplares
print("\nEjemplar 1:")
archivo = open(os.getcwd()+"/ejemplar_1/ejemplar.txt", "r")
lineas = archivo.readlines()
print("\nEl numero de vertices del ejemplar " + repr(i) + " es: " + repr(numVertices(lineas)))
print("El numero de aristas del ejemplar " + repr(i) + " es: " + repr(numAristas(lineas)))
for nombre in certificados_1:
    archivo = open(os.getcwd()+"/ejemplar_1/certificados/"+nombre, "r")
    cert = archivo.readlines()
    print("\n  Certificado " + repr(i) + ":")
    print("     Nuestro umbral en el certificado es: " + repr(getUmbral(cert)))
    print("     El costo total del recorrido fue: " + repr(costo(cert)))
    print("     ¿Se cumplio la condicion de pertenecia?: " + repr(pertenencia(cert)))
    i += 1 
i = 1

print("\nEjemplar 2:")
archivo = open(os.getcwd()+"/ejemplar_2/ejemplar.txt", "r")
lineas = archivo.readlines()
print("\nEl numero de vertices del ejemplar " + repr(i) + " es: " + repr(numVertices(lineas)))
print("El numero de aristas del ejemplar " + repr(i) + " es: " + repr(numAristas(lineas)))
for nombre in certificados_2:
    archivo = open(os.getcwd()+"/ejemplar_2/certificados/"+nombre, "r")
    cert = archivo.readlines()
    print("\n  Certificado " + repr(i) + ":")
    print("     Nuestro umbral en el certificado es: " + repr(getUmbral(cert)))
    print("     El costo total del recorrido fue: " + repr(costo(cert)))
    print("     ¿Se cumplio la condicion de pertenecia?: " + repr(pertenencia(cert)))
    i += 1 
i = 1

print("\nEjemplar 3:")
archivo = open(os.getcwd()+"/ejemplar_3/ejemplar.txt", "r")
lineas = archivo.readlines()
print("\nEl numero de vertices del ejemplar " + repr(i) + " es: " + repr(numVertices(lineas)))
print("El numero de aristas del ejemplar " + repr(i) + " es: " + repr(numAristas(lineas)))
for nombre in certificados_3:
    archivo = open(os.getcwd()+"/ejemplar_3/certificados/"+nombre, "r")
    cert = archivo.readlines()
    print("\n  Certificado " + repr(i) + ":")
    print("     Nuestro umbral en el certificado es: " + repr(getUmbral(cert)))
    print("     El costo total del recorrido fue: " + repr(costo(cert)))
    print("     ¿Se cumplio la condicion de pertenecia?: " + repr(pertenencia(cert)))
    i += 1 
i = 1