package unittests;

import geometries.Plane;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.*;

public class PlaneTest {

    @Test
    public void equals() {
        Plane a =new Plane(new Point3D(1,2,3),new Vector(2,3,4));
        Plane b = new Plane(new Point3D(1,2,3),new Vector(4,6,8));
        assertTrue(a.equals(b));
    }

    @Test
    public void getNormal() {
    }
}