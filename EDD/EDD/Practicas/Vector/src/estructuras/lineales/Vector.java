/*
 * Código utilizado para el curso de Estructuras de Datos.
 * Se permite consultarlo para fines didácticos en forma personal,
 * pero no está permitido transferirlo resuelto a estudiantes actuales o potenciales.
 */
package estructuras.lineales;

/**
 * Arreglo redimensionable.
 * @author veronica
 */
public class Vector<T> {
    public static final int INC = 10;
    private Object[] buffer;
    private Object[] aux;

    /**
     * Constructor que crea un <code>Vector</code> con capacidad inicial INC.
     */
    public Vector() {
        buffer = new Object[INC];
    }

    /// MÉTODOS DE ACCESO

    /**
     * Devuelve el elemento almacenado en la posición <code>i</code>.
     * @param i el índice del objeto a recuperar.
     * @return el elemento almacenado en la posición <code>i</code>.
     * @throws IndexOutOfBoundsException si
     *         <code>!(0 &lt;= i &lt; this.leeCapacidad()) </code>.
     */
    public T lee(int i) {
      if(i < 0 || i > buffer.length-1) throw new IndexOutOfBoundsException();
      else return (T)buffer [i];

    }

    /**
     * Devuelve la capacidad actual de este <code>Vector</code>.
     * @return la capacidad actual del <code>Vector</code>.
     */
    public int leeCapacidad() {
      return buffer.length;

    }

    /// MÉTODOS DE MANIPULACIÓN

    /**
     * Almacena el elemento <code>e</code> en la posición <code>i</code>.
     * @param i el índice en el cual <code>e</code> será almacenado.
     *          Debe cumplirse <code>0 &lt;= i &lt; this.leeCapacidad() </code>.
     * @param e el elemento a almacenar.
     * @throws IndexOutOfBoundsException si
     *         <code>!(0 &lt;= i &lt; this.leeCapacidad()) </code>.
     */
    public void asigna(int i, T e) {
      if(i < 1) throw new IndexOutOfBoundsException();
      else buffer[i] = e;

    }

    /**
     * Asigna la capacidad del <code>Vector</code>.
     * Si <code>n &lt; this.leeCapacidad()</code> los elementos de
     * <code>n</code> en adelante son descartados.
     * Si <code>n &gt; this.leeCapacidad()</code> se agregan <code>null</code>
     * en los espacios agregados.
     * @param n la nueva capacidad del <code>Vector</code>, debe ser mayor que
     *          cero.
     * @throws IllegalSizeException si <code>n &lt; 1</code>.
     */
    public void asignaCapacidad(int n) {

      if(n <= 0) throw new IllegalSizeException();
      if(n < buffer.length){
        aux = new Object[n];
        for(int i=0; i<n; i++){
          //buffer [i] = null;
          aux [i] = buffer [i];
        }
        buffer = aux;
      }
      if(n >= buffer.length){
        aux = new Object[n];
        for(int i=0; i>buffer.length; i++){
          //buffer [i] = null;
          aux [i] = buffer [i];
        }
        for(int i=buffer.length; i<n; i++){
          aux [i] = null;
        }
        buffer = aux;
      }

    }

    /**
     * Garantiza que el <code>Vector</code> cuente al menos con capacidad para
     * almacenar <code>n</code> elementos.
     * Si <code>n &gt; this.leeCapacidad()</code> el tamaño del
     * <code>Vector</code> es incrementado de tal modo que el requerimiento
     * sea satisfecho con cierta holgura.
     * @param n capacidad mínima que debe tener el <code>Vector</code>,
     *          no puede ser menor a cero.
     */
    public void aseguraCapacidad(int n) {
      //if(n <= 0) throw new NegativeArraySizeException();
      if(n > buffer.length && n > 0){
        double i = (double)n/INC;
        int t = (int)Math.ceil(Math.log(i)/Math.log(2));
        //buffer = new Object[(int)Math.pow(2,i)*INC];
        int aum = (int)Math.pow(2,t)*INC;
        asignaCapacidad(aum);
      }

    }
}
