package ed.estructuras.nolineales;

public class NodoRojinegro<C extends Comparable<C>> extends NodoAVL<C>{

  private Color color;

  public enum Color{
    ROJO,
    NEGRO
  }

  public NodoRojinegro(NodoBOLigado<C> hi, NodoBOLigado<C> p, C e, NodoBOLigado<C> hd){
    super(hi,  p,  e, hd);
    color = Color.ROJO;
  }

  public NodoRojinegro <C> getTio(){
    NodoRojinegro <C> aux = (NodoRojinegro <C>)this;
    NodoRojinegro <C> don = this.papa.papa;
    if((don.hijoI != null && don.hijoI.hijoI == aux) || (don.hijoI != null && don.hijoI.hijoD == aux)) return don.hijoD;
    else return don.hijoI;
  }

  public NodoRojinegro<C> removerRojinego(){
    if(this.esHoja()){
      if(this.color == Color.NEGRO) this.balancearResta();
      if(this.color == Color.ROJO){
        this.padre = null;
        this.elemento = null;
        if(this.papa.hijoI == this) this.papa.hijoI = null;
        else this.papa.hijoD = null;
      }
      return obtenerRaiz();
    }
    else{
      C elemento
      NodoRojinegro <C> aux;
      if(this.hijoI != null){
        if(this.hijoD == null) aux = (NodoRojinegro <C>)this.hijoI.der();
        else aux = (NodoRojinegro <C>)this.hijoI.izq();
        elemento = this.elem;
        this.elem = aux.elem;
        aux.elem = elemento;
        return aux.removerRojinego();
      }else{
        if(this.hijoD != null){
          aux = this.hijoD.izq();
          elemento = this.elem;
          this.elem = aux.elem;
          aux.elem = elemento;
          return aux.removerRojinego();
        }
      }
    }
    return null;
  }

  public NodoRojinegro<C> balancearAdd(){
    NodoRojinegro<C> aux = (NodoRojinegro<C>)this;
    if(aux.papa == null) aux.color = Color.NEGRO;
    else if(aux.papa.color == Color.ROJO){
           if(aux.getTio() != null && aux.getTio().color == Color.ROJO){
             aux.getTio().color = Color.NEGRO;
             aux.papa = Color.NEGRO;
             aux.papa.papa = Color.ROJO;
             aux.papa.papa.balancearAdd();
           }else if((aux.papa.papa.hijoI == aux.papa && aux.papa.hijoD == aux) || (aux.papa.papa.hijoD == aux.papa && aux.papa.hijoI == aux)){
                   NodoRojinegro<C> padre = aux.papa;
                   if(aux.papa.papa.hijoI == aux.papa) aux.papa.rotaLL();
                   if(aux.papa.papa.hijoD == aux.papa) aux.papa.rotaRR();
                   aux = padre;
                   aux.balancearAdd();
                 }else{
                   aux.papa.color = Color.NEGRO;
                   aux.papa.papa = Color.ROJO;
                   if(aux.papa.papa.hijoI != null && aux.papa.papa.hijoI.hijoI == aux) aux.papa.papa.rotaRR();
                   else if(aux.papa.papa.hijoD != null && aux.papa.papa.hijoD.hijoD == aux) aux.papa.papa.rotaLL();
                 }
         }
    return aux.obtenerRaiz();
  }

  public void balancearRemove(){
    NodoRojinegro<C> aux = (NodoRojinegro<C>)this;
    NodoRojinegro<C> hermano = (NodoRojinegro<C>)getHermanoSiguiente(aux);
    while(aux.papa != null && aux.color == Color.NEGRO){
        if(hermano.color == Color.ROJO){
          hermano.color = Color.NEGRO;
          aux.papa.color = Color.ROJO;
          if(aux.papa.hijoI == aux) aux.papa.rotaLL();
          else aux.papa.rotaRR();
        }else if((hermano.hijoI == null || hermano.hijoI.color == Color.NEGRO) && (hermano.hijoD == null || hermano.hijoD.color == Color.NEGRO)){
                hermano.color = Color.ROJO;
                aux = aux.papa;
                if(aux.color == Color.ROJO){
                  aux.color = Color.NEGRO;
                  if(aux.papa.hijoI == aux) aux = aux.hijoI;
                  else aux = aux.hijoD;
                  aux.color = Color.ROJO;
                }
              }else if(aux.papa.hijoI == aux){
                      if(hermano.hijoD == null || hermano.hijoD.color == Color.NEGRO){
                        hermano.hijoI.color = Color.NEGRO;
                        hermano.color = Color.ROJO;
                        if(hermano.hijoD == null) hermano.hijoD = new NodoRojinegro(null,null,null,null);
                        aux.papa.rotaRR();
                        if(getHermanoSiguiente(aux).hijoD.elem == null) hermano.hijoD = null;
                      }else{
                        if(aux.papa.color == Color.ROJO) aux.papa.color = Color.NEGRO;
                        if(hermano.color == Color.ROJO) hermano.color = Color.NEGRO;
                        else hermano.color = Color.ROJO;
                        hermano.hijoD.color = Color.NEGRO;
                        aux.papa.rotaLL();
                        aux.color = Color.ROJO;
                      }
                    }else if(hermano.hijoI == null || hermano.hijoI.color == Color.NEGRO){
                            hermano.hijoD.color = Color.NEGRO;
                            hermano.color = Color.ROJO;
                            if(hermano.hijoI == null) hermano.hijoI = new NodoRojinegro(null,null,null,null);
                            aux.papa.rotaLL();
                            if(getHermanoSiguiente(aux).hijoI.elem == null) hermano.hijoI = null;
                          }else{
                            if(aux.papa.color == Color.ROJO) aux.papa.color = Color.NEGRO;
                            if(hermano.color == Color.ROJO) hermano.color = Color.NEGRO;
                            else hermano.color = Color.ROJO;
                            hermano.hijoI.color = Color.NEGRO;
                            aux.papa.rotaRR();
                            aux.color = Color.ROJO;
                          }
    }
    NodoRojinegro<C> root = obtenerRaiz();
    if(root.color == Color.ROJO) root.color = Color.NEGRO;
  }

}
