from itertools import product
from functools import reduce

class Variable:
    
    def __init__(self, nombre, valores_posibles):
        """
        Constructor de variable
        """
        self.nombre = nombre
        self.valores_posibles = [str(x) for x in valores_posibles]

    def __str__(self):
        """
        ToString de variable
        """
        return self.nombre + " : " + str(self.valores_posibles)


class Factor:

    def __init__(self, alcance, valores):
        """
        Constructor de factor
        """
        self.alcance = alcance
        self.valores = valores
        self.nombre_vars = list(map(lambda x: x.nombre, self.alcance))

    def __str__(self):
        """
        ToString de factor
        """
        s = ''
        aux = list(product(*[x.valores_posibles for x in self.alcance]))
        s += ((8 +(8*(len(self.nombre_vars)+1))) * '-') + '\n'
        for x in self.nombre_vars:
            s += '| ' + x + '\t'
        s += '|' + '  P(' + ','.join(self.nombre_vars) + ')\n'
        s += ((8 +(8*(len(self.nombre_vars)+1))) * '-') + '\n'
        for i in range(len(aux)):
            s += '| ' + '\t| '.join(aux[i]) + '\t|  ' + str(self.valores[i]) + '\n'
        s += ((8 +(8*(len(self.nombre_vars)+1))) * '-') + '\n'
        return s
    
    def repetidos(self, f2):
        l = list(self.alcance)
        for x in f2.alcance:
            if x not in l:
                l.append(x)
        return l

    def assigmentToIndex(self, asignacion):
        """
        Metodo para encontrar el indice de la asignacion
        """
        asig = []
        for x in self.alcance:
            if x in asignacion:
                asig.append((x, asignacion[x]))
        ind = 0
        for i, (variable, valor) in enumerate(asig):
            ind += variable.valores_posibles.index(str(valor))
            for var, _ in asig[i+1:]:
                ind *= len(var.valores_posibles)
        return ind

    def multiplica(self, f2):
        """
        Metodo para multiplicar dos factores
        for variable in new_var:
            tam *= len(variable.valores_posibles)
        """
        new_var = self.repetidos(f2)
        tam = reduce(lambda x, y: len(y.valores_posibles)*len(x.valores_posibles), new_var)
        new_fac = Factor(new_var, [0]*tam)
        for x in list(product(*[x.valores_posibles for x in new_var])):
            asig = {}
            for i, j in zip(x, new_var):
                asig[j] = i
            new_fac.valores[new_fac.assigmentToIndex(asig)] = self.valores[self.assigmentToIndex(asig)]*f2.valores[f2.assigmentToIndex(asig)]
        return new_fac

    def reduce(self, var, valor):
        """
        Metodo para reducir un factor
        """
        v = str(valor)
        new_var = list(filter(lambda x: x!=var, self.alcance))
        tam = len(new_var[0].valores_posibles) if len(new_var) == 1 else reduce(lambda x, y: len(y.valores_posibles)*len(x.valores_posibles), new_var)
        new_fac = Factor(new_var, [0]*tam)
        for x in list(product(*[x.valores_posibles for x in new_var])):
            asig = {var: v}
            for i, j in zip(x, new_var):
                asig[j] = i
            new_fac.valores[new_fac.assigmentToIndex(asig)] = self.valores[self.assigmentToIndex(asig)]
        return new_fac

    def normaliza(self):
        """
        Metodo para normalizar un factor
        """
        suma = sum(self.valores)
        for x in range(len(self.valores)):
            self.valores[x] = self.valores[x] / suma

    def marginaliza(self, var):
        """
        Metodo para marginalizar un factor
        """
        new_var = list(filter(lambda x: x!=var, self.alcance))
        new_fac = Factor((var, ), [0]*len(var.valores_posibles))
        valores = {key: 0 for key in var.valores_posibles}
        tabla = list(product(*[x.valores_posibles for x in new_var]))
        for v in var.valores_posibles:
            for v_tabla in tabla:
                asig = {var: v}
                for x, y in zip(v_tabla, new_var):
                    asig[y] = x
                    valores[v] += self.valores[self.assigmentToIndex(asig)]
                    new_fac.valores[new_fac.assigmentToIndex({var: v})] = valores[v]
        return new_fac

v1 = Variable("a", [0, 1, 2])
v2 = Variable("b", [1, 2, 3])
f1 = Factor([v1,v2], [.9, .8, .7, .6, .5, .4, .3, .2, .1])
f2 = Factor([v1,v2], [.5 for _ in range(9)])

print("\nFactores a utilizar: \n\nFactor 1:")
print(f1)
print("Factor 2:")
print(f2)

print("Multiplicacion del factor 1 por el factor 2:")
print(f1.multiplica(f2))

print("Reduccion del factor 1:")
print(f1.reduce(v2, 1))

print("Normalizacion del factor 1:")
aux = f1
aux.normaliza()
print(aux)

print("Marginalizacion del factor 1:")
print(f1.marginaliza(v1))


