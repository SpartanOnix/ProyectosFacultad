import simpy
from Nodo import *
from Canales.CanalRecorridos import *

# La unidad de tiempo
TICK = 1

class NodoConsenso(Nodo):
    ''' Implementa la interfaz de Nodo para el algoritmo de Consenso.'''

    def __init__(self, id_nodo, vecinos, canal_entrada, canal_salida):
        ''' Constructor de nodo que implemente el algoritmo de consenso. '''
        self.id_nodo = id_nodo
        self.vecinos = vecinos
        self.canal_entrada = canal_entrada
        self.canal_salida = canal_salida
        # Atributos extra
        self.V = [None] * (len(vecinos) + 1) # Llenamos la lista de Nones
        self.V[id_nodo] = id_nodo
        self.New = set([id_nodo])
        self.rec_from = [None] * (len(vecinos) + 1)
        self.fallare = False      # Colocaremos esta en True si el nodo fallará
        self.lider = None         # La elección del lider.

    def consenso(self, env, f):
        '''El algoritmo de consenso.'''
        nodo_vivos = []
        num_ronda = env.now
        yield env.timeout(TICK)
        while env.now <= f+1:

            # Aqui tomamos la muerte de un nodo por cada ronda.
            if self.id_nodo+1 == env.now:
                if env.now < f+1:
                    self.fallare = True
                    return

            # Aqui obtenemos a los nodos que sobrevivieron por cada ronda
            if num_ronda == f+1:
                num_ronda = num_ronda-1
            for nodo in self.vecinos:
                if nodo >= num_ronda:
                    nodo_vivos.append(nodo)
            vecinos = nodo_vivos

            # Enviamos los mensajes
            if len(self.New) != 0:
                self.canal_salida.envia((self.id_nodo, self.New), vecinos)

            # Asignamos los mensajes a rec_from
            mensajes = self.canal_entrada.items
            for mensaje in mensajes:
                envia = mensaje[0]
                recibido = mensaje[1]
                self.rec_from[envia] = recibido
            self.New = set()

            # Recorremos rec_from para llenar V[] de forma correcta
            for j in vecinos:
                if self.rec_from[j] != None:
                    for node in self.rec_from[j]:
                        if self.V[node] == None:
                            self.V[node] = node
                            self.New = set([node])

            # Asignamos el lider
            yield env.timeout(TICK)
            for elem in self.V:
                if elem != None:
                    self.lider = elem
                    break
