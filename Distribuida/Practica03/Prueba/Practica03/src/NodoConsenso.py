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

    def elige_lider(self):
        ''' Metodo que itera sobre V y regresa asigna a su lider como el primer
        elemento distinto de None'''

        for e in self.V:
            if e != None:
                self.lider = e
                return 

    def vecinosVivos(self,vecinos,ronda,fallos):
        '''Método que nos regresa a los vecinos que siguen vivos en la ronda'''

        vivos = []

        if ronda == fallos:
            ronda = ronda -1

        for nodo in vecinos:
            if nodo >= ronda:
                vivos.append(nodo)

        return vivos

    def consenso(self, env, f):
        '''El algoritmo de consenso.'''
        yield env.timeout(1)

        while env.now <= f + 1:
            
            # Aqui simulamos que muere un nodo por ronda. 
            # Por ejemplo si f = 2, en la ronda r = 1 muere el nodo p_0 y en la ronda 2 muere el nodo p_1
            if self.id_nodo + 1 == env.now:
                if env.now < f + 1:
                    self.fallare = True
                    return

            vecinos = self.vecinosVivos(self.vecinos,env.now,f + 1)

            if len(self.New) != 0:
                self.canal_salida.envia([self.id_nodo,self.New],vecinos)

            # Esperamos a que lleguen los mensajes, pero no lo tomamos en cuenta como una ronda
            yield env.timeout(0)

            msg = self.canal_entrada.items

            # Hacemos este recorrido para asignar todos los mensajes recibidos en rec
            for mensaje in msg:

                emisor = mensaje[0]
                conj_msg = mensaje[1]

                self.rec_from[emisor] = conj_msg

            self.New = set()

            for v in vecinos:
                if self.rec_from[v] != None:
                    for c in self.rec_from[v]:
                        if self.V[c] == None:

                            self.V[c] = c
                            self.New = self.New.union(set([c]))

            yield env.timeout(1)

        self.elige_lider()