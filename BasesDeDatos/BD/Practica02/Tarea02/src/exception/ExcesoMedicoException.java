package exception;

/**
 * exception.ExcesoMedicoException.java
 * Clase que implementa escepcion para cuando no se pueda guardar a otro medico  
 * @author lalo
 * @version Noviembre 2016
 */
public class ExcesoMedicoException extends Exception{
	
	public ExcesoMedicoException(String message){
		super(message);
	}

}

