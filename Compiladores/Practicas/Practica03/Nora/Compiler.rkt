#lang nanopass

(require parser-tools/lex
         (prefix-in : parser-tools/lex-sre)
         (prefix-in re- parser-tools/lex-sre)
         parser-tools/yacc)

(provide (all-defined-out))

(define-tokens a (VAR BOOL INT)) ;Tokens que son contenedores --- data Tokens = NUM Int | VAR String | BOOL Bool
(define-empty-tokens b (+ - * = / and or
                        APP ASSIGN BEGIN ARROW
                        LET IN END
                        FUN FUNF ;FUNC
                        IF THEN ELSE 
                        LP RP LK RK LB RB
                        TYPEOF Bool Int
                        APPT
                        EOF)) ;Tokens que no almacenan datos

; "fun (x:T) : T => x"
; [FUN,LP,VAR "x",TYPEOF,TYPE "T",RP,TYPEOF,TYPE "T",ACA,VAR "x",ATA,EOF]
; TYPE (TYPE (TYPE ...))
; T ::= INT | BOOL | T -> T

; Esta definicion pertenece a parsers-tools/lex-ptv-v200
; pero al importar esta biblioteca, causa problemas de sintaxis con los
; operadores aritmeticos
(define-lex-trans (epsilon stx)
  (syntax-case stx ()
    [(_) #'""]))

; Nuestro hermosisimo lexer
(define minHS-lexer
  (lexer
   ;[RegularExpression
   ;=>
   ;Token]

   ;---------------------------------Empty Tokens----------------------

   [#\+
    ; =>
    (token-+)]

   [#\-
    ; =>
    (token--)]

   [#\/
    ; =>
    (token--)]

   [#\*
    ; =>
    (token-*)]

   [#\/
    ; =>
    (token-/)]

   [#\=
    ; =>
    (token-=)]
   
   ;and
   [(:: #\a (:: #\n #\d))
    ; =>
    (token-and)]

   ;or
   [(:: #\o #\r)
    ; =>
    (token-or)]
   
   ; APP
   [(:: #\a #\p #\p)
    ; =>
    (token-APP)]

   [(::  #\a #\s #\s #\i #\g #\n)
    ; =>
    (token-ASSIGN)]

   ;begin
   [(:: #\b #\e #\g #\i #\n)
    ; =>
    (token-BEGIN)]

   ;=>
   [(:: #\= #\>)
    ; =>
    (token-ARROW)]
   
   ; let
   [(:: #\l #\e #\t) ; Expresion regular let
    ; =>
    (token-LET)] ;token resultante

   ;in
   [(:: #\i #\n)
    ; =>
    (token-IN)]

   ;end
   [(:: #\e (:: #\n #\d ))
    ; =>
    (token-END)]

   ;funF
   [(:: #\f (:: #\u (:: #\n #\F)))
    ; =>
    (token-FUNF)]

   ;Func
   ;[(:: #\F (:: #\u (:: #\n #\c)))
    ; =>
    ;(token-FUNC)]

   ;fun
   [(:: #\f (:: #\u #\n))
    ; =>
    (token-FUN)]

   [(:: #\i #\f) ; Expresion regular if
     ; =>
     (token-IF)]

   [(:: #\t #\h #\e #\n) ; Expresion regular then
    ; =>
    (token-THEN)]

   [(:: #\e #\l #\s #\e) ; Expresion regular else
   ; =>
   (token-ELSE)]

   [#\(
    ;=>
    (token-LP)]

   [#\)
    ;=>
    (token-RP)]
    
   [#\{
    ;=>
    (token-LK)]

   [#\}
    ;=>
    (token-RK)]

   [#\[
    ;=>
    (token-LB)]

   [#\]
    ;=>
    (token-RB)]

   ;TYPEOF
   [#\:
    ; =>
    (token-TYPEOF)]

   ;Bool
   [(:: #\B (:: #\o (:: #\o #\l )))
    ; =>
    (token-Bool)]

   ;Int
   [(:: #\I (:: #\n #\t ))
    ; =>
    (token-Int)]

   
   [(:: #\] #\[)
    ; =>
    (token-APPT)]

   ;-----------------Container Tokens--------------------------

   ;BOOL
   [(:: #\# (:or #\t #\f))
    ; =>
    (token-BOOL (string->symbol lexeme))]

   [(:+ (:or (char-range #\a #\z) (char-range #\A #\Z))) ; ([a..z]|[A..Z])^+
    ; =>
    (token-VAR (string->symbol lexeme))]
    
   ;INT (Minimo un digito seguido de cualquier numero)
   [(:: (:or #\- (epsilon)) (:: (:+ (char-range #\0 #\9)) (:* (char-range #\0 #\9))))
    ; =>
    (token-INT (string->number lexeme))]

   [whitespace ;Caso expecial
    ; =>
    (minHS-lexer input-port)] ;borramos todos los posibles espacios en blanco, tabuladores, etc

   [(eof) ;Token que indica que se termino de lexear la cadena
    (token-EOF)]))

#|
; Empecemos a definir la gramatica de minHS
; data minHS = NUM Int | ... | ADD minHS minHS | ...
(define-struct if-exp (e1 e2 e3) #:transparent) ; if(e1,e2,e3) --- if e1 then e2 else e3
(define-struct let-exp (var e1 e2) #:transparent) ; let(e1,x.e2) --- let x = e1 in e2 end
(define-struct bin-exp (op e1 e2) #:transparent) ; opb(e,e)
(define-struct un-exp (op e1) #:transparent) ; opu(e)
(define-struct par-exp (exp) #:transparent) ; (e)
(define-struct num-exp (n) #:transparent)
(define-struct int-exp () #:transparent)
(define-struct var-exp (i) #:transparent)
(define-struct bool-exp (b) #:transparent)
(define-struct typeof-exp (v e) #:transparent)
(define-struct and-exp (e1 e2) #:transparent)
(define-struct or-exp (e1 e2) #:transparent)
(define-struct app-exp (e1 e2) #:transparent)
(define-struct fun-exp (v e) #:transparent)
(define-struct fun-f-exp (v e) #:transparent)
(define-struct fun-c-exp (e1 e2 e3) #:transparent)
; e :: = num | x | bool | opu(e) | opb(e,e) | fun [(x:T)]* e | ...
|#


; Ejemplos de uso
(display "Ejemplo 1: -6 + 25 \n")
(let ((input (open-input-string "-6 + 25")))
  (minHS-lexer input))

(display "\n Ejemplo 2: (-6 + 25) * 7 \n")
(let ((input (open-input-string "(-6 + 25) * 7")))
  (minHS-lexer input))

(display "\n Ejemplo 3: [(-6 + 25) / 2] * 7 \n")
(let ((input (open-input-string "[(-6 + 25) / 2] * 7")))
  (minHS-lexer input))

(display "\n Ejemplo 4: {[(-6 + 25) / 2] * 7} / 28 \n")
(let ((input (open-input-string "{[(-6 + 25) / 2] * 7} / 28")))
  (minHS-lexer input))

(display "\n Ejemplo 5: if x = 5 then #t else #f \n")
(let ((input (open-input-string "if x = 5 then #t else #f")))
  (minHS-lexer input))

(display "\n Ejemplo 6: myvar = #t \n")
(let ((input (open-input-string "myvar = #t")))
  (minHS-lexer input))

(display "\n Ejemplo 7: fun ([v1:Int][v2:Bool]) => let [x:Int = 1] in x + 2 end \n")
(let ((input (open-input-string "fun ([v1:Int][v2:Bool]) => let [x:Int = 1] in x + 2 end")))
  (minHS-lexer input))

(display "\n Ejemplo 8: [v1:Int][v2:Bool]) => let [x:Int = 1] in x + 2 end \n")
(let ((input (open-input-string "[v1:Int][v2:Bool]) => let [x:Int = 1] in x + 2 end")))
  (minHS-lexer input))

(display "\n Ejemplo 9: :Int][v2:Bool]) => let [x:Int = 1] in x + 2 end \n")
(let ((input (open-input-string ":Int][v2:Bool]) => let [x:Int = 1] in x + 2 end")))
  (minHS-lexer input))

(display "\n Ejemplo 10: Int][v2:Bool]) => let [x:Int = 1] in x + 2 end \n")
(let ((input (open-input-string "Int][v2:Bool]) => let [x:Int = 1] in x + 2 end")))
  (minHS-lexer input))

(display "\n Ejemplo 11: ][v2:Bool]) => let [x:Int = 1] in x + 2 end \n")
(let ((input (open-input-string "][v2:Bool]) => let [x:Int = 1] in x + 2 end")))
  (minHS-lexer input))

(display "\n Ejemplo 12: => let [x:Int = 1] in x + 2 end \n")
(let ((input (open-input-string "=> let [x:Int = 1] in x + 2 end")))
  (minHS-lexer input))

(display "\n Ejemplo 13: let [x:Int = 1] in x + 2 end \n")
(let ((input (open-input-string "let [x:Int = 1] in x + 2 end")))
  (minHS-lexer input))

(display "\n Ejemplo 14: in x + 2 end \n")
(let ((input (open-input-string "in x + 2 end")))
  (minHS-lexer input))

(display "\n Ejemplo 15: x + 2 end \n")
(let ((input (open-input-string "x + 2 end")))
  (minHS-lexer input))

(display "\n Ejemplo 16: end \n")
(let ((input (open-input-string "end")))
  (minHS-lexer input))

(display "\n Ejemplo 17: ][ \n")
(let ((input (open-input-string "][")))
  (minHS-lexer input))

(display "\n Ejemplo 18: begin \n")
(let ((input (open-input-string "begin")))
  (minHS-lexer input))

(display "\n Ejemplo 19: => \n")
(let ((input (open-input-string "=>")))
  (minHS-lexer input))