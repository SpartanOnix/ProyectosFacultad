#lang plai

(define-type WAE
    [id (i symbol?)]
    [num (n number?)]
    [add (izq WAE?) (der WAE?)]
    [sub (izq WAE?) (der WAE?)]
    [with (id symbol?) (value WAE?) (body WAE?)])

(define (contiene? e l)
    (match l
        ['() #f]
        [(cons x xs) (or (equal? e x) (contiene? e xs))]))

(define (union-listas l1 l2)
    (match l1
        ['() l2]
        [(cons x xs)
           (if (contiene? x l2)
                (union-listas xs l2)
                (cons x (union-listas xs l2)))]))

(define (libres expr)
    (libresAux expr '()))

(define (libresAux expr de-ligado)
    (type-case WAE expr
        [id (i) (if (not (contiene? i de-ligado))
                   (list i)
                   empty)]
        [num (n) empty]
        [add (izq der) (union-listas (libresAux izq de-ligado) (libresAux der de-ligado))]
        [sub (izq der) (union-listas (libresAux izq de-ligado) (libresAux der de-ligado))]
        [with (id value body) (union-listas (libresAux value de-ligado) (libresAux body (union-listas de-ligado (list id))))]))

(define (ligadas expr)
    (ligadasAux expr '()))

(define (ligadasAux expr de-ligado)
    (type-case WAE expr
        [id (i) (if (contiene? i de-ligado)
                   (list i)
                   empty)]
        [num (n) empty]
        [add (izq der) (union-listas (ligadasAux izq de-ligado) (ligadasAux der de-ligado))]
        [sub (izq der) (union-listas (ligadasAux izq de-ligado) (ligadasAux der de-ligado))]
        [with (id value body) (union-listas (ligadasAux value de-ligado) (ligadasAux body (union-listas de-ligado (list id))))]))

(define (de-ligado expr)
    (type-case WAE expr
        [id (i) empty]
        [num (n) empty]
        [add (izq der) (union-listas (de-ligado izq) (de-ligado der))]
        [sub (izq der) (union-listas (de-ligado izq) (de-ligado der))]
        [with (id value body) (union-listas (list id) (union-listas (de-ligado value) (de-ligado body)))]))