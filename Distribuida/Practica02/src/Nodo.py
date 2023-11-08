import simpy

class Nodo:
    """Representa un nodo.

    Cada nodo tiene un id, una lista de vecinos y dos canales de comunicación.
    Los métodos que tiene son únicamente getters.
    """
    def __init__(self, id_nodo: int, vecinos: list, canal_entrada: simpy.Store, canal_salida: simpy.Store):
        '''Inicializa los atributos del nodo.'''

    def get_id(self) -> int:
        '''Regresa el id del nodo.'''
        return self.id_nodo

    def get_vecinos(self) -> list:
        '''Regresa los vecinos del nodo.'''
        return self.vecinos

    def get_entrada(self) -> object:
        '''Regresa el canal de entrada.'''
        return self.canal_entrada

    def get_salida(self) -> object:
        '''Regresa el canal de salida.'''
        return self.canal_salida

    def minimo(self,visitados):
        '''Obtiene el menor vecino'''
        diferencia = list(set(self.vecinos).difference(visitados))
        dif = diferencia[0]
        for i in diferencia:
            if i < dif:
                dif = i
        return dif
