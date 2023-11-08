package recocido;

import static java.lang.System.out;    

/**
Clase para ejecutar un proceso de optimización usando recocido simulado.
@author Benjamin Torres Saavedra
@author Verónica E. Arriola
@version 0.1
*/
public class Main{
  public static void main(String []args){
    int interacciones=100;
    RecocidoSimulado recocido=new RecocidoSimulado(null);
    Solucion s;
    for(int i=0; i<interacciones; i++){
      s=recocido.ejecutar();        
    }
    out.println(s); 
  }
}
