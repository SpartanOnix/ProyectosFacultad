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

(require nanopass/base)

;; Definimos el lenguaje L8 como viene en el PDF
(define-language L8
   (terminals
      (variable (x))
      (primitive (pr))
      (constant (c))
      (type (t)))
   (Expr (e body)
      x
      (quot c)
      (begin e* ... e)
      (primapp pr e* ...)
      (if e0 e1 e2)
      (lambda ([x* t*] ...) body)
      (let ([x t e]) body)
      (letrec ([x t e]) body)
      (letfun ([x t e]) body)
      (list e* ...)
      (e0 e1 ...)))

;; Metodo auxiliar para el type
(define (T->T lista)
   (if (and (= (length lista) 3) (memq '-> lista))
      (and (type? (car lista)) (type? (caddr lista)))
       #f))

(define (ListofT lista)
   (if (and (= (length lista) 3) (memq 'of lista))
      (and (type? (car lista)) (type? (caddr lista)))
       #f))

;; Definimos los predicados del lenguaje
(define (variable? x)
  (symbol? x))

(define (type? t)
 (or (equal? t 'Int) (equal? t 'Bool) (eq? t 'String) (eq? t 'Lambda) (eq? t 'List) (and (list? t) (or (T->T t) (ListofT t)))))

(define (constant? x)
  (or (number? x) (boolean? x)))

(define (primitive? x)
  (or (procedure? x) (memq x '(+ * - / car or cdr and or length))))

;; Definimos el lenguaje L9
(define-language L9
   (extends L8)
   (Expr (e body)
      (- (lambda ([x* t*] ...) body)
         (e0 e1 ...))
      (+ (lambda ([x t]) body)
         (e0 e1))))

;; Definimos el lenguaje L10
(define-language L10
   (extends L9)
   (Expr (e body)
      (- (quot c))
      (+ (const t c))))

;; Definimos los parsers de los lenguajes
(define-parser parse-L8 L8)
(define-parser parse-L9 L9)
(define-parser parse-L10 L10)

;; Primera parte:

;; Ejercicio 1
(define-pass curry : L8 (e) -> L9()
   (Expr : Expr (e) -> Expr ()
        [(lambda ([,x* ,t*] ...) ,[body])
          (let f ([jx* x*] [jt* t*])
             (if (equal? (length jx*) 1)
                `(lambda ([, (car jx*) , (car jt*)]) , body)
                `(lambda ([, (car jx*) , (car jt*)]) , (f (cdr jx*) (cdr jt*)))))]
        [(, [e0] , [e1] ...)
           (let f ([be0 e0] [be1 e1])
              (if (equal? (length be1) 0)
                 be0
                 (f `(, be0 , (car be1)) (cdr be1))))]))

;; Ejercicio 2
(define-pass type-const : L9 (e) -> L10 ()
   (Expr : Expr (e) -> Expr ()
      [(quot ,c)
        (cond
           [(boolean? c) `(const Bool ,c)]
           [(number? c) `(const Int ,c)]
           [(char? c) `(const Char ,c)])]))

;; Segunda parte:

;; Auxiliares
(define (unify t1 t2)
   (if (and (type? t1) (type? t2))
      (cond
         [(equal? t1 t2) #t]
         [(and (equal? 'List t1) (list? t2)) (equal? (car t2) 'List)]
         [(and (equal? 'List t2) (list? t1)) (equal? (car t1) 'List)]
         [(and (list? t1) (list? t2)) (and (unify (car t1) (car t2)) (unify (caddr t1) (caddr t2)))]
         [else #f])
      (error "Se esperaban 2 tipos ")))

(define (lookup x body)
  (if (equal? (length body) 0)
      "Error variable libre"
      (if (equal? x (car (car body)))
          (cdr (car body))
          (lookup x (cdr body)))))

(define (part lst)
   (if (equal? (car lst) 'List)
      (part (cdr lst))
      (car lst)))

;; Ejercicio 1
(define (J e entorno)
   (nanopass-case (L10 Expr) e
      [,x (lookup x entorno)]
      [(const ,t ,c) `,t]
      [(begin ,e* ... ,e) (let* ([types (map (lambda(x)(J x entorno)) (unparse-L10 e*))]) (J e entorno))]
      [(primapp ,pr ,e0)
         (let* ([t (J e0 entorno)])
            (if (equal? 'car (unparse-L10 pr))
               (if (list? t)
                  (if (equal? (car t) 'List)
                     (last t)
                     (error "El tipo no corresponde con el valor"))
                  ((error "El tipo no corresponde con el valor")))
               (if (equal? 'cdr (unparse-L10 pr))
                  (if (list? t)
                     (if (equal? (car t) 'List)
                        t
                        (error "El tipo no corresponde con el valor"))
                     ((error "El tipo no corresponde con el valor")))
                  (if (equal? 'length (unparse-L10 pr))
                     (if (list? t)
                        (if (equal? (car t) 'List)
                           'Int
                           (error "El tipo no corresponde con el valor"))
                        ((error "El tipo no corresponde con el valor")))
                     (error "El tipo no corresponde con el valor")))))]
      [(primapp ,pr ,e0 ,e1)
         (let* ([t (J e0 entorno)] [t1 (J e1 entorno)])
            (if (or (equal? '+ (unparse-L10 pr)) (equal? '* (unparse-L10 pr)) (equal? '/ (unparse-L10 pr)) (equal? '- (unparse-L10 pr)))
               (if (unify 'Int t)
                  (if (unify 'Int t1)
                     'Int
                     (error "El tipo no corresponde con el valor"))
                  (error "El tipo no corresponde con el valor"))                
               (error "El tipo no corresponde con el valor")))]
      [(list ,e* ...)
         (if (empty? e*)
            'List
            (let* ([tip (J (car e*) entorno)] [new (andmap (lambda (x) (equal? tip (J x entorno))) (cdr e*))])
              (if new
                 (list 'List 'of tip)
                 (error "El tipo no corresponde con el valor"))))]
      [(letrec ([,x ,t ,e]) ,body)
         (let* ([newentorno (append (cons (cons x t) '())  entorno)] [t2 (J e newentorno)] [S (J body newentorno)])
            (if (unify t t2)
               S
               (error "El tipo no corresponde con el valor")))]
      [(letfun ([,x ,t ,e]) ,body)
         (let* ([newentorno (append (cons (cons x t) '())  entorno)] [t2 (J e entorno)] [S (J body newentorno)])
           (if (and (unify t t2) (equal? (cadr t) '->))
              S
              (error "El tipo no corresponde con el valor")))]
      [(if ,e0 ,e1 ,e2)
         (let* ([t0 (J e0 entorno)] [t1 (J e1 entorno)] [t2 (J e2 entorno)])
            (if (and (unify t0 'Bool) (unify t1 t2))
               t1
               (error "El tipo no corresponde con el valor")))]
      [(lambda ([,x ,t]) ,body)
         (let* ([newentorno (append (cons (cons x t) '())  entorno)] [S (J body newentorno)]) `(,t -> ,S))]
      [(let ([,x ,t ,e]) ,body)
         (let* ([newentorno (append (cons (cons x t) '())  entorno)] [t2 (J e body)] [S (J body newentorno)])
            (if (unify t t2)
               S
               (error "El tipo no corresponde con el valor")))]
      [(,e0 ,e1)
         (let* ([t0 (J e0 entorno)] [t1 (J e1 entorno)])
            (if (unify t0 `(,t1 -> ,(last t0)))
               (last t0)
               (error "El tipo no corresponde con el valor")))]))

;; Ejercicio 2
(define-pass type-infer : L10 (e) -> L10 ()
   (Expr : Expr (expr) -> Expr ()
      [(letrec ([,x ,t ,e]) ,body) `(letrec ([,x ,(J e empty) ,e]) ,body)]
      [(letfun ([,x ,t ,e]) ,body) `(letfun ([,x ,(J e empty) ,e]) ,body)]
      [(let ([,x ,t ,e]) ,body) `(let ([,x ,(J e empty) ,e]) ,body)]))

;; Ejemplos para corroborar los ejercicios

;; Parte 1

; Ejercicio 1
; Entrada: (curry (parse-L8 '(foo x y)))
; Salida: (language:L9 '((foo x) y))
; Entrada: (curry (parse-L8 '(lambda ([x Int] [y Int]) (+ x y))))
; Salida: (language:L9 '(lambda ((x Int)) (lambda ((y Int)) ((+ x) y))))

; Ejercicio 2
; Entrada: (type-const (parse-L9 '(quot 5)))
; Salida: (language:L10 '(const Int 5))

;; Parte 2

; Ejercicio 1
; Entrada: (J (parse-L10 '(lambda ([x Int]) x)) '())
; Salida: '(Int -> Int)

; Ejercicio 2
; Entrada: (type-infer (parse-L10 '(letrec ([foo Lambda (lambda ([x Int]) x)]) (foo (const Int 5)))))
; Salida: (language:L10 '(letrec ((foo (Int -> Int) (lambda ((x Int)) x))) (foo (const Int 5))))
; Entrada: (type-infer (parse-L10 '(let ([x List (list)]) x)))
; Salida: (language:L10 '(let ((x List (list))) x))
; Entrada: (type-infer (parse-L10 '(let ([x List (list (const Int 1) (const Int 2) (const Int 3) (const Int 4))]) x)))
; Salida: (language:L10 '(let ((x (List of Int) (list (const Int 1) (const Int 2) (const Int 3) (const Int 4)))) x))