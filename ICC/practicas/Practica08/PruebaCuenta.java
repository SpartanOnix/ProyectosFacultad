public class PruebaCuenta {
        
        public static void main(String[] args) {
                Cuenta cuenta2010 = new Cuenta(2010,30);        
                Cuenta cuenta2011 = new Cuenta(2011,3000);              
                System.out.println("Se creó la cuenta " + cuenta2011.obtenerNumCuenta() + " con un saldo = " + cuenta2011.obtenerDisponible());
                cuenta2011.retirar(50);
                System.out.println("La cuenta " + cuenta2011.obtenerNumCuenta() + " tiene un saldo = " + cuenta2011.obtenerDisponible());
                cuenta2011.retirar(-10);
                System.out.println("Se va a realizar un retiro de $400");       
                cuenta2011.retirar(400);        
                System.out.println("La cuenta " + cuenta2011.obtenerNumCuenta() + " tiene un saldo = " +  cuenta2011.obtenerDisponible());
                cuenta2011.retirar(4000);
                cuenta2011.depositar(3000);
                System.out.println("Se va a realizar un retiro de $3000");      
                cuenta2011.retirar(3000);       
                cuenta2011.depositar(-10);              
                cuenta2011.depositar(1000);             
                System.out.println("La cuenta " + cuenta2011.obtenerNumCuenta() + " tiene un saldo de = " +  cuenta2011.obtenerDisponible());   
                System.out.println("FIN DEL PROGRAMA");
        }
}