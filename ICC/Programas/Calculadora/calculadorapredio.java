import java.util.Scanner;
public class calculadorapredio{

  public static int calcular(int anoin, int anofin, int dinero){
    int money = dinero;
    int saldo = 0;
    for(int i=anoin; i<=anofin; i++){
      saldo += (money*6);
      money += 2;
    }
    return saldo;
  }

  public static void main(String[] args){
    int ano1 = 0;
    int ano2 = 0;
    int din = 0;
    int total = 0;
    Scanner scan = new Scanner(System.in);
    System.out.println("introduzca su año de inicio");
    ano1 = scan.nextInt();
    System.out.println("introduzca su ultimo año de pago");
    ano2 = scan.nextInt();
    System.out.println("introduzca su primer valor de pago");
    din = scan.nextInt();
    total = calcular(ano1, ano2, din);
    System.out.println("su total a pagar es: " + total);
  }
}
