package ed.estructuras.lineales;
import java.util.Iterator;
import ed.estructuras.ColeccionAbstracta;
import java.util.Collection;

/**
*   Clase para crear una pila.
*   @author Dante Jusepee Sinencio Granados
*/
public class PilaLigada<E>  extends ColeccionAbstracta<E> implements IPila<E>{
  //Nodo superior de la pila.
  private Nodo<E> cabeza;

  /**
  *   Construye una pila vacia.
  */
  public PilaLigada(){
    tam = 0;
    cabeza = null;
  }

  /**
  *   Iterador para la clase pila.
  */
  private class Iterador implements Iterator<E>{
    //Nodo de la posicion de la pila.
    Nodo<E> pos;

    /**
    *   Coloca la cabeza de la pila en el primer elemento de la pila.
    */
    public Iterador(){
      pos = cabeza;
    }

    /**
    *   Pregunta si hay un elemento.
    *   @return true - si hay un elemento.
    */
    public boolean hasNext(){
      if(pos == null) return false;
      else return true;
    }

    /**
    *   Regresa el elemento en el que esta posicionado y se pasa al siguiente.
    *   @return El elemento en el que estaba posicionado.
    */
    public E next(){
      if(this.hasNext()){
        E o = (E) pos.elem;
        pos = pos.sig;
        return o;
      }else return null;
    }

    /**
    *   Metodo no soportado.
    */
    public void remove(){
      throw new UnsupportedOperationException();
    }
  }

  /**
  *   Nodos utilizados en la pila.
  */
  private class Nodo<E>{
    E elem;
    Nodo<E> sig;

    /**
    *    Crea un nodo en la primera posicion de la pila.
    *    @param e - El elemento contenido en ese nodo.
    */
    public Nodo(E e){
      elem = e;
      sig = null;
    }

    /**
    *   Crea un nodo el cual tiene un nodo siguiente.
    *   @param e - Elemento guardado en el Nodo.
    *   @param n - Nodo el cual le sigue.
    */
    public Nodo(E e, Nodo<E> n){
      elem = e;
      sig = n;
    }

  }


  /**
  *   Metodo para checar el elemto hasta arriba de la pila.
  *   @return El elemento hasta arriba de la pila.
  */
  public E mira(){
    if(isEmpty()) return null;
    return cabeza.elem;
  }

  /**
  *   Metodo para eliminar el nodo de hasta arriba de la pila.
  *   @return El elemento que se elimino.
  */
  public E expulsa(){
    if(isEmpty()) return null;
    E elem = cabeza.elem;
    cabeza.elem =  null;
    cabeza = cabeza.sig;
    tam--;
    return elem;
  }

  /**
  *   Metodo para agregar un nodo.
  *   @param e - El nuevo elemento que se va a agregar a la pila.
  */
  public void empuja(E e){
    this.add(e);
  }

  /**
  *   Metodo para agregar un nodo.
  *   @param e - El nuevo elemento que se va a agregar a la pila.
  */
  public boolean add(E e){
    //return this.empuja(e);
    Nodo<E> nono = new Nodo<>(e, cabeza);
    cabeza = nono;
    tam++;
    return true;
  }

  /**
  *   Metodo que llama al iterador.
  */
  public Iterator<E> iterator(){
    return new Iterador();
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
  *   Metodo sobrescrito, elimina todos los elemento de la pila.
  */
  @Override
  public void clear(){
    while(! this.isEmpty()){
      this.expulsa();
    }
  }

}
