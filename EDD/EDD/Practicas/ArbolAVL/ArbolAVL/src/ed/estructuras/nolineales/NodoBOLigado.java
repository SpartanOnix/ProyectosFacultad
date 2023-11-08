package ed.estructuras.nolineales;
import java.util.List;

/**
*   Clase que crea nodos para arboles.
*   @author Dante Jusepee Sinencio Granados.
*/
public class NodoBOLigado<C extends Comparable<C>> implements NodoBinario<C>{

  protected int grado;
  protected int altura;
  protected List<Nodo<C>> lista = new ListaDoblementeLigada<>();
  protected C elem;
  protected NodoBOLigado<C> papa;
  protected NodoBOLigado<C> hijoI;
  protected NodoBOLigado<C> hijoD;

  /**
  *   Constructor de una raiz.
  *   @param e - Elemento a agregar.
  */
  public NodoBOLigado(C e){
    elem = e;
    papa = null;
    hijoI = null;
    hijoD = null;
    grado = 0;
    altura = 0;
    lista.add(this);
  }

  /**
  *   Constructor de nodos.
  *   @param hi - Hijo izquierdo.
  *   @param p - Nodo padre.
  *   @param e - Elemento que contiene el nodo.
  *   @param hd - Hijo derecho.
  */
  public NodoBOLigado(NodoBOLigado<C> hi, NodoBOLigado<C> p, C e, NodoBOLigado<C> hd){
    elem = e;
    papa = p;
    hijoI = hi;
    hijoD = hd;
    if(hi != null) grado += 1;
    else grado += 0;
    if(hd != null) grado += 1;
    else grado += 0;
    if(p != null) altura = p.getAltura() + 1;
    else altura = 0;
    lista.add(this);
  }

  /**
  *   Metodo para agregar un nodo al arbol.
  *   @param e - Elemento que contiene el nodo.
  *   @return El nodo agregado.
  */
  public NodoBinario<C> addNodo(NodoBinario<C> n){
    NodoBOLigado<C> a = (NodoBOLigado<C>)n;
    C e = a.elem;
    if(e.compareTo(this.elem) < 0){
      if(this.hijoI == null){
        this.hijoI = a;
        a.papa = this;
        return this.hijoI;
      }else return this.hijoI.addNodo(n);
    }else{
      if(this.hijoD == null){
        this.hijoD = a;
        a.papa = this;
        return this.hijoD;
      }else return this.hijoD.addNodo(n);
    }
  }

  /**
  *   Metodo para obtener el elemento en un nodo.
  *   @return El elemento contenido en el nodo.
  */
  @Override
  public C getElemento(){
    return elem;
  }

  /**
  *   Metodo para establecer el elemento de un nodo.
  *   @param dato - Elemento nuevo a establecerse en el nodo.
  */
  @Override
  public void setElemento(C dato){
    elem = dato;
  }

  /**
  *   Metodo para obtener el padre del nodo.
  *   @return El padre del nodo.
  */
  @Override
  public NodoBinario<C> getPadre(){
    return papa;
  }

  /**
  *   Metodo para establecer un nuevo padre para el nodo.
  *   @param padre - Padre a cambiar del nodo.
  */
  @Override
  public void setPadre(Nodo<C> padre){
    papa = (NodoBOLigado<C>)padre;
  }

  /**
  *   Metodo para obtener a un hijo en una cierto indice.
  *   @param indice - Indice en donde buscar el hijo.
  *   @return El hijo.
  */
  @Override
  public Nodo<C> getHijo(int índice){
    if(índice > lista.size()) throw new IndexOutOfBoundsException();
    return lista.get(índice);
  }

  /**
  *   Metodo para obtener el hijo izquierdo.
  *   @return El hijo izquierdo.
  */
  @Override
  public NodoBinario<C> getHijoI(){
    return this.hijoI;
  }

  /**
  *   Metodo para obtener el hijo derecho.
  *   @return El hijo derecho.
  */
  @Override
  public NodoBinario<C> getHijoD(){
    return this.hijoD;
  }

  /**
  *   Metodo para obtener la cantidad de hijos que tiene un nodo.
  *   @return 0, 1 o 2 dependiendo de la cantidad de hijos.
  */
  @Override
  public int getGrado(){
    return grado;
  }

  /**
  *   Metodo para obtener la altura de un nodo.
  *   @return La altura del nodo.
  */
  @Override
  public int getAltura(){
    if(this.hijoI == null && this.hijoD == null) return 0;
    else{
      if(this.hijoI != null && this.hijoD == null) return this.hijoI.getAltura() + 1;
      else{
        if(this.hijoI == null && this.hijoD != null) return this.hijoD.getAltura() + 1;
        else return Math.max(this.hijoI.getAltura() + 1, this.hijoD.getAltura() + 1);
      }
    }
  }

  /**
  *   Metodo para obtener su nodo hermano.
  *   @param hijo - El nodo del cual se quiere conocer su hermano.
  *   @return El hermano del nodo.
  */
  @Override
  public Nodo<C> getHermanoSiguiente(Nodo<C> hijo){
    NodoBOLigado<C> aux = (NodoBOLigado<C>)hijo;
    if(this.papa != aux.papa) throw new IllegalArgumentException();
    if(hijo == aux.papa.hijoI) return aux.papa.hijoD;
    else return aux.papa.hijoI;
  }

  /**
  *   Metodo para obtener una lista con los hijos de un nodo.
  *   @return La lista con los hijos.
  */
  @Override
  public List<Nodo<C>> getListaHijos(){
    return lista;
  }

  /**
  *   Metodo para conocer si un nodo no tiene hijos.
  *   @return true - si es hoja.
  */
  @Override
  public boolean esHoja(){
    if(this.hijoI == null && this.hijoD == null) return true;
    else return false;
  }

  /**
  *   Metodo para eliminar el hijo de un nodos
  *   @param hijo - El nodo a eliminar.
  *   @return true - si se elimino el nodo.
  */
  @Override
  public boolean remueveHijo(Nodo<C> hijo){
    NodoBOLigado <C> aux = (NodoBOLigado)hijo;
    if(aux.esHoja()){
      if(aux.papa.hijoD == aux) aux.papa.hijoD = null;
      else aux.papa.hijoI = null;
      aux.papa = null;
      aux.elem = null;
      return true;
    }else{
      NodoBOLigado <C> swap;
      if(aux.hijoD != null){
        swap = aux.hijoD.izq();
        C elemento = aux.elem;
        aux.elem = swap.elem;
        swap.elem = elemento;
        swap.papa.remueveHijo(swap);
        return true;
      }else{
        if(aux.hijoI != null){
          if(aux.hijoD == null) swap = aux.hijoI.der();
          else swap = aux.hijoI.izq();
          C elemento = aux.elem;
          aux.elem = swap.elem;
          swap.elem = elemento;
          swap.papa.remueveHijo(aux);
          return true;
        }
      }
    }
    return false;
  }

  /**
  *   Metodo para obtener el nodo mas grande del sub arbol izquierdo.
  *   @param nodo - El nodo que se utiliza como raiz.
  *   @return el nodo mas grande del sub arbol izquierdo.
  */
  public NodoBOLigado<C> masG(NodoBOLigado<C> nodo){
    NodoBOLigado<C> aux;
    while(nodo.hijoI != null && nodo.hijoD == null){
      nodo = nodo.hijoI;
    }
    while((nodo.hijoD != null & nodo.hijoI != null) || (nodo.hijoD != null & nodo.hijoI == null)){
      nodo = nodo.hijoD;
    }
    return nodo;
  }

  /**
  *   Metodo para obtener el nodo mas grande del sub arbol derecho.
  *   @param nodo - El nodo que se utiliza como raiz.
  *   @return el nodo mas grande del sub arbol derecho.
  */
  public NodoBOLigado<C> masP(NodoBOLigado<C> nodo){
    NodoBOLigado<C> aux;
    while(nodo.hijoD != null && nodo.hijoI == null){
      nodo = nodo.hijoD;
    }
    while((nodo.hijoI != null & nodo.hijoD != null) || (nodo.hijoI != null & nodo.hijoD == null)){
      nodo = nodo.hijoI;
    }
    return nodo;
  }

  /**
  *   Metodo para llegar a la hoja mas a la izquierda de un arbol.
  *   @return La hoja mas a la izquierda de un arbol.
  */
  public NodoBOLigado <C> izq(){
    NodoBOLigado <C> aux = this;
    while(aux.hijoI != null){
      aux = aux.hijoI;
    }
    return aux;
  }

  /**
  *   Metodo para llegar a la hoja mas a la derecha de un arbol.
  *   @return La hoja mas a la derecha de un arbol.
  */
  public NodoBOLigado <C> der(){
    NodoBOLigado <C> aux = this;
    while(aux.hijoD != null){
      aux = aux.hijoD;
    }
    return aux;
  }

  /**
  *   Metodo para obtener la raiz de un arbol.
  *   @return La raiz del arbol.
  */
  public NodoBOLigado<C> obtenerRaiz(){
    NodoBOLigado <C> aux = this;
    while(aux.papa != null){
      aux = aux.papa;
    }
    return aux;
  }

  /**
  *   Metodo para buscar un elemento en un arbol.
  *   @param elemento - El elemento a buscar.
  *   @return El nodo que contiene al elemento.
  */
  public NodoBOLigado <C> busca(C elemento){
    NodoBOLigado <C> aux = this;
    while(aux != null){
      if(aux.elem.compareTo(elemento) > 0) aux = aux.hijoI;
      else{
        if(aux.elem.compareTo(elemento) < 0) aux = aux.hijoD;
        else{
          if(aux.elem.compareTo(elemento) == 0) return aux;
        }
      }
    }
    return null;
  }

}
