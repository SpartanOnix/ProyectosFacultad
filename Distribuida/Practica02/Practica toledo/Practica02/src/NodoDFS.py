import simpy
from Nodo import *
from Canales.CanalRecorridos import *


# La unidad de tiempo
TICK = 1


class NodoDFS(Nodo):
    def __init__(self, id_nodo, vecinos, canal_entrada, canal_salida):
        self.id_nodo = id_nodo
        self.vecinos = vecinos
        self.canal_entrada = canal_entrada
        self.canal_salida = canal_salida

        self.children = []

    def dfs(self, env):
        yield envi.timeout(TICK)
        # El nodo 0 sera la raíz del arbol
        if (self.id_nodo == 0):
            print("El nodo raiz " + str(self.id_nodo) +
                  " ha empezado ha constuir el arbol DFS\n")
            self.parent = self.id_nodo
            minimo = min(self.vecinos)
            self.children.append(minimo)
            visitados = [self.id_nodo]
            self.canal_salida.envia(("Go", visitados, self.id_nodo), [minimo])

        while True:
            mensaje = yield self.canal_entrada.get()
            # Si el mensaje recibido es del tipo GO, entonces
            if (mensaje[0] == "Go"):
                visitados = mensaje[1]

                print("El nodo " + str(self.id_nodo) + " recibio GO de " +
                      str(mensaje[2]) + " el conjunto de visitados " +
                      str(mensaje[1]))
                self.parent = mensaje[2]
                if (len(list(set(self.vecinos) - set(mensaje[1]))) == 0):
                    visitados.append(self.id_nodo)
                    self.canal_salida.envia(("Back", visitados), [mensaje[2]])
                else:
                    minimo = min(set(self.vecinos) - set(mensaje[1]))
                    self.children.append(minimo)
                    visitados.append(self.id_nodo)
                    self.canal_salida.envia(("Go", visitados, self.id_nodo),
                                            [minimo])

            #Si el mensaje recibido es del tipo BACK, entonces
            if (mensaje[0] == "Back"):
                visitados = mensaje[1]
                print("El nodo "+ str(self.id_nodo)+ " recibio BACK de " + str(mensaje[0]) +
                      " el conjunto de visitados " + str(mensaje[1]))

                if (len(list(set(self.vecinos) - set(mensaje[1]))) == 0):
                    if (self.parent == self.id_nodo):
                        print("El arbol ha terminado de construirse")
                    else:
                        self.canal_salida.envia(("Back", visitados),
                                                [self.parent])

                else:
                    minimo = min(set(self.vecinos) - set(mensaje[1]))
                    self.children.append(minimo)
                    self.canal_salida.envia(("Go", visitados, self.id_nodo),
                                            [minimo])
            print("")
            yield envi.timeout(TICK)


if __name__ == "__main__":
    # Inicializamos ambiente y canal
    envi = simpy.Environment()
    bc_pipe = CanalRecorridos(envi)

    # Creamos los nodos
    grafica = []
    adyacencias = [[1, 3, 4, 6], [0, 3, 5, 7], [3, 5, 6], [0, 1, 2], [0],
                   [1, 2], [0, 2], [1]]

    for i in range(0, len(adyacencias)):
        grafica.append(
            NodoDFS(i, adyacencias[i], bc_pipe.crea_canal_de_entrada(),
                    bc_pipe))

    # Y le decimos al ambiente que lo procese
    for i in range(0, len(adyacencias)):
        envi.process(grafica[i].dfs(envi))

