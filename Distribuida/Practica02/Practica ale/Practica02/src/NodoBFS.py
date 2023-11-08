import simpy
from Nodo import *
from Canales.CanalRecorridos import *
from math import inf as infinito

# La unidad de tiempo
TICK = 1

class NodoBFS(Nodo):
    ''' Implementa la interfaz de Nodo para el algoritmo de Broadcast.'''
    def __init__(self, id_nodo, vecinos, canal_entrada, canal_salida):
        ''' Constructor de nodo que implemente el algoritmo BFS. '''
        self.id_nodo = id_nodo
        self.vecinos = vecinos
        self.canal_entrada = canal_entrada
        self.canal_salida = canal_salida
        # Atributos extra
        self.padre = id_nodo
        self.distancia = infinito

    def get_id(self):
        return self.id_nodo

    def bfs(self, env):
        ''' Algoritmo BFS. '''
        if self.id_nodo == 0:

            yield env.timeout(TICK)
            self.distancia = 0
            self.canal_salida.envia([self.id_nodo,self.distancia],self.vecinos)

        while True:

            msg = yield self.canal_entrada.get()

            emisor = msg[0]
            dist = msg[1]

            if (dist + 1) < self.distancia:

                self.distancia = dist + 1
                self.padre = emisor
                yield env.timeout(TICK)
                self.canal_salida.envia([self.id_nodo,self.distancia],self.vecinos)