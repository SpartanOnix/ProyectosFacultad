--Sinencio Granados Dante Jusepee

import Data.List (nub)

type ProductoBI = [(Int,Int)]
type RelacionBI = ProductoBI
--type auxiliar
type Conj a = [a]

--Funciones auxiliares
nub' :: (Eq a) => [(a,a)] -> [(a,a)]
nub' [] = []
nub' (x:xs)
      |x `notElem` xs = [x] ++ nub'(xs)
      |otherwise = nub'(xs)

inicial :: (Int,Int) -> Int
inicial (x,y) = x

ultimo :: (Int,Int) -> Int
ultimo (x,y) = x

pertenece :: (Eq a) => a -> [a] -> Bool
pertenece x [] = False
pertenece x (y:ys)  | x==y = True
                    |otherwise = pertenece x ys

eliminarElemento :: Eq a => [a] -> [a]
eliminarElemento [] = []
eliminarElemento (x:xs) | x `elem` xs = eliminarElemento xs
                        |otherwise = x : eliminarElemento xs

dom :: RelacionBI -> [Int]
dom [(a,b)] = [inicial (a,b)]
dom (x:xs) = eliminarElemento (dom [(x)] ++ dom(xs))

im :: RelacionBI -> [Int]
im [(a,b)] = [ultimo (a,b)]
im (x:xs) = eliminarElemento (im [(x)] ++ im(xs))

subconjunto :: Eq a => [a] -> [a] -> Bool
--subconjunto xs ys = and [elem x ys | x <- xs]
subconjunto conjunto1 conjunto2 = all (`elem` conjunto2) conjunto1

listC :: Eq a => [a] -> Conj a
listC = nub


--Funciones de la tarea
union :: RelacionBI -> RelacionBI -> RelacionBI
union x y = nub' (concat [x,y])


interseccion :: RelacionBI -> RelacionBI -> RelacionBI
interseccion []_ = []
interseccion (x:xs) ys
            | x `elem` ys = x : interseccion xs ys
            |otherwise = interseccion xs ys


diferencia ::  RelacionBI -> RelacionBI -> RelacionBI
diferencia []_ = []
diferencia (x:xs) ys
          | x `notElem` ys = x : diferencia xs ys
          |otherwise = diferencia xs ys


composicion :: RelacionBI -> RelacionBI -> RelacionBI
composicion f g = nub' [(x,z) | (x,y) <- f, (y1,z) <- g, y == y1]


reflexividad :: RelacionBI -> Bool
reflexividad [] = False
reflexividad xs = and [pertenece (x,x) xs | x <- (dom xs) ++ (im xs)]

antireflexividad :: RelacionBI -> Bool
antireflexividad [] = False
antireflexividad xs = and [ (x,x) `notElem` xs | x <- (dom xs) ++ (im xs)]


simetria :: RelacionBI -> Bool
simetria f = listC [(y,x) | (x,y) <- f] `subconjunto` f

antisimetria :: RelacionBI -> Bool
antisimetria f = and [x == y | (x,y) <- f, (y,x) `elem` f]


transitividad :: RelacionBI -> Bool
transitividad f = and [elem (x,y) f | (x,a) <- f, (b,y) <- f, a==b]


equivalencia :: RelacionBI -> Bool
equivalencia f = reflexividad f && simetria f && transitividad f


ordenParcial :: RelacionBI -> Bool
ordenParcial f = reflexividad f && antisimetria f && transitividad f
