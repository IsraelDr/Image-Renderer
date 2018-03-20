package unittests;

import elements.Camera;
import org.junit.Test;
import primitives.*;

import static org.junit.Assert.*;

public class CameraTest {

    @Test
    public void constructRayThroughPixel() {
        int pixelX = 1081;
        int pixelY = 1921;
        double screenDistance = 1.5;
        double screenWidth = 2.5;
        double screenHeight = 3.5;
        int i = 500;
        int j = 600;
        Point3D center = new Point3D(5,5,5);
        Vector unit1 = new Vector(2,0,0);
        Vector unit2 = new Vector(0,2,0);
        Camera camera = new Camera(center,unit1, unit2);
        Ray rayTest = camera.constructRayThroughPixel(pixelX,pixelY,i,j,screenDistance,screenWidth,screenHeight);
        assertTrue(rayTest.get_point().equals(center));
        assertTrue(rayTest.get_vector().equals(new Vector(1.5,-0.09481961147085993,0.6577303487766786)));
        int pixelX2 = 1081;
        int pixelY2 = 1921;
        double screenDistance2= 1.5;
        double screenWidth2 = 2.5;
        double screenHeight2 = 3.5;
        int i2 = 500;
        int j2 = 600;
        Point3D center2 = new Point3D(5,5,5);
        Vector unit12 = new Vector(2,-2,1);
        Vector unit22 = new Vector(1,2,2);
        Camera camera2 = new Camera(center2,unit12, unit22);
        Ray rayTest2 = camera2.constructRayThroughPixel(pixelX2,pixelY2,i2,j2,screenDistance2,screenWidth2,screenHeight2);
        assertTrue(rayTest2.get_point().equals(center2));
        assertTrue(rayTest2.get_vector().equals(new Vector(0.529906563658594,-1.282456523906133,0.8752738248705452)));
        int pixelX3 = 3077;
        int pixelY3 = 5469;
        double screenDistance3= 10.788;
        double screenWidth3 = 157.86;
        double screenHeight3 = 426.11;
        int i3 = 145;
        int j3 = 277;
        Point3D center3 = new Point3D(2,7,-3);
        Vector unit13 = new Vector(2,-2,1);
        Vector unit23 = new Vector(1,2,2);
        Camera camera3 = new Camera(center3,unit13, unit23);
        Ray rayTest3 = camera3.constructRayThroughPixel(pixelX3,pixelY3,i3,j3,screenDistance3,screenWidth3,screenHeight3);
        assertTrue(rayTest3.get_point().equals(center3));
        assertTrue(rayTest3.get_vector().equals(new Vector(-144.32147051369734,-118.70707779828513,83.59278543082442)));
    }
}