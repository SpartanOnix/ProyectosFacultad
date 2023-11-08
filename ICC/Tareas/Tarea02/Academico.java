public class Academico extends Trabajador{
  protected Materia materia1;
  protected Materia materia2;
  protected String grado;
  
  public Academico(String s, double d, String g){
    super(s,d);
    grado = g;
  }
  
  public String toString(){
    String s = super.toString() + " , " + materia1.toString();
    return s;
  }
}