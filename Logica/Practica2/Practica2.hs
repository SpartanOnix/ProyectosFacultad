module Practica2
 where
import Data.List
--Tipo de dato indice
type Indice = Int
type Modelo = [Indice]
-- Tipo de dato formula
data LP = T | F | Var Indice
        | Neg LP
        | And LP LP | Or LP LP
        | Imp LP LP deriving (Eq, Show)

--Funciones auxiliares
satMod :: Modelo -> LP -> Bool
satMod m phi = case phi of T -> True
                           F -> False
                           Var n -> elem n m
                           Neg alpha -> not(satMod m alpha)
                           And alpha beta -> (satMod m alpha) && (satMod m beta)
                           Or alpha beta -> (satMod m alpha) || (satMod m beta)
                           Imp alpha beta -> not(satMod m alpha) || (satMod m beta)

--Ejercicios

varForm :: LP -> [Indice]
varForm T = []
varForm F = []
varForm (Var i) = [i]
varForm (Neg x) = nub (varForm x)
varForm (And x y) = nub (varForm x ++ varForm y)
varForm (Or x y) = nub (varForm x ++ varForm y)
varForm (Imp x y) = nub (varForm x ++ varForm y)

conjuntoPot:: [t] -> [[t]]
conjuntoPot [] = [[]]
conjuntoPot (x:xs) = conjuntoPot xs ++ map (x:) (conjuntoPot xs)

esValLP :: LP -> Bool
esValLP x = and[satMod y x | y <- conjuntoPot(varForm(x))]

esSatLP :: LP -> Bool
esSatLP x = or[satMod y x | y <- conjuntoPot(varForm(x))]

quitaImp :: LP -> LP
quitaImp T = T
quitaImp F = F
quitaImp (Var x) = Var x
quitaImp (Neg alpha) = Neg (quitaImp alpha)
quitaImp (And alpha beta) = And (quitaImp alpha) (quitaImp beta)
quitaImp (Or alpha beta) = Or (quitaImp alpha) (quitaImp beta)
quitaImp (Imp alpha beta) = (Or (Neg (quitaImp alpha)) (quitaImp beta))
