#lang nanopass

(require "Compiler.rkt" ;; Here goes your lexer file. In my case I called "Compiler.rkt".
         (prefix-in : parser-tools/lex-sre)
         (prefix-in re- parser-tools/lex-sre)
          parser-tools/yacc
          parser-tools/lex)

(provide (all-defined-out))

;; An abstraction of the grammar minHS.
(define-struct var-exp (i) #:transparent)       ; For the variables.
(define-struct num-exp (n) #:transparent)       ; For the numbers.
(define-struct bool-exp (b) #:transparent)      ; For the booleans.
;; Empty Tokens
(define-struct typeof-exp (v e) #:transparent)  ; For the type of operator ":".
(define-struct int-exp () #:transparent)        ; For the Int type.
(define-struct boole-exp () #:transparent)      ; For the Bool type.
#|
Equipo:
Hernández Leyva Mirén Jessamyn (315309452)
Muñoz Barón Luis Miguel (315116663)
|#

(define-struct bin-exp (op e1 e2) #:transparent); For the arithmetic operations.
(define-struct if1-exp (e1 e2) #:transparent)   ; For the one armed if conditions.
(define-struct if-exp (e1 e2 e3) #:transparent) ; For the if conditions.
(define-struct let-exp (e1 e2) #:transparent)   ; For let expresions.
(define-struct appt-exp (e1 e2) #:transparent)  ; For AppT operator.
(define-struct app-exp (e1 e2) #:transparent)   ; For the app operator.
(define-struct fun-exp (t e) #:transparent)     ; For the functions.
(define-struct fun-f-exp (f t e) #:transparent) ; For the F functions.
;(define-struct fun-c-exp (t2 t3) #:transparent) ; For the C functions.
(define-struct begin-exp (e1) #:transparent)    ; For the Begin{e} operator.
;(define-struct arrow-exp (e1 e2) #:transparent) ;For the => operator.
(define-struct assign-exp (e1 e2) #:transparent); For the = operator.  

(define-struct par-exp (e) #:transparent)       ; For the parenthesis.
(define-struct bra-exp (e) #:transparent)       ; For the keys.
(define-struct key-exp (e) #:transparent)       ; For the brackets


(define minHS-parser
  (parser
   (start exp) ; start clause. The exp is the initial symbol where the parser begins the analysis. 
   (end EOF) ; end clause. The parser ends when it reads the given symbol. In our case, EOF.
   (error void) ; error clause. Here can be some errors presented in the anlysis.
   (tokens a b) ; tokens clause. Here goes our tokens. In our case, we defined the tokens in the lexer script.
   (precs (nonassoc LP RP LB RB LK RK IF THEN ELSE FUN FUNF LET IN END Int Bool VAR INT BOOL ARROW EOF)
          (left APPT)
          (left =)
          ;(right FUNC)
          (nonassoc TYPEOF)
          (left APP)
          (left and or)
          (left - +)
          (left * /))
   (grammar ; grammar clause. Here goes the grammar of minHS.
    (exp                                                                                                   ; Árbol de Sintaxis abstracta:
         ((INT) (num-exp $1)) ;; ((Token) (constructor $1 $2 ... $n)) [1,2,3,...,n]                        ; (Int  e)
         ((BOOL) (bool-exp $1))                                                                            ; (Bool e)
         ((VAR) (var-exp $1))                                                                              ; (Var  e)
         ;Tokens Vacios                                                                                   
         ((Int)  (make-int-exp))                                                                           ; (Int)
         ((Bool) (make-boole-exp))                                                                         ; (Bool)
         ((Bool) (make-boole-exp))                                                                         ; (Bool)
         
         ((LP exp RP) (make-par-exp $2))                                                                   ; (par e)
         ((LB exp RB) (make-bra-exp $2))                                                                   ; (key e)
         ((BEGIN LK exp RK) (make-begin-exp $3))                                                           ; (begin e)
         ((LK exp RK) (make-key-exp $2))                                                                   ; (brac e)      
         ((exp + exp)   (make-bin-exp + $1 $3)) ; ((e1 e2 e3 .... en) (constructor $1 $2 $3 ... $n))       ; (+ e1 e2)
         ((exp - exp)   (make-bin-exp - $1 $3))                                                            ; (- e1 e2)
         ((exp * exp)   (make-bin-exp * $1 $3))                                                            ; (* e1 e2)
         ((exp / exp)   (make-bin-exp / $1 $3))                                                            ; (/ e1 e2)
         ((exp and exp) (make-bin-exp 'and' $1 $3))                                                        ; (and e1 e2)
         ((exp or exp)  (make-bin-exp 'or'  $1 $3))                                                        ; (or e1 e2) 
         ((exp APP exp) (make-app-exp $1 $3))                                                              ; (app e1 e2)
         ((exp APPT exp) (make-appt-exp $1 $3))                                                            ; (appt e1 e2)
         ((exp TYPEOF exp) (make-typeof-exp $1 $3))                                                        ; (typeof var Type)
         ((LET LP exp RP IN exp END)  (make-let-exp  $3 $6))                                               ; (let e1 e2)
         ((IF LP exp RP THEN LK exp RK) (if1-exp $3 $7))                                                   ; (if e1 e2)
         ((IF LP exp RP THEN LK exp RK ELSE LK exp RK) (if-exp $3 $7 $11))                                 ; (if e1 e2 e3)
         ((FUN LP exp RP ARROW exp) (make-fun-exp $3 $6))                                                  ; (fun e1 e2)
         ((FUNF LP exp LP exp RP TYPEOF exp RP ARROW exp) (make-fun-f-exp (make-typeof-exp $3 $5) $8 $11)) ; (funf e0 e1 e2)
         ;((FUNC exp exp) (make-fun-c-exp $2 $3))                                                           ; (func e1 e2)
         ((exp = exp) (make-assign-exp $1 $3))                                                             ; (assign e1 e2)
    ))))

; A function that stores our lexer into a lambda function without arguments.
(define (lex-this lexer input) (lambda () (lexer input)))

; A lot of examples.
(display "Example 1: 3 - (3 / 6)\n")
(let ((input (open-input-string "3 - (3 / 6)")))
  (minHS-parser (lex-this minHS-lexer input)))
#|
Desired response:
(prim-exp #<procedure:-> (num-exp 3) (par-exp (prim-exp #<procedure:/> (num-exp 3) (num-exp 6))))
|#

(display "\nExample 2: if(#t)then{2}else{3}\n")
(let ((input (open-input-string "if(#t)then{2}else{3}")))
  (minHS-parser (lex-this minHS-lexer input)))
#|
Desired response:
(if-exp (bool-exp #t) (num-exp 2) (num-exp 3))
|#

(display "\nExample 3: fun ([x:Int]:Int) => x\n")
(let ((input (open-input-string "fun ([x:Int]:Int) => x")))
  (minHS-parser (lex-this minHS-lexer input)))
#|
Desired response:
(fun-exp (typeof-exp (brack-exp (typeof-exp (var-exp 'x) (int-exp))) (int-exp)) (var-exp 'x))
|#

(display "\nExample 4: fun ([f:Func Int Int]:Int) => f app 1 (Ejemplo muerto)\n")
;(let ((input (open-input-string "fun ([f:Func Int Int]:Int) => f app 1")))
  ;(minHS-parser (lex-this minHS-lexer input)))
#|
Desired response:
(fun-exp (typeof-exp (brack-exp (typeof-exp (var-exp 'f) (func-exp (int-exp) (int-exp)))) (int-exp)) (app-exp (var-exp 'f) (num-exp 1)))
|#

(display "\nExample 5: fun ([f:Func (Func Int Bool) Int]:Bool) => #t ejemplo muerto \n")
;(let ((input (open-input-string "fun ([f:Func (Func Int Bool) Int]:Bool) => #t")))
  ;(minHS-parser (lex-this minHS-lexer input)))
#|
Desired response:
(fun-exp (typeof-exp (brack-exp (typeof-exp (var-exp 'f) (func-exp (par-exp (func-exp (int-exp) (boole-exp))) (int-exp)))) (boole-exp)) (bool-exp #t))
|#

(display "\nExample 6: funF (sumita ([x:Int][y:Int]):Int) => x+y\n")
(let ((input (open-input-string "funF (sumita ([x:Int][y:Int]):Int) => x+y")))
  (minHS-parser (lex-this minHS-lexer input)))
#|
Desired response:
(fun-f-exp
 (typeof-exp (var-exp 'sumita) (brack-exp (app-t-exp (typeof-exp (var-exp 'x) (int-exp)) (typeof-exp (var-exp 'y) (int-exp)))))
 (int-exp)
 (prim-exp #<procedure:+> (var-exp 'x) (var-exp 'y)))
|#

(display "\nExample 7: let ([x:Int = 1][y:Int = 2]) in x+y end\n")
(let ((input (open-input-string "let ([x:Int = 1][y:Int = 2]) in x+y end")))
  (minHS-parser (lex-this minHS-lexer input)))
#|
Desired response:
(let-exp
 (brack-exp (app-t-exp (assign-exp (typeof-exp (var-exp 'x) (int-exp)) (num-exp 1)) (assign-exp (typeof-exp (var-exp 'y) (int-exp)) (num-exp 2))))
 (prim-exp #<procedure:+> (var-exp 'x) (var-exp 'y)))
|#

(display "\nExample 8: ((funF (sumita ([x:Int][y:Int]):Int) => x+y) app 2) app 4\n")
(let ((input (open-input-string "((funF (sumita ([x:Int][y:Int]):Int) => x+y) app 2) app 4")))
  (minHS-parser (lex-this minHS-lexer input)))
#|
Desired response:
(app-exp
 (par-exp
  (app-exp
   (par-exp
    (fun-f-exp
     (typeof-exp (var-exp 'sumita) (brack-exp (app-t-exp (typeof-exp (var-exp 'x) (int-exp)) (typeof-exp (var-exp 'y) (int-exp)))))
     (int-exp)
     (prim-exp #<procedure:+> (var-exp 'x) (var-exp 'y))))
   (num-exp 2)))
 (num-exp 4))
|#