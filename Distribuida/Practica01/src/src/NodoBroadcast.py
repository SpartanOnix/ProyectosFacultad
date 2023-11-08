import simpy
from Canales.CanalBroadcast import *
from Nodo import *

class NodoBroadcast(Nodo):
    '''Implementa el algoritmo broadcast'''
    
    def __init__(self, id_nodo, hijos, canal_entrada, canal_salida):
        '''Inicializamos el nodo.'''
        self.id_nodo = id_nodo
        self.hijos = hijos
        self.canal_entrada = canal_entrada
        self.canal_salida = canal_salida
        self.mensaje = ""

    def broadcast(self, envi):
        '''Función de broadcast para nodos.'''
        self.mensaje = "Send Nudes"
        if self.id_nodo == 0:
            yield envi.timeout(1)
            self.canal_salida.envia(self.id_nodo, self.hijos)
        else:
            while True:
                mensaje = yield self.canal_entrada.get()
                print('El nodo %d recibío de %d el mensaje %s en la ronda %d' %(self.id_nodo, mensaje, self.mensaje, envi.now))
                yield envi.timeout(1)
                self.canal_salida.envia(self.id_nodo, self.hijos)
                break

if __name__ == "__main__":
    # Inicializamos ambiente y canal
    envi = simpy.Environment()
    bc_pipe = CanalBroadcast(envi)

    # Creamos los nodos
    grafica = []
    adyacencias = [[1, 2], [0, 2, 7], [0, 1, 3, 7], [2, 4, 6], [2, 3, 5], [4], [3, 7], [1, 2, 6]]
    for i in range(0, len(adyacencias)):
        grafica.append(NodoBroadcast(i, adyacencias[i], bc_pipe.crea_canal_de_entrada(), bc_pipe))

    # Y le decimos al ambiente que lo procese
    for i in range(0, len(adyacencias)):
        envi.process(grafica[i].broadcast(envi))
