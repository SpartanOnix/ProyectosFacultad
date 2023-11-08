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

;; Importamos nanopass/base
(require nanopass/base
   (prefix-in : parser-tools/lex-sre)
   (prefix-in re- parser-tools/lex-sre)
   parser-tools/yacc
   parser-tools/lex)

;; Para la exportacion del archivo
(provide (all-defined-out))

;; Definimos el lenguaje base LF
(define-language LF
   (terminals
      (variable (x))
      (primitive (pr))
      (constant (c))
      (string (s))
      (char (c1))
      (type (t))
      (list (l))
      (void (v)))
   (Expr (e body)
      x
      pr
      c
      s
      c1
      t
      l
      v
      (begin e* ... e)
      (if e0 e1)
      (if e0 e1 e2)
      (fun ((x* t*) ...) t body* ... body)
      (let ((x* t* e*) ...) body* ... body)
      (funF x ((x* t*) ...) t body* ... body)
      (e0 e1 ...)
      (pr e* ... e)
      ;; Agregada la extension del while (para el punto 2)
      (while [e0] e1)
      ;; Agregada la modificacion del for (para el punto 3)
      (for [x l] e1)))

;; Definimos el lenguaje para eliminar el uso del if
(define-language LNI
   (extends LF)
   (Expr (e body) (- (if e0 e1))))

;; Definimos el lenguaje para eliminar las strings como elementos terminales
(define-language LNI2
  (extends LNI)
  (terminals (- (string (s))))
  (Expr (e body) (- s)))

;; Definimos el lenguaje L6 en el modificamos principalmente a las funciones
(define-language L6
   (extends LNI2)
   (Expr (e body)
      (- t
          (pr e* ... e)
          (fun ((x* t*) ...) t body* ... body)
          (let ((x* t* e*) ...) body* ... body)
          (funF x ((x* t*) ...) t body* ... body)
          (for [x l] e1))
      (+ (quot c)
           (primapp pr e* ...)
           (let ([x* t* e*] ...) body)
           (lambda ([x* t*] ...) body)
           (letrec ([x* t* e*] ...) body)
           (list e* ...)
           (for [x e0] e1))))

;; Definimos el lenguaje L7 en el que eliminaremos las listas de let y solo asignaremos un let
(define-language L7
   (extends L6)
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

;; Definimos el lenguaje L9 en el que modificamos lambda
(define-language L9
   (extends L8)
   (Expr (e body)
      (- (lambda ([x* t*] ...) body)
          (e0 e1 ...))
      (+ (lambda ([x t]) body)
          (e0 e1))))

;; Definimos el lenguaje L10 en el que quitmos quot y agregamos constantes
(define-language L10
   (extends L9)
   (Expr (e body)
      (- (quot c))
      (+ (const t c))))

;; Definimos el lenguaje L11 agrega los lambdas con mas de un parametro
(define-language L11
   (extends L10)
   (Expr (e body)
      (- (lambda ([x t]) body))
      (+ (lambda ([x* t*] ...) body))))

;; Definimos el lenguaje L12 podemos cambiar los constructores por let, letrec y letfun 
(define-language L12
   (extends L11)
   (Expr (e body)
      (- (let ([x t e]) body)
          (letrec ([x t e]) body)
          (letfun ([x t e]) body))
      (+ (let x body)
          (letrec x body)
          (letfun x body))))

;; Definimos el lenguaje L13 elimina listas y agrega en su lugar arreglos
(define-language L13
   (extends L12)
   (terminals (+ (len (le))))
   (Expr (e body)
      (- (list e* ...))
      (+ (array le t [e* ...]))))

;; Definimos el parser de este lenguaje
(define-parser parse-LF LF)
(define-parser parse-LNI LNI)
(define-parser parse-LNI2 LNI2)
(define-parser parse-L6 L6)
(define-parser parse-L7 L7)
(define-parser parse-L8 L8)
(define-parser parse-L9 L9)
(define-parser parse-L10 L10)
(define-parser parse-L11 L11)
(define-parser parse-L12 L12)
(define-parser parse-L13 L13)

;; Funcion para saber si es un primitivo
(define (primitive? x)
   (or
      (procedure? x)
      ;; Agregada la modificacion de los primitivos (para el punto 4)
      (memq x '(+ * - / car or cdr and or length < > equal? iszero? ++ -- void))))

;; Funcion para saber si es una constante
(define (constant? x)
   (or
      (number? x)
      (boolean? x)
      (char? x)))

;; Funcion para saber si es una variable
(define (variable? x)
   (and
      (symbol? x)
      (not (symbol=? 'void x))
      (not (primitive? x))
      (not (constant? x))))

;; Funcion para saber si una variable es vacia
(define (void? v)
   (and (symbol? v) (symbol=? 'void v)))

;; Funcion para saber si una variable indica tamaño
(define (len? le) (number? le))

;; Funcion para saber que tipo es
(define (type? x)
   (or
      (b-type? x)
      (c-type? x)))

;; Funcion para saber si es tipo B
(define (b-type? x) (memq x '(Bool Char Int List Lambda)))

;; Funcion para saber si es tipo C
(define (c-type? x)
   (if (list? x)
       (let* (
          [f (car x)]
          [s (car x)]
          [t (caddr x)])
        (or
           (and (equal? f 'List) (equal? s 'of) (type? t))
           (and (type? f) (equal? s '->) (type? t))))
       #f))

;; Funcion para saber si es un simbolo aritmetico
(define (arit? x) (memq x '(+ - * /)))

;; Funcion para saber si es una lista
(define (lst? x) (memq x '(length car cdr)))

;; Funcion para traducir el lenguaje LNI2 a L6
(define-pass traductor-LNI2-L6 : LNI2(ir) -> L6()
   (Expr : Expr (e) -> Expr ()
      [,c `(quot ,c)]
      [(fun ((,x* ,t*) ...) ,t ,[body*] ... ,[body]) `(lamda ([,x* ,t*] ...1) (begin ,body* ... ,body))]
      [(funF ,x ((,x* ,t*) ...) ,t ,[body*] ... ,[body]) `(letrec ([,x Lamba (lambda ([,x* ,t*] ...) (begin ,body* ... ,body))]) ,x)]
      [(let ((,x* ,t* ,[e*]) ...) ,[body*] ... ,[body]) `(let ([,x* ,t* ,e*] ...) (begin ,body* ... ,body))]
      [(,pr ,[e*] ... ,[e]) `(primaap ,pr ,e* ... ,e)]
      ;; Agregada la modificacion del for (para el punto 3)
      [(for [,x ,l] ,[e1]) (let ([nombre (newName 'arr)]) `(let ([,nombre List ,(traductor-LNI2-L6 l)]) (for [,x ,nombre] ,e1)))]))

;; Funcion auxiliar de newName que toma una lista [x1,...,xn-1,xn] y regresa la lista [x1,...,xn-1]
(define (init l)
   (take l (- (length l) 1)))

;; Funcion auxiliar de newVar que toma la lista de tokens [t1,...,tn] y si el ultimo token es un numero n, regresa [t1,...,n+1]
;  en otro caso, regresa [t1,...,tn,0]
(define (newName lToks)
   (let ([ultimo (last (last lToks))])
      (if (number? ultimo)
         (append (init lToks) (list (quasiquote (NUM1 (unquote (add1 ultimo))))))
         (append lToks (list '(NUM1 0))))))

;; Funcion auxiliar de newVar que recibe una funcion que da una lista de tokens y convierte una lista de tokens en un string
(define (constructVar lToks) 
   (string-join (foldr (lambda (tok l) (cons (format "~a" (last tok)) l)) '() lToks) ""))

;; Funcion que almacena nuestro lexer en una funcion lambda sin argumentos
(define (lex-this lexer input) (lambda () (lexer input)))

;; Tokens para el lexer
(define-tokens a1 (NUM1 VAR1))
(define-empty-tokens b1 (EOF))

;; Funcion auxiliar de newVar, un lexer para las variables
;  Toma una cadena alfanumerica y regresa una lista de tokens, donde cada token es un numero o una cadena
(define var-lexer
   (lexer
      [(:: (:+ (char-range #\a #\z) (:+ (char-range #\A #\Z)))) (cons  `(VAR1 ,(string->symbol lexeme)) (var-lexer input-port))]
      [(:: (:: (:+ (char-range #\0 #\9)) (:* (char-range #\0 #\9)))) (cons `(NUM1 ,(string->number lexeme)) (var-lexer input-port))]
      [(eof)
      `()]))

;; Funcion auxiliar de rename-var que toma un simbolo que representa el valor de una variable y 
;  regresa un nuevo nombre de una variable como string
(define (newVar x)
   (string->symbol (constructVar (newName (var-lexer (open-input-string (format "~a" x)))))))

;; Funcion auxiliar de rename-var que toma naive-rename sobre cada variable que esta en una exprecion generada por el lenguaje LF
(define-pass naive-rename-var : LF (ir) -> LF ()
   (Expr : Expr (ir) -> Expr ()
      [,x (let* ([newX (newVar x)]) newX)]
      [(fun ((,x* ,t*) ...) ,t ,[body*] ... ,[body]) (let* ([newXs (map newVar x*)])
         `(fun ((,newXs ,t*) ...) ,t ,body* ... ,body))]
      [(let ((,x* ,t* ,[e*]) ...) ,[body*] ... ,[body]) (let* ([newXs (map newVar x*)])
         `(let ((,newXs ,t* ,e*) ...) ,body* ... ,body))]
      [(funF ,x ((,x* ,t*) ...) ,t ,[body*] ... ,[body]) (let* ([newX (newVar x)] [newXs (map newVar x*)])
         `(funF ,newX ((,newXs ,t*) ...) ,t ,body* ... ,body))]))

;; Funcion que renombra cada variable que existe en una expresion generada por LF, por lo que las
;  ocurrencias de las variables anidadas de la misma variable son distintas
(define-pass rename-var : LF (ir) -> LF ()
   (Expr : Expr (ir) -> Expr ()
      [,x (let* ([newX (newVar x)]) newX)]
      [(fun ((,x* ,t*) ...) ,t ,[body*] ... ,[body]) (let* ([newXs (map newVar x*)])
         (naive-rename-var `(fun ((,newXs ,t*) ...) ,t ,body* ... ,body)))]
      [(let ((,x* ,t* ,[e*]) ...) ,[body*] ... ,[body]) (let* ([newXs (map newVar x*)])
         (naive-rename-var `(let ((,newXs ,t* ,e*) ...) ,body* ... ,body)))]
      [(funF ,x ((,x* ,t*) ...) ,t ,[body*] ... ,[body]) (let* ([newX (newVar x)] [newXs (map newVar x*)])
         (naive-rename-var `(funF ,newX ((,newXs ,t*) ...) ,t ,body* ... ,body)))]))

;; Funcion para eliminar el if de la expresion dada
(define-pass remove-one-armed-if : LF (ir) -> LNI()
   (Expr : Expr (ir) -> Expr()
      [(if ,[e0] ,[e1]) `(if ,e0 ,e1 (void))]))

;; Funcion para eliminar las strings como elementos terminales en una expresion dada
(define-pass remove-string : LNI(ir) -> LNI2 ()
   (Expr : Expr (ir) -> Expr()
      [,s '(lista, (string->list , s))]))

;; Tomara la expresion dada para transformar su let universal a un let por cada asignacion
(define-pass curry-let : L6 (e) -> L7 ()
   (Expr : Expr (e) -> Expr ()
      [(let ([,x* ,t* ,[e*]] ...) ,[body])
         (let f ([bindingx* x*] [bindingt* t*] [bindinge* e*])
            (if (equal? (length bindingx*) 1)
               `(let ([,(car bindingx*) ,(car bindingt*) ,(car bindinge*)]) ,body)
               `(let ([,(car bindingx*) ,(car bindingt*) ,(car bindinge*)]) ,(f(cdr bindingx*) (cdr bindingt*) (cdr bindinge*)))))]
      [(letrec ([,x* ,t* ,[e*]] ...) ,[body])
         (let f ([bindingx* x*] [bindingt* t*] [bindinge* e*])
            (if (equal? (length bindingx*) 1)
               `(let ([,(car bindingx*) ,(car bindingt*) ,(car bindinge*)]) , body)
               `(let ([,(car bindingx*) ,(car bindingt*) ,(car bindinge*)]) ,(f(cdr bindingx*) (cdr bindingt*) (cdr bindinge*)))))]))

;; Tomara la expresion dada para transformar sus let's a los que se le esten asignando lambdas a un solo letrec
(define-pass identify-assigments : L7 (e) -> L7 ()
   (Expr : Expr (e) -> Expr ()
      [(let ([,x,t,e]) ,[body])
         (if (equal? t 'Lambda)
            `(letrec ([,x,t,e]) ,body)
            `(let ([,x,t,e]) ,body))]))

;; Tomara la expresion dada para cachar las lambdas y asignarlas en un letfun
(define-pass un-anonymous : L7 (e) -> L8 ()
   (Expr : Expr (e) -> Expr ()
      [(lambda ([,x* ,t*] ...) ,[body]) `(letfun ([,'foo ,'Lambda (lambda ([,x* ,t*] ...) ,body)]) ,'foo)]))

; Función auxiliar de verify-arity si la aridad del operador es compatible con el número de argumentos
(define (prc-ar pr act)
   (match pr
      ["+" (< 1 act)]
      ["-" (< 1 act)]
      ["*" (< 1 act)]
      ["/" (< 1 act)]
      ["length" (eq? 1 act)]
      ["car" (eq? 1 act)]
      ["cdr" (eq? 1 act)]
     ;; Agregada la modificacion de los primitivos (para el punto 4)
      ["<" (eq? 2 act)]
      [">" (eq? 2 act)]
      ["equal?" (eq? 2 act)]
      ["++" (= 1 act)]
      ["--" (= 1 act)]
      ["iszero?" (= 1 act)]))

;; Tomara la expresion dada para verificar si los primitivos estan siendo aplicados en sus operadores con la aridad correcta
(define-pass verify-arity : L8 (e) -> L8()
   (Expr : Expr (e) -> Expr ()
      [(primapp, pr, [e*] ...)
         (if (prc-ar (symbol->string pr) (length e*))
            `(primapp, pr, e* ...)
            (error (string-append "arriba de " (symbol->string pr) ", <" (~v (length e*)) ">")))]))

;; Tomara la expresion dada para verificar que la expresion no contenga variables libres
(define-pass verify-vars : L8 (ir) -> L8 ()
  (Expr : Expr (ir [env null]) -> Expr ()
     [,x (if (memq x env)
              x
              (error (string-append "Var libre: " (symbol->string x))))]
     [(let ([,x ,t ,[e]]) ,[Expr : body (cons x env) -> body]) `(let ([,x ,t ,e]) ,body)]
     [(letfun ([,x ,t ,[e]]) ,[Expr : body (cons x env) -> body]) `(letfun ([,x ,t]) ,body)]
     [(letrec ([,x ,t ,[Expr : e (cons x env) -> e]]) , [Expr : body (cons x env) -> body]) `(letrec ([,x ,t ,e]) ,body)]
     [(lambda ([,x*, t*] ...) ,[Expr : body (append x* env) -> body]) `(lambda ([,x* ,t*] ...) ,body)]
     ;; Agregada la modificacion del for (para el punto 3)
     [(for [,x ,e0] ,[Expr : e1 (cons x env) -> e1]) `(for [,x ,e0] ,e1)]))

;; Toma la expresion dada y la anida con su respectivo tipo
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

;; Convierte los quots en contantes
(define-pass type-const : L9 (e) -> L10 ()
   (Expr : Expr (e) -> Expr ()
      [(quot ,c)
        (cond
           [(boolean? c) `(const Bool ,c)]
           [(number? c) `(const Int ,c)]
           [(char? c) `(const Char ,c)])]))

;; Metodo auxiliar de J para revisar si las variables son  del mismo tipo
(define (unify t1 t2)
   (if (and (type? t1) (type? t2))
      (cond
         [(equal? t1 t2) #t]
         [(and (equal? 'List t1) (list? t2)) (equal? (car t2) 'List)]
         [(and (equal? 'List t2) (list? t1)) (equal? (car t1) 'List)]
         [(and (list? t1) (list? t2)) (and (unify (car t1) (car t2)) (unify (caddr t1) (caddr t2)))]
         [else #f])
      (error "Se esperaban 2 tipos ")))

;; Metodo auxiliar de J para revisar si una variable no es una variable libre
(define (lookup x body)
   (if (equal? (length body) 0)
      "Error variable libre"
      (if (equal? x (car (car body)))
         (cdr (car body))
         (lookup x (cdr body)))))

;; Metodo auxiliar de type-infer que toma una expresion y su entorno para recorrerlos y regresar el tipo
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
                     ;; Agregada la modificacion de los primitivos (para el punto 4)
                     (if (equal? '++ (unparse-L10 pr))
                        (if (list? t)
                           (if (equal? (car t) 'List)
                              'Int
                              (error "El tipo no corresponde con el valor"))
                           ((error "El tipo no corresponde con el valor")))
                        (if (equal? '-- (unparse-L10 pr))
                           (if (list? t)
                              (if (equal? (car t) 'List)
                                 'Int
                                 (error "El tipo no corresponde con el valor"))
                              ((error "El tipo no corresponde con el valor")))
                           (if (equal? 'iszero? (unparse-L10 pr))
                              (if (list? t)
                                 (if (equal? (car t) 'List)
                                    'Int
                                    (error "El tipo no corresponde con el valor"))
                                 ((error "El tipo no corresponde con el valor")))
                              (error "El tipo no corresponde con el valor"))))))))]
      [(primapp ,pr ,e0 ,e1)
         (let* ([t (J e0 entorno)] [t1 (J e1 entorno)])
            (if (or (equal? '+ (unparse-L10 pr)) (equal? '* (unparse-L10 pr)) (equal? '/ (unparse-L10 pr)) (equal? '- (unparse-L10 pr))
                    ;; Agregada la modificacion de los primitivos (para el punto 4)
                    (equal? 'equal? (unparse-L10 pr)) (equal? '< (unparse-L10 pr)) (equal? '> (unparse-L10 pr)))
               (if (unify 'Int t)
                  (if (unify 'Int t1)
                     'Int
                     (error "El tipo no corresponde con el valor"))
                  (if (unify 'Bool t)
                      'Bool
                      (error "El tipo no corresponde con el valor")))
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
               (error "El tipo no corresponde con el valor")))]
      ;; Agregada la modificacion del while (para el punto 2)
      [(while [,e0] ,e1)
         (let* ([t0 (J e0 entorno)] [t1 (J e1 entorno)]) (if (unify t1 'Bool)
                                                                                        t1
                                                                                        (error "El tipo no es corresponde con el valor")))]
      ;; Agregada la modificacion del for (para el punto 3)
      [(for [,x ,e0] ,e1) (let ([t0 (J e0 entorno)]) (if (unify t0 'List)
                                                                                  (J e1 (cons (list x (list t0) entorno)))
                                                                                  (error "El tipo no corresponde con el valor")))]))

;; Toma la expresion e infiere el tipo de sus variables
(define-pass type-infer : L10 (e) -> L10 ()
   (Expr : Expr (expr) -> Expr ()
      [(letrec ([,x ,t ,e]) ,body) `(letrec ([,x ,(J e empty) ,e]) ,body)]
      [(letfun ([,x ,t ,e]) ,body) `(letfun ([,x ,(J e empty) ,e]) ,body)]
      [(let ([,x ,t ,e]) ,body) `(let ([,x ,(J e empty) ,e]) ,body)]))

;; Funcion auxiliar de uncurry-aux que obtiene la lista de asignación de una expresión
(define (asig-lambda expr acc)
   (nanopass-case (L10 Expr) expr
      [(lambda ([,x ,t]) ,body) (append (list (list x t)) (asig-lambda body acc))]
      [else acc]))

;; Funcion auxiliar de uncurry-aux que obtiene una expressión lambda currificada
(define (body-lambda expr)
   (nanopass-case (L10 Expr) expr
      [(lambda ([,x ,t]) ,body) (body-lambda body)]
      [else expr]))

;; Funcion auxiliar de uncurry que elimina expresiones lambda
(define (uncurry-aux expr)
   (nanopass-case (L10 Expr) expr
      [(lambda ([,x ,t]) ,body) (parse-L11 `(lambda ,(asig-lambda expr '()) ,(unparse-L11 (uncurry-aux (body-lambda expr)))))]
      [(let ([,x ,t ,[e]]) ,[body]) (with-output-language (L11 Expr) `(let ([,x ,t ,e]) ,body))]
      [(letrec ([,x ,t ,[e]]) ,[body]) (with-output-language (L11 Expr) `(letrec ([,x ,t ,e]) ,body))]
      [(letfun ([,x ,t ,[e]]) ,[body]) (with-output-language (L11 Expr) `(letfun ([,x ,t ,e]) ,body))]
      [(begin, [e*] ... ,[e]) (with-output-language (L11 Expr) `(begin ,e* ... ,e))]
      [(primapp, pr, [e*] ...) (with-output-language (L11 Expr) `(primapp ,pr, e* ...))]
      [(if, [e0], [e1] ,[e2]) (with-output-language (L11 Expr) `(if ,e0, e1, e2))]
      [(const ,t ,c) (with-output-language (L11 Expr) `(const ,t ,c))]
      [(list ,[e*] ...) (with-output-language (L11 Expr) `(list ,e* ...))]
      [(,[e0] ,[e1]) (with-output-language (L11 Expr) `(,e0 ,e1))]
      ;; Agregada la modificacion del while (para el punto 2)
      [(while [,e0] ,e1) (with-output-language (L11 Expr) `(while [,e0] ,e1))]
      ;; Agregada la modificacion del for (para el punto 3)
      [(for [,x ,e0] ,e1) (with-output-language (L11 Expr) `(for [,x ,e0] ,e1))]
      [else (parse-L11 (unparse-L10 expr))]))

;; Funcion que elimina expresiones lambda
(define-pass uncurry : L10 (e) -> L11 ()
   (Expr : Expr (e) -> Expr ())
   (uncurry-aux e))

;; Funcion auxiliar de symbol-table-var que genera la tabla de símbolos de una expresión
(define (symbol-table-var-aux expr table)
   (nanopass-case (L11 Expr) expr
      [(let ([,x ,t ,e]) ,body) (begin (hash-set! table x (cons t e)) (symbol-table-var-aux body table))]
      [(letrec ([,x ,t ,e]) ,body) (begin (hash-set! (symbol-table-var-aux body table) x (cons t e)))]
      [(,e0, e1) (begin
                   (define h1 table) (set! h1 (symbol-table-var-aux e0 h1))
                   (define h2 h1) (set! h2 (symbol-table-var-aux e1 h2)) h2)]
      [(primapp ,pr ,[e*] ...) (let f ([e* e*]) (if (null? e*) table (symbol-table-var-aux (first e*) (f (rest e*)))))]
      [(begin ,e* ... , e) (begin (map (lambda (e) (symbol-table-var-aux e table)) e*) (symbol-table-var-aux e table))]
      [(if ,e0, e1, e2) (begin (symbol-table-var-aux e1 table) (symbol-table-var-aux e2 table))]
      [(lambda ([,x* ,t*] ...), body) (symbol-table-var-aux body table)]
      [(list ,e* ... ,e) (begin (map (lambda (e) (symbol-table-var-aux e table)) e*) (symbol-table-var-aux e table))]
      ;; Agregada la modificacion del for (para el punto 3)
      [(for [,x ,e0] ,e1) (begin (hash-set! table x (list (newName 'arr))) (symbol-table-var-aux e0 table) (symbol-table-var-aux e1 table))]
      [else table]))

;; Funcion auxiliar de assignment  que genera la tabla de símbolos de una expresión
(define (symbol-table-var expr)
   (nanopass-case (L11 Expr) expr
      [else (symbol-table-var-aux expr (make-hash))]))

;; Funcion que elimina el valor de los identificadores y el tipo de let, letrec y letfun.
(define-pass assignment : L11 (e) -> L12 (hash)
   (Expr : Expr (e) -> Expr ()
      [(let ([,x ,t ,e]) ,[body]) `(let ,x ,body)]
      [(letrec ([,x ,t ,e]) ,[body]) `(letrec ,x ,body)]
      [(letfun ([,x ,t ,e]) ,[body]) `(letfun, x ,body)])
    (values (Expr e) (symbol-table-var e)))

;; Funcion auxiliar de list-to-array para saber de que tipo es la lista
(define (typeof l)
   (let ([firt (car l)])
      (nanopass-case (L13 Expr) firt
         [(const ,t ,c) t]
         [(lambda ([,x* ,t*] ...), body) 'Lambda]
         [(array ,le ,t [,e*]) 'List]
         [else #f])))

;; Funcion que traduce las listas en arreglos
(define-pass list-to-array : L12 (e) -> L13 ()
   (Expr : Expr (e) -> Expr()
      [(list ,e* ...) `(array ,(length e*) ,(typeof (e*)) [,e*])]))

;; Funcion para convertir expresiones del lenguaje L13 a expresiones de C (para el punto 5) 
(define (c expr tabla)
   (nanopass-case (L13 Expr) expr
      [,x (symbol->string x)]
      [(const ,t ,c) (match t
                              ['Int (number->string c)]
                              ['Bool (if c "1" "0")]
                              ['Char (string c)])]
      [(begin ,e* ... ,e) (string-append "{" (foldr string-append " " (map c e*)) (c e) "}")]
      [(primapp ,pr ,e* ...) (case
                                           [(eq? pr 'not) (string-append "(!" (c (car e*)) ")")]
                                           [(eq? pr 'equal?) (string-join (map c e*) ("=="))]
                                           [(eq? pr 'iszero?) (string-append "(" (c (car e*)) "==0)")]
                                           [(memq pr '(++ --)) (string-append "(" (c (car e*)) (symbol->string pr) ")")]
                                           [(memq pr '(< >)) (string-append "(" (c (car e*)) (symbol->string pr) (c (cadr e*)) ")")]
                                           [else (string-join (map c e*) (symbol->string pr))])]
      [(if ,e0 ,e1 ,e2) (string-append "if (" (c e0) ") { \\n" (c e1) "\\n }else{\\n " (c e2) "\\n }")]
      [(let ,x ,body) (string-append (c x) "=" (c body))]
      [(letrec ,x ,body) (string-append "(" (c x) ") { \\n" (c body) "\\n }")]
      [(letfun ,x ,body) (string-append "(" (c x) ") { \\n" (c body) "\\n }")]
      [(array ,le ,t [,e* ...]) (string-append (c t) " [" (c le) "] = {" (c e*) "};")]
      [(,e0 ,e1) (string-append (c e0) "(" (c e1) ")")]
      [(while [,e0] ,e1) "while (" (c e0) ") { \\n" (c e1) "\\n }"]
      [(for [,x ,e0] ,e1)
          (string-append "for (" (symbol->string x) " = 0; " (symbol->string x) " < " "sizeof(" (symbol->string e0)
           ")/sizeof(" (symbol->string e0) "[0]); " (symbol->string x) "++) {\\n" (c e1 tabla) "; \\n}\\n")]
      [else (error expr)]))