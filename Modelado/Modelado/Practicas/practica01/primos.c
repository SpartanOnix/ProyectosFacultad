#include<stdio.h>

int esprimos(int numero){
  if(numero==1) return 0;
  for(int i=2; i<numero; i++){
    if(numero%i==0) return 0;
  }
  return 1;
}

int main(){
  int n;
  printf("hola\n");
  scanf("%i",&n);
  for(int i=2; i<n; i++){
    if(esprimos(i)) printf("%i\n",i);
  }
}
