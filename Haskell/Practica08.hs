--Sinenencio Granados Dante Jusepee

data Prop = T | F | Var String | Neg Prop | Conj Prop Prop | Disy Prop Prop |Imp Prop Prop | Equiv Prop Prop deriving (Eq,Show)

p, q, r, s, t, u, x, y, z :: Prop
p = Var "p"
q = Var "q"
r = Var "r"
s = Var "s"
t = Var "t"
u = Var "u"
x = Var "x"
y = Var "y"
z = Var "z"

--Firmas secundarias

eliminaImp :: Prop -> Prop
eliminaImp (Var f) = (Var f)
eliminaImp (Neg f) = Neg (eliminaImp f)
eliminaImp (Conj f g) = Conj (eliminaImp f) (eliminaImp g)
eliminaImp (Disy f g) = Disy (eliminaImp f) (eliminaImp g)
eliminaImplicaciones (Imp f g) = Disy (Neg (eliminaImp f)) (eliminaImp g)

eliminaEquiv :: Prop -> Prop
eliminaEquiv (Var f) = (Var f)
eliminaEquiv (Neg f) = Neg (eliminaEquiv f)
eliminaEquiv (Conj f g) = Conj (eliminaEquiv f) (eliminaEquiv g)
eliminaEquiv (Disy f g) = Disy (eliminaEquiv f) (eliminaEquiv g)
eliminaEquiv (Imp f g) = Imp (eliminaEquiv f) (eliminaEquiv g)
eliminaEquiv (Equiv f g) = Conj (Imp (eliminaEquiv f) (eliminaEquiv g))
                                (Imp (eliminaEquiv g) (eliminaEquiv f))

eliminaNeg :: Prop -> Prop
eliminaNeg (Neg a) = a

dist :: Prop -> Prop
dist (Conj(p)(Disy(q)(r))) = (Disy(Conj(p)(q))(Conj(p)(r)))
--dist (Disy(Conj(s)(t))(Conj(s)(u))) = (Conj(s)(Disy(t)(u)))
dist (Disy(p)(Conj(q)(r))) = (Conj(Disy(p)(q))(Disy(p)(r)))
--dist (Conj(Disy(p)(q))(Disy(p)(r))) = (Disy(p)(Conj(q)(r)))


--Firma principal

formaNormalD :: Prop -> Prop
formaNormalD p = dist(eliminaImp(eliminaEquiv(eliminaNeg p)))
