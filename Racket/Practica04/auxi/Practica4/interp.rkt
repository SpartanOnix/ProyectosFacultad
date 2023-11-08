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
    [mtSub () (error (string-append "lookup: Hay un identificador libre: " (symbol->string name)))]
    [aSub  (name-ds value-ds rest-ds)
           (if (equal? name-ds name)
               value-ds
               (lookup name rest-ds))]))


;; Toma un árbol de sintáxis abstraca del lenguaje CFWAE, un caché de
;; sustituciones y lo interpreta dependiendo de las definiciones dentro del caché,
;; devolviendo el valor numérico correspondiente.
;; interp: CFWAE DefrdSub-> CFWAE-Value
(define (interp expr ds)
 (type-case CFWAE expr
    [id  (i)  (lookup i ds)]
    [num (n)  (numV n) ]
    [op (f args) (let ([nums (map (λ (e) (numV-n (interp e ds))) args) ]) (numV (apply f nums)))]
    [if0 (cond then else) (interp-if cond then else ds) ]
    [fun (params body)    (closure params body ds)]
    [app (fun args)       (interp-app fun (map (λ (x) (interp x ds)) args) ds)]
    [with* (bindings body)
          (if (empty? bindings)
              (interp body ds)
              (interp (desugar-with*-to-fun bindings body) ds))]))


;; Interpreta un if del lenguaje CFWAE tomando sus expresiones cond, then y else,
;; así como un caché de sustituciones.
(define (interp-if cond-expr then-expr else-expr ds)
  (let ([cond-value (interp cond-expr ds)])
           (type-case CFWAE-Value cond-value
             [numV (n) (if (zero? n) (interp then-expr ds) (interp else-expr ds) )]
             [else (error "interp: Símbolo no esperado la condicional de if0, no es un número")])))


;; Interpreta una aplicacion de funcion del lenguaje CFWAE tomando la funcion y sus argumentos,
;; así como un caché de sustituciones.
(define (interp-app fun args ds)
  (let ([cls (interp fun ds)])
      (interp (closure-body cls) (extend-enviroment (closure-param cls) args (closure-env cls)))))

;; Extiende el ambiente recibido a partir de una lista de parametros y argumentos (con la misma aridad).
(define (extend-enviroment params args env)
  (if (= (length params) (length args))
      (cond
        [(empty? params) env]
        [else (aSub (car params) (car args) (extend-enviroment (cdr params) (cdr args) env))])
      (error "interp: La cardinalidad de los argumentos difiere de la aridad de la función")))


;; Desugariza un with* dada su lista de bindings y su cuerpo
(define (desugar-with*-to-fun bindings body)
  (app(fun
      (list (binding-id (car bindings)))
      (with* (cdr bindings) body))
      (list (binding-value (car bindings)))))




(define (prueba sexp)
  (interp (parse sexp) (mtSub)))

