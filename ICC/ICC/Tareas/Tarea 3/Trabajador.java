public class Trabajador{
  protected String noTrabajador;
  protected double sueldo;
  
  public Trabajador(String s, double d){
    noTrabajador = s;
    sueldo = d;
  }
  
  public String toString(){
    String s = noTrabajador + "\n" + "  -Sueldo: " + sueldo;
    return s;
  }
}