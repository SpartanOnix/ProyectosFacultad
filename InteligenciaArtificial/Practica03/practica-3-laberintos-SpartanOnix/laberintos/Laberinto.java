package laberintos;

import processing.core.PApplet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

/**
 * Clase que crea un laberinto con Processing.
 * @author Sara
 * @author Baruch
 */
public class Laberinto extends PApplet {
    int alto = 10;            // Altura (en celdas) de la cuadricula.
    int ancho = 15;           // Anchura (en celdas) de la cuadricula.
    int celda = 40;           // Tamanio de cada celda cuadrada (en pixeles).
    ModeloLaberinto modelo;   // El objeto que representa el modelo del laberinto.

    @Override
    public void setup() {
        frameRate(60);
        background(50);
        modelo = new ModeloLaberinto(ancho, alto, celda);
    }
    @Override
    public void settings() {
        size(ancho * celda, (alto * celda));
    }
    
    /**
     * Pintar el mundo del modelo.
     */
    @Override
    public void draw() {
      for (int i = 0; i < alto; i++)
        for (int j = 0; j < ancho; j++){
            fill(204, 204, 204);
            stroke(25,25,25);
            rect(j * modelo.tamanio, i * modelo.tamanio, modelo.tamanio, modelo.tamanio);
            // En caso de que las paredes de las celdas ya no se encuentren activas, estás se
            // pintarán del color del fondo.
            if(!modelo.mundo[i][j].pared_1){
                stroke(204, 204, 204);
                line(j * modelo.tamanio, i * modelo.tamanio, ((j + 1) * modelo.tamanio), i * modelo.tamanio);                    
            }
            if(!modelo.mundo[i][j].pared_2){
                stroke(204, 204, 204);
                line((j * modelo.tamanio) + modelo.tamanio, i * modelo.tamanio, (j + 1) * modelo.tamanio, (((i + 1) * modelo.tamanio)));
            }
            if(!modelo.mundo[i][j].pared_3){
                stroke(204, 204, 204);
                line(j * modelo.tamanio, (i * modelo.tamanio) + modelo.tamanio, ((j + 1) * modelo.tamanio), ((i + 1) * modelo.tamanio));                    
            }
            if(!modelo.mundo[i][j].pared_4){
                stroke(204, 204, 204);
                line(j * modelo.tamanio, i * modelo.tamanio, j * modelo.tamanio, ((i + 1) * modelo.tamanio));               
            }
        }
        modelo.CreaLaberinto();
    }

    /**
     * Clase que representa cada celda de la cuadricula.
     */
    class Celda{
        int celdaX; 
        int celdaY;
        boolean pared_1;
        boolean pared_2;
        boolean pared_3;
        boolean pared_4;
        boolean estado;
        
        /** Constructor de una celda.
          *@param celdaX Coordenada en x
          *@param celdaY Coordenada en y
          *@param estado Estado de la celda. true si no ha sido visitada, false en otro caso.
        */
        Celda(int celdaX, int celdaY, boolean estado){
            this.celdaX = celdaX;
            this.celdaY = celdaY;
            this.estado = estado;
            this.pared_1 = true; // Booleano que representa la pared de arriba
            this.pared_2 = true; // Booleano que representa la pared de la derecha
            this.pared_3 = true; // Booleano que representa la pared de abajo
            this.pared_4 = true; // Booleano que representa la pared de la izquierda
        }
    }
    
    /**
     * Clase que representa cada celda de la cuadricula.
     */
    public class Actual{
        int posX; 
        int posY;
        /** Constructor de una celda.
          *@param posX Coordenada en x
          *@param posY Coordenada en y
        */  
        Actual(int posX,  int posY){
            this.posX = posX;
            this.posY = posY;
        }
    }

    /**
     * Clase que modela el laberinto, es decir, crea el mundo del laberinto.
     */
    class ModeloLaberinto{
      Random rnd = new Random();  // objeto para el uso del random
      Stack<Celda> visitados; // Pila para guardar las celdas visitadas
        int ancho, alto;  // Tamaño de celdas a lo largo y ancho de la cuadrícula.
        Celda[][] mundo;  // Mundo de celdas
        int direccion;  // Variable para indicar la direccion
        Actual actual; // Celda para saber donde estoy
        int tamanio;  // Tamaño en pixeles de cada celda.
        
      /** Constructor del modelo
        @param ancho Cantidad de celdas a lo ancho en la cuadricula.
        @param alto Cantidad de celdas a lo largo en la cuadricula.
        @param tamanio Tamaño (en pixeles) de cada celda cuadrada que compone la cuadricula.
      */
      ModeloLaberinto(int ancho, int alto, int tamanio){
          this.ancho = ancho;
          this.alto = alto;
          this.tamanio = tamanio;
          mundo = new Celda[alto][ancho];
          int anc = rnd.nextInt(ancho);
          int alt = rnd.nextInt(alto);
          actual = new Actual(anc,alt);
          mundo[alt][anc].estado = false;
          visitados = new Stack<>();
          visitados.push(mundo[alt][anc]);
          for(int i = 0; i < alto; i++)
            for(int j = 0; j < ancho; j++)
              mundo[i][j] = new Celda(j,i, true);
      }

      /**
       * Metodo para revisar si ya se visito una celda
       * @param posX Posicion en X de la celda
       * @param posY Posicion en Y de la celda
       * @param direccion Numero que indica la direccion de movimiento
       * @return False si aun no se ha visitado la celda, True en caso contrario 
       */
      boolean fueVisitada(int posX , int posY, int direccion){
          switch (direccion){
              case 0: if (posY-1 < 0) return false;
                      return mundo[posY-1][posX].estado;
              case 1: if (posX+1 >= ancho) return false;
                      return mundo[posY][posX+1].estado;
              case 2: if (posY+1 >= alto) return false;
                      return mundo[posY+1][posX].estado;
              case 3: if (posX-1 < 0) return false;
                      return mundo[posY][posX-1].estado;
          }
          return false;   
      }

      /**
       * Metodo para quitar las paredes en las casillas
       * @param posX Posicion en X de la celda
       * @param posY Posicion en Y de la celda
       * @param direccion Numero que indica la direccion de movimiento
       */
      void eliminaPared(int posX, int posY, int direccion){
          switch(direccion){
              case 0: mundo[posY][posX].pared_1 = false;
                      mundo[posY-1][posX].pared_3 = false;
                      break;
              case 1: mundo[posY][posX].pared_2 = false;
                      mundo[posY][posX+1].pared_4 = false;
                      break;
              case 2: mundo[posY][posX].pared_3 = false;
                      mundo[posY+1][posX].pared_1 = false;
                      break;
              case 3: mundo[posY][posX].pared_4 = false;
                      mundo[posY][posX-1].pared_2 = false;
                      break;
          }
      }

      /**
       * Metodo para moverte de casilla
       * @param actual Casilla en la que te encuentras
       * @param direccion Numero que indica la direccion de movimiento
       */
      void moverCasilla(Actual actual, int direccion){
          visitados.push (mundo[actual.posY][actual.posX]);
          switch (direccion) {
              case 0: if((actual.posY-1 <= 0) && (actual.posY < alto)) actual.posY--;
                      break;
              case 1: if((actual.posX+1 <= 0) && (actual.posX < ancho)) actual.posX++;
                      break;
              case 2: if((actual.posY+1 <= 0) && (actual.posY < alto)) actual.posY++;
                      break;
              case 3: if((actual.posX-1 <= 0) && (actual.posX < ancho)) actual.posX--;
                      break;
          }
          mundo[actual.posY][actual.posX].estado = false;
      }
      
      /**
       * Metodo para crear el laberinto
       */
      void CreaLaberinto(){
          boolean mover = false;
          LinkedList<Integer> visto = new LinkedList<> ();
          int pos = 0;
          while (visto.size() < 4 && !mover){
              pos = rnd.nextInt(4);
              if (fueVisitada (actual.posX, actual.posY, pos)) mover = true;
              if (!visto.contains(pos)) visto.add(pos);
          }
          if (mover){
              eliminaPared(actual.posX, actual.posY, pos);
              moverCasilla(actual, pos);
              eliminaPared(actual.posX, actual.posY, pos);
          }else if (!visitados.empty()){
              Celda a = visitados.pop();
              actual.posY = a.celdaY;
              actual.posX = a.celdaX;                
          }
      }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         PApplet.main(new String[] { "laberintos.Laberinto" });
    } 
}