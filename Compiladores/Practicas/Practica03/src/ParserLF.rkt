#lang nanopass
#|
Compiladores 2022-1
Profesora: Dra. Lourdes del Carmen Gonzalez Huesca
Ayudante: Naomi Itzel Reyes Granados
Laboratorio: Nora Hilda Hernández Luna

Parser LF
|#

(require "Lexer.rkt" "Parser.rkt"
   nanopass/base
   (prefix-in : parser-tools/lex-sre)
   (prefix-in re- parser-tools/lex-sre)
   parser-tools/yacc
   parser-tools/lex)

;; Ejercicio 1

;; Definimos nuestro nuevo lenguaje LF
;; se modifica para poder agregar expresiones '*'
(define-language LF
  (terminals
  (variable (x))
  (primitive (pr))
  (constant (c))
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

;; Funcion que almacena nuestro lexer en una funcion lambda sin argumentos
(define (lex-this lexer input) (lambda () (lexer input)))

;;  Funcion para saber si el token es una variable
(define (variable? x)
   (and (symbol? x) (not (memq x '(and or + - * / > <)))))

;;  Funcion para saber si el token es un tipo
(define (type? t)
   (or (equal? t 'Int) (equal? t 'Bool) (eq? t 'String) (eq? t 'Char)))

;;  Funcion para saber si el token es una lista
(define (lista? x)
   (list? x))

;;  Funcion para saber si el token es un primitivo
(define (primitive? x)
   (or (procedure? x) (memq x '(and or + - * / > <))))

;;  Funcion para saber si el token es una constante
(define (constant? x)
   (or (number? x) (boolean? x)))

;; Ejercicio 2

;; Definimos el parseador del lenguaje LF
(define-parser parse-LF LF)

;; Definimos el parseo para las expreciones
(define (expr->string e)
   (match e
   [(var-exp e) (symbol->string e)]
   [(num-exp e) (number->string e)]
   [(bool-exp e) (format "~a" e)]
   [(prim-exp op e1 e2) (cond
      [(equal? op +) (string-append "(+ " (expr->string e1) " " (expr->string e2) ")")]
      [(equal? op -) (string-append "(- " (expr->string e1) " " (expr->string e2) ")")]
      [(equal? op *) (string-append "(+ " (expr->string e1) " " (expr->string e2) ")")])]
   [(if-then-exp e1 e2 e3) (string-append "(if " (expr->string e1) " then " (expr->string e2) " else " (expr->string e3) ")")]
   [(fun-exp vt e) (string-append "(fun (" (expr->string vt) ") => " (expr->string e) "")]
   [(fun-f-exp  e1 e2 e3) (string-append "(funf (" (expr->string e1) " " (expr->string e2) ")) =>" (expr->string e3))]
   [(let-exp e1 e2) (string-append "(let (" (type->string e1) ") " (expr->string e2) ")")]
   [(par-exp e) (expr->string e)]
   [(begin-exp e1) (string-append "Begin { " (expr->string e1) " }")] 
   [(brack-exp e1) (string-append "[" (expr->string e1) "]" )]
   [(typeof-exp e1 e2) (string-append (expr->string e1) " : " (expr->string e2))]
   [(app-exp e1 e2) (string-append "(app " (expr->string e1) " " (expr->string e2) ")")]
   [(app-t-exp e1 e2) (string-append "(" (expr->string e1) (expr->string e2) ")")]))

;; Ejercicio 3

;; Definimos el parseo par los tipos
(define (type->string e)
  (match e
     [(int-exp) "Int"]
     [(boole-exp) "Bool"]
     [(var-exp e) (expr->string (var-exp e))]
     [(typeof-exp v t) (string-append (type->string v) ":" (type->string t))]
     [(typeof-f-exp f v t) (string-append (expr->string f) " (" (type->string v) " " (type->string t) ")")]
     [(app-t-exp t1 t2) (string-append "(" (type->string t1) " " (type->string t2) ")")]
     [(assign-exp t e) (string-append " (" (type->string t) " " (expr->string e) ")")]
     [(brack-exp t) (type->string t)]
     [(par-exp t) (type->string t)]
     [(prim-exp op e1 e2) (string-append (type->string op) (type->string e1) (type->string e2))]))

;; Ejercicio 4

;; Tokens para el lexer
(define-tokens a1 (NUM1 VAR1))
(define-empty-tokens b1 (EOF))

;; Un lexer para las variables
;; Toma una cadena alfanumerica y regresa una lista de tokens, donde cada token es un numero o una cadena
(define var-lexer
   (lexer
      [(:: (:+ (char-range #\a #\z) (:+ (char-range #\A #\Z)))) (cons  `(VAR1 ,(string->symbol lexeme)) (var-lexer input-port))]
      [(:: (:: (:+ (char-range #\0 #\9)) (:* (char-range #\0 #\9)))) (cons `(NUM1 ,(string->number lexeme)) (var-lexer input-port))]
      [(eof)
      `()]))

;: Funcion que toma una lista [x1,...,xn-1,xn] y regresa la lista [x1,...,xn-1]
(define (init l)
   (take l (- (length l) 1)))

;: Funcion que toma la lista de tokens [t1,...,tn] y si el ultimo token es un numero n, regresa [t1,...,n+1]
;: en otro caso, regresa [t1,...,tn,0]
(define (newName lToks)
   (let ([ultimo (last (last lToks))])
      (if (number? ultimo)
         (append (init lToks) (list (quasiquote (NUM1 (unquote (add1 ultimo))))))
         (append lToks (list '(NUM1 0))))))

;; Funcion que recibe una funcion que da una lista de tokens y convierte una lista de tokens en un string
(define (constructVar lToks) 
   (string-join (foldr (lambda (tok l) (cons (format "~a" (last tok)) l)) '() lToks) ""))
  
;; Funcion que toma un simbolo que representa el valor de una variable y regresa un nuevo nombre de una
;; variable como string
(define (newVar x)
   (string->symbol (constructVar (newName (var-lexer (open-input-string (format "~a" x)))))))

;; Funcion que toma naive-rename sobre cada variable que esta en una exprecion generada por el lenguaje LF
(define-pass naive-rename-var : LF (ir) -> LF ()
   (Expr : Expr (ir) -> Expr ()
      [,x (let* ([newX (newVar x)]) newX)]
      [(fun ((,x* ,t*) ...) ,t ,[body*] ... ,[body]) (let* ([newXs (map newVar x*)])
         `(fun ((,newXs ,t*) ...) ,t ,body* ... ,body))]
      [(let ((,x* ,t* ,[e*]) ...) ,[body*] ... ,[body]) (let* ([newXs (map newVar x*)])
         `(let ((,newXs ,t* ,e*) ...) ,body* ... ,body))]
      [(funF ,x ((,x* ,t*) ...) ,t ,[body*] ... ,[body]) (let* ([newX (newVar x)] [newXs (map newVar x*)])
         `(funF ,newX ((,newXs ,t*) ...) ,t ,body* ... ,body))]))

;; Funcion que renombra cada variable que existe en una expresion generada por LF, por lo que las
;; ocurrencias de las variables anidadas de la misma variable son distintas
(define-pass rename-var : LF (ir) -> LF ()
   (Expr : Expr (ir) -> Expr ()
      [,x (let* ([newX (newVar x)]) newX)]
      [(fun ((,x* ,t*) ...) ,t ,[body*] ... ,[body]) (let* ([newXs (map newVar x*)])
         (naive-rename-var `(fun ((,newXs ,t*) ...) ,t ,body* ... ,body)))]
      [(let ((,x* ,t* ,[e*]) ...) ,[body*] ... ,[body]) (let* ([newXs (map newVar x*)])
         (naive-rename-var `(let ((,newXs ,t* ,e*) ...) ,body* ... ,body)))]
      [(funF ,x ((,x* ,t*) ...) ,t ,[body*] ... ,[body]) (let* ([newX (newVar x)] [newXs (map newVar x*)])
         (naive-rename-var `(funF ,newX ((,newXs ,t*) ...) ,t ,body* ... ,body)))]))

;; Ejercicio 5

;; Definimos el lenguaje para eliminar el uso del if
(define-language LNI
   (extends LF)
   (Expr (e body) (- (if e0 e1))))

;; Definimos el parser de este lenguaje
(define-parser parse-LNI LNI)

;; Funcion para eliminar el if de la expresion dada
(define-pass remove-cne-armed-if : LF (ir) -> LNI()
   (Expr : Expr (ir) -> Expr()
      [(if, [e0], [e1]) (if, e0, e1 (void))]))

;; Ejercicio 6

;; Definimos el lenguaje para eliminar las strings como elementos terminales
(define-language LNI2
  (extends LNI)
  (terminals (- (string (s))))
  (Expr (e body) (- s)))

;; Definimos el parser de este lenguaje
(define-parser parse-LNI2 LNI2)

;; Funcion para eliminar las strings como elementos terminales en una expresion dada
(define-pass remove-string : LNI(ir) -> LNI2 ()
  (Expr : Expr (ir) -> Expr()
     [,s '(lista, (string->list , s))]))