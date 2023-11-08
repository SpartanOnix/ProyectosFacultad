
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
public abstract class PilaTestA extends ColeccionAbstractaTestA{

    @Override
    public void init(){
        allowRemove = allowRemoveAll = allowRetainAll = allowIteratorRemove = false;
    }

    protected abstract IPila<String> getPila();

    public static final String PilaA = "PilaA";

    //expulsa

    @Test
    public void expulsaContainsTest(){
        IPila<String> pila;
        startTest(PilaA, "expulsaContains", "Tests the stack do not contains any expulsa element",1.0);
        pila = getPila();
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            pila.empuja(rsgIt.next());
        }
        while(!pila.isEmpty()){
            pila.expulsa();
        }
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            assertFalse(pila.contains(rsgIt.next()));
        }
        addUp(1.0);
        passed();
    }

    @Test
    public void expulsaMiraTest(){
        String s;
        IPila<String> pila;
        startTest(PilaA, "expulsaMira", "Tests the expulsa and mira methods return the same element",1.0);
        pila = getPila();
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            pila.empuja(rsgIt.next());
        }
        while(!pila.isEmpty()){
            s = pila.mira();
            if(s == null){
                assertNull(pila.expulsa());
            }else{
                assertTrue(s.equals(pila.expulsa()));
            }
        }
        addUp(1.0);
        passed();
    }

    @Test
    public void expulsaSizeTest(){
        int i;
        IPila<String> pila;
        startTest(PilaA, "expulsaSize", "Tests the stack size after expulsa and empty when all elements are removed",1.0);
        pila = getPila();
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            pila.empuja(rsgIt.next());
        }
        i = 1;
        while(i <= range){
            pila.expulsa();
            assertEquals(pila.size(), range - i++);
        }
        assertTrue(pila.isEmpty());
        addUp(1.0);
        passed();
    }

    @Test
    public void expulsaEmptyTest(){
        IPila<String> pila;
        startTest(PilaA, "expulsaEmpty", "Tests the expulsa method returns null when empty",1.0);
        pila = getPila();
        assertNull(pila.expulsa());
        addUp(1.0);
        passed();
    }

    //empuja

    @Test
    public void empujaContainsTest(){
        IPila<String> pila;
        startTest(PilaA, "empujaContains", "Tests the stack contains empuja elements",1.0);
        pila = getPila();
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            pila.empuja(rsgIt.next());
        }
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            assertTrue(pila.contains(rsgIt.next()));
        }
        addUp(1.0);
        passed();
    }

    @Test
    public void empujaSizeTest(){
        int i;
        IPila<String> pila;
        startTest(PilaA, "empujaSize", "Tests the stack has correct size and is not empty after empuja",1.0);
        pila = getPila();
        rsgIt = rsg.iterator();
        i = 1;
        while(rsgIt.hasNext()){
            pila.empuja(rsgIt.next());
            assertEquals(pila.size(), i++);
            assertFalse(pila.isEmpty());
        }
        addUp(1.0);
        passed();
    }

    //LIFO

    @Test
    public void LIFOTest(){
        int i;
        String s;
        IPila<String> pila;
        LinkedList<String> l;
        startTest(PilaA, "LIFO", "Tests the stack is LIFO ",1.0);
        pila = getPila();
        l = new LinkedList<>();
        rsgIt = rsg.iterator();
        i = 1;
        while(rsgIt.hasNext()){
            s = rsgIt.next();
            pila.empuja(s);
            l.addFirst(s);
        }
        while(!pila.isEmpty()){
            s = pila.expulsa();
            if(s == null){
                assertNull(l.removeFirst());
            }else{
                assertTrue(s.equals(l.removeFirst()));
            }
        }
        addUp(1.0);
        passed();
    }
}
