#lang nanopass

(require "Parser.rkt"
              "lexer.rkt"
              nanopass/base)

(define-language LF
   (terminals
   (variable (x)) ;;Done
   (primitive (pr))
   (constant (c))
   (string (s))
   (char (c1))
   (type (t)) ;; Done
   (lista (l))) ;; Done
   ;; e ::= x | pr | c | s | t | l | begin (e,...,e) | if(e,e) | if(e,e,e)
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
      (fun ((x* t*) ...) t body* ... body) ;; fun ((x Int) (y Float)) Float (asign z (* (+ x y) 3)) (asign z 10))
      (let ((x* t* e*) ...) body* ... body)
      (funF x ((x* t*) ...) t body* ... body)
      (e0 e1 ...) ;; Aplicacion (pr e0 e1) --- (+ 1 #true)
      (pr e* ... e)))