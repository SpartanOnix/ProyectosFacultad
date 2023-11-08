
package ed.estructuras;

import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ed.*;


/**
 *
 * @author mindahrelfen
 */
public abstract class ColeccionAbstractaTestA extends Calificador{

    protected boolean allowRemove;
    protected boolean allowRemoveAll;
    protected boolean allowRetainAll;
    protected boolean allowIteratorRemove;

    public static final String CA = "CA";

    @Override
    public void init(){
        allowRemove = allowRemoveAll = allowRetainAll = allowIteratorRemove = true;
    }

    protected abstract Collection<String> getColeccionAbstracta();

    //add

    @Test
    public void addContainsTest(){
        Collection<String> ac;
        startTest(CA, "addContains", "Tests the collection contains elements added elements",1.0);
        ac = getColeccionAbstracta();
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            assertTrue(ac.add(rsgIt.next()));
        }
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            assertTrue(ac.contains(rsgIt.next()));
        }
        addUp(1.0);
        passed();
    }

    @Test
    public void addSizeTest(){
        int i;
        Collection<String> ac;
        startTest(CA, "addSize", "Tests the collection has correct size and is not empty after add",1.0);
        ac = getColeccionAbstracta();
        rsgIt = rsg.iterator();
        i = 1;
        while(rsgIt.hasNext()){
            assertTrue(ac.add(rsgIt.next()));
            assertEquals(ac.size(), i++);
            assertFalse(ac.isEmpty());
        }
        addUp(1.0);
        passed();
    }

    //addAll

    @Test
    public void addAllContainsTest(){
        Collection<String> ac;
        LinkedList<String> l;
        startTest(CA, "addAllContains", "Tests the collection contains all added elements after addAll",1.0);
        ac = getColeccionAbstracta();
        l = new LinkedList<>();
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            l.add(rsgIt.next());
        }
        assertTrue(ac.addAll(l));
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            assertTrue(ac.contains(rsgIt.next()));
        }
        addUp(1.0);
        passed();
    }

    @Test
    public void addAllSizeTest(){
        Collection<String> ac;
        LinkedList<String> l;
        startTest(CA, "addAllSize", "Tests the collection has correct size and is not empty after addAll",1.0);
        ac = getColeccionAbstracta();
        l = new LinkedList<>();
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            l.add(rsgIt.next());
        }
        assertTrue(ac.addAll(l));
        assertEquals(ac.size(), range);
        assertFalse(ac.isEmpty());
        addUp(1.0);
        passed();
    }

    @Test(expected=NullPointerException.class)
    public void addAllNullPointerTest(){
        Collection<String> ac;
        startTest(CA, "addAllNullPointer", "Tests a NullPointerException is thrown if the parameter is null",1.0);
        ac = getColeccionAbstracta();
        try{
            ac.addAll(null);
        }catch(NullPointerException e){
            addUp(1.0);
            passed();
            throw e;
        }
    }

    @Test(expected=IllegalArgumentException.class)
    public void addAllIllegalArgumentTest(){
        Collection<String> ac;
        startTest(CA, "addAllIllegalArgument", "Tests an IllegalArgumentException is thrown if the parameter is itself",1.0);
        ac = getColeccionAbstracta();
        try{
            ac.addAll(ac);
        }catch(IllegalArgumentException e){
            addUp(1.0);
            passed();
            throw e;
        }
    }

    //clear

    @Test
    public void clearContainsTest(){
        Collection<String> ac;
        startTest(CA, "clearContains", "Tests the collection do not contains any cleared element",1.0);
        ac = getColeccionAbstracta();
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            ac.add(rsgIt.next());
        }
        ac.clear();
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            assertFalse(ac.contains(rsgIt.next()));
        }
        addUp(1.0);
        passed();
    }

    @Test
    public void clearSizeTest(){
        Collection<String> ac;
        startTest(CA, "clearSize", "Tests the collection is empty and zero size after clear for a non empty non zero size collection",1.0);
        ac = getColeccionAbstracta();
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            ac.add(rsgIt.next());
        }
        ac.clear();
        assertTrue(ac.isEmpty());
        assertEquals(ac.size(), 0);
        addUp(1.0);
        passed();
    }

    @Test
    public void clearInitTest(){
        Collection<String> ac1, ac2;
        startTest(CA, "clearInit", "Tests a cleared collection is equal to new collection",1.0);
        ac1 = getColeccionAbstracta();
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            ac1.add(rsgIt.next());
            System.out.println(ac1.toString());
        }
        ac1.clear();
        System.out.println(ac1.toString() + "[+]");
        ac2 = getColeccionAbstracta();
        assertTrue(ac1.equals(ac2));
        assertTrue(ac2.equals(ac1));
        addUp(1.0);
        passed();
    }

    //contains

    //containsAll

    @Test
    public void containsAllTest(){
        Collection<String> ac1, ac2;
        startTest(CA, "containsAll", "Tests the containAll with two non empty equal collections",1.0);
        ac1 = getColeccionAbstracta();
        ac2 = getColeccionAbstracta();
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            ac1.add(rsgIt.next());
        }
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            ac2.add(rsgIt.next());
        }
        assertTrue(ac1.containsAll(ac2));
        assertTrue(ac2.containsAll(ac1));
        addUp(1.0);
        passed();
    }

    @Test
    public void containsAllButOneTest(){
        int i;
        String aux;
        Collection<String> ac1, ac2;
        startTest(CA, "containsAllButOne", "Tests the containAll method with a collection which contains all but one element in the original collection",1.0);
        ac1 = getColeccionAbstracta();
        ac2 = getColeccionAbstracta();
        rsgIt = rsg.iterator();
        i = 0;
        while(rsgIt.hasNext()){
            aux = rsgIt.next();
            if(i++ != 0){
                ac1.add(aux);
            }
            ac2.add(aux);
        }
        assertFalse(ac1.containsAll(ac2));
        assertTrue(ac2.containsAll(ac1));
        addUp(1.0);
        passed();
    }

    @Test
    public void containsAllItselfTest(){
        Collection<String> ac;
        startTest(CA, "containsAllItself", "Tests the containAll method with itself",1.0);
        ac = getColeccionAbstracta();
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            ac.add(rsgIt.next());
        }
        assertTrue(ac.containsAll(ac));
        addUp(1.0);
        passed();
    }
    @Test
    public void containsAllInitTest(){
        Collection<String> ac1, ac2;
        startTest(CA, "containsAllInit", "Tests the containAll with two empty collections",1.0);
        ac1 = getColeccionAbstracta();
        ac2 = getColeccionAbstracta();
        assertTrue(ac1.containsAll(ac2));
        assertTrue(ac2.containsAll(ac1));
        addUp(1.0);
        passed();
    }

    //equals

    @Test
    public void equalsTest(){
        String aux;
        Collection<String> ac1, ac2;
        startTest(CA, "equals", "Tests the equality between two equal collections (same order, size and elements)",1.0);
        ac1 = getColeccionAbstracta();
        ac2 = getColeccionAbstracta();
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            aux = rsgIt.next();
            ac1.add(aux);
            ac2.add(aux);
        }
        assertTrue(ac1.equals(ac2));
        assertTrue(ac2.equals(ac1));
        addUp(1.0);
        passed();
    }

    @Test
    public void equalsItselfTest(){
        Collection<String> ac;
        startTest(CA, "equalsItself", "Tests the equality between a collection with itself",1.0);
        ac = getColeccionAbstracta();
        assertTrue(ac.equals(ac));
        addUp(1.0);
        passed();
    }

    @Test
    public void equalsNullTest(){
        Collection<String> ac;
        startTest(CA, "equalsNull", "Tests the inequality between a collection with null",1.0);
        ac = getColeccionAbstracta();
        assertFalse(ac.equals(null));
        addUp(1.0);
        passed();
    }

    @Test
    public void equalsObjectTest(){
        Collection<String> ac;
        startTest(CA, "equalsObject", "Tests the inequality between a collection with an non collection object",1.0);
        ac = getColeccionAbstracta();
        assertFalse(ac.equals(new Object()));
        addUp(1.0);
        passed();
    }

    //empty

    @Test
    public void emptyTest(){
        Collection<String> ac;
        startTest(CA, "isEmpty", "Tests the collection is empty at initialization",1.0);
        ac = getColeccionAbstracta();
        assertTrue(ac.isEmpty());
        addUp(1.0);
        passed();
    }

    //iterator

    @Test
    public void iteratorContainsTest(){
        Iterator<String> it;
        String aux;
        Collection<String> ac;
        LinkedList<String> l;
        startTest(CA, "iteratorContains", "Tests the elements retrieved by the iterator are equal to the ones inserted (not necessarily same order)",1.0);
        ac = getColeccionAbstracta();
        l = new LinkedList<>();
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            aux = rsgIt.next();
            l.add(aux);
            ac.add(aux);
        }
        it = ac.iterator();
        while(it.hasNext()){
            assertTrue(l.contains(it.next()));
        }
        addUp(1.0);
        passed();
    }

    @Test
    public void iteratorSizeTest(){
        int aux;
        Iterator<String> it;
        Collection<String> ac;
        startTest(CA, "iteratorSize", "Tests the number of elements retrieved by the iterator are equal to the ones inserted",1.0);
        ac = getColeccionAbstracta();
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            ac.add(rsgIt.next());
        }
        aux = 0;
        it = ac.iterator();
        while(it.hasNext()){
            it.next();
            aux++;
        }
        assertEquals(ac.size(), aux);
        addUp(1.0);
        passed();
    }

    @Test
    public void iteratorInitTest(){
        Iterator<String> it;
        Collection<String> ac;
        startTest(CA, "iteratorInit", "Tests the collection iterator has no elements initialization",1.0);
        ac = getColeccionAbstracta();
        it = ac.iterator();
        assertFalse(it.hasNext());
        addUp(1.0);
        passed();
    }

    //remove

    @Test
    public void removeContainsTest(){
        Collection<String> ac;
        if(allowRemove){
            startTest(CA, "removeContains", "Tests the collection do not contains any removed element",1.0);
            ac = getColeccionAbstracta();
            rsgIt = rsg.iterator();
            while(rsgIt.hasNext()){
                ac.add(rsgIt.next());
            }
            rsgIt = rsg.iterator();
            while(rsgIt.hasNext()){
                assertTrue(ac.remove(rsgIt.next()));
            }
            rsgIt = rsg.iterator();
            while(rsgIt.hasNext()){
                assertFalse(ac.contains(rsgIt.next()));
            }
            addUp(1.0);
            passed();
        }
    }

    @Test
    public void removeSizeTest(){
        int i;
        Collection<String> ac;
        if(allowRemove){
            startTest(CA, "removeSize", "Tests the collection size after remove and empty when all elements are removed",1.0);
            ac = getColeccionAbstracta();
            rsgIt = rsg.iterator();
            while(rsgIt.hasNext()){
                ac.add(rsgIt.next());
            }
            i = 1;
            rsgIt = rsg.iterator();
            while(rsgIt.hasNext()){
                assertTrue(ac.remove(rsgIt.next()));
                assertEquals(ac.size(), range - i++);
            }
            assertTrue(ac.isEmpty());
            addUp(1.0);
            passed();
        }
    }

    @Test
    public void removeEmptyTest(){
        Collection<String> ac;
        if(allowRemove){
            startTest(CA, "removeEmpty", "Tests the remove method returns false when empty",1.0);
            ac = getColeccionAbstracta();
            assertFalse(ac.remove(new Object()));
            addUp(1.0);
            passed();
        }
    }

    @Test(expected=UnsupportedOperationException.class)
    public void removeUnsupportedTest(){
        Collection<String> ac;
        if(!allowRemove){
            startTest(CA, "removeUnsupported", "Tests a UnsupportedOperationException is thrown if remove(Object) is called",1.0);
            ac = getColeccionAbstracta();
            try{
                ac.remove(null);
            }catch(UnsupportedOperationException e){
                addUp(1.0);
                passed();
                throw e;
            }
        }else{
            throw new UnsupportedOperationException();
        }
    }

    //removeAll

    @Test
    public void removeAllContainsTest(){
        String aux;
        Collection<String> ac;
        LinkedList<String> l;
        if(allowRemoveAll){
            startTest(CA, "removeAllContains", "Tests the collection do not contains any elements from original collection after removeAll",1.0);
            ac = getColeccionAbstracta();
            l = new LinkedList<>();
            rsgIt = rsg.iterator();
            while(rsgIt.hasNext()){
                aux = rsgIt.next();
                l.add(aux);
                ac.add(aux);
            }
            assertTrue(ac.removeAll(l));
            rsgIt = rsg.iterator();
            while(rsgIt.hasNext()){
                assertFalse(ac.contains(rsgIt.next()));
            }
            addUp(1.0);
            passed();
        }
    }

    @Test
    public void removeAllSizeTest(){
        String aux;
        Collection<String> ac;
        LinkedList<String> l;
        if(allowRemoveAll){
            startTest(CA, "removeAllSize", "Tests the collection is empty and zero size after removeAll from equal collection",1.0);
            ac = getColeccionAbstracta();
            l = new LinkedList<>();
            rsgIt = rsg.iterator();
            while(rsgIt.hasNext()){
                aux = rsgIt.next();
                l.add(aux);
                ac.add(aux);
            }
            assertTrue(ac.removeAll(l));
            assertTrue(ac.isEmpty());
            assertEquals(ac.size(), 0);
            addUp(1.0);
            passed();
        }
    }

    @Test(expected=NullPointerException.class)
    public void removeAllNullPointerTest(){
        Collection<String> ac;
        if(allowRemoveAll){
            startTest(CA, "removeAllNullPointer", "Tests a NullPointerException is thrown if the object parameter is null",1.0);
            ac = getColeccionAbstracta();
            try{
                ac.removeAll(null);
            }catch(NullPointerException e){
                addUp(1.0);
                passed();
                throw e;
            }
        }else{
            throw new NullPointerException();
        }
    }

    @Test
    public void removeAllItselfTest(){
        Collection<String> ac;
        if(allowRemoveAll){
            startTest(CA, "removeAllItself", "Tests the collection is empty and zero size after removeAll from itself",1.0);
            ac = getColeccionAbstracta();
            rsgIt = rsg.iterator();
            while(rsgIt.hasNext()){
                ac.add(rsgIt.next());
            }
            assertTrue(ac.removeAll(ac));
            assertTrue(ac.isEmpty());
            assertEquals(ac.size(), 0);
            addUp(1.0);
            passed();
        }
    }

    @Test
    public void removeAllEmptyTest(){
        Collection<String> ac1, ac2;
        if(allowRemoveAll){
            startTest(CA, "removeAllEmpty", "Tests the removeAll method returns false when empty",1.0);
            ac1 = getColeccionAbstracta();
            ac2 = getColeccionAbstracta();
            assertFalse(ac1.removeAll(ac2));
            addUp(1.0);
            passed();
        }
    }

    @Test(expected=UnsupportedOperationException.class)
    public void removeAllUnsupportedTest(){
        Collection<String> ac;
        if(!allowRemoveAll){
            startTest(CA, "removeAllUnsupported", "Tests a UnsupportedOperationException is thrown if removeAll(Collection<?>) is called",1.0);
            ac = getColeccionAbstracta();
            try{
                ac.removeAll(null);
            }catch(UnsupportedOperationException e){
                addUp(1.0);
                passed();
                throw e;
            }
        }else{
            throw new UnsupportedOperationException();
        }
    }

    //retainAll

    @Test
    public void retainAllContainsTest(){
        String aux;
        Collection<String> ac1, ac2;
        if(allowRetainAll){
            startTest(CA, "retainAllContains", "Tests the retain all method between two equivalent collections",1.0);
            ac1 = getColeccionAbstracta();
            ac2 = getColeccionAbstracta();
            rsgIt = rsg.iterator();
            while(rsgIt.hasNext()){
                aux = rsgIt.next();
                ac1.add(aux);
                ac2.add(aux);
            }
            assertFalse(ac1.retainAll(ac2));
            assertFalse(ac2.retainAll(ac1));
            assertFalse(ac1.isEmpty());
            assertEquals(ac1.size(), range);
            assertFalse(ac2.isEmpty());
            assertEquals(ac2.size(), range);
            addUp(1.0);
            passed();
        }
    }

    @Test
    public void retainAllDontContainsTest(){
        int i;
        String aux;
        Collection<String> ac1, ac2;
        if(allowRetainAll){
            startTest(CA, "retainAllDontContains", "Tests the retain all method between two different collections",1.0);
            ac1 = getColeccionAbstracta();
            ac2 = getColeccionAbstracta();
            rsgIt = rsg.iterator();
            i = 0;
            while(rsgIt.hasNext()){
                aux = rsgIt.next();
                if(i++ % 2 == 0){
                    ac1.add(aux);
                }else{
                    ac2.add(aux);
                }
            }
            assertTrue(ac1.retainAll(ac2));
            assertTrue(ac2.retainAll(ac1));
            assertTrue(ac1.isEmpty());
            assertEquals(ac1.size(), 0);
            assertTrue(ac2.isEmpty());
            assertEquals(ac2.size(), 0);
            addUp(1.0);
            passed();
        }
    }

    @Test(expected=NullPointerException.class)
    public void retainAllNullPointerTest(){
        Collection<String> ac;
        if(allowRetainAll){
            startTest(CA, "retainAllNullPointer", "Tests a NullPointerException is thrown if the object parameter is null",1.0);
            ac = getColeccionAbstracta();
            try{
                ac.retainAll(null);
            }catch(NullPointerException e){
                addUp(1.0);
                passed();
                throw e;
            }
        }else{
            throw new NullPointerException();
        }
    }

    @Test
    public void retainAllItselfTest(){
        Collection<String> ac;
        if(allowRetainAll){
            startTest(CA, "retainAllItself", "Tests the collection is non empty and same size after retainAll from itself",1.0);
            ac = getColeccionAbstracta();
            rsgIt = rsg.iterator();
            while(rsgIt.hasNext()){
                ac.add(rsgIt.next());
            }
            assertFalse(ac.retainAll(ac));
            assertFalse(ac.isEmpty());
            assertEquals(ac.size(), range);
            addUp(1.0);
            passed();
        }
    }

    @Test
    public void retainAllEmptyTest(){
        Collection<String> ac1, ac2;
        if(allowRetainAll){
            startTest(CA, "retainEmptyAll", "Tests the collection is empty and zero size after retainAll from empty collection",1.0);
            ac1 = getColeccionAbstracta();
            ac2 = getColeccionAbstracta();
            rsgIt = rsg.iterator();
            while(rsgIt.hasNext()){
                ac1.add(rsgIt.next());
            }
            assertTrue(ac1.retainAll(ac2));
            assertTrue(ac1.isEmpty());
            assertEquals(ac1.size(), 0);
            addUp(1.0);
            passed();
        }
    }

    @Test(expected=UnsupportedOperationException.class)
    public void retainAllUnsupportedTest(){
        Collection<String> ac;
        if(!allowRetainAll){
            startTest(CA, "retainAllUnsupported", "Tests a UnsupportedOperationException is thrown if retainAll(Collection<?>) is called",1.0);
            ac = getColeccionAbstracta();
            try{
                ac.retainAll(null);
            }catch(UnsupportedOperationException e){
                addUp(1.0);
                passed();
                throw e;
            }
        }else{
            throw new UnsupportedOperationException();
        }
    }

    //size

    @Test
    public void zeroSizeTest(){
        Collection<String> ac;
        startTest(CA, "initSize", "Tests the collection has zero size at initialization",1.0);
        ac = getColeccionAbstracta();
        assertEquals(ac.size(), 0);
        addUp(1.0);
        passed();
    }

    //toArray: Object[]

    @Test
    public void toObjectArrayContainsTest(){
        int i;
        Iterator<String> it;
        Object arr[];
        Collection<String> ac;
        startTest(CA, "objectArrayContains", "Tests the collection elements in the array are have same order as iterator",1.0);
        ac = getColeccionAbstracta();
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            ac.add(rsgIt.next());
        }
        arr = ac.toArray();
        it = ac.iterator();
        i = 0;
        while(it.hasNext()){
            if(arr[i] == null){
                assertNull(it.next());
            }else{
                assertTrue(arr[i].equals(it.next()));
            }
            i++;
        }
        addUp(1.0);
        passed();
    }

    @Test
    public void toObjectArraySizeTest(){
        int i;
        Iterator<String> it;
        Object arr[];
        Collection<String> ac;
        startTest(CA, "objectArraySize", "Tests the number of elements in the array are the same as iterator",1.0);
        ac = getColeccionAbstracta();
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            ac.add(rsgIt.next());
        }
        arr = ac.toArray();
        it = ac.iterator();
        i = 0;
        while(it.hasNext()){
            it.next();
            i++;
        }
        assertEquals(arr.length, i);
        addUp(1.0);
        passed();
    }

    @Test
    public void toObjectArrayEmptyTest(){
        Iterator<String> it;
        Object arr[];
        Collection<String> ac;
        startTest(CA, "objectArrayEmpty", "Tests the number of elements is zero in the array when empty",1.0);
        ac = getColeccionAbstracta();
        arr = ac.toArray();
        assertEquals(arr.length, 0);
        addUp(1.0);
        passed();
    }

    //toArray: E[]

    @Test
    public void toGenericArrayContainsTest(){
        int i;
        Iterator<String> it;
        String arr[];
        Collection<String> ac;
        startTest(CA, "genericArrayContains", "Tests the elements in the array are have same order as iterator",1.0);
        ac = getColeccionAbstracta();
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            ac.add(rsgIt.next());
        }
        arr = new String[range];
        arr = ac.toArray(arr);
        it = ac.iterator();
        i = 0;
        while(it.hasNext()){
            if(arr[i] == null){
                assertNull(it.next());
            }else{
                assertTrue(arr[i].equals(it.next()));
            }
            i++;
        }
        addUp(1.0);
        passed();
    }

    @Test
    public void toGenericArrayZeroSizeTest(){
        String arr[];
        Collection<String> ac;
        startTest(CA, "genericArrayZeroSize", "Tests the number of elements in the array are the same as collection when parameter size is lower",1.0);
        ac = getColeccionAbstracta();
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            ac.add(rsgIt.next());
        }
        arr = new String[0];
        arr = ac.toArray(arr);
        assertEquals(arr.length, range);
        addUp(1.0);
        passed();
    }

    @Test
    public void toGenericArrayNullFillTest(){
        String arr[];
        Collection<String> ac;
        startTest(CA, "genericArrayNullFill", "Tests the elements in the array with index higher than collection size are null",1.0);
        ac = getColeccionAbstracta();
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            ac.add(rsgIt.next());
        }
        arr = new String[range*2];
        arr = ac.toArray(arr);
        for(int i = range; i < range*2; i++){
            assertNull(arr[i]);
        }
        addUp(1.0);
        passed();
    }

    @Test
    public void toGenericArraySizeTest(){
        int i;
        Iterator<String> it;
        String arr[];
        Collection<String> ac;
        startTest(CA, "genericArraySize", "Tests the collection number of elements in the array are the same as iterator",1.0);
        ac = getColeccionAbstracta();
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            ac.add(rsgIt.next());
        }
        arr = new String[ac.size()];
        arr = ac.toArray(arr);
        it = ac.iterator();
        i = 0;
        while(it.hasNext()){
            it.next();
            i++;
        }
        assertEquals(arr.length, i);
        addUp(1.0);
        passed();
    }

    @Test(expected=NullPointerException.class)
    public void toGenericArrayNullPTest(){
        String arr[];
        Collection<String> ac;
        startTest(CA, "genericArray", "Tests a NullPointerException is thrown if the array parameter is null",1.0);
        ac = getColeccionAbstracta();
        try{
            arr = ac.toArray(null);
        }catch(NullPointerException e){
            addUp(1.0);
            passed();
            throw e;
        }
    }

    @Test
    public void toGenericArrayEmptyTest(){
        Iterator<String> it;
        String arr[];
        Collection<String> ac;
        startTest(CA, "genericArrayEmpty", "Tests the collection number of elements is zero in the array when empty",1.0);
        ac = getColeccionAbstracta();
        arr = new String[range];
        arr = ac.toArray(arr);
        assertEquals(arr.length, range);
        for(int i = 0; i < arr.length; i++){
            assertNull(arr[i]);
        }
        addUp(1.0);
        passed();
    }

}
