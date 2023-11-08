package ed.estructuras.nolineales;

/**
*   Clase para crear arboles rojinegros.
*   @author Dante Jusepee Sinencio Granados.
*/
public class ÁrbolRojinegro<C extends Comparable<C>> extends ÁrbolAVL<C>{

  /**
  *   Constructor que crea un arbol vacio.
  */
  public ÁrbolRojinegro(){
    raiz = null;
  }

  /**
  *   Constructor de nodos en un arbol.
  *   @param hijoI - Hijo izquierdo.
  *   @param padre - Nodo padre.
  *   @param dato - Elemento que contiene el nodo.
  *   @param hijoD - Hijo derecho.
  *   @return El nodo creado.
  */
  protected NodoBinario<C> creaNodo(C dato, NodoBinario<C> padre, NodoBinario<C> hijoI, NodoBinario<C> hijoD){
    return new NodoRojinegro((NodoAVL<C>)hijoI, (NodoAVL<C>)padre, dato, (NodoAVL<C>)hijoD);
  }

  /**
  *   Metodo para agregar un elemento al arbol.
  *   @param c - El elemento a agregar.
  *   @return true - Si se pudo agregar el elemento.
  */
  @Override
  public boolean add(C c){
    if(c == null) throw new NullPointerException();
    NodoRojinegro<C> newa;
    if(this.getRaíz() == null){
      this.raiz = (NodoRojinegro<C>)creaNodo(c, null, null, null);
      newa = (NodoRojinegro<C>)this.raiz;
      newa.balancearAdd();
    }else{
      newa = (NodoRojinegro<C>)raiz.addNodo(creaNodo(c, null, null, null));
      newa = newa.balancearAdd();
      if(newa != null){
        if(newa.getPadre() == null) this.raiz = newa;
      }
    }
    tam ++;
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
    NodoRojinegro<C> remo = (NodoRojinegro<C>)raiz.busca(o);
    if(remo.esHoja() && this.raiz == remo){
      this.raiz = null;
      return true;
    }else{
      this.raiz = remo.removerRojinego();
      return true;
    }
  }

}
