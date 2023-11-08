import random

def stochastic_hill_climber(tam, iter):
    ciudades = {}
    ciudades["vecinos"] = randomizer(tam)
    costo = ciudades["costo"] = valor_maximo(ciudades["vecinos"])
    t = 0
    while t < iter:
        aux = {}
        aux["vecinos"] = vecino_random(ciudades["vecinos"])
        aux["costo"] = valor_maximo(aux["vecinos"])
        if  aux["costo"] >= ciudades["costo"]: ciudades = aux
        if ciudades["costo"] == tam: break
        t += 1
    ciudades["costo_inicial"] = costo
    ciudades["iteraciones"] = t
    return ciudades

def valor_maximo(vector):
    return vector.count("1")

def randomizer(size):
    aux = []
    while size > 0:
        if random.random() < 0.5: aux.append("1")
        else: aux.append("0")
        size -= 1
    return aux

def vecino_random(vector):
    ciudades = vector[:]
    aux = random.randrange(0, len(vector))
    if ciudades[aux] == "0": ciudades[aux]="1"
    return ciudades
