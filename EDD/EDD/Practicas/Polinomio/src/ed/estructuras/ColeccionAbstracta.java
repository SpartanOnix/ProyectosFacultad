package ed.estructuras;
import java.lang.StringBuilder;
import java.util.Collection;
import java.util.Iterator;
import java.lang.NullPointerException;
import java.lang.IllegalArgumentException;
import java.util.Arrays;

/**
*   Clase para crear colecciones
*   @author Dante Jusepee Sinencio Granados
*/
public abstract class ColeccionAbstracta<E> implements Collection<E>{
  //Atributo del tamaño de la coleccion.
  protected int tam;

  /**
  *   Metodo para verificar si el elemento esta contenido en la coleccion.
  *   @param o - El objeto a buscar.
  *   @return true - si el objeto esta contenido en la coleccion,
  *           false - si el objeto no esta contenido en la coleccion.
  *   @throws NullPointerException - Si el elemento es nulo
  */
  public boolean contains(Object o){
  //  if(o == null) throw new NullPointerException();
    Iterator <E> it = this.iterator();
  	while (it.hasNext()){
      E aux = it.next();
      if(aux == null){
        if(o == null) return true;
      }else{
        if(aux.equals(o)) return true;
      }
    }
  	return false;
  }

  /**
  *   Devuelve un arreglo que contiene todos los elementos de esta colección.
  *   @return El arreglo con todos los elementos de esta coleccion.
  */
  public Object[] toArray(){
    Iterator <E> it = this.iterator();
  	Object [] aux = new Object[this.size()];
  	int i = 0;
  	while (it.hasNext()){
  	    aux[i] = it.next();
  	    i++;
  	}
  	return aux;
  }

  /**
  *   Devuelve un arreglo que contiene todos los elementos de esta colección;
  *   el tipo del arreglo puede ser cualquiera.
  *   @param a - El arreglo de tipo generico donde se almacenaran los objetos.
  *   @return El arreglo con todos los elementos de esta coleccion.
  */
  public <T> T[] toArray(T[] a){
    //if(a.isEmpty())
    //if(a.length == this.size()) return a;
    T[] aux = Arrays.copyOf(a, tam);//(T[])new Object[1];
    if(a.length < this.size()){
      Iterator <E> it = this.iterator();
      a = Arrays.copyOf(a, this.size());
      int i = 0;
      while (it.hasNext()){
        a[i] = (T)it.next();
        i++;
      }
    }
    if(a.length >= this.size()){
      Iterator <E> it = this.iterator();
      //aux = a;
      int i = 0;
      while (it.hasNext()){
        a[i] = (T)it.next();
        i++;
      }
      while(i<a.length){
        a[i] = null;
        i++;
      }
    }
    return a;
  }

  /**
  *   Metodo para verificar si una coleccion contiene todos los elementos de esta coleccion.
  *   @param c - Coleccion donde se comprobara que el elemento esta contenido.
  *   @return true - si esta colección contiene todos los elementos de la colección especificada.
  */
  public boolean containsAll(Collection<?> c){
    if(c == null) throw new NullPointerException();
    if(c == this) return true;
    for(Object x: c){
      if(!this.contains(x)) return false;
    }
    return true;
  }


  /**
  *   Metodo para agregar los elementos de la coleccion a esta coleccion.
  *   @param c - La coleccion que contiene los elementos a agregar en esta coleccion.
  *   @return true - si la coleccion cambia por el resultado de la llamada a metodo.
  *   @throws NullPointerException - Si la coleccion esta vacia.
  *
  */
  public boolean addAll(Collection<? extends E> c){
    if(c == null) throw new NullPointerException();
    if(c.isEmpty()) throw new IllegalArgumentException();
    if(c == this) return false;
    for(E x: c){
      this.add(x);
    }
    return true;
  }

  /**
  *   Elimina un elemento especifico en la coleccion.
  *   @param o - El elemto que se quiere eliminar.
  *   @return true - si el elemento fue eliminado de la coleccion.
  */
  public boolean remove(Object o){
    //if(o == null) throw new NullPointerException();
    Iterator <E> it = iterator();
    //Object nulo = null;
    Object aux;
    while (it.hasNext()){
      aux = it.next();
      if (aux == null){
        if (o == null){
          it.remove();
          return true;
        }
      }else{
        if(aux.equals(o)){
          it.remove();
          return true;
        }
      }
    }
    return false;
  }

  /**
  *   Elimina todos los elementos de la coleccion que esten contenidos en esta.
  *   @param c - La coleccion a la que se le eliminaran los elementos.
  *   @return true - si la colleccion cambia por la llamada a metodo.
  */
  public boolean removeAll(Collection<?> c){
    if(c == null) throw new NullPointerException();
    if(c.isEmpty()) return false;
    if(c == this){
      this.clear();
      return true;
    }
    // Iterator <?> it = c.iterator();
    // Object no_eli = null;
    // int col = this.size();
    // int colfinal = c.size();
    // while (it.hasNext()){
    //     no_eli = it.next();
    //     if (! contains(no_eli)){
    //       remove(no_eli);
    //       colfinal--;
    //     }
    // }
    // if (col == colfinal){
    //     return false;
    // }
    for(Object x: c){
      this.remove(x);
    }
    return true;
  }

  /**
  *   Elimina los elementos de esta coleccion que no estan contenidos en la coleccion especifica.
  *   @param c - La coleccion que contiene los elementos a ser retenidos.
  *   @return true - si la coleccion cambia por el resultado de la llamada a metodo.
  *   @throws UnsupportedOperationException - Si la coleccion esta vacia
  */
  public boolean retainAll(Collection<?> c){
    //if(c == null) throw new NullPointerException();
    if(c.isEmpty()){
      this.clear();
      return true; //throw new UnsupportedOperationException();
    }
    if(c == this) return false;
    Iterator<?> it = this.iterator();
  	int tamaño = tam;
    // while(it.hasNext()){
  	// 	if (!c.contains(it.next())){
  	// 	    it.remove();
  	// 	}
  	// }
    for(Object x: c){
      if (!c.contains(it.next())){
  		    it.remove();
  		}
    }
  	return tam != tamaño;
  }

  /**
  *   Elimina todos los elementodos de la coleccion, la coleccion se queda vacia.
  *   @throws UnsupportedOperationException - si la operacion no se puede aplicar en la coleccion.
  */
  public void clear(){
    Iterator <E> it = iterator();
  	while(it.hasNext()){
  	    it.next();
  	    it.remove();
  	}
  }

  /**
  *   Metodo para saber el tamaño de la coleccion.
  *   @return El numero de elementos en la coleccion.
  */
  public int size(){
    return tam;
  }

  /**
  *   Regresa el valor has para esta coleccion.
  *   @return El valor has para esta coleccion.
  */
  @Override
  public int hashCode(){
    int primo = tam*17;
    return primo;
  }

  /**
  *   Metodo para saber si la coleccion esta vacia.
  *   @return true - Si la coleccion esta vacia.
  */
  public boolean isEmpty(){
    return size() == 0;
  }

  /**
  *   Metodo para imprimir lo contenido en la Coleccion.
  *   @return La cadena con los elemetos convertidos en strings.
  */
  public String toString(){
    StringBuilder sb = new StringBuilder("[");
    Iterator<E> it = iterator();
    if(it.hasNext()){
      sb.append(it.next());
    }
    while(it.hasNext()){
      sb.append(", ");
      sb.append(it.next());
    }
    sb.append("]");
    return sb.toString();
  }

  /**
  *   Metodo para comparar que 2 dos objetos son iguales.
  *   @param obj - El objeto con el cual se va a comparar.
  *   @return true - si los objetos son iguales,
  *           false - si los objetos no son iguales.
  */
  public boolean equals(Object obj){
    if(obj == this){
      return true;
    }else{
      if(obj == null){
        return false;
      }else{
        if(!(obj instanceof ColeccionAbstracta)){
          return false;
        }else{
          Iterator<?> it;
          Iterator<E> this_it;
          ColeccionAbstracta<E> c = (ColeccionAbstracta<E>)obj;
          it = c.iterator();
          this_it = this.iterator();
          while(it.hasNext() && this_it.hasNext()){
            E e = this_it.next();
            Object o = it.next();
            if((o == null &&  e !=null) || (o != null &&  e == null)){
              return false;
            }
            if(e == null && o == null){
              return true;
            }else{
              if(!e.equals(o)){
                return false;
              }
            }
          }
          if(it.hasNext() || this_it.hasNext())  return false;
          return true;
        }
      }
    }
  }


}
