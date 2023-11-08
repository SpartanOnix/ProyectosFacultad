/**
 * Es lanzada cuando se reciben unos puntos no válidos para alguna figura geométrica.
 */
public class ValorInvalidoExcepcion extends NumberFormatException{
	
	/**
	 * Constructor por omisión.
	 */
	public ValorInvalidoExcepcion(){
		super();
	}

	/**
	 * Constructor que recibe un mensaje para mostrar cuando la excepción es lanzada.
	 * @param mensaje El mensaje a imprimir.
	 */
	public ValorInvalidoExcepcion(String mensaje){
		super(mensaje);
	}
}