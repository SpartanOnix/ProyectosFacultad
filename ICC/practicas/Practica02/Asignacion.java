public class Asignacion {
 
 public static void main(String[] args) {
  int a = 5;
  int b = 2;
  int c = 3;
  //El resto de tu código va aquí 
  double resultado = a + b + c + a * b + c;
  System.out.println("Resultado 1: " + resultado); 
  resultado = (a + b) * c;
  System.out.println("Resultado 2: " + resultado); 
  resultado = a + b * c;
  System.out.println("Resultado 3: " + resultado); 
 }
}
