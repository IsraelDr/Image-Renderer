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
        /*Scene scene=new Scene("testpic");
        scene.addGeometry(new Triangle(new Point3D(1,-100,100),new Point3D(1,-100,0),new Point3D(1,0,100)));
        scene.addGeometry(new Triangle(new Point3D(1,-100,-100),new Point3D(1,-100,0),new Point3D(1,0,-100)));
        scene.addGeometry(new Triangle(new Point3D(1,100,-100),new Point3D(1,100,0),new Point3D(1,0,-100)));
        scene.addGeometry(new Triangle(new Point3D(1,100,0),new Point3D(1,100,100),new Point3D(1,0,100)));
        scene.addGeometry(new Sphere(new Point3D(1001,0,0),1000.8 ));
        ImageWriter imageWriter=new ImageWriter("testpic",500,500,500,500);
        Render render=new Render(scene,imageWriter);

        render.renderImage();*/

        Scene scene2 = new Scene("Testscene");
        scene2.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Vector(0, 1, 0)));
        scene2.setDistance(150);
        scene2.setBackgroundColor(new Color(0, 0, 0));
        Geometries geometries2 = new Geometries();

        geometries2.addGeometry(new Sphere( new Point3D(0, 0, 150),50,new Color(50,50,50)));

        geometries2.addGeometry(new Triangle(new Point3D( 100, 0, 149),
                new Point3D(  0, 100, 149),
                new Point3D( 100, 100, 149),new Color(50,50,50)));

        geometries2.addGeometry(new Triangle(new Point3D( 100, 0, 149),
                new Point3D(  0, -100, 149),
                new Point3D( 100,-100, 149),new Color(50,50,50)));

        geometries2.addGeometry(new Triangle(new Point3D(-100, 0, 149),
                new Point3D(  0, 100, 149),
                new Point3D(-100, 100, 149),new Color(50,50,50)));

        geometries2.addGeometry(new Triangle(new Point3D(-100, 0, 149),
                new Point3D(  0,  -100, 149),
                new Point3D(-100, -100, 149),new Color(50,50,50)));
        scene2.setGeometries(geometries2);
        ImageWriter imageWriter2 = new ImageWriter("test0", 500, 500, 500, 500);
        Render render2 = new Render(scene2,imageWriter2);

        render2.renderImage();
        render2.printGrid(50);
        render2.getImageWriter().writeToimage();

        Triangle upLeft     = new Triangle(new Point3D(100,0,-49),new Point3D(0,100,-49),new Point3D(100,100,-49),new Color(100,90,250));
        Triangle upRight    = new Triangle(new Point3D(-100,0,-49),new Point3D(0,100,-49),new Point3D(-100,100,-49),new Color(50,20,200));
        Triangle downLeft   = new Triangle(new Point3D(100,0,-49),new Point3D(0,-100,-49),new Point3D(100,-100,-49),new Color(33,50,45));
        Triangle downRight  = new Triangle(new Point3D(-100,0,-49),new Point3D(0,-100,-49),new Point3D(-100,-100,-49),new Color(55,140,90));

        Sphere middle = new Sphere(new Point3D(0,0,-50),35,new Color(50,50,50));

        Camera camera3 = new Camera(new Point3D(0,0,0),new Vector(0,0,-1),new Vector(-1,0,0));

        Scene myScene = new Scene("Triangles and Sphere, Asher and Zvei, Targil 4");
        myScene.setDistance(50);
        myScene.setCamera(camera3);
        myScene.setBackgroundColor(new Color(75,127,190));

        myScene.addGeometry(upLeft);
        myScene.addGeometry(upRight);
        myScene.addGeometry(downLeft);
        myScene.addGeometry(downRight);
        myScene.addGeometry(middle);

        ImageWriter sceneWriter = new ImageWriter("Triangles and Sphere, Israel Eli",500,500,500,500);
        Render myRender = new Render(myScene,sceneWriter);

        myRender.renderImage();
        myRender.printGrid(50);
        myRender.getImageWriter().writeToimage();
    }
}