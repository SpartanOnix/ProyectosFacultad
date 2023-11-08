import os 

# Ruta para acceder a los ejemplares del agente viajero
nombres_AV = os.listdir("./ejemplares_AV")

# Ruta para acceder a los ejemplares del max sat
nombres_MS = os.listdir("./ejemplares_MS")

# Variables auxiliares para los print 
i = 1
j = 1

# Variables auxiliares para ir guardando los elementos de los .txt
lineas = ""
clausulas = ""

# Metodo para obtener el numero de vertices en los archivos AV
def numVertices(graf):
    lin = ""
    vertices = 0
    for n in graf:
        lin = n.split()
        if (lin[0] < lin[2]):
            vertices += 1
    return vertices

# Metodo para obtener el numero de aristas en los archivos AV
def numAristas(graf):
    aristas = 0
    for n in graf:
        aristas += 1
    return aristas

# Metodo para obtener el peso maximo en los archivos AV
def pesoMax(graf):
    lin = ""
    peso = 0
    for n in graf:
        lin = n.split()
        if(int(lin[1]) > peso):
            peso = int(lin[1])
    return peso

# Metodo para obtener el peso minimo en los archivos AV
def pesoMin(graf):
    lin = ""
    peso = pesoMax(graf)
    for n in graf:
        lin = n.split()
        if(int(lin[1]) < peso):
            peso = int(lin[1])
    return peso

# Metodo para obtener el numero de variables en los archivos MS
def numVaribles(expr):
    lin = ""
    vari = 0
    for n in expr:
        lin = n.split("u")
        for i in lin:
            vari += 1
    return vari

# Metodo para obtener el numero de clausulas en los archivos MS
def numClausula(expr):
    vari = 0
    for n in expr:
        vari += 1
    return vari

# Metodo para obtener el peso promedio en los archivos MS
def promedio(expr):
    vari = 0
    suma = 0
    for n in expr:
        suma = suma + int(n[0])
        vari += 1
    return (suma / vari)

# Ejecucion para los archivos AV
print("Ejemplares del Agente Viajero:")
for nombre in nombres_AV:
    archivo = open(os.getcwd()+"/ejemplares_AV/"+nombre, "r")
    lineas = archivo.readlines()
    print("\nEl numero de vertices del ejemplar " + repr(i) + " es: " + repr(numVertices(lineas)))
    print("El numero de aristas del ejemplar " + repr(i) + " es: " + repr(numAristas(lineas)))
    print("El peso maximo del ejemplar " + repr(i) + " es: " + repr(pesoMax(lineas)))
    print("El peso minimo del ejemplar " + repr(i) + " es: " + repr(pesoMin(lineas)))
    i += 1

# Ejecucion para los archivos MS
print("\nEjemplares del Max SAt:")
for nombre in nombres_MS:
    archivo = open(os.getcwd()+"/ejemplares_MS/"+nombre, "r")
    lineas = archivo.readline()
    clausulas = lineas.split("*")
    print("\nEl numero de variables del ejemplar " + repr(i) + " es: " + repr(numVaribles(clausulas)))
    print("El numero de clausulas del ejemplar " + repr(i) + " es: " + repr(numClausula(clausulas)))
    print("El peso promedio del ejemplar " + repr(i) + " es: " + repr(promedio(clausulas)))
    j += 1