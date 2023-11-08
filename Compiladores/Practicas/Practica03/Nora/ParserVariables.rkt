#lang nanopass


(require parser-tools/lex
         (prefix-in : parser-tools/lex-sre)
         (prefix-in re- parser-tools/lex-sre)
         parser-tools/yacc)

(provide (all-defined-out))

(define-tokens a1 (LETRAS NUMEROS))
(define-empty-tokens b1 (EOF))

(define-lex-trans (epsilon stx)
  (syntax-case stx ()
    [(_) #'""]))

(define minHS-lexer
  (lexer

   [(:: (:or #\- (epsilon)) (:: (:+ (char-range #\0 #\9)) (:* (char-range #\0 #\9))))
    ; =>
    (token-NUMEROS (string->number lexeme))]

  [(:+ (:or (char-range #\a #\z) (char-range #\A #\Z))) ; ([a..z]|[A..Z])^+
    ; =>
    (token-LETRAS (string->symbol lexeme))]

   [whitespace ;Caso expecial
    ; =>
    (minHS-lexer input-port)] ;borramos todos los posibles espacios en blanco, tabuladores, etc

   [(eof) ;Token que indica que se termino de lexear la cadena
    (token-EOF)]
   ))


(display "\n Ejemplo 1: holaXDD1 \n")
(let ((input (open-input-string "1")))
  (minHS-lexer input))