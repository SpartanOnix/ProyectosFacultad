[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-f059dc9a6f8d3a56e377f745f24479a46679e63a5d9fe6f495e02850cd0d8118.svg)](https://classroom.github.com/online_ide?assignment_repo_id=5874099&assignment_repo_type=AssignmentRepo)
# Práctica 2 - Estados y espacio de búsqueda.
### Inteligencia Artificial 2021-2

Generación de los posibles estados del gato. 

Debe implementar los métodos que se le indican. Para más información consulte el pdf que se incluye.
Es el código base para la práctica correspondiente. Está prohibido compartir soluciones con compañeros o estudiantes fuera del curso.

## Ejecución
Para compilar y ejecutar este código desde la terminal, se recomiendan los comandos siguientes:

```
javac -d ./classes -cp lib/core.jar:. gatos/*.java
java -cp ./classes:lib/core.jar gatos.Gatos
```
## Observaciones
-Como soy de windows tuve que usar los siguientes comandos modificados para ejecutar la practica:

```
compilar: javac -d ./classes -cp lib\core.jar gatos\*.java
ejecutar: java -cp ./classes;lib\core.jar gatos.Gatos
```

-En el archivo Gatos.java le movi a la linea 32 y 71 para agregar los tipos <Gato> de las listas para que no me salieran warnings.

-Al ejecutarlo sin codigo en las simetrias, solo me daba un juego el cual era:

```
profundidad 1:   profundidad 2:   profundidad 3:   profundidad 4:
    x| |             x|O|             x|O|x            x|O|x
     | |              | |              | |             O| |
     | |              | |              | |              | |

profundidad 5:   profundidad 6:   profundidad 7:
    x|O|x            x|O|x            x|O|x
    O|X|             O|X|O            O|X|O
     | |              | |             X| |
```

Asi ganando "x" y era una secuencia seguida.

-Al ejecutarlo ya con simetrias podiamos ver que todo se ejecutaba normal y la expansion por profundidad era:

```
profundidad 1: 3
profundidad 2: 12
profundidad 3: 66
profundidad 4: 360
profundidad 5: 1710
profundidad 6: 5992
profundidad 7: 15878
profundidad 8: 20964
profundidad 9: 13538
```

Asi que el diametro maximo es 20964 y los nodos en la ultima profundidad son 13538 (Supongo que es menor la cantidad en la profundidad 9 ya que la mayoria de Gatos se completan en la profundidad 8).