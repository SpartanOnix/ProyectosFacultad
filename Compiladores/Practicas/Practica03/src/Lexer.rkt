#lang nanopass

;; Bibliotecas chidas para lexear
(require parser-tools/lex
         parser-tools/lex-plt-v200
         (prefix-in : parser-tools/lex-sre);Operadores
         (prefix-in re- parser-tools/lex-sre)
         parser-tools/yacc)

(provide (all-defined-out));Exporta todos los identificadores que están definidos en el  nivel
;de fase relevante dentro del módulo de exportación, y que tienen el mismo contexto léxico

(define-tokens a (NUM VAR BOOL))
(define-empty-tokens b (LP RP  EOF LK RK LB RB + - * / IF THEN ELSE FUN FUNF LET IN END APP APPT AND OR BOOLE INT FUNC TYPEOF = =>))

; sre : S-regular expressions
(define calc-lexer
  (lexer
   
     ; Boleanos          
     ["#f" (token-BOOL #f)]
     ["#t" (token-BOOL #t)]

     ; Palabras reservadas
     ["if" (token-IF)]
     ["then" (token-THEN)]
     ["else" (token-ELSE)]
     ["fun" (token-FUN)]
     ["funF" (token-FUNF)]
     ["let" (token-LET)]
     ["in" (token-IN)]
     ["end" (token-END)]
     ["app" (token-APP)]
     ["and" (token-AND)]
     ["or" (token-OR)]
     ["Bool" (token-BOOLE)]
     ["Int" (token-INT)]
     ["Func" (token-FUNC)]

     ; Variables
     [(:+ (:or (char-range #\a #\z) (char-range #\A #\Z))) ; (a-z | A-Z)^+
      ; =>
      (token-VAR (string->symbol lexeme))]

     ; Numeros
     [(::  (:or #\- (epsilon)) (:: (:* (char-range #\0 #\9)) (:: (:or (:: #\. (char-range #\0 #\9)) (:: (char-range #\0 #\9)) #\.) (:* (char-range #\0 #\9)))))
     ; =>
     (token-NUM (string->number lexeme))]

     ; Primitivos
     [#\+ (token-+)]
     [#\- (token--)]
     [#\* (token-*)]
     [#\/ (token-/)]
     [#\( (token-LP)]
     [#\) (token-RP)]
     [#\{ (token-LK)]
     [#\} (token-RK)]
     [#\[ (token-LB)]
     [#\] (token-RB)]

     ; Simbolos en las funciones
     [(:: #\] #\[) (token-APPT)]
     [#\: (token-TYPEOF)]
     ["=>"  (token-=>)]
     [#\=  (token-=)]

     ; Simbolos auxiliares
     [whitespace (calc-lexer input-port)]
     [(eof) (token-EOF)]))

(define (minHS-lexer i)
   (calc-lexer i))

;; (define-struct arith-exp (op e1 e2) #:transparent)
;; (define-struct num-exp (n) #:transparent)
;; (define-struct var-exp (i) #:transparent)