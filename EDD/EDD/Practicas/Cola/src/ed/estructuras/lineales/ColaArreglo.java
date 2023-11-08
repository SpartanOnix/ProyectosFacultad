package ed.estructuras.lineales;
import ed.estructuras.ColeccionAbstracta;
import java.util.Iterator;
import java.util.Collection;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
*   Clase para crear una cola.
*   @author Dante Jusepee Sinencio Granados
*/
public class ColaArreglo<E> extends ColeccionAbstracta<E> implements ICola<E>{
  private E[] cola;
  private int rabo;
  private int cabeza;
  public static final int DEFAULT = 10;

  /**
  *   Construye una cola con un arreglo vacio de algun tipo y un tamaño inicial.
  *   @param a - Arreglo vacio de algun tipo especifico.
  *   @param tamInicial - Tamaño inicial para el arreglo.
  */
  public ColaArreglo (E[] a ,int tamInicial){
    if(tamInicial <= 0 || a.length != 0) throw new IllegalArgumentException();
    rabo = 0;
    cabeza = 0;
    cola = Arrays.copyOf(a, tamInicial);
    tam = 0;

  }

  /**
  *   Construye una cola con un arreglo vacio de algun tipo y un tamaño inicial de 10.
  *   @param a - Arreglo vacio de algun tipo especifico.
  */
  public ColaArreglo (E[] a){
    this (a , DEFAULT) ;

  }

  /**
   * Agrega un elemento al final de la cola.
   * @param e Referencia al elemento a agregar.
   */
  public void forma(E e){
    this.add(e);

  }

  /**
   * Devuelve el elemento al principio de la cola y lo elimina.
   * Devuelve <code>null</code> si está vacía.
   * @return Una referencia al elemento siguiente.
   */
  public E atiende(){
    if(this.isEmpty()) return null;
    E o = cola[cabeza];
    cabeza = (cabeza +1)%cola.length;
    tam--;
    return o;

  }

  /**
   * Muestra el primer elemento formado en la cola.
   * Devuelve <code>null</code> si está vacía.
   * @return Una referencia al elemento en la cabeza de la cola.
   */
  public E mira(){
    return cola[cabeza];

  }

  /**
   * Agrega un elemento al final de la cola.
   * @param e Referencia al elemento a agregar.
   * @return true - Si se pudo agregar el elemento.
   */
  public boolean add(E e){
    E[] aux;
    int j= 0;
    if(tam == cola.length-1){
      aux = Arrays.copyOf(cola, cola.length*2);
      for(int i=cabeza; j<cola.length-1; i = (i+1) % cola.length){
        aux[j++] = cola[i];
      }
      cola = aux;
      cabeza = 0;
      rabo = tam;
    }
    cola[rabo] = e;
    rabo = (rabo+1)%cola.length;
    tam++;
    return true;

  }

  /**
  *   Metodo que manda a llamar al iterador.
  *   @return El iterador de tipo generico.
  */
  public Iterator<E> iterator(){
    return new Iterador();

  }

  private class Iterador implements Iterator<E>{
    private int index;

    /**
    *   coloca nuestro primer indice en el valor de la cabeza.
    */
    public Iterador(){
      index = cabeza;

    }

    /**
    *   Pregunta si hay un elemento.
    *   @return true - si hay un elemento.
    */
    public boolean hasNext(){
      return index != rabo ;

    }

    /**
    *   Regresa el elemento en el que esta posicionado y se pasa al siguiente.
    *   @return El elemento en el que estaba posicionado.
    */
    public E next(){
      if(this.hasNext()){
        int aux = index;
        index = (index+1)%cola.length ;
        return cola[aux];
      }else throw new NoSuchElementException();

    }

    /**
    *   Metodo no soportado.
    */
    public void remove(){
      throw new UnsupportedOperationException();
    }

  }

  /**
  *   Metodo no soportado.
  */
  @Override
  public boolean remove(Object e){
    throw new UnsupportedOperationException();

  }

  /**
  *   Metodo no soportado.
  */
  @Override
  public boolean removeAll(Collection<?> e){
    throw new UnsupportedOperationException();

  }

  /**
  *   Metodo no soportado.
  */
  @Override
  public boolean retainAll(Collection<?> e){
    throw new UnsupportedOperationException();

  }

  /**
  *   Metodo sobrescrito, elimina todos los elemento de la cola.
  */
  @Override
  public void clear(){
    while(! this.isEmpty()){
      this.atiende();
    }

  }

}
