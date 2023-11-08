package ed.estructuras.nolineales;

/**
*   Clase que crea nodos para arboles AVL.
*   @author Dante Jusepee Sinencio Granados.
*/
public class NodoRojinegro<C extends Comparable<C>> extends NodoAVL<C>{

  private Color color;

  /**
  *   Creador de un atributo a utilizar  
  */
  public enum Color{
    ROJO,
    NEGRO
  }

  /**
  *   Constructor de nodos.
  *   @param hi - Hijo izquierdo.
  *   @param p - Nodo padre.
  *   @param e - Elemento que contiene el nodo.
  *   @param hd - Hijo derecho.
  */
  public NodoRojinegro(NodoBOLigado<C> hi, NodoBOLigado<C> p, C e, NodoBOLigado<C> hd){
    super(hi,  p,  e, hd);
    color = Color.ROJO;
  }

  /**
  *   Metodo para obtener el tio de un nodo.
  *   @return El nodo tio.
  */
  public NodoRojinegro <C> getTio(){
    NodoRojinegro <C> aux = (NodoRojinegro <C>)this;
    NodoRojinegro <C> don = (NodoRojinegro <C>)this.papa.papa;
    if((don.hijoI != null && don.hijoI.hijoI == aux) || (don.hijoI != null && don.hijoI.hijoD == aux)) return (NodoRojinegro <C>)don.hijoD;
    else return (NodoRojinegro <C>)don.hijoI;
  }

  /**
  *   Metodo para obtener el color de un nodo.
  *   @return El color del nodo.
  */
  public Color getColor(){
    return this.color;
  }

  /**
  *   Metodo para remover un nodo del arbol.
  *   @return La raiz del arbol.
  */
  public NodoRojinegro<C> removerRojinego(){
    if(this.esHoja()){
      NodoRojinegro<C> aux = this;
      if(this.color == Color.NEGRO) this.balancearRemove();
      aux = (NodoRojinegro<C>)this.obtenerRaiz();
      if(this.color == Color.ROJO){
        if(this.papa.hijoI == this) this.papa.hijoI = null;
        else if(this.papa.hijoD == this) this.papa.hijoD = null;
        aux = (NodoRojinegro<C>)this.obtenerRaiz();
        this.papa = null;
        this.elem = null;
      }
      return aux;
    }
    else{
      C elemento;
      NodoRojinegro <C> aux;
      if(this.hijoI != null){
        if(this.hijoD == null) aux = (NodoRojinegro<C>)this.hijoI.der();
        else aux = (NodoRojinegro<C>)this.hijoI.izq();
        elemento = this.elem;
        this.elem = aux.elem;
        aux.elem = elemento;
        return aux.removerRojinego();
      }else{
        if(this.hijoD != null){
          aux = (NodoRojinegro<C>)this.hijoD.izq();
          elemento = this.elem;
          this.elem = aux.elem;
          aux.elem = elemento;
          return aux.removerRojinego();
        }
      }
    }
    return null;
  }

  /**
  *   Metodo para balancear los colores al agregar.
  *   @return La raiz del arbol.
  */
  public NodoRojinegro<C> balancearAdd(){
    NodoRojinegro<C> aux = (NodoRojinegro<C>)this;
    NodoRojinegro<C> padre;
    NodoRojinegro<C> abuelo;
    if(aux.papa != null) padre = (NodoRojinegro<C>)aux.papa;
    else padre = null;
    if(aux.papa != null && aux.papa.papa != null) abuelo = (NodoRojinegro<C>)aux.papa.papa;
    else abuelo = null;
    if(aux.papa == null) aux.color = Color.NEGRO;
    else if(padre.color == Color.ROJO){
           if(aux.getTio() != null && aux.getTio().color == Color.ROJO){
             aux.getTio().color = Color.NEGRO;
             padre.color = Color.NEGRO;
             abuelo.color = Color.ROJO;
             abuelo.balancearAdd();
           }else if(aux.papa != null && aux.papa.papa != null && ((aux.papa.papa.hijoI == aux.papa && aux.papa.hijoD == aux) || (aux.papa.papa.hijoD == aux.papa && aux.papa.hijoI == aux))){
                   if(aux.papa.papa.hijoI == aux.papa) padre.rotaLL();
                   if(aux.papa != null && aux.papa.papa != null && aux.papa.papa.hijoD == aux.papa) padre.rotaRR();
                   aux = padre;
                   aux.balancearAdd();
                 }else{
                   padre.color = Color.NEGRO;
                   abuelo.color = Color.ROJO;
                   if(aux.papa.papa.hijoI != null && aux.papa.papa.hijoI.hijoI == aux) abuelo.rotaRR();
                   else if(aux.papa.papa.hijoD != null && aux.papa.papa.hijoD.hijoD == aux) abuelo.rotaLL();
                 }
         }
    return (NodoRojinegro <C>)aux.obtenerRaiz();
  }

  /**
  *   Metodo para balancear los colores al momento de remover.
  */
  public void balancearRemove(){
    NodoRojinegro<C> aux = (NodoRojinegro<C>)this;
    NodoRojinegro<C> hermano = (NodoRojinegro<C>)getHermanoSiguiente(aux);
    NodoRojinegro<C> newh;
    NodoRojinegro<C> padre = (NodoRojinegro<C>)aux.papa;
    NodoRojinegro<C> sobrinoI = (NodoRojinegro<C>)hermano.hijoI;
    NodoRojinegro<C> sobrinoD = (NodoRojinegro<C>)hermano.hijoD;
    while(aux.papa != null && aux.color == Color.NEGRO){
          if(hermano.color == Color.ROJO){
          hermano.color = Color.NEGRO;
          padre.color = Color.ROJO;
          if(aux.papa.hijoI == aux) padre.rotaLL();
          else padre.rotaRR();
        }else if((hermano.hijoI == null || sobrinoI.color == Color.NEGRO) && (hermano.hijoD == null || sobrinoD.color == Color.NEGRO)){
                hermano.color = Color.ROJO;
                aux = (NodoRojinegro<C>)aux.papa;
                if(aux.color == Color.ROJO){
                  aux.color = Color.NEGRO;
                  if(aux.papa.hijoI == aux) aux = (NodoRojinegro<C>)aux.hijoI;
                  else aux = (NodoRojinegro<C>)aux.hijoD;
                  aux.color = Color.ROJO;
                }
              }else if(aux.papa.hijoI == aux){
                      if(hermano.hijoD == null || sobrinoD.color == Color.NEGRO){
                        sobrinoI.color = Color.NEGRO;
                        hermano.color = Color.ROJO;
                        if(hermano.hijoD == null) hermano.hijoD = new NodoRojinegro(null,null,null,null);
                        padre.rotaRR();
                        newh = (NodoRojinegro<C>)getHermanoSiguiente(aux);
                        if(newh.hijoD.elem == null) newh.hijoD = null;
                      }else{
                        if(padre.color == Color.ROJO) padre.color = Color.NEGRO;
                        if(hermano.color == Color.ROJO) hermano.color = Color.NEGRO;
                        else hermano.color = Color.ROJO;
                        sobrinoD.color = Color.NEGRO;
                        padre.rotaLL();
                        aux.color = Color.ROJO;
                      }
                    }else if(hermano.hijoI == null || sobrinoI.color == Color.NEGRO){
                            sobrinoD.color = Color.NEGRO;
                            hermano.color = Color.ROJO;
                            if(hermano.hijoI == null) hermano.hijoI = new NodoRojinegro(null,null,null,null);
                            padre.rotaLL();
                            newh = (NodoRojinegro<C>)getHermanoSiguiente(aux);
                            if(newh.hijoI.elem == null) newh.hijoI = null;
                          }else{
                            if(padre.color == Color.ROJO) padre.color = Color.NEGRO;
                            if(hermano.color == Color.ROJO) hermano.color = Color.NEGRO;
                            else hermano.color = Color.ROJO;
                            sobrinoI.color = Color.NEGRO;
                            padre.rotaRR();
                            aux.color = Color.ROJO;
                          }
    }
    NodoRojinegro<C> root = (NodoRojinegro<C>)obtenerRaiz();
    if(root.color == Color.ROJO) root.color = Color.NEGRO;
  }

}
