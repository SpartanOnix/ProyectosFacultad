% Automata finito determinista %
inicial1(q0). % estado inicial %
final1(q3). % estado final%

% funcion de transicion %
delta(q0,a,q1).
delta(q1,b,q2).
delta(q2,c,q1).
delta(q2,d,q3).
delta(q3,c,q0).

afd(xs) :- inicial1(Q0), transicion1(Q0,XS).

transicion1(QF,[]) :- final1(QF).
transicion1(Q,[X|XS]) :- delta(Q,X,Qi), transicion1(Qi,XS).

% Automata finito no-determinista %
inicial2(q1). % estado inicial %
final2(q3). % estado final%

% funcion de transicion %
gama(q1,b,q2).
gama(q2,a,q2).
gama(q2,a,q3).
gama(q3,b,q2).

transicion2(QF,[]) :- final2(QF).
transicion2(Q,[X|XS]):- gama(Q,X,Q1), transicion2(Q1,XS).

afn(QI,XS):- transicion2(QI,XS).

transicion_descrita(QF,[]):- final2(QF), display([QF]).
transicion_descrita(Q,[X|XS]):- delta(Q,X,Q1), display([Q,Q1]), transicion2(Q1,XS).

afn_descrita(QI,XS):- inicial2(QI), transicion_descrita(QI,XS).
