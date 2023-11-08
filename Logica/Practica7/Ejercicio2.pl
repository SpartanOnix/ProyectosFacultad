% Me base en: https://baptiste-wicht.com/posts/2010/09/solve-einsteins-riddle-using-prolog.html#:~:text=Here%20is%20the%20riddle%20as,and%20keep%20a%20different%20pet. %

persona(0,[]) :- !.
persona(N,[(_Persona,_Color,_Bebida,_Cigarro,_Mascota)|X]) :- N1 is N-1, persons(N1,X).

person(1, [H|_], H) :- !.
person(N, [_|T], R) :- N1 is N-1, person(N1, T, R).

hint1([(noruego,_,_,_,_),(_,azul,_,_,_),(_,_,leche,_,_),_,_]).

hint2([(ingles,roja,_,_,_)|_]).
hint2([_|X]) :- hint2(X).

hint3([(espanol,_,_,_,perro)|_]).
hint3([_|X]) :- hint3(X).

hint4([(_,verde,cafe,_,_)|_]).
hint4([_|X]) :- hint4(X).

hint5([(ucraniano,_,te,_,_)|_]).
hint5([_|X]) :- hint5(X).

hint6([(_,marfil._,_,_),(_,verde,_,_,_)|_]).
hint6([_|X]) :- hint6(X).

hint7([(_,_,_,oldGold,caracoles)|_]).
hint7([_|X]) :- hint7(X).

hint8([(_,amarilla,_,kools,_)|_]).
hint8([_|X]) :- hint8(X).

hint9([(_,_,_,chesterfields,_),(_,_,_,_,zorro)|_]).
hint9([(_,_,_,_,zorro),(_,_,_,chesterfields,_)|_]).
hint9([_|X]) :- hint9(X).

hint10([(_,_,_,kools,_),(_,_,_,_,caballo)|_]).
hint10([(_,_,_,_,caballo),(_,_,_,kools,_)|_]).
hint10([_|X]) :- hint10(X).

hint11([(_,_,jugoNaranja,luckyStrike,_)|_]).
hint11([_|X]) :- hint11(X).

hint12([(japones,_,_,parliaments,_)|_]).
hint12([_|X]) :- hint12(X).

pregunta1([(_,_,_,_,pez)|_]).
pregunta1([_|X]) :- pregunta1(X).

solucion1(Persona) :-
  persons(5, Persona),
  hint1(Persona),
  hint2(Persona),
  hint3(Persona),
  hint4(Persona),
  hint5(Persona),
  hint6(Persona),
  hint7(Persona),
  hint8(Persona),
  hint9(Persona),
  hint10(Persona),
  hint11(Persona),
  hint12(Persona),
  hint13(Persona),
  hint14(Persona),
  hint15(Persona),
  pregunta1(Persona).

pregunta2([(_,_,_,malboro,_)|_]).
pregunta2([_|X]) :- pregunta2(X).

solucion2(Persona) :-
  persons(4, Persona),
  hint1(Persona),
  hint2(Persona),
  hint3(Persona),
  hint4(Persona),
  hint5(Persona),
  hint6(Persona),
  hint7(Persona),
  hint8(Persona),
  hint9(Persona),
  hint10(Persona),
  hint11(Persona),
  hint12(Persona),
  hint13(Persona),
  hint14(Persona),
  hint15(Persona),
  pregunta2(Persona).
