//Sinencio Granados Dante Jusepee
/**
 * Clase que contiene métodos que ejemplifican el uso de ciclos.
 */
public class Ciclos {

  /**
   * Constructor por omisión que no hace nada.
   */
  public Ciclos(){}

  /**
   * Regresa una cadena con una piramide de n pisos.
   * @param n El número de pisos.
   * @return String con la piramide.
   */
  public static String piramide(int n){
    String s = " ";
    int m = 0;
    int p = 0;
    String nivel = "";
    while(n > m){
      while (m >= p){
        s = s + "*";
        p++;
        nivel = nivel + s + "\n";
      }
      m++;
    }
    return nivel;
  }
  
  /**
   * Imprime los números pares de 0 a n.
   * @param n El límite para calcular pares.
   */
  public static void pares(int n){
    int par;
    if(n > 0){
      do{
        if(n%2 == 0){
          par = n;
          n--;
        }else{
          n--;
        }
      }
      while(n > 0);
    }
  }
  
  /**
   * Calcula el promedio de 5,10,15,20,25
   * @return El promedio.
   */
  public static double promedio(){
    int n = 5;
    int m = 10;
    int o = 15;
    int p = 20;
    int q = 25;
    int promedio = (n + m + o + p + q)/5;
    return promedio;
    
  }
  
}