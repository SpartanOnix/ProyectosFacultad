#lang nanopass

#|
Compiladores 2022-1
Profesora: Dra. Lourdes del Carmen Gonzalez Huesca
Ayudante: Naomi Itzel Reyes Granados
Laboratorio: Nora Hilda Hernández Luna

Lexer y parser
|#

(require "Lexer.rkt"
   (prefix-in : parser-tools/lex-sre)
   (prefix-in re- parser-tools/lex-sre)
   parser-tools/yacc
   parser-tools/lex)

;; Una abstraccion de la gramatica minHS.

(provide (all-defined-out))

; Estructura para los tipos
(define-struct var-exp (i) #:transparent)
(define-struct num-exp (n) #:transparent)
(define-struct bool-exp (b) #:transparent)
(define-struct int-exp () #:transparent) ; For the Int type.
(define-struct boole-exp () #:transparent) ; For the Bool type.
; Note: There is a difference: bool is for values and boole is for type

; Estructura para los primitivos
(define-struct prim-exp (op e1 e2) #:transparent) ; For the arithmetic operations.

; Estructura para los: "(), {}, []"
(define-struct par-exp (exp) #:transparent) 
(define-struct key-exp (exp) #:transparent) 
(define-struct brack-exp (exp) #:transparent)

; Estructura para las funciones
(define-struct fun-exp (f g) #:transparent)
(define-struct func-exp (f g) #:transparent)
(define-struct fun-f-exp (f g h) #:transparent)
(define-struct app-exp (f g) #:transparent)
(define-struct app-t-exp (f g) #:transparent)

; Estrtuctura para los operadores
(define-struct if-then-exp (g e1 e2) #:transparent) ; For the if conditionals.
(define-struct let-exp (v e) #:transparent)
(define-struct assign-exp (v e) #:transparent)
(define-struct typeof-exp (v e) #:transparent) ; For the type of operator ":".
(define-struct typeof-f-exp (f t e) #:transparent) ; For the name and the parameters of the function (f), the returning type (t) and the body (e).

(define-struct begin-exp (exp) #:transparent) 

(define (and-exp . e)
   (andmap (lambda (x) (equal? #t x)) e))

(define (or-exp . e)
   (andmap (lambda (x) (equal? #t x)) e))

(define minHS-parser
   (parser
      (start exp) 
      (end EOF) 
      (error void) 
      (tokens a b) 
      (precs (left - +) (left * /))
   (grammar 
      (exp
         ; Parseadores de tipos
         ((NUM) (num-exp $1))
         ((BOOL) (bool-exp $1))
         ((VAR) (var-exp $1))
         
         ; Parseadores de primitivos
         ((exp + exp) (make-prim-exp + $1 $3)) 
         ((exp - exp) (make-prim-exp - $1 $3))
         ((exp * exp) (make-prim-exp * $1 $3))
         ((exp / exp) (make-prim-exp / $1 $3))

         ; Parseador de "(), {}"
         ((LP exp RP) (make-par-exp $2))
         ((LK exp RK) (make-key-exp $2))

         ; Parseador para las funciones
         ((FUN LP list RP => exp) (make-fun-exp $3 $6))
         ((FUNC exp exp) (make-func-exp $2 $3))
         ((FUNF LP exp LP list RP TYPEOF type RP => exp) (make-fun-f-exp (make-typeof-exp $3 $5) $8 $11))
         ((exp APP exp) (make-app-exp $1 $3))
         ((LET LP list RP IN exp END) (make-let-exp $3 $6))

         ; Parseador para los operadores
         ((IF LP exp RP THEN LK exp RK ELSE LK exp RK) (if-then-exp $3 $7 $11))
         ((exp TYPEOF type) (make-typeof-exp $1 $3))

         ; Parseador para OR y AND
         ((exp AND exp) (make-prim-exp and-exp $1 $3))
         ((exp OR exp) (make-prim-exp and-exp $1 $3))
         )

      ; Parseador para varios tipos
      (type
         ((INT) (int-exp))
         ((BOOLE) (boole-exp)) 
         ((FUNC type type) (make-func-exp $2 $3))
         ((LP type RP) (make-par-exp $2))
      )

      ; Parseador para varios operadores
      (list
         ((exp TYPEOF type = exp) (make-assign-exp (make-typeof-exp $1 $3) $5))
         ((exp TYPEOF type) (make-typeof-exp $1 $3))
         ((list TYPEOF type) (make-typeof-exp $1 $3))
         ((list APPT list) (make-app-t-exp $1 $3))
         ((LB list RB) (make-brack-exp $2))
      )
  ))
)

; A function that stores our lexer into a lambda function without arguments.
(define (lex-this lexer input) (lambda () (lexer input)))