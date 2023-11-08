import java.util.Scanner;
public class Menu{
  
  public static void main(String [] pps){
    Administrador admin = new Administrador("777", 2000.00);
    Scanner scan = new Scanner(System.in);
    
    System.out.println("Sistema para dar de Alta a Profesores de la Facultad de Ciencias (SAPFC)");
    System.out.println("-> Datos para dar de alta a un profesor:");
    System.out.println("Numero de trabajador:");
    String noTr = scan.next();
    System.out.println("Sueldo:");
    double sueldo = scan.nextDouble();
    System.out.println("Grado o titulo:");
    String grado = scan.next();
    Profesor prof = new Profesor(noTr, sueldo, grado);
    
    System.out.println("-> Datos de la materia: ");
    System.out.println("Nombre:");
    String nombre = scan.next();
    System.out.println("Clave:");
    String clave = scan.next();
    System.out.println("Creditos:");
    int creditos = scan.nextInt();
    Materia m = new Materia(nombre, clave, creditos);
    admin.asignarMateria(prof, m);
    
    System.out.println("-> Datos para dar de alta a un ayudante:");
    System.out.println("Numero de trabajador:");
    String no = scan.next();
    System.out.println("Sueldo:");
    double sue = scan.nextDouble();
    System.out.println("Grado o titulo:");
    String grad = scan.next();
    System.out.println("Promedio:");
    double promedio = scan.nextDouble();
    Ayudante ayudante = new Ayudante(no, sue, grad, promedio);
    ayudante.pedirAyudantia(prof);
    System.out.println("Selecciona un ayudante de la lista de candidatos (solo hay 1 candidato por el momento)");
    int n = scan.nextInt();
    prof.asignarAyudante(n-1);
    
    System.out.println("Datos del profesor: " + prof);
    System.out.println("Datos del ayudante: " + ayudante);
  }
}