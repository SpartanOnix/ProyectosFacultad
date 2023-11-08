 /**
  * Clase abstracta que modela los comportamientos más básicos de una figura.
  */
 public abstract class Figura {
 	
 	/*Arreglo de Líneas, cada figura usa este atributo como le convenga*/
 	protected Linea [] lineas;

 	/**
 	 * Regresa las líneas.
 	 * @return las líneas de la figura.
 	 */
 	public final Linea [] getLineas(){
 		return lineas;
 	}

 	/**
 	 * El perímetro depende de cada Figura.
 	 * @return el perímetro de alguna figura.
 	 */
 	public abstract double getPerimetro();

 	/**
 	 * El área depende de cada Figura.
 	 * @return el área de alguna figura.
 	 */
 	public abstract double getArea();

 }