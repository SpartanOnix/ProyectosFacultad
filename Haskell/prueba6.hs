--INTEGRANTES 
--REYES MARTINEZ ANTONIO
--SANDOVAL MENDOZA ANTONIO


--Codigo Auxiliar para porblema 4--
import Data.List (nub)

--type dado por la practica--
type ProductoBI = [(Int, Int)]
type RelacionBI = ProductoBI

--FUNCIONES AUXILIARES PARA REFLEXIVIDAD (5) Y ANTIREFLEXIVIDAD(6) 
eliminarElemento :: Eq a => [a] -> [a]
eliminarElemento [] = []
eliminarElemento (x:xs) | x `elem` xs = eliminarElemento xs
                |otherwise = x : eliminarElemento xs

inicial :: (Int,Int) -> Int
inicial (x,y) = x

ultimo :: (Int,Int) -> Int
ultimo (x,y) = x

member x [] = False
member x (y:ys)
               | x == y = True
               |otherwise = member x ys 

--DEVUELVE LA LISTA DE LOS PRIMEROS NUMEROS EN PARES ORDENADOS
dominio :: RelacionBI -> [Int]
dominio [(a,b)] = [inicial (a,b)]
dominio (x:xs) = eliminarElemento (dominio [(x)] ++ dominio(xs))
--DEVUELVE LA LISTA DE LOS SEGUNDOS NUMEROS EN PARES ORDENADOS
imagen :: RelacionBI -> [Int]
imagen [(a,b)] = [ultimo (a,b)]
imagen (x:xs) = eliminarElemento (imagen [(x)] ++ imagen(xs))

--Codigo Auxiliar para problema 7--
type Conj a = [a]

subcon :: Eq a => [a] -> [a] -> Bool
subcon c1 c2 = all (`elem` c2) c1

listaAConjunto :: Eq a => [a] -> Conj a
listaAConjunto = nub



--PROBLEMA 1 UNION --
union :: RelacionBI -> RelacionBI -> RelacionBI
union xs ys = xs ++ [y | y <- ys, y `notElem` xs]

--PROBLEMA 2 INTERSECCION --
interseccion :: RelacionBI -> RelacionBI -> RelacionBI
interseccion []     ys = []
interseccion (x:xs) ys | x `elem` ys = x : interseccion xs ys
                        | otherwise   = interseccion xs ys
--PROBLEMA 3 DIFERENCIA--
diferencia :: RelacionBI -> RelacionBI -> RelacionBI
diferencia [] ys = []
diferencia (x:xs) ys | x `elem` ys = diferencia xs ys
                      | otherwise   = x : diferencia xs ys

--PROBLEMA 4 COMPOSICION --
composicion :: RelacionBI -> RelacionBI -> RelacionBI
composicion r s = nub [(x,y) | (x,u) <- r, (v,y) <- s, u == v]

--PROBLEMA 5 REFLEXIVA--
reflexividad :: RelacionBI -> Bool
reflexividad [] = False
reflexividad xs = and [member (x,x) xs | x <- (dominio xs) ++ (imagen xs)]

--PROBLEMA 6 ANTIRREFLEXIVA -- 
antireflexividad :: RelacionBI -> Bool
antireflexividad [] = False
antireflexividad xs = and [ (x,x) `notElem` xs | x <- (dominio xs) ++ (imagen xs)]


--PROBLEMA 7 SIMETRIA--
simetria :: RelacionBI -> Bool
simetria r =
    listaAConjunto [(y,x) | (x,y) <- r] `subcon` r

--PROBLEMA 8 ASIMETRIA--
antisimetria :: RelacionBI -> Bool
antisimetria r =
 and [x == y | (x,y) <- r, (y,x) `elem` r]

--PROBLEMA 9 TRANSITIVIDAD--
transitividad :: RelacionBI -> Bool
transitividad r = and [elem (x,y) r | (x,a) <- r, (b,y) <- r, a==b]
 
 --PROBLEMA 10 EQUIVALENCIA--
equivalencia :: RelacionBI -> Bool
equivalencia r = reflexividad r && simetria r && transitividad r

 -- PROBLEMA 11 Orden Pracial --
ordenParcial :: RelacionBI -> Bool
ordenParcial r = reflexividad r && antisimetria r && transitividad r
