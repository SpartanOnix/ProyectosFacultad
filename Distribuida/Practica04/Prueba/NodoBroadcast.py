import simpy
from Canales.CanalRecorridos import *
from Nodo import *
import random

class NodoBroadcast(Nodo):
    def __init__(self, id_nodo, vecinos, canal_entrada,canal_salida):
        self.id_nodo = id_nodo
        self.vecinos = vecinos
        self.canal_entrada = canal_entrada
        self.canal_salida = canal_salida
        # Atributo extra
        self.mensaje = None
        self.eventos = []
        self.reloj = 0

    def almacena_evento(self,evento,mensaje,emisor,receptores):
        ''' Función que almacena el evento recibido en el atributo eventos del nodo'''
        for e in receptores:
            self.eventos.append([self.reloj,evento,mensaje,emisor,e])

    def broadcast(self,env):
        ''' Algoritmo de broadcast asincrono con relojes de Lamport'''
        if self.id_nodo == 0:

            yield env.timeout(random.randint(1,20))

            self.mensaje = "info"

            for vecino in self.vecinos:
                self.reloj += 1
                self.canal_salida.envia([self.mensaje,self.reloj,self.id_nodo],[vecino])
                self.almacena_evento('E',self.mensaje,self.id_nodo, [vecino])

        else:
            self.mensaje = None

        while True:

            yield env.timeout(random.randint(1,20))

            msg = yield self.canal_entrada.get()

            data_recibida = msg[0]
            cont_recibido = msg[1]
            emisor = msg[2]

            # Elijo el valor más grande entre mi reloj y el que recibí
            self.reloj = max(self.reloj,cont_recibido) + 1

            self.mensaje = data_recibida

            self.almacena_evento('R',self.mensaje,emisor,[self.id_nodo])

            yield env.timeout(random.randint(1,20))

            for vecino in self.vecinos:

                # Aumento  mi reloj por cada envío
                self.reloj += 1
                self.canal_salida.envia([self.mensaje,self.reloj,self.id_nodo],[vecino])
                self.almacena_evento('E',self.mensaje,self.id_nodo, [vecino])
