public class NumerosAux{
  
  public static void quicksort(int A[], int izq, int der) {

    int pivote=A[izq]; 
    int i=izq; 
    int j=der; 
    int aux;
 
    while(i<j){            
      while(A[i]<=pivote && i<j) i++; 
      while(A[j]>pivote) j--;         
      if (i<j) {                                          
        aux= A[i];              
        A[i]=A[j];
        A[j]=aux;
      }
    }
    A[izq]=A[j]; 
    A[j]=pivote; 
    if(izq<j-1)
      quicksort(A,izq,j-1); 
    if(j+1 <der)
      quicksort(A,j+1,der); 
  }
  
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
}