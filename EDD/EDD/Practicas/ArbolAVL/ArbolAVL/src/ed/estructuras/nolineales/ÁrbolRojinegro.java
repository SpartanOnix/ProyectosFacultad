package ed.estructuras.nolineales;

public class ÁrbolRojinegro<C extends Comparable<C>> extends ÁrbolAVL<C>{

  public ÁrbolRojinegro(){
    raiz = null;
  }

  protected NodoBinario<C> creaNodo(C dato, NodoBinario<C> padre, NodoBinario<C> hijoI, NodoBinario<C> hijoD){
    return new NodoRojinegro((NodoAVL<C>)hijoI, (NodoAVL<C>)padre, dato, (NodoAVL<C>)hijoD);
  }

}
