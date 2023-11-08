/*
 * Código utilizado para el curso de Estructuras de Datos.
 * Se permite consultarlo para fines didácticos en forma personal,
 * pero no está permitido transferirlo tal cual a estudiantes actuales o potenciales.
 */
package ed.visualización.demos;

import ed.estructuras.nolineales.ÁrbolRojinegro;
import ed.visualización.dibujantes.DibujanteDeÁrbolRojinegro;

/**
 *
 * @author veronica
 */
public class DemoÁrbolesRojinegros extends Demo {
    private DibujanteDeÁrbolRojinegro dibujante;
    
    @DemoMethod(name = "Árbol vacío")
    public String dibujaÁrbol0() {
        ÁrbolRojinegro<Integer> árbol;
        árbol = new ÁrbolRojinegro<>();
        dibujante = new DibujanteDeÁrbolRojinegro();
        dibujante.setEstructura(árbol);
        return dibujante.drawSVG();
    }
    
    @DemoMethod(name = "Un elemento")
    public String dibujaÁrbol1() {
        ÁrbolRojinegro<String> árbol;
        árbol = new ÁrbolRojinegro();
        dibujante = new DibujanteDeÁrbolRojinegro();
        dibujante.setEstructura(árbol);
        árbol.add("A");
        return dibujante.drawSVG();
    }
    
    @DemoMethod(name = "Dos elementos")
    public String dibujaÁrbol2() {
        ÁrbolRojinegro<String> árbol;
        árbol = new ÁrbolRojinegro();
        dibujante = new DibujanteDeÁrbolRojinegro();
        dibujante.setEstructura(árbol);
        árbol.add("B");
        árbol.add("A");
        return dibujante.drawSVG();
    }
    
    @DemoMethod(name = "Dos elementos derecha")
    public String dibujaÁrbol2b() {
        ÁrbolRojinegro<String> árbol;
        árbol = new ÁrbolRojinegro();
        dibujante = new DibujanteDeÁrbolRojinegro();
        dibujante.setEstructura(árbol);
        árbol.add("A");
        árbol.add("B");
        return dibujante.drawSVG();
    }
    
    @DemoMethod(name = "Tres balanceados")
    public String dibujaÁrbol3() {
        ÁrbolRojinegro<String> árbol;
        árbol = new ÁrbolRojinegro();
        dibujante = new DibujanteDeÁrbolRojinegro();
        dibujante.setEstructura(árbol);
        árbol.add("B");
        árbol.add("A");
        árbol.add("C");
        return dibujante.drawSVG();
    }
    
    @DemoMethod(name = "Caso 1 (izquierda)")
    public String dibujaÁrbol4() {
        ÁrbolRojinegro<String> árbol;
        árbol = new ÁrbolRojinegro();
        dibujante = new DibujanteDeÁrbolRojinegro();
        dibujante.setEstructura(árbol);
        árbol.add("C");
        árbol.add("B");
        árbol.add("D");
        árbol.add("A");
        return dibujante.drawSVG();
    }
    
    @DemoMethod(name = "Caso 1 (derecha)")
    public String dibujaÁrbol4d() {
        ÁrbolRojinegro<String> árbol;
        árbol = new ÁrbolRojinegro();
        dibujante = new DibujanteDeÁrbolRojinegro();
        dibujante.setEstructura(árbol);
        árbol.add("B");
        árbol.add("A");
        árbol.add("C");
        árbol.add("D");
        return dibujante.drawSVG();
    }
    
    @DemoMethod(name = "Caso 1b")
    public String dibujaÁrbol4e() {
        ÁrbolRojinegro<String> árbol;
        árbol = new ÁrbolRojinegro();
        dibujante = new DibujanteDeÁrbolRojinegro();
        dibujante.setEstructura(árbol);
        árbol.add("D");
        árbol.add("C");
        árbol.add("A");
        árbol.add("B");
        return dibujante.drawSVG();
    }
    
    @DemoMethod(name = "Caso 2")
    public String dibujaLL() {
        ÁrbolRojinegro<String> árbol;
        árbol = new ÁrbolRojinegro();
        dibujante = new DibujanteDeÁrbolRojinegro();
        dibujante.setEstructura(árbol);
        árbol.add("C");
        árbol.add("B");
        árbol.add("A");
        return dibujante.drawSVG();
    }
    
    @DemoMethod(name = "Caso 2 (espejo)")
    public String dibujaRR() {
        ÁrbolRojinegro<String> árbol;
        árbol = new ÁrbolRojinegro();
        dibujante = new DibujanteDeÁrbolRojinegro();
        dibujante.setEstructura(árbol);
        árbol.add("A");
        árbol.add("B");
        árbol.add("C");
        return dibujante.drawSVG();
    }
    
    @DemoMethod(name = "Caso 3")
    public String dibujaLR() {
        ÁrbolRojinegro<String> árbol;
        árbol = new ÁrbolRojinegro();
        dibujante = new DibujanteDeÁrbolRojinegro();
        dibujante.setEstructura(árbol);
        árbol.add("C");
        árbol.add("A");
        árbol.add("B");
        return dibujante.drawSVG();
    }
    
    @DemoMethod(name = "Caso 3 (espejo)")
    public String dibujaRL() {
        ÁrbolRojinegro<String> árbol;
        árbol = new ÁrbolRojinegro();
        dibujante = new DibujanteDeÁrbolRojinegro();
        dibujante.setEstructura(árbol);
        árbol.add("B");
        árbol.add("A");
        árbol.add("C");
        return dibujante.drawSVG();
    }
    
    @DemoMethod(name = "Cormen")
    public String dibujaCormen() {
        ÁrbolRojinegro<Integer> árbol;
        árbol = new ÁrbolRojinegro();
        dibujante = new DibujanteDeÁrbolRojinegro();
        dibujante.setEstructura(árbol);
        árbol.add(new Integer(11));
        árbol.add(new Integer(2));
        árbol.add(new Integer(14));
        árbol.add(new Integer(1));
        árbol.add(new Integer(7));
        árbol.add(new Integer(15));
        árbol.add(new Integer(5));
        árbol.add(new Integer(8));
        return dibujante.drawSVG();
    }
    
    @DemoMethod(name = "Cormen 3 en 1")
    public String dibujaCormen3en1() {
        ÁrbolRojinegro<Integer> árbol;
        árbol = new ÁrbolRojinegro();
        dibujante = new DibujanteDeÁrbolRojinegro();
        dibujante.setEstructura(árbol);
        árbol.add(new Integer(11));
        árbol.add(new Integer(2));
        árbol.add(new Integer(14));
        árbol.add(new Integer(1));
        árbol.add(new Integer(7));
        árbol.add(new Integer(15));
        árbol.add(new Integer(5));
        árbol.add(new Integer(8));
        árbol.add(new Integer(4));
        return dibujante.drawSVG();
    }
    
    @DemoMethod(name = "Muchos nodos")
    public String dibujaMuchosNodos() {
        ÁrbolRojinegro<Integer> árbol;
        árbol = new ÁrbolRojinegro();
        dibujante = new DibujanteDeÁrbolRojinegro();
        dibujante.setEstructura(árbol);
        árbol.add(new Integer(11));
        árbol.add(new Integer(2));
        árbol.add(new Integer(14));
        árbol.add(new Integer(1));
        árbol.add(new Integer(7));
        árbol.add(new Integer(15));
        árbol.add(new Integer(5));
        árbol.add(new Integer(8));
        árbol.add(new Integer(4));
        árbol.add(new Integer(54));
        árbol.add(new Integer(48));
        árbol.add(new Integer(10));
        árbol.add(new Integer(60));
        árbol.add(new Integer(58));
        árbol.add(new Integer(36));
        árbol.add(new Integer(3));
        árbol.add(new Integer(9));
        árbol.add(new Integer(40));
        árbol.add(new Integer(72));
        árbol.add(new Integer(6));
        árbol.add(new Integer(57));
        árbol.add(new Integer(77));
        return dibujante.drawSVG();
    }
    
    /*@DemoMethod(name = "Remover caso 1")
    public String dibujaRemueveCaso1() {
        ÁrbolRojinegro<Integer> árbol;
        árbol = new ÁrbolRojinegro();
        dibujante = new DibujanteDeÁrbolRojinegro();
        dibujante.setEstructura(árbol);
        árbol.add(new Integer(50));
        árbol.add(new Integer(25));
        árbol.add(new Integer(75));
        árbol.add(new Integer(15));
        //árbol.add(new Integer(60));
        árbol.add(new Integer(90));
        árbol.add(new Integer(7));
        árbol.add(new Integer(20));
        árbol.add(new Integer(55));
        árbol.add(new Integer(8));
        árbol.add(new Integer(3));
        árbol.add(new Integer(23));
        árbol.add(new Integer(17));
        árbol.add(new Integer(54));
        árbol.add(new Integer(53));
        árbol.add(new Integer(52));
        árbol.add(new Integer(51));
        árbol.add(new Integer(1));
        return dibujante.drawSVG();
    }*/
    
    @DemoMethod(name = "Remover 1 antes")
    public String dibujaRemueve1Antes() {
        ÁrbolRojinegro<Integer> árbol;
        árbol = new ÁrbolRojinegro();
        dibujante = new DibujanteDeÁrbolRojinegro();
        dibujante.setEstructura(árbol);
        árbol.add(new Integer(50));
        return dibujante.drawSVG();
    }
    
    @DemoMethod(name = "Remover 1")
    public String dibujaRemueve1() {
        ÁrbolRojinegro<Integer> árbol;
        árbol = new ÁrbolRojinegro();
        dibujante = new DibujanteDeÁrbolRojinegro();
        dibujante.setEstructura(árbol);
        árbol.add(new Integer(50));
        árbol.remove(new Integer(50));
        return dibujante.drawSVG();
    }
    
    @DemoMethod(name = "Remover A de caso 1 derecha")
    public String dibujaRemueve2() {
        ÁrbolRojinegro<String> árbol;
        árbol = new ÁrbolRojinegro();
        dibujante = new DibujanteDeÁrbolRojinegro();
        dibujante.setEstructura(árbol);
        árbol.add("B");
        árbol.add("A");
        árbol.add("C");
        árbol.add("D");
        árbol.remove("A");
        return dibujante.drawSVG();
    }
    
    @DemoMethod(name = "Remueve 1 de Cormen (Caso 4)")
    public String dibujaRemueveCormen() {
        ÁrbolRojinegro<Integer> árbol;
        árbol = new ÁrbolRojinegro();
        dibujante = new DibujanteDeÁrbolRojinegro();
        dibujante.setEstructura(árbol);
        árbol.add(new Integer(11));
        árbol.add(new Integer(2));
        árbol.add(new Integer(14));
        árbol.add(new Integer(1));
        árbol.add(new Integer(7));
        árbol.add(new Integer(15));
        árbol.add(new Integer(5));
        árbol.add(new Integer(8));
        árbol.remove(new Integer(1));
        return dibujante.drawSVG();
    }
    
    @DemoMethod(name = "Remueve 1,15,14 de Cormen (Caso 1)")
    public String dibujaRemueveCormen2() {
        ÁrbolRojinegro<Integer> árbol;
        árbol = new ÁrbolRojinegro();
        dibujante = new DibujanteDeÁrbolRojinegro();
        dibujante.setEstructura(árbol);
        árbol.add(new Integer(11));
        árbol.add(new Integer(2));
        árbol.add(new Integer(14));
        árbol.add(new Integer(1));
        árbol.add(new Integer(7));
        árbol.add(new Integer(15));
        árbol.add(new Integer(5));
        árbol.add(new Integer(8));
        árbol.remove(new Integer(1));
        árbol.remove(new Integer(15));
        árbol.remove(new Integer(14));
        return dibujante.drawSVG();
    }
}
