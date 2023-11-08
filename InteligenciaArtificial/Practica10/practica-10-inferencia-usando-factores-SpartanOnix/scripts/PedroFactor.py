from Factores import *

# Punto 1: Construir las variables
MA = Variable('MA', [0, 1])
MP = Variable('MP', [0, 1])
AA = Variable('AA', [0, 1])
AP = Variable('AP', [0, 1])
JA = Variable('JA', [0, 1])
JP = Variable('JP', [0, 1])
BA = Variable('BA', [0, 1])
BP = Variable('BP', [0, 1])
LI = Variable('LI', [0, 1])
LF = Variable('LF', [0, 1])
FIN = Variable('FIN', [0, 1])
E = Variable('E', [0, 1])
IT = Variable('IT', [0, 1])
VP = Variable('VP', [0, 1])

# Punto 2: Construir los factores
f_LF = Factor((LF, ), [.78, .22])
f_LI = Factor((LI, ), [.78, .22])
f_FIN = Factor((FIN, ), [.72, .28])
f_IT = Factor((IT, ), [.7, .3])
f_E = Factor((E, ), [.35, .65])
f_JAJI = Factor((JA, LI), [0.1, 0.6, 0.9, 0.4])
f_AAFIN = Factor((AA, FIN), [.6, .2, .4, .8])
f_BAIT = Factor((BA, IT), [.05, .7, .95, .3])
f_MPMA = Factor((MP, MA), [.97, .03, .03, .97])
f_APAA = Factor((AP, AA), [.5, .2, .5, .8])
f_MAJAAA = Factor((MA, JA, AA), [.5, .15, .05, .95, .5, .85, .95, .05])
f_VPAABA = Factor((VP, AA, BA), [.3, .6, .1, 0, .7, .4, .9, 1])
f_JPJALF = Factor((JP, JA, LF), [.6, 1, .1, .3, .4, 0, .9, .7])
f_BPBAE = Factor((BP, BA, E), [.8, 1, .05, 1, .2, 0, .95, 0])

# Punto 3: Resolver las ecuaciones
def punto3():

    # Primera ecuacion
    print(("-" * 35) + "Primera ecuacion" + ("-" * 35))
    f_VPBP = f_VPAABA.multiplica(f_BPBAE).multiplica(f_AAFIN).multiplica(f_BAIT).multiplica(f_FIN).multiplica(f_IT).multiplica(f_E)
    f_VPBP = f_VPBP.marginaliza(AA).marginaliza(BA).marginaliza(FIN).marginaliza(IT).marginaliza(E)
    f_VPBP.normiliza()
    print(f_VPBP)

    # Segunda ecuación.
    print(("-" * 35) + "Segunda ecuacion" + ("-" * 35))
    f_VPBP2 = f_BAIT.multiplica(f_FIN).multiplica(f_IT).multiplica(f_E).marginaliza(IT)
    f_VPBP2 = f_VPBP2.multiplica(f_AAFIN).marginaliza(FIN)
    f_VPBP2 = f_VPBP2.multiplica(f_BPBAE).marginaliza(E)
    f_VPBP2 = f_VPBP2.multiplica(f_VPAABA).marginaliza(AA).marginaliza(BA)
    f_VPBP2.normiliza()
    print(f_VPBP)
    # Tercera ecuacion
    print(("-" * 35) + "Tercera ecuacion" + ("-" * 35))
    f_VPBP3 = f_BAIT.multiplica(f_IT).marginaliza(IT)
    f_VPBP3 = f_VPBP3.multiplica(f_BPBAE).multiplica(f_E).marginaliza(E)
    f_VPBP3aux = f_AAFIN.multiplica(f_FIN).marginaliza(FIN).multiplica(f_VPAABA)
    f_VPBP3 = f_VPBP3.multiplica(f_VPBP3aux).marginaliza(AA).marginaliza(BA)
    f_VPBP3.normiliza()
    print(f_VPBP)

def punto4():
    print(("-" * 15) + "Probabilidad de que María, Alicia y Víctor estén en la fiesta" + ("-" * 15))
    f_MAV = f_MPMA.multiplica(f_MAJAAA).multiplica(f_APAA).multiplica(f_VPAABA).multiplica(f_JPJALF).multiplica(f_AAFIN).multiplica(f_BAIT).multiplica(f_LI).multiplica(f_FIN).multiplica(f_IT)
    f_MAV = f_MAV.marginaliza(MA).marginaliza(JA).marginaliza(AA).marginaliza(BA).marginaliza(LI).marginaliza(FIN).marginaliza(IT)
    f_MAV = f_MAV.normaliza().reducir(MP, '1').reducir(AP, '1').reducir(VP, '1')
    print(f_MAV)

def punto5():
    print(("-" * 5) + "probabilidad de que haya llovido el día que enviaron la invitación dado que María y Alicia estuvieron presentes" + ("-" * 5))
    f_LLMA = f_AAFIN.multiplica(f_FIN).marginaliza(FIN)
    f_LLMA_aux = f_MAJAAA.multiplica(f_JAJI)
    f_LLMA_aux2 = f_LLMA_aux.multiplica(f_LLMA)
    f_LLMA_aux3 = f_APAA.reducir(AP, '1')
    f_LLMA_aux4 = f_LLMA_aux3.multiplica(f_LLMA_aux2).marginaliza(AA)
    f_LLMA_aux5 = f_MPMA.reducir(MP, '1')
    f_LLMA_aux6 = f_LLMA_aux5.multiplica(f_LLMA_aux4).marginaliza(MA)
    f_LLMA_aux7 = f_LI.multiplica(f_LLMA_aux6)
    f_LLMA = f_LLMA_aux7.normaliza().reducir(LI, '1')
    print(f_LLMA)

if __name__ == '__main__':
    punto3()
    punto4()
    punto5()