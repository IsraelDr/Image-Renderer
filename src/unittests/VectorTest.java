package unittests;

import org.junit.Test;
import primitives.Vector;

import static org.junit.Assert.*;

public class VectorTest {


    @Test
    public void equals() {
        Vector EX1 = new Vector(1,2,3);
        Vector EX2 = new Vector(1,2,3);
        Vector EX3 = new Vector(1,2,4);
        Vector EX4 = new Vector(1,2,4.0000001);
        Vector EX11 = new Vector(1.00000004,2.00000001,4);
        Vector EX12 = new Vector(1,2,4.0000001);
        assertTrue(EX1.equals(EX2));
        assertFalse(EX1.equals(EX3));
        assertTrue(EX3.equals(EX4));
        assertTrue(EX11.equals(EX12));
    }

    @Test
    public void add() {
    }

    @Test
    public void multipliedbyScalar() {
    }

    @Test
    public void scalarProduct() {
        Vector EX1 = new Vector(1,2,3);
        Vector EX2 = new Vector(1,2,3);
        Vector EX3 = new Vector(1,2,4);
        Vector EX4 = new Vector(1,2,4.0000001);
        Vector EX11 = new Vector(1.00000004,2.00000001,4);
        Vector EX12 = new Vector(1,2,4.0000001);
        assertTrue(EX11.ScalarProduct(EX12)==21.000000460000003);
        assertTrue(EX1.ScalarProduct(EX2)==14);
        assertFalse(EX3.ScalarProduct(EX4)==0);
    }

    @Test
    public void vectorProduct() {
    }

    @Test
    public void size() {
        Vector EX1 = new Vector(1,2,3);
        Vector EX2 = new Vector(1,2,3);
        Vector EX3 = new Vector(1,2,4);
        Vector EX0 = new Vector(0,0,0);

        Vector EX4 = new Vector(1,2,4.0000001);
        Vector EX11 = new Vector(1.00000004,2.00000001,4);
        Vector EX12 = new Vector(1,2,4.0000001);
        assertTrue(EX1.size()==Math.sqrt(14));
        assertTrue(EX11.size()==4.582575708048914);
        assertTrue(EX0.size()==0);
        assertFalse(EX3.size()==1);

    }

    @Test
    public void normalVector() {

    }
}