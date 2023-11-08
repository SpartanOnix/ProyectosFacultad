import simpy
from Nodo import *
from Canales.CanalRecorridos import *
# La unidad de tiempo
TICK = 1

class NodoBFS(Nodo):
    
    def __init__(self, id_nodo, vecinos, canal_entrada, canal_salida):
        self.id_nodo = id_nodo
        self.vecinos=vecinos
        self.canal_entrada = canal_entrada
        self.canal_salida = canal_salida
        
        self.distance=float('inf')
        self.parent=-1

    def bfs(self, env):
        yield envi.timeout(1)
        # El nodo 0 sera la raíz del arbol
        if( self.id_nodo == 0 ):
            print("El nodo raiz "+str(self.id_nodo)+" ha empezado ha constuir el arbol BFS\n")
            self.distance=0
            self.parent=self.id_nodo
            self.canal_salida.envia((self.distance,self.id_nodo),self.vecinos)
            

        while True:
            mensaje= yield self.canal_entrada.get()
            print("El nodo "+str(self.id_nodo)+" ha recibido un mensaje del nodo "+ str(mensaje[1]))
            if(mensaje[0]+1 < self.distance):
                print("El nodo " + str(self.id_nodo) + " ha actualizado su distancia")
                self.distance=mensaje[0]+1
                self.parent=mensaje[1]
                self.canal_salida.envia((self.distance,self.id_nodo),self.vecinos)


    
if __name__ == "__main__":
# Inicializamos ambiente y canal
  envi = simpy.Environment()
  bc_pipe = CanalRecorridos(envi)

  # Creamos los nodos
  grafica = []
  adyacencias =[[1, 3, 4, 6], [0, 3, 5, 7], [3, 5, 6], [0, 1, 2], [0], [1, 2], [0, 2], [1]]
  
  for i in range(0, len(adyacencias)):
      grafica.append(NodoBFS(i, adyacencias[i], bc_pipe.crea_canal_de_entrada(), bc_pipe))

  # Y le decimos al ambiente que lo procese
  for i in range(0, len(adyacencias)):
      envi.process(grafica[i].bfs(envi))

    



