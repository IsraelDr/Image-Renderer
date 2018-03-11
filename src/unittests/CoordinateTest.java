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
        Coordinate e = new Coordinate(1.2000000001);
        Coordinate f = new Coordinate(1.2000000002);
        Coordinate g = new Coordinate(1.1999990);
        //assertTrue(a.equals(d));
        //assertFalse(c.equals(b));
        //assertFalse(b.equals(c));
        //assertTrue(d.equals(a));
        //assertTrue(e.equals(f));
        //assertFalse(e.equals(g));

    }

    @Test
    public void add() {
    }

    @Test
    public void substract() {
    }
}