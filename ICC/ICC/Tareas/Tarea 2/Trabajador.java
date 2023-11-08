public class Trabajador{
  protected String noTrabajador;
  protected double sueldo;
  
  public Trabajador(String s, double d){
    noTrabajador = s;
    sueldo = d;
  }
  
  public String toString(){
    String s = noTrabajador + "\n" + sueldo;
    return s;
  }
}