package ed.estructuras.lineales;
public class Pruebas{

  public static void main(String[]pps){
    char[] a = new char[1]; a[0] = 'X';
    char[] b = new char[2]; b[0] = 'X'; b[1] = 'Y';
    char[] c = new char[3]; c[0] = 'X'; b[1] = 'Y'; c[2] = 'Z';
    char[] d = new char[3]; d[0] = 'Z'; b[1] = 'Y'; c[2] = 'X';
    char[] ab = new char[2]; ab[0] = 'X'; ab[1] = 'X';

    int[] e = new int[1]; e[0] = 2;
    int[] f = new int[2]; f[0] = 1; f[1] = 1;
    int[] g = new int[3]; g[0] = 2; g[1] = 3; g[2] = 5;
    int[] h = new int[3]; h[0] = 5; h[1] = 3; h[2] = 2;

    Monomio one = new Monomio(2.5, a, e);
    Monomio two = new Monomio(10.4, b, f);
    //Monomio three = new Monomio(5.5, a, f);
    Monomio four = new Monomio(5.5, c, g);
    Monomio five = new Monomio(5.5, d, h);
    Monomio six = new Monomio(5.5, ab, f);

    try{
      System.out.println(one + "+" + two + ": " + one.suma(two).toString());
      System.out.println("/n" + one.suma(one).toString());
      System.out.println("/n" + four + "+" + five + ": " + four.suma(five).toString());

    }catch(IllegalArgumentException ex){
      System.out.println("No se pueden sumar estos monomios");
    }

  }

}
