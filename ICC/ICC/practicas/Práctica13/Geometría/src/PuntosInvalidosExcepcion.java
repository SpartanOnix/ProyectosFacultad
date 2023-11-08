import java.lang.IllegalArgumentException;


/**
 * Es lanzada cuando se reciben unos puntos no válidos para alguna figura geométrica.
 */
public class PuntosInvalidosExcepcion extends IllegalArgumentException{
	
	/**
	 * Constructor por omisión.
	 */
	public PuntosInvalidosExcepcion(){
		super();
	}

	/**
	 * Constructor que recibe un mensaje para mostrar cuando la excepción es lanzada.
	 * @param mensaje El mensaje a imprimir.
	 */
	public PuntosInvalidosExcepcion(String mensaje){
		super(mensaje);
	}
}