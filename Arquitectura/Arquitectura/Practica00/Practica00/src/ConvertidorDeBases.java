public class ConvertidorDeBases{

static String conversion = "";
static String digits = "0123456789ABCDEF";

public static String reversa(String C){
  String inverso = "";
  for(int i = C.length()-1; i>=0; i--){
    inverso = inverso + C.charAt(i);
  }
  return inverso;
}

public static String reductorDeBase16(String n){
  int num = 0;
  for(int i = 0; i<n.length(); i++){
   char c = n.charAt(i);
   int d = digits.indexOf(c);
   num = 16*num + d;
  }
  String aux = String.valueOf(num);
  return aux;
}

public static String aumentoDeBase16(String n){
  int num = Integer.parseInt(n);
  int aux = 0;
  while(num > 0){
    aux = num % 16;
    conversion = digits.charAt(aux) + conversion;
    num = num / 16;
  }
  return conversion;
}

public static String reductorDeBase10(String bd, String n){
  int num = Integer.parseInt(n);
  if(num == 0) return conversion;
  int base = Integer.parseInt(bd);
  int res = num%base;
  int div = num/base;
  conversion = conversion + String.valueOf(res);
  reductorDeBase10(String.valueOf(bd), String.valueOf(div));
  String aux = reversa(conversion);
  return aux;
}

public static String aumentoDeBase10(String bo, String n){
  int decimal = 0;
  int binary = Integer.parseInt(n);
  int power = 0;
  while (binary != 0) {
    int lastDigit = binary % 10;
    decimal += lastDigit * Math.pow(Integer.parseInt(bo), power);
    power++;
    binary = binary / 10;
  }
  String aux = String.valueOf(decimal);
  return aux;
}

public static void main(String[] args){
  String respuesta = "";
  String respuestaAux = "";

  if(Integer.parseInt(args[0]) > Integer.parseInt(args[1])){
    if(Integer.parseInt(args[0]) == 10){
      respuesta = reductorDeBase10(args[1], args[2]);
      System.out.println("El numero " + args[2] + " convertido de base " + args[0] + " a base " + args[1] + " es: " + respuesta + "\n");
    }else if(Integer.parseInt(args[0]) < 10){
            respuesta = aumentoDeBase10(args[0], args[2]);
            respuestaAux = reductorDeBase10(args[1], respuesta);
            System.out.println("El numero " + args[2] + " convertido de base " + args[0] + " a base " + args[1] + " es: " + respuestaAux + "\n");
          }else if(Integer.parseInt(args[0]) > 10){
                  respuesta = reductorDeBase16(args[2]);
                  if(Integer.parseInt(args[1]) == 10) System.out.println("El numero " + args[2] + " convertido de base " + args[0] + " a base " + args[1] + " es: " + respuesta + "\n");
                  else{
                    respuestaAux = reductorDeBase10(args[1], respuesta);
                    System.out.println("El numero " + args[2] + " convertido de base " + args[0] + " a base " + args[1] + " es: " + respuestaAux + "\n");
                  }
                }else System.out.println("Aun no se puede joven");

  }else if(Integer.parseInt(args[1]) == 10){
          respuesta = aumentoDeBase10(args[0], args[2]);
          System.out.println("El numero " + args[2] + " convertido de base " + args[0] + " a base " + args[1] + " es: " + respuesta + "\n");
        }else if(Integer.parseInt(args[1]) < 10){
                respuesta = aumentoDeBase10(args[0], args[2]);
                respuestaAux = reductorDeBase10(args[1], respuesta);
                System.out.println("El numero " + args[2] + " convertido de base " + args[0] + " a base " + args[1] + " es: " + respuestaAux + "\n");
              }else if(Integer.parseInt(args[1]) > 10){
                      respuesta = aumentoDeBase10(args[0], args[2]);
                      respuestaAux = aumentoDeBase16(respuesta);
                      System.out.println("El numero " + args[2] + " convertido de base " + args[0] + " a base " + args[1] + " es: " + respuestaAux + "\n");
              }else System.out.println("Aun no se puede joven");
}

}
