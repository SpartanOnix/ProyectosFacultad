import simpy
import random
from Nodo import *
from Canales.CanalRecorridos import *

# La unidad de tiempo
TICK = random.randint(1,20) # Tenemos sistema asíncrono

class NodoDFS(Nodo):
    ''' Implementa la interfaz de Nodo para el algoritmo de Broadcast. '''
    def __init__(self, id_nodo, vecinos, canal_entrada, canal_salida, num_nodos):
        ''' Constructor de nodo que implemente el algoritmo DFS. '''
        self.id_nodo = id_nodo
        self.vecinos = vecinos
        self.canal_entrada = canal_entrada
        self.canal_salida = canal_salida
        self.padre = id_nodo
        self.hijos = []
        self.eventos = [] # Lista de eventos
        self.reloj = [0] * num_nodos # Reloj vectorial

    def dfs(self, env):
        ''' Algoritmo DFS. '''
        if self.id_nodo == 0:
            yield env.timeout(TICK)
            self.canal_salida.envia(('Go', self.id_nodo, {0}, self.reloj[:]), [self.minimo(set())])
            self.hijos.append(self.minimo(set()))
            self.eventos = self.get_eventos('E',['Go',{0}],self.id_nodo,[self.minimo(set())])

        while True:
            mensaje = yield self.canal_entrada.get()
            msg = mensaje[0]
            vecino = mensaje[1]
            visitados = mensaje[2]
            reloj_vecino = mensaje[3]
            self.reloj[vecino] += 1
            self.maximo(reloj_vecino)
            self.eventos = self.get_eventos('R', [msg]+[visitados], vecino, [self.id_nodo])

            if (msg == 'Go'):
                self.padre = vecino
                self.reloj[self.id_nodo] += 1
                yield env.timeout(TICK)
                if (set(self.vecinos).issubset(visitados)):
                    self.canal_salida.envia(('Back', self.id_nodo, visitados.union({self.id_nodo}), self.reloj[:]), [vecino])
                    self.hijos = []
                    self.eventos = self.get_eventos('E', ('Back', visitados.union({self.id_nodo})), self.id_nodo, [vecino])
                else:
                    min = self.minimo(visitados)
                    self.canal_salida.envia(('Go', self.id_nodo, visitados.union({self.id_nodo}), self.reloj[:]), [min])
                    self.hijos = [min]
                    self.eventos = self.get_eventos('E', ('Go', visitados.union({self.id_nodo})), self.id_nodo, [min])

            if (msg == 'Back'):
                self.reloj[self.id_nodo] += 1
                if (set(self.vecinos).issubset(visitados)):
                    if (self.padre != self.id_nodo):
                        yield env.timeout(TICK)
                        self.canal_salida.envia(('Back', self.id_nodo, visitados, self.reloj[:]), [self.padre])
                        self.eventos = self.get_eventos('E', ('Back', visitados), self.id_nodo, [self.padre])
                else:
                    yield env.timeout(TICK)
                    min = self.minimo(visitados)
                    self.canal_salida.envia(('Go', self.id_nodo, visitados, self.reloj[:]), [min])
                    self.hijos = list(set(self.hijos).union({min}))
                    self.eventos = self.get_eventos('E', ('Go', visitados), self.id_nodo, [min])

    def maximo(self,reloj):
        ''' Funcion que obtiene y modifica el valor maximo del reloj '''
        for value in range(0, len(reloj)):
            self.reloj[value] = max(self.reloj[value], reloj[value])
