#lang plai
;; Ejercicio 1
;; Predicado que recibe un número natural n y devuelve verdadero si n es par, falso en otro caso.
;; esPar? : numner -> boolean
(define (esPar? n)
   (if (= (modulo n 2) 0)
     #t
     #f))

;; Ejercicio 2
;; Función que recibe un número natural n y devuelve una lista en orden ascendente,
;; con los números menores o iguales a n.
;; menores : number -> (listof number)
(define (menores n)
    (if (< n 0)
         empty
         (append (menores (- n 1)) (list n))))

;; Auxiliar ejercicio 3
;; crea_lista: number number list number -> (list of numbers)
(define (crea_lista num inicio lista fin)
    (if (= num inicio)
       (append lista (list inicio))
         (if (< inicio num)
            (crea_lista num (+ inicio fin) (append lista (list inicio)) fin)
            lista)))
;; Ejercicio 3
;; Función que recibe un número natural n y devuelve una lista en orden ascendente,
;; con los números pares desde 0 hasta n.
;; pares: number -> (listof number)
(define (pares n)
   (crea_lista n 0 '() 2))

;; Ejercicio 4
;; Función que recibe un número n y calcula la suma de los primeros n números naturales al cuadrado.
;; Esta función utiliza a fórmula conocida para esta cuenta.
;; suma-cuadrados: number -> number
(define (suma-cuadrados n)
   (/ (* n (+ n 1) (+ (* 2 n) 1)) 6))

;; Ejercicio 5
;; Función recursiva, que calcula la suma de los primeros n números naturales al cuadrado.
;; Esta función no utiliza la fórmula conocida, ni directa ni indirectamente.
;; suma-cuadrados: number -> number
(define (suma-cuadradosR n)
    (if (= 0 n)
         0
         (+ (* n n) (suma-cuadradosR (- n 1)))))

;; Auxiliar ejercicio 6
;; discriminante: number number number -> number
(define (discriminante a b c)
  (- (* b b) (* 4 a c)))
;; Ejercicio 6
;; Función que recibe los términos a, b y c,  de una expresión cuadrática y decide si la expresión tiene
;; raíces reales. La función verifica que el discriminante sea mayor o igual a cero.
;; raicesReales? : number number number -> boolean
(define (raicesReales? a b c)
   (if (< -1 (discriminante a b c))
     #t
     #f))

;; Ejercicio 7
;; Función que recibe tres números a, b y c y devuelve la primer raíz de la fórmula general (sumando la raíz cuadrada)
;; general1: number number number -> number
(define (general1 a b c)
   (if (raicesReales? a b c)
       (/ (+ (* b -1) (sqrt (-(* b b) (* 4 (* a c))))) (* 2 a))
       (error (string-append "general1: El polonomio " (~v a)"x² + "(~v b)"x + "(~v c)" no tiene raíces reales"))))

;; Ejercicio 8
;; Función que recibe tres números a, b y c y devuelve la segunda raíz de la fórmula general (restando la raíz cuadrada)
;; general2: number number number -> number
(define (general2 a b c)
    (if (raicesReales? a b c)
       (/ (- (* b -1) (sqrt (-(* b b) (* 4 (* a c))))) (* 2 a))
       (error (string-append "general2: El polonomio " (~v a)"x² + "(~v b)"x + "(~v c)" no tiene raíces reales"))))

;; Ejercicio 9
;; Función que nos da una lista invertida de la lista pasada como parámetro
;; reversa-lista: (listof a) -> (listof a)
(define (reversa-lista cadena)
  (if (null? cadena)
      '()
       (append (reversa-lista (cdr cadena)) (list (car cadena)))))

;; Ejercicio 10
;; Predicado que nos dice si una lista contiene elementos que forman un palíndromo
;; palindromo-lista?: (listof a) -> Boolean
(define (palindromo? lista)
    (if (equal? lista (reversa-lista lista))
       #t
       #f))