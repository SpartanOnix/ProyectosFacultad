public class Conocer {
	public static void main(String[] args) {
		int resultado1;
		double resultado2=0;

		resultado1 = 46/9;
		System.out.println("Resultado 1.1: " + resultado1);
		
		resultado1 = 46 % 9 + 4 * 4 - 2;
		System.out.println("Resultado 1.2: " + resultado1);		
		
		resultado1 = 45 + 43 % 5 * (23 * 3 % 2);
		System.out.println("Resultado 1.3: " + resultado1);
		
		resultado2 = 4 + resultado2 * resultado2 + 4;
		System.out.println("Resultado 2.1: " + resultado2);	

		resultado2 += 1.5* 3 + (++resultado1);
		System.out.println("Resultado 2.2: " + resultado2);

		resultado2 = 1.5 * 3 + resultado1++;
		System.out.println("Resultado 2.3: " + resultado2);
	}
}
