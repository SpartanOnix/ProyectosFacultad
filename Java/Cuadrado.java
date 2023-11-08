//Paqueteria a usar
//Package to use
import java.util.Scanner;

//Clase para alinear los vertices de un cuadrado
//Class to align the vertices of a square
public class Cuadrado{

    //Variables de la clase Cuadrado, cada punto es un arreglo de dos elementos
    //Variables of class Cuadrado, each point is an array of two elements
    int P1[] = new int[2];
    int P2[] = new int[2];
    int P3[] = new int[2];
    int P4[] = new int[2];
    
    //Constructor de la clase Cuadrado con las coordenadas de los vertices
    //Constructor of the Cuadrado class with the coordinates of the vertices
    public Cuadrado(int[] p1, int[] p2, int[] p3, int[] p4){
        P1 = p1;
        P2 = p2;
        P3 = p3;
        P4 = p4;
    }
    
    //Verifica los vertices de un cuadrilatero y devuelve true si es un cuadrilatero
    //Verifies the vertices of a quadrilateral and returns true if it is a quadrilateral
    public boolean esCuadrilatero(Cuadrado cuadrado){
        if (cuadrado.P1[0] == cuadrado.P4[0] && cuadrado.P2[0] == cuadrado.P3[0] && cuadrado.P1[1] == cuadrado.P2[1] && cuadrado.P3[1] == cuadrado.P4[1]){
            return true;
        }
        else{
            return false;
        }
    }

    //Alinea los vertices de un cuadrilatero
    //Aligns the vertices of a quadrilateral
    public void alinear(Cuadrado cuadrado){
        if (!esCuadrilatero(cuadrado)){

            //Casos para alinear p2 con respecto a p1 en el eje y
            //Cases for aligning p2 with respect to p1 on the y-axis
            if (cuadrado.P1[1] > cuadrado.P2[1]){
                while(cuadrado.P1[1] != cuadrado.P2[1]){
                    cuadrado.P2[1] = cuadrado.P2[1] + 1;
                }
            }
            if (cuadrado.P1[1] < cuadrado.P2[1]){
                while(cuadrado.P1[1] != cuadrado.P2[1]){
                    cuadrado.P2[1] = cuadrado.P2[1] - 1;
                }
            }

            //Casos para alinear p4 con respecto a p1 en el eje x
            //Cases for aligning p4 with respect to p1 on the x-axis
            if (cuadrado.P1[0] > cuadrado.P4[0]){
                while(cuadrado.P1[0] != cuadrado.P4[0]){
                    cuadrado.P4[0] = cuadrado.P4[0] + 1;
                }
            }
            if (cuadrado.P1[0] < cuadrado.P4[0]){
                while(cuadrado.P1[0] != cuadrado.P4[0]){
                    cuadrado.P4[0] = cuadrado.P4[0] - 1;
                }
            }

            //Casos para alinear p3 con respecto a p2 en el eje x
            //Cases for aligning p3 with respect to p2 on the x-axis
            if (cuadrado.P2[0] > cuadrado.P3[0]){
                while(cuadrado.P2[0] != cuadrado.P3[0]){
                    cuadrado.P3[0] = cuadrado.P3[0] + 1;
                }
            }
            if (cuadrado.P2[0] < cuadrado.P3[0]){
                while(cuadrado.P2[0] != cuadrado.P3[0]){
                    cuadrado.P3[0] = cuadrado.P3[0] - 1;
                }
            }

            //Casos para alinear p3 con respecto a p4 en el eje y
            //Cases for aligning p3 with respect to p4 on the y-axis
            if (cuadrado.P4[1] > cuadrado.P3[1]){
                while(cuadrado.P4[1] != cuadrado.P3[1]){
                    cuadrado.P3[1] = cuadrado.P3[1] + 1;
                }
            }
            if (cuadrado.P4[1] < cuadrado.P3[1]){
                while(cuadrado.P4[1] != cuadrado.P3[1]){
                    cuadrado.P3[1] = cuadrado.P3[1] - 1;
                }
            }
        }
    }

    //Iguala la distancia entre los vertices de un cuadrilatero
    //Equalizes the distance between the vertices of a quadrilateral
    public void iguala(Cuadrado cuadrado){

        //Caso en el que la distancia entre p1 y p2 es mayor que la distancia entre p1 y p4
        //Case in which the distance between p1 and p2 is greater than the distance between p1 and p4
        if (cuadrado.P2[0]-cuadrado.P1[0] > cuadrado.P4[1]-cuadrado.P1[1]){
            while(cuadrado.P2[0]-cuadrado.P1[0] != cuadrado.P4[1]-cuadrado.P1[1]){
                cuadrado.P4[1] = cuadrado.P4[1] + 1;
                cuadrado.P3[1] = cuadrado.P3[1] + 1;
            }
        }

        //Caso en el que la distancia entre p1 y p2 es menor que la distancia entre p1 y p4
        //Case in which the distance between p1 and p2 is less than the distance between p1 and p4
        if (cuadrado.P2[0]-cuadrado.P1[0] < cuadrado.P4[1]-cuadrado.P1[1]){
            while(cuadrado.P2[0]-cuadrado.P1[0] != cuadrado.P4[1]-cuadrado.P1[1]){
                cuadrado.P4[1] = cuadrado.P4[1] - 1;
                cuadrado.P3[1] = cuadrado.P3[1] - 1;
            }
        }
    }
       

    public static void main(String[] args){
        
        //Variables para las coordenadas de los puntos
        //Variables for the coordinates of the points
        int x1, y1, x2, y2, x3, y3, x4, y4;
        int p1[] = new int[2];
        int p2[] = new int[2];
        int p3[] = new int[2];
        int p4[] = new int[2];

        //Interfaz en consola
        //Console interface
        Scanner sc = new Scanner(System.in);
        System.out.println("\nIngrese las coordenadas del primer punto: | Enter the coordinates of the first point:");
        System.out.println("Ingrese | Enter x1: ");
        x1 = sc.nextInt();
        System.out.println("Ingrese | Enter y1: ");
        y1 = sc.nextInt();
        p1[0] = x1;
        p1[1] = y1;
        System.out.println("\nIngrese las coordenadas del segundo punto | Enter the coordinates of the second point:");
        System.out.println("Ingrese | Enter x2: ");
        x2 = sc.nextInt();
        System.out.println("Ingrese | Enter y2: ");
        y2 = sc.nextInt();
        p2[0] = x2;
        p2[1] = y2;
        System.out.println("\nIngrese las coordenadas del tercer punto | Enter the coordinates of the third point:");
        System.out.println("Ingrese | Enter x3: ");
        x3 = sc.nextInt();
        System.out.println("Ingrese | Enter y3: ");
        y3 = sc.nextInt();
        p3[0] = x3;
        p3[1] = y3;
        System.out.println("\nIngrese las coordenadas del cuarto punto | Enter the coordinates of the fourth point:");
        System.out.println("Ingrese | Enter x4: ");
        x4 = sc.nextInt();
        System.out.println("Ingrese | Enter y4: ");
        y4 = sc.nextInt();
        p4[0] = x4;
        p4[1] = y4;

        //Crea un objeto de la clase Cuadrado con los valores obtenidos
        //Creates an object of the Cuadrado class with the values obtained
        Cuadrado cuadrado = new Cuadrado(p1, p2, p3, p4);

        //Llama al metodo alinear para crear un cuadrilatero con lados rectos
        //Calls the "alinear" method to create a quadrilateral with straight sides
        cuadrado.alinear(cuadrado);

        //Llama al metodo iguala para transformar el cuadrilatero en un cuadrado
        //Calls the "iguala" method to transform the quadrilateral into a square
        cuadrado.iguala(cuadrado);

        //Imprime los vertices del cuadrado
        //Prints the vertices of the square
        System.out.println("\nLos vertices del cuadrado son: | The vertices of the square are:");
        System.out.println("P1: (" + cuadrado.P1[0] + "," + cuadrado.P1[1] + ")");
        System.out.println("P2: (" + cuadrado.P2[0] + "," + cuadrado.P2[1] + ")");
        System.out.println("P3: (" + cuadrado.P3[0] + "," + cuadrado.P3[1] + ")");
        System.out.println("P4: (" + cuadrado.P4[0] + "," + cuadrado.P4[1] + ")");
    }
}
