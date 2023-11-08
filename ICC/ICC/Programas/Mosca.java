public class Mosca {
	public static void main(String[] args) {
		String cadenaOriginal = "Una mosca parada en la pared, en la pared              ";
		//Escribe tu programa aquí.
		System.out.println("1-Cadena original: " + cadenaOriginal + " \nLongitud de la cadena: " + cadenaOriginal.length());

		cadenaOriginal = cadenaOriginal.trim();
		System.out.println("2-La cadena: " + cadenaOriginal + " tiene " + cadenaOriginal.length() + " caracteres");

		System.out.println("Frase#1: " + cadenaOriginal);
		
		System.out.println("Frase#2: " + cadenaOriginal.toUpperCase());

		String cadenaOriginal2;		
		cadenaOriginal2 = cadenaOriginal;
		cadenaOriginal2 = cadenaOriginal2.replace("a","I"); 
		cadenaOriginal2 = cadenaOriginal2.replace("e","I");
		cadenaOriginal2 = cadenaOriginal2.replace("o","I");
		cadenaOriginal2 = cadenaOriginal2.replace("U","I");
		System.out.println("Frase#3: " + cadenaOriginal2.toUpperCase());

		cadenaOriginal2 = cadenaOriginal2.replace("I","U");
		System.out.println("Frase#4: " + cadenaOriginal2.toUpperCase());

		System.out.println("Frase#5: " + cadenaOriginal.replace("mosca","MOSCOTA"));

		System.out.println("Frase#6: " + cadenaOriginal + " PINTADA DE ROJO");

		
	}
}
