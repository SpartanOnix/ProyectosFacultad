/*
 * Código utilizado para el curso de Estructuras de Datos.
 * Se permite consultarlo para fines didácticos en forma personal,
 * pero no está permitido transferirlo tal cual a estudiantes actuales o potenciales.
 */
package ed.aplicaciones.calculadora;

import java.util.Stack;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Clase para evaluar expresiones en notación infija.
 *
 * @author blackzafiro
 */
public class Infija {

	private static Stack <String> pila = new Stack <>();
	private static LinkedList <String> cola = new LinkedList<>();
	private static String [] oper;

	/**
	 * Devuelve la precedencia de cada operador. Entre mayor es la precedencia,
	 * más pronto deverá ejecutarse la operación.
	 *
	 * @operador Símbolo que representa a las operaciones: +,-,*,/ y '('.
	 * @throws UnsupportedOperationException para cualquier otro símbolo.
	 */
	 private static int precedencia(char operador) {
		//if(operador != '+' || operador != '-' || operador != '*' ||operador != '/' || operador != '(') throw new UnsupportedOperationException();
		if(operador == '(') return 1;
		if(operador == '+' || operador == '-') return 2;
		if(operador == '*' || operador == '/') return 3;
		throw new UnsupportedOperationException();
	}

	/**
	 * Pasa las operaciones indicadas en notación infija a notación sufija o
	 * postfija.
	 *
	 * @param tokens Arreglo con símbolos de operaciones (incluyendo paréntesis)
	 * y números (según la definición aceptada por
	 * <code>Double.parseDouble(token)</code> en orden infijo.
	 * @return Arreglo con símbolos de operaciones (sin incluir paréntesis) y
	 * números en orden postfijo.
	 */
	public static String[] infijaASufija(String[] tokens) {
		oper = new String [tokens.length];
		char [] token = new char [tokens.length];
		for(int n=0; n<tokens.length-1; n++){
			if(tokens[n].charAt(0) != '+' && tokens[n].charAt(0) != '-' && tokens[n].charAt(0) != '*' && tokens[n].charAt(0) != '/'){
				cola.offer(tokens[n]);
			}else{
				if(tokens[n].charAt(0) == ')'){
					while(pila.peek().charAt(0) != '('){
						cola.offer(pila.pop());
					}
				}else{
					int prec = precedencia(tokens[n].charAt(0));
					while(precedencia(pila.peek().charAt(0)) <= prec){
						cola.offer(pila.pop());
						pila.push(tokens[n]);
					}
				}
		  }
		}
		while(pila.empty() != true){
			cola.offer(pila.pop());
		}
		for(int n=0; n<tokens.length-1; n++){
			oper[n] = cola.poll();
		}
		return oper;
	}

	/**
	 * Recibe la secuencia de símbolos de una expresión matemática en notación
	 * infija y calcula el resultado de evaluarla.
	 *
	 * @param tokens Lista de símbolos: operadores, paréntesis y números.
	 * @return resultado de la operación.
	 */
	public static double evaluaInfija(String[] tokens) {
		String[] suf = infijaASufija(tokens);
		System.out.println("Sufija: " + Arrays.toString(suf));
		return Fija.evaluaPostfija(suf);
	}

	/**
	 * Interfaz de texto para la calculadora.
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String sentence;
		String method = "infija";
		String delims = "\\s+|(?<=\\()|(?=\\))";
		String[] tokens;

		System.out.println("Calculadora en modo notación " + method);
		while (true) {
			sentence = scanner.nextLine();
			switch (sentence) {
				case "exit":
					return;
				case "infija":
				case "prefija":
				case "postfija":
					System.out.println("Cambiando a notación " + sentence);
					method = sentence;
					continue;
				default:
					break;
			}
			tokens = sentence.split(delims);
			System.out.println("Tokens: " + Arrays.toString(tokens));
			double resultado;
			switch (method) {
				case "infija":
					resultado = evaluaInfija(tokens);
					break;
				case "prefija":
					resultado = Fija.evaluaPrefija(tokens);
					break;
				case "postfija":
					resultado = Fija.evaluaPostfija(tokens);
					break;
				default:
					System.out.println("Método inválido <" + method
							+ "> seleccione alguno de:\n"
							+ "\tinfija\n"
							+ "\tprefija\n"
							+ "\tpostfija\n");
					continue;
			}
			System.out.println("= " + resultado);
		}
	}
}
