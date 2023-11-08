//Dante Jusepee Sinencio Granados
/**
 * Clase para crear triángulos en un plano cartesiano.
 * @author Miguel Mendoza.
 */
public class Triangulo {
 
  /*Líneas que forman el perímetro del triángulo*/
  private Linea linea1;
  private Linea linea2;
  private Linea linea3;

  /**
    * Constructor por omisión.
    * Construye un triángulo en los puntos (0,0),(5,0),(2.5,2.5).
    */
  public Triangulo(){
    Punto primero = new Punto(0,0);
    Punto segundo = new Punto(5,0);
    Punto tercero = new Punto(2.5,2.5);
    linea1 = new Linea(primero,segundo);
    linea2 = new Linea(segundo,tercero);
    linea3 = new Linea(tercero,primero);
      
  }

  /**
   * Construye un triángulo dados 3 puntos del plano.
   * @param p1 El punto 1
   * @param p2 El punto 2
   * @param p3 El punto 3
   */
  public Triangulo(Punto p1, Punto p2, Punto p3){
    Triangulo one = new Triangulo(p1, p2, p3);
  }

  /**
   * Constructor copia. Construye un triángulo a partir de otro.
   * @param t El triángulo del cual se creará uno nuevo.
   */
  public Triangulo(Triangulo t){
    Triangulo fake = new Triangulo(t);
  
  }

  /**
   * Obtiene el perímetro del triángulo.
   * @return El perímetro. 
   */
  public double getPerimetro(){
   Punto P1linea1 = linea1.getP1();
   Punto P2linea1 = linea1.getP2();
   Punto P2linea2 = linea2.getP2();
   Punto P2linea3 = linea3.getP2();
   double Dlinea1 = P1linea1.distancia(P2linea1);
   double Dlinea2 = P2linea1.distancia(P2linea2);
   double Dlinea3 = P2linea2.distancia(P2linea3);
   double perimetro = Dlinea1 + Dlinea2 + Dlinea3;
   return perimetro;
   
  }
 

  /**
   * Obtiene el área del triángulo. Fórmula de Herón.
   * @return El área del triángulo.
   */
  public double getArea(){
   Punto P1linea1 = linea1.getP1();
   Punto P2linea1 = linea1.getP2();
   Punto P2linea2 = linea2.getP2();
   Punto P2linea3 = linea3.getP2();
   double Dlinea1 = P1linea1.distancia(P2linea1);
   double Dlinea2 = P2linea1.distancia(P2linea2);
   double Dlinea3 = P2linea2.distancia(P2linea3);
   double a = Dlinea1;
   double b = Dlinea2;
   double c = Dlinea3;
   double area = Math.sqrt((((a+b+c)/(2))*(((a+b+c)/(2))-a)*(((a+b+c)/(2))-b)*(((a+b+c)/(2))-c)));
   return area;
    
  }

  /**
   * Mueve el triángulo en el plano.
   * @param deltaX El desplazamiento en el eje X.
   * @param deltaY El desplazamiento en el eje Y.
   */
  public void moverTriangulo(double deltaX, double deltaY){
    
    linea1.getP1().desplazar(deltaX,deltaY);
    linea1.getP2().desplazar(deltaX,deltaY);
    
    linea2.getP1().desplazar(deltaX,deltaY);
    linea2.getP2().desplazar(deltaX,deltaY);
    
    linea3.getP1().desplazar(deltaX,deltaY);
    linea3.getP2().desplazar(deltaX,deltaY);
    
  }

  /**
   * Rota el triangulo 90�
   */
  public void rotarTriangulo90(){
    double x1 = linea1.getP1().obtenerX();
    double y1 = linea1.getP1().obtenerY();
    double x2 = linea1.getP2().obtenerX();
    double y2 = linea1.getP2().obtenerY();
    double x3 = linea2.getP2().obtenerX();
    double y3 = linea2.getP2().obtenerY();
    
     double x1r = x1 * (-1);
     double y1r = y1 * (1);
     double x2r = x2 * (-1);
     double y2r = y2 * (1);
     double x3r = x3 * (-1);
     double y3r = y3 * (1);
     
    linea1.getP1().asignarX(y1r);
    linea1.getP1().asignarY(x1r);
    linea1.getP2().asignarX(y2r);
    linea1.getP2().asignarY(x2r);

    linea2.getP1().asignarX(y1r);
    linea2.getP1().asignarY(x1r);
    linea2.getP2().asignarX(y3r);
    linea2.getP2().asignarY(x3r);
    
    linea3.getP1().asignarX(y2r);
    linea3.getP1().asignarY(x2r);
    linea3.getP2().asignarX(y3r);
    linea3.getP2().asignarY(x3r);
    
  }
  /**
   * Rota el triángulo 180°.
   */
  public void rotarTriangulo180(){
    double x1 = linea1.getP1().obtenerX();
    double y1 = linea1.getP1().obtenerY();
    double x2 = linea1.getP2().obtenerX();
    double y2 = linea1.getP2().obtenerY();
    double x3 = linea2.getP2().obtenerX();
    double y3 = linea2.getP2().obtenerY();
    
     double x1r = x1 * (-1);
     double y1r = y1 * (-1);
     double x2r = x2 * (-1);
     double y2r = y2 * (-1);
     double x3r = x3 * (-1);
     double y3r = y3 * (-1);
     
    linea1.getP1().asignarX(x1r);
    linea1.getP1().asignarY(y1r);
    linea1.getP2().asignarX(x2r);
    linea1.getP2().asignarY(y2r);

    linea2.getP1().asignarX(x1r);
    linea2.getP1().asignarY(y1r);
    linea2.getP2().asignarX(x3r);
    linea2.getP2().asignarY(y3r);
    
    linea3.getP1().asignarX(x2r);
    linea3.getP1().asignarY(y2r);
    linea3.getP2().asignarX(x3r);
    linea3.getP2().asignarY(y3r);
    
  }

  /**
   * Rota el triangulo n grados.
   * @param grados Cuantos grados se rotará el triángulo.
   */
  public void rota(int grados){
    /*Convertir grados a radianes*/
    double radianes = Math.toRadians(grados);
    /*Similar los valores de la matriz de rotación*/
    /* | m1=cos(x)  m2=-sin(x)|
       |                      |
       | m3=sin(x)  m4=cos(x) | */
    double m1 = Math.cos(radianes);
    double m2 = -1*Math.sin(radianes);
    double m3 = -1*m2;
    double m4 = m1;
    /*Obtener las coordenadas originales de cada punto simulando la matriz de 2x3*/
    /*| x1  x2  x3|
      |           |
      | y1  y2  y3| */
    double x1 = linea1.getP1().obtenerX();
    double y1 = linea1.getP1().obtenerY();
    double x2 = linea1.getP2().obtenerX();
    double y2 = linea1.getP2().obtenerY();
    double x3 = linea2.getP2().obtenerX();
    double y3 = linea2.getP2().obtenerY();
    /*Multiplicamos ambas matrices*/
    double nx1 = m1*x1 + m2*y1;
    double ny1 = m3*x1 + m4*y1;
    double nx2 = m1*x2 + m2*y2;
    double ny2 = m3*x2 + m4*y2;
    double nx3 = m1*x3 + m2*y3;
    double ny3 = m3*x3 + m4*y3;
    
    /*Asignamos a cada punto sus nuevas coordenadas*/ 
    linea1.getP1().asignarX(nx1);
    linea1.getP1().asignarY(ny1);
    linea1.getP2().asignarX(nx2);
    linea1.getP2().asignarY(ny2);

    linea2.getP1().asignarX(nx1);
    linea2.getP1().asignarY(ny1);
    linea2.getP2().asignarX(nx3);
    linea2.getP2().asignarY(ny3);
    
    linea3.getP1().asignarX(nx2);
    linea3.getP1().asignarY(ny2);
    linea3.getP2().asignarX(nx3);
    linea3.getP2().asignarY(ny3);
  }
 
  /**
   * Nos dice si este triángulo es igual a otro.
   * @param t El triángulo a comparar.
   * @return true si son iguales.
   */
  public boolean equals(Triangulo t){
    Triangulo m = new Triangulo(linea1.getP1(), linea2.getP1(), linea3.getP1());
    if(linea1.getP1()==t.getL1().getP1() && linea2.getP1()==t.getL2().getP1() && linea3.getP1()==t.getL3().getP1()){
      return true;
    }else{
      return false;
    }

  }

  /**
   * Devuelve la linea 1.
   * @return Linea 1 
   */
  public Linea getL1(){
   return linea1;

  }

  /**
   * Devuelve la linea .
   * @return Linea 2 
   */
  public Linea getL2(){
    return linea2;
    
  }

  /**
   * Devuelve la linea 3.
   * @return Linea 3. 
   */
  public Linea getL3(){
    return linea3;
    
  }

  /**
   * Regresa los datos del triángulo (Coordenadas. área y perímetro).
   */
  public String toString(){
    Triangulo t2 = new Triangulo();
    return "Area: " + t2.getArea() + "\nPerimetro: " + t2.getPerimetro() + "\nCoordenadas: \n P1: " + t2.getL1().getP1() + "\n P2: " +  t2.getL2().getP1() + "\n P3: " + t2.getL3().getP1();

  }


}
