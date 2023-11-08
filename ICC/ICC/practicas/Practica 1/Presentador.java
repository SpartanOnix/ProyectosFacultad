import java.util.Scanner;
public class Presentador{

    public static void main(String [] args){
      
      Scanner scan = new Scanner(System.in);
      System.out.println("Dame tu nombre");
      String d = scan.nextLine();
      System.out.println("Dame tu facultad");
      String nF = scan.nextLine();
      System.out.println("Dame tu carrera");
      String l = scan.nextLine();
      System.out.println("Dame tu edad");
      String e = scan.nextLine();
      
      System.out.println("hola, soy " + d + " estudio en la " + nF + ", en la carrera de " + l + " y tengo " + e + " años");
      
      String a = "hola, soy " + d + " estudio en la " + nF + ", en la carrera de " + l + " y tengo " + e + " años";
      System.out.println(a.toUpperCase());
 
    }
}
