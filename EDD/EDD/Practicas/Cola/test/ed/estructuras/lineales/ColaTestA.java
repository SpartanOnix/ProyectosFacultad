
package ed.estructuras.lineales;

import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.Random;
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
public abstract class ColaTestA extends ColeccionAbstractaTestA{

    @Override
    public void init(){
        allowRemove = allowRemoveAll = allowRetainAll = allowIteratorRemove = false;
    }

    protected abstract ICola<String> getCola();

    public static final String ColaA = "ColaA";

    //atiende

    @Test
    public void atiendeContainsTest(){
        ICola<String> cola;
        startTest(ColaA, "atiendeContains", "Tests the queue do not contains any atiende element",1.0);
        cola = getCola();
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            cola.forma(rsgIt.next());
        }
        while(!cola.isEmpty()){
            cola.atiende();
        }
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            assertFalse(cola.contains(rsgIt.next()));
        }
        addUp(1.0);
        passed();
    }

    @Test
    public void atiendeMiraTest(){
        String s;
        ICola<String> cola;
        startTest(ColaA, "atiendeMira", "Tests the atiende and mira methods return the same element",1.0);
        cola = getCola();
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            cola.forma(rsgIt.next());
        }
        while(!cola.isEmpty()){
            s = cola.mira();
            if(s == null){
                assertNull(cola.atiende());
            }else{
                assertTrue(s.equals(cola.atiende()));
            }
        }
        addUp(1.0);
        passed();
    }

    @Test
    public void atiendeSizeTest(){
        int i;
        ICola<String> cola;
        startTest(ColaA, "atiendeSize", "Tests the queue size after atiende and empty when all elements are removed",1.0);
        cola = getCola();
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            cola.forma(rsgIt.next());
        }
        i = 1;
        while(i <= range){
            cola.atiende();
            assertEquals(cola.size(), range - i++);
        }
        assertTrue(cola.isEmpty());
        addUp(1.0);
        passed();
    }

    @Test
    public void atiendeEmptyTest(){
        ICola<String> cola;
        startTest(ColaA, "atiendeEmpty", "Tests the atiende method returns null when empty",1.0);
        cola = getCola();
        assertNull(cola.atiende());
        addUp(1.0);
        passed();
    }

    //forma

    @Test
    public void formaContainsTest(){
        ICola<String> cola;
        startTest(ColaA, "formaContains", "Tests the queue contains forma elements",1.0);
        cola = getCola();
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            cola.forma(rsgIt.next());
        }
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            assertTrue(cola.contains(rsgIt.next()));
        }
        addUp(1.0);
        passed();
    }

    @Test
    public void formaSizeTest(){
        int i;
        ICola<String> cola;
        startTest(ColaA, "formaSize", "Tests the queue has correct size and is not empty after forma",1.0);
        cola = getCola();
        rsgIt = rsg.iterator();
        i = 1;
        while(rsgIt.hasNext()){
            cola.forma(rsgIt.next());
            assertEquals(cola.size(), i++);
            assertFalse(cola.isEmpty());
        }
        addUp(1.0);
        passed();
    }

    //FIFO

    @Test
    public void FIFOTest(){
        int i;
        String s;
        ICola<String> cola;
        LinkedList<String> l;
        startTest(ColaA, "FIFO", "Tests the queue is FIFO ",1.0);
        cola = getCola();
        l = new LinkedList<>();
        rsgIt = rsg.iterator();
        i = 1;
        while(rsgIt.hasNext()){
            s = rsgIt.next();
            cola.forma(s);
            l.addFirst(s);
        }
        while(!cola.isEmpty()){
            s = cola.atiende();
            if(s == null){
                assertNull(l.removeLast());
            }else{
                assertTrue(s.equals(l.removeLast()));
            }
        }
        addUp(1.0);
        passed();
    }
}
