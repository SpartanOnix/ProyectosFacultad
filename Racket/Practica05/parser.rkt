#lang plai
(require (file "./grammars.rkt"))

;; Toma una lista de números, symbolos o listas
;; y la traduce a un árbol de sintaxis abstracta CFWBAE
;; A::=<number>
;;    | <symbol>
;;    | listof(A)
;; parse: A -> SCFWBAE
;; parse: s-expression -> SCFWBAE
(define (parse sexp)
   (cond
     [(symbol? sexp) (cond
                                   [(symbol=? 'true sexp) (boolS #t)]
                                   [(symbol=? 'false sexp) (boolS #f)]
                                   [else (idS sexp)])]
     [(number? sexp) (numS sexp)]
     [(boolean? sexp) (boolS sexp)]
     [(list? sexp) (let* ([x (car sexp)] [xs (cdr sexp)])
                             (case x
                               [(if) (if (= (length xs) 3)
                                          (let ([a (car xs)] [b (cadr xs)] [c (caddr xs)]) (iFS (parse a) (parse b) (parse c)))
                                          (error "Falta la else-expresion"))]
                               [(+) (opS + (parse xs))]
                               [(-) (opS - (parse xs))]
                               [(*) (opS * (parse xs))]
                               [(/) (opS / (parse xs))]
                               [(modulo) (if (equal? (length xs) 2) (opS modulo (parse xs)) (error "Aridad incorrecta"))]
                               [(expt) (if (equal? (length xs) 2) (opS expt (parse xs)) (error "Aridad incorrecta"))]
                               [(add1) (if (equal? (length xs) 1) (opS add1 (parse xs)) (error "Aridad incorrecta"))]
                               [(sub1) (if (equal? (length xs) 1) (opS sub1 (parse xs)) (error "Aridad incorrecta"))]
                               [(<) (opS < (parse xs))]
                               [(<=) (opS <= (parse xs))]
                               [(=) (opS = (parse xs))]
                               [(>) (opS > (parse xs))]
                               [(>=) (opS >= (parse xs))]
                               [(not) (if (equal? (length xs) 1) (opS not (parse xs)) (error "Aridad incorrecta"))]
                               [(and) (opS son-andS? (parse xs))]
                               [(or) (opS son-orS? (parse xs))]
                               [(zero?) (if (equal? (length xs) 1) (opS not (parse xs)) (error "Aridad incorrecta"))]
                               [(cond) (parse-cond sexp)]
                               [(with) (withS (for/list ([i (car xs)]) (binding (car i) (parse (cadr i)))) (parse (cadr xs)))]
                               [(with*) (withS* (for/list ([i (car xs)]) (binding (car i) (parse (cadr i)))) (parse (cadr xs)))]
                               [(fun) (funS (car xs) (parse (cadr xs)))]
                               [else (parse-app sexp)]))]))

;; Comprueba que se cumpla el "and"
;; son-andS?: sexp -> bool
(define (son-andS? s)
   (andmap (lambda (x) (equal? true x)) s))

;; Comprueba que se cumpla el "or"
;; son-orS?: sexp -> bool
(define (son-orS? s)
   (ormap (lambda (x) (equal? true x)) s))

;; Parsea las aplicaciones de las funciones
;; parse-app: sexp -> sexp
(define (parse-app f)
   (let ([x (car f)] [xs (cdr f)])
      (cond
         [(and (= (length f) 2) (or (symbol? x) (list? x)) (list? (cadr f)))
           (let ([fun (parse (car f))] [arg (parse-lst (cadr f))])
              (type-case SCFWBAE fun
                [idS (i) (appS fun arg)]
                [funS (par bod) (if (= (length par) (length arg)) (appS fun arg) (error "Algo no salio bien"))]
                [else (list fun arg)]))]
         [else (parse-lst f)])))

;; Parsea listas con elementos del lenguaje
;; parse-lst: list -> sexp
(define (parse-lst lst)
   (match lst
      ['() empty]
      [(cons x xs) (cons (parse x) (parse-lst xs))]))

;; Toma una lista de parejas de condiciones y genera la sintáxis abstracta
;; de una condicional en CFWBAE
;; parse-cond: A -> SCFWBAE
;; parse-cond: s-expression -> SCFWBAE
(define (parse-cond cond-expr)
   (let ([condi (cdr cond-expr)])
      (condS (map (lambda (x) (let ([con (car x)] [the (cadr x)])
                              (if (and (symbol? con) (symbol=? con 'else)) (else-cond (parse the)) (condition (parse con) (parse the))))) condi))))