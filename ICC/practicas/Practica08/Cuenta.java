/**
 * Clase para manejar cuentas de debito
 * @author Sinencio Granados Dante Jusepee
 * @version #1
 */
public class Cuenta {
  
    //Atributos
  private long numCuenta;
  private double saldo;

    /** Constructor que crea una cuenta con disponible mínimo de $2500
      * @param nCta - Es el numero de cuenta
      * @param disponibleInicial - Es el credito con el que creas la cuanta (debe de ser mayor a 2500)
      */
    Cuenta(long nCta, double disponibleInicial) {
      if(disponibleInicial > 2500){
        saldo = disponibleInicial;
        numCuenta = nCta;
      } else {
          System.out.println("No se puede crear la cuenta " + nCta + " con tan poco dinero, el minimo para crearla es de 2.500");
        }
    }


    /** Metodo que retira una cantidad de dinero en la cuenta
      * @param monto - Monto elegido para la transaccion
      */
    public void retirar(double monto) { 
      if(monto < 0){
        System.out.println("No se pueden retirar cantidades negativas");
      } else{
      if (monto > saldo){
        System.out.println("No hay suficiente dinero para retirar esa cantidad");
      } else{
        saldo = saldo - monto;
      }
        }
    }

    /** Metodo que deposita una cantidad de dinero en la cuenta
      * @param monto - Monto elegido para la transaccion 
      */
    public void depositar(double monto) {
      if (monto > 0){
        saldo += monto;
      } else{
        System.out.println("No se puede depositar dinero negativo");
      }
    }
    /** Metodo para obtener el disponible actual de la cuenta
      * @return Devuelve el disponible de la cuenta
      */
    public double obtenerDisponible() {
      return saldo;
  }

    /** Metodo para obtener el numero de cuenta
      * @return Devuelve el número de identificación de la cuenta bancaria
      */
    public long obtenerNumCuenta() {
      return numCuenta;
    } 
    
    /** Metodo para representar una cuenta
      * @return Devuelve una cadena
      */
    public String toString(){
      return "Tu saldo es " + saldo + " y tu numero de cuenta es " + numCuenta;
    }
}

