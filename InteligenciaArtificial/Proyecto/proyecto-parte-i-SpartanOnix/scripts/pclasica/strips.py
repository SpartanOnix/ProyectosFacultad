from itertools import product

import copy
def clona(obj):
    """ Atajo para crear copias de objetos recursivamente. """
    return copy.deepcopy(obj)

def _lista_a_dict(l):
    """ Pone a todos los elementos de la lista en un diccionario cuya
    llave es el nombre del objeto.
    Sólo sirve para listas de objetos con atributo 'nombre'.
    """
    d = {}
    for o in l:
        d[o.nombre] = o
    return d

class Dominio:
    """ Clase para definir el dominio, o espacio de estados en el cual se plantearán problemas de planeación. """
    def __init__(self, nombre, tipos, predicados, acciones):
        """
        Inicializa un dominio
        :param nombre:
        :param tipos:
        :param predicados:
        :param acciones:
        """
        self.nombre = nombre
        self.tipos = tipos
        self.predicados = predicados
        self.acciones = acciones

        self._predicados = _lista_a_dict(predicados)

    def __str__(self):
        dic = {'name':          self.nombre,
               'types':         "\n        ".join(self.tipos),
               'predicates':    "\n        ".join(str(p) for p in self.predicados),
               'actions':       "\n    ".join(str(a) for a in self.acciones)
               }
        return """(define (domain {name})
            (:requirements :strips :typing)
            (:types {types})
            (:predicates {predicates})
            {actions})
        """.format(**dic)

    def declaración(self, nombre):
        """ 
        Devuelve la declaración del predicado con el nombre indicado.
        """
        return self._predicados[nombre]


class Objeto:
    """ Valor concreto para variables en el dominio. """
    def __init__(self, nombre, tipo):
        """
        Crea un objeto existente en el dominio para este problema.
        :param nombre: Símbolo del objeto
        :param tipo: tipo del objeto
        """
        self.nombre = nombre
        self.tipo = tipo

    def __str__(self):
        return "{} - {}".format(self.nombre, self.tipo)


class Variable:
    """ Variable tipada. """
    def __init__(self, nombre, tipo, valor=None):
        """
        :param nombre: símbolo nombre de esta variable.  Los nombres de variables inician con ?
        :param tipo: tipo de la variable, debe estar registrado en la descripción del dominio
        :param valor: objeto vinculado a esta variable, si es None la variable está libre
        """
        self.nombre = nombre
        self.tipo = tipo
        self._valor = valor

    @property
    def valor(self):
        return self._valor

    @valor.setter
    def valor(self, valor_nuevo):
        """
        Permite asignar None o un valor de tipo de esta variable.
        """
        if valor_nuevo and valor_nuevo.tipo != self.tipo:
            raise Exception(f"{valor_nuevo} no es de tipo {self.tipo}")
        self._valor = valor_nuevo

    @valor.deleter
    def valor(self):
        self._valor = None

    def __str__(self):
        if self.valor:
            return self.valor.nombre
        return "{} - {}".format(self.nombre, self.tipo)
        
        
class Formula:
    """
    Predicado o fórmula generada.
    """
    pass


class Predicado(Formula):
    """ Representa un hecho. """
    def __init__(self, declaracion, variables):
        """
        Predicados para representar hechos.
        :param predicado: declaración con los tipos de las variables.
        :param variables: lista de instancias de variables en la acción donde se usa este predicado.
        :param negativo: indica un predicado del tipo "no P", utilizable para especificar efectos o metas.
        """
        self.declaracion = declaracion
        self.variables = variables

    def __str__(self):
        pred = "({0} {1})".format(self.declaracion.nombre, " ".join(v.valor.nombre if v.valor else v.nombre for v in self.variables))
        return pred


class DeclaracionDePredicado:
    """ Representa un hecho. """
    def __init__(self, nombre, variables):
        """
        Declaración de predicados para representar hechos.
        :param nombre:
        :param variables: lista de variables tipadas
        """
        self.nombre = nombre
        self.variables = variables

    def __str__(self):
        pred = "({0} {1})".format(self.nombre, " ".join(str(v) for v in self.variables))
        return pred

    def _verifica_tipos(self, variables):
        """
        Las variables en la lista deben tener los mismos tipos, en el mismo orden, que este predicado.
        """
        for dec, var in zip(self.variables, variables):
            if (dec.tipo != var.tipo):
                raise Exception(f"Los tipos de las variables {dec} y {var} no coinciden")

    def __call__(self, *args):
        """
        Crea un predicado con las variables o valores indicados y verifica que sean del tipo
        correspondiente a esta declaración.

        Cuando se usa dentro de una acción las variables deben ser las mismas instancias para todos
        los predicados dentro de la misma acción.
        """
        variables = []
        for var, arg in zip(self.variables, args):
            if isinstance(arg, Objeto):
                # print("instancia ", arg)
                temp_v = clona(var)
                temp_v.valor = arg
                variables.append(temp_v)
            elif isinstance(arg, Variable):
                # print("variable ", arg)
                variables.append(arg)
            else:
                print("Ni lo uno ni lo otro ", arg, " tipo ", type(arg))

        return Predicado(self, variables)


class No(Formula):
    """
    Negación de un predicado.
    """
    def __init__(self, predicado):
        super().__init__()
        self.predicado = predicado

    def __str__(self):
        return "(not {0})".format(str(self.predicado))



class Acción:
    """ Función de transición con su acción correspondiente. """
    def __init__(self, nombre, parámetros, variables, precondiciones, efectos):
        """
        Inicializa definición de la función de transición para esta acción.
        :param nombre: nombre de la acción
        :param parámetros: lista de variables tipadas
        :param variables: lista de variables libres que pueden tomar su valor de cualquier objeto del domino siempre que
               sus valores satisfagan las restriciones de las precondiciones.
        :param precondiciones: lista de predicados con variables libres
        :param efectos: lista de predicados con variables libres
        """
        self.nombre = nombre
        self.parámetros = parámetros
        self.vars = variables
        self.precondiciones = precondiciones
        self.efectos = efectos

    def __str__(self):
        dic = {'name':      self.nombre,
               'params':    " ".join(str(p) for p in self.parámetros),   # Podrían reunirse 1o los de tipos iguales
               'prec':      " ".join(str(p) for p in self.precondiciones),
               'efec':      " ".join(str(p) for p in self.efectos)
               }

        if self.vars:
            dic['vars'] = "\n        :vars         ({})".format(" ".join(str(v) for v in self.vars))
        else:
            dic['vars'] = ""

        if len(self.precondiciones) >= 2:
            dic['prec'] = "(and " + dic['prec'] + ")"

        if len(self.efectos) >= 2:
            dic['efec'] = "(and " + dic['efec'] + ")"

        return """(:action {name}
            :parameters   ({params}) {vars}
            :precondition {prec}
            :effect       {efec}
            )""".format(**dic)

class Problema:
    """ Definicion de un problema en un dominio particular. """
    def __init__(self, nombre, dominio, objetos, predicados, predicados_meta):
        """
        Problema de planeación en una instancia del dominio.
        :param nombre: nombre del problema
        :param dominio: referencia al objeto con la descripción genérica del dominio
        :param objetos: lista de objetos existentes en el dominio, con sus tipos
        :param predicados: lista de predicados con sus variables aterrizadas, indicando qué cosas son verdaderas en el
               estado inicial.  Todo aquello que no esté listado es falso.
        :param predicados_meta: lista de predicados con sus variables aterrizadas, indicando aquellas cosas que deben
               ser verdaderas al final.  Para indicar que algo debe ser falso, el predicado debe ser negativo.
        """
        self.nombre = nombre
        self.dominio = dominio # ref a objeto Dominio
        d_objetos = {}
        for objeto in objetos:
            if objeto.tipo not in d_objetos:
                d_objetos[objeto.tipo] = [objeto]
            else:
                d_objetos[objeto.tipo].append(objeto)
        self.d_objetos = d_objetos
        self.estado = predicados
        self.meta = predicados_meta

    def __str__(self):
        dic = {'name':          self.nombre,
               'domain_name':   self.dominio.nombre,
               'objects':       "\n      ".join(" ".join(o.nombre for o in self.d_objetos[tipo]) + " - " + tipo for tipo in self.d_objetos),
               'init':          "\n      ".join(str(p) for p in self.estado),
               'goal':          "\n      ".join(str(p) for p in self.meta)}
        if len(self.meta) >= 2:
            dic['goal'] = "(and " + dic['goal'] + ")"
        return """(define (problem {name}
            (:domain {domain_name})
            (:objects {objects})
            (:init {init})
            (:goal {goal})
        """.format(**dic)

    def copia(self):
        """
        Crea una copia del problema self.
        """
        return Problema(self.nombre, self.dominio, self.d_objetos, [x.copia () for x in self.estado], self.meta)

    def varList(self, variables):
        """
        Regresa todas las posibles asignaciones de las variables en la lista de entrada en el estado actual.
        """
        asign = []
        for var in variables:
            asign = [(var, val) for val in self.d_objetos if val.tipo == var.tipo]
            if asign == []:
                return []
            asign.append(asign)
        return map (list, product (*asign))

    def aplicable(self, accion):
        """
        Determina si la acción dada, que debe estar en el dominio, es aplicable en el estado actual del problema.
        """
        vars = []
        vars.extend(accion.parámetros + (accion.vars if accion.vars != None else [])) 
        asign = self.varList(vars)
        if asign == []:
            return (False, asign)
        band = False;
        sust = []
        for val in asign:
            for (elem, obj) in val:
                elem.valor = obj
            if self.satisface (accion.precondiciones):
                band = True
                sust = val
                break
        for elem in vars: elem.valor = None
        return (band, sust)

    def selfMeta(self):
        """
        Metodo para obtener el atributo de meta de la clase problema.
        """
        return self.satisface(self.meta)

    def satisface(self, condiciones):
        """  
        Determina si el estado actual del problema satisface las condiciones dadas (lista de predicados aterrizados). 
        """
        condiciones, estado = self.recorrer(condiciones, self.estado)
        for precondicion in condiciones:
            return_value = False
            for predicado in estado:
                if isinstance(precondicion, No) and isinstance(predicado, No):
                    if (precondicion.predicado.declaracion.nombre == predicado.predicado.declaracion.nombre and
                        self.son_iguales_variables(precondicion.variables, predicado.variables)):
                        return_value = True
                        break
        return return_value

    def recorrer(self, cond, est):
        """
        Recorre la lista de predicados eliminando el and
        """
        list1 = []
        list2 = []
        for c1 in cond:
            if isinstance(c1, And):
                list1 += c1.predicados
            else:
                list1 += c1
        for c2 in est:
            if isinstance(c2, And):
                list2 += c2.predicados
            else:
                list2 += c2
        return (list1, list2)

    def accionarSus(self, accion, sustitucion):
        """
        Aplica la acción con la sustitución dada sobre el problema actual.
        """
        sucesor = self.copia()
        for cop in map(lambda x: x.copia(), accion.efectos):
            for var in cop.variables:
                for (x, y) in sustitucion:
                    if var.tipo == x.tipo and var.nombre == x.nombre:
                        var.valor = y
                        break
            sucesor.elimNegacion(cop)
            sucesor.estado.append(cop)
        return sucesor

    def elimNegacion(self, predicado):
        """
        Elimina la negacion del predicado
        """
        igual = True
        for param in self.estado:
            igual = self.varIguales(param.variables, predicado.variables)
            if (param.nombre == predicado.nombre and param.negativo != predicado.negativo and igual):
                self.estado.remove(param)

    def varIguales(self, list1, list2):
        """
        Determina si dos listas de variables son iguales.
        """
        if len(list1) != len(list2):
            return False
        for i in range(len(list1)):
            if(list1[i].tipo != list2[i].tipo or list1[i].valor != list2[i].valor):
                return False
        return True

class And(Formula):
    """
    Conjuncion de un predicado.
    """
    def __init__(self, predicados):
        super().__init__()
        self.predicados = predicados

    def __str__(self) -> str:
        cade = "(and "
        for elem, pred in enumerate(self.predicados):
            if elem == len(self.predicados) - 1:
                cade += " {0})".format(str(pred))
            else:
                cade += "{0} ".format(str(pred))
        return cade

class Solucion:
    """
    Clase para realizar una búsqueda en amplitud de la solución del problema.
    """

    def __init__ (self, problema):
        """
        Crea un objeto solución con una pila de tuplas (Pr,Pa,A,S)
        :param problema: el problema.
        """
        self.listaAbierta = [Solucion.Nodo(problema)]

    class Nodo:
        """
        Clase para los nodos de búsqueda.
        """

        def __init__(self, problema, padre = None, accion = None, sustitucion = None):
            self.problema = problema
            self.padre = padre
            self.accion = accion
            self.sustitucion = sustitucion

        def sucesores(self):
            """
            Obtiene los sucesores por profundidad.
            """
            if self.problema.selfMeta(): return []
            profundidad = []
            for nod in self.problema.dominio.acciones:
                (band, sust) = self.problema.aplicable(nod)
                if band:
                    for sus in sust:
                        profundidad.append(Solucion.Nodo(self.problema.accionarSus(nod, sus), self, nod, sus))
            return profundidad

    def expande(self, imprime = False):
        """
        Regresa una lista de nodos de búsqueda.
        """
        metas = []
        while self.listaAbierta != []:
            nodoAct = self.listaAbierta.pop(0)
            sucesores = nodoAct.sucesores()
            self.listaAbierta.extend(sucesores)
            for sus in sucesores:
                print(sus.accion.nombre)
                if sus.problema.selfMeta():
                    metas.append(sus)
            if imprime:
                print(self.listaAbierta)
                print(metas, "\n")
        return metas

if __name__ == '__main__':
    print("Crea aquí los objetos del problema y pide a la computadora que lo resuelva")

    param_sostiene = DeclaracionDePredicado('sostiene', [Variable('?k', 'grua'), Variable('?c', 'contenedor')])
    param_libre = DeclaracionDePredicado('libre', [Variable('?k', 'grua')])
    param_en = DeclaracionDePredicado('en', [Variable('?c', 'contenedor'), Variable('?p', 'pila')])
    param_hastarriba = DeclaracionDePredicado('hasta_arriba', [Variable('?c', 'contenedor'), Variable('?p', 'pila')])
    param_sobre = DeclaracionDePredicado('sobre', [Variable('?k1', 'contenedor'), Variable('?k2', 'contenedor')])

    # Parametros
    param_k1 = Variable('?k', 'grua')
    param_k2 = Variable('?k', 'grua')
    param_c1 = Variable('?c', 'contenedor')
    param_c2 = Variable('?c', 'contenedor')
    param_p1 = Variable('?p', 'pila')
    param_p2 = Variable('?p', 'pila')

    # Variables
    var_1 = Variable('?otro', 'contenedor')
    var_2 = Variable('?otro', 'contenedor')

    # Acción
    accion_toma = Acción('toma', [param_k1, param_c1, param_p1],
                    [var_1],
                    [And([param_libre(param_k1), param_en(param_c1, param_p1), param_hastarriba(param_c1, param_p1), param_sobre(param_c1, var_1)])],
                    [And([param_sostiene(param_k1, param_c1), param_hastarriba(var_1, param_p1), 
                        No(param_en(param_c1, param_p1)), No(param_hastarriba(param_c1, param_p1)), No(param_sobre(param_c1, var_1)), No(param_libre(param_k1))])])
    
    accion_pon = Acción('pon', [param_k2, param_c2, param_p2],
                    [var_2],
                    [And([param_sostiene(param_k2, param_c2), param_hastarriba(var_2, param_p2)])],
                    [And([param_en(param_c2, param_p2), param_hastarriba(param_c2, param_p2), param_sobre(param_c2, var_2),
                        No(param_hastarriba(var_2, param_p2)), No(param_sostiene(param_k2, param_c2)), param_libre(param_k1)])])
    
    # Dominio
    dominio = Dominio('plataform-worker-robot', ['contenedor', 'pila', 'grua'],
                        [param_sostiene, param_libre, param_en, param_hastarriba, param_sobre],
                        [accion_toma, accion_pon])

    print(dominio)

    # Objetos
    k1 = Objeto('k1', 'grua')
    k2 = Objeto('k2', 'grua')

    p1 = Objeto('p1', 'pila')
    q1 = Objeto('q1', 'pila')
    p2 = Objeto('p2', 'pila')
    q2 = Objeto('q2', 'pila')

    ca = Objeto('ca', 'contenedor')
    cb = Objeto('cb', 'contenedor')
    cc = Objeto('cc', 'contenedor')
    cd = Objeto('cd', 'contenedor')
    ce = Objeto('ce', 'contenedor')
    cf = Objeto('cf', 'contenedor')
    pallet = Objeto('pallet', 'contenedor')

    # Predicados aterrizados
    paramE_ca_p1 = param_en(ca, p1)
    paramE_cb_p1 = param_en(cb, p1)
    paramE_cc_p1 = param_en(cc, p1)
    paramE_cd_q1 = param_en(cd, q1)
    paramE_ce_q1 = param_en(ce, q1)
    paramE_cf_q1 = param_en(cf, q1)

    paramS_cb_ca = param_sobre(cb, ca)
    paramS_cc_cb = param_sobre(cc, cb)
    paramS_ce_cd = param_sobre(ce, cd)
    paramS_cf_ce = param_sobre(cf, ce)
    paramS_ca_pa = param_sobre(ca, pallet)
    paramS_cd_pa = param_sobre(cd, pallet)

    paramA_cc_p1 = param_hastarriba(cc, p1)
    paramA_cf_q1 = param_hastarriba(cf, q1)
    paramA_pa_p2 = param_hastarriba(pallet, p2)
    paramA_pa_q2 = param_hastarriba(pallet, q2)

    paramL_k1 = param_libre(k1)
    paramL_k2 = param_libre(k2)

    gg = And([param_en(ca, p2), param_en(cb, q2), param_en(cc, p2),
                param_en(cd, q2), param_en(ce, q2), param_en(cf, q2)])

    problema = Problema('dwrpb1', dominio,
                    [k1, k2, p1, q1, p2, q2, ca, cb, cc, cd, ce, cf, pallet],
                    [paramE_ca_p1, paramE_cb_p1, paramE_cc_p1, paramE_cd_q1, paramE_cd_q1, paramE_cf_q1,
                    paramS_ca_pa, paramS_cb_ca, paramS_cc_cb, paramS_cd_pa, paramS_ce_cd, paramS_cf_ce,
                    paramA_cc_p1, paramA_cf_q1, paramA_pa_p2, paramA_pa_q2,
                    paramL_k1, paramL_k2],
                    [gg])
    print(problema)
    problema.aplicable(accion_pon)

    solucion = Solucion(problema)
    solucion.expande()

    """
            Estado inicial
    
        | p1 | q1 | p2 | q2 | K1 | K2 |
        -------------------------------
        | cc | cf | -- | -- | -- | -- |
        | cb | ce | -- | -- | -- | -- |
        | ca | cd | -- | -- | -- | -- |
        -------------------------------
    """

    """
    Punto 7 de la parte 2: No entendi especificamente a que exactamente se referian
    con el problema anterior, pero siguiendo la ligica de mi codigo y asumiendo que
    se refieren al punto 6, si es suficiente la informacion que se tiene para poder
    listar la secuencia de acciones, como cada accion depende del resultado de la
    accion anterior el codigo lleva una secuencia clara.
    """
