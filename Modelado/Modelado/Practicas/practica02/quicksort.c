#include<stdio.h>

void quicksort(int arr[], int primero, int ultimo)
{
    int izq = primero;
    int der = ultimo;
    int pivote = arr[(izq+der)/2];
    int aux;

    do{
        while(arr[izq] < pivote && izq < ultimo) izq++;
        while(pivote < arr[der] && der > primero) der--;
        if(izq <= der)
        {
            aux = arr[izq];
            arr[izq] = arr[der];
            arr[der] = aux;
            izq++;
            der--;
        }
    }while(izq <= der);

    if(primero < der) quicksort(arr, primero, der);
    if(ultimo > izq) quicksort(arr, izq, ultimo);
}

int main()
{
    int argc;
    scanf("%i",&argc);
    int arr[argc];
    for (size_t i = 0; i < argc; i++) {
      scanf("%i",&arr[i]);
    }

    quicksort(arr, 0, argc-1);

    for (int i = 0; i < argc; i++) {
        printf("%d ",arr[i]);
    }
    return 0;
}
