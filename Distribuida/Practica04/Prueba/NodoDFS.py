import simpy
from Nodo import *
from Canales.CanalRecorridos import *
import random

# La unidad de tiempo
TICK = 1

class NodoDFS(Nodo):
    ''' Implementa la interfaz de Nodo para el algoritmo de Broadcast.'''
    def __init__(self, id_nodo, vecinos, canal_entrada, canal_salida,num_nodos):
        ''' Constructor de nodo que implemente el algoritmo DFS. '''
        self.id_nodo = id_nodo
        self.vecinos = vecinos
        self.canal_entrada = canal_entrada
        self.canal_salida = canal_salida
        # Atributos extra
        self.padre = id_nodo
        self.hijos = []
        self.eventos = []
        self.reloj = [0]*num_nodos

    def vecinoMenor(self,visitados):
        ''' Funcion para obtener el menor de los vecinos'''
        dif = list(set(self.vecinos).difference(visitados))
        r = dif[0]

        for n in dif:
            if n < r:
                r = n 

        return r

    def almacena_evento(self,evento,mensaje,emisor,receptores):
        ''' Función que almacena el evento recibido en el atributo eventos del nodo'''
        for e in receptores:
            self.eventos.append([self.reloj[:],evento,str(mensaje),emisor,e])

    def max_vector(self,reloj):
        ''' Funcion que modifica el reloj de un nodo obteniendo el máximo 
            entre el reloj recibido y el del nodo'''
        for i in range(0,len(reloj)):
            self.reloj[i] = max(self.reloj[i],reloj[i])

    def dfs(self, env):
        ''' Algoritmo DFS asincrono con relojes vectoriales. '''
        
        if self.id_nodo == 0:

            yield env.timeout(random.randint(1,20))
            self.reloj[self.id_nodo] += 1
            self.canal_salida.envia(['G',self.id_nodo,{0},self.reloj[:]],[self.vecinoMenor(set())])
            self.almacena_evento('E',['G',{0}],self.id_nodo,[self.vecinoMenor(set())])
            self.hijos.append(self.vecinoMenor(set()))

        while True:

            yield env.timeout(random.randint(1,20))

            msg = yield self.canal_entrada.get()

            tipo_msg = msg[0]
            emisor = msg[1]
            visitados = msg[2]
            reloj_emisor = msg[3]

            # Aumentamos el valor del emisor en el reloj
            self.reloj[emisor] += 1

            # Obtenemos el valor más alto del reloj recibido y el nodo
            self.max_vector(reloj_emisor)

            self.almacena_evento('R',[tipo_msg]+[visitados],emisor,[self.id_nodo])

            if (tipo_msg == 'G'):

                self.padre = emisor

                if (set(self.vecinos).issubset(visitados)):

                    yield env.timeout(random.randint(1,20))
                    self.reloj[self.id_nodo] += 1
                    self.canal_salida.envia(['B',self.id_nodo,visitados.union({self.id_nodo}),self.reloj[:]],[emisor])
                    self.almacena_evento('E',['B',visitados.union({self.id_nodo})],self.id_nodo,[emisor])
                    self.hijos = []
                    
                else:

                    k = self.vecinoMenor(visitados)       
                    yield env.timeout(random.randint(1,20))
                    self.reloj[self.id_nodo] += 1
                    self.canal_salida.envia(['G',self.id_nodo,visitados.union({self.id_nodo}),self.reloj[:]],[k])
                    self.almacena_evento('E',['G',visitados.union({self.id_nodo})],self.id_nodo,[k])
                    self.hijos = [k]

            elif (tipo_msg == 'B'):

                if (set(self.vecinos).issubset(visitados)):

                    if (self.padre == self.id_nodo):

                        print("El algoritmo terminó")

                    else:

                        yield env.timeout(random.randint(1,20))
                        self.reloj[self.id_nodo] += 1
                        self.canal_salida.envia(['B',self.id_nodo,visitados,self.reloj[:]],[self.padre])
                        self.almacena_evento('E',['B',visitados],self.id_nodo,[self.padre])
                        
                else:

                    k = self.vecinoMenor(visitados)
                    yield env.timeout(random.randint(1,20))
                    self.reloj[self.id_nodo] += 1
                    self.canal_salida.envia(['G',self.id_nodo,visitados,self.reloj[:]],[k])
                    self.almacena_evento('E',['G',visitados],self.id_nodo,[k])
                    self.hijos = list(set (self.hijos).union({k}))