package ed.complejidad;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.IndexOutOfBoundsException;

public class Complejidad implements IComplejidad {
  
  //Atributos
  private long contador;
  private int res;
  
  //Metodos
  public long leeContador(){
    return contador;
  }
    
  public static void escribeOperaciones(String archivo, int par, long ops) throws FileNotFoundException {
    try (PrintStream writer = new PrintStream(new FileOutputStream(archivo, true))) {
      writer.println(par + " " + ops);
      writer.close();
    }
  }
    
  public static void escribeOperaciones(String archivo, int par1, int par2, long ops) throws FileNotFoundException {
    try (PrintStream writer = new PrintStream(new FileOutputStream(archivo, true))) {
      writer.println(par1 + " " + par2 + " " + ops);
      writer.close();
    }
  }
    
  public static void escribeLineaVacia(String archivo) throws FileNotFoundException {
    try (PrintStream writer = new PrintStream(new FileOutputStream(archivo, true))) {
      writer.println();
      writer.close();
    }
  }

    
  public int tPascalRec(int ren, int col) throws IndexOutOfBoundsException{
    if((ren < 0) || (col < 0) || (col > ren))throw new IndexOutOfBoundsException();  
    if((col == 0) || (col == ren) ) return 1;  
    else return tPascalRec(ren-1, col-1) + tPascalRec(ren-1, col); 
  }
    
  public int tPascalIt(int ren, int col) throws IndexOutOfBoundsException{
    int coli = 1;
    if((ren < 0) || (col < 0) || (col > ren)) throw new IndexOutOfBoundsException();
    else          
      for(int i=0; i<ren+1; i++) {        
        coli = 1;            
        for(int j=0;j<col;j++) {
          coli = coli * (i - j) / (j + 1);
        }        
    }
    return coli;   
  }
    
    
  public int fibonacciRec(int n) throws IndexOutOfBoundsException{
    if(n < 1) throw new IndexOutOfBoundsException();
    if(n == 1 || n==2) return 1;
    else return fibonacciRec(n-1) + fibonacciRec(n-2);
  }
    
  public int fibonacciIt(int n) throws IndexOutOfBoundsException{
    if(n == 0 || n < 0){
      throw new IndexOutOfBoundsException();
    }else{     
        int i = 0;
        int j = 1;
 
        for (int k = 1; k < n; k++){ 
          int t;
          t = i + j;
          i = j;
          j = t;
        }
        return j;
      }
    }
  
}