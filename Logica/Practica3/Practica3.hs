module Practica3
where
import Data.List
--Tipo de dato indice
type Indice = Int
-- Tipo de dato formula
data LP = T | F
        | Var Indice
        | Neg LP
        | And LP LP
        | Or LP LP
        | Imp LP LP deriving (Eq, Show)

--Funciones auxiliares

quitaImp :: LP -> LP
quitaImp T = T
quitaImp F = F
quitaImp (Var x) = Var x
quitaImp (Neg alpha) = Neg (quitaImp alpha)
quitaImp (And alpha beta) = And (quitaImp alpha) (quitaImp beta)
quitaImp (Or alpha beta) = Or (quitaImp alpha) (quitaImp beta)
quitaImp (Imp alpha beta) = (Or (Neg (quitaImp alpha)) (quitaImp beta))

varForm :: LP -> [Indice]
varForm T = []
varForm F = []
varForm (Var i) = [i]
--varForm (Neg x) = nub (varForm x)
varForm (Neg x) = [x * (-1) | x <- (varForm x)]
varForm (And x y) = nub (varForm x ++ varForm y)
varForm (Or x y) = nub (varForm x ++ varForm y)
varForm (Imp x y) = nub (varForm x ++ varForm y)

neg:: LP -> LP
neg T = F
neg F = T
neg (Var x) = Neg (Var x)
neg (Neg (Neg alpha)) = negacion alpha
neg (Neg alpha) = neg alpha
neg (And alpha beta) = Or (neg (Neg alpha)) (neg (Neg beta))
neg (Or alpha beta) = And (neg (Neg alpha)) (neg (Neg beta))

negacion :: LP -> LP
negacion T = T
negacion F = F
negacion (Var x) = Var x
negacion (Neg alpha) = neg alpha
negacion (And alpha beta) = And (negacion alpha) (negacion beta)
negacion (Or alpha beta) = Or (negacion alpha) (negacion beta)

distrCNF :: LP -> LP
distrCNF T = T
distrCNF F = F
distrCNF (Var x) = Var x
distrCNF (Neg (Var x)) = Neg (Var x)
distrCNF (Or (And alpha beta) gama) = distrCNF (And (distrCNF (Or alpha gama)) (distrCNF (Or beta gama)))
distrCNF (Or gama (And alpha beta)) = distrCNF (And (distrCNF (Or gama alpha)) (distrCNF (Or gama beta)))
distrCNF (Or alpha beta) = Or (distrCNF alpha) (distrCNF beta)
distrCNF (And alpha beta) = And (distrCNF alpha) (distrCNF beta)

distrDNF :: LP -> LP
distrDNF T = T
distrDNF F = F
distrDNF (Var x) = Var x
distrDNF (Neg (Var x)) = Neg (Var x)
distrDNF (And (Or alpha beta) gama) = distrDNF (Or (distrDNF (And alpha gama)) (distrDNF (And beta gama)))
distrDNF (And gama (Or alpha beta)) = distrDNF (Or (distrDNF (And gama alpha)) (distrDNF (And gama beta)))
distrDNF (Or alpha beta) = Or (distrDNF alpha) (distrDNF beta)
distrDNF (And alpha beta) = And (distrDNF alpha) (distrDNF beta)

literalNeg :: [Indice] -> Bool
literalNeg xs = listVisit xs []

listVisit :: [Int] -> [Int] -> Bool
listVisit [] _ = False
listVisit (x:xs) visit
          | elem (x * (-1)) visit = True
          | otherwise = listVisit xs (visit ++ [x])

clausulaCNF :: LP -> [[Indice]]
clausulaCNF (And alpha beta) = clausulaCNF alpha ++ clausulaCNF beta
clausulaCNF (Or alpha beta) = [literalesDis (Or alpha beta)]

literalesDis :: LP -> [Indice]
literalesDis (Var x) = varForm (Var x)
literalesDis (Neg alpha)  = varForm (Neg alpha)
literalesDis (Or alpha beta) = literalesDis alpha ++ literalesDis beta

clausulaDNF :: LP -> [[Indice]]
clausulaDNF (Or alpha beta) = clausulaDNF alpha ++ clausulaDNF beta
clausulaDNF (And alpha beta) = [literalesConj (And alpha beta)]

literalesConj :: LP -> [Indice]
literalesConj (Var x) = varForm (Var x)
literalesConj (Neg alpha)  = varForm (Neg alpha)
literalesConj (And alpha beta) = literalesConj alpha ++ literalesConj beta

--Ejercicios parte 1
fnn :: LP -> LP
fnn alpha = negacion $ quitaImp alpha

fnc :: LP -> LP
fnc alpha = distrCNF alpha

fnd :: LP -> LP
fnd alpha = distrDNF alpha

--Ejercicios parte 2

esCNF :: LP -> Bool
esCNF T = True
esCNF F = True
esCNF (Var x) = True
esCNF (Neg alpha) = esCNF alpha
esCNF (And (Var x) (Var y)) = False
esCNF (And alpha beta) = esCNF alpha && esCNF beta
esCNF (Or alpha beta) = esCNF alpha && esCNF beta

valCNF :: LP -> Bool
valCNF alpha | (esCNF(fnc alpha) == True) = and[literalNeg y | y <- clausulaCNF(fnc alpha)]

esDNF :: LP-> Bool
esDNF T = True
esDNF F = True
esDNF (Var x) = True
esDNF (Neg alpha) = esDNF alpha
esDNF (Or (Var x) (Var y)) = False
esDNF (Or alpha beta) = esDNF alpha && esDNF beta
esDNF (And alpha beta) = esDNF alpha && esDNF beta

satDNF :: LP -> Bool
satDNF alpha | (esDNF(fnd alpha) == True) = or[not(literalNeg y) | y <- clausulaDNF(fnd alpha)]
