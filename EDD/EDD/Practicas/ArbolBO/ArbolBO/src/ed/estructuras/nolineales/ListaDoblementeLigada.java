package ed.estructuras.nolineales;
import ed.estructuras.ColeccionAbstracta;
import java.util.List;
import java.util.Iterator;
import java.util.Collection;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.lang.IllegalStateException;
import java.lang.IndexOutOfBoundsException;


/**
*   Clase para crear colecciones
*   @author Dante Jusepee Sinencio Granados
*/
public class ListaDoblementeLigada<E> extends ColeccionAbstracta<E> implements List<E>{
  private Nodo<E> inicio;
  private Nodo<E> fin;

/**
*   Crea una lista vacia.
*/
  public ListaDoblementeLigada(){
    inicio = null;
    fin = null;
    tam = 0;
  }

  /**
  *   Clase iterador.
  */
  private class Iteradora implements ListIterator<E>{
    Nodo<E> posant;
    Nodo<E> pos;
    int indexant;
    int indexsig;
    int canset;

    /**
    *   Crea un iterador desde el inicio de la lista.
    */
    public Iteradora(){
      this(0);
    }

    /**
    *   Crea un iterador desde un indice especificado.
    *   @param i - Indice desde el que se va a empezar la iteracion.
    */
    public Iteradora(int i){
      posant = null;
      pos = inicio;
      indexant = -1;
      indexsig = 0;
      canset = 0;
      while(nextIndex() != i){
        next();
      }
    }

    /**
    *   Metodo para agregar un metodo en la lista.
    *   @param e - Elemento a agregar.
    */
    public void add(E e){
      if(isEmpty()){
        Nodo<E> nono = new Nodo<>(fin, e, null);
        pos = inicio = fin = nono;
      }else{
        if(pos == inicio){
          Nodo<E> nono = new Nodo<>(null, e, inicio);
          inicio.ant = nono;
          posant = nono;
          inicio = nono;
          indexant++;
          indexsig++;
        }else if(pos  == null){
                Nodo<E> nono = new Nodo<>(fin, e, null);
                fin.sig = nono;
                posant = nono;
                fin = nono;
                indexant++;
                indexsig++;
              }else{
                Nodo<E> nono = new Nodo<>(posant, e, pos);
                posant.sig = nono;
                pos.ant = nono;
                posant = nono;
                indexant++;
                indexsig++;
              }
      }
      canset = 0;
      tam++;
    }

    /**
    *   Metodo para saber si hay un siguiente.
    *   @return true - Si hay siguiente.
    */
    public boolean hasNext(){
      if(pos == null) return false;
      else return true;
    }

    /**
    *   Metodo para saber si hay anterior.
    *   @return true - Si hay anterior.
    */
    public boolean hasPrevious(){
      if(posant == null) return false;
      else return true;
    }

    /**
    *   Metodo para pasar al siguiente elemento.
    *   @return El elemento anterior.
    */
    public E next(){
      if(this.hasNext()){
        posant = pos;
        E o = (E) pos.elem;
        pos = pos.sig;
        canset = 1;
        indexsig++;
        indexant++;
        return o;
      }else throw new NoSuchElementException();
    }

    /**
    *   Metodo para obtener el indice donde se esta iterando.
    *   @return El indice donde se esta iterando.
    */
    public int nextIndex(){
      return indexsig;
    }

    /**
    *   Metodo para pasar al elemento anterior.
    *   @return El elemento siguiente.
    */
    public E previous(){
      if(this.hasPrevious()){
        pos = posant;
        E o = (E) posant.elem;
        posant = posant.ant;
        canset = -1;
        indexsig--;
        indexant--;
        return o;
      }else throw new NoSuchElementException();
    }

    /**
    *   Metodo para obtener el indice anterior a donde se esta iterando.
    *   @return El indice anterior donde se esta iterando.
    */
    public int previousIndex(){
      return indexant;
    }

    /**
    *   Metodo para eliminar elementos.
    */
    public void remove(){
      if(canset == 0) throw new IllegalStateException();
      if(canset == 1){
        if(tam == 1){
          posant.sig = null;
          posant.ant = null;
          posant = null;
          inicio = fin = null;
        }else{
          if(posant.ant == null){
            pos.ant = null;
            posant.sig = null;
            posant = null;
            inicio = pos;
          }else{
            if(pos != null){
              pos.ant = posant.ant;
              posant.ant.sig = pos;
              posant = posant.ant;
            }else{
              fin = posant.ant;
              posant.ant.sig = null;
              posant.ant = null;
              posant = fin;
            }
          }
        }
        indexsig--;
        indexant--;
      }else{
        if(tam == 1){
          pos.sig = null;
          pos.ant = null;
          pos = null;
          inicio = fin = null;
        }else{
          if(pos.sig == null){
            posant.sig = null;
            pos.ant = null;
            fin = posant;
          }else{
            if(posant != null){
              posant.sig = pos.sig;
              pos.sig.ant = posant;
              pos = pos.sig;
            }else{
              inicio = pos.sig;
              pos.sig.ant = null;
              pos.sig = null;
              pos = inicio;
            }
          }
        }
      }
      canset = 0;
      tam--;
    }

    /**
    *   Metodo para intercambir un elemento en la posicion del iterador.
    */
    public void set(E e){
      if(canset == 0) throw new IllegalStateException();
      if(canset == 1) posant.elem = e;
      if(canset == -1) pos.elem = e;
    }
  }

  /**
  *   Clase que contiene a los nodos.
  */
  private class Nodo<E>{
    E elem;
    Nodo<E> sig;
    Nodo<E> ant;

    /**
    *   Construye un nodo .
    *   Elemento del nodo.
    */
    public Nodo(E e){
      elem = e;
      sig = null;
      ant = null;
    }

    /**
    *   Construye un nodo con referencia a sus nodos adyacentes.
    *   @param a - Nodo anterior.
    *   @param e - Elemento del nodo.
    *   @param s - Nodo siguiente.
    */
    public Nodo(Nodo<E> a, E e, Nodo<E> s){
      elem = e;
      ant = a;
      sig = s;
    }
  }


  /**
  *   Metodo para agregar un elemento.
  *   @param e - El elemento a agregar.
  *   @return true - Si se pudo agregar.
  */
  public boolean add(E e){
    ListIterator<E> act = this.listIterator(size());
    act.add(e);
    return true;
  }

  /**
  *   Metodo para agregar toda una coleccion a la lista desde un cierto indice.
  *   @param index - Indice desde donde va a comenzar a agregar.
  *   @param c - Coleccion a agregar.
  *   @return true - Si se pudo agregar.
  */
  public boolean addAll(int index,Collection<? extends E> c){
    if(index < 0 || index > size()) throw new IndexOutOfBoundsException();
    if(c == null) throw new NullPointerException();
    if(c.isEmpty()) throw new IllegalArgumentException();
    for(E x : c){
      add(index++, x);
    }
    return true;
  }

  /**
  *   Metodo para obtener un elemento en la lista.
  *   @param index - Indice desde donde se va a empezar.
  *   @return El elemento del nodo.
  */
  public E get(int index){
    if(index < 0 || index >= size()) throw new IndexOutOfBoundsException();
    ListIterator<E> act = this.listIterator(index);
    return act.next();
  }

  /**
  *   Metodo para intercambiar un elemento.
  *   @param index - Indice del elemento a intercambiar.
  *   @param element - El elemento a intercambiar.
  *   @return El elemento intercambiado.
  */
  public E set(int index,E element){
    if(index < 0 || index >= size()) throw new IndexOutOfBoundsException();
    ListIterator<E> act = this.listIterator(index);
    E aux = act.next();
    act.set(element);
    return aux;
  }

  /**
  *   Metodo para agregar un elemento desde un cierto indice.
  *   @param index - Indice donde se va a agtregar el elemento.
  *   @param element - El elemento a agregar.
  */
  public void add(int index,E element){
    if(index < 0 || index > size()) throw new IndexOutOfBoundsException();
    ListIterator<E> act = this.listIterator(index);
    act.add(element);

  }

  /**
  *   Metodo para remover un elemento.
  *   @param index - El indice del elemento a eliminar.
  *   @return El elemento eliminado.
  */
  public E remove(int index){
    if(index < 0 || index >= size()) throw new IndexOutOfBoundsException();
    ListIterator<E> act = this.listIterator(index);
    E aux = act.next();
    act.remove();
    return aux;
  }

  /**
  *   Metodo para obtener el indice de un elemento.
  *   @param o - Objeto del cual se quiere saber su indice.
  *   @return El indice del objeto.
  */
  public int indexOf(Object o){
    ListIterator<E> act = this.listIterator();
    E aux;
    while(act.hasNext()){
      aux = act.next();
      if(o.equals(aux)) return act.previousIndex();
    }
    return -1;
  }

  /**
  *   Metodo para obtener el indice de un elemento.
  *   @param o - Objeto del cual se quiere saber su indice.
  *   @return El indice del objeto.
  */
  public int lastIndexOf(Object o){
    ListIterator<E> act = this.listIterator(tam);
    E aux;
    while(act.hasPrevious()){
      aux = act.previous();
      if(o.equals(aux)) return act.nextIndex();
    }
    return -1;
  }

  /**
  *   Metodo para obtener un iterador.
  *   @return Un iterador.
  */
  public Iterator<E> iterator(){
    return new Iteradora();
  }

  /**
  *   Metodo para obtener un iterador.
  *   @return Un iterador.
  */
  public ListIterator<E> listIterator(){
    return new Iteradora();
  }

  /**
  *   Metodo para obtener un iterador desde un cierto indice.
  *   @param index - El indice desde el cual quieres inicializar el iterador.
  *   @return Un iterador.
  */
  public ListIterator<E> listIterator(int index){
    if(index < 0 || index > size()) throw new IndexOutOfBoundsException();
    return new Iteradora(index);
  }

  /**
  *   Metodo no soportado
  */
  @Override
  public List<E> subList(int fromIndex, int toIndex){
    return null;
  }


}
