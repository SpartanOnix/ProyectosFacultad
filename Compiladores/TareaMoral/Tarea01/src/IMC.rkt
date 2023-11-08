#lang racket

;; Ejercicio 4
;; Recibe un valor peso kg en kilogramos y un valor estatura h en metros y regresa el IMC
;; imc : number number -> number
(define (imc kg h)
  (/ kg (* h h)))