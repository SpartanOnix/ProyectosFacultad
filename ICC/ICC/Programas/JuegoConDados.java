/**
 *  Esta clase se contiene un programa para jugar dados con  la computadora
 *  @author Amparo Lopez Gaona
 */
import java.util.Scanner;
public class JuegoConDados {
    
    public static void main(String args[]) {
        
        /*
         *  Aqui, crea los objetos necesarios y usa los metodos
         *  adecuados para resolver tu problema.
         */
		Dado dado = new Dado();
		
		String nombre;
		int resultado;
		int resultado2;

	//Pedir nombre del jugador 
		System.out.println("Hola, yo soy Onix y hoy jugare con tigo, ¿cual es tu nombre?");
		Scanner lector = new Scanner(System.in);
		nombre = lector.nextLine();
		System.out.println("Hola " + nombre + ", entoces jugemos :D"); 

	// El jugador Lanza los dados
		int numero1 = dado.lanzar();
		int numero2 = dado.lanzar();
		System.out.println("Al lanzar los dados tus resultados fueron: " + numero1 + " y " + numero2);

	// Calcular la suma de los dados
		resultado = numero1 + numero2;
		System.out.println("Eso nos da un: " + resultado);
		if(resultado == 2){
		  System.out.println("Perdiste :(");
		}
		  
		
		if(resultado == 3){
		  System.out.println("Perdiste :(");
		}
		  
		
		if(resultado == 12){
		  System.out.println("Perdiste :(");
		}
		  
		
		 
	// Si es necesario volver a lanzar los dados
	if(resultado < 12){
	   	if(resultado == 4){
		  int numero3 = dado.lanzar();
		  int numero4 = dado.lanzar();
		  resultado2 = numero3 + numero4;
		  System.out.println("Al no perder, debes de volver a tirar, ahora la suma de los dados nos da: " + resultado2 + " vuelve a jugar");
		  
		}
		if(resultado == 5){
		  int numero5 = dado.lanzar();
		  int numero6 = dado.lanzar();
		  resultado2 = numero5 + numero6;
		  System.out.println("Al no perder, debes de volver a tirar, ahora la suma de los dados nos da: " + resultado2 + " vuelve a jugar");
		  
		}
		if(resultado == 6){
		  int numero7 = dado.lanzar();
		  int numero8 = dado.lanzar();
		  resultado2 = numero7 + numero8;
		  System.out.println("Al no perder, debes de volver a tirar, ahora la suma de los dados nos da: " + resultado2 + " vuelve a jugar");
		  
		}
		if(resultado == 8){
		  int numero9 = dado.lanzar();
		  int numero10 = dado.lanzar();
		  resultado2 = numero9 + numero10;
		  System.out.println("Al no perder, debes de volver a tirar, ahora la suma de los dados nos da: " + resultado2 + " vuelve a jugar");
		  
		}
		if(resultado == 9){
		  int numero11 = dado.lanzar();
		  int numero12 = dado.lanzar();
		  resultado2 = numero11 + numero12;
		  System.out.println("Al no perder, debes de volver a tirar, ahora la suma de los dados nos da: " + resultado2 + " vuelve a jugar");
		  
		}
		if(resultado == 10){
		  int numero13 = dado.lanzar();
		  int numero14 = dado.lanzar();
		  resultado2 = numero13 + numero14;
		  System.out.println("Al no perder, debes de volver a tirar, ahora la suma de los dados nos da: " + resultado2 + " vuelve a jugar");
		  
		}
	}else{
	 System.out.println("Perdiste :(");
	}
		

	// Determinar si el jugador gana o pierde
		if(resultado == 7){
		  System.out.println("Ganaste :D");
		}else{
	 	 System.out.println("Perdiste :(");
		}
		if(resultado == 11){
		  System.out.println("Ganaste :D");
		}else{
	 	 System.out.println("Perdiste :(");
		}
		
    }
}
