import simpy
from Nodo import *
from Canales.CanalRecorridos import *

# La unidad de tiempo
TICK = 1

class NodoDFS(Nodo):
    ''' Implementa la interfaz de Nodo para el algoritmo de Broadcast.'''
    def __init__(self, id_nodo, vecinos, canal_entrada, canal_salida):
        ''' Constructor de nodo que implemente el algoritmo DFS. '''
        self.id_nodo = id_nodo
        self.vecinos = vecinos
        self.canal_entrada = canal_entrada
        self.canal_salida = canal_salida
        # Atributos extra
        self.padre = id_nodo
        self.hijos = []

    def vecinoMenor(self,visitados):
        ''' Funcion para obtener el menor de los vecinos'''
        dif = list(set(self.vecinos).difference(visitados))
        r = dif[0]

        for n in dif:
            if n < r:
                r = n 

        return r

    def get_id(self):
        return self.id_nodo

    def dfs(self, env):
        ''' Algoritmo DFS. '''
        
        if self.id_nodo == 0:

            yield env.timeout(TICK)
            self.canal_salida.envia(['G',self.id_nodo,{0}],[self.vecinoMenor(set())])
            self.hijos.append(self.vecinoMenor(set()))

        while True:
            msg = yield self.canal_entrada.get()

            tipo_msg = msg[0]
            emisor = msg[1]
            visitados = msg[2]

            if (tipo_msg == 'G'):

                self.padre = emisor

                if (set(self.vecinos).issubset(visitados)):

                    yield env.timeout(TICK)
                    self.canal_salida.envia(['B',self.id_nodo,visitados.union({self.id_nodo})],[emisor])
                    self.hijos = []
                    
                else:

                    k = self.vecinoMenor(visitados)       
                    yield env.timeout(TICK)
                    self.canal_salida.envia(['G',self.id_nodo,visitados.union({self.id_nodo})],[k])
                    self.hijos = [k]

            elif (tipo_msg == 'B'):

                if (set(self.vecinos).issubset(visitados)):

                    if (self.padre == self.id_nodo):

                        print("El algoritmo terminó")

                    else:

                        yield env.timeout(TICK)
                        self.canal_salida.envia(['B',self.id_nodo,visitados],[self.padre])
                        
                else:

                    k = self.vecinoMenor(visitados)
                    yield env.timeout(TICK)
                    self.canal_salida.envia(['G',self.id_nodo,visitados],[k])
                    self.hijos = list(set (self.hijos).union({k}))