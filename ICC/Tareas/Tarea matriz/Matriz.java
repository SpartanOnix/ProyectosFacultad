//Sinencio Granados Dante Jusepee
public class Matriz{
  
  //Atributo
  private double [][] coeficientes;
  
  //constructor
  public Matriz(double[][] c){
    this.coeficientes = c;
    
  }
  
  //Metodos auxiliares
    private double karatazo(int fila, int columna, double[][] m1, double[][] m2){
      double k = 0;
      for(int i=0; i<m1.length; i++){
        k += (m1[i][fila]*m2[columna][i]);
      }
      return k;
    }
    
    private void pivotar(int fila, double[][] coef){
      double pivote = coef[fila][fila];
      for(int i=fila; i<coef[fila].length; i++){
        coef[fila][i] = coef[fila][i]/pivote;
      }
    }
    
    public void restarFila(int pivote, int fila, double[][] coef){
      double constante = coef[fila][pivote];
      for(int i=pivote; i<coef[0].length; i++){
        coef[fila][i] = coef[fila][i] - constante*coef[pivote][i];
      }
    }
    
   public void restarFilaInferior(int pivote, int fila, double[][] coef){
      double constante = coef[fila][pivote];
      for(int i=pivote; i<coef[0].length; i++){
        coef[fila][i] = coef[fila][i] - constante*coef[pivote][i];
      }
    }
   
   public void eliminarColumnaSuperior(int pivote, double[][] coef){
     this.pivotar(pivote, coef);
     for(int i=pivote-1; i>=0; i--){
       this.restarFila(pivote, i, coef);
     }
   }
   
   public void diagonalSuperior(double[][] coef){
     for(int i=0; i<coef.length-1; i++){
       this.eliminarColumnaSuperior(i, coef);
     }
     this.pivotar(coef.length-1, coef);
   }
   
   public void diagonalInferior(double[][] coef){
     for(int i=coef.length-1; i>=0; i--){
       this.eliminarColumnaSuperior(i, coef);
     }     
   }
  
  public String toString(){
    String s = "";
    for(int i=0; i<this.coeficientes.length; i++){
      for(int j=0; j<this.coeficientes[0].length; j++){
        s += this.coeficientes[i][j] + " ";
      }
      s += "\n";
    }
    return s;
  }
    

  //Metodos
  public Matriz suma(Matriz m){
    if(coeficientes.length == m.coeficientes.length && coeficientes[0].length == m.coeficientes[0].length){
      double[][] aux = new double [coeficientes.length][coeficientes[0].length];
      for(int i=0; i<coeficientes.length; i++){
        for(int j=0; j<coeficientes[0].length; j++){
          aux [i][j] = coeficientes [i][j] + m.coeficientes[i][j];
        }
      }
      Matriz suma = new Matriz (aux);
      return suma;
    }else{
      return null;
    }
  }
  
  public Matriz multiplicarEscalar(double r){
    double [][] producto = new double[coeficientes.length][coeficientes[0].length];
    for(int i=0; i<producto.length; i++){
        for(int j=0; j<producto[0].length; j++){
          producto [i][j] = r * this.coeficientes[i][j];
        }
    }
    Matriz me = new Matriz(producto);
    return me;
  }
  
  public Matriz Traspuesta(){
    double [][] aux = new double[coeficientes.length][coeficientes[0].length];
    for(int i=0; i<coeficientes.length; i++){
        for(int j=0; j<coeficientes[0].length; j++){
          aux [j][i] = coeficientes[i][j];
        }
    }
    return new Matriz(aux);
  }
  
  public Matriz multiplicacion(Matriz m){
    System.out.println(coeficientes[0].length);
    System.out.println(m.coeficientes.length);
    double[][] multi = new double[m.coeficientes.length][coeficientes[0].length];
    for(int i=0; i<coeficientes[0].length; i++){
      for(int j=0; j<m.coeficientes.length; j++){
        System.out.println(i + "," + j);
        multi[j][i] = this.karatazo(i, j, coeficientes, m.coeficientes);
      }
    }
    return new Matriz(multi);
  }
  
  public double[] ecuaciones(){
    double[][] aux = new double[this.coeficientes.length][this.coeficientes[0].length];
    for(int i=0; i<this.coeficientes.length; i++){
      for(int j=0; j<this.coeficientes.length; j++){
        aux[i][j] = this.coeficientes[i][j];
      }
    }
    this.diagonalSuperior(aux);
    this.diagonalInferior(aux);
    double[] resultado = new double[this.coeficientes.length];
    for(int i=0; i<resultado.length; i++){
      resultado[i] = aux[i][aux.length];
    }
    return resultado;
  }
  
  public double determinante(){
    double det;
    if(coeficientes.length==2){
        det=(coeficientes[0][0]*coeficientes[1][1]) - (coeficientes[1][0]*coeficientes[0][1]);
        return det;
    }
    double suma=0;
    for(int i=0; i<coeficientes.length; i++){
      double[][] nm = new double[coeficientes.length-1][coeficientes.length-1];
        for(int j=0; j<coeficientes.length; j++){
            if(j!=i){
                for(int k=1; k<coeficientes.length; k++){
                  int indice=-1;
                  if(j<i){
                    indice=j;
                  }else{ if(j>i)
                    indice=j-1;
                  }
                  nm[indice][k-1] = coeficientes[j][k];
                }
            }
        }
        Matriz matrix = new Matriz(nm);
        if(i%2==0){
          suma += coeficientes[i][0] * matrix.determinante();
        }else{
          suma -= coeficientes[i][0] * matrix.determinante();
        }
    }
    return suma;
  }
  
  public Matriz asignarMatriz(Matriz m){
    if(coeficientes.length == m.coeficientes.length && coeficientes[0].length == m.coeficientes[0].length){
      double[][] cln = new double[coeficientes.length][coeficientes[0].length];
      for(int i=0; i<coeficientes.length; i++){
        for(int j=0; j<coeficientes[0].length; j++){
          cln [i][j] = coeficientes[i][j];
        }
      }
      Matriz clon = new Matriz(cln);
      return clon;
    }else{
      return null;
    }
  }
  
  
}