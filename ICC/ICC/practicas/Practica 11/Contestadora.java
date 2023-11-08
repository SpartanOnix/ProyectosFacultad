import java.util.Scanner;

/**
 * Clase para crear una contestadora 
 * @author Sinencio Granados Dante Jusepee
 */
public class Contestadora {
    
    //Estructura de la contestadora
  private static Mensaje [] mensajes;
    
    /**
     * Constructor de una contestadora con capacidad para 10 mensajes
     */
    public Contestadora() {
      mensajes = new Mensaje[10];

    }

    /**
     * Constructor de una contestadora con capacidad para n mensajes
     * @param n - capacidad de la contestadora
     */
    public Contestadora(int n) {
      mensajes = new Mensaje[n];

    }

    /**
     *  Metodo que agrega un nuevo mensaje a la contestadora.
     *  @param nuevoMensaje Nuevo mensaje que sera agregado a la contestadora.
     */
    private static void agregarMensaje(Mensaje nuevoMensaje){
      for(int i=0; i<mensajes.length; i++){
        if(mensajes[i] != null){
          System.out.println("El espacio " + (i+2) + " de la contestadora esta ocupado");
        }else{
          if(mensajes[i] == null || mensajes[i].fueEscuchado() == true){
            mensajes[i] = nuevoMensaje;
            break;
          }
        }
        if(i==9 && mensajes[i] != null){
          System.out.println("La contestadora esta llena");
        }
      }

    }
    
    /**
     *  Metodo que imprime en pantalla el contenido de la contestadora.
     */
    private static void escucharMensajes(){
      for(int i=0; i<mensajes.length-1; i++){
        if(mensajes[i] != null){
          System.out.println(mensajes[i].escuchar());
        }else{
          System.out.println("No hay mensajes nuevos");
          break;
        }
      }
    }
    
     /**
     *  Metodo para ver el ultimo mensaje en la contestadora
     */
    private void ultimoMensaje(){

        for(int i = mensajes.length-1; i >=0; i--){
            if(mensajes[i]!=null){
            System.out.println(mensajes[i].escuchar() +"\n");
            break;
            } 
        }
    }
    
    
      /*
       * Metodo auxiliar para mostrar el menu de opciones
       * NO MODIFICARLO
       */
    private static int menu(){
      Scanner in = new Scanner(System.in); 

      int opcion = 0;
      boolean validacion = true;

      System.out.println("\nContestadora");
      System.out.println("1. Agregar mensaje");
      System.out.println("2. Revisar mensaje reciente");
      System.out.println("3. Revisar mensajes");
      System.out.println("4. Salir");
      System.out.print("Seleccionar una opcion --> ");

      do{
        opcion = 0;
        validacion = true;
        try{
          opcion = in.nextInt();       
        }catch(Exception e){
          validacion = false;
        }
        if(opcion < 1 || opcion > 4){
          validacion = false;
          System.out.println("\nOpcion invalida.\nIntroduzca 1, 2, 3 o 4.\n");
          return 100;
        }
      } while(!validacion);
      return opcion;
    }

    
    public static void main(String pps[]){
 //Programar el funcionamiento de la contestadora 
      Contestadora nm = new Contestadora();
      boolean bandera = true;
      
      while(bandera == true){
        switch(nm.menu()){
          case 1:
            System.out.println("Deje su mensaje porfavor");
            Scanner scan = new Scanner(System.in);
            String s = scan.nextLine();
            Mensaje nuevo = new Mensaje(s);
            nm.agregarMensaje(nuevo);
            break;

          
          case 2:
            nm.ultimoMensaje();
            break;
          
          case 3:
            nm.escucharMensajes();
            break;
          
          case 4:
            bandera = false;
            break;
          
          default:
            break;
      }
        
      }
      
      
    }
    
}
