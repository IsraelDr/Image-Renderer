import elements.Camera;
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
        Plane a =new Plane(new Point3D(1,2,3),new Vector(2,3,4));
        Plane b = new Plane(new Point3D(1,2,3),new Vector(4,6,8));
        System.out.println(a);
        System.out.println(b);

    }
}
