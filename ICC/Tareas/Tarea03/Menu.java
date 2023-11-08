import java.util.Scanner;
public class Menu{

  public static void main(String [] pps){


    Scanner scan = new Scanner(System.in);
    boolean bandera = true;
    System.out.println("Bienvenido al sistema de la facultad de ciencias");

    System.out.println("Entra a tu cuenta de administrador");
    System.out.println("Mete tu numero o nickname de trabajador:");
    String noTr = scan.next();
    Administrador admin = new Administrador(noTr, 2800.00);


    while(bandera == true){

      System.out.println("\n¿Que quieres hacer?");
      System.out.println("1. Dar de alta a un profesor");
      System.out.println("2. Dar de alta a un ayudante");
      System.out.println("3. Ver perfil del administrador");
      System.out.println("4. Salir");
      try{
        scan = new Scanner(System.in);
        int opcion = scan.nextInt();

        switch(opcion){
          case 1:
            System.out.println("\nSistema para dar de Alta a Profesores de la Facultad de Ciencias (SAPFC)");
            System.out.println("-> Datos para dar de alta a un profesor:");
            System.out.println("  -Numero de trabajador o nombre:");
            noTr = scan.next();
            System.out.println("  -Grado o titulo:");
            String grado = scan.next();
            Profesor profesor = new Profesor(noTr, 3000.00, grado);

            System.out.println("-> Datos de la materia: ");
            System.out.println("  -Nombre:");
            String nombre = scan.next();
            System.out.println("  -Clave:");
            String clave = scan.next();
            System.out.println("  -Creditos:");
            int creditos = scan.nextInt();
            Materia m = new Materia(nombre, clave, creditos);
            admin.asignarMateria(profesor, m);

            System.out.println("\nDatos del profesor: " + profesor);
            break;

          case 2:
            System.out.println("\nSistema para dar de Alta a Ayudantes de la Facultad de Ciencias (SAAFC)");
            System.out.println("Numero de trabajador o profesor al que se le asignara el ayudante:");
            noTr = scan.next();
            grado = "Profesor";
            Profesor profe = new Profesor(noTr, 3000.00, grado);

            System.out.println("-> Datos de la materia: ");
            System.out.println("Nombre:");
            String nombr = scan.next();
            System.out.println("Clave:");
            String clav = scan.next();
            System.out.println("Creditos:");
            int credito = scan.nextInt();
            Materia mi = new Materia(nombr, clav, credito);
            admin.asignarMateria(profe, mi);

            System.out.println("-> Datos para dar de alta a un ayudante:");
            System.out.println("Numero de trabajador o nombre del ayudante:");
            String no = scan.next();
            System.out.println("Grado o titulo:");
            String grad = scan.next();
            System.out.println("Promedio:");
            double promedio = scan.nextDouble();
            Ayudante ayudan = new Ayudante(no, 2500.00, grad, promedio);
            ayudan.pedirAyudantia(profe);
            System.out.println("Selecciona un ayudante de la lista de candidatos (solo hay 1 candidato por el momento)");
            int n = scan.nextInt();
            profe.asignarAyudante(n-1);
            System.out.println("Datos del ayudante: " + ayudan);
            break;

          case 3:
            System.out.println("\nDatos del administrador: " + admin);
            break;

          case 4:
            bandera = false;
            break;

          default:
            System.out.println("Escoge una opccion valida");
            return;
        }
      }catch(java.util.InputMismatchException e){
        System.out.println("\nDebes de poner una cantidad entera a los creditos");
      }
    }
  }
}
