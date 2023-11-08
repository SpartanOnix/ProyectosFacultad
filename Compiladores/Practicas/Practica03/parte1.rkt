

(require "Parser.rkt"
         "Lexer.rkt"
         nanopass/base)

(define-language LF
  (terminals
   (variable(x))
   (primitive (pr))
   (constamt (c))
   (string (s))
   (char (c1))
   (type (t))
   (lista (l)))
   (Expr (e body)
         x
         pr
         c
         s
         c1
         t
         l
         (begin e* ... e)
         (if e0 e1)
         (if e0 e1 e2)
         (fun ((x* t*) ...) t body* ... body)
         (let ((x* t* e*) ...) body* ... body)
         (funF x ((x* t*) ...) t body* ... body)
         (e0 e1 ...)
         (pr e* ... e)))

;;Ejercicio 3 

(define (varible? x)
  (and (symbol? x) (not (memq x '(and or + - * / > <)))))

(define (typee? x)
  (or (equal? t 'Int) (equal? t 'Bool) (eq? t 'String) (eq? t 'Char)))

(define (lista? x)
  (list? x))

;;Si solo dejamos procedure? nos toma las op
(define (primitive? x)
  (or (procedure? x) (memq x '(and or + - * / > <))))

(define (constant? x)
  (or (number? x) (boolean? x)))

(define-parser parse-LF LF)

;;Ejercicio 2

(define (expr->string e)
  (match e
    [(var-exp e) (symbol->string e)]
    [(num-exp e) (number->string e)]
    [(bool-exp e) (format "~a" e)]
    [(prim-exp op e1 e2) (cond
                            [(equal? op +) (string-append "(+ " (expr->string e1) " " (expr->string e2) ")")]
                            [(equal? op -) (string-append "(- " (expr->string e1) " " (expr->string e2) ")")]
                            [(equal? op *) (string-append "(* " (expr->string e1) " " (expr->string e2) ")")]
                            [(equal? op /) (string-append "(/ " (expr->string e1) " " (expr->string e2) ")")]
                            [(or (equal? op myAnd) (and (symbol? op) (symbol=? op 'and)))
                              (string-append "(and " (expr->string e1) " " (exper->string e2) ")")]
                            [(or (equal? op myOr) (and (symbol? op) (symbol=? op 'or)))
                              (string-append "(or " (expr->string e1) " " (exper->string e2) ")")]
    [(if-then-else-exp e1 e2 e3) (string-append "(if " (expr->string e1) " " (expr->string e2) " " (expr->string e3 ")")]
    [(if-then-exp e1 e2) (string-append "(if " (expr->string e1) " " (expr->string e2)")")]
    [(fun-exp vt e) (string-append "(fun " (type->string vt) " " (expr->string e) ")")]
    [(fun-f-exp e1 e2) (string-append "(funf " (type->string e1) " " (expr->string e2) ")")]
    [(let-exp e1 e2) (string-append "(let " (type->string e1) " " (expr->string e2) ")")]
     [(par-exp e) (expr->string e)]))




