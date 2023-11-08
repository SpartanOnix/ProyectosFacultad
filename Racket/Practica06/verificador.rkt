#lang plai
(require (file "./grammars.rkt"))
(require (file "./parser.rkt"))

;; Toma un Árbol de sintaxis abstracta CFWBAE y obtiene el tipo
;; de la expresión mínima.
;; typeof CFWBAE -> Type-Context -> Type
;; (define (typeof expr context)
(define (typeof expr context)
  (type-case SCFWBAE expr
    [idS (i) (contextos i context)]
    [numS (n) (numberT)]
    [boolS (b) (booleanT)]
    [iFS (con th el) (let ([con-type (typeof con context)] [th-type (typeof th context)] [el-type (typeof el context)])
                                (if (booleanT? con-type)
                                   (if(equal? th-type el-type) th-type (error "El then y el else del iF no son del mismo tipo"))
                                   (error "La condición del iF no es de tipo boolean")))]
    [opS (f args) (cond
                             [(pertenece? f (list son-andS? son-orS?)) (if (andmap (lambda (x) (booleanT? (typeof x context))) args) (booleanT) (error "Uno de los tipos no es boolean"))]
                             [(pertenece? f (list + - * / < <= > >= = modulo expt)) (if (not (equal? (length args) 2))
                                                                                                                (error "La operación debe recibir 2 operandos")
                                                                                                                (if (andmap (lambda (x) (numberT? (typeof x context))) args) (numberT) (error "Uno de los tipos no es number")))]
                             [(pertenece? f (list add1 sub1 zero?)) (if (not (equal? (length args) 1))
                                                                                           (error "La operación debe recibir 1 operando")
                                                                                           (if (andmap (lambda (x) (numberT? (typeof x context))) args) (numberT) (error "Uno de los tipos no es number")))]
                             [(equal? f not) (if (not (equal? (length args) 1))
                                                        (error  "La operación debe recibir 1 operando")
                                                        (if (andmap (lambda (x) (booleanT? (typeof x context))) args) (booleanT) (error "El operando no es de tipo boolean")))])]
    [condS (cases) (let ([tipo "None"])
                               (if (andmap (lambda (x) (type-case Condition x
                                                     [condition (test th)
                                                        (if (booleanT? (typeof test context))
                                                           (let ([th-type (typeof th context)])
                                                             (if (or (list (equal? tipo th-type) (equal? tipo "None"))) #t (error "Una de las expresiones then tiene tipo distinto a las demás"))
                                                             (set! tipo th-type))
                                                           (error "Una de las expresiones test no es de tipo booleano"))]
                                                    [else-cond (else-expr) (if (equal? tipo (typeof else-expr context))
                                                                                             #t
                                                                                             (error "La expresión else es de tipo distinto a las demás"))]))
                                          cases) tipo (error "Error de tipo en el condicional")))]
    [withS (bd bo) (typeof bo (bind-contexto bd context))]
    [withS* (bd bo) (typeof bo (bind-contexto bd context))]
    [funS (par rType bo) (let ([new-context (par-con par context)])
                                                     (if (equal? rType (typeof bo new-context))
                                                        (funT (append (map (lambda (p) (param-tipo p)) par) (list rType)))
                                                        (error "El valor de regreso no es del mismo tipo que el indicado")))]
    [appS (fun args) (let* ([args-types (map (lambda (a) (typeof a context)) args)] 
                                   [fun-type (typeof fun context)]                    
                                   [parametros (funT-params fun-type)]                 
                                   [return (first (reverse parametros))])             
                                (cond
                                  [(equal? (sub1 (length parametros)) (length args-types)) 
                                   (if (equal-types? (take parametros (sub1 (length parametros))) args-types) 
                                      (if (idS? fun)  
                                         return      
                                         (if (equal? (typeof (funS-body fun) (par-con (funS-params fun) context)) return)  
                                            return  
                                            (error "El valor de regreso no coincide con el resultado")))
                                      (error "El tipo de uno de los argumentos es incorrecto."))]
                                  [else (error "El número de parámetros y argumentos es distinto")]))]))

;; Busca el tipo de la variable.
;; contextos: elem params -> tipo
(define (contextos id contexto)
  (type-case Type-Context contexto
    [phi () (error "Hay un identificador libre")]
    [gamma (iD tipo xs) (if (symbol=? id iD) tipo (contextos id xs))]))

;; Contruye un contexto para los bindings.
;; bind-contexto: (listof Binding) Context -> Context
(define (bind-contexto bindings init-context)
  (cond
    [(empty? bindings) init-context]
    [else
     (let ([bnd (car bindings)])
       (type-case Binding bnd
         [binding (id type value)
                  (if (not (equal? (typeof value init-context) type))
                      (error "Error de tipos")
                      (bind-contexto (cdr bindings) (gamma id type init-context)))]))]))

;; Verifica si las listas tienen los mismos tipos.
;; equal-types?: (listof Type) (listof Type) -> boolean
(define (equal-types? params args)
  (cond
    [(empty? params) #t]
    [(equal? (car params) (car args))
     (equal-types? (cdr params) (cdr args))]
    [else #f]))

;; Contruye un contexto para los parametros.
;; par-con: (listof Param) Context -> Context
(define (par-con params init-context)
  (cond
    [(empty? params) init-context]
    [else (let ([par (car params)])
               (type-case Param par
                  [param (p type) (par-con (cdr params) (gamma p type init-context))]))]))

;; Nos dice si un elemento es miembro de una lista.
;; pertenece?: elem list -> elem
(define (pertenece? x xs)
  (cond
    [(empty? xs) #f]
    [(equal? x (car xs)) #t]
    [else (pertenece? x (cdr xs))]))
  
  
(define (prueba exp)
  (typeof (parse exp) (phi)))