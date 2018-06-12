package unittests;

import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

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
    public void addVectorToPoint() {
        Point3D e = new Point3D(-1.000987,2.9876450,Math.sqrt(7));
        Point3D f = new Point3D(6.0093625,0.5789,-2.90908);
        Vector vector1 = new Vector(1.9,-0.6,9.8);
        Vector vector2 = new Vector(11.09,-0.654,2.00008);
        Vector vector1_2 = new Vector(0.8990129999999998, 2.387645, 12.44575131106459);//The result from adding Vector1 to point e
        assertTrue(e.addVectorToPoint(vector1).get_x()== vector1_2.getPoint().get_x());
        assertTrue(e.addVectorToPoint(vector1).get_y()== vector1_2.getPoint().get_y());
        assertTrue(e.addVectorToPoint(vector1).get_z()== vector1_2.getPoint().get_z());
        assertFalse(f.addVectorToPoint(vector2).get_x()== vector1_2.getPoint().get_x());
        assertFalse(f.addVectorToPoint(vector2).get_y()== vector1_2.getPoint().get_y());
        assertFalse(f.addVectorToPoint(vector2).get_z()== vector1_2.getPoint().get_z());
    }

}