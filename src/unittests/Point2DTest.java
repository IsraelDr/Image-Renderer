package unittests;

import org.junit.Test;
import primitives.Point2D;

import static org.junit.Assert.*;

public class Point2DTest {

    @Test
    public void get_x() {
    }

    @Test
    public void get_y() {
    }

    @Test
    public void equals() {
        Point2D a = new Point2D(1,2);
        Point2D b = new Point2D(3,4);
        Point2D c = new Point2D(3,4);
        assertFalse( c.equals(b));
        assertFalse( a.equals(b));



    }


}