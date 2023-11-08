/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.estructuras.lineales;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author blackzafiro
 */
public class ArregloTest {

    private static float points;
    private static float totalPoints;
    private float testValue;

    private static Arreglo a;
    private static Arreglo b;

    public ArregloTest() {
        a = new Arreglo(new int [] {4,5});
        b = new Arreglo(new int [] {3,5,2});
    }

    @BeforeClass
    public static void setUpClass() {
        totalPoints = points = 0;
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("==============================");
    	System.out.println("Calificacion automatica: " + points * 10 /totalPoints);
        System.out.println("==============================");
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of obtenerElemento method, of class Arreglo.
     *//*
    @Test
    public void testObtenerElemento() {
        System.out.println("obtenerElemento");
        int[] indices = null;
        Arreglo instance = null;
        int expResult = 0;
        int result = instance.obtenerElemento(indices);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */

    /**
     * Test of almacenarElemento method, of class Arreglo.
     *//*
    @Test
    public void testAlmacenarElemento() {
        System.out.println("almacenarElemento");
        int[] indices = null;
        int elem = 0;
        Arreglo instance = null;
        instance.almacenarElemento(indices, elem);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */

    /**
     * Test of obtenerIndice method, of class Arreglo.
     */
    /*
    @Test
    public void testObtenerIndice() {
        System.out.println("obtenerIndice");
        int[] indices = null;
        Arreglo instance = null;
        int expResult = 0;
        int result = instance.obtenerIndice(indices);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */

    @Test
    public void testBidimensional() {
        System.out.println("2-dimensiones");
        testValue = 4.0f;
        totalPoints += testValue;

        int count = 1;
        for(int i=0;i<4;i++){
            for (int j=0;j<5;j++){
                a.almacenarElemento(new int [] {i,j},count);
                count++;
            }
        }
        int [] indices = {3,2};
        int result = a.obtenerElemento(indices);
        assertEquals(result,18);

        points += testValue;
    }

    @Test
    public void testTridimensional(){
        System.out.println("3-dimensiones");
        testValue = 4.0f;
        totalPoints += testValue;

        int count = 1;
        for(int i=0;i<3;i++){
            for (int j=0;j<5;j++) {
                for (int k=0;k<2;k++) {
                    b.almacenarElemento(new int [] {i,j,k},count);
                    count++;
                }
            }
        }
        int [] indices = {2,3,0}; //indices [2][3][0]
        int result = b.obtenerElemento(indices);
        assertEquals(result,27);

        points += testValue;
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testThrow1(){
        System.out.println("Obtener índice en almacenarElemento - excepción");
        testValue = 1.0f;
        totalPoints += testValue;
        try{
            b.almacenarElemento(new int [] {0,10,2},100);
        }catch(IndexOutOfBoundsException e){
            points += testValue;
            throw e;
        }

    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testThrow2(){
        System.out.println("Obtener índice en obtenerElemento - excepción");
        testValue = 1.0f;
        totalPoints += testValue;
        try{
            b.obtenerElemento(new int [] {0,10,2});
        }catch(IndexOutOfBoundsException e){
            points += testValue;
            throw e;
        }
    }

}
