/**
 * Clase para trabajar con puntos en plano Cartesiano
 * @author Amparo L&oacute;pez  Gaona
 * @version 2da. ed.
 */
public class Punto {            
                            //    Atributos
   private double x;      // Coordenada x
   private double y;      // Coordenada y
   private String etiqueta;

  /**
   * M&eacute;todo para asignar valor a la coordenada x del punto.
   * @param nuevaX - nuevo valor para la coordenada x.
   */
  public void asignarX(double nuevaX) {   
    x = nuevaX; 
  }

  /**
   * M&eacute;todo para asignar valor a la coordenada y del punto.
   * @param nuevaY - nuevo valor para la coordenada y.
   */
  public void asignarY(double nuevaY) {   
    y = nuevaY; 
  }

  /**
   * M&eacute;todo para obtener el valor de la coordenada x del punto.
   * @return double - la coordenada x del punto.
   */  
  public double obtenerX () { 
    return x; 
  }

  /**
   * M&eacute;todo para obtener el valor de la coordenada y del punto.
   * @return double - la coordenada y del punto.
   */  
  public double obtenerY () { 
    return y; 
  } 

  /**
   * M&eacute;todo para asignar los valores de las coordenas del punto pasado como
   * parametro al punto que lo llama.
   * @param p --  Punto que se va a asignar
   */
  public void asignarPunto(Punto p) {
    x = p.x;
    y = p.y;
  }

  /**
   * M&eacute;todo para desplazar un punto.
   * @param deltaX - desplazamiento en el eje de las Xs.
   * @param deltaY - desplazamiento en el eje de las Ys.
   */  
  public void desplazar (double deltaX, double deltaY) {
    x += deltaX;
    y += deltaY;
  }

  /**
   *  Metodo para calcular la distancia entre dos puntos usando la formula
   *  ((x2-x1)^2 + (y2-y1)^2)^1/2
   *  @param p - punto respecto al que se quiere determinar la distancia.
   *  @return double - distancia entre los dos puntos.
   */
  public double distancia (Punto p) {
    return Math.sqrt((x-p.obtenerX())*(x-p.obtenerX()) + (y-p.obtenerY())*(y-p.obtenerY()));
  }

  /**
   * M&eacute;todo para determinar si tres puntos est&aacute;n alineados verificando que se cumpla la
   * igualdad: (y-y1)*(x2-x1) = (y2-y1)*(x -x1)
   * @param p1 - Punto que junto con p2 y el que llama se verifica si est&aacute;n alineados
   * @param p2 - Punto que junto con p1 y el que llama se verifica si est&aacute;n alineados
   * se verificara si est&aacute;n alineados.
   * @return true si est&aacute;n alineados, false en otro caso.
   */
  public boolean estanAlineados (Punto p1, Punto p2) {
    return (y - p1.y)*(p2.x - p1.x) == (p2.y - p1.y)*(x -p1.x);
  }

  /**
   * Constructor de un punto, crea el punto (0,0)
   */
  public Punto () { 
    x = y = 0;
    etiqueta = "P";
  }

  /**
   * Constructor de un punto a partir de dos n&uacute;meros enteros que
   * representan las coordenadas del nuevo punto.
   * @param x - coordenada x del nuevo punto.
   * @param y - coordenada y del nuevo punto.
   * @param etiqueta - La etiqueta del punto.
   */
  public Punto (double x, double y,String etiqueta) { 
    this.x = x; 
    this.y = y; 
    this.etiqueta = etiqueta;
  }

  /**
   * Constructor de un punto a partir de dos n&uacute;meros enteros que
   * representan las coordenadas del nuevo punto.
   * @param x - coordenada x del nuevo punto.
   * @param y - coordenada y del nuevo punto.
   */
  public Punto (double x, double y) { 
    this.x = x; 
    this.y = y; 
    this.etiqueta = "P";
  }


  /**
   * Constructor de un punto a partir de otro punto.
   * @param p - punto a partir del cual se creara el nuevo.
   */
  public Punto (Punto p) { 
    this(p.x, p.y, p.etiqueta); 
  }
  
  public String getEtiqueta(){
    return etiqueta;
  }

  /** 
   * M&eacute;todo para determinar si dos puntos son iguales.
   * @param p - punto contra el cual se va a comparar
   * @return boolean - true si son iguales y false en otro caso
   */
  public boolean equals (Object p) {
    Punto pto = (Punto)p;
    return x == pto.obtenerX() && y == pto.obtenerY();
  }

  /** 
   * M&eacute;todo para convertir un Punto a cadena de caracteres 
   * @return String -- el punto en formato de cadena
   */
  public String toString() {
    return "(" + x + "," + y + ")";
  }
  
}
