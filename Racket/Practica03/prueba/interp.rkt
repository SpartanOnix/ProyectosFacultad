#lang plai
(require (file "./grammars.rkt"))
(require (file "./parser.rkt"))

;; Recibe una expresión (expr) del lenguaje WAE,
;; un id (sub-id) y otra expresión (value).
;; Sustituye el valor sub-id por value, en expr.
;; subst: WAE symbol WAE -> WAE
(define (subst expr sub-id value)
  (type-case WAE expr
    [id (i) (if (symbol=? sub-id i) value (id i))]
    [num (n) (num n)]
    [op (f args) (op f (for/list ([i args]) (subst i sub-id value)))]
    [with (bindings body) (cond
                       [(contiene? sub-id bindings) (with (intercambia sub-id value bindings) body)]
                       [else (with (intercambia sub-id value bindings) (subst body sub-id value))])]
    [with* (bindings body) (subst (transforma expr) sub-id value)]))
                                      
;; Toma un árbol de sintáxis abstraca del lenguaje WAE
;; y lo interpreta, devolviendo el valor numérico correspondiente
;; interp: WAE -> number
(define (interp expr)
   (type-case WAE expr
     [id (i) (error "Error: variable libre")]
     [num (n) n]
     [op (f args) (apply f (for/list ([i args]) (interp i)))]
     [with (bindings body) (cond
                    [(empty? bindings) (interp body)]
                    [else (interp (with (cdr bindings) (subst body (binding-id (car bindings)) (binding-value (car bindings)))))])]
     [with* (bindings body) (cond
                     [(empty? bindings) (interp body)]
                     [else (interp (with (list (car bindings)) (with* (cdr bindings) body)))])]))

;; transforma: WAE(with*) -> WAE(with)
(define (transforma expr)
  (if (empty? (with*-bindings expr))
    (with*-body expr)
    (with (list (car (with*-bindings expr))) (transforma (with* (cdr (with*-bindings expr)) (with*-body expr))))))

;; contiene?: symbol (listof binding) -> Bool
(define (contiene? simbolo bindings)
  (if (empty? bindings)
    #f
    (or (symbol=? simbolo (binding-id (car bindings))) (contiene? simbolo (cdr bindings)))))

;; intercambia: symbol WAE (listof binding) -> (listof binding)
(define (intercambia sub-id value bindings)
  (if (empty? bindings)
     empty
    (cons (binding (binding-id (car bindings)) (subst (binding-value (car bindings)) sub-id value)) (intercambia sub-id value (cdr bindings)))))