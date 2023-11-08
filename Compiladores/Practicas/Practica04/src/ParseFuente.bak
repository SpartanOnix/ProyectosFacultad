#lang nanopass

#|
Compiladores 2022-1
Profesora: Dra. Lourdes del Carmen Gonzalez Huesca
Ayudante: Naomi Itzel Reyes Granados
Laboratorio: Nora Hilda Hernández Luna

Parser fuente

Equipo:
•	Cruz Jimenez Alejandro - 316008488 
•	Sandoval Mendoza Antonio - 316075725 
•	Sinencio Granados Dante Jusepee - 316246019
|#

(require nanopass/base)

;; Definimos el lenguaje LF como viene en el PDF
(define-language LF
   (terminals
   (variable (x))
   (primitive (pr))
   (constant (c))
   (type (t)))
   (Expr (e body)
      x
      pr
      c
      t
      (quot c)
      (begin e* ... e)
      (primapp pr e* ...)
      (if e0 e1 e2)
      (lambda ([x* t*] ...) body)
      (let ([x* t* e*] ...) body)
      (letrec ([x* t* e*] ...) body)
      (list e* e)
      (e0 e1 ...)))

;; Definimos los predicados del lenguaje
(define (variable? x)
  (symbol? x))

(define (type? t)
 (or (equal? t 'Int) (equal? t 'Bool) (eq? t 'String) (eq? t 'Lambda)))

(define (constant? x)
  (or (number? x) (boolean? x)))

(define (primitive? x)
  (or (procedure? x) (memq x '(+ * - / car or cdr and or length))))

;; Definimos el lenguaje L7 en el que eliminaremos las listas de let y solo asignaremos un let
(define-language L7
   (extends LF)
   (Expr (e body)
      (- (let ([x* t* e*] ...) body)
         (letrec ([x* t* e*] ...) body))
      (+ (let ([x t e]) body)
         (letrec ([x t e]) body))))

;; Definimos el lenguaje L8 en el que definiremos a letfun
(define-language L8
   (extends L7)
   (Expr (e body)
      (+ (letfun ([x t e]) body))))

;; Definimos los parsers de los lenguajes
(define-parser parse-LF LF)
(define-parser parse-L7 L7)
(define-parser parse-L8 L8)

;; Ejercicio 1
;; Tomara la expresion dada para transformar su let universal a un let por cada asignacion
(define-pass curry-let : LF (e) -> L7 ()
  (Expr : Expr (e) -> Expr ()
        [(let ([,x* ,t* ,[e*]] ...) ,[body])
         (let f ([bindingx* x*]
                 [bindingt* t*]
                 [bindinge* e*])
           (if (equal? (length bindingx*) 1)
                `(let ([,(car bindingx*) ,(car bindingt*) ,(car bindinge*)]) ,body)
                `(let ([,(car bindingx*) ,(car bindingt*) ,(car bindinge*)]) ,(f(cdr bindingx*) (cdr bindingt*) (cdr bindinge*)))))]
        [(letrec ([,x* ,t* ,[e*]] ...) ,[body])
         (let f ([bindingx* x*]
                 [bindingt* t*]
                 [bindinge* e*])
           (if (equal? (length bindingx*) 1)
                `(let ([,(car bindingx*) ,(car bindingt*) ,(car bindinge*)]) , body)
                `(let ([,(car bindingx*) ,(car bindingt*) ,(car bindinge*)]) ,(f(cdr bindingx*) (cdr bindingt*) (cdr bindinge*)))))]))

;; Ejercicio 2
;; Tomara la expresion dada para transformar sus let's a los que se le esten asignando lambdas
;; a un solo letrec
(define-pass identify-assigments : L7 (e) -> L7 ()
   (Expr : Expr (e) -> Expr ()
      [(let ([,x,t,e]) ,[body])
         (if (equal? t 'Lambda)
            `(letrec ([,x,t,e]) ,body)
            `(let ([,x,t,e]) ,body))]))

;; Ejercicio 3
;; Tomara la expresion dada para cachar las lambdas y asignarlas en un letfun
(define-pass un-anonymous : L7 (e) -> L8 ()
   (Expr : Expr (e) -> Expr ()
      [(lambda ([,x* ,t*] ...) ,[body])
         `(letfun ([,'foo ,'Lambda (lambda ([,x* ,t*] ...) ,body)]) ,'foo)]))

;; Ejercicio 4
; Función auxiliar si la aridad del operador es compatible con el número de argumentos
(define (prc-ar pr act)
   (match pr
      ["+" (< 1 act)]
      ["-" (< 1 act)]
      ["*" (< 1 act)]
      ["/" (< 1 act)]
      ["length" (eq? 1 act)]
      ["car" (eq? 1 act)]
      ["cdr" (eq? 1 act)]))

;; Tomara la expresion dada para verificar si los primitivos estan siendo aplicados en sus operadores con
;; la aridad correcta
(define-pass verify-arity : L8 (e) -> L8()
   (Expr : Expr (e) -> Expr ()
      [(primapp, pr, [e*] ...)
         (if (prc-ar (symbol->string pr) (length e*))
            `(primapp, pr, e* ...)
            (error (string-append "arriba de " (symbol->string pr)
               ", <" (~v (length e*)) ">")))]))

;; Ejercicio 5
;; Tomara la expresion dada para verificar que la expresion no contenga variables libres
(define-pass verify-vars : L8 (ir) -> L8 ()
  (Expr : Expr (ir [env null]) -> Expr ()
        [,x
         (if (memq x env)
             x
             (error (string-append "Var libre: " (symbol->string x))))]
        [(let ([,x ,t ,[e]]) ,[Expr : body (cons x env) -> body])
         `(let ([,x ,t ,e]) ,body)]
        [(letfun ([,x ,t ,[e]]) ,[Expr : body (cons x env) -> body])
         `(letfun ([,x ,t]) ,body)]
        [(letrec ([,x ,t ,[Expr : e (cons x env) -> e]]) , [Expr : body (cons x env) -> body])
         `(letrec ([,x ,t ,e]) ,body)]
        [(lambda ([,x*, t*] ...) ,[Expr : body (append x* env) -> body])
         `(lambda ([,x* ,t*] ...) ,body)]))

;; Ejemplos para corroborar los ejercicios

;; Ejercicio 1
; Entrada: (curry-let (parse-LF '(let ([ x Int 4] [ y Int 6]) (+ x y ) )))
; Salida: (language:L7 '(let ((x Int 4)) (let ((y Int 6)) (+ x y))))

;; Ejercicio 2
; Entrada: (identify-assigments (parse-L7 '(let ([ foo Lambda ( lambda ([ x Int ]) x ) ]) ( foo 5) )))
; Salida: (language:L7 '(letrec ((foo Lambda (lambda ((x Int)) x))) (foo 5)))

;; Ejercicio 3
; Entrada: (un-anonymous (parse-L7 '( lambda ([ x Bool ]) (if x 1 2) )))
; Salida: (language:L8 '(letfun ((foo Lambda (lambda ((x Bool)) (if x 1 2)))) foo))

;; Ejercicio 4
; Entrada: (verify-arity (parse-L8 '(primapp + 2 3)))
; Salida: (language:L8 '(primapp + 2 3))
; Entrada: (verify-arity (parse-L8 '(primapp car 2 3)))
; Salida: arriba de car, <2>

;; Ejercicio 5
; Entrada: (verify-vars (parse-L8 '(primapp + 2 x)))
; Salida: Var libre: x