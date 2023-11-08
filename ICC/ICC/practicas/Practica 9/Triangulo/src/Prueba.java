import processing.core.PApplet;
import processing.core.PFont;

/**
 * Modela una interfaz para dibujar figuras en un plano.
 * @author Miguel Mendoza
 */
public class Prueba extends PApplet {

 /*Propiedades de la interfaz*/   
    int ancho=600;
    int largo=600;

    /*Figuras a dibujar (Se pueden agregar más figuras).*/
    Triangulo t1 ;
    //Triangulo t2;

 /**
  *
  */
 public void settings(){
  size(ancho,largo);
 }

 /** 
  * Configuracion inicial.
  */
    @Override
    public void setup(){
  t1 = new Triangulo();
  System.out.println(t1);
  /*Aquí se pueden inicializar las figuras*/
  //t2 = new Triangulo(new Punto(0,0),new Punto(4,5),new Punto(9,8));
    }
    /**
     * Se ejecuta cuando se hace click sobre la pantalla.- 
     */
    @Override
    public void mouseClicked() {
     t1.moverTriangulo(1,1);
     t1.rotarTriangulo180(); 
     /*Aquí se pueden agregar más comportamientos de las figuras*/          
    }

    /**
     * Se ejecuta indeterminadamente dibujando lo que se encuentra programado.
     */
    @Override
    public void draw(){
     background(255,255,255);
     strokeWeight(2);
     stroke(0,0,0);
     textSize(15);
     fill(0,0,255);
     /*Pintar eje Y*/
     stroke(0,0,255);
     line(ancho/2,0,ancho/2,largo);         
     for(int i=(largo/2),j=0,k=(largo/2), l=0; i > 0; i-=30,j++,k+=30,l--){
      if(j == 0)
       continue;
      text("--" + j,(largo/2) - 4 ,i+4);
      text("--" + l,(largo/2) - 4 ,k+4);
     }
     /*Pintar eje X*/
     line(0,largo/2,ancho,largo/2);
     for(int i=(ancho/2),j=0 , k=(largo/2), l=0; i < ancho; i+=30,j++,k-=30,l--){
      if(j == 0)
       continue;
      text("|",i-2,(ancho/2)+4);
      text(j,i-3,(ancho/2)+20);
      text("|",k-2,(ancho/2)+4);      
      text(l,k-3,(ancho/2)+20);
     } 
     /*Aquí se pueden pintar más figuras */   
     dibujaTriangulo(t1);
     //dibujarTriangulo(t2);
    }

    /**
  * Dibuja un triángulo.
     */
    public void dibujaTriangulo(Triangulo t){     
     strokeWeight(5);
     stroke(255,0,0);
     fill(0,0,0);
     textSize(20);
     line((float)t.getL1().getP1().obtenerX()*30+(ancho/2),(largo/2) - (float)t.getL1().getP1().obtenerY()*30,
       (float)t.getL1().getP2().obtenerX()*30+(ancho/2),(largo/2) - (float)t.getL1().getP2().obtenerY()*30);   
      text(t.getL1().getP1().getEtiqueta(),(float)t.getL1().getP1().obtenerX()*30+(ancho/2)+10,(largo/2) - (float)t.getL1().getP1().obtenerY()*30);

     line((float)t.getL2().getP1().obtenerX()*30+(ancho/2),(largo/2) - (float)t.getL2().getP1().obtenerY()*30,
       (float)t.getL2().getP2().obtenerX()*30+(ancho/2),(largo/2) - (float)t.getL2().getP2().obtenerY()*30);     
      text(t.getL1().getP2().getEtiqueta(),(float)t.getL1().getP2().obtenerX()*30+(ancho/2)+10,(largo/2) - (float)t.getL1().getP2().obtenerY()*30);

     line((float)t.getL3().getP1().obtenerX()*30+(ancho/2),(largo/2) - (float)t.getL3().getP1().obtenerY()*30,
       (float)t.getL3().getP2().obtenerX()*30+(ancho/2),(largo/2) - (float)t.getL3().getP2().obtenerY()*30);     
      text(t.getL2().getP2().getEtiqueta(),(float)t.getL2().getP2().obtenerX()*30+(ancho/2)+10,(largo/2) - (float)t.getL2().getP2().obtenerY()*30);
    }


 public static void main(String[] args) {
     PApplet.main(new String[] { "Prueba" });
 }
}
