
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
public class PilaLigadaTest extends PilaTestA{

    @Override
    protected ColeccionAbstracta<String> getColeccionAbstracta(){
        return getPilaLigada();
    }

    @Override
    protected IPila<String> getPila(){
        return getPilaLigada();
    }

    protected PilaLigada<String> getPilaLigada(){
        return new PilaLigada<>();
    }
}
