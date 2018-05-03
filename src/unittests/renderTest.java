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

        Triangle upLeft     = new Triangle(new Point3D(100,0,-49),new Point3D(0,100,-49),new Point3D(100,100,-49),new Color(100,90,250));
        Triangle upRight    = new Triangle(new Point3D(-100,0,-49),new Point3D(0,100,-49),new Point3D(-100,100,-49),new Color(50,20,200));
        Triangle downLeft   = new Triangle(new Point3D(100,0,-49),new Point3D(0,-100,-49),new Point3D(100,-100,-49),new Color(33,50,45));
        Triangle downRight  = new Triangle(new Point3D(-100,0,-49),new Point3D(0,-100,-49),new Point3D(-100,-100,-49),new Color(55,140,90));

        Sphere middle = new Sphere(new Point3D(0,0,-50),35,new Color(2,2,2));

        Camera camera3 = new Camera(new Point3D(0,0,0),new Vector(0,0,-1),new Vector(-1,0,0));

        Scene myScene = new Scene("Triangles and Sphere, Israel and Eli");
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