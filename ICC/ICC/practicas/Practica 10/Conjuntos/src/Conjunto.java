/**
 *  
 *  Clase que implementa la representacion de un Conjunto de numeros enteros
 *  cuyo valor esta entre 1 y 100.
 *
 *  <p>El Conjunto se representa como un arreglo de valores booleanos, donde si elementos[i]
 *  tiene valor true, significa que el entero i esta en el conjunto y si es false
 *  significa que el entero i no esta en el conjunto.
 *
 *  @author Sinencio Granados Dante
 *
 */
public class Conjunto {
    
    /**
     *  Arreglo que nos sirve para determinar que elementos
     *  pertenecen al Conjunto.
     */
  private boolean [] elementos;
    
    /**
     *  Constructor que inicializa un Conjunto vacio.
     *  Es decir, un Conjunto sin ningun elemento.
     */
    public Conjunto() {
      elementos = new boolean[100];
      
    }
    
    /**
     *  Metodo que inicializa un Conjunto que contenga
     *  los enteros pasados como parametro.
     *  El arreglo pasado como parametro contiene, solamente,
     *  enteros cuyo valor esta entre 1 y 100.
     *  @param elementosIniciales Elementos que debera de contener el nuevo Conjunto.
     */
    public Conjunto(int[] elementosIniciales){
      elementos = new boolean[100];
      for(int i=0; 1<elementosIniciales.length; i++){
        if(elementosIniciales[i]>0 && elementosIniciales[i]<101){
          elementos[elementosIniciales[i]-1] = true;
        }
      }

    }
    
    /**
     *  Devuelve un Conjunto que contiene la union de el Conjunto
     *  que manda a llamar al metodo con el Conjunto c.
     *  @param c Conjunto que se va a unir.
     *  @return Nuevo Conjunto resultado de la union de ambos Conjuntos.
     */
    public Conjunto union(Conjunto c){
      Conjunto union = new Conjunto();
      for(int i=1; i<101; i++){
        if(elementos[i-1] == c.pertenece(i)){
          union.elimina(i);
        }else{
          union.introduce(i);
        }
      }
      return union;
     
    }
    
    /**
     *  Devuelve un Conjunto que contiene la interseccion de el Conjunto
     *  que manda a llamar al metodo con el Conjunto c.
     *  @param c Conjunto que se va a unir.
     *  @return Nuevo Conjunto resultado de la interseccion de ambos Conjuntos.
     */
    public Conjunto interseccion(Conjunto c){
      Conjunto interseccion = new Conjunto();
      for(int i=1; i<101; i++){
        if(elementos[i-1] && c.pertenece(i)){
          interseccion.introduce(i);
        }
      }
      return interseccion;

    }
    
    /**
     *  Devuelve un Conjunto resultado de la diferencia entre el Conjunto
     *  que llama al metodo y el Conjunto c.
     *  @param c Conjunto cuyos elementos seran restados al Conjunto que llama al metodo.
     *  @return Resultado de la diferencia entre ambos Conjuntos.
     */
    public Conjunto diferencia(Conjunto c){
            Conjunto diferencia = new Conjunto();
      for(int i=1; i<101; i++){
        if(elementos[i-1] && c.pertenece(i)){
          diferencia.elimina(i);
        }
      }
      return diferencia;
    }
    
    /**
     *  Determina si el elemento pasado como parametro pertenece o no
     *  al Conjunto.
     *  @param elemento Elemento que sera buscado dentro del Conjunto.
     *  @return true - Si el elemento esta en el Conjunto. false - En otro caso.
     */
    public boolean pertenece(int elemento){
      int[] owo = {elemento};
      int[] uwu = new int[100];
      Conjunto n = new Conjunto(uwu);
      Conjunto n1 = new Conjunto(owo);
      if(n.interseccion(n1) == n1){
        return true;
      }else{
        return false;
      }
    }
    
    /**
     *  Metodo que introduce un nuevo elemento al Conjunto.
     *  @param elemento Elemento que sera introducido al Conjunto.
     */
    public void introduce(int elemento){
      int [] owo = {elemento};
      Conjunto n = new Conjunto(owo);
      Conjunto n1 = new Conjunto();
      Conjunto n2 = new Conjunto();
      n2 = n.union(n1);
    }    
    
    /**
     *  Metodo que elimina un elemento del Conjunto.
     *  @param elemento Elemento que sera eliminado del Conjunto.
     */
    public void elimina(int elemento){
      int [] owo = {elemento};
      Conjunto n = new Conjunto(owo);
      Conjunto n1 = new Conjunto();
      Conjunto n2 = new Conjunto();
      n2 = n.diferencia(n1);
    }    
    
    /**
     *  Metodo que determina si dos Conjuntos son o no iguales.
     *  @param c Conjunto que sera comparado.
     *  @return true - Si son iguales. false - En otro caso.
     */
    public boolean equals(Conjunto c){
      for(int i=0; i<elementos.length; i++){
        if(elementos[i] == c.pertenece(i)){
          return true;
        }else{
          return false;
        }
      }
      return false;
    }
    
    /**
     *  Metodo que devuelve la representacion en cadena del Conjunto.
     *  La cadena resultante tiene un formato como el que sigue:
     *  {4, 6, 80, 99}
     *  @return Representacion en cadena del Conjunto.
     */
    public String toString(){
      String s = "{";
      for(int i=1; i<101; i++){
        if(elementos[i]){
          s = s + i + ", ";
        }
      }
      s = s + "}";
      return s;

    }
    
}
