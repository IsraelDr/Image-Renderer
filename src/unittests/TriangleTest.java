package unittests;

import elements.Camera;
import geometries.Plane;
import geometries.Triangle;
import org.junit.Test;
import primitives.*;

import static org.junit.Assert.*;

public class TriangleTest {

    /*@Test
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
    }*/

    @Test
    public void findIntersections() {
        Camera cam =new Camera(new Point3D(0,0,0),new Vector(0,0,-1),new Vector(1,0,0));
        int length=0;
        Triangle _triangle1=new Triangle(new Point3D(1,1,-2),new Point3D(-1,1,-2),new Point3D(0,-1,-2), new Color(50,50,50),new Material(10,15,20));
        for (int i=1;i<=3;i++)
        {
            for (int j = 1; j <= 3; j++) {
                Ray _ray=cam.constructRayThroughPixel(3,3,i,j,1,3,3);
                length+=_triangle1.findIntersections(_ray).size();
            }
        }
        assertTrue(length==1);

        length=0;
        Triangle _triangle2=new Triangle(new Point3D(1,1,-2),new Point3D(-1,1,-2),new Point3D(0,-20,-2),new Color(50,50,50),new Material(10,15,20));
        for (int i=1;i<=3;i++)
        {
            for (int j = 1; j <= 3; j++) {
                Ray _ray=cam.constructRayThroughPixel(3,3,i,j,1,3,3);
                length+=_triangle2.findIntersections(_ray).size();
            }
        }
        assertTrue(length==2);
    }
}