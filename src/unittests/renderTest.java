package unittests;

import Renderer.ImageWriter;
import Renderer.Render;
import elements.*;
import geometries.*;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.*;
import scene.Scene;

import static org.junit.Assert.*;

public class renderTest {

    @Test
    public void renderImage() {
        Scene scene=new Scene("testpic");
        scene.addGeometry(new Triangle(new Point3D(1,-100,100),new Point3D(1,-100,0),new Point3D(1,0,100)));
        scene.addGeometry(new Triangle(new Point3D(1,-100,-100),new Point3D(1,-100,0),new Point3D(1,0,-100)));
        scene.addGeometry(new Triangle(new Point3D(1,100,-100),new Point3D(1,100,0),new Point3D(1,0,-100)));
        scene.addGeometry(new Triangle(new Point3D(1,100,0),new Point3D(1,100,100),new Point3D(1,0,100)));
        scene.addGeometry(new Sphere(new Point3D(1001,0,0),1000.8 ));
        ImageWriter imageWriter=new ImageWriter("testpic",500,500,500,500);
        Render render=new Render(scene,imageWriter);

        render.renderImage();

        Scene scene2 = new Scene("Testscene");
        scene2.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Vector(0, 1, 0)));
        scene2.setDistance(100);
        scene2.setBackgroundColor(new Color(0, 0, 0));
        Geometries geometries2 = new Geometries();

        geometries2.addGeometry(new Sphere( new Point3D(0, 0, 150),50));

        geometries2.addGeometry(new Triangle(new Point3D( 100, 0, 149),
                new Point3D(  0, 100, 149),
                new Point3D( 100, 100, 149)));

        geometries2.addGeometry(new Triangle(new Point3D( 100, 0, 149),
                new Point3D(  0, -100, 149),
                new Point3D( 100,-100, 149)));

        geometries2.addGeometry(new Triangle(new Point3D(-100, 0, 149),
                new Point3D(  0, 100, 149),
                new Point3D(-100, 100, 149)));

        geometries2.addGeometry(new Triangle(new Point3D(-100, 0, 149),
                new Point3D(  0,  -100, 149),
                new Point3D(-100, -100, 149)));
        scene2.setGeometries(geometries2);
        ImageWriter imageWriter2 = new ImageWriter("test0", 500, 500, 500, 500);
        Render render2 = new Render(scene2,imageWriter2);

        render2.renderImage();
        render2.printGrid(50);
        render2.getImageWriter().writeToimage();
    }
}