public class Administrador extends Trabajador{
  
  public Administrador(String noTr, double s){
    super(noTr, s);
  }
  
  public void asignarMateria(Profesor p, Materia m){
    p.asignarMateria(m);
  }
}