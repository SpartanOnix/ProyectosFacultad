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
    Figura [] figuras;
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
        figuras = new Figura[3];
		Triangulo t = new Triangulo();
        Cuadrado c = new Cuadrado();
        Circulo ci = new Circulo(new Punto(1,-1),10);
        figuras[0] = t;
        figuras[1] = c;
        figuras[2] = ci;
        for (Figura f: figuras) {        
            System.out.println(f);   
        }
    }

   	/**
     * Se ejecuta cuando se hace click sobre la pantalla.- 
     */
    @Override
    public void mouseClicked() {
        ((FiguraI)figuras[0]).rotar90();
        ((FiguraI)figuras[1]).mover(1,1);
        ((FiguraI)figuras[2]).mover(-1,-1);
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
    	for(Figura f: figuras){
            dibuja(f);
        }
    }

    /**
	 * Dibuja un triángulo.
     * @param t La figura a dibujar.
     */
    public void dibuja(Figura t){    	
    	strokeWeight(5);
    	stroke(255,0,0);
    	fill(0,0,0);
    	textSize(20);
        if(t instanceof Circulo){
            Circulo c = (Circulo)t;
            fill(255,255,255,0);
            ellipse((float)c.getCentro().obtenerX()*30+(ancho/2),(float)c.getCentro().obtenerY()*30+(ancho/2),(float)c.getRadio()*30,(float)c.getRadio()*30);
        }else{
            for(Linea l: t.getLineas()){
                line((float)l.getP1().obtenerX()*30+(ancho/2),(largo/2) - (float)l.getP1().obtenerY()*30,
                     (float)l.getP2().obtenerX()*30+(ancho/2),(largo/2) - (float)l.getP2().obtenerY()*30);   
                text(l.getP1().getEtiqueta(),(float)l.getP1().obtenerX()*30+(ancho/2)+10,(largo/2) - (float)l.getP1().obtenerY()*30);                        
                text(l.getP2().getEtiqueta(),(float)l.getP2().obtenerX()*30+(ancho/2)+10,(largo/2) - (float)l.getP2().obtenerY()*30);
            }
        }
    }


	public static void main(String[] args) {
    	PApplet.main(new String[] { "Prueba" });
	}
}