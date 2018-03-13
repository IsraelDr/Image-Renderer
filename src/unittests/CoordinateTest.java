package unittests;

import org.junit.Test;
import primitives.Coordinate;

import static org.junit.Assert.*;

public class CoordinateTest {


    @Test
    public void equals() {
        Coordinate a = new Coordinate(1.2);
        Coordinate b = new Coordinate(2);
        Coordinate c = new Coordinate(0);
        Coordinate d = new Coordinate(1.2);
        Coordinate e = new Coordinate(2.2000000001);
        Coordinate f = new Coordinate(1.20002);
        Coordinate ex1 = new Coordinate(1.1999990);
        Coordinate ex2 = new Coordinate(1.1999991);
        Coordinate ex3 = new Coordinate(1000000000);
        Coordinate ex4 = new Coordinate(1000000000.00001);
        assertFalse(ex3.equals(ex4));
        assertFalse(ex4.equals(ex3));
        assertTrue(ex1.equals(ex2));
        assertTrue(a.equals(d));
        assertFalse(c.equals(b));
        assertFalse(b.equals(c));
        assertTrue(d.equals(a));
        assertFalse(e.equals(f));
        assertFalse(b.equals(e));
        assertFalse(d.equals(f));


    }

    @Test
    public void add() {
    }

    @Test
    public void substract() {
    }
}