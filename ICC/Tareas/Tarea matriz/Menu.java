import java.util.Scanner;
public class Menu{
  
  public static Matriz opcion1(){
    System.out.println("Escribe el archivo.txt donde este tu matriz");
    Scanner scan = new Scanner(System.in);
    String ruta = scan.nextLine();
    LeerArchivo la = new LeerArchivo();
    String[] lineas = la.leerArchivo(ruta);
    
    double[][] coeficientes = new double[lineas.length][];
    
    for(int i=0; i<lineas.length; i++){
      String[] fila = lineas[i].split(" ");
      double[] filaD = new double[fila.length];
      for(int j=0; j<fila.length; j++){
        filaD[j] = Double.parseDouble(fila[j]);
      }
      coeficientes[i] = filaD;
    }
    return new Matriz(coeficientes);
  }
  
  public static void main(String[] args){
    Matriz m = opcion1();
    System.out.println(m);
      boolean bandera = true;
      
      while(bandera){
        System.out.println("Opcciones:");
        System.out.println("1. Suma");
        System.out.println("2. Multiplicacion por un escalar");
        System.out.println("3. Multiplicacion por otra matriz");
        System.out.println("4. Traspuesta");
        System.out.println("5. Sistema de ecuaciones");
        System.out.println("6. Determinante");
        System.out.println("7. Cambiar de matriz");
        System.out.println("8. Cerrar el programa");
        Scanner scan = new Scanner(System.in);
        int opcion = scan.nextInt();
        
        switch(opcion){
          
          case 1:
            Matriz n = opcion1();
            Matriz suma= m.suma(n);
            System.out.println("M= " + "\n" + m);
            System.out.println("N= " + "\n" + m);
            System.out.println("M+N= " + "\n" + suma);
            break;
            
          case 2:
            System.out.println("La matriz se multiplicara por el numero: ");
             double multEsc = scan.nextInt();
             n= opcion1();
             Matriz escalar = m.multiplicarEscalar(multEsc);
             System.out.println("M=" + "\n" + m);
             System.out.println("Se multiplicara por el valor: " + multEsc);
             System.out.println("M * numero seleccionado =" + "\n" + escalar);
             break;
            
          case 3:
           n = opcion1();
           System.out.println("Multiplicar la Matriz M por la matriz: ");
           Matriz mul = m.multiplicacion(n);
           System.out.println("Multiplicacion de M= \n" + m + "y de N=\n"+ n);
           System.out.println("M * N = \n" + mul);
           break;
            
          case 4:
            Matriz traspuesta = m.Traspuesta();
            System.out.println("M= " + "\n" + m);
            System.out.println("Traspuesta de M= " +  "\n" + traspuesta);
            break;
            
          case 5:
            double[] ecuacion = m.ecuaciones();
            for(int i=0; i<ecuacion.length; i++){
              System.out.println("Resultado= " + (i+1) + " es " + ecuacion[i]);
            }
            System.out.println(" ");
            break;
            
          case 6:
            double resultado = m.determinante();
            System.out.println("El resultado de la determinante es: " + "\n" + resultado);
            break;
            
          case 7:
            n = opcion1();
            System.out.println("Se cambiara la matriz base: ");
            Matriz paraC = opcion1();
            Matriz clon = n.asignarMatriz(paraC);
            System.out.println("Cambiar M= \n" + m + "por N=\n"+ n);
            System.out.println("Se cambio M por N= \n" + clon);
            break;
            
          case 8:
            bandera = false;
            break;
            
          default:
            System.out.println("Opcion Invalida, selecciona una de las mencionadas");
            break;
        }
      }
  }
}