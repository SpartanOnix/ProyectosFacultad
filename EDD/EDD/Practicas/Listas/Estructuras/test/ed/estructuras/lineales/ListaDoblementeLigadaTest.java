
package ed.estructuras.lineales;

import java.util.Iterator;
import java.util.Collection;
import java.util.List;
import java.util.LinkedList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ed.estructuras.*;

/**
 *
 * @author mindahrelfen
 */
public class ListaDoblementeLigadaTest extends ListaTestA{

    @Override
    protected Collection<String> getColeccionAbstracta(){
        return getListaDoblementeLigada();
    }

    @Override
    protected List<String> getLista(){
        return getListaDoblementeLigada();
    }

    protected ListaDoblementeLigada<String> getListaDoblementeLigada(){
        return new ListaDoblementeLigada<>();
    }
}
