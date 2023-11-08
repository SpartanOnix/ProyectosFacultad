package ed.estructuras.lineales;
public class Arreglo{

  private int [] elem;
  private int [] dim;

  public Arreglo(int [] nDimenciones){
    dim = nDimenciones;
    int tam = 1;
    for(int k=0; k<dim.length; k++){
      tam *= dim[k];
    }
  //  for (int d : dim){
  //    tam *= d;
  //  }

    elem = new int [tam];

  }

  public int obtenerElemento(int [] indices){
    if(verValidez(indices) == true) throw new IndexOutOfBoundsException();
    int t = obtenerIndice(indices);
    return elem[t];

  }

  public void almacenarElemento(int [] indices, int eleme){
    if(verValidez(indices) == true) throw new IndexOutOfBoundsException();
    int t = obtenerIndice(indices);
    elem[t] = eleme;

  }

  public int obtenerIndice(int [] indices){
    if(verValidez(indices) == true) throw new IndexOutOfBoundsException();
    int i = 0;
    for(int k=0; k<=dim.length-1; k++){
      int d = 1;
      for(int j=k+1; j<dim.length; j++){
        d *= dim[j];
      }
      i += indices[k]*d;
    }
    return i;

  }

  private boolean verValidez(int [] indices){
    boolean val = false;
    if(indices.length != dim.length) return true;
    else{
    for(int k=0; k<indices.length; k++){
      if(indices[k] < 0 || indices[k] > dim[k]-1 ) {
        val = true;
        k = indices.length;
      }
    }
  }
    return val;
  }

}
