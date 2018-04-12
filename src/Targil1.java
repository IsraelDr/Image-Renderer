import elements.Camera;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import java.util.ArrayList;


/**
 * Authors:
 * Israel Dreyfuss 4301288, sruli97@gmail.com
 * Eli Cahn, 13333573, eli.cahn@gmail.com
 */
public class Targil1 {

    public static void main(String[] args) {
        ArrayList<Point3D> a=new ArrayList<Point3D>();
        Camera cam =new Camera(new Point3D(0,0,0),new Vector(0,0,-1),new Vector(1,0,0));
        int length=0;
        Triangle _triangle1=new Triangle(new Point3D(1,1,-2),new Point3D(-1,1,-2),new Point3D(0,-1,-2));
        for (int i=1;i<=3;i++)
        {
            for (int j = 1; j <= 3; j++) {
                Ray _ray=cam.constructRayThroughPixel(3,3,i,j,1,3,3);
                a.addAll(_triangle1.findIntersections(_ray));
            }
        }

        System.out.println(a);
        Scene s = new Scene("eli");
        s.get_camera();
        s.set_camera(cam);


    }
}
