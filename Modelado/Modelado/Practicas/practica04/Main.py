#%%
from matplotlib import pyplot as plt 
from tensorflow import keras
from Preprocess import Preprocess as pr

# Librerias de apoyo
import os
import cv2
import numpy as np
# Model reconstruction from JSON file

class Main(object):

    def __init__(self):
        # Aqui cargamos el modelo
        self.model = keras.models.load_model('model.h5')

    # Aqui ajustamos la imagen
    def preparar(self, img):
          img_array = cv2.imread(img, cv2.IMREAD_GRAYSCALE)
          aux_array = cv2.resize(img_array,(50, 50))
          return aux_array.reshape(-1,50,50,1)/255

    # Aqui es donde vemos si es hombre o mujer
    def clasifica(self):
        for img in os.listdir("test_images"):
            img_test = self.preparar(os.path.join("test_images", img))
            predict = self.model.predict(img_test)
            max = np.argmax(predict)
            print(img)
            if max == 0 :
                print("Hombre")
            else:
                print("Mujer")
#%%
