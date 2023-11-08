package ed.estructuras.lineales;
public class Polinomio{

  private Monomio primero;
  private Monomio segundo;
  private Monomio tercero;
  private Monomio cuarto;
  private Monomio quinto;

  private Polinomio uno;
  private Polinomio fin;

  private ListaDoblementeLigada<Monomio> list = new ListaDoblementeLigada<>();

  public Polinomio(Monomio a, Monomio b){
    primero = a;
    segundo = b;
    tercero = null;
    cuarto = null;
    quinto = null;
    //uno = this(primero, segundo, tercero, cuarto, quinto);
  }

  public Polinomio(Monomio a, Monomio b, Monomio c){
    primero = a;
    segundo = b;
    tercero = c;
    cuarto = null;
    quinto = null;
    //uno = this(primero, segundo, tercero, cuarto, quinto);
  }

  public Polinomio(Monomio a, Monomio b, Monomio c, Monomio d){
    primero = a;
    segundo = b;
    tercero = c;
    cuarto = d;
    quinto = null;
    //uno = this(primero, segundo, tercero, cuarto, quinto);
  }

  public Polinomio(Monomio a, Monomio b, Monomio c, Monomio d, Monomio e){
    primero = a;
    segundo = b;
    tercero = c;
    cuarto = d;
    quinto = e;
    //uno = this(primero, segundo, tercero, cuarto, quinto);
  }

  public Polinomio suma(Polinomio p){
    return null;
  }

  public Polinomio multiplica(Polinomio p){
    return null;
  }

  public void simplifica(){

  }

  public Monomio getMonomio(){
    return null;
  }

  public String toString(){
    return null;
  }
}
