% Verificar si un elemento pertenece auna lista %
pertenece(X,[X|_],1).
pertenece(X,[_|XS],N) :- pertenece(X,XS,N1), N is N1+1.

% Verifija si una lista es el prefijo de otra %
prefijo([],L).
prefijo([X|XS],[X|YS]) :- prefijo(XS,YS).

% Verifica si la segunla lista es la suma acumulada de la primera %
acumulada([],[],Acc).
acumulada([X|XS],[Y|YS],ACC) :- Y is ACC+X, acumulada(XS,YS,Y).
sumaAcumulada([X|XS],[Y|YS]) :- acumulada([X|XS],[Y|YS],0).

% Crea una lista de tuplas con las veces que se repite un elemento %
nuevalista(XS,YS) :- repetidos(XS,C), creador(C,YS).

% Auxiliares de nueva lista %
repetidos([],[]).
repetidos([X|XS],[Z|ZS]) :- verificador(X,XS,YS,Z), repetidos(YS,ZS).

verificador(X,[],[],[X]).
verificador(X,[Y|YS],[Y|YS],[X]) :- X\=Y.
verificador(X,[X|XS],YS,[X|ZS]) :- verificador(X,XS,YS,ZS).

creador([],[]).
creador([[X|XS]|YS],[(c(N,X))|ZS]) :- longitud([X|XS],N), creador(YS,ZS).

longitud([],0).
longitud([_|XS],N):- longitud(XS,N1), N is N1+1.
