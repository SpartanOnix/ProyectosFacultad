//Sinencio Granados Dante Jusepee

/**
 * Clase para hacer operaciones con numeros
 * -M determina el valor mayor
 * -m determina el valor menor
 * -p calcula el promedio de los valores
 * -o ordena y muestra los valores en orden decreciente
 * -t realiza las 4 operaciones anteriores
 * @param - -M, -m, -p, -o, -t que son las operaciones realizables
 */

public class Numeros{
  
  public static void main(String [] pps){
    if (pps.length > 1) { 
      int [] num = new int[pps.length-1];
      for (int i = 1; i< pps.length ;i++) {
        num[i-1] = Integer.parseInt(pps[i]);
      }

      if (pps[0].indexOf('t') > 0) {
        pps[0] = "-Mmpo";
      }
      
      if (pps[0].indexOf('M') > 0){
        System.out.print("El mayor de ");
        for(int i=0; i<num.length-2; i++){
          System.out.print(num[i] + ", ");
        }
        double prom = promedio(num);
        System.out.print(num[num.length-2] + " y " + num[num.length-1] + " es: ");
                orden(num);
        System.out.print(num[num.length-1] + "\n");
      }
      
      if (pps[0].indexOf('m') > 0){
        System.out.print("El menor de ");
        for(int i=0; i<num.length-2; i++){
          System.out.print(num[i] + ", ");
        }
        double prom = promedio(num);
        System.out.print(num[num.length-2] + " y " + num[num.length-1] + " es: ");
                orden(num);
        System.out.print(num[0] + "\n");
      }
      
      if (pps[0].indexOf('p') > 0){
        System.out.print("El promedio de ");
        for(int i=0; i<num.length-2; i++){
          System.out.print(num[i] + ", ");
        }
        double prom = promedio(num);
        System.out.println(num[num.length-2] + " y " + num[num.length-1] + " es: " + promedio(num));
      }
      
      if (pps[0].indexOf('o') > 0){
        orden(num);
        System.out.print("El arreglo ordenado es: ");
        for(int i=0; i<num.length; i++){
          System.out.print(num[i] + ", ");
        }
      }

      } else {
      System.out.println("Esto no esta definido, mete -M, -m, -p ó -o y por lo menos 2 numeros");
    }
  }

/**
 * Metodos auxiliares
 */

  public static void orden(int lista[]){
 
        for(int i=0;i<(lista.length-1);i++){
            for(int j=i+1;j<lista.length;j++){
                if(lista[i]>lista[j]){
                    int variableauxiliar=lista[i];
                    lista[i]=lista[j];
                    lista[j]=variableauxiliar;
                }
            }
        }
    }
  
  public static double promedio(int lista[]){
    double prom = 0.0;
    for(int i=0; i<lista.length; i++){
      prom += lista[i];
    }
    prom /= lista.length;
    return prom;
  }
  
}