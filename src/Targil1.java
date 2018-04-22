import elements.Camera;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;


/**
 * Authors:
 * Israel Dreyfuss 4301288, sruli97@gmail.com
 * Eli Cahn, 13333573, eli.cahn@gmail.com
 */
public class Targil1 {

    public static void main(String[] args) {
        int pixelX = 3;//
        int pixelY = 3;
        double screenDistance = 1;
        double screenWidth = 1002;
        double screenHeight = 1002;
        int i = 2;
        int j = 2;
        Point3D center = new Point3D(0,0,0);
        Vector unit1 = new Vector(1,0,0);
        Vector unit2 = new Vector(0,1,0);
        Camera camera = new Camera(center,unit1, unit2);
        Ray rayTest = camera.constructRayThroughPixel(pixelX,pixelY,i,j,screenDistance,screenWidth,screenHeight);

        System.out.println(rayTest);
    }
}
