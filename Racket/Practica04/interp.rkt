#lang plai
(require (file "./grammars.rkt"))
(require (file "./parser.rkt"))

;; Busca el identificador "name" en el caché de 
;; sustitución "ds" regresando el valor correspondiente
;; o informando un error si no lo encuentra.
;; lookup: symbol DefrdSub -> CFWAE
;; (define (lookup name ds)
(define (lookup name ds)
  (type-case DefrdSub ds
    [mtSub () (error "Variable libre:")]
    [aSub (nameDs valorDs colaDs) (if (equal? name nameDs) valorDs (lookup name colaDs))]))

;; Toma un árbol de sintáxis abstraca del lenguaje CFWAE, un caché de
;; sustituciones y lo interpreta dependiendo de las definiciones dentro del caché,
;; devolviendo el valor numérico correspondiente.
;; interp: CFWAE DefrdSub-> CFWAE-Value
(define (interp expr ds)
 (type-case CFWAE expr
    [id (i) (lookup i ds)]
    [num (n)  (numV n)]
    [if0 (co th el) (let ([cvalue (interp co ds)]) (type-case CFWAE-Value cvalue
                              [numV (n) (if (zero? n) (interp th ds) (interp el ds) )]
                              [else (error "Símbolo no esperado la condicional de if0, no es un número")]))]
    [op (fun arg) (let ([num (map (lambda (x) (numV-n (interp x ds))) arg)]) (numV (apply fun num)))]
    [with* (bd bo) (if (empty? bd)
                              (interp bo ds)
                              (interp (app(fun (list (binding-id (car bd))) (with* (cdr bd) bo)) (list (binding-value (car bd)))) ds))]
    [fun (par bo) (closure par bo ds)]
    [app (fun arg) (let ([env (interp fun ds)])
                      (interp (closure-body env) (envi (closure-param env) (map (lambda (x) (interp x ds)) arg) (closure-env env))))]))

;; Extiende el ambiente.
;; envi: (listof params) (listof args) envirioment -> envirioment
(define (envi params arg env)
  (if (equal? (length params) (length arg))
      (cond [(empty? params) env] [else (aSub (car params) (car arg) (envi (cdr params) (cdr arg) env))])
      (error "La cardinalidad de los argumentos difiere de la aridad de la función")))
