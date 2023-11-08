--Sinencio Granados Dante Jusepee

burbuja :: (Ord a) =>  [a] -> [a]
burbuja (x:y:xs) =
  if x > y then y:burbuja (x:xs)
  else x:burbuja (y:xs)
burbuja xs = xs

ordBurbuja :: (Ord a) => [a] -> [a]
ordBurbuja [] = []
ordBurbuja lst =
  let t1 = burbuja lst
  in ordBurbuja (init t1) ++ [last t1]
