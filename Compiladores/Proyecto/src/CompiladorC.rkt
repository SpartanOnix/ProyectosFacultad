#lang nanopass
#|
Compiladores 2022-1
Profesora: Dra. Lourdes del Carmen Gonzalez Huesca
Ayudante: Naomi Itzel Reyes Granados
Laboratorio: Nora Hilda Hernández Luna

Proyecto

Equipo:
•	Cruz Jimenez Alejandro - 316008488 
•	Sandoval Mendoza Antonio - 316075725 
•	Sinencio Granados Dante Jusepee - 316246019
|#

;; Importamos nuestro archivo con todos los metodos solicitados
(require "Compilador.rkt" racket/pretty)

;; Metodo para leer un archivo (para el punto 7)
(define (read-file path)
   (call-with-input-file path (lambda (x) (read x)) #:mode 'text))

;; Metodo para escribir en el archivo (para el punto 7)
(define (write-file codigo path)
   (with-output-to-file path (lambda () (printf "~a" codigo)) #:mode 'text #:exists 'replace))

;; Metodo donde se aplican todos los preprocesos (para el punto 7)
(define (compilar path)
   (let* ([source-code (read-file path)]
             [front-end (curry (verify-vars (verify-arity (un-anonymous (identify-assigments (curry-let
                (traductor-LNI2-L6(remove-string (remove-one-armed-if (rename-var (parse-LF source-code)))))))))))]
             [middle-end (uncurry (type-infer (type-const front-end)))])
  (define-values (back-end tabla) (assignment middle-end))
  (define back-end-final (list-to-array back-end))
  (write-file (unparse-L9 front-end) "ejemplos/ejemplo.fe")
  (write-file (unparse-L11 middle-end) "ejemplos/ejemplo.me")
  (write-file (c back-end-final tabla) "ejemplos/ejemplo.c")
  (display back-end-final)
  (display "\n")
  (display tabla)))