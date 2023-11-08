import java.util.Scanner;
/**
 * Programa para calcular la edad de una persona
 * Objetivo. crear y utilizar objetos de la clase Fecha
 * @author 
 * @version
 */ 
public class Edad {
    public static void main(String[] pps) {
 Scanner in = new Scanner(System.in);
 

 //Declarar las variables

 System.out.println("Dame tu nombre ");
 String nombre = in.nextLine();

 //Recabar informacion
 System.out.println("Hola " + nombre + ", dime tu fecha de nacimineto, cada dato ponlo en una linea diferente de la forma dd mm aaaa");
 int d = in.nextInt();
 int m = in.nextInt();
 int a = in.nextInt();
 
 Fecha nacimiento = new Fecha( d, m, a);
 
 //Validar la informacion 
 if(d > 31){
   System.out.println("Dia no valido");
   return;
  }else{if(m > 12){
     System.out.println("Mes no valido");
     return;
   }else{if(a < 1900){
      System.out.println("Año no valido");
      return;
    }}
   } 
  System.out.println("Naciste el: " + nacimiento);
 
 
 //Calcular edad
 Fecha hoy = new Fecha();
 int Tn = nacimiento.diasTranscurridos();
 int Th = hoy.diasTranscurridos();
 int edadD = (-1) * (Tn - Th);
 int edadA = edadD / 365;
 System.out.println("Tienes: " + edadD + " dias de edad que son " + edadA + " años");

 //Calcular cantidad de dias para el proximo cumpleanos
 int dh = hoy.obtenerDia();
 int mh = hoy.obtenerMes();
 int df;
 int mf;
 if(d > dh){
   df = (-1) * (dh - d);
  }else{
    df = d + (31 - dh);
  }
 if(m > mh){
   mf = (-1) * (mh - m);
  }else{
    mf = m + (12 - mh);
  }
   System.out.println("Faltan " + df + " dias y " + mf + " meses para tu proximo cumpleaños");
   
 //Calcular en que rango de edad esta
 if(edadA < 18){
   System.out.println("Eres menor de edad, todavia estas chamaco");
   return;
  }else{if(edadA > 17 && edadA < 31){
     System.out.println("Estas joven, espero que ya estes pensando en independizarte");
     return;
  }else{if(edadA > 30 && edadA < 70){
      System.out.println("Eres un adulto, ¿Cuanto pelo te queda?");
      return;
   }else{if(edadA > 69){
       System.out.println("Eres un adulto mayor, ¿Como llegaste aqui?");
       return;
     }
    }
  }
    }
    }} 
