package ed.estructuras.nolineales;

/**
*   Clase para crear arboles AVL.
*   @author Dante Jusepee Sinencio Granados.
*/
public class ÁrbolAVL<C extends Comparable<C>> extends ÁrbolBOLigado<C>{

  /**
  *   Constructor que crea un arbol vacio.
  */
  public ÁrbolAVL(){
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
    return new NodoAVL((NodoAVL<C>)hijoI, (NodoAVL<C>)padre, dato, (NodoAVL<C>)hijoD);
  }

  /**
  *   Metodo para borrar un objeto.
  *   @param o - Objeto a eliminar del arbol.
  *   @return true - Si se pudo eliminar el objeto
  */
  @Override
  public boolean remove(C o) throws NullPointerException{
    if(o == null) throw new NullPointerException();
    if(this.raiz == null) return false;
    NodoAVL<C> aux = (NodoAVL<C>)this.raiz;
    aux = (NodoAVL<C>)aux.busca(o);
    if(aux == null) return false;
    if(aux.esHoja() && this.raiz == aux){
      this.raiz = null;
      tam--;
      return true;
    }else{
      NodoAVL<C> remo;
        remo = aux.removerAVL();
        this.raiz = remo;
        tam--;
        return true;
    }
  }

  /**
  *   Metodo para agregar un elemento al arbol.
  *   @param c - El elemento a agregar.
  *   @return true - Si se pudo agregar el elemento.
  */
  @Override
  public boolean add(C c){
    if(c == null) throw new NullPointerException();
    if(this.raiz == null) this.raiz = (NodoBOLigado<C>)creaNodo(c, null, null, null);
    else{
      NodoAVL<C> newa = (NodoAVL<C>)this.raiz.addNodo(creaNodo(c, null, null, null));
      newa = newa.balanceo();
      if(newa != null && newa.getPadre() == null) this.raiz = newa;
    }
    tam++;
    return true;
  }

}
