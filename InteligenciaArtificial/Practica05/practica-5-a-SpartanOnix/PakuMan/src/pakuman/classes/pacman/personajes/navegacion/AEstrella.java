/*
 * Código utilizado para el curso de Inteligencia Artificial.
 * Se permite consultarlo para fines didácticos en forma personal,
 * pero no esta permitido transferirlo resuelto a estudiantes actuales o potenciales.
 */
package pacman.personajes.navegacion;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import javafx.scene.paint.Color;
import pacman.personajes.Movimiento;

/**
 * Clase donde se define en algoritmo de A* para que se use en el fantasma.
 * @author baruch
 * @author blackzafiro
 */
public class AEstrella extends Algoritmo {
	
	private final static Logger LOGGER = Logger.getLogger("pacman.personajes.navegacion.AEstrella");
	static { LOGGER.setLevel(Level.FINE); }
	
	private PriorityQueue<NodoBusqueda> listaAbierta;   // Cola de prioridad de donde obtendremos los nodos
	                                                    // sobre los que se realizará el algoritmo.
	private HashMap<Estado, Estado> listaCerrada;       // Tabla de dispersión donde se agregan todos los estados
                                                        // que se terminó de revisar.
    private Estado estadoFinal;                         // Casilla donde se encuentra pacman.
    private boolean terminado;                          // Define si nuestro algoritmo ha terminado.
    private NodoBusqueda nodoSolucion;                  // Nodo a partir del cual se define la solución,
	                                                    // porque ya se encontró la mejor rutal al estado meta.
	
    /**
     * Inicializador del algoritmo.
	 * Se debe mandar llamar cada vez que cambien el estado incial y el estado
	 * final.
     * @param estadoInicial Pasillo donde se encuentra el fantasma.
     * @param estadoFinal Pasillo donde se encuentra pacman.
     */
    private void inicializa(Estado estadoInicial, Estado estadoFinal){
		this.estadoFinal = estadoFinal;
        this.terminado = false;
		this.nodoSolucion = null;
        this.listaAbierta = new PriorityQueue<>();
        this.listaCerrada = new HashMap<>();
        estadoInicial.calculaHeuristica(estadoFinal);
        this.listaAbierta.offer(new NodoBusqueda(estadoInicial));
    }
    
    /**
     * Función que realiza un paso en la ejecución del algoritmo.
     */
    private void expandeNodoSiguiente(){
		NodoBusqueda actual = listaAbierta.poll();
		NodoBusqueda anterior;
		listaCerrada.put(actual.estado(), actual.estado());
		for(NodoBusqueda n : actual.getSucesores()){
			if(!listaCerrada.containsKey(n.estado())){
				anterior = listaAbierta.peek();
				if(!listaAbierta.contains(n)){
					n.estado().calculaHeuristica(estadoFinal);
					listaAbierta.add(n);
				}else if(n.gn() < anterior.gn()){
					listaAbierta.remove(anterior);
					listaAbierta.offer(n);
				}
			}
		}
		if(actual.estado().equals(estadoFinal)) nodoSolucion = actual;
		
    }
	
	/**
	 * Se puede llamar cuando se haya encontrado la solución para obtener el
	 * plan desde el nodo inicial hasta la meta.
	 * @return secuencia de movimientos que llevan del estado inicial a la meta.
	 */
	private LinkedList<Movimiento> generaTrayectoria() {
		LinkedList<Movimiento> trayecto = new LinkedList<>();
		NodoBusqueda papa = nodoSolucion.padre();
		if(nodoSolucion != null){
			while(papa.padre() != null){
				trayecto.addFirst(papa.accionPadre());
				papa = papa.padre(); 
			}
		}
		return trayecto;
	}
	
	/**
	 * Pinta las celdas desde el nodo solución hasta el nodo inicial
	 */
	private void pintaTrayectoria(Color color) {
		if (nodoSolucion == null) return;
		NodoBusqueda temp = nodoSolucion.padre();
		while(temp.padre() != null) {
			temp.estado().pintaCelda(color);
			temp = temp.padre();
		}
	}
    
    /**
     * Función que ejecuta A* para determinar la mejor ruta desde el fantasma,
	 * cuya posición se encuetra dentro de <code>estadoInicial</code>, hasta
	 * Pacman, que se encuentra en <code>estadoFinal</code>.
	 * @return Una lista con la secuencia de movimientos que Sombra debe
	 *         ejecutar para llegar hasta PacMan.
     */
	@Override
    public LinkedList<Movimiento> resuelveAlgoritmo(Estado estadoInicial, Estado estadoFinal){
		// TODO:
		// Reemplaza este código para que ejecute el algoritmo A* desde
		// estadoInicial hasta estadoFinal y genere la lista de acciones
		pintaTrayectoria(Color.BLACK);
		inicializa(estadoInicial, estadoFinal);
		while(nodoSolucion == null){
			expandeNodoSiguiente();
		}
		pintaTrayectoria(Color.PURPLE);
		return generaTrayectoria();
		
    }
	
}
