#lang racket
;; Ejercicio 1
;; Recibe un valor en años edad e y regresa el tiempo vivido en horas, minutos y segundos.
;; edad : number -> string string string
(define (edad e)
   (let*  ((a (+ (* e 8760) (* (truncate (/ e 4)) 24)))
              (b (+ (* e 525600) (* (truncate (/ e 4)) 1440)))
              (c (+ (* e 31536000) (* (truncate (/ e 4)) 86400))))
     (display "Edad en horas: ")
     (display a)
     (display "\nEdad en minutos: ")
     (display b)
     (display "\nEdad en segundos: ")
     (display c)
   )
)


;; Ejercicio 2
;; Recibe un valor i  que es el indice de inicio y un valo n que es el indice de final de la serie
;; y devulve desde el i-esimo valor hasta el n-esimo de la serie de Fibonacci.
;; fibonacci : number number -> number ... number string
;; Ejemplo para llamar la funcion: (fibonacci 0 10)
(define (fibonacci i n)
   (if (= i n)
      (begin
         (display(fiboAux i))
         (display "\nEsta seria la lista de fibonacci hasta el indice: ")
         (display i)
      )
      (begin
         (display (fiboAux i))
         (display ", ")
         (fibonacci (+ i 1) n)
      )
   )
) 

;; Funcion Auxiliar del metodo fibonacci
;; Calcula el n-esimo valor de la serie de fibonacci.
;; fiboAux: number -> number
(define (fiboAux n)
   (cond
      [(= n 0) 1]
      [(= n 1) 1]
      [else (+ (fiboAux (- n 1)) (fiboAux (- n 2)))]
   )
)


;; Estructura de las hojas 
(define-struct leaf (n) #:transparent)

;; Estructura de los arboles
(define-struct node (n i d) #:transparent)

;; Ejercicio 3 a)
;; Resive un valor n y regresa un arbol donde las hojas son los minimos divisores.
;; divTree: number -> tree
(define (divTree n)
   (let* ([div (minDiv n (- n 1) 0)] [res (/ n div)])
      (if (primo? res 2)
         (node n (leaf div) (leaf res))
         (node n (leaf div) (divTree (/ n div)))
      )
   )
)

;; Ejercicio 3 b)
;; Resive un valor n, un valor auxiliar c y una lista vacia l y
;; regresa las hojas en forma de lista con los factores primos.
;; Ejemplo para llamar la funcion: (primeFac 90 0 '())
(define (primeFac n c l)
   (let* ([div (minDiv n (- n 1) 0)] [res (/ n div)])
      (cond
         [(primo? res 2) (cond
                                       [(= div c)  (reverse (cons res (cons (* div c) l)))]
                                       [else (reverse (cons res (cons div l)))])]
         [else (cond
                     [(zero? c) (primeFac res div l)]
                     [(= div c) (primeFac res (* div c) (cons (* div c) l))]
                     [else (primeFac res div (cons c l))])]
      )
   )
)

;; Funcion auxiliar del ejercicio 3
;; Recibe un numero n y un auxiliar c y regresa verdadero si n es primo.
;; primo?: number number -> boolean
(define (primo? n c)
   (cond
      [(not (< c n)) #t]
      [(zero? (modulo n c)) #f]
      [else (primo? n (+ c 1))]
   )
)

;; Funcion auxiliar del ejercicio 3
;; Recibe un numero n, un indice i y un auxiliar c y regresa el minimo divisor de n.
;; minDiv; number number number -> number
(define (minDiv n i c)
   (cond
      [(= i 1) c]
      [(zero? (modulo n i)) (minDiv n (- i 1) i)]
      [else (minDiv n (- i 1) c)]
   )
)

;; Ejercicio 4 a)
;; Sintaxis de los arboles binarios.
;; T ::= e | aTT

;; Ejercicio 4 b)
;; Definimos la estructura tree para arboles binarios donde n es el nodo,
;; i es el sub-arbol izquierdo y d el sub-arbol derecho.
(define-struct tree (n i d) #:transparent)

;; Definimos la estructura de epsilon como terminal.
(define-struct e () #:transparent)

;; Dedinimos la estructura de a como nodo.
(define-struct a () #:transparent)

;; Ejercicio 4 c)
;; Recibe un valor d que sera el nivel hasta donde acabe la estructuracion de los arboles
;; y regresa la estructura de arboles con costruccion abajo-arriba.
;; enumerative: number -> tree
(define (enumerative d)
   (cond
      [(= d 1) (cons (tree (a) (e) (e)) (cons (e) '()))]
      [else (cons (treeEven d) (enumerative (- d 1)))]
   )
)

;; Funcion auxiliar de enumerative
;; Recibe un valor n  y regresa un arbol
;; treeEven: number -> tree
(define (treeEven n)
   (if (= n 0)
     (e)
     (tree (a) (treeEven (- n 1)) (e))
   )
)