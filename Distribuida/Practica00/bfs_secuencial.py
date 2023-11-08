#Lista de nodos visitados.
visitado = []

#Cola para los nodos
cola = []

#Codigo donde se crea un diccionario y se ejecuta el BFS
def texto1():
    print("Hola, introduce un arbol como se te indica para que te regrese sus nodos en forma de texto de esta forma: A B C D E F")
    grapt = {}
    nodos = int(input("\nDame el numero de nodos de tu grafica: "))
    for i in range(0, nodos):
        nodo_id= input("Id: ") 
        grapt[nodo_id] = []
        print("Presiona x para terminar de introduir los nodos o si es que ya no tiene relaciones")
        while True:
            nodo_v = input("Vecino: ")
            if(nodo_v == 'x'):
                break
            grapt[nodo_id].append(nodo_v)
    raiz = input("Dame la raiz de la grafica: ")
    print(grapt)
    bfs(visitado, grapt, raiz)

#Codigo del BFS
def bfs(visitado, arbol, nodo):
   visitado.append(nodo)
   cola.append(nodo)
   while cola:
      s = cola.pop(0) 
      print (s, end = " ") 
      for hijo in arbol[s]:
         if hijo not in visitado:
           visitado.append(hijo)
           cola.append(hijo)

#Ejemplo
arbol1 = {
  'A' : ['B','C'],
  'B' : ['D', 'E'],
  'C' : ['F'],
  'D' : [],
  'E' : [],
  'F' : []
}

def main():
    texto1()
    #bfs(visitado, arbol1, 'A')
