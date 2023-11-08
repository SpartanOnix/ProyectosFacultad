public class Materia{
  private String nombre;
  private String clave;
  private int creditos;
  
  public Materia(String n, String c, int cr){
    nombre = n;
    clave = c;
    creditos = cr;
  }
  
  public String toString(){
    String s = "\n" + "Datos de la materia: " + nombre + "\n  -Clave: " + clave + "\n  -Creditos: " + creditos;
    return s;
  }
}