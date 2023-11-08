import random as rnd
import numpy as np

class CadenaDeMarkov:
  
    def __init__(self, estados, estaInit, matriz):
        self.estados = estados
        self.estaInit = estaInit
        self.matriz = matriz

    def generadorEst(self, elems, semilla = None):
        """
        Un método para generar una secuencia de estados a partir del modelo de Márkov iniciado dado el número n de elementos que tendrá la secuencia y una semilla.
        """
        # Si nos dan una semilla la ajustamos con un random
        if semilla is not None:
            rnd.seed(semilla)
        # Creamos una variable para el estado inicial aleatorio
        est = self.estaInit
        estados = []
        for _ in range(elems):
            # Obtenemos un estado para cada caso
            estado = rnd.choices(self.estados, est)
            # Lo agregamos a la lista
            estados.append(estado)
            # Multiplicamos la transpuesta de la matriz con est para obtener un nuevo estado inicial
            est = np.dot(self.matriz.T, est)
        # Regresamos la lista de estados
        return estados

    def probAux(self, est, probs, lst):
        """
        Metodo auxiliar para obtener la lista de probabilidad de un estado
        """
        if est is not None:
            # Obtenemos el valor del indice en el estado dado
            index = np.where(self.estados == est)[0][0]
            # Obtenemos el indice y el valor de las probabilidades
            probas = enumerate(probs)
            # Regresamos en un arreglo la lista de probabilidades
            return np.array([p if i == index else lst for i, p in probas])
        # Si el estado es nulo regresamos las probabilidades dadas
        else: return probs
            

    def probCadena(self, est):
        """
        Metodo para obtener la probabilidad de una cadena de estados 
        """
        # Si el estado dado es nulo regresamos un arreglo del tamaño de est
        if est is None: return [*range(self.est.size)]
        # Creamos un arreglo de ceros
        vector0 = np.zeros(self.estaInit.size)
        # Variable para nuestra variable inicial
        est = self.estaInit
        for i in range(1, len(est)):
            est = self.probAux(est[i-1], est, 0)
            probas = self.probAux(est[i], self.matriz.T, vector0)
            est = np.dot(probas, est)
        # Si en el ultimo indice es nulo regresamos el valor de est
        if est[-1] is None: return est
        # Regresamos los estados finales
        return est[np.where(self.estados == est[-1])[0][0]]

    def distLim(self):
        """Formula de la distribucion limite: f* = (1-P^t)^-1"""
        # Ignoramos los renglones
        _ , columnas = self.matriz.shape
        # Obtenemos la identidad y le restamos la transpuenta
        matriz1 = np.identity(columnas) - self.matriz.T
        # Vector con ceros
        vector1 = np.zeros(columnas)
        # Obtenemos las filas
        mFila = np.vstack((matriz1, np.ones(columnas)))
        # Obtenemos las columnas
        mColumna = np.hstack((vector1, np.ones(1)))
        # Sacamos la inversa
        inversa = np.hstack((mFila, mColumna.reshape((-1, 1))))
        if np.linalg.matrix_rank(mFila) == np.linalg.matrix_rank(inversa):
            # Obtenemos la distribucion limite
            return np.linalg.solve(mFila.T @ mFila, mFila.T @ mColumna)
        return "No es la misma solucion"