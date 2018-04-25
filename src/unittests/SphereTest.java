package unittests;


import elements.Camera;
import geometries.Sphere;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SphereTest {


    @org.junit.Test
    public void equals() {
        Point3D pnt = new Point3D(1, 2, 3);
        Point3D pnt1 = new Point3D(1, 2, 0);
        double rad = 1;
        Sphere a = new Sphere(pnt, rad);
        Sphere b = new Sphere(pnt1, rad);
        assertFalse(a.equals(b));


    }

    @org.junit.Test
    public void getNormal() {
        Point3D pnt = new Point3D(1, 2, 3);
        Point3D pnt1 = new Point3D(9, 8, 3);
        Vector d = new Vector(0.8, 0.6, 0);
        double rad = 10;
        Sphere a = new Sphere(pnt, rad);
        assertTrue(a.getNormal(pnt1).equals(d));

    }

    @Test
    public void findIntersections() {
        // ***************** Test 0 ******************** //
        Camera camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 0, -1), new Vector(1, 0, 0));
        Point3D center = new Point3D(0, 0, -3);
        double rad = 1;
        Sphere sphere = new Sphere(center, rad);
        Ray[] rays = new Ray[9];
        ArrayList<Point3D> a = new ArrayList<Point3D>();
        int g = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Ray ray = camera.constructRayThroughPixel(3, 3, i + 1, j + 1, 1, 3, 3);
                if (ray.get_point() != null) {
                    rays[g] = ray;
                    a.addAll(sphere.findIntersections(ray));
                    ++g;
                }
            }
        }
        assertTrue(a.size() == 2);

        // ***************** Test 1 ******************** //
        Camera camera1 = new Camera(new Point3D(0, 0, 0), new Vector(0, 0, -1), new Vector(1, 0, 0));
        Point3D center1 = new Point3D(0, 0, -2.5);
        double rad1 = 2;
        Sphere sphere1 = new Sphere(center1, rad1);
        Ray[] rays1 = new Ray[9];
        ArrayList<Point3D> a1 = new ArrayList<Point3D>();
        int g1 = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Ray ray1 = camera1.constructRayThroughPixel(3, 3, i + 1, j + 1, 1, 2, 2);
                if (ray1.get_point() != null) {
                    rays1[g1] = ray1;
                    a1.addAll(sphere1.findIntersections(ray1));
                    ++g1;
                }
            }
        }
        assertTrue(a1.size() == 18);

// ***************** Test 2 ******************** //
        Camera camera2 = new Camera(new Point3D(0, 0, 0), new Vector(0, 0, -1), new Vector(1, 0, 0));
        Point3D center2 = new Point3D(0, 0, -2);
        double rad2 = 1.5;
        Sphere sphere2 = new Sphere(center2, rad2);
        Ray[] rays2 = new Ray[9];
        ArrayList<Point3D> a2 = new ArrayList<Point3D>();
        int g2 = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Ray ray1 = camera2.constructRayThroughPixel(3, 3, i + 1, j + 1, 1, 3, 3);
                if (ray1.get_point() != null) {
                    rays2[g2] = ray1;
                    a2.addAll(sphere2.findIntersections(ray1));
                    ++g2;
                }
            }
        }
        assertTrue(a2.size() == 10);

        // ***************** Test 3 ******************** //
        Camera camera3 = new Camera(new Point3D(0, 0, 0), new Vector(0, 0, -1), new Vector(1, 0, 0));
        Point3D center3 = new Point3D(0, 0, -1);
        double rad3 = 4;
        Sphere sphere3 = new Sphere(center3, rad3);
        Ray[] rays3 = new Ray[9];
        ArrayList<Point3D> a3 = new ArrayList<Point3D>();
        int g3 = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Ray ray3 = camera3.constructRayThroughPixel(3, 3, i + 1, j + 1, 1, 3, 3);
                if (ray3.get_point() != null) {
                    rays3[g3] = ray3;
                    a3.addAll(sphere3.findIntersections(ray3));
                    ++g3;
                }
            }
        }
        assertTrue(a3.size() == 9);

        // ***************** Test 4 ******************** //
        Camera camera4 = new Camera(new Point3D(0, 0, 0), new Vector(0, 0, -1), new Vector(1, 0, 0));
        Point3D center4 = new Point3D(0, 0, 1);
        double rad4 = 1;
        Sphere sphere4 = new Sphere(center4, rad4);
        Ray[] rays4 = new Ray[9];
        ArrayList<Point3D> a4 = new ArrayList<Point3D>();
        int g4 = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Ray ray4 = camera4.constructRayThroughPixel(3, 3, i + 1, j + 1, 1, 3, 3);
                if (ray4.get_point() != null) {
                    rays4[g4] = ray4;
                    a4.addAll(sphere4.findIntersections(ray4));
                    ++g4;
                }
            }
        }
        assertTrue(a4.size() == 0);



    }
}






