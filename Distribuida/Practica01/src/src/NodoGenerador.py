import simpy
from Canales.CanalBroadcast import *
from Nodo import *

class NodoGenerador(Nodo):
    """Implementa la creacion del arbol generador"""

    def __init__(self, id_nodo, vecino, canal_entrada, canal_salida):
        '''Inicializamos el nodo.'''
        self.id_nodo = id_nodo
        self.vecino = vecino
        self.canal_entrada = canal_entrada
        self.canal_salida = canal_salida
        self.padre = -1
        self.hijos = []
        self.msg = -1

    def generador(self,envi):
      if self.id_nodo == 0:
            yield env.timeout(1)
            self.padre = 0
            self.msg = len(self.vecinos)
            for nodos in self.vecinos:
                self.envia(nodos,[True, self])
      else:
        while True:
            mensaje = yield self.canal_entrada.get()
            if mensage == True:
                if self.padre == -1:
                    self.padre = self.id_nodo
                    self.msg = len(self.vecinos) - 1
                    if self.msg == 0:
                        self.envia(self.id_nodo, [False,self])


if __name__ == "__main__":
    # Inicializamos ambiente y canal
    envi = simpy.Environment()
    bc_pipe = CanalBroadcast(envi)

    # Creamos los nodos
    grafica = []
    adyacencias = [[1, 2], [0, 2, 7], [0, 1, 3, 7], [2, 4, 6], [2, 3, 5], [4], [3, 7], [1, 2, 6]]
    for i in range(0, len(adyacencias)):
        grafica.append(Nodo(i, adyacencias[i], bc_pipe.crea_canal_de_entrada(), bc_pipe))

    # Y le decimos al ambiente que lo procese
    for i in range(0, len(adyacencias)):
        envi.process(grafica[i].generador(envi))
