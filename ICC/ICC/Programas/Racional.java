/**
 *  Esta clase se encarga de implementar n&uacute;meros racionales.
 * y sus principales operaciones.
 * @author Amparo Lopez Gaona
 */
public class Racional {
   
    /**
     *  Entero que representa el numerador del racional.
     */
    private int numerador;
    
    /**
     *  Entero que representa el denominador del racional.
     */
    private int denominador;
    
    /**
     *  Constructor que recibe como par&aacute;metro dos n&uacute;meros enteros
     *  que ser&aacute;n el numerador y el denominador respectivamente.
     *  @param nuevoNumerador -- numerador del nuevo racional.
     *  @param nuevoDenominador -- denominador del nuevo racional.
     */
    public Racional(int nuevoNumerador, int nuevoDenominador) {
      if(nuevoDenominador == 0){
        
        numerador = 0;
        denominador = 1;
      }else{
        numerador = nuevoNumerador;
        denominador = nuevoDenominador;
      }
    }
    
    /**
     *  Metodo que recibe un racional como par&aacute;metro y devuelve 
     *  la suma de este con el racional que llama al metodo.
     *  @param sumando -- Racional con el que se har&aacute; la suma.
     *  @return Racional -- resultado de efectuar la operacion de suma.
     */
    public Racional sumar(Racional sumando){
      Racional sumatoria = new Racional(1,1);
      if (this.denominador == sumando.denominador){
        sumatoria.numerador = this.numerador + sumando.numerador;
        sumatoria.denominador = this.denominador;
        return sumatoria;
      }else{
        sumatoria.numerador = this.numerador * sumando.denominador + this.denominador * sumando.numerador;
        sumatoria.denominador = this.denominador * sumando.denominador;
        return sumatoria;
      }
    }
    
    /**
     *  Metodo que recibe un racional como par&aacute;metro y devuelve 
     *  la multiplicacion de este con el racional que llama al metodo.
     *  @param factor -- Racional con el que se har&aacute; la multiplicacion.
     *  @return Racional -- resultado de efectuar la operacion de multiplicacion.
     */
    public Racional multiplicar(Racional factor){
      Racional multi = new Racional(1,1);
      multi.numerador = this.numerador * factor.numerador;
      multi.denominador = this.denominador * factor.denominador;
      return multi;

    }
    
    /**
     *  Metodo que devuelve la representacion en cadena del racional
     *  que lo llama.
     *  @return String -- Representacion en cadena del racional que llama al metodo.
     */
    public String toString(){
      return (numerador + "/" + denominador);

    }
    
    /**
     *  Metodo que recibe un objeto que se supone es otro Racional y determina
     *  si este y el Racional que llama al metodo son o no iguales.
     *  @param comparacion -- Racional con el que se har&aacute; la comparacion.
     *  @return boolean -- true si son iguales, false en caso contrario.
     */
    public boolean equals(Object comparacion){
      Racional rac = (Racional)comparacion;
        if (comparacion == null){
        return false;
        }
        if (obtenerDenominador() != rac.obtenerDenominador()){
          return false;
        }
        if (obtenerNominador() != rac.obtenerNominador()){
          return false;
        }
        if (obtenerDenominador() == rac.obtenerDenominador() && obtenerNominador() == rac.obtenerNominador()){
          return true;
        }
        return false;
    }    
    
    /**
     *  Metodo que devuelve el numerador del racional.
     *  @return int -- numerador del racional.
     */
    public int obtenerNominador(){
      return numerador;

    }
    
    /**
     *  Metodo que devuelve el denominador del racional.
     *  @return int -- denominador del racional.
     */
    public int obtenerDenominador(){
      return denominador;

    }
} 
