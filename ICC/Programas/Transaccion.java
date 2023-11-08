/**
 * Clase para realizar una transaccion de una cuenta a otra
 * @author Sinencio Granados Dante Jusepee
 * @version #1
 */

public class Transaccion {
    public static void main(String[] pps) {
      
 //Declara las dos cuentas y demas variables
      Cuenta ultra = new Cuenta(777,5000);
      Cuenta pobre = new Cuenta(115,3000);
      System.out.println("Se realizara una transaccion entre las cuentas: " + ultra.obtenerNumCuenta() + " y " + pobre.obtenerNumCuenta());
      System.out.println("El monto inicial de las cuentas es: " + "\n777: $" + ultra.obtenerDisponible() + "\n115: $" + pobre.obtenerDisponible());

 //Toma dinero de una cuenta
      ultra.retirar(1500);
      double saldoUltra = ultra.obtenerDisponible();

 //Deposita en la otra cuenta
      pobre.depositar(1500);

 // Muestra el saldo en ambas cuentas
      System.out.println("Su transaccion fue de $5000 y el saldo despues de esta quedo: " + "\n Cuenta 777: $" + ultra.obtenerDisponible() + "\n Cuenta 115: $" + pobre.obtenerDisponible());
    }
}
