package ed.estructuras.lineales;
public class Monomio{

  private double con;
  private int[] exp = new int[26];
  private String varexp;
  private String mon;
  private ListaDoblementeLigada<Monomio> list = new ListaDoblementeLigada<>();

  public Monomio(double a, char[] b, int[] c){
    if(b.length == c.length){
      con = a;
      for(int i=0; i<c.length; i++){
        exp[b[i] - 'A'] += c[i];
        varexp = varexp + b[i] + "^" + c[i];
      }
      mon = con + varexp;
    }else throw new IllegalArgumentException();
  }

  public Monomio(double a, int[] c){
    if(c.length == 26){
      con = a;
      for(int i=0; i<c.length; i++){
        exp[i] = c[i];
      }
    }
  }

  public Monomio(){
    con = 0;
    exp = null;
  }

  // public void guardar(){
  //   list.add(new Monomio());
  // }

  public Monomio suma(Monomio m){
    boolean pro = true;
    for(int i=0; i<m.exp.length; i++){
      if(this.exp[i] != m.exp[i]) pro = false;
    }
    if(pro = true){
      return new Monomio(m.con + this.con, m.exp);
    }else throw new IllegalArgumentException();
  }

  public String toString(){
    return mon;
  }
//MonomioTest.java
}
