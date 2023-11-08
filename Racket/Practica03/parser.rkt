#lang plai
(require (file "./grammars.rkt"))

;; Toma una lista de números, symbolos o listas
;; y la traduce a un árbol de sintaxis abstracta WAE
;; A::=<number>
;;    | <symbol>
;;    | listof(A)
;; parse: A -> WAE
;; parse: s-expression -> WAE
(define (parse sexp)
   (cond
      [(number? sexp) (num sexp)]
      [(symbol? sexp) (id sexp)]
      [(list? sexp) (let ([x (car sexp)] [xs (cdr sexp)])
        (case x
          [(+) (op + (parse xs))]
          [(-) (op - (parse xs))]
          [(*) (op * (parse xs))]
          [(/) (op / (parse xs))]
          [(modulo) (if (not(= (length sexp) 3)) (error "Aridad incorrecta") (op modulo (parse xs)))]
          [(expt) (if (not(= (length sexp) 3)) (error "Aridad incorrecta") (op expt (parse xs)))]
          [(add1) (if (not(= (length sexp) 2)) (error "Aridad incorrecta") (op add1 (parse xs)))]
          [(sub1) (if (not(= (length sexp) 2)) (error "Aridad incorrecta") (op sub1 (parse xs)))]
          [(with) (with (for/list ([i (car xs)]) (binding (car i) (parse (cadr i)))) (parse (cadr xs)))]
          [(with*) (with* (for/list ([i (car xs)]) (binding (car i) (parse (cadr i)))) (parse (cadr xs)))]
          [else (for/list ([i sexp]) (parse i))]))]))