#lang racket

(define e 2)

; ; Funcion identidad
(define (identidad x)
  x)

; ; Funcion suma
; ; Suma-dos: number -> number
(define (suma-dos n)
  (+ n 2))

#|
comentarios
|#

(define (neg? x)
  (if (< x 0)
       #t
       #f))

(define (iguales? n m)
  (if (= n m)
        #t
        #f))

(define (same n m)
  (if (= n m)
        "Es verdadero"
        (error "Hubo un error por que no son iguales")))

(define (menor n m)
  (cond
      [(or (not (number? n)) (not (number? m))) (error "Algun parametro no es un numero")]
      [(< n m) #t]
      [(equal? n m) #f]
      [else #f]))

(define (defVar n m)
  (let ([x (menor n m)]
          [y (menor n 0)]
          [z (menor 0 m)])
    (equal? x  (equal? y z))))

(define (letR n m)
    (let* ([x (+ 1  n)]
              [y (+ x n)]
              [z (- m y)])
      (+ z 1)))

(define (discriminante a b c)
  (- (* b b) (* 4 a c)))

(define (extra a b c)
    (let ([d (discriminante a b c)])
        (- d 1)))

(define quinto
    (let ([a (list 2 3 4 6 7 8)])
        (fifth a)))

; ; (for/list (i 10) i)
; ; (filter (neg?) (list 0 3 -1 5 -43))
; ; (filter (lambda (x) (< x 30)) (list 0 3 -1 5 -43 30 50 31))