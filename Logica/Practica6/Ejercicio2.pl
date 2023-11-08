% Simples %
aprobara_el_examen(pedro) :- estudia(pedro), dormir_temprano(pedro); trampa(pedro).
casarse(daniel,sofia) :- pedir_matrimonio(daniel, sofia).
maullan(gatitos) :- hambre(gatitos); alegres(gatitos); celo(gatitos).
le_gusta_anadar_en_bici(julian) :- azul(cielo), verde(pasto).

% Recursivas %
natural(0).
natural(s(X)) :- natural(X).

fibonaci(0,0).
fibonaci(1,1).
fibonaci(N,Residuo) :- fibonaci(N1,Res1), fibonaci(N2,Res2), N1 is N-1, N2 is N-2, N>1, Residuo is Res1 + Res2.

suma(X,0,X).
suma(0,X,X).
suma(X,s(Y),s(Z)) :- suma(X,Y,Z).

potencia(_,0,s(0)).
potencia(0,_,0).
potencia(X,s(0),X).
potencia(s(0),_,s(0)).
potencia(X,s(Y),Residuo):- potencia(X,Y,Res1), multi(X,Res1,Residuo).

% Auxiliar de potencia %
multi(_,0,0).
multi(0,_,0).
multi(s(0), X, X).
multi(X, s(0), X).
multi(X,s(Y),Residuo):- multi(X,Y,Res1), suma(X,Res1,Residuo).
