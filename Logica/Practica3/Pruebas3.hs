module Pruebas3
where
import Practica3

f1 = fnn (Imp (And (Var 1) (Imp (Var 2) (Var 3))) (Imp (Or (Var 3) (Var 1)) (Var 2)))
f2 = fnn (Imp (Imp (Imp (Var 10) (Var 11)) (Imp (Var 12) (Var 13))) (Imp (Imp (Var 14) (Var 15)) (Imp (Var 16) (Var 17))))
f3 = fnc (Or (And (Or (And (Var 1) (Var 2)) (Var 3)) (Or (And (Var 4) (Var 5)) (Var 6))) (Var 7))
f4 = fnc (And (And (Neg (Var 5)) (Var 23)) (Or (Neg (Var 1)) (And (Or (Var 3) (Var 6)) (Var 8))))
f5 = fnd (And (Or (And (Or (Var 1) (Var 2)) (Var 3)) (And (Or (Var 4) (Var 5)) (Var 6))) (Var 7))
f6 = fnd (Or (Or (Neg (Var 5)) (Var 23)) (And (Neg (Var 1)) (Or (And (Var 3) (Var 6)) (Var 8))))

l1 = esCNF (And (And (Or (Var 1) (Var 2)) (Or (Var 3) (Var 4))) (Or (Var 6) (Var 7)))
l2 = esCNF (And (And (Or (Var 35) (Var 43)) (Neg (Var 18))) (And (Neg (Var 64)) (Or (And (Var 13) (Var 29)) (Neg (Neg (Var 15))))))
l3 = esDNF (Or (Or (Neg (Var 56)) (And (Var 14) (Var 72))) (Or (And (Var 19) (Var 0)) (And (Var 92) (Var 1))))
l4 = esDNF (Or (Or (Var 23) (Var 89)) (And (Var 82) (Neg (Neg (Var 14)))))
l5 = satDNF (Or (Or (And (And (Var 1) (Var 4)) (Neg (Var 1))) (And (Var 3) (And (Var 5) (Neg (Var 5))))) (And (Var 6) (Neg (Var 6))))
l6 = satDNF (Or (Or (And (And (Var 1) (Var 4)) (Neg (Var 1))) (And (Var 3) (And (Var 5) (Neg (Var 5))))) (And (Var 6) (Var 8)))
l7 = valCNF (And (And (Or (Var 3) (Var 67)) (Or (Or (Var 17) (Neg (Var 27))) (Var 61))) (And (Or (Var 5) (Or (Var 52) (Neg (Var 3)))) (Or (Var 9) (Var 10))))
l8= valCNF (And (And (Or (Var 3) (Neg (Var 3))) (Or (Or (Var 17) (Neg (Var 17))) (Var 61))) (And (Or (Var 5) (Or (Var 52) (Neg (Var 5)))) (Or (Var 9) (Neg (Var 9)))))
l9 = valCNF (And (And (Or (Var 3) (Neg (Var 3))) (Or (Or (Var 17) (Neg (Var 17))) (Var 61))) (And (Or (Var 5) (Or (Var 52) (Neg (Var 5)))) (Or (Var 9) (Neg (Var 4)))))
