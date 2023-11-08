#%%
from tensorflow import keras

# Librerias de apoyo
import os
import cv2
import numpy as np
# Model reconstruction from JSON file

class ClasificaJirafa(object):

    def __init__(self):
        # Aqui cargamos el modelo
        self.model = keras.models.load_model('modelos/Jirafamodelo.h5')

    # Aqui ajustamos la imagen
    def preparar(self, img):
          img_array = cv2.imread(img, cv2.IMREAD_GRAYSCALE)
          aux_array = cv2.resize(img_array,(50, 50))
          return aux_array.reshape(-1,50,50,1)/255

    # Aqui es donde vemos si es hombre o mujer
    def clasifica(self, ruta):
        img_test = self.preparar(ruta)
        predict = self.model.predict(img_test)
        max = np.argmax(predict)
        return (max == 0)