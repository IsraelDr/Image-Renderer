package unittests;

import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;

import static org.junit.Assert.*;

public class TriangleTest {

    @Test
    public void equals() {
        Triangle a = new Triangle(new Point3D(1,2,-3),new Point3D(5,9,0),new Point3D(23,-5,4));
        Triangle b = new Triangle(new Point3D(1,2,-3),new Point3D(23,-5,4),new Point3D(5,9,0));
        Triangle c = new Triangle(new Point3D(23,-5,4),new Point3D(5,9,0),new Point3D(1,2,-3));
        Triangle d = new Triangle(new Point3D(1,2,-3),new Point3D(5,9,0),new Point3D(23,-5,4));
        Triangle e = new Triangle(new Point3D(1,2,-3),new Point3D(5,9,0),new Point3D(23,-5,4));
        Triangle f = new Triangle(new Point3D(5,9,0),new Point3D(1,2,-3),new Point3D(23,-5,4));
        assertTrue(a.equals(b));
        assertTrue(a.equals(c));
        assertTrue(a.equals(d));
        assertTrue(a.equals(f));
        assertTrue(a.equals(e));
    }
}