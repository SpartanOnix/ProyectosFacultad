package ed.estructuras.lineales;

import java.util.Collection;

/**
 * Estructura "Último en entrar, ultimo en salir".
 * Corresponde a una cola que no permite almacenar elementos nulos.
 * @author Dante Jusepee Sinencio Granados
 */
public interface ICola<E> extends Collection<E>{

  /**
   * Agrega un elemento al final de la cola.
   * @param e Referencia al elemento a agregar.
   */
  public void forma(E e);

  /**
   * Devuelve el elemento al principio de la cola y lo elimina.
   * Devuelve <code>null</code> si está vacía.
   * @return Una referencia al elemento siguiente.
   */
  public E atiende();

  /**
   * Muestra el primer elemento formado en la cola.
   * Devuelve <code>null</code> si está vacía.
   * @return Una referencia al elemento en la cabeza de la cola.
   */
  public E mira();
}
