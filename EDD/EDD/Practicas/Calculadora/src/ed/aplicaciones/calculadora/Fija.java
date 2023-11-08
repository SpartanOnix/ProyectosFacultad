/*
 * Código utilizado para el curso de Estructuras de Datos.
 * Se permite consultarlo para fines didácticos en forma personal,
 * pero no está permitido transferirlo tal cual a estudiantes actuales o potenciales.
 */
package ed.aplicaciones.calculadora;

import java.util.Stack;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Clase para evaluar expresiones en notaciones prefija y postfija.
 *
 * @author blackzafiro
 */
public class Fija {

	private static Stack <String> pila = new Stack<>();
	private static LinkedList <String> cola = new LinkedList<>();

	/**
	 * Evalúa la operación indicada por <code>operador</code>.
	 */
	private static double evalua(char operador, double operando1, double operando2) {
		double res = 0;
		if(operador == '+') res = operando1 + operando2;
		if(operador == '-') res = operando1 - operando2;
		if(operador == '*') res = operando1 * operando2;
		if(operador == '/') res = operando1 / operando2;
		return res;
	}

	/**
	 * Recibe la secuencia de símbolos de una expresión matemática en notación
	 * prefija y calcula el resultado de evaluarla.
	 *
	 * @param tokens Lista de símbolos: operadores y números.
	 * @return resultado de la operación.
	 */
	public static double evaluaPrefija(String[] tokens) {
		double opr1 = 0;
		double opr2 = 0;
		for(int n=tokens.length; n>1; n--){
			if(tokens[n].charAt(0) != '+' && tokens[n].charAt(0) != '-' && tokens[n].charAt(0) != '*' && tokens[n].charAt(0) != '/'){
				pila.push(tokens[n]);
			}else{
				opr1 = Double.parseDouble(pila.pop());
				opr2 = Double.parseDouble(pila.pop());
				pila.push(String.valueOf(evalua(tokens[n].charAt(0), opr1, opr2)));
			}
		}
		return Double.parseDouble(pila.pop());
	}

	/**
	 * Recibe la secuencia de símbolos de una expresión matemática en notación
	 * postfija y calcula el resultado de evaluarla.
	 *
	 * @param tokens Lista de símbolos: operadores y números.
	 * @return resultado de la operación.
	 */
	public static double evaluaPostfija(String[] tokens) {
		double opr1 = 0;
		double opr2 = 0;
		for(int n=0; n<tokens.length-1; n++){
			if(tokens[n].charAt(0) != '+' && tokens[n].charAt(0) != '-' && tokens[n].charAt(0) != '*' && tokens[n].charAt(0) != '/'){
				pila.push(tokens[n]);
			}else{
				opr1 = Double.parseDouble(pila.pop());
				opr2 = Double.parseDouble(pila.pop());
				pila.push(String.valueOf(evalua(tokens[n].charAt(0), opr1, opr2)));
			}
		}
		return Double.parseDouble(pila.pop());
	}

	/**
	 * Interfaz de texto para la calculadora.
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String sentence;
		String method = "prefija";
		String delims = "\\s+|(?<=\\()|(?=\\))";
		String[] tokens;
		while (true) {
			sentence = scanner.nextLine();
			switch (sentence) {
				case "exit":
					return;
				case "prefija":
				case "postfija":
					System.out.println("Cambiando a notación " + sentence);
					method = sentence;
					continue;
				default:
					break;
			}
			tokens = sentence.split(delims);
			System.out.println(Arrays.toString(tokens));
			if (method.equals("postfija")) {
				System.out.println("= " + evaluaPostfija(tokens));
			} else {
				System.out.println("= " + evaluaPrefija(tokens));
			}

		}
	}
}
