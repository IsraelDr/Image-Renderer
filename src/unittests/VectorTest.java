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
        Vector a = new Vector(1,2,3);
        Vector b = new Vector(2,3,4);
        Vector d = new Vector(-1,-2,-3);
        Vector c = new Vector(0,0,0);
        assertTrue("Is not equal ",a.add(b).equals(new Vector(3, 5, 7)));
        assertTrue("Is not equal ",a.add(c).equals(new Vector(1, 2, 3)));
        assertTrue("Is not equal ",a.add(d).equals(c));
    }

    @Test
    public void multipliedbyScalar() {
        Vector a = new Vector(1,2,3);
        Vector b = new Vector(2,3,4);
        Vector d = new Vector(-1,-2,-3);
        Vector c = new Vector(0,0,0);
        assertTrue("Is not equal",a.multipliedbyScalar(-1).equals(d));
        assertFalse("Is not equal ",c.multipliedbyScalar(3).equals(new Vector(0, 0, 1)));
        assertTrue("Is not equal ",b.multipliedbyScalar(2).equals(new Vector(4,6,8)));
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
        Vector a = new Vector(1,2,3);
        Vector b = new Vector(2,3,4);
        Vector d = new Vector(-1,-2,-3);
        Vector c = new Vector(0,0,0);
        Vector g = new Vector(1,2.45,3.098);
        assertTrue("Is not equal ",a.vectorProduct(b).equals(new Vector(-1,2,-1)));
        assertTrue("Is not equal ",c.vectorProduct(d).equals(c));
        assertTrue("Is not equal ",d.vectorProduct(a).equals(c));
        assertTrue("Is not equal ",b.vectorProduct(g).equals(new Vector(-0.506, -2.196, 1.9)));
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
        Vector b = new Vector(2,3,4);
        Vector d = new Vector(-1,-2,-3);
        Vector c = new Vector(0,0,0);
        Vector g = new Vector(1,2.45,3.098);
        assertTrue("Is not equal1 ",b.NormalVector().size()==1.0);
        assertTrue("Is not equal2 ",d.NormalVector().size()==1.0);
        assertTrue("Is not equal3 ",c.NormalVector().size()==0.0);
        assertTrue("Is not equal 4",g.NormalVector().size()==0.9999999999999999);
    }
}