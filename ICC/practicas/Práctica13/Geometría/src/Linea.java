/**
 *Clase para crear l�neas rectas en el plano Cartesiano
 * Objetivo: ilustrar la relaci�n de agregaci�n entre objetos.
 * @author  Amparo L�pez Gaona
 * @see     Punto
 * @version 2da. ed.
 */
public class Linea {
                        //Estructura
  private Punto p1;
  private Punto p2;

  /**
   * Constructor por omisi�n.
   * Crea la linea que pasa por los puntos (0,0) y (1,1)
   */
  Linea () { 
      this(new Punto(0,0), new Punto(1,1));  
  }

  /**
   * Constructor de una l�nea a partir de dos puntos
   * @param p1Ini -- punto de origen
   * @param p2Ini -- segundo punto
   */
  Linea (Punto p1Ini, Punto p2Ini) {
    p1 = new Punto(p1Ini); 
    p2 = new Punto(p2Ini);
  }

  /**
   * Constructor de copia
   * @param recta -- L�nea que se toma para crear una nueva
   */
  Linea (Linea recta) { 
      this(recta.p1, recta.p2);
  }

  /**
   * Determina la ecuaci�n de una recta que pasa por 2 puntos
   * @return String -- La ecuaci�n de la recta
   */
  public String ecuacion() {
    if (p1.obtenerY() == p2.obtenerY())
      return "y = " + p1.obtenerY();  //Recta paralela al eje x.

    if (p1.obtenerX() == p2.obtenerX())
      return "x = " + p1.obtenerX();  //Recta paralela al eje y.

    double m = pendiente();
    double b = p1.obtenerY() - p1.obtenerX()*m;
    return (b >= 0) ? "y = "+ m +"x + "+b
                   : "y = "+ m +"x "+b;
  }

  /**
   * Devuelve la pendiente de una recta.
   * @return double - La pendiente de la recta.
   */
  public double pendiente() {
    double divisor = p2.obtenerX() - p1.obtenerX();
    return (divisor != 0) ? (p2.obtenerY() - p1.obtenerY())/divisor
	  : Double.POSITIVE_INFINITY;            //Recta paralela al eje Y
  }

  /**
   * Devuelve la ordenada al origen 
   * @return la ordenada al origen.
   */
  public double ordenada() {    
      return p1.obtenerX() == p2.obtenerX()? Double.POSITIVE_INFINITY
	     : p1.obtenerY() - pendiente()*p1.obtenerX();
  }

  /**
   * Determina si un punto pertenece a la recta
   * @param p - el punto a verificar si esta contenido.
   * @return un booleano que nos dice si el punto p está contenido.
   **/
  public boolean contiene(Punto p) {  
      return p.estanAlineados(p1,p2);
  }

  /**
   * Determina si dos l�neas son la misma 
   * @param linea1 -- L�nea con la que se comparar� la l�nea original
   * @return true si son la misma l�nea y false en otro caso
   */
  public boolean equals(Linea linea1) {  
    return pendiente() == linea1.pendiente() && ordenada() == linea1.ordenada();
  }

  /**
   * Determina si dos l�neas son paralelas 
   * @param linea1 -- L�nea con la que se comparar� la l�nea original
   * @return true si ambas l�neas son paralelas y false en otro caso
   */
  public boolean esParalelaA(Linea linea1) {  
    return pendiente () == linea1.pendiente();
  }

  /**
   * Determina si dos l�neas son perpendiculares 
   * @param linea1 -- L�nea con la que se comparar� la l�nea original
   * @return true si ambas l�neas son paralelas y false en otro caso
   */
  public boolean esPerpendicularA(Linea linea1) {  
    double m = pendiente();
    double m1 = linea1.pendiente();
    final double INFINITO = Double.POSITIVE_INFINITY;
    boolean perpendicular;

    perpendicular = (m == 0 && m1 == INFINITO) || (m1 == 0 && m == INFINITO);
    if (!perpendicular) {
      perpendicular = m == (-1 / m1);
    }
    return perpendicular;
  }

  /**
   * Determina el punto de intersecci�n entre dos l�neas 
   * @param linea1 - la segunda l�nea
   * @return Punto - punto de intesercci�n
   */
  public Punto interseccion(Linea linea1) { 
      double nuevaX, nuevaY;

      if (esParalelaA(linea1)) {
	  nuevaX = nuevaY = (int)Double.POSITIVE_INFINITY;
      } else {
	  nuevaX = (ordenada()- linea1.ordenada())/(linea1.pendiente() - pendiente());
	  nuevaY = (pendiente()*nuevaX + ordenada());
      }
      return new Punto(nuevaX, nuevaY);
  }

  /**
   * M�todo para escribir la reperesentacion de la linea usando
   * sus dos puntos
   */
  public String toString() {
  	//	return ecuacion();
  	return "Recta que pasa por "+p1+" y "+p2;
  }

  public Punto getP1(){
    return p1;
  }


  public Punto getP2(){
    return p2;
  }

}
