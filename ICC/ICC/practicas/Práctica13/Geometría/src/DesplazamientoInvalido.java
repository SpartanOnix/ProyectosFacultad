/**
 * Es lanzada cuando los valores delta son cero.
 */
public class DesplazamientoInvalido extends IllegalArgumentException {
	
	/**
	 * Constructor por omisión.
	 */
	public DesplazamientoInvalido(){
		super();
	}

	/**
	 * Constructor que recibe un mensaje.
	 * @param mensaje El mensaje a mostrar si se lanza la excepción.
	 */
	public DesplazamientoInvalido(String mensaje){
		super(mensaje);
	}
}