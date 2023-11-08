import simpy
from Canales.CanalBroadcast import *
from Nodo import *

class NodoVecinos(Nodo):
    """Implementa el algoritmo para conocer al vecino de nuestros vecinos"""

    def __init__(self, id_nodo, vecino, canal_entrada, canal_salida):
        '''Inicializamos el nodo.'''
        self.id_nodo = id_nodo
        self.vecino = vecino
        self.identifiers = []
        self.canal_entrada = canal_entrada
        self.canal_salida = canal_salida

    def vecinos(self, envi):
        if self.id_nodo == 0:
            yield envi.timeout(1)
            self.canal_salida.envia(self.id_nodo, self.vecino)
        else:
            while True:
                mensaje = yield self.canal_entrada.get()
                print("El nodo " + str(self.id_nodo) + " es vecino de: ")
                print(self.vecino)
                yield envi.timeout(1)
                self.canal_salida.envia(self.id_nodo, self.vecino)
                break

if __name__ == "__main__":
    # Inicializamos ambiente y canal
    envi = simpy.Environment()
    bc_pipe = CanalBroadcast(envi)

    # Creamos los nodos
    grafica = []
    adyacencias = [[1, 2], [0, 2, 7], [0, 1, 3, 7], [2, 4, 6], [2, 3, 5], [4], [3, 7], [1, 2, 6]]
    for i in range(0, len(adyacencias)):
        grafica.append(NodoVecinos(i, adyacencias[i], bc_pipe.crea_canal_de_entrada(), bc_pipe))

    # Y le decimos al ambiente que lo procese
    for i in range(0, len(adyacencias)):
        envi.process(grafica[i].vecinos(envi))
