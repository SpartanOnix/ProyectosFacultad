public class Profesor extends Academico{
  private Ayudante[] candidatos;
  
  public Profesor(String noTr, double sueldo, String grado){
    super(noTr, sueldo, grado);
    candidatos = new Ayudante[10];
  }
  
  public void asignarAyudante(int n){
    Ayudante elegido = candidatos[n];
    elegido.asignarMateria(super.materia1);
  }
  
  public void asignarMateria(Materia m){
    if(super.materia1 != null){
      super.materia2 = m;
    }else{
      super.materia1 = m;
    }
  }
  
  public void candidato(Ayudante candidato){
    for(int i=0; i<candidatos.length; i++){
      if(candidatos[i] == null){
        candidatos[i] = candidato;
        return;
      }
    }
  }
  
  public String toString(){
    String s = super.toString() + "\n";
    return s;
  }
}