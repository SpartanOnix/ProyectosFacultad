#lang racket

;; Ejercicio 1
;; Recibe un numero r y devuelve el area de un circulo dado su radio r
;; area : number -> number
(define (area r)
   (* pi (* r r)))

;; Valor auxiliar pi
;; Propone a pi con el valor de 3.1416
(define pi 3.1416)