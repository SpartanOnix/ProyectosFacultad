/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.visualización.dibujantes;

import ed.estructuras.nolineales.NodoBinario;
import ed.estructuras.nolineales.NodoRojinegro;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 *
 * @author veronica
 */
public class DibujanteDeÁrbolRojinegro extends DibujanteDeÁrbolBinarioOrdenado {
    
    @Override
    protected void escribeNodo(NodoBinario<?> nb, int x0, int x1, int y, BufferedWriter escriba) throws IOException{
        NodoRojinegro<?> nr = (NodoRojinegro<?>)nb;
        int x = (x1 + x0) / 2;
        String línea;
        
        // Aristas
        int hx;
        if(nb.getHijoI() != null) {
            hx = ((x0 + (x1 - x0)/2) + x0) / 2;
            línea = "<line x1=\"" + x + "\" y1=\"" + y + "\" x2=\"" +
                    hx + "\" y2=\"" + (y + TAM_NODO) + "\" style=\"stroke:black;stroke-width:2\" />";
            escriba.write(línea); escriba.newLine();
        }
        if(nb.getHijoD() != null) {
            hx = (x1 + (x0 + (x1 - x0)/2) + 1) / 2;
            línea = "<line x1=\"" + x + "\" y1=\"" + y + "\" x2=\"" +
                    hx + "\" y2=\"" + (y + TAM_NODO) + "\" style=\"stroke:black;stroke-width:2\" />";
            escriba.write(línea); escriba.newLine();
        }
        
        // Nodo
        línea = "<g transform=\"translate(" + x + "," + y + ")\"><circle cx=\"0\" cy=\"0\" r=\"20\" stroke=\"black\" stroke-width=\"3\" style=\"fill:" + colorName(nr) + "\">";
        escriba.write(línea); escriba.newLine();
        
        escriba.write("</circle>"); escriba.newLine();
        línea = "<text x=\"-7\" y=\"7\" style=\"fill:white\" font-size=\"20\">" + nb.getElemento() + "</text>";
        escriba.write(línea); escriba.newLine();
        línea = "<text x=\"-14\" y=\"35\" style=\"fill:black\" font-size=\"15\">h=" + nb.getAltura() + "</text></g>";
        escriba.write(línea); escriba.newLine();
        
        // Hijos
        if(nb.getHijoI() != null) {
            escribeNodo(nb.getHijoI(), x0, x0 + (x1 - x0)/2, y + TAM_NODO, escriba);
        }
        if(nb.getHijoD() != null) {
            escribeNodo(nb.getHijoD(), x0 + (x1 - x0)/2 + 1, x1, y + TAM_NODO, escriba);
        }
    }
    
    protected String colorName(NodoRojinegro<?> nr) {
        if(nr.getColor() == NodoRojinegro.Color.NEGRO) {
            return "black";
        } else return "red";
    }
}
