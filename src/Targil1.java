import jdk.internal.util.xml.impl.Input;
import primitives.*;
import geometries.*;
import primitives.Vector;

import java.util.prefs.Preferences;
import java.io.*;
import java.util.*;


/**
 * Authors:
 * Israel Dreyfuss 4301288, sruli97@gmail.com
 * Eli Cahn, 13333573, eli.cahn@gmail.com
 */
public class Targil1 {

    public static void main(String[] args) {
        Vector vector1 = new Vector(1,2,3);
        Vector vector2 = new Vector(6.987,2.98,10);
        Vector vector3 = new Vector(123,0,-3);
        Vector vector4 = new Vector(-0.5,-2,-7);
        Vector vector5 = new Vector(-1.1,2,6);
        Vector vector6 = new Vector(0,0,0);
        Vector vector7 = new Vector(1,2,3);
        System.out.println(vector1.equals(vector7));
        System.out.println(vector1.toString());
        System.out.println(vector2.toString());
        System.out.println("ScalarProduct: " + vector1.ScalarProduct(vector2));
        System.out.println("VectorProduct: " + vector1.vectorProduct(vector2));
        System.out.println("vector5.multipliedbyScalar: " +vector5.multipliedbyScalar(8.5));
        Point3D e = new Point3D(-1.000987,2.9876450,Math.sqrt(7));
        Point3D f = new Point3D(6.0093625,0.5789,-2.90908);
        System.out.println(e.distance(f));
        Vector vector11 = new Vector(1.9,-0.6,9.8);
        Point3D e1 = new Point3D(-1.000987,2.9876450,Math.sqrt(7));
        Vector vector1_2 = new Vector(0.899013,2.38765,12.4458);
        System.out.println("addVectorToPiont: " + e1.addVectorToPiont(vector11).toString());
        Coordinate ex11 = new Coordinate(1000000000.00001);
        Coordinate ex12 = new Coordinate(0.00001);
        System.out.println(ex12.subtract(ex11).toString());
        Vector a = new Vector(1,2,3);
        //Vector b = new Vector(2,3,4);
        //System.out.println( a.add(b).toString());
        Vector b = new Vector(2,3,4);
        Vector d = new Vector(-1,-2,-3);
        Vector c = new Vector(0,0,0);
        Vector g = new Vector(1,2.45,3.098);
        System.out.println(g.NormalVector().size());

    }
}
