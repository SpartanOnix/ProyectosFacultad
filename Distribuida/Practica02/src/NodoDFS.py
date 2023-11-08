import simpy
from Nodo import *
from Canales.CanalRecorridos import *

# La unidad de tiempo
TICK = 1

class NodoDFS(Nodo):
    '''
    Implementa la interfaz de Nodo para el algoritmo de Broadcast.
    '''
    def __init__(self, id_nodo, vecinos, canal_entrada, canal_salida):
        ''' Constructor de nodo que implemente el algoritmo DFS. '''
        self.id_nodo = id_nodo
        self.vecinos = vecinos
        self.canal_entrada = canal_entrada
        self.canal_salida = canal_salida
        self.padre = id_nodo
        self.hijos = []

    def dfs(self, env):
        ''' Algoritmo DFS. '''
        if self.id_nodo == 0:
            yield env.timeout(TICK)
            self.canal_salida.envia(('Go', self.id_nodo, {0}), [self.minimo(set())])
            self.hijos.append(self.minimo(set()))

        while True:
            mensaje = yield self.canal_entrada.get()
            msg = mensaje[0]
            vecino = mensaje[1]
            visitados = mensaje[2]

            if (msg == 'Go'):
                self.padre = vecino
                if (set(self.vecinos).issubset(visitados)):
                    yield env.timeout(TICK)
                    self.canal_salida.envia(('Back', self.id_nodo, visitados.union({self.id_nodo})), [vecino])
                    self.hijos = []
                else:
                    yield env.timeout(TICK)
                    min = self.minimo(visitados)
                    self.canal_salida.envia(('Go', self.id_nodo, visitados.union({self.id_nodo})), [min])
                    self.hijos = [min]

            if (msg == 'Back'):
                if (set(self.vecinos).issubset(visitados)):
                    if (self.padre != self.id_nodo):
                        yield env.timeout(TICK)
                        self.canal_salida.envia(('Back', self.id_nodo, visitados), [self.padre])
                else:
                    yield env.timeout(TICK)
                    min = self.minimo(visitados)
                    self.canal_salida.envia(('Go', self.id_nodo, visitados), [min])
                    self.hijos = list(set(self.hijos).union({min}))
