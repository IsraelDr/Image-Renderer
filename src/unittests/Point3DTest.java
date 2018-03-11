package unittests;

import org.junit.Test;
import primitives.Point3D;

import static org.junit.Assert.*;

public class Point3DTest {

    @Test
    public void equals() {
        Point3D a = new Point3D(1,0,0);
        Point3D b = new Point3D(0,1,0);
        Point3D c = new Point3D(0,0,1);
        Point3D d = new Point3D(1,0,0);
        assertFalse(a.equals(b));
        assertFalse(b.equals(c));
        assertTrue(a.equals(d));
    }

    @Test
    public void distance() {
        Point3D a = new Point3D(1,2,3);
        Point3D b = new Point3D(2,4,6);
        Point3D c = new Point3D(0,0,1);
        Point3D d = new Point3D(0,0,0);
        Point3D e = new Point3D(-1.000987,2.9876450,Math.sqrt(7));
        Point3D f = new Point3D(6.0093625,0.5789,-2.90908);
        assertTrue(a.distance(b)== Math.sqrt(14));//sqrt(14)
        assertTrue(c.distance(d)==1);
        assertFalse(c.distance(d)==1.01); //==1
        assertTrue(d.distance(d)==0);
        assertFalse(d.distance(d)==0.0001);//==0
        assertTrue(e.distance(f)==9.263001861252043);
    }

    @Test
    public void addVectorToPiont() {
    }

    @Test
    public void vectorSubstract() {
    }
}