#lang nanopass


(require nanopass/base)

;; se agrega para el manejo de tabla hash y uniones entre tablas hash
(require racket/hash)

#|
Compiladores 2021-1
Profesora: Dra. Lourdes del Carmen Gonzalez Huesca
Ayudante: Juan Alfonso Garduño Solís
Laboratorio: Fernando Abigail Galicia Mendoza

Our first approach with nanopass.
|#

(require parser-tools/lex 
         (prefix-in : parser-tools/lex-sre)
         (prefix-in re- parser-tools/lex-sre)
         parser-tools/yacc)

; Calling the script of "Practica 2 - Parte 2"
(require "Parser.rkt")

;Ejercicio 1)----------------------------------------------------------------------------------------------------------
;Implementado en Compiler y Parser (se eliminó el token Func, y sea gregó BEGIN, ARROW, se agregó if1-expr a Parser).


;Ejercicio 2)----------------------------------------------------------------------------------------------------------
; Function that returns the string representation of a ASA
(define (expr->string e)
  (match e
    [(var-exp e) (symbol->string e)]
    [(num-exp e) (number->string e)]
    [(bool-exp e) (format "~a" e)]
    [(bin-exp e0 e1 e2) (if (eq? e0 + ) (string-append "(+ " (expr->string e1) " " (expr->string e2) ")")
                        (if (eq? e0 - ) (string-append "(- " (expr->string e1) " " (expr->string e2) ")")
                        (if (eq? e0 * ) (string-append "(* " (expr->string e1) " " (expr->string e2) ")")
                        (if (eq? e0 * ) (string-append "(* " (expr->string e1) " " (expr->string e2) ")")
                        (if (eq? e0 / ) (string-append "(/ " (expr->string e1) " " (expr->string e2) ")")
                        (if (eq? e0 'and ) (string-append "(and " (expr->string e1) " " (expr->string e2) ")")
                        (if (eq? e0 'or ) (string-append "(or " (expr->string e1) " " (expr->string e2) ")")
                        ("")   )  ) ) ) ) ) )]
    [(if1-exp e1 e2) (string-append "(if " (expr->string e1) " " (expr->string e2) ")")]
    [(if-exp e1 e2 e3) (string-append "(if " (expr->string e1) " " (expr->string e2) " " (expr->string e3) ")")]
    [(typeof-exp v e) (string-append "(" (expr->string v) " " (expr->string e) ")" )]
    [(int-exp) (string-append "Int" "")]
    [(boole-exp) (string-append "Bool" "")]
    [(let-exp e1 e2) (string-append "(let " (expr->string e1) "in " (expr->string e2) ")" )]
    [(appt-exp e1 e2) (string-append  (expr->string e1) " " (expr->string e2) )]
    [(app-exp e1 e2) (string-append "(" (expr->string e1) " " (expr->string e2) ")" )]
    [(fun-exp e1 e2) (string-append "(fun " (expr->string e1) " => " (expr->string e2) ")" )]
    [(fun-f-exp e1 e2 e3) (string-append "(funF " (expr->string e1) " " (expr->string e2) " => " (expr->string e3)")")]
    [(begin-exp e1) (string-append "(begin (" (expr->string e1) "))")]
    [(assign-exp e1 e2) (string-append "( " (expr->string e1) " = " (expr->string e1)")")]
    [(par-exp e1) (string-append "" (expr->string e1) "" )]
    [(key-exp e1) (string-append "" (expr->string e1) "" )]
    [(bra-exp e1) (string-append "" (expr->string e1) "" )]
    ))


;---------------------------------------------------------------------------------------------------------------------------------------
; The definition of our language
(define-language LF
  (terminals
   (variable (x))
   (primitive (pr))
   (constant (c))
   (string (s))
   (type (t)))
  (Expr (e body)
    x
    pr
    c
    s
    t
    (begin e* ... e)
    (if e0 e1)
    (if e0 e1 e2)
    (fun ((x* t*) ...) t body* ... body)
    (let ((x* t* e*) ...) body* ... body)
    (funF x ((x* t*) ...) t body* ... body)
    (e0 e1 ...)
    (pr e* ... e)))

;Ejercicio 3) -----------------------------------------------------------------
;Some predicates
(define (variable? x)
  (symbol? x))

(define (type? t)
 (or (equal? t 'Int) (equal? t 'Bool) (eq? t 'String)))

(define (constant? x)
  (or (number? x) (boolean? x)))

(define (pr? x)
  (or (procedure? x) (eq? x 'and) (eq? x 'or)))

;------------------------------------------------------------------------------------------
;Ejercicio 4) Alpha equivalencia

;Funciones auxiliares de Fernando pero decidí hacerlo de otra forma  ----------------------
; Tokens for the lexer
(define-tokens a1 (NUM1 VAR1))
(define-empty-tokens b1 (EOF))

; A lexer for the variables.
; Takes an alphanumeric string and returns a list of tokens, where each token is
; a number or a string.
(define var-lexer
  (lexer
   [(:: (:+ (char-range #\a #\z) (:+ (char-range #\A #\Z))))
    ; =>
    (cons  `(VAR1 ,(string->symbol lexeme)) (var-lexer input-port))]

   [(:: (:: (:+ (char-range #\1 #\9)) (:* (char-range #\0 #\9))))
    ; =>
    (cons `(NUM1 ,(string->number lexeme)) (var-lexer input-port))]

   [(eof)
    `()]))

; Define a function that takes a list [x1,...,xn-1,xn] and
; returns the list [x1,...,xn-1]
(define (init l)
  (error "Oopsie Woopsie! Uwu We made a fucky wucky!! A wittle fucko boingo! The code monkeys at our headquarters are working VEWY HAWD to fix this!"))

; Define a function that takes a list of tokens [t1,...,tn] and if the last token is
; a number n, returns [t1,...,n+1]; otherwise, returns [t1,...,tn,0]
(define (newName lToks)
  (error "Oopsie Woopsie! Uwu We made a fucky wucky!! A wittle fucko boingo! The code monkeys at our headquarters are working VEWY HAWD to fix this!"))

; Define a function that takes a function that given a list of tokens,
; flats the list of tokens into a string
(define (constructVar lToks)
  (if (empty? lToks)
      ("")
      (match (first lToks)
            [`(NUM1 ,x) (string-append (number->string x) (constructVar (rest lToks)))]
            [`(VAR1 ,x) (string-append (symbol->string x) (constructVar (rest lToks)))])))

; Define a function that takes a symbol that represents a name of a variable
; and returns a fresh name of variable as a string
;(define (newVar x)
;  (constructVar (newName (var-lexer x))))


;Aquí terminan los hints de Fernando ------------------------------------------------------------------------------------------------


;; Funcion para obtener las variables libres en una expresión dada
(define (freeVars x)
  (nanopass-case (LF Expr) x
    ;; FV(x) = {x} 
    ;; FV(c) = vacio
    ;; FV(o(t1, t2, ... , tn)) = FV(t1) U FV(t2) U ... U FV(tn)o es un operador n-ario
    ;; FV(x.t) = FV(t)\{x} donde x.t es un ligado, x una variable y t una expresion
    [,x (mutable-set x)]
    [(begin ,e* ... ,e)
      (let ([set-vars (mutable-set)])
        (for-each (lambda (st) (set-union! set-vars st)) (map freeVars e*))
        (set-union! set-vars (freeVars e))
        set-vars)]
    [(if ,e0 ,e1 ,e2)
      (let ([set-vars (mutable-set)])
      (set-union! set-vars (freeVars e0))
      (set-union! set-vars (freeVars e1))
      (set-union! set-vars (freeVars e2))
      set-vars)]
    [(fun ((,x* ,t*) ...) ,t ,body* ... ,body)
      (let ([set-vars (mutable-set)])
        (for-each (lambda (st) (set-union! set-vars st)) (map freeVars body*))
        (set-union! set-vars (freeVars body))
        (for-each (lambda (v) (set-remove! set-vars v)) x*)
        set-vars)]
    [(funF ,x ((,x* ,t*) ...) ,t ,body* ... ,body)
      (let ([set-vars (mutable-set)])
        (for-each (lambda (st) (set-union! set-vars st)) (map freeVars body*))
        (set-union! set-vars (freeVars body))
        (for-each (lambda (v) (set-remove! set-vars v)) x*)
        set-vars)]
    [(let ((,x* ,t* ,e*) ...) ,body* ... ,body)
      (let ([set-vars (mutable-set)])
        (for-each (lambda (st) (set-union! set-vars st)) (map freeVars body*))
        (set-union! set-vars (freeVars body))
        (for-each (lambda (v) (set-remove! set-vars v)) x*)
        (for-each (lambda (st) (set-union! set-vars st)) (map freeVars e*))
        set-vars)]

    [else (mutable-set)]))

;; renombra las variables por x0, x1, ..., xn
(define-pass rename-var : LF (ir) -> LF ()
  (definitions
    ;; variable auxiliar para indexar variables según las creamos
    (define id-simbolo-actual 0)
    ;; se devuelve la siguiente variable de la forma xn+1 si xn fue la última generada por la función
    (define (newVar)
      (let* ([str-numero (number->string id-simbolo-actual)]
             [str-simbolo (string-append "x" str-numero)])
        (set! id-simbolo-actual (add1 id-simbolo-actual))
        (string->symbol str-simbolo)))

    ;; Renombra las variables que recibe como entrada y las guarda en un diccionario Hash
    (define (renameFreeVars vars-libres)
      (let ([tabla-renombrado (hash)])
        (set-for-each vars-libres
          (lambda (var) (set! tabla-renombrado (hash-set tabla-renombrado var (newVar)))))
        tabla-renombrado)))

      ;; Procesaiento de una expresion para obtener el renombrado de
      ;; sus variables.
      (Expr : Expr (ir tabla) -> Expr ()
        [,x `,(hash-ref tabla x x)]
        [(if ,e0 ,e1 ,e2) `(if ,(Expr e0 tabla) ,(Expr e1 tabla) ,(Expr e2 tabla))]
        [(begin ,e* ... ,e) `(begin ,(map (lambda (x) (Expr x tabla)) e*) ... ,(Expr e tabla))]
        [(fun ((,x* ,t*) ... ) ,t ,body* ... ,body)
          (let ([tabla-local (hash)])
            (for-each (lambda (var) (set! tabla-local (hash-set tabla-local var (newVar)))) x*)
            (set! tabla-local (hash-union tabla tabla-local #:combine/key (lambda (k vg vl) vl)))
            `(fun ((,(map (lambda (x) (Expr x tabla-local)) x*) ,t*) ...) ,t
              ,(map (lambda (x) (Expr x tabla-local)) body*) ... ,(Expr body tabla-local)))]
        [(funF ,x ((,x* ,t*) ... ) ,t ,body* ... ,body)
          (let ([tabla-local (hash)])
            (for-each (lambda (var) (set! tabla-local (hash-set tabla-local var (newVar)))) x*)
            (set! tabla-local (hash-union tabla tabla-local #:combine/key (lambda (k vg vl) vl)))
            `(funF ,x ((,(map (lambda (x) (Expr x tabla-local)) x*) ,t*) ...) ,t
              ,(map (lambda (x) (Expr x tabla-local)) body*) ... ,(Expr body tabla-local)))]
		[(let ((,x* ,t*, e*) ...) ,body* ... ,body)
          (let ([tabla-local (hash)])
            (for-each (lambda (var) (set! tabla-local (hash-set tabla-local var (newVar)))) x*)
            (set! tabla-local (hash-union tabla tabla-local #:combine/key (lambda (k vg vl) vl)))
            `(let ((,(map (lambda (x) (Expr x tabla-local)) x*) ,t* ,(map (lambda (x) (Expr x tabla)) e*)) ...)
              ,(map (lambda (x) (Expr x tabla-local)) body*) ... ,(Expr body tabla-local)))]
		)
			  

        ;; Generamos el Hash table para renombrar las variables libres;
        (let ([tabla-renombrado (renameFreeVars (freeVars ir))])
          (Expr ir tabla-renombrado)))



;Funciones auxiliares para alpha equivalencia --------------------------------------------------------------------------------------



; The parser of LF
(define-parser parse-LF LF)

; A function that make explicit the ocurrences of the begin
(define-pass make-explicit : LF (ir) -> LF ()
  (Expr : Expr (ir) -> Expr ()
    [,c `',c]
    [(fun ([,x* ,t*] ...) ,t ,[body*] ... ,[body])
     `(fun ([,x* ,t*] ...) t (begin ,body* ... ,body))]
    [(let ([,x* ,t* ,[e*]] ...) ,[body*] ... ,[body])
     `(let ([,x* ,t* ,e*] ...) (begin ,body* ... ,body))]
    [(funF ,x ([,x* ,t*] ...) ,t ,[body*] ... ,[body])
     `(funF x ([,x* ,t*] ...) t (begin ,body* ... ,body))]))

;Ejercicio 5)         -------------------------------------------------------
(define-language LNI
  (extends LF)
  (Expr (e body)
        (- (if e0 e1))))

(define-parser parse-LNI LNI)

;; Define un preproceso para eliminar la expresion
;; if sin el caso para el else
;; Regresa un nuevo lenguaje LF en el que no tenemos
;; if sin else
(define-pass remove-one-armed-if : LF (ir) -> LNI ()
  (Expr : Expr (ir) -> Expr ()
    [(if ,[e0] ,[e1]) `(if ,e0 ,e1 (void))]))

;Ejercicio 6)         -------------------------------------------------------

;; Definimos un nuevo lenguaje que extiende
;; a LNI para quitar los strings como terminales
;; tambien quitamos los string de los
;; de las expresiones porque ya no hay
;; string que sean termnales
(define-language LNT (extends LNI)
  (terminals
    (- (string (s))))
  (Expr (e body)
    (- s)))


; Define un preproceso del compilador para eliminar las cadenas
;como elementos terminales del lenguaje, y en su lugar trata a las
;cadenas como sinónimos de listas de caracteres.

(define-pass remove-string : LNI (ir) -> LNT ()
  (definitions
    ; se definen dos funciones auxiliares para obtener los caracteres
    ; de la cadena
    (with-output-language (LNT Expr)
      ; obtiene los primeros n-1 caracteres de la cadena
      (define (primeros-chars s)
        (let ([longitud (string-length s)])
          (if (= longitud 1)
            '()
            (take (string->list s) (- longitud 1)))))
      ; obtiene el ultimo caracter de la cadena
      (define (ultimo-char s)
        (last (string->list s)))))
  (Expr : Expr (ir) -> Expr ()
    ;  match para la cadena vacia
    [,s (guard (string=? s "")) `()]
    ; match para una cadena no vacia
    [,s `(list ,(primeros-chars s) ... ,(ultimo-char s))]))




;---------------------------------------------------------------------------
;Ejemplos Ejercicio 2)

; Concrete expression;
; (33 + 2)
(expr->string (par-exp (bin-exp + (num-exp 33) (num-exp 2))))
; Answer: "(+ 33 2)"

; Concrete expression
; 3 - (3 / 6)
(expr->string (bin-exp - (num-exp 3) 
    (par-exp (bin-exp / (num-exp 3) (num-exp 6)))))
; Answer "(- 3 (/ 3 6))"

; Concrete expression:
; if(#t and #f)then{2}else{3}
(expr->string (if-exp (bin-exp 'and (bool-exp #t) (bool-exp #f))
    (num-exp 2) (num-exp 3)))
; Answer: "(if (and #t #f) 2 3)"

; Concrete expression:
; if(#t and #f)then{2}
(expr->string (if1-exp (bin-exp 'and (bool-exp #t) (bool-exp #f))
    (num-exp 2)))
; Answer: "(if (and #t #f) 2 3)"

; Concrete expression:
; fun ([x:Int]:Int) => x
(expr->string (fun-exp (typeof-exp (bra-exp (typeof-exp (var-exp 'x)
    (int-exp))) (int-exp)) (var-exp 'x)))
; Answer: "(fun ((x Int)) Int x)"

; Concrete expression:
; fun ([x:Int][y:Int]:Int) => x*y
(expr->string (fun-exp
 (typeof-exp (bra-exp (appt-exp 
    (typeof-exp (var-exp 'x) 
        (int-exp)) (typeof-exp (var-exp 'y) (int-exp)))) (int-exp))
 (bin-exp * (var-exp 'x) (var-exp 'y))))
; Answer: (fun ((x Int) (y Int)) Int (* x y))"

; Concrete expression:
; funF (sumita ([x:Int][y:Int]):Int) => x+y
(expr->string (fun-f-exp
 (typeof-exp (var-exp 'sumita) (bra-exp 
    (appt-exp (typeof-exp (var-exp 'x) (int-exp)) 
    (typeof-exp (var-exp 'y) (int-exp))))) (int-exp)
 (bin-exp + (var-exp 'x) (var-exp 'y))))
; Answer: "(funF sumita ((x Int) (y Int)) Int (+ x y))"



;Ejemplos Ejercicio 4)
(rename-var (parse-LF '(let ((x Int (let ((p Int 0)) r))) z (let ((x Int 5)) x))))
; respuesta esperada (language:LF '(let ((x2 Int (let ((x4 Int 0)) x1))) x0 (let ((x3 Int 5)) x3)))
(rename-var (parse-LF '(let ((x Bool #f) (y Int 3) (z Int 4)) (if x y z))))
; respuesta esperada (language:LF '(let ((x0 Bool #f) (x1 Int 3) (x2 Int 4)) (if x0 x1 x2)))
(rename-var (parse-LF '(begin (fun ((x Int)) Int  x y) (let ((x1 Int 2)) x1 x))))
; respuesta esperada (language:LF '(begin (fun ((x3 Int)) Int x3 x0) (let ((x2 Int 2)) x2 x1)))
(rename-var (parse-LF '(let ((x Int 0)) x (fun ((x Int)) Int x y))))
; respuesta esperada (language:LF '(let ((x1 Int 0)) x1 (fun ((x2 Int)) Int x2 x0)))