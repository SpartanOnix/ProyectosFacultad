--Sinencio Granados Dante Jusepee
--Sandoval Mendoza Antonio

data Exp = Var1 V | Var2 V V | Var3 V V V | Suma Exp Exp deriving(Eq,Show)
data V = X1 | Y1| Z1| X0| Y0| Z0 deriving (Eq,Show)
type Matriz = [[Int]]


--Firmas auxiliares

auxInvX :: V -> V
auxInvX f
      | f == X1 = X0
      | f == X0 = X1
      | f == Y1 = Y1
      | f == Y0 = Y0
      | f == Z1 = Z0 --
      | f == Z0 = Z1 --

invX :: Exp -> Exp
invX (Var1 f)
    | f == X1 = Var1 X0
    | f == X0 = Var1 X1
    | f == Y1 = Var1 Y1
    | f == Y0 = Var1 Y0
    | f == Z1 = Var1 Z1 --
    | f == Z0 = Var1 Z0 --
invX (Var2 f g) = Var2 (auxInvX f) (auxInvX g)
invX (Suma f g) = Suma (invX f) (invX g)
invX (Var3 f g h) = Var3 (auxInvX f) (auxInvX g) (auxInvX h) --
--invX (Suma f (Suma g h)) = Suma (invX f) (Suma (invX g) (invX h)) --

auxInvY :: V -> V
auxInvY f
      | f == X1 = X1
      | f == X0 = X0
      | f == Y1 = Y0
      | f == Y0 = Y1
      | f == Z1 = Z0 --
      | f == Z0 = Z1 --

invY :: Exp -> Exp
invY (Var1 f)
    | f == Y1 = Var1 Y0
    | f == Y0 = Var1 Y1
    | f == X1 = Var1 X1
    | f == X0 = Var1 X0
    | f == Z1 = Var1 Z1 --
    | f == Z0 = Var1 Z0 --
invY (Var2 f g) = Var2 (auxInvY f) (auxInvY g)
invY (Suma f g) = Suma (invY f) (invY g)
invY (Var3 f g h) = Var3 (auxInvY f) (auxInvY g) (auxInvY h) --
--invY (Suma f (Suma g h)) = Suma (invY f) (Suma (invY g) (invY h)) --

auxBool :: Exp -> Bool
auxBool (Var1 f)
        | f == X1 = True
        | f == X0 = False
        | f == Y1 = True
        | f == Y0 = False
        | f == Z1 = True --
        | f == Z0 = False --
auxBool (Var2 f g) = (auxBool (Var1 f)) && (auxBool (Var1 g))
auxBool (Suma f g) = (auxBool f) || (auxBool g)
auxBool (Var3 f g h) = (auxBool (Var1 f)) && (auxBool (Var1 g)) && (auxBool (Var1 h)) --
--auxBool (Suma f (Suma g h)) = (auxBool f) || (auxBool g) || (auxBool h) --

listaV :: Exp -> [Bool]
listaV f = [auxBool f, auxBool (invY f), auxBool (invX(invY f)), auxBool (invX f)]


--Firma principal

karnaughDos :: Exp -> Exp
karnaughDos (Var1 f) = Var1 f
karnaughDos (Var2 f g) = if f == g
            then Var1 f
            else Var2 f g
karnaughDos (Suma (Var1 f) (Var1 g))
            | f == g = Var1 f
            | f /= g = Suma (Var1 f) (Var1 g)
karnaughDos f
            | (listaV f) !! 0 == False = Suma (Var1 X0) (Var1 Y0)
            | (listaV f) !! 1 == False = Suma (Var1 X0) (Var1 Y1)
            | (listaV f) !! 2 == False = Suma (Var1 X1) (Var1 Y1)
            | (listaV f) !! 3 == False = Suma (Var1 X1) (Var1 Y0)

karnaughTres :: Exp -> Exp
karnaughTres (Var1 f) = Var1 f
karnaughTres (Var2 f g) = if f == g
            then Var1 f
            else Var2 f g
karnaughTres (Var3 f g h) = if ((f == h) && (f == g) && (g == h))
            then Var1 f
            else Var3 f g h
karnaughTres (Suma (Var1 f) (Var1 g))
            | f == g = Var1 f
            | f /= g = Suma (Var1 f) (Var1 g)
karnaughTres (Suma (Var1 f) (Suma (Var1 g) (Var1 h)))
            | g == h = Var1 g
            | f == g = Var1 f
            | g /= h = Suma (Var1 g) (Var1 h)
            | f /= g = Suma (Var1 f) (Suma (Var1 g) (Var1 h))
karnaughTres f
            | (listaV f) !! 0 == False = Suma (Var1 X0) (Suma (Var1 Y0) (Var1 Z0))
            | (listaV f) !! 1 == False = Suma (Var1 X0) (Suma (Var1 Y0) (Var1 Z1))
            | (listaV f) !! 2 == False = Suma (Var1 X0) (Suma (Var1 Y1) (Var1 Z0))
            | (listaV f) !! 3 == False = Suma (Var1 X0) (Suma (Var1 Y1) (Var1 Z1))
            | (listaV f) !! 4 == False = Suma (Var1 X1) (Suma (Var1 Y0) (Var1 Z0))
            | (listaV f) !! 5 == False = Suma (Var1 X1) (Suma (Var1 Y0) (Var1 Z1))
            | (listaV f) !! 6 == False = Suma (Var1 X1) (Suma (Var1 Y1) (Var1 Z0))
            | (listaV f) !! 7 == False = Suma (Var1 X1) (Suma (Var1 Y1) (Var1 Z1))
