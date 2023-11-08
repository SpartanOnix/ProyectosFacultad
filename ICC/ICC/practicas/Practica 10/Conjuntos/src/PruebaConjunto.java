public class PruebaConjunto {
	
	public static void main(String[] args) {
		System.out.println("********TEST DE Ciclos.java********\n");
		System.out.println("Creando pirámide de 10 pisos." + Ciclos.piramide(10));
		System.out.println("Imprimiendo pares hasta 20.");		
		Ciclos.pares(20);		
		System.out.println("\nCalculando promedio de 5,10,15,20,25: " + Ciclos.promedio());
		System.out.println("\n\n********TEST DE Conjuntos.java********\n");
		Conjunto a = new Conjunto();
		System.out.println("Se creó un conjunto vacío A.\nA = " + a);	
		System.out.println("Introduciendo en A los múltiplos de 5.");
		for(int i = 1; i < 101;i++)
			if(i % 5 == 0) 		
				a.introduce(i);
		System.out.println("A = " + a);	
		Conjunto b = new Conjunto(new int[]{20,80});
		System.out.println("Se creó un conjunto B que contiene los elementos 20 y 80.\nB = " + b);		
		System.out.println("Introduciendo en B los múltiplos de 10.");		
		for(int i = 1; i < 101;i++)
			if(i % 10 == 0) 		
				b.introduce(i);
		System.out.println("b = " + b);	
		Conjunto c = a.union(b);			
		System.out.println("Se creó un conjunto C igual a la unión de A y B.\nC = " + c);
		System.out.println("A y C tienen que ser iguales.\n¿Son iguales? --> " + a.equals(c));
		Conjunto d = a.interseccion(b);		
		System.out.println("Se creó un conjunto D igual a la intersección de A y B.\nD = " + d);		
		System.out.println("B y D tienen que ser iguales.\n¿Son iguales? --> " + b.equals(d));
		Conjunto e = a.diferencia(b);
		System.out.println("Se creó un conjunto E que contiene la diferencia entre A y B.\nE = " + e);
		System.out.println("Buscando y eliminando elementos mayores que 50 que pertenecen a E.");
		for(int i = 50; i < 101; i ++)
			e.elimina(i);
		System.out.println("E = " + e);
		System.out.println("¿El elemento 35 está en el conjunto E? --> " + e.pertenece(35));
		System.out.println("¿El elemento 208 está en el conjunto E? --> " + e.pertenece(208));
		e.introduce(-101);
		System.out.println("FIN DE LA PRUEBA");
	}
}