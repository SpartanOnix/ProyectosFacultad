--Sinencio Granados Dante Jusepee

productoCart :: [a] -> [b] -> [(a,b)]
productoCart xs ys = [(x,y) | x <- xs, y <- ys]


r1 :: (Ord a) => [a] -> [a] -> [(a,a)]
r1 f g = [(x,y) | x <- f, y <- g, x==y]

r1' :: (Ord a) => [a] -> [a] -> [(a,a)]
r1' []_ = []
r1' _[] = []
r1' (x:xs) (y:ys)
    |x==y = (x,y):r1' xs ys
    |otherwise = r1' xs ys


r2 :: (Integral a) => [a] -> [a] -> [(a,a)]
r2 f g = [(x,y) | x <- f, y <- g, x+y==5]

r2' :: (Integral a) => [a] -> [a] -> [(a,a)]
r2' _ [] = []
r2' [] _ = []
r2' (x:xs) (y:ys)
    |x+y==5 = (x,y):r2' xs ys
    |otherwise = r2' xs ys

--Intento 2
--r2' :: (Integral a) => [a] -> [a] -> [(a,a)]
--r2' _ [] = []
--r2' [] _ = []
--r2' x y = nub' (filter (\(a,b)-> a+b==5)(productoCart x y) ++ r2' xy)


nub' :: (Eq a) => [(a,a)] -> [(a,a)]
nub' [] = []
nub' (x:xs)
      |x `notElem` xs = [x] ++ nub'(xs)
      |otherwise = nub'(xs)


union' :: (Eq a) => [(a,a)] -> [(a,a)] -> [(a,a)]
union' f g = nub' (concat [f,g])


interseccion' :: (Eq a) => [(a,a)] -> [(a,a)] -> [(a,a)]
interseccion' []_ = []
interseccion' (x:xs) ys
            | x `elem` ys = x : interseccion' xs ys
            |otherwise = interseccion' xs ys
