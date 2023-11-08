state(on(c,on(b,on(a,void))),void,void).

move(state(on(X,PostX),Y,Z),state(PostX,on(X,Y),Z)).
move(state(on(X,PostX),Y,Z),state(PostX,Y,on(X,Z))).
move(state(PostX,on(X,Y),Z),state(on(X,PostX),Y,Z)).
move(state(PostX,on(X,Y),Z),state(PostX,Y,on(X,Z))).
move(state(PostX,Y,on(X,Z)),state(on(X,PostX),Y,Z)).
move(state(PostX,Y,on(X,Z)),state(PostX,on(X,Y),Z)).

path(X,X,[]).
path(Init,Fin,[X|XS]):-move(Init,X),path(X,Fin,XS).
