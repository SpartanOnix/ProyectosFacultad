#!/usr/bin/python3
# -*- coding: utf-8 -*-
from Factores import *

LF = Variable("LF",[0,1])
LI = Variable("LI",[0,1])
FIN = Variable("FIN",[0,1])
IT = Variable("IT",[0,1])
JA = Variable("JA",[0.1])
AA = Variable("AA",[0,1])
MA = Variable("MA",[0,1])
VP = Variable("VP",[0,1])
BA = Variable("BA",[0,1])
MP = Variable("MP",[0,1])

## Marginales
pLF = Factor((LF,),[0.78,0.22])
pLI = Factor((LI,),[0.78,0.22])
pFIN = Factor((FIN,),[0.78,0.22])

## Condicionales
pJA_LI = Factor((JA,LI),[0.1, 0.6, 0.9, 0.4])
pAA_FIN = Factor((AA,FIN),[0.6,0.2,0.4,0.8])
pMP_MA = Factor((MP,MA),[0.2, 0.3, 0.4, 0.5])

pMA_JAAA = Factor((MA,JA,AA),[0.5,0.15,0.05,0.95,0.5,0.85,0.95,0.05])
pVP_AABA = Factor((VP,AA,BA),[0.3,0.6,0.1,0,0.7,0.4,0.9,1])

def pLuviaInv_NoMayNoFin():
    print("P(li|¬mp,¬fin)")
    Prob = pMP_MA.reduce(MP,0).multiplica(pMA_JAAA).marginaliza(MA)
    print(Prob)
    Prob = Prob.multiplica(pAA_FIN.reduce(FIN,0)).marginaliza(AA)
    print(Prob)
    Prob = Prob.multiplica(pJA_LI).marginaliza(JA).multiplica(pFIN.reduce(FIN,0)).multiplica(pLI)
    print(Prob)
    Prob = Prob.normalize()
    print(Prob)
    Prob = Prob.reduce(LI,1)
    print("Resultado = ", Prob)
    
if __name__ == '__main__':
    pLuviaInv_NoMayNoFin()
