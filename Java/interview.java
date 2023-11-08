public class interview{

    public static int repetidos(int[] i, int[] j){
        int tam = 0;
        boolean igual = false;
        for(int m=0; m<i.length; m++){
            for(int n=0; n<j.length; n++){
                if(j[n] == i[m]) igual = true;
            }
            if(igual == true) tam++;
            igual = false;
        }
        return tam;
    }

    public static void combine(int[] i, int[] j){
        int tam = repetidos(i, j);
        System.out.println("DEBUG tam: " + tam);
        int out[] = new int[i.length+j.length-tam]; 
        System.out.println("DEBUG array: " + out.length);
        boolean repetido = false;
        for(int k=0; k<out.length; k++){
            int aux = 0;
            for(int m=0; m<i.length; m++){
                out[m] = i[m];
            }
            for(int n=0; n<j.length; n++){
                for(int p=0; p<i.length; p++){
                    if(j[n] == i[p]) repetido = true;
                }
                if(repetido == false){
                    out[i.length+aux] = j[n];
                    aux++;
                }
                repetido = false;
                //System.out.println(i.length+aux);
            }
            System.out.println("Posicion " + (k+1) + ": " + out[k]);
        }
    }

    public static void main(String[] args) {
        int arr1[] = {1,2,3,4,5,6};
        int arr2[] = {3,6,8,9,10};
        int arr3[] = {2,4,6};
        
        combine(arr1, arr2);
        //combine(arr1, arr3);
    }
}