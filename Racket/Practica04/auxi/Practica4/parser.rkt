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
  [(number? sexp) (num sexp)]
  [(symbol? sexp) (id sexp)]
  [(empty? sexp) (error "expresion vacia")]
  [(list? sexp)
     (case (first sexp)
       [(+)   (if(> (length sexp) 2) (op + (map (λ (x) (parse x)) (cdr sexp))) (error "Error: + debe recibir dos o más argumentos" ) )]
       [(-)   (if(> (length sexp) 2) (op - (map (λ (x) (parse x)) (cdr sexp))) (error "Error: - debe recibir dos o más argumentos" ) )]
       [(*)   (if(> (length sexp) 2) (op * (map (λ (x) (parse x)) (cdr sexp))) (error "Error: * debe recibir dos o más argumentos" ) )]
       [(/)   (if(> (length sexp) 2) (op / (map (λ (x) (parse x)) (cdr sexp))) (error "Error: / debe recibir dos o más argumentos" ) )]
       [(modulo) (if(= (length sexp) 3)  (op modulo (map (λ (x) (parse x)) (cdr sexp))) (error "Error: modulo debe recibir dos argumentos" ) )]
       [(expt)   (if(= (length sexp) 3)  (op expt   (map (λ (x) (parse x)) (cdr sexp))) (error "Error: expt debe recibir dos argumentos" ) )]
       [(add1)   (if(= (length sexp) 2)  (op add1   (map (λ (x) (parse x)) (cdr sexp))) (error "Error: add1 debe recibir un argumento" ) )]
       [(sub1)   (if(= (length sexp) 2)  (op sub1   (map (λ (x) (parse x)) (cdr sexp))) (error "Error: sub1 debe recibir un argumento" ) )]
       [(with)   (parse-with-to-fun (cdr sexp)) ]
       [(with*)  (with* (map (λ (x) (binding   (first x) (parse(second x)))) (second sexp)) (parse (third sexp)))]
       [(if0)    (if0   (parse (second sexp))  (parse (third sexp)) (parse (fourth sexp)))]
       [(fun)    (check-fun-duplicates (fun (second sexp) (parse (third sexp))))]
       [(app)    (app   (parse (second sexp))  (map (λ (x) (parse x)) (third sexp)))]
       [else     (app   (parse (first sexp))   (map (λ (x) (parse x)) (second sexp)))]
)]))


(define (parse-with-to-fun sexp)
  (app(fun
      (map (λ (x) (car x)) (first sexp ))
      (parse (second sexp)))
      (map (λ (x) (parse (second x))) (first sexp ))))


(define (check-fun-duplicates exp-fun)
  (let ([dup (check-duplicates (fun-params exp-fun))])
  (if (equal? dup #f )
      exp-fun
      (error (string-append "parser: parámetro definido dos veces: " (symbol->string dup))))))
