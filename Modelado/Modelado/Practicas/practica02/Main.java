import java.util.Scanner;
import java.util.Stack;

public class Main{

private static Stack <Integer> pilaNum = new Stack <>();
private static Stack <Character> pilaOpe = new Stack <>();

 private static void acomodar(String cadena){
   String num = "";
   int conver;
   for (int i = 0; i < cadena.length(); i++){
     if(cadena.charAt(i) == '+' || cadena.charAt(i) == '*'){
       pilaOpe.push(cadena.charAt(i));
       conver = Integer.parseInt(num);
       num = "";
       pilaNum.push(conver);
     }else{
       num = num + cadena.charAt(i);
       if(i == cadena.length()-1) {
         conver = Integer.parseInt(num);
         pilaNum.push(conver);
       };
     }
   }
 }

  public static int calculadora(String cadena){
    int res;
    int aux;
    char temp;
    if(cadena != "") acomodar(cadena);
    if(pilaOpe.peek() == '*'){
      aux = evalua(pilaOpe.pop(), pilaNum.pop(), pilaNum.pop());
      pilaNum.push(aux);
    }else{
      if(pilaOpe.peek() == '+'){
        temp = pilaOpe.pop();
        if(pilaOpe.empty() == true || pilaOpe.peek() == '+'){
          aux = evalua(temp, pilaNum.pop(), pilaNum.pop());
          pilaNum.push(aux);
        }else{
          int guardar = pilaNum.pop();
          aux = evalua(pilaOpe.pop(), pilaNum.pop(), pilaNum.pop());
          pilaNum.push(aux);
          pilaNum.push(guardar);
          pilaOpe.push(temp);
        }
      }
    }
    if(pilaOpe.empty() == false) calculadora("");
    res = pilaNum.peek();
    return res;
  }

  private static int evalua(char operador, int operando1, int operando2){
		int res = 0;
		if(operador == '+') res = operando1 + operando2;
		if(operador == '*') res = operando1 * operando2;
		return res;
	}

  public static void main(String[] args){

    Scanner scan = new Scanner(System.in);
    String cadena = scan.nextLine();
    int resultado = calculadora(cadena);
    System.out.println(resultado);

  }

}
