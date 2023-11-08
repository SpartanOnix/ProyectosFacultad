module Practica4
  where
import SintaxisPLI

-- Función que nos dice si una formula de PLI cumple el Axioma 1
esAxL1 :: PLI -> Bool
esAxL1 (alpha `Imp` (beta `Imp` gama))= if (alpha == gama)
                                        then True
                                        else False
esAxL1 otherwise = False
--esAxL1 phi = case phi of
  --_ -> error $ "Se necesita implementar."

-- Función que nos dice si una formula de PLI cumple el Axioma 2
esAxL2 :: PLI -> Bool
esAxL2 ((alpha `Imp` (beta `Imp` gama)) `Imp` ((delta `Imp` epsilon) `Imp` (dzeta `Imp` eta))) = if ((alpha == delta) && (alpha == dzeta) && (beta == epsilon) && (gama == eta))
                                                                                                  then True
                                                                                                  else False
esAxL2 otherwise = False
{-esAxL2 phi = case phi of
  _ -> error $ "Se necesita implementar."-}

-- Función que nos dice si una formula de PLI cumple el Axioma 3
esAxL3 :: PLI -> Bool
esAxL3 (((alpha `Imp` F) `Imp` (beta `Imp` F)) `Imp` (gama `Imp` delta)) = if ((alpha == delta) && (beta == gama))
                                                                            then True
                                                                            else False
esAxL3 otherwise = False
{-esAxL3 phi = case phi of
  _ -> error $ "Se necesita implementar."-}

-- Función que nos dice si una formula es una Axioma del sistema L
esAxiomaDeL :: PLI -> Bool
esAxiomaDeL alpha = if ((esAxL1 alpha) || (esAxL2 alpha) || (esAxL3 alpha))
                     then True
                     else False
--esAxiomaDeL phi = error $ "Se necesita implementar."

-- Función que nos dice si se aplico de manera correcta Modus Ponens
esModusPonens :: (PLI, PLI, PLI) -> Bool
esModusPonens (alpha, (beta `Imp` gama), delta) = if ((alpha == beta) && (gama == delta))
                                                   then True
                                                   else False
esModusPonens ((alpha `Imp` beta), gama, delta) = if ((alpha == gama) && (beta == delta))
                                                   then True
                                                   else False
esModusPonens otherwise = False
{-= case (phi, chi, psi) of
  _ -> error $ "Se necesita implementar."-}


-- Reglas del sistema L
data ReglaL = Prem           -- Las premisas son validas
            | Ax             -- Lasyaelantoniocm@ciencias.unam.mx formulas pueden ser axiomas
            | ModPon Int Int -- Es resultado de aplicar MP a dos formulas anteriores
            deriving (Eq,Show)
-- Tipo Paso
-- Una fomula PLI y la Regla utilizada
type Paso = (PLI, ReglaL)

-- Tipo Numero de Paso
type NumPaso = (Int, Paso)

-- Nos regresa la formula del paso n
phiPasoNum :: Int -> [NumPaso] -> PLI
phiPasoNum i lpasos = case mpi of
  Just (phi, _) -> phi
  _ -> error $ "phiPasoNum: indice fuera de rango."
  where
    mpi = lookup i lpasos

-- Nos regresa el último NumPaso de una lista
ultimoPaso :: [NumPaso] -> NumPaso
ultimoPaso lpasos
  | lpasos /= [] = (n,p)
  | otherwise = (0,(oT,Prem))
  where
    (n,p) = last lpasos

-- Revisa que el paso sea correcto
checkPaso :: [PLI] -> [NumPaso] -> NumPaso -> Bool
checkPaso lprem lpp p = case p of
  (n, (phi, Prem)) -> (elem phi lprem) && (nU == n-1) -- Revisamos que phi sea parte de lprem y que n sea el ùltimo paso
  (n, (phi, Ax)) -> (esAxiomaDeL phi) && (nU == n-1) -- Revisamos que phi sea un axioma  y que n sea el ùltimo paso
  (n, (phi, ModPon i j)) -> esModusPonens (alpha, beta, phi) && n == nU+1 -- Revisamos que phi sea resultado de hacer modus ponens con i y j
    where
      alpha = phiPasoNum i lpp
      beta = phiPasoNum j lpp
  where
    (nU,(fU,_)) = ultimoPaso lpp

-- Revisa que la prueba sea correcta
checkPrueba :: [PLI] -> [NumPaso] -> Bool
checkPrueba lprem lpasos = case lpasos of
  []      -> True -- Si la lista es vacía entonces es cierto
  _:_     -> checkPrueba lprem lpp && checkPaso lprem lpp p -- Hacemos recursión.
  where
    n = length lpasos
    lpp = Prelude.take (n-1) lpasos
    p = last lpasos

--Muestra una lista de formulas.
showLphi :: [PLI] -> String
showLphi lphi= case lphi of
                    [f]     -> showPLI f
                    f:lf    -> showPLI f ++","++ showLphi lf
                    []      -> ""

-- Muesta la regla
showRegla :: ReglaL -> String
showRegla r = case r of
  Prem -> "premisa"
  Ax -> "axioma"
  ModPon i j -> "Modus Ponens "++show i++","++show j

-- Muestra un paso indicando, mediante b, si es correcto, o no.
showNumPasoCheck :: Int -> NumPaso -> Bool -> String
showNumPasoCheck fSize (n,(phi, r)) b = "\t" ++ (show n) ++ ", " ++ fS ++ spaceAfterPhi ++ rS ++ checks
  where
    fS = showPLI phi
    spaceAfterPhi = " " ++ Prelude.take (fSize-(length fS)) (repeat ' ')
    rS = "\t" ++ (showRegla r)
    checks = if b
      then ". Correcto"
      else ". Incorrecto"

-- Muestra los pasos de lpasos indicando si son correctos, o no.
-- Initial call: showLpasos fSize lprem [] lpasos
showLpasos :: Int -> [PLI] -> [NumPaso] -> [NumPaso] -> IO ()
showLpasos fSize lprem prevLp lpasos = case lpasos of
  [] -> putStr ""
  p:lps -> do
    putStrLn $ showNumPasoCheck fSize p (checkPaso lprem prevLp p)
    showLpasos fSize lprem (prevLp++[p]) lps

-- Muestra el resultado de la prueba realizada.
showCheckConclusion :: [PLI] -> [NumPaso] -> PLI -> IO ()
showCheckConclusion lpremisas lpasos phi =
  do
    putStrLn mensaje
    putStrLn ""
    where
      mensaje
        | not pruebaOK = "\t*** Hay pasos incorrectos. ***"
        | phi /= fN = "\t*** La ultima formula no es el objetivo: ***"++ (showPLI phi) ++" /= "++ (showPLI fN)
        | otherwise =  "\tCorrecto. Mediante el sistema L: "++ lpremS ++ " |- " ++ showPLI fN
      pruebaOK = checkPrueba lpremisas lpasos
      (_,(fN,_)) = ultimoPaso lpasos
      lpremS = if lpremisas /= []
        then "{" ++ showLphi lpremisas ++"}"
        else ""

-- Función que nos regresa el elemento más grande.
maxL :: Ord a => [a] -> a
maxL = foldr1 (\x y ->if x >= y then x else y)

-- Revisa si los pasos son correctos y el resultado de la prueba realizada.
esDeduccionEnL :: [PLI] -> [NumPaso] -> PLI -> IO()
esDeduccionEnL lpremisas lpasos phi =
  do
    showLpasos fSize lpremisas [] lpasos
    showCheckConclusion lpremisas lpasos phi
  where
    fSize = maxL [length (showPLI f) | (_,(f,_)) <- lpasos ]
