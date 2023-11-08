#lang plai
;; Ejercicio 1
;; Función que recibe n, r y devuelve el conjunto con los primeros r múltiplos de n.
;; multiplos: number number -> (listof number)
(define (multiplos n r)
  (map (λ (x) (* n x))
  (for/list ([i r]) (+ i 1))))
 

;; Ejercicio 2
;; Predicado que nos dice si un número m es divisor de otro número n.
;; Si el parámetro recibido es cero, se devuelve un error.
;; divisor?: number number -> number
(define (divisor? m n)
  (cond
    [(= m 0) (error "El cero no es divisor de nadie")]
    [(= (modulo n m) 0) #t]
    [not (= (modulo m n) 0) #f]))

;; Ejercicio 3
;; Función que nos da el una lista de divisores de un número pasado como parámetro
;; divisores: number -> (listof number)
(define (divisores n)
  (filter (lambda (x) (= (modulo n x) 0))
          (for/list ([i n]) (+ i 1))))

;; Ejercicio 4
;; Función que recibe un elemento a, una lista l y decide si a pertenece a l.
;; pertenece: a (listof a) -> boolean
(define (pertenece? a l)
  ;(match l
   ; [(list) #f]
    ;[(cons x xs) (or (equal? x a) (pertenece? a xs))]))
  (if (equal? l (list))
     #f
    (or (equal? a (car l)) (pertenece? a (cdr l)))))

;; Ejercicio 5
;; Función que recibe una lista l con elementos. Devuelve una lista sin repeticiones con los elementos de l.
;; eliminaRep: (listof a) -> (listof a)
(define (eliminaRepetidos lista)
  (if (equal? lista (list))
     (list)
     (cons (car lista) (eliminaRepetidos (filter (lambda (num) (not (equal? (car lista) num))) (cdr lista))))))

;; Estructura que nos permite modelar puntos en el plano.
;; Sirve para modelar figuras geométricas.
(struct Punto (x y) #:inspector #f)


;; Ejercicio 6
;; Funcion que nos permite calcular el punto equidistante entre dos puntos.
;; Si alguno de los dos parámetros recibidos no es un punto, lanza un error
;; punto-medio: Punto Punto -> Punto
(define (punto-medio p q)

  (if (or (not (Punto? p)) (not (Punto? q)))
      (error "N se puede hacer la operacion, error en un punto")
      (let ([x1 (Punto-x p)]
            [y1 (Punto-y p)]
            [x2 (Punto-x q)]
            [y2 (Punto-y q)])
      (Punto (/ (+ x1 x2) 2) (/ (+ y1 y2) 2))
      )))
  

;; Ejercicio 7
;; Funcion que nos permite calcular la distancia entre dos puntos.
;; Si alguno de los dos parámetros recibidos no es un punto, lanza un error
;; distancia: Punto Punto -> number
(define (distancia p q)

(if (or (not (Punto? p)) (not (Punto? q)))
      (error "N se puede hacer la operacion, error en un punto")
      (let ([x1 (Punto-x p)]
            [y1 (Punto-y p)]
            [x2 (Punto-x q)]
            [y2 (Punto-y q)])
      (sqrt(+ (expt(- (Punto-x q) (Punto-x p))2) (expt (- (Punto-y q) (Punto-y p))2)))
      )))
  

;; Ejercicio 8
;; Definición del tipo abstracto de datos Figura
(define-type Figura
  [Circulo (p Punto?) (r positive?)]
  [Triangulo (a Punto?) (b Punto?) (c Punto?)]
  [Rectangulo (s Punto?) (l positive?) (a positive?)]
  [Cuadrado (x Punto?) (y positive?)])

;; Ejercicio 9
;; Función que recibe una figura y calcula su perímetro.
;; perimetro: Figura -> number
(define (perimetro fig)
 (type-case Figura fig
   [Circulo (Circulo-p Circulo-r ) (* (* 2 pi) Circulo-r)]
   [Triangulo (Triangulo-a Triangulo-b Triangulo-c) (+ (+ (distancia Triangulo-a Triangulo-b)
                                                          (distancia Triangulo-a Triangulo-c))
                                                          (distancia Triangulo-b Triangulo-c))]
   [Rectangulo (Rectangulo-s Rectangulo-l Rectangulo-a) (+ (* 2 Rectangulo-a) (* 2 Rectangulo-l))]
   [Cuadrado (Cuadrado-x Cuadrado-y) (* 4 Cuadrado-y)]
   ))

;; Ejercicio 10
;; Función que recibe una figura y calcula su área.
;; area: Figura -> number
(define (area fig)

(type-case Figura fig
   [Circulo (Circulo-p Circulo-r ) (* pi (* Circulo-r Circulo-r))]
   [Triangulo (Triangulo-a Triangulo-b Triangulo-c) (let ([s (/ (+ (distancia Triangulo-a Triangulo-b)   ;; FORMULA HERON VISTA EN CLASE
                                                                   (distancia Triangulo-a Triangulo-c)
                                                                   (distancia Triangulo-b Triangulo-c)) 2)]
                                                           [t 4])
                                                       (sqrt (* s (- s (distancia Triangulo-a Triangulo-b))
                                                                (- s (distancia Triangulo-a Triangulo-c))
                                                                (- s (distancia Triangulo-b Triangulo-c)))))]
   [Rectangulo (Rectangulo-s Rectangulo-l Rectangulo-a) (* Rectangulo-l Rectangulo-a)]
   [Cuadrado (Cuadrado-x Cuadrado-y) (* Cuadrado-y Cuadrado-y)]
   ))
  