
package ed.estructuras.lineales;

import java.util.NoSuchElementException;
import java.util.ListIterator;
import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ed.estructuras.*;

/**
 * 
 * @author mindahrelfen
 */
public abstract class ListaTestA extends ColeccionAbstractaTestA{

    protected static Random rdm = new Random();

    public static final String LI = "List";

    protected abstract List<String> getLista();
    
    //add

    @Test
    public void list_addContainsTest(){
        int index;
        List<String> li;
        startTest(LI, "addContains", "Tests the list contains elements added elements",1.0);
        li = getLista();
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            index = rdm.nextInt(li.size() + 1);
            li.add(index,rsgIt.next());
        }
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            assertTrue(li.contains(rsgIt.next()));
        }
        addUp(1.0);
        passed();
    }

    @Test
    public void list_addSizeTest(){
        int i;
        List<String> li;
        startTest(LI, "addSize", "Tests the list has correct size and is not empty after add",1.0);
        li = getLista();
        rsgIt = rsg.iterator();
        i = 1;
        while(rsgIt.hasNext()){
            assertTrue(li.add(rsgIt.next()));
            assertEquals(li.size(), i++);
            assertFalse(li.isEmpty());
        }
        addUp(1.0);
        passed();
    }

    @Test
    public void list_addOrderTest(){
        String s,s1,s2,aux=null;
        List<String> li;
        LinkedList<String> l1;
        Iterator<String> it1,it2;
        startTest(LI, "addOrder", "Tests the list contains added elements in order after add",1.0);
        li = getLista();
        l1 = new LinkedList<>();
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            s = rsgIt.next();
            l1.add(s);
            li.add(s);
        }
        rsgIt = rsg.iterator();
        if(rsgIt.hasNext()){
            aux = rsgIt.next();
            l1.add(aux);
        }
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            s = rsgIt.next();
            l1.add(s);
            li.add(s);
        }
        li.add(range,aux);
        it1 = li.iterator();
        it2 = l1.iterator();
        while(it1.hasNext() && it2.hasNext()){
            s1 = it1.next();
            s2 = it2.next();
            assertEquals(s1,s2);
        }
        assertFalse(it1.hasNext() || it2.hasNext());
        addUp(1.0);
        passed();
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void list_addIndexOutOfBoundsTest(){
        List<String> li;
        startTest(LI, "addIndexOutOfBounds", "Tests a IndexOutOfBoundsException is thrown if the parameter is outside boundaries",1.0);
        li = getLista();
        try{
            li.add(-1,new String());
        }catch(IndexOutOfBoundsException e){
            addUp(1.0);
            passed();
            throw e;
        }
    }

    //addAll

    @Test
    public void list_addAllContainsTest(){
        List<String> li;
        LinkedList<String> l;
        startTest(LI, "addAllContains", "Tests the list contains all added elements after addAll",1.0);
        li = getLista();
        l = new LinkedList<>();
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            l.add(rsgIt.next());
        }
        assertTrue(li.addAll(0,l));
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            assertTrue(li.contains(rsgIt.next()));
        }
        addUp(1.0);
        passed();
    }

    @Test
    public void list_addAllSizeTest(){
        List<String> li;
        LinkedList<String> l;
        startTest(LI, "addAllSize", "Tests the list has correct size and is not empty after addAll",1.0);
        li = getLista();
        l = new LinkedList<>();
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            l.add(rsgIt.next());
        }
        assertTrue(li.addAll(0,l));
        assertEquals(li.size(), range);
        assertFalse(li.isEmpty());
        addUp(1.0);
        passed();
    }

    @Test(expected=NullPointerException.class)
    public void list_addAllNullPointerTest(){
        List<String> li;
        startTest(LI, "addAllNullPointer", "Tests a NullPointerException is thrown if the parameter is null",1.0);
        li = getLista();
        try{
            li.addAll(0,null);
        }catch(NullPointerException e){
            addUp(1.0);
            passed();
            throw e;
        }
    }

    @Test(expected=IllegalArgumentException.class)
    public void list_addAllIllegalArgumentTest(){
        List<String> li;
        startTest(LI, "addAllIllegalArgument", "Tests an IllegalArgumentException is thrown if the parameter is itself",1.0);
        li = getLista();
        try{
            li.addAll(0,li);
        }catch(IllegalArgumentException e){
            addUp(1.0);
            passed();
            throw e;
        }
    }

    @Test
    public void list_addAllOrderTest(){
        String s,s1,s2;
        List<String> li;
        LinkedList<String> l1,l2;
        Iterator<String> it1,it2;
        startTest(LI, "addAllOrder", "Tests the list contains all added elements in order after addAll",1.0);
        li = getLista();
        l1 = new LinkedList<>();
        l2 = new LinkedList<>();
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            s = rsgIt.next();
            l1.add(s);
            li.add(s);
        }
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            s = rsgIt.next();
            l1.add(s);
            l2.add(s);
        }
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            s = rsgIt.next();
            l1.add(s);
            li.add(s);
        }
        assertTrue(li.addAll(range,l2));
        it1 = li.iterator();
        it2 = l1.iterator();
        while(it1.hasNext() && it2.hasNext()){
            s1 = it1.next();
            s2 = it2.next();
            assertEquals(s1,s2);
        }
        assertFalse(it1.hasNext() || it2.hasNext());
        addUp(1.0);
        passed();
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void list_addAllIndexOutOfBoundsTest(){
        List<String> li,l;
        startTest(LI, "addAllIndexOutOfBounds", "Tests a IndexOutOfBoundsException is thrown if the parameter is outside boundaries",1.0);
        li = getLista();
        l = getLista();
        try{
            li.addAll(-1,l);
        }catch(IndexOutOfBoundsException e){
            addUp(1.0);
            passed();
            throw e;
        }
    }

    //get

    @Test
    public void getContainsTest(){
        int i,index;
        String s;
        Iterator<String> it;
        List<String> li;
        startTest(LI, "getContains", "Tests the get method returns the correct element",1.0);
        li = getLista();
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            li.add(rsgIt.next());
        }
        it = li.iterator();
        index = rdm.nextInt(range);
        i = 0;
        while(i < index){
            it.next();
            i++;
        }
        assertTrue(it.hasNext());
        s = it.next();
        assertEquals(s,li.get(index));
        addUp(1.0);
        passed();
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void getIndexOutOfBoundsTest(){
        List<String> li;
        startTest(LI, "getIndexOutOfBounds", "Tests a IndexOutOfBoundsException is thrown if the parameter is outside boundaries",1.0);
        li = getLista();
        try{
            li.get(-1);
        }catch(IndexOutOfBoundsException e){
            addUp(0.5);
        }
        try{
            li.get(li.size());
        }catch(IndexOutOfBoundsException e){
            addUp(0.5);
            passed();
            throw e;
        }
    }

    //indexOf

    @Test
    public void indexOfContainsTest(){
        int index;
        String s;
        ListIterator<String> it;
        List<String> li;
        startTest(LI, "indexOfContains", "Tests the indexOf method returns the correct index",1.0);
        li = getLista();
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            li.add(rsgIt.next());
        }
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            li.add(rsgIt.next());
        }
        index = 0;
        it = li.listIterator(index);
        rsgIt = rsg.iterator();
        s = rsgIt.next();
        if(s == null){
            while(it.hasNext()){
                if(it.next() == null) break;
                index++;
            }
        }else{
            while(it.hasNext()){
                if(s.equals(it.next())) break;
                index++;
            }
        }
        assertEquals(index,li.indexOf(s));
        addUp(1.0);
        passed();
    }

    @Test
    public void indexOfNoElementTest(){
        int i;
        List<String> li;
        startTest(LI, "indexOfNoElement", "Tests indexof returns -1 if the element is not in the list",1.0);
        li = getLista();
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            li.add(rsgIt.next());
        }
        i = li.indexOf(new String());
        assertEquals(-1,i);
        addUp(1.0);
        passed();
    }

    @Test
    public void indexOfEmptyTest(){
        int i;
        List<String> li;
        startTest(LI, "indexOfEmpty", "Tests indexof returns -1 if the list is empty",1.0);
        li = getLista();
        i = li.indexOf(new String());
        assertEquals(-1,i);
        addUp(1.0);
        passed();
    }

    //lasTIndexOf

    @Test
    public void lastIndexOfContainsTest(){
        int index;
        String s;
        ListIterator<String> it;
        List<String> li;
        startTest(LI, "lastIndexOfContains", "Tests the lastIndexOf method returns the correct index",1.0);
        li = getLista();
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            li.add(rsgIt.next());
        }
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            li.add(rsgIt.next());
        }
        index = li.size();
        it = li.listIterator(index--);
        rsgIt = rsg.iterator();
        s = rsgIt.next();
        if(s == null){
            while(it.hasPrevious()){
                if(it.previous() == null) break;
                index--;
            }
        }else{
            while(it.hasPrevious()){
                if(s.equals(it.previous())) break;
                index--;
            }
        }
        assertEquals(index,li.lastIndexOf(s));
        addUp(1.0);
        passed();
    }

    @Test
    public void lastIndexOfEmptyTest(){
        int i;
        List<String> li;
        startTest(LI, "lastIndexOfEmpty", "Tests indexof returns -1 if the list is empty",1.0);
        li = getLista();
        i = li.lastIndexOf(new String());
        assertEquals(-1,i);
        addUp(1.0);
        passed();
    }

    //listIterator

    //listIterator(int)

    @Test(expected=IndexOutOfBoundsException.class)
    public void listIteratorIndexOutOfBoundsTest(){
        List<String> li;
        startTest(LI, "listIteratorIndexOutOfBounds", "Tests a IndexOutOfBoundsException is thrown if the parameter is outside boundaries",1.0);
        li = getLista();
        try{
            li.listIterator(-1);
        }catch(IndexOutOfBoundsException e){
            addUp(1.0);
            passed();
            throw e;
        }
    }

    //remove(int)

    @Test(expected=IndexOutOfBoundsException.class)
    public void removeIndexOutOfBoundsTest(){
        List<String> li;
        startTest(LI, "removeIndexOutOfBounds", "Tests a IndexOutOfBoundsException is thrown if the parameter is outside boundaries",1.0);
        li = getLista();
        try{
            li.remove(-1);
        }catch(IndexOutOfBoundsException e){
            addUp(1.0);
            passed();
            throw e;
        }
    }

    @Test
    public void list_removeContainsTest(){
        List<String> li;
        if(allowRemove){
            startTest(LI, "removeContains", "Tests the List do not contains any removed element",1.0);
            li = getLista();
            rsgIt = rsg.iterator();
            while(rsgIt.hasNext()){
                li.add(rsgIt.next());
            }
            rsgIt = rsg.iterator();
            while(!li.isEmpty()){
                li.remove(rdm.nextInt(li.size()));
            }
            rsgIt = rsg.iterator();
            while(rsgIt.hasNext()){
                assertFalse(li.contains(rsgIt.next()));
            }
            addUp(1.0);
            passed();
        }
    }

    @Test
    public void list_removeSizeTest(){
        int i;
        List<String> li;
        if(allowRemove){
            startTest(LI, "removeSize", "Tests the List size after remove and empty when all elements are removed",1.0);
            li = getLista();
            rsgIt = rsg.iterator();
            while(rsgIt.hasNext()){
                li.add(rsgIt.next());
            }
            i = 1;
            rsgIt = rsg.iterator();
            while(!li.isEmpty()){
                li.remove(rdm.nextInt(li.size()));
                assertEquals(li.size(), range - i++);
            }
            assertTrue(li.isEmpty());
            addUp(1.0);
            passed();
        }
    }

    //set

    @Test(expected=IndexOutOfBoundsException.class)
    public void setIndexOutOfBoundsTest(){
        List<String> li;
        startTest(LI, "setIndexOutOfBounds", "Tests a IndexOutOfBoundsException is thrown if the parameter is outside boundaries",1.0);
        li = getLista();
        try{
            li.set(-1,null);
        }catch(IndexOutOfBoundsException e){
            addUp(1.0);
            passed();
            throw e;
        }
    }
    
    @Test
    public void list_setContainsTest(){
        int index;
        String s;
        List<String> li;
        startTest(LI, "setContains", "Tests the list contains elements set elements",1.0);
        li = getLista();
        rsgIt = rsg.iterator();
        while(rsgIt.hasNext()){
            index = rdm.nextInt(li.size() + 1);
            li.add(index,rsgIt.next());
        }
        index = rdm.nextInt(li.size());
        li.set(index,new String());
        s = li.get(index);
        assertEquals(s,new String());
        addUp(1.0);
        passed();
    }

}
