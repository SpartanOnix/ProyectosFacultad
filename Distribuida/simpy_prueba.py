import simpy

# Forma bien estructurada
class carro(object):
    def __init__(self, id_carro, env, estacion_carga):
        self.id_carro = id_carro
        self.env = env
        self.accion = env.process(self.run())
        self.estacion_carga = estacion_carga

    def run(self):
        while True:
            
            # Estaciona y carga
            print("Empieza a estacionarse y a cargar en el %d" % (self.id_carro, self.env.now))
            #duracion_cargado = 5
            #yield self.env.process(self.charge(duracion_cargado))
            with estacion_carga.request() as req:
                yield req
            print("Empezo a cargar en el %d" % self.env.now)
            duracion_carga = 5
            yield self.charge.timeout(duracion_carga)

            # Conduccion
            print("Empezo a conducir en el %d" % self.env.now)
            duracion_viaje = 4
            yield self.env.timeout(duracion_viaje)

    def charge(self, duracion):
        yield self.env.timeout(duracion)

# Main
env = simpy.Environment()
estacion_carga = simpy.Resource(env, capacity=1)
carros = []
for i in range(4):
    carros.append(carro(i, env, estacion_carga))

# Forma chafa
def car(env):
    while True:
        print("Empieza a estacionarse en el %d" % env.now)
        duracion_estacionado = 5
        yield env.timeout(duracion_estacionado)
        print("Empieza a conducir en el %d" % env.now)
        duracion_viaje = 4
        yield env.timeout(duracion_viaje)
