#lang plai
(require (file "./grammars.rkt"))
(require (file "./parser.rkt"))

;; Función que toma una expresión con azúcar sintáctica
;; SCFWBAE y elimina el azúcar sintáctica, tansformándola
;; en una expresión del tipo CFWBAE; formando el árbol de
;; sintáxis abstracta correspondiente a la expresión recibida.
;; desugar SCFWBAE-> CFWBAE
;; (define (desugar sexpr))
(define (desugar sexpr)
   (type-case SCFWBAE sexpr
      [idS (i) (id i)]
      [numS (n) (num n)]
      [boolS (b) (bool b)]
      [iFS (con the els) (iF (desugar con) (desugar the) (desugar els))]
      [opS (fun arg) (op fun (map (lambda (x) (desugar x)) arg))]
      [condS (cas) (desugar-cond cas)]
      [withS (bs bo) (app (fun (map (lambda (x) (binding-id x)) bs) (desugar bo)) (map (lambda (x) (desugar (binding-value x))) bs))]
      [withS* (bs bo) (desugar (convertir bs bo))]
      [funS (params bo) (fun params (desugar bo))]
      [appS (fun arg) (app (desugar fun) (map desugar arg))]))

;; Le quita la azucar a las condicionales
;; desugar-cond: sexpr -> sexpr
(define (desugar-cond cas)
  (type-case Condition (car cas)
    [condition (exp the) (iF (desugar exp) (desugar the) (desugar-cond (cdr cas)))]
    [else-cond (else-expr) (desugar else-expr)]))

;; Convierte los withS* a withs
;; convertir: withS*-bindings withS*-body -> withS
(define (convertir bs bo)
   (cond
     [(empty? bs) bo]
     [else (withS (list (car bs)) (convertir (cdr bs) bo))]))