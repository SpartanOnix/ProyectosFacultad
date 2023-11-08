import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

//Clase para crear, modificar y borrar archivos CSV.
class Escritor{

  //Archivo que se crea con el nombre Info.csv.
  private static File file = new File("Info.csv");
  
  //Metodo que crea o reinicia el archivo Info.csv .
  public static void creaArchivo(){
    file = new File("Info.csv");
    FileWriter arch;
    try {
      arch = new FileWriter(file);
      arch.write("Nombre chofer, Nombre dueño, Numero taxi \n");
      arch.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
  
  /**
   * Metodo que escribe en el archivo CSV.
   * @param La informacion a escribir de la forma: X,Y,Z.
   */
  public static void escribeArchivo(String info){   
    try {
      FileWriter fileW = new FileWriter("Info.csv", true);
      BufferedWriter bw = new BufferedWriter(fileW);
      bw.write(info + "\n");
      bw.close();
      fileW.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
  
  /**
   * Metodo que lee el archivo CSV.
   * @return Un array donde en cada indice tiene una linea del CSV.
   */
  public static String[] leeArchivo(){
  Scanner input = null;
  String lineas = "";
  try {
   input = new Scanner(file);
   while (input.hasNextLine()) {
    lineas += input.nextLine() + "|";
   }
  } catch (Exception e) {
    System.out.println("No se encontro ningun archivo");
  }
  String lines [] = lineas.split("\\|");
  return lines;
 }
  
  
  public static void main(String[] args){
  
    Scanner scan = new Scanner(System.in);    
    String[] info = {"","",""};
    String salir = "a";
    
    while(salir != "x"){
      System.out.println("\nHola, ingresa el numero de lo que quieras hacer: \n1.-Crear un nuevo archivo (solo si no haz creado ninguno) \n2.-Escribir nueva informacion \n3.-Actualizar informacion \n4.-Borrar informacion \n5.-Salir \n");
      String opccion = scan.nextLine();
      switch(opccion){
    
        //Crea el Archivo o lo reinicia si ya habia alguno existente.
        case "1": creaArchivo();
              System.out.println("Archivo creado o reiniciado.\n");
              break;
      
        //Agrega la informacion al CSV.
        case "2": System.out.println("\nNombre del chofer: ");
              String dato = scan.nextLine();
              info[0] = dato;
              System.out.println("Nombre del dueño: ");
              dato = scan.nextLine();
              info[1] = dato;
              System.out.println("Numero del taxi: ");
              dato = scan.nextLine();
              info[2] = dato;
              String informacion = info[0] + ", " + info[1] + ", " + info[2];
              System.out.println("Agregaste: " + informacion);
              escribeArchivo(informacion);
              break;
        
        //Modifica la linea del CSV que quieras con nueva informacion.
        case "3": String[] leido1 = leeArchivo();
              System.out.println("\nQue linea quieres modificar?\n");
              String aux1 = scan.nextLine();
              int linea1 = Integer.parseInt(aux1);
              System.out.println("\nNombre del chofer: ");
              String dato1 = scan.nextLine();
              info[0] = dato1;
              System.out.println("Nombre del dueño: ");
              dato1 = scan.nextLine();
              info[1] = dato1;
              System.out.println("Numero del taxi: ");
              dato1 = scan.nextLine();
              info[2] = dato1;
              String informacion1 = info[0] + ", " + info[1] + ", " + info[2];
              leido1[linea1] = informacion1 + "\n";
              String nuevo1 = "";
              for(int i=1; i<leido1.length; i++){
                nuevo1 = nuevo1 + leido1[i];
              }
              System.out.println("Agregaste: " + informacion1);
              creaArchivo();
              escribeArchivo(nuevo1);
          break;
        
        //Borra la linea que quieras del CSV.
        case "4": String[] leido2 = leeArchivo();
              System.out.println("\nQue linea quieres modificar?\n");
              String aux = scan.nextLine();
              int linea = Integer.parseInt(aux);
              leido2[linea] = " , , \n";
              String nuevo = "";
              for(int i=1; i<leido2.length; i++){
                nuevo = nuevo + leido2[i];
              }
              creaArchivo();
              escribeArchivo(nuevo);
              System.out.println("Linea eliminada con exito\n");
          break;
        
        //Salida del programa.
        case "5": System.out.println("Hasta luego.");
              salir = "x";
              break;
           
        //Si meten algun caso invalido solo se vuelve a ejecutar.
        default: System.out.println("Opccion invalida");
               break;
      }
    }
  }
  
}