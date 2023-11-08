//Sinencio Granados Dante Jusepee

/**
 * Clase para registrar tu nombre
 * @param - Tu nombre completo, debes de meter por lo menos 3 strings
 */
public class Nombre{
  
  public static void main(String [] pps){
    String [] s = new String[pps.length];
    if(pps.length < 3){
      System.out.println("Debes ingresar al menos tres cadenas para el nombre");
    }else{
      for(int i=0; i<pps.length; i++){
        s[i] = pps[i];
      }
      System.out.print("Tu nombre completo es: " + s[pps.length-2] + " " + s[pps.length-1] + " ");
      for(int i=0; i<pps.length-2; i++){
        System.out.print(s[i] + " ");
      }
      System.out.print("\nTu nombre es: ");
      for(int i=0; i<pps.length-2; i++){
        System.out.print(s[i] + " ");
      }
    } 
  }
  
}