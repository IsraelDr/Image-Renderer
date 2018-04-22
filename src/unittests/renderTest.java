package unittests;

import Renderer.ImageWriter;
import Renderer.Render;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;
import scene.Scene;

import static org.junit.Assert.*;

public class renderTest {

    @Test
    public void renderImage() {
        Scene scene=new Scene("testpic");
        scene.addGeometry(new Triangle(new Point3D(1,-10,10),new Point3D(1,-10,0),new Point3D(1,0,10)));
        scene.addGeometry(new Sphere(new Point3D(1001,0,0),1000.8 ));
        ImageWriter imageWriter=new ImageWriter("testpic",501,501,501,501);
        Render render=new Render(scene,imageWriter);

        render.renderImage();
    }
}