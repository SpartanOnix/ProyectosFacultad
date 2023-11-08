package ed.estructuras.nolineales;

import ed.estructuras.nolineales.*;
import ed.estructuras.ColeccionAbstracta;
import java.util.NoSuchElementException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
*   Clase para crear arboles binarios ordenados.
*   @author Dante Jusepee Sinencio Granados.
*/
public class ÁrbolBOLigado<C extends Comparable<C>> extends ColeccionAbstracta<C> implements ÁrbolBinarioOrdenado<C>{

  private NodoBOLigado<C> raiz;

  /**
  *   Constructor que crea un arbol vacio.
  */
  public ÁrbolBOLigado(){
    raiz = null;
  }

  /**
  *   Constructor de nodos en un arbol.
  *   @param hijoI - Hijo izquierdo.
  *   @param padre - Nodo padre.
  *   @param dato - Elemento que contiene el nodo.
  *   @param hijoD - Hijo derecho.
  */
  protected NodoBinario<C> creaNodo(C dato, NodoBinario <C> padre, NodoBinario <C> hijoI, NodoBinario <C> hijoD){
    return new NodoBOLigado((NodoBOLigado<C>)hijoI, (NodoBOLigado<C>)padre, dato, (NodoBOLigado<C>)hijoD);
  }


  /**
  *   Clase que crea un iterador en inorden.
  */
  private class IteradorIn implements Iterator<C>{
    NodoBOLigado<C> pos;
    NodoBOLigado<C> posant;

    /**
    *   Inicializa el iterador en el nodo de mas a la izquierda del arbol.
    */
    public IteradorIn(){
      pos = raiz.izq();
      posant = raiz;
    }

    /**
    *   Metodo para saber si hay un siguiente.
    *   @return true - si hay siguiente.
    */
    public boolean hasNext(){
      if(pos == null) return false;
      else return true;
    }

    /**
    *   Metodo para pasar al siguiente elemento.
    *   @return El elemento siguiente.
    */
    public C next(){
      if(this.hasNext()){
        C o = (C) pos.getElemento();
        if (pos.getHijoD() != null){
          pos = (NodoBOLigado<C>)pos.getHijoD();
          pos = pos.izq();
        }else{
          posant = pos;
          pos = (NodoBOLigado<C>)pos.getPadre();
          while(pos != null && (pos.getHijoD() == posant)){
            posant = pos;
            pos = (NodoBOLigado<C>)pos.getPadre();
          }
        }
        return o;
      }else throw new NoSuchElementException();
    }

    /**
    *   Metodo no soportado.
    */
    public void remove(){}

  }


  /**
  *   Clase para crear un iterador en preorden.
  */
  private class IteradorPre implements Iterator<C>{
    NodoBOLigado<C> pos;
    NodoBOLigado<C> posant;

    /**
    *   Crea un iterador desde la raiz.
    */
    public IteradorPre(){
      pos = raiz;
      posant = null;
    }

    /**
    *   Metodo para saber si hay un siguiente.
    *   @return true - si hay siguiente.
    */
    public boolean hasNext(){
      if(pos == null) return false;
      else return true;
    }

    /**
    *   Metodo para pasar al siguiente elemento.
    *   @return El elemento siguiente.
    */
    public C next(){
      if(this.hasNext()){
        posant = pos;
        C o = (C) pos.getElemento();
        if (pos.getHijoI() != null) pos = (NodoBOLigado<C>)pos.getHijoI();
        else{
          if (pos.getHijoD() != null) pos = (NodoBOLigado<C>)pos.getHijoD();
          else pos = (NodoBOLigado<C>)pos.getPadre().getHermanoSiguiente(pos);
        }
        return o;
      }else throw new NoSuchElementException();
    }

    /**
    *   Metodo no soportado.
    */
    public void remove(){}

  }


  /**
  *   Crea un iterador en postorden.
  */
  private class IteradorPost implements Iterator<C>{
    NodoBOLigado<C> pos;
    NodoBOLigado<C> posant;

    /**
    *   Crea un iterador desde el elemto mas chico del arbol.
    */
    public IteradorPost(){
      pos = auxPost(raiz);
      posant = null;
    }

    /**
    *   Metodo para saber si hay un siguiente.
    *   @return true - si hay siguiente.
    */
    public boolean hasNext(){
      if(pos == null) return false;
      else return true;
    }

    /**
    *   Metodo auxiliar para el next en este iterador.
    */
    private NodoBOLigado <C> auxPost(NodoBOLigado <C> n){
      NodoBOLigado<C> aux = n;
      if(aux.getHijoI()!= null){
        aux = (NodoBOLigado<C>)aux.getHijoI();
        aux = aux.izq();
        if(aux.getHijoD() != null) return auxPost((NodoBOLigado<C>)aux.getHijoD());
        else return aux;
      }else if(aux.getHijoD()!= null){
            aux = (NodoBOLigado<C>)aux.getHijoD();
            return aux.izq();
            }
      return aux;
    }

    /**
    *   Metodo para pasar al siguiente elemento.
    *   @return El elemento siguiente.
    */
    public C next(){
      if(this.hasNext()){
        posant = pos;
        C o = (C) pos.getElemento();
        //pos = auxPost(this);
        if(pos == raiz.getHijoD()) pos = raiz;
        else{
          if(pos == raiz.getHijoI()){
            if(raiz.getHijoD() != null) pos = auxPost((NodoBOLigado<C>)raiz.getHijoD());
            else pos = raiz;
          }else{
            if(pos == pos.getPadre().getHijoI() && pos.getPadre() != raiz){
              if(pos.getPadre().getHijoD() != null) pos = auxPost((NodoBOLigado<C>)pos.getPadre().getHijoD());
              else pos = (NodoBOLigado<C>)pos.getPadre();
            }else{
              if(pos == pos.getPadre().getHijoD() && pos.getPadre() != raiz) pos = (NodoBOLigado<C>)pos.getPadre();
            }
          }
        }
        return o;
      }else throw new NoSuchElementException();
    }

    /**
    *   Metodo no soportado.
    */
    public void remove(){}

  }

  /**
  *   Metodo para obtener la raiz del arbol.
  *   @return La raiz del arbol.
  */
  @Override
  public NodoBinario<C> getRaíz(){
    return raiz;
  }

  /**
  *   Metodo para obtener la altura de un arbol.
  *   @return La altura del arbol.
  */
  @Override
  public int getAltura(){
    if(this == null) return -1;
    else return this.getRaíz().getAltura();
  }

  /**
  *   Metodo que llama al iterador inorden.
  *   @return El iterador inorden.
  */
  public Iterator<C> iterator(){
    return new IteradorIn();
  }

  /**
  *   Metodo que llama al iterador inorden.
  *   @return El iterador inorden.
  */
  @Override
  public Iterator<C> getIteradorInorden(){
    return new IteradorIn();
  }

  /**
  *   Metodo que llama al iterador preorden.
  *   @return El iterador preorden.
  */
  @Override
  public Iterator<C> getIteradorPreorden(){
    return new IteradorPre();
  }

  /**
  *   Metodo que llama al iterador postorden.
  *   @return El iterador postorden.
  */
  @Override
  public Iterator<C> getIteradorPostorden(){
    return new IteradorPost();
  }

  /**
  *   Metodo para guardar en una lista un arbol.
  *   @return La lista que contiene al arbol.
  */
  @Override
  public List<C> recorridoPostorden(){
    ListaDoblementeLigada<C> lista = new ListaDoblementeLigada<>();
    Iterator<C> it = getIteradorPostorden();
    while(it.hasNext()){
      C aux = it.next();
      lista.add(aux);
    }
    return lista;
  }

  /**
  *   Metodo que checa que un objeto pertenezca al arbol.
  *   @param o - El objeto a buscar.
  *   @return true - Si esta el objeto en el arbol.
  */
  @Override
  public boolean contains(C o){
    if(o == null) throw new NullPointerException();
    Iterator<C> it = getIteradorInorden();
    C dato;
    while(it.hasNext()){
      dato = it.next();
      if(o == dato) return true;
    }
    return false;
  }

  /**
  *   Metodo para agregar un elemento al arbol.
  *   @param c - El elemento a agregar.
  *   @return true - Si se pudo agregar el elemento.
  */
  public boolean add(C c){
    if(c == null) throw new NullPointerException();
    if(this.raiz == null) this.raiz = new NodoBOLigado(c);
    else raiz.addNodo(c);
    tam++;
    return true;
  }

  /**
  *   Metodo para borrar un objeto.
  *   @param o - Objeto a eliminar del arbol.
  *   @return true - Si se pudo eliminar el objeto
  */
  @Override
  public boolean remove(C o){
    if(o == null) throw new NullPointerException();
    NodoBOLigado <C> aux = raiz.busca(o);
    if(aux.esHoja() && this.raiz == aux){
      this.raiz = null;
      return true;
    }else{
      if(aux == raiz) return aux.remueveHijo(aux);
      else return aux.getPadre().remueveHijo(aux);
    }
  }

}
