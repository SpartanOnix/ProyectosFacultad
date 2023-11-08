package ed.estructuras.nolineales;

/**
*   Clase que crea nodos para arboles AVL.
*   @author Dante Jusepee Sinencio Granados.
*/
public class NodoAVL<C extends Comparable<C>> extends NodoBOLigado<C>{

  /**
  *   Constructor de nodos.
  *   @param hi - Hijo izquierdo.
  *   @param p - Nodo padre.
  *   @param e - Elemento que contiene el nodo.
  *   @param hd - Hijo derecho.
  */
  public NodoAVL(NodoBOLigado<C> hi, NodoBOLigado<C> p, C e, NodoBOLigado<C> hd){
    super(hi,  p,  e, hd);
  }

  /**
  *   Metodo para obtener el factor de balanceo de un nodo.
  *   @return El valor del factor de balanceo.
  */
  public int getFB(){
    int altHI;
    int altHD;
    if(this.hijoI == null) altHI = 0;
    else altHI = hijoI.getAltura() + 1;
    if(this.hijoD == null) altHD = 0;
    else altHD = hijoD.getAltura() + 1;
    return altHI - altHD;
  }

  /**
  *   Metodo para hacer rotacion hacia la derecha.
  *   @return El nodo padre ya balanceado.
  */
  public NodoAVL<C> rotaRR(){
    NodoAVL<C> pd = (NodoAVL<C>)this.papa;
    NodoAVL<C> desva = this;
    NodoAVL<C> newp = (NodoAVL<C>)this.hijoI;
    NodoAVL<C> sub = (NodoAVL<C>)newp.hijoD;
      newp.papa = pd;
      if(pd != null && pd.hijoI == this) pd.hijoI = newp;
      if(pd != null && pd.hijoD == this) pd.hijoD = newp;
      desva.papa = newp;
      newp.hijoD = desva;
      desva.hijoI = sub;
      if(sub != null) sub.papa = desva;
      return newp;
  }

  /**
  *   Metodo para hacer rotacion hacia la izquierda.
  *   @return El nodo padre ya balanceado.
  */
  public NodoAVL<C> rotaLL(){
    NodoAVL<C> pd = (NodoAVL<C>)this.papa;
    NodoAVL<C> desva = this;
    NodoAVL<C> newp = (NodoAVL<C>)this.hijoD;
    NodoAVL<C> sub = (NodoAVL<C>)newp.hijoI;
      newp.papa = pd;
      if(pd != null && pd.hijoI == this) pd.hijoI = newp;
      if(pd != null && pd.hijoD == this) pd.hijoD = newp;
      desva.papa = newp;
      newp.hijoI = desva;
      desva.hijoD = sub;
      if(sub != null) sub.papa = desva;
      return newp;

  }

  /**
  *   Metodo para al momento de remover un nodo balancear el arbol.
  *   @return El nodo eliminado.
  */
  public NodoAVL<C> removerAVL(){
    if(this.esHoja()){
      NodoAVL<C> padre = (NodoAVL<C>)this.papa;
      this.papa = null;
      this.elem = null;
      if(padre.hijoI == this) padre.hijoI = null;
      else padre.hijoD = null;
      return padre.balanceo();
    }else{
      C swap;
      NodoAVL<C> auxi;
      if(this.hijoI != null){
        auxi = (NodoAVL<C>)this.hijoI.der();
        swap = this.elem;
        this.elem = auxi.elem;
        auxi.elem = swap;
        return auxi.removerAVL();
      }else{
        if(this.hijoD != null){
          auxi = (NodoAVL<C>)this.hijoD.izq();
          swap = this.elem;
          this.elem = auxi.elem;
          auxi.elem = swap;
          return auxi.removerAVL();
        }
      }
    }
    return null;
  }

  /**
  *   Metodo para balancear un subarbol.
  *   @return El nuevo this balanceado.
  */
  public NodoAVL<C> balanceo(){
    NodoAVL<C> aux = this;
    NodoAVL<C> avl;
    NodoAVL<C> root = aux;
    while(aux != null){
      if(aux.getFB() == 2){
        avl = (NodoAVL<C>)aux.hijoI;
        if(avl.getFB() == 1 || avl.getFB() == 0) aux = aux.rotaRR();
        else if(avl.getFB() == -1){
                avl.rotaLL();
                aux = aux.rotaRR();
              }
      }
      if(aux.getFB() == -2){
        avl = (NodoAVL<C>)aux.hijoD;
        if(avl.getFB() == -1 || avl.getFB() == 0) aux = aux.rotaLL();
        else if(avl.getFB() == 1){
                avl.rotaRR();
                aux = aux.rotaLL();
              }
      }
      root = aux;
      aux = (NodoAVL<C>)aux.papa;
    }
    return root;
  }

}
