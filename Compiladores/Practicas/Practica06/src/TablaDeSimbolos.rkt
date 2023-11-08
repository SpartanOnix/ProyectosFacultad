#lang nanopass

#|
Compiladores 2022-1
Profesora: Dra. Lourdes del Carmen Gonzalez Huesca
Ayudante: Naomi Itzel Reyes Granados
Laboratorio: Nora Hilda Hernández Luna

Inferencias

Equipo:
•	Cruz Jimenez Alejandro - 316008488 
•	Sandoval Mendoza Antonio - 316075725 
•	Sinencio Granados Dante Jusepee - 316246019
|#

;; Importamos nanopass/base
(require nanopass/base)

;; Funcion para saber si es un primitivo
(define (primitive? x)
   (or
      (procedure? x)
      (memq x '(+ * - / car or cdr and or length))))

;; Funcion para saber si es una constante
(define (constant? x)
   (or
      (number? x)
      (boolean? x)))

;; Funcion para saber si es una variable
(define (variable? x)
   (and
      (symbol? x)
      (not (primitive? x))
      (not (constant? x))))

;; Funcion para saber que tipo es
(define (type? x)
   (or
      (b-type? x)
      (c-type? x)))

;; Funcion para saber si es tipo B
(define (b-type? x) (memq x '(Bool Char Int List Lambda)))

;; Funcion para saber si es tipo C
(define (c-type? x)
   (if (list? x)
       (let* (
          [f (car x)]
          [s (car x)]
          [t (caddr x)])
        (or
           (and (equal? f 'List) (equal? s 'of) (type? t))
           (and (type? f) (equal? s '->) (type? t))))
       #f))

;; Funcion para saber si es un simbolo aritmetico
(define (arit? x) (memq x '(+ - * /)))

;; Funcion para saber si es una lista
(define (lst? x) (memq x '(length car cdr)))

;; Definimos el lenguaje L10
(define-language L10
   (terminals
      (variable (x))
      (primitive (pr))
      (constant (c))
      (type (t)))
   (Expr (e body)
      x
      (const t c)
      (begin e* ... e)
      (primapp pr e* ...)
      (if e0 e1 e2)
      (lambda ([x t]) body)
      (let ([x t e]) body)
      (letrec ([x t e]) body)
      (letfun ([x t e]) body)
      (list e* ...)
      (e0 e1)))

;; Definimos el lenguaje L11
; Agrega los lambdas con mas de un parametro
(define-language L11
   (extends L10)
   (Expr (e body)
      (- (lambda ([x t]) body))
      (+ (lambda ([x* t*] ...) body))))

;; Definimos el lenguaje L12
; Podemos cambiar los constructores por let, letrec y letfun 
(define-language L12
  (extends L11)
  (Expr (e body)
     (- (let ([x t e]) body)
        (letrec ([x t e]) body)
        (letfun ([x t e]) body))
     (+ (let x body)
        (letrec x body)
        (letfun x body))))

;; Definimos el lenguaje L13
; Elimina listas y agrega en su lugar arreglos
(define-language L13
   (extends L12)
   (Expr (e body)
      (- (list e* ...))
      (+ (array c t [e* ...]))))

;; Parser para los lenguajes
(define-parser parse-L10 L10)
(define-parser parse-L11 L11)
(define-parser parse-L12 L12)
(define-parser parse-L13 L13)

;;EJERCICIO 1
;; Funcion que elimina expresiones lambda
(define-pass uncurry : L10 (e) -> L11 ()
   (Expr : Expr (e) -> Expr ())
   (uncurry-aux e))

;; Funcion que elimina expresiones lambda
(define (uncurry-aux expr)
   (nanopass-case (L10 Expr) expr
      [(lambda ([,x ,t]) ,body) (parse-L11 `(lambda ,(asig-lambda expr '()) ,(unparse-L11 (uncurry-aux (body-lambda expr)))))]
      [(let ([,x ,t ,[e]]) ,[body]) (with-output-language (L11 Expr) `(let ([,x ,t ,e]) ,body))]
      [(letrec ([,x ,t ,[e]]) ,[body]) (with-output-language (L11 Expr) `(letrec ([,x ,t ,e]) ,body))]
      [(letfun ([,x ,t ,[e]]) ,[body]) (with-output-language (L11 Expr) `(letfun ([,x ,t ,e]) ,body))]
      [(begin, [e*] ... ,[e]) (with-output-language (L11 Expr) `(begin ,e* ... ,e))]
      [(primapp, pr, [e*] ...) (with-output-language (L11 Expr) `(primapp ,pr, e* ...))]
      [(if, [e0], [e1] ,[e2]) (with-output-language (L11 Expr) `(if ,e0, e1, e2))]
      [(const ,t ,c) (with-output-language (L11 Expr) `(const ,t ,c))]
      [(list ,[e*] ...) (with-output-language (L11 Expr) `(list ,e* ...))]
      [(,[e0] ,[e1]) (with-output-language (L11 Expr) `(,e0 ,e1))]
      [else (parse-L11 (unparse-L10 expr))]))

;; Funcion que obtiene la lista de asignación de una expresión
(define (asig-lambda expr acc)
   (nanopass-case (L10 Expr) expr
      [(lambda ([,x ,t]) ,body) (append (list (list x t)) (asig-lambda body acc))]
      [else acc]))

;; Obtiene una expressión lambda currificada
(define (body-lambda expr)
   (nanopass-case (L10 Expr) expr
      [(lambda ([,x ,t]) ,body) (body-lambda body)]
      [else expr]))

;; EJERCICIO 2
;; Funcion que genera la tabla de símbolos de una expresión
(define (symbol-table-var expr)
   (nanopass-case (L11 Expr) expr
      [else (symbol-table-var-aux expr (make-hash))]))

;; Funcion que genera la tabla de símbolos de una expresión
(define (symbol-table-var-aux expr table)
   (nanopass-case (L11 Expr) expr
      [(let ([,x ,t ,e]) ,body) (begin (hash-set! table x (cons t e)) (symbol-table-var-aux body table))]
      [(letrec ([,x ,t ,e]) ,body) (begin (hash-set! (symbol-table-var-aux body table) x (cons t e)))]
      [(,e0, e1) (begin
                   (define h1 table) (set! h1 (symbol-table-var-aux e0 h1))
                   (define h2 h1) (set! h2 (symbol-table-var-aux e1 h2)) h2)]
      [(primapp ,pr ,[e*] ...) (let f ([e* e*]) (if (null? e*) table (symbol-table-var-aux (first e*) (f (rest e*)))))]
      [(begin ,e* ... , e) (begin (map (lambda (e) (symbol-table-var-aux e table)) e*) (symbol-table-var-aux e table))]
      [(if ,e0, e1, e2) (begin (symbol-table-var-aux e1 table) (symbol-table-var-aux e2 table))]
      [(lambda ([,x* ,t*] ...), body) (symbol-table-var-aux body table)]
      [(list ,e* ... ,e) (begin (map (lambda (e) (symbol-table-var-aux e table)) e*) (symbol-table-var-aux e table))]
      [else table]))

;; EJERCICIO 3
;; Funcion que elimina el valor de los identificadores y el tipo de let, letrec y letfun.
(define-pass assignment : L11 (e) -> L12 (hash)
   (Expr : Expr (e) -> Expr ()
      [(let ([,x ,t ,e]) ,[body]) `(let ,x ,body)]
      [(letrec ([,x ,t ,e]) ,[body]) `(letrec ,x ,body)]
      [(letfun ([,x ,t ,e]) ,[body]) `(letfun, x ,body)])
    (values (Expr e) (symbol-table-var e)))

;; EJERCICIO 4
;; Funcion que traduce las listas en arreglos
(define-pass list-to-array : L12 (e) -> L13 ()
   (Expr : Expr (e) -> Expr()
      [(list ,e* ...) `(array ,(length e*) ,(typeof (e*)) [,e*])]))

;; Funcion para saber de que tipo es la lista
(define (typeof l)
   (let ([firt (car l)])
      (nanopass-case (L13 Expr) firt
         [(const ,t ,c) t]
         [(lambda ([,x* ,t*] ...), body) 'Lambda]
         [(array ,c ,t [,e*]) 'List]
         [else #f])))

;; Ejemplos para corroborar los ejercicios

;; Ejercicio 1
; Entrada: (uncurry (parse-L10 '(lambda ([x Int]) (lambda ([y Int]) (primapp + x y )))))
; Salida: (language:L11 '(lambda ((x Int) (y Int)) (primapp + x y)))

;; Ejercicio 3
; Entrada: (assignment (parse-L11 '(letrec ([foo Lambda (lambda ([x Int]) x)]) (foo (const Int 5)))))
; Salida: (language:L12 '(letrec foo (foo (const Int 5))))

;; Ejercicio 4
; Entrada: (list-to-array ’(list (const Int 1) (const Int 2) (const Int 3) (const Int 4) (const Int 5)))
; Salida: (language:L13 '(array 5 Int [1 2 3 4 5]))