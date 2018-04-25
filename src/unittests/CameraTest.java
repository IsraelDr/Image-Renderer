package unittests;

import elements.Camera;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class CameraTest {

    @Test
    public void constructRayThroughPixel() {

        int pixelX = 501;//
        int pixelY = 501;
        double screenDistance = 1;
        double screenWidth = 1000;
        double screenHeight = 1000;
        int i = 2;
        int j = 2;
        Point3D center = new Point3D(0,0,0);
        Vector unit1 = new Vector(1,0,0);
        Vector unit2 = new Vector(0,1,0);
        Camera camera = new Camera(center,unit1, unit2);
        Ray rayTest = camera.constructRayThroughPixel(pixelX,pixelY,i,j,screenDistance,screenWidth,screenHeight);


        /*
        */
    }
}