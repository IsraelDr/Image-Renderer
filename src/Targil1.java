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
        int pixelX2 = 3077;
        int pixelY2 = 5469;
        double screenDistance2= 10.788;
        double screenWidth2 = 157.86;
        double screenHeight2 = 426.11;
        int i2 = 145;
        int j2 = 277;
        Point3D center2 = new Point3D(2,7,-3);
        Vector unit12 = new Vector(2,-2,1);
        Vector unit22 = new Vector(1,2,2);
        Camera camera2 = new Camera(center2,unit12, unit22);
        Ray rayTest2 = camera2.constructRayThroughPixel(pixelX2,pixelY2,i2,j2,screenDistance2,screenWidth2,screenHeight2);
        System.out.println(rayTest2.toString());//.equals(new Vector(-3.5,-5.09482,-4.34227)));


    }
}
