#lang racket

;; Ejercicio 2
;; Recibe un número natural n y devuelve verdadero si n es par, falso en otro caso.
;; esPar? : numner -> boolean
(define (esPar? n)
   (if (= (modulo n 2) 0)
     #t
     #f))