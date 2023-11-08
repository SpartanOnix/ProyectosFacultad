Sinencio Granados Dante Jusepee
Sandoval Mendoza Antonio

Para la implementacion del BFS le paso una lista, la grafica y la raiz, la grafica la 
utilizo en forma de diccionario, y para meter la grafica la pido en de forma de diccionrio
pidiendo 4 cosas:

-Meter la cantidad de nodos que contiene la grafica. (Que sean enteros porfa)

-Meter el Id del nodo. (pueden ser enteros o caracteres ya que al final se parsean en strings)

-Meter el vecino. (Se refiere a los nodos con los que esta relacionado el que metiste en Id)
 en esta opccion puedes meter la letra "x" para indicar que ya no tiene mas relaciones o que
 ese nodo no tiene mas vecinos.

-Meter la raiz. (Se refiere desde que nodo quieres que empieze el BFS, es recomendado que
                 indiques la raiz exacta si metes un arbol y que la raiz si la hayas
		 indicado en Id)

Un ejemplo de como funcionaria es:
>>main()
>>Mete la cantidad de nodos de la grafica: 3
>>Id: A
>>Vecino: B
>>Vecino: C
>>Vecino: x
>>Id: B
>>Vecino: x
>>Id: C
>>Vecino: x
>>Mete la raiz de la grafica: A
{'A':['B','C'], 'B':[], 'C':[]}     --------------> La grafica creada.
A B C                               --------------> Los nodos por los que paso el algoritmo.