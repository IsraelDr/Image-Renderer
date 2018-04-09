package unittests;

import geometries.Sphere;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.*;

public class SphereTest {

    @org.junit.Test
    public void equals() {
        Point3D pnt = new Point3D (1,2,3);
        Point3D pnt1 = new Point3D (1,2,0);
        double rad = 1;
        Sphere a = new Sphere(pnt,rad);
        Sphere b = new Sphere(pnt1,rad);
        assertFalse(a.equals(b));


    }

    @org.junit.Test
    public void getNormal() {
        Point3D pnt = new Point3D (1,2,3);
        Point3D pnt1 = new Point3D (9,8,3);
        Vector d = new Vector(0.8,0.6,0);
        double rad = 10;
        Sphere a = new Sphere(pnt,rad);
        assertTrue(a.getNormal(pnt1).equals(d));

    }
}