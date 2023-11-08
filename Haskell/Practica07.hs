--Sinencio Granados Dante Jusepee

data N = Dig D | ConsN N D deriving (Eq,Show)
data D = D0 | D1 | D2 | D3 | D4 | D5 | D6 | D7 | D8 | D9 deriving (Eq,Show)

--Funciones auxiliares

digito :: D -> Int
digito D0 = 0
digito D1 = 1
digito D2 = 2
digito D3 = 3
digito D4 = 4
digito D5 = 5
digito D6 = 6
digito D7 = 7
digito D8 = 8
digito D9 = 9

digitoInv :: Int -> D
digitoInv 0 = D0
digitoInv 1 = D1
digitoInv 2 = D2
digitoInv 3 = D3
digitoInv 4 = D4
digitoInv 5 = D5
digitoInv 6 = D6
digitoInv 7 = D7
digitoInv 8 = D8
digitoInv 9 = D9

divicionEntera a = (a-(modulo a))
divicion a = div (divicionEntera a) 10
modulo a = mod a 10


--Funciones de la tarea

nToInt :: N -> Int
nToInt (Dig a) = digito a
nToInt (ConsN a b) = 10*(nToInt a) + (digito b)

intToN :: Int -> N
intToN a = if a < 10
          then (Dig (digitoInv a))
          else (ConsN (intToN(divicion a)) (digitoInv(modulo a)))
