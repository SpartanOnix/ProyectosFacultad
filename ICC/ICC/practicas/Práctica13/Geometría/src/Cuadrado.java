/**
 * Clase para crear cuadrados en un plano cartesiano.
 * @author Miguel Mendoza.
 */
public class Cuadrado extends Figura implements FiguraI {

 private int largo;
 
 /**
  * Constructor por omisión.
  * Construye un cuadrado en los puntos (-3,3),(3,3),(-3,-3),(3,-3).
  */
 public Cuadrado(){
  super.lineas = new Linea[4];
  Punto a = new Punto(-3,3);
  Punto b = new Punto(3,3);
  Punto c = new Punto(-3,-3);
  Punto d = new Punto(3,-3);
  
  Linea ln1 = new Linea(a,b);
  Linea ln2 = new Linea(b,c);
  Linea ln3 = new Linea(c,d);
  Linea ln4 = new Linea(d,a);
  
  lineas[0] = ln1;
  lineas[1] = ln2;
  lineas[2] = ln3;
  lineas[3] = ln4;
 }
 
 /**
  * Construye un Cuadrado con el centro en el Punto dado y del largo indicado.
  * @param centro El centro del Cuadrado.
  * @param largo El largo del Cuadrado.
  * @throws ValorInvalidoExcepcion si el largo dado es negativo.
  */
 public Cuadrado(Punto p1, Punto p2, Punto p3, Punto p4) throws ValorInvalidoExcepcion {
//   throw new ValorInvalidoExcepcion("Mete un valor valido para el cuadrado");
    super.lineas = new Linea[4];
  Punto a = new Punto(p1);
  Punto b = new Punto(p2);
  Punto c = new Punto(p3);
  Punto d = new Punto(p4);
  
  Linea ln1 = new Linea(a,b);
  Linea ln2 = new Linea(b,c);
  Linea ln3 = new Linea(c,d);
  Linea ln4 = new Linea(d,a);
  
  lineas[0] = ln1;
  lineas[1] = ln2;
  lineas[2] = ln3;
  lineas[3] = ln4;
 }

 /**
  * Constructor copia, construye un cuadrado a partir de otro.
  * @param c El Cuadrado a copiar.
  */
 public Cuadrado(Cuadrado cu){
   super.lineas = new Linea[4];
  Punto a = new Punto(cu.getL1().getP1().obtenerX(),cu.getL1().getP1().obtenerY());
  Punto b = new Punto(cu.getL2().getP1().obtenerX(),cu.getL2().getP1().obtenerY());
  Punto c = new Punto(cu.getL3().getP1().obtenerX(),cu.getL3().getP1().obtenerY());
  Punto d = new Punto(cu.getL4().getP1().obtenerX(),cu.getL4().getP1().obtenerX());
  
  Linea ln1 = new Linea(a,b);
  Linea ln2 = new Linea(b,c);
  Linea ln3 = new Linea(c,d);
  Linea ln4 = new Linea(a,d);
  
  lineas[0] = ln1;
  lineas[1] = ln2;
  lineas[2] = ln3;
  lineas[3] = ln4;
  
 }

 /**
  * Obtiene el perímetro del cuandrado.
  * @return El perímetro.
  */
 @Override
 public double getPerimetro(){
   double a = lineas[0].getP1().distancia(lineas[0].getP2());
   double b = lineas[1].getP1().distancia(lineas[1].getP2());
   double c = lineas[2].getP1().distancia(lineas[2].getP2());
   double d = lineas[3].getP1().distancia(lineas[2].getP2());
   double perim = a + b + c + d;
   return perim;
 }

 //aquí va Método getArea()
   public double getArea(){
   double a = lineas[0].getP1().distancia(lineas[0].getP2());
   double b = lineas[1].getP1().distancia(lineas[1].getP2());
   double c = lineas[2].getP1().distancia(lineas[2].getP2());
   double area = Math.sqrt((((a+b+c)/(2))*(((a+b+c)/(2))-a)*(((a+b+c)/(2))-b)*(((a+b+c)/(2))-c)));
   return area;
  
  }


 //Aqúí va Método rotar90()
   public void rotar90() throws UnsupportedOperationException{
     throw new UnsupportedOperationException("No tiene sentido rotar 90� el cuadrado.");
   }

 //Rotar 180
 public void rotar180() throws UnsupportedOperationException{
   throw new UnsupportedOperationException("No tiene sentido rotar 180� el cuadrado.");
  
 }

 /**
  * Mueve el cuadrado en el plano.
  * @param deltaX El desplazamiento en el eje X.
  * @param deltaY El desplazamiento en el eje Y.
  * @throws DesplazamientoInvalido si los deltas son cero.
  */
 @Override
 public void mover(double deltaX, double deltaY){
   double x1 = lineas[0].getP1().obtenerX();
   double y1 = lineas[0].getP1().obtenerY();
   double x2 = lineas[0].getP2().obtenerX();
   double y2 = lineas[0].getP2().obtenerY();
   double x3 = lineas[1].getP2().obtenerX();
   double y3 = lineas[1].getP2().obtenerY();
   double x4 = lineas[2].getP2().obtenerX();
   double y4 = lineas[2].getP2().obtenerY();
    
   double x1r = deltaX;
   double y1r = deltaY;
   double x2r = deltaX;
   double y2r = deltaY;
   double x3r = deltaX;
   double y3r = deltaY;
   double x4r = deltaX;
   double y4r = deltaY;
     
    lineas[0].getP1().asignarX(x1r);
    lineas[0].getP1().asignarY(y1r);
    lineas[0].getP2().asignarX(x2r);
    lineas[0].getP2().asignarY(y2r);

    lineas[1].getP1().asignarX(x1r);
    lineas[1].getP1().asignarY(y1r);
    lineas[1].getP2().asignarX(x3r);
    lineas[1].getP2().asignarY(y3r);
    
    lineas[2].getP1().asignarX(x2r);
    lineas[2].getP1().asignarY(y2r);
    lineas[2].getP2().asignarX(x3r);
    lineas[2].getP2().asignarY(y3r);
    
    lineas[3].getP1().asignarX(x3r);
    lineas[3].getP1().asignarY(y3r);
    lineas[3].getP2().asignarX(x4r);
    lineas[3].getP2().asignarY(y4r);
  
 }

 /**
  * Nos dice si este cuadrado es igual a otro.
  * @param f La FiguraI a comparar.
  * @return true si son iguales.
  */
 public boolean equals(FiguraI f){ 
   if( ! (f instanceof Cuadrado))
  return false;
     Cuadrado t = (Cuadrado)f;
     return lineas[0].equals(t.getL1()) && lineas[1].equals(t.getL2()) && lineas[2].equals(t.getL3()) && lineas[3].equals(t.getL4());
  
 }

 /**
  * Devuelve la linea 1.
  * @return Linea 1 
  */
 public Linea getL1(){
   return lineas[0];
 }

 /**
  * Devuelve la linea .
  * @return Linea 2 
  */
 public Linea getL2(){
   return lineas[1];
 }

 /**
  * Devuelve la linea 3.
  * @return Linea 3. 
  */
 public Linea getL3(){
   return lineas[2];
 }

 /**
  * Devuelve la linea 4.
  * @return Linea 4. 
  */
 public Linea getL4(){
   return lineas[3];
 }

 /**
  * Obtiene el largo de un lado del cuadrado.
  * @return largo El largo del cuadrado.
  */
 public double getLargo(){
   Punto a = this.getL1().getP1();
   Punto b = this.getL2().getP2();
   double l = a.distancia(b);
   return l;
 }

 /**
  * Regresa los datos del triángulo.
  */
 @Override
 public String toString(){
   String inf = "";
   inf += "Info. Cuadrado:\n";
   inf += "Area: " + getArea()+ "\nPerimetro: " + getPerimetro() + "\nlargo: " + largo + "\nCoordenadas: \n P1: " + this.getL1().getP1() + "\n P2: " +  this.getL2().getP1() + "\n P3: " + this.getL3().getP1() + "\n P4: " + this.getL4().getP1();
   return inf;
 }

}