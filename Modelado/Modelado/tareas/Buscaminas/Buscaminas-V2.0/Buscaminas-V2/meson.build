
project('buscaminas-v2','vala','c')

# iniciamos el ejecutabe
deps = [dependency('glib-2.0'),dependency('gtk+-3.0')]
sources = files(
  'src/Celdas/Celdas.vala',
  'src/Tablero/Tablero.vala',
  'src/Menu/InicioJuego.vala',
  'src/Juego de Partidas/JuegodePartida.vala'
)
exe = executable('Bucaminas', sources: sources, dependencies : deps)