import simpy
import random
from Nodo import *
from Canales.CanalRecorridos import *

# La unidad de tiempo
TICK = random.randint(1,20) # Tenemos sistema asíncrono

class NodoBroadcast(Nodo):
    '''Implementa el algoritmo broadcast'''

    def __init__(self, id_nodo, hijos, canal_entrada, canal_salida):
        '''Inicializamos el nodo.'''
        self.id_nodo = id_nodo
        self.hijos = hijos
        self.canal_entrada = canal_entrada
        self.canal_salida = canal_salida
        self.mensaje = ""
        self.eventos = [] #Lista de eventos
        self.reloj = 0 # Reloj de lampaurt

    def broadcast(self, envi):
        '''Función de broadcast para nodos.'''
        self.mensaje = "Send Nudes"
        if self.id_nodo == 0:
            yield envi.timeout(TICK)
            self.reloj += 1
            self.canal_salida.envia(self.reloj, (self.id_nodo, self.hijos))
            self.eventos = self.get_eventos('E', self.mensaje, self.id_nodo, self.hijos)
        else:
            while True:
                mensaje = yield self.canal_entrada.get()
                yield envi.timeout(TICK)
                for hijo in self.hijos:
                    self.reloj += 1
                    self.reloj = max(self.reloj, mensaje[0]) + 1
                    self.canal_salida.envia(self.reloj, (self.id_nodo, self.hijos))
                    self.eventos = self.get_eventos('R', self.mensaje, self.id_nodo, self.hijos)
                    self.eventos = self.get_eventos('E', self.mensaje, self.id_nodo, self.hijos)
                break
