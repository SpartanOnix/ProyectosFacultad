#lang plai
(require (file "./grammars.rkt"))
(require (file "./parser.rkt"))
(require (file "./desugar.rkt"))

;; Busca el identificador "name" en el caché de 
;; sustitución "ds" regresando el valor correspondiente
;; o informando un error si no lo encuentra.
;; lookup: symbol DefrdSub -> CFWBAE-Value
(define (lookup name ds)
  (type-case DefrdSub ds
    [mtSub () (error "Variable libre:")]
    [aSub (nameDs valorDs colaDs) (if (equal? name nameDs) valorDs (lookup name colaDs))]))


;; Toma un árbol de sintáxis abstraca del lenguaje CFWAE, un caché de
;; sustituciones y lo interpreta dependiendo de las definiciones dentro del caché,
;; devolviendo el valor numérico correspondiente.
;; interp: CFWBAE DefrdSub-> CFWBAE-Value
(define (interp expr ds) 
    (type-case CFWBAE expr
       [id (i) (let ([tail (lookup i ds)]) (cond
                                                            [(numV? tail) (interp (num (numV-n tail)) (cola ds))]
                                                            [(boolV? tail) (interp (bool (boolV-b tail)) (cola ds))]
                                                            [else tail]))]
       [num (n) (numV n)]
       [bool (b) (boolV b)]
       [iF (con the els) (let ([con (interp con ds)]) (if (boolV? con)
                                                                                (if (boolV-b (interp con ds)) (interp the ds) (interp els ds))
                                                                                (error "Símbolo no esperado la condicional de if, no es un booleano")))]
       [op (fun arg) (let* ([lst (map (lambda (x) (let ([envi (interp x ds)])
                                              (if (numV? envi) (numV-n envi) (boolV-b envi)))) arg)]
                            [tail (apply fun lst)])
                              (cond [(number? tail) (numV tail)] [(boolean? tail) (boolV tail)]))]
       [fun (pa bo) (closure pa bo ds)]
       [app (fun arg) (let* ([val (map (lambda (x) (interp x ds)) arg)]
                         [gloton (interp fun ds)]
                         [par (closure-param gloton)]
                         [envi (closure-env gloton)]
                         [envi-f (ambiente par val envi)])
                    (interp (closure-body gloton) envi-f))]))

;; Checa el cache en la cola
;; cola: DefrdSub -> DefrdSub
(define (cola envi)
   (type-case DefrdSub envi
      [mtSub () mtSub]
      [aSub (nam val ds) ds]))

;; Obtiene el ambiente final de la funcion
;; ambiente: symbol CFWBAE-Value DefrdSub -> DefrdSub
(define (ambiente par arg ds)
  (match par
    ['() ds]
    [(cons x xs) (aSub x (car arg) (ambiente xs (cdr arg) ds))]))
