% potencia, Z=X^Y %
pot(X,Y,Z) :- powend(X,Y,1,Z),!.

powend(_,0,A,Z) :- Z is A.
powend(X,Y,A,Z) :- Y1 is Y - 1, A1 is A*X, powend(X,Y1,A1,Z).

% factorial, Y=X! %
factorial(0,1).
factorial(N,Salida) :- M is N-1, factorial(M,Salida_dos), Salida is N*Salida_dos.
