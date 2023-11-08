#lang racket

;; Ejercicio 3
;; Recibe un numero n y devuelve si n es mayor a 7
;; esMayorA7? : number -> boolean
(define (esMayorA7? n)
  (if (< 7 n)
     #t
     #f))