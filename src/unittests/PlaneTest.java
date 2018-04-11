package unittests;

import elements.Camera;
import geometries.Plane;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;

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

    @Test
    public void findIntersections() {
        Camera cam =new Camera(new Point3D(0,0,0),new Vector(-1,0,0),new Vector(0,1,0));
        int length=0;
        Plane _plane=new Plane(new Point3D(-5,0,0),new Vector(2,0,0));
        for (int i=1;i<=3;i++)
        {
            for (int j = 1; j <= 3; j++) {
                Ray _ray=cam.constructRayThroughPixel(3,3,i,j,1,10,10);
                length+=_plane.findIntersections(_ray).size();
            }
        }
        assertTrue(length==9);

        Camera cam1 =new Camera(new Point3D(0,0,0),new Vector(-1,0,0),new Vector(0,1,0));
        int length1=0;
        Plane _plane1=new Plane(new Point3D(-10,0,0),new Vector(2,300000,0));
        for (int i=1;i<=3;i++)
        {
            for (int j = 1; j <= 3; j++) {
                Ray _ray=cam1.constructRayThroughPixel(3,3,i,j,1,10,10);
                length1+=_plane1.findIntersections(_ray).size();
            }
        }
        assertTrue(length1==6);
        Camera cam2 =new Camera(new Point3D(0,0,0),new Vector(-1,0,0),new Vector(0,1,0));
        int length2=0;
        Plane _plane2=new Plane(new Point3D(10,0,0),new Vector(2,300000,0));
        for (int i=1;i<=3;i++)
        {
            for (int j = 1; j <= 3; j++) {
                Ray _ray=cam2.constructRayThroughPixel(3,3,i,j,1,10,10);
                length2+=_plane2.findIntersections(_ray).size();
            }
        }
        assertTrue(length2==3);
    }
}