#lang plai
(require (file "./grammars.rkt"))

;; Toma una lista de números, symbolos o listas
;; y la traduce a un árbol de sintaxis abstracta CFWAE
;; A::=<number>
;;    | <symbol>
;;    | listof(A)
;; parse: A -> CFWAE
;; parse: s-expression -> CFWAE
(define (parse sexp)
  (cond
    [(symbol? sexp) (id sexp)]
    [(number? sexp) (num sexp)]
    [(list? sexp) (let* ([x (car sexp)] [xs (cdr sexp)])
                                   (case x
                                     [(if0) (if0 (parse (car xs)) (parse (cadr xs)) (parse (caddr xs)))]
                                     [(+) (if (equal? (length xs) 2) (op + (map (lambda (x) (parse x)) xs)) (error "Aridad incorrecta"))]
                                     [(-) (if (equal? (length xs) 2) (op - (map (lambda (x) (parse x)) xs)) (error "Aridad incorrecta"))]
                                     [(*) (if (equal? (length xs) 2) (op * (map (lambda (x) (parse x)) xs)) (error "Aridad incorrecta"))]
                                     [(/) (if (equal? (length xs) 2) (op / (map (lambda (x) (parse x)) xs)) (error "Aridad incorrecta"))]
                                     [(modulo) (if (equal? (length xs) 2) (op modulo (parse xs)) (error "Aridad incorrecta"))]
                                     [(expt) (if (equal? (length xs) 2) (op expt (parse xs)) (error "Aridad incorrecta"))]
                                     [(add1) (if (equal? (length xs) 1) (op add1 (parse xs)) (error "Aridad incorrecta"))]
                                     [(sub1) (if (equal? (length xs) 1) (op sub1 (parse xs)) (error "Aridad incorrecta"))]
                                     [(with) (app (fun (map (lambda (x) (car x)) (car xs)) (parse (cadr xs)))
                                                                 (map (lambda (x) (parse (cadr x))) (car xs)))]
                                     [(with*) (with* (map (lambda (x) (binding (car x) (parse (cadr x)))) (car xs)) (parse (cadr xs)))]
                                     [(fun) (let ([exp (check-duplicates (fun-params (fun (car xs) (parse (cadr xs)))))])
                                                  (if (equal? exp #f) (fun (car xs) (parse (cadr xs))) (error "Parámetro definido dos veces")))]
                                     [(app) (app (parse (car xs)) (map (lambda (x) (parse x)) (cadr xs)))]
                                     [else (app (parse x) (map (lambda (x) (parse x)) (car xs)))]))]))
