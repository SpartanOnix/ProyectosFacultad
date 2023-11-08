#include "MacrosArea.c"

double areas(int numArgs, char const *dato[])
{
	float area;
	char figura[2];
	strcpy(figura, dato[1]);

	if(numArgs == 3)
	{
		if(strcmp(figura, "-c") == 0){
			double radio = atof(dato[2]);
			area = AREACIRCULO(radio);
			printf("El area de tu circulo es: %.2f\n", area);
		}
		else if(strcmp(figura, "-dc") == 0){
			double lado = atof(dato[2]);
			area = AREACUADRADO(lado);
			printf("El area de tu cuadrado es: %.2f\n", area);
		}
		else{
			SINTAXISFIGURA;
			return 0;
		}
	}

	else if(numArgs == 4)
	{
		if(strcmp(figura, "-t") == 0){
			double baze = atof(dato[2]);
			double haltura = atof(dato[3]);
			area = AREATRIANGULO(baze, haltura);
			printf("El area de tu triangulo es: %.2f\n", area);
		}
		else if (strcmp(figura, "-dr")==0 && numArgs==4){
			double base = atof(dato[2]);
			double altura = atof(dato[3]);
			area = AREARECTANGULO(base, altura);
			printf("El area de tu rectangulo es: %.2f\n", area);
		}
		else{
			SINTAXISFIGURA;
			return 0;
		}
	}else{
		SINTAXIS;
		return 0;
	}

}

int main (int numArgs, char const *dato[])
{
	areas(numArgs, dato);
}
