package unittests;

import Renderer.*;
import elements.*;
import geometries.*;
import org.junit.Test;
import primitives.*;
import scene.*;

public class renderTest {

    @Test
    public void renderImage() {//

        Triangle upLeft     = new Triangle(new Point3D(100,0,-49),new Point3D(0,100,-49),new Point3D(100,100,-49),new Color(100,90,250),new Material(20,15,2));
        Triangle upRight    = new Triangle(new Point3D(-100,0,-49),new Point3D(0,100,-49),new Point3D(-100,100,-49),new Color(50,20,200),new Material(50,15,100));
        Triangle downLeft   = new Triangle(new Point3D(100,0,-49),new Point3D(0,-100,-49),new Point3D(100,-100,-49),new Color(33,50,45),new Material(10,50,5));
        Triangle downRight  = new Triangle(new Point3D(-100,0,-49),new Point3D(0,-100,-49),new Point3D(-100,-100,-49),new Color(55,140,90),new Material(10,100,70));

        Sphere middle = new Sphere(new Point3D(0,0,-50),35,new Color(2,2,2),new Material(1,1,1));

        Camera camera3 = new Camera(new Point3D(0,0,0),new Vector(0,0,-1),new Vector(-1,0,0));

        Scene myScene = new Scene("Triangles and Sphere, Israel and Eli");
        myScene.setDistance(50);
        myScene.setCamera(camera3);
        //myScene.setBackgroundColor(new Color(75,127,190));

       // myScene.addGeometry(upLeft);
        //myScene.addGeometry(upRight);
        //myScene.addGeometry(downLeft);
        //myScene.addGeometry(downRight);
        myScene.addGeometry(middle);
        //LightSource l=new PointLight(new Color(45,15,98),new Point3D(0,50,-45),1,2,0.5);
        LightSource l=new SpotLight(new Color(200,140,255),new Vector(0,0,-1), new Point3D(0,0,-15),0.5,0.25,0.0045);
        myScene.addLight(l);
        ImageWriter sceneWriter = new ImageWriter("Triangles and Sphere, Israel Eli",500,500,500,500);
        Render myRender = new Render(myScene,sceneWriter);

        //myRender.renderImage();
        //myRender.printGrid(50);
        myRender.getImageWriter().writeToimage();


        Sphere middle2 = new Sphere(new Point3D(50, 0, 0),
                49,new Color(0,20,100),new Material(1,1,10));

        Camera camera2 = new Camera(new Point3D(0, 0, 0),

                new Vector(1, 0, 0), new Vector(0, 1, 0));
        Scene myScene2 = new Scene("sphere in the spot light");
        myScene2.setDistance(50);
        myScene2.setCamera(camera2);
        myScene2.setBackgroundColor(new Color(0, 0, 0));
        myScene2.addGeometry(middle2);
        myScene2.setAmbientlight(new Color(20,20,20), 0.1);

       SpotLight mySpotLight = new SpotLight(new Color (255,255,255),new Vector(1,0.5,0.5),
                new Point3D(-0.5,-2,-2),
                1,0.01,0.1
                );
        /*SpotLight mySpotLight = new SpotLight(
                new Color (255,0,255),new Vector(2,-2,30),
                new Point3D(-1,1,-3),
                5,5,12.5
                );*/
        myScene2.addLight(mySpotLight);

        ImageWriter sceneWriter2 = new ImageWriter("sphere in the spot light",1000,1000,1000,1000);
        Render myRender2 = new Render(myScene2,sceneWriter2);

        myRender2.renderImage();

        //myRender.printGrid(100);
        myRender2.getImageWriter().writeToimage();

        Sphere middle3= new Sphere(new Point3D(50, 0, 0),
                49,new Color(0,0,60),new Material(1,1,1));

        Camera camera4 = new Camera(new Point3D(0, 0, 0),

                new Vector(1, 0, 0), new Vector(0, 1, 0));
        Scene myScene3 = new Scene("sphere in the spot lightfrfrr");
        myScene3.setDistance(50);
        myScene3.setCamera(camera4);
        myScene3.setBackgroundColor(new Color(0, 0, 0));
        myScene3.addGeometry(middle3);
        myScene3.setAmbientlight(new Color(120, 120, 120), 0.1);

        PointLight mySpotLigh2t = new PointLight(new Color (255,0,255),
                new Point3D(1,0,0),
                5,5,10
        );
        /*SpotLight mySpotLight = new SpotLight(
                new Color (255,0,255),new Vector(2,-2,30),
                new Point3D(-1,1,-3),
                5,5,12.5
                );*/
        myScene2.addLight(mySpotLigh2t);

        ImageWriter sceneWriter3 = new ImageWriter("sphere in the spot lig222ht",1000,1000,1000,1000);
        Render myRender3 = new Render(myScene3,sceneWriter3);

        myRender3.renderImage();

        //myRender.printGrid(100);
        myRender3.getImageWriter().writeToimage();


        Triangle triangle1     = new Triangle(new Point3D(130,-130,-50),new Point3D(-130,-130,-50),new Point3D(-130,130,-40),new Color(40,40,40),new Material(0.5,1,1));
        Triangle triangle2    = new Triangle(new Point3D(-130,130,-40),new Point3D(130,130,-40),new Point3D(130,-130,-50),new Color(40,40,40),new Material(0.5,1,1));
        Camera camera5 = new Camera(new Point3D(0, 0, 0),

                new Vector(0, 0, -1), new Vector(1, 0, 0));
        Scene myScene6 = new Scene("sphere in the spot ligddddhtfrfrr");
        myScene6.setDistance(170);
        myScene6.setCamera(camera5);
        myScene6.setBackgroundColor(new Color(0, 0, 0));
        myScene6.addGeometry(triangle1);
        myScene6.addGeometry(triangle2);
        myScene6.setAmbientlight(new Color(0,0,0), 0.1);

        SpotLight mySpotLigh233t = new SpotLight(new Color (201,226,255),new Vector(0,1,-26),
                new Point3D(0,0,500),
                0.8,0.000001,0.0000001
        );
        myScene6.addLight(mySpotLigh233t);
        ImageWriter sceneWriter4 = new ImageWriter("sphere in the spot ligsss222ht",1000,1000,1000,1000);
        Render myRender5 = new Render(myScene6,sceneWriter4);

        myRender5.renderImage();

        //myRender.printGrid(100);
        myRender5.getImageWriter().writeToimage();
    }
}