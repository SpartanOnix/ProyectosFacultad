/*
 * Código utilizado para el curso de Estructuras de Datos.
 * Se permite consultarlo para fines didácticos en forma personal,
 * pero no está permitido transferirlo tal cual a estudiantes actuales o potenciales.
 */
package ed.visualización.demos;

import ed.estructuras.nolineales.ÁrbolAVL;
import ed.visualización.dibujantes.DibujanteDeÁrbolBinarioOrdenado;

/**
 *
 * @author veronica
 */
public class DemoÁrbolesAVL extends Demo {
    private DibujanteDeÁrbolBinarioOrdenado dibujante;
    
    @DemoMethod(name = "Árbol vacío")
    public String dibujaÁrbol0() {
        ÁrbolAVL<String> árbol;
        árbol = new ÁrbolAVL<>();
        dibujante = new DibujanteDeÁrbolBinarioOrdenado();
        dibujante.setEstructura(árbol);
        return dibujante.drawSVG();
    }
    
    @DemoMethod(name = "Un elemento")
    public String dibujaÁrbol1() {
        ÁrbolAVL<String> árbol;
        árbol = new ÁrbolAVL<>();
        dibujante = new DibujanteDeÁrbolBinarioOrdenado();
        dibujante.setEstructura(árbol);
        árbol.add("A");
        return dibujante.drawSVG();
    }
    
    @DemoMethod(name = "Desbalance LL")
    public String dibujaLL() {
        ÁrbolAVL<String> árbol;
        árbol = new ÁrbolAVL<>();
        dibujante = new DibujanteDeÁrbolBinarioOrdenado();
        dibujante.setEstructura(árbol);
        árbol.add("C");
        árbol.add("B");
        árbol.add("A");
        return dibujante.drawSVG();
    }
    
    @DemoMethod(name = "Desbalance RR")
    public String dibujaRR() {
        ÁrbolAVL<String> árbol;
        árbol = new ÁrbolAVL<>();
        dibujante = new DibujanteDeÁrbolBinarioOrdenado();
        dibujante.setEstructura(árbol);
        árbol.add("A");
        árbol.add("B");
        árbol.add("C");
        return dibujante.drawSVG();
    }
    
    @DemoMethod(name = "Desbalance LR")
    public String dibujaLR() {
        ÁrbolAVL<String> árbol;
        árbol = new ÁrbolAVL<>();
        dibujante = new DibujanteDeÁrbolBinarioOrdenado();
        dibujante.setEstructura(árbol);
        árbol.add("C");
        árbol.add("A");
        árbol.add("B");
        return dibujante.drawSVG();
    }
    
    @DemoMethod(name = "Desbalance RL")
    public String dibujaRL() {
        ÁrbolAVL<String> árbol;
        árbol = new ÁrbolAVL<>();
        dibujante = new DibujanteDeÁrbolBinarioOrdenado();
        dibujante.setEstructura(árbol);
        árbol.add("A");
        árbol.add("C");
        árbol.add("B");
        return dibujante.drawSVG();
    }
    
    @DemoMethod(name = "Todas las rotaciones")
    public String dibujaCuatro() {
        ÁrbolAVL<Integer> árbol;
        árbol = new ÁrbolAVL<>();
        dibujante = new DibujanteDeÁrbolBinarioOrdenado();
        dibujante.setEstructura(árbol);
        árbol.add(new Integer(65));
        árbol.add(new Integer(50));
        árbol.add(new Integer(23));
        árbol.add(new Integer(70));
        árbol.add(new Integer(82));
        árbol.add(new Integer(68));
        árbol.add(new Integer(39));
        return dibujante.drawSVG();
    }
    
    @DemoMethod(name = "Todas las rotaciones hasta raíz")
    public String dibujaCuatroExtra() {
        ÁrbolAVL<Integer> árbol;
        árbol = new ÁrbolAVL<>();
        dibujante = new DibujanteDeÁrbolBinarioOrdenado();
        dibujante.setEstructura(árbol);
        árbol.add(new Integer(65));
        árbol.add(new Integer(50));
        árbol.add(new Integer(23));
        árbol.add(new Integer(70));
        árbol.add(new Integer(82));
        árbol.add(new Integer(68));
        árbol.add(new Integer(39));
        árbol.add(new Integer(10));
        árbol.add(new Integer(30));
        árbol.add(new Integer(55));
        árbol.add(new Integer(25));
        return dibujante.drawSVG();
    }
    
    @DemoMethod(name = "Todas las rotaciones y remover 68")
    public String dibujaCuatroRemover1() {
        ÁrbolAVL<Integer> árbol;
        árbol = new ÁrbolAVL<>();
        dibujante = new DibujanteDeÁrbolBinarioOrdenado();
        dibujante.setEstructura(árbol);
        árbol.add(new Integer(65));
        árbol.add(new Integer(50));
        árbol.add(new Integer(23));
        árbol.add(new Integer(70));
        árbol.add(new Integer(82));
        árbol.add(new Integer(68));
        árbol.add(new Integer(39));
        árbol.remove(new Integer(68));
        return dibujante.drawSVG();
    }
    
    @DemoMethod(name = "Todas las rotaciones y remover 82")
    public String dibujaCuatroRemover2() {
        ÁrbolAVL<Integer> árbol;
        árbol = new ÁrbolAVL<>();
        dibujante = new DibujanteDeÁrbolBinarioOrdenado();
        dibujante.setEstructura(árbol);
        árbol.add(new Integer(65));
        árbol.add(new Integer(50));
        árbol.add(new Integer(23));
        árbol.add(new Integer(70));
        árbol.add(new Integer(82));
        árbol.add(new Integer(68));
        árbol.add(new Integer(39));
        árbol.remove(new Integer(68));
        árbol.remove(new Integer(82));
        return dibujante.drawSVG();
    }
    
    @DemoMethod(name = "Todas las rotaciones y remover 70")
    public String dibujaCuatroRemover3() {
        ÁrbolAVL<Integer> árbol;
        árbol = new ÁrbolAVL<>();
        dibujante = new DibujanteDeÁrbolBinarioOrdenado();
        dibujante.setEstructura(árbol);
        árbol.add(new Integer(65));
        árbol.add(new Integer(50));
        árbol.add(new Integer(23));
        árbol.add(new Integer(70));
        árbol.add(new Integer(82));
        árbol.add(new Integer(68));
        árbol.add(new Integer(39));
        árbol.remove(new Integer(68));
        árbol.remove(new Integer(82));
        árbol.remove(new Integer(70));
        return dibujante.drawSVG();
    }
}
