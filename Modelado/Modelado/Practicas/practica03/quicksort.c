#include <stdio.h>
#include <omp.h>
#include <time.h>
#include <stdlib.h>

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

void merge(int arr[], int ini, int med, int fin)
{
	int i;
  int j;
  int k;
	int n1 = med - ini + 1;
	int n2 = fin - med;
	int L[n1];
  int R[n2];

	for (i = 0; i < n1; i++) L[i] = arr[ini + i];
	for (j = 0; j < n2; j++) R[j] = arr[med + 1 + j];

	i = 0;
	j = 0;
	k = ini;

	while (i < n1 && j < n2)
	{
		if (L[i] <= R[j])
		{
			arr[k] = L[i];
			i++;
		}
		else
		{
			arr[k] = R[j];
			j++;
		}
		k++;
	}

	while (i < n1)
	{
		arr[k] = L[i];
		i++;
		k++;
	}

	while (j < n2)
	{
		arr[k] = R[j];
		j++;
		k++;
	}
}


void llenaArreglo(int *arreglo, int size)
{
  srand(time(NULL));
  for (size_t i = 0; i < size; i++) {
    int r = rand()%1000;
    arreglo[i] = r;
  }
}

int main()
{
    int argc;
    printf("%s\n", "Introduce el tamaño del arreglo que quieras ordenar:");
    scanf("%i", &argc);
    int medio = argc/2;
    int arr[argc];
    llenaArreglo(arr, argc);

    omp_set_num_threads(2);
    #pragma omp parallel
    {
    quicksort(arr, 0, medio-1);
    quicksort(arr, medio, argc-1);
    }

    merge(arr, 0, medio-1, argc-1);
    printf("%s\n", "El arreglo ordenado es:");
    printf("%s[", "");
    for (size_t i = 0; i < argc-1; i++) {
        printf("%i, ", arr[i]);
    }
    printf("%i]\n", arr[argc-1]);
}
