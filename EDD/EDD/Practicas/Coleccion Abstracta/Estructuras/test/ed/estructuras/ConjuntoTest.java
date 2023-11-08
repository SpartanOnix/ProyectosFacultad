
package ed.estructuras;

import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Random;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mindahrelfen
 */
public class ConjuntoTest extends ColeccionAbstractaTestA{

    protected ColeccionAbstracta<String> getColeccionAbstracta(){
        return getConjunto();
    }

    protected Conjunto<String> getConjunto(){
        return new Conjunto<>();
    }
}