
package ed.estructuras.lineales;

import java.util.Iterator;;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ed.estructuras.*;

/**
 *
 * @author mindahrelfen
 */
public class ColaArregloTest extends ColaTestA{

    public static final String ColaArreglo = "ColaArreglo";

    @Override
    protected ColeccionAbstracta<String> getColeccionAbstracta(){
        return getColaArreglo();
    }

    @Override
    protected ICola<String> getCola(){
        return getColaArreglo();
    }

    protected ColaArreglo<String> getColaArreglo(){
        return new ColaArreglo<>(new String[0]);
    }

    @Test(expected=IllegalArgumentException.class)
    public void constructorIllegalWithSizeTest(){
        ColaArreglo<String> cola;
        startTest(ColaArreglo, "constructorIllegalWithSize", "Tests an IllegalArgumentException is thrown if the array parameter in constructor has length != 0",1.0);
        try{
            cola = new ColaArreglo<>(new String[range],range);
        }catch(IllegalArgumentException e){
            addUp(1.0);
            passed();
            throw e;
        }
    }

    @Test(expected=IllegalArgumentException.class)
    public void constructorIllegalNoSizeTest(){
        ColaArreglo<String> cola;
        startTest(ColaArreglo, "constructorIllegalNoSize", "Tests an IllegalArgumentException is thrown if the array parameter in constructor has length != 0",1.0);
        try{
          System.out.println(range);
            cola = new ColaArreglo<>(new String[range]);
        }catch(IllegalArgumentException e){
            addUp(1.0);
            passed();
            throw e;
        }
    }
}
