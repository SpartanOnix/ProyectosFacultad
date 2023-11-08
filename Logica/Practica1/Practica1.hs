module Practica1
where
data Natural = Cero | Suc Natural deriving Show
data ListaNat = Nil | Cons Natural ListaNat deriving Show
data BTree a = Void | Node ( BTree a ) a ( BTree a ) deriving Show
data ListaSnoc a = Empty | Snoc ( ListaSnoc a ) a deriving Show

-- EJERCICIO 1
mayorQue :: Natural -> Natural -> Bool
mayorQue Cero x = False
mayorQue y Cero = True
mayorQue (Suc x) (Suc y) = mayorQue x y

-- EJERCICIO 2
restaNat :: Natural -> Natural -> Natural
restaNat x Cero = x
restaNat (Suc x) (Suc y) = if mayorQue (Suc x) (Suc y)
                           then restaNat x y
                           else Cero

-- EJERCICIO 3
sumaNat :: Natural -> Natural -> Natural
sumaNat (Suc x) Cero = (Suc x)
sumaNat x y = sumaNat (Suc x) (restaNat y (Suc Cero))

mulNat :: Natural -> Natural -> Natural
mulNat x Cero = Cero
mulNat x (Suc y) = sumaNat x (mulNat x y)

-- EJERCICIO 4
concatena :: ListaNat -> ListaNat -> ListaNat
concatena xs Nil = xs
concatena Nil ys = ys
concatena (Cons x xs) ys = (Cons x (concatena xs ys))

-- EJERCICIO 5
reversa :: ListaNat -> ListaNat
reversa Nil = Nil
reversa (Cons x xs) = concatena (reversa xs)  (Cons x Nil)

-- EJERCICIO 6
igual :: Natural -> Natural -> Bool
igual x Cero = False
igual Cero y = False
igual (Suc x) (Suc y) = if (mayorQue x y == False) && (mayorQue y x == False)
                        then True
                        else False

perteneceNat :: Natural -> ListaNat -> Bool
perteneceNat x Nil = False
perteneceNat x (Cons y ys) = if igual x y
                    then True
                    else perteneceNat x ys


-- EJERCICIO 7
inOrden :: BTree a -> [a]
inOrden Void = []
inOrden (Node i r d) = (inOrden i) ++ [r] ++ (inOrden d)

-- EJERCICIO 8
agregaOrden :: (Ord a) => a -> BTree a -> BTree a
agregaOrden x Void = Node Void x Void
agregaOrden x (Node i r d) = if (x < r)
                             then Node (agregaOrden x i) r d
                             else Node i r (agregaOrden x d)

-- EJERCICIO 9
tailSnoc :: ListaSnoc a -> ListaSnoc a
tailSnoc (Snoc Empty x) = Empty
tailSnoc (Snoc xs x) = Snoc (tailSnoc xs) x

-- EJERCICIO 10
mapSnoc :: (a -> b) -> ListaSnoc a -> ListaSnoc b
mapSnoc fun Empty = Empty
mapSnoc fun (Snoc ys y) = (Snoc (mapSnoc fun ys) (fun y))

{-- PUNTO EXTRA
longitud :: Int -> Int

tribonaccies :: Int -> [Int]

-}
