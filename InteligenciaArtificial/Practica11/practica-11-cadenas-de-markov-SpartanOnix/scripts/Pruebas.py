from CadenaDeMarkov import CadenaDeMarkov
import numpy as np

if __name__=='__main__':
    """
    Saque los ejemplo de la pagina: https://www.gestiondeoperaciones.net/cadenas-de-markov/cadenas-de-markov-ejercicios-resueltos/
    """
    print("Una empresa esta considerando utilizar Cadenas de Markov para analizar los cambios en las preferencias de los usuarios por tres marcas distintas de un determinado producto. El estudio ha arrojado la siguiente estimación de la matriz de probabilidades de cambiarse de una marca a otra cada mes:")
    
    # Probabilidades
    p = np.array([[0.80, 0.10, 0.10], [0.03, 0.95, 0.02], [0.20, 0.05, 0.75]])
    print(p)
    
    # Cadena de markov
    cadena = CadenaDeMarkov(
        estados = np.array(['1', '2', '3']),
        estaInit = np.array([0.45, 0.25, 0.30]),
        matriz = p
        )
        
    print("Si en la actualidad la participación de mercado es de 45%, 25% y 30%, respectivamente, entonces:")
    
    print("¿Cuales serán las participaciones de mercado de cada marca en dos meses más?")
    p = cadena.prob_estados([None, None, None])
    for n, p in zip(cadena.estados, p):
        print(n, p)

    print("¿Cuál es la cuota de mercado en el largo plazo para cada una de las marcas descritas en el anterior?.")
    p = cadena.distLim()
    for n, p in zip(cadena.estados, p):
        print(n, p)

    print("Generamos la secuencia de estados a partir del modelo de Márkov iniciado dado el número 7 de elementos que tendrá la secuencia, con la semilla de 0.42 para generar las variable aleatorias.")
    print(cadena.generadorEst(7, 0.42))