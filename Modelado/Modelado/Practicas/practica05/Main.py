#%%
#Librerias de apoyo
import sys
import os
import cv2
#Clasificadores de los animales
from clasificadores.ClasificaHippo import ClasificaHippo as hippo
from clasificadores.ClasificaAvestruz import ClasificaAvestruz as at
from clasificadores.ClasificaCuyo import ClasificaCuyo as cu
from clasificadores.ClasificaJirafa import ClasificaJirafa as jir
from clasificadores.ClasificaPinguino import ClasificaPinguino as pin 

class Main(object):
    
    #Inicializamos nuestros clasificadores
    def __init__(self):
        self.hippo = hippo()
        self.at = at()
        self.cu = cu()
        self.jir = jir()
        self.pin = pin()

    #Clasificamos las imagenes
    def clasificaG(self):
        for img in os.listdir("test_images"):
            print(img)
            ruta = "test_images/"+img
            if self.hippo.clasifica(ruta):
                print("Es un hipopotamo")
            elif self.at.clasifica(ruta):
                print("Es un Avestruz")
            elif self.cu.clasifica(ruta):
                print("Es un cuyo")
            elif self.jir.clasifica(ruta):
                print("Es una jirafa")
            elif self.pin.clasifica(ruta):
                print("Es un pinguino")
            else:
                print ("Es otro animal u cosa")

#%%
