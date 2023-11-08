import java.util.*;
import java.io.*;

public class Buscaminas{
  
  public static void main(String[] pps){

    Scanner scan = new Scanner(System.in);
    boolean bandera = true;

    int fila =0;
    int columna =0;
    
    double w=0;
    double z=0;

    int ntminas=0;
    int tminas =0;

    System.out.println("\nBienvenido al Buscaminas Ultra HD 4K 3018 Edition");

    do{
      System.out.println("\nEl tablero minimo es de 8x8");
      System.out.println("Cuantas filas quieres?");
      fila = scan.nextInt();

      System.out.println("Cuantas columnas quieres?");
      columna = scan.nextInt();
    }while(fila<8 && columna<8);

    int tablero [][]= new int[fila][columna];
    int intento= fila * columna;
    do{
      try{
        System.out.println("Cuantas minas quieres?");
        scan = new Scanner(System.in);
        tminas = scan.nextInt();
      }catch(InputMismatchException e){
        System.out.println("Mete un valor valido");
      }
    }while(tminas<0);
    
    for(int j=0;j<fila;j++)
      for (int i=0;i<columna;i++)
      tablero [j][i]=0;

    do{
      w=Math.random()*fila;
      z=Math.random()*columna;
      w=(int)w;
      z=(int)z;
      if(z!=0 && w!=0 && z!=columna-1 && w!=fila-1){
        tablero[(int)w][(int) z ]=1;
        ntminas++;
      }
    }while (ntminas<=tminas);

    while(bandera == true){
      System.out.println("\n Que quieres hacer?");
      System.out.println("1. jugar");
      System.out.println("2. Guardar tablero");
      System.out.println("3. Cargar tablero");
      System.out.println("4. Salir");
      try{
        scan = new Scanner(System.in);
        int opcion = scan.nextInt();
        
        switch(opcion){
          case 1:
            System.out.println("\n**-------------------------------------------------------------------------------**");
            System.out.println("\n  Ingresa las coordenas para las filas del 1 al " + fila + " y para las columnas del 1 al " + columna );
            do {
              int x=0,y=0;
              int nminas = 0;
              
              do{
                try{
                  scan = new Scanner(System.in);
                  System.out.println("\n**-------------------------------------------------------------------------------**");
                  System.out.println("\n Intentos restantes : " + intento +"\n Cual es su siguiente coordenada? \n");
                  System.out.print("  Fila:  ");
                  x=scan.nextInt();
                  System.out.println();
                  System.out.print("  Columna:  ");
                  y=scan.nextInt();
                  
                  if(x==0 || y==0 || x>fila || y>columna){
                    System.out.println("\n ==>Coordenadas incorrectas<== \n");
                  }
                }catch(InputMismatchException e){
                  System.out.println("Mete un valor valido");
                }
              }while(x==0 || y==0 || x>fila || y>columna);
              
              x=x-1;
              y=y-1;
              
              if(tablero [x][y]==0){
                
                if (x==0&&y==0){
                  if(x+1>=0 && x+1<fila && y<columna)
                    nminas=nminas+tablero [x+1][y];
                  if(x+1>=0 && x+1<fila && y+1<columna && y+1>=0)
                    nminas=nminas+tablero [x+1][y+1];
                  if(x>=0 && x<fila && y+1<columna && y+1>= 0)
                    nminas=nminas+tablero [x][y+1];
                }else{
                  if(y==0){
                    if(x+1>=0 && x+1<fila && y<columna)
                      nminas=nminas+tablero [x+1][y];
                    if(x-1>=0 && x-1<fila && y<columna)
                      nminas=nminas+tablero [x-1][y];
                    if(x-1>=0 && x-1<fila && y+1<columna && y+1>=0)
                      nminas=nminas+tablero [x-1][y+1];
                    if(x>=0 && x<fila && y+1<columna && y+1>= 0)
                      nminas=nminas+tablero [x][y+1];
                    if(x+1>=0 && x+1<fila && y+1<columna && y+1>=0)
                      nminas=nminas+tablero [x+1][y+1];
                    
                  }else{
                    
                    if(x-1>=0 && x-1<fila && y-1<columna)
                      nminas=nminas+tablero [x-1][y-1];
                    if(x>=0 && x<fila && y-1<columna)
                      nminas=nminas+tablero [x][y-1];
                    if(x+1>=0 && x+1<fila && y-1<columna)
                      nminas=nminas+tablero [x+1][y-1];
                    if(x+1>=0 && x+1<fila && y<columna)
                      nminas=nminas+tablero [x+1][y];
                    if(x-1>=0 && x-1<fila && y<columna)
                      nminas=nminas+tablero [x-1][y];
                    if(x-1>=0 && x-1<fila && y+1<columna && y+1>=0)
                      nminas=nminas+tablero [x-1][y+1];
                    if(x>=0 && x<fila && y+1<columna && y+1>= 0)
                      nminas=nminas+tablero [x][y+1];
                    if(x+1>=0 && x+1<fila && y+1<columna && y+1>=0)
                      nminas=nminas+tablero [x+1][y+1];
                  }
                }
                System.out.print("\n Mina no encontrada. ");
                
                if(intento>tminas)
                  System.out.print(" Alrededor hay: "+ nminas);
                System.out.println("\n Intentos restantes: "+(intento-1));
              }else{
                tminas = tminas-1;
                System.out.println("\n Acertaste!, Mina Eliminada Restan "+tminas+" Minas");
                System.out.println("\n Intentos restantes: "+(intento-1));
              }
              
              intento=intento-1;
            }while (intento>=tminas && intento>0 && tminas>0);
            
            if(tminas==0)
              System.out.println("  Ganaste!");
            
            if(tminas>intento)
              System.out.println("  Ya no puedes completar el juego!    (Exceso de minas restantes "+tminas+")\n\n");
            
            if(intento==0)
              System.out.println("  Se te acabaron los turnos!");
            break;
            
          case 2:
            try{
            FileOutputStream fs = new FileOutputStream("save.txt");
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(tablero);
            os.close();
          }catch(FileNotFoundException e){
            e.printStackTrace();
          }catch(IOException e){
            e.printStackTrace();
          }
            break;
            
          case 3:
            try{
            FileInputStream fis = new FileInputStream("save.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            tablero = (int[][])ois.readObject();
            ois.close(); fis.close();
          }catch(FileNotFoundException e){
            e.printStackTrace();
          }catch(IOException e){
            e.printStackTrace();
          }catch(ClassNotFoundException e){
            e.printStackTrace();
          }
            break;
            
          case 4:
            bandera = false;
            break;
            
          default:
            System.out.println("Escoge una opccion valida");
            return;
        }
      }catch(InputMismatchException e){
        System.out.println("\nDebes de poner una cantidad entera a los creditos");
      }
    }
  }
}
