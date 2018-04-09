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
        Plane c =new Plane(new Point3D(1,-88,-43),new Vector(-31,17,-43));
        Plane d = new Plane(new Point3D(2,15,-3),new Point3D(4,6,-8),new Point3D(1,-2,-9));
        assertTrue(c.equals(d));
    }

    @Test
    public void getNormal() {
        Plane c =new Plane(new Point3D(1,-88,-43),new Vector(-31,17,-43));
        Plane d = new Plane(new Point3D(2,15,-3),new Point3D(4,6,-8),new Point3D(1,-2,-9));
        assertTrue(d.getNormal(new Point3D(4,6,-8)).equals(c.getNormal(new Point3D(1,-88,-43))));
    }
}