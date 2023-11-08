/*
 * Código utilizado para el curso de Estructuras de Datos.
 * Se permite consultarlo para fines didácticos en forma personal.
 */
package estructuras.lineales;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Random;

/**
 * Batería de pruebas unitarias para la clase <code>Vector</code>.
 * @author veronica
 */
public class VectorTest {
    
    private Random intGenerator;
    private static float mark;
    
    public VectorTest() {
        intGenerator = new Random();
    }
    
    @BeforeClass
    public static void setUpClass() {
        mark = 0;
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("==============================");
    	System.out.println("Calificación automática: " + mark);
        System.out.println("==============================");
    }

    /**
     * Test of lee method, of class Vector.
     */
    @Test
    public void testLee() {
        System.out.println("lee de vector vacío");
        int i = intGenerator.nextInt(Vector.INC);
        Vector<Object> instance = new Vector<Object>();
        Object expResult = null;
        Object result = instance.lee(i);
        assertEquals(expResult, result);
        mark += 1;
    }

    /**
     * Test of leeCapacidad method, of class Vector.
     */
    @Test
    public void testLeeCapacidad() {
        System.out.println("leeCapacidad");
        Vector instance = new Vector();
        int expResult = Vector.INC;
        int result = instance.leeCapacidad();
        assertEquals(expResult, result);
        mark += 1;
    }
    
    /**
     * Test of asignaCapacidad y leeCapacidad method, of class Vector.
     */
    @Test
    public void testAsignaLeeCapacidad1() {
        System.out.println("leeCapacidad tras redimensionar");
        Vector instance = new Vector();
        int expResult = intGenerator.nextInt(1000) * Vector.INC;
        instance.asignaCapacidad(expResult);
        int result = instance.leeCapacidad();
        assertEquals(expResult, result);
        mark += 0.7;
    }
    
    /**
     * Test of asignaCapacidad y leeCapacidad method, of class Vector.
     */
    @Test(expected=IllegalSizeException.class)
    public void testAsignaCapacidad() {
        System.out.println("Asigna capacidad - valor inválido");
        Vector instance = new Vector();
        int index = 0;
        try {
            instance.asignaCapacidad(index);
        } catch (IllegalSizeException e) {
            mark += 0.3;
            throw e;
        }
    }

    /**
     * Test of asigna y lee method, of class Vector.
     */
    @Test
    public void testAsignaLee() {
        System.out.println("asigna y lee - dentro de INC");
        int i = intGenerator.nextInt(Vector.INC);
        String e = "Palabra";
        Vector<String> instance = new Vector<String>();
        instance.asigna(i, e);
        assertEquals(instance.lee(i), e);
        mark += 1;
    }

    /**
     * Test of aseguraCapacidad y leeCapacidad method, of class Vector.
     */
    @Test
    public void testAseguraLeeCapacidad() {
        System.out.println("aseguraLeeCapacidad - sin cambios");
        int n = intGenerator.nextInt(Vector.INC);
        Vector instance = new Vector();
        instance.aseguraCapacidad(n);
        assertEquals(Vector.INC, instance.leeCapacidad());
        mark += 2;
    }
    
    /**
     * Test of aseguraCapacidad y leeCapacidad method, of class Vector.
     */
    @Test
    public void testAseguraLeeCapacidad1() {
        System.out.println("aseguraLeeCapacidad - con incremento");
        int n = Vector.INC + 1 + intGenerator.nextInt(10000);
        Vector instance = new Vector();
        instance.aseguraCapacidad(n);
        assertTrue(n < instance.leeCapacidad());
        mark += 2;
    }
    
    /**
     * Test of aseguraCapacidad y leeCapacidad method, of class Vector.
     */
    @Test
    public void testAseguraLeeCapacidad2() {
        System.out.println("aseguraLeeCapacidad - con incremento, caso frontera");
        int n = Vector.INC + 1;
        Vector instance = new Vector();
        instance.aseguraCapacidad(n);
        assertTrue(n < instance.leeCapacidad());
        mark += 2;
    }
    
}
