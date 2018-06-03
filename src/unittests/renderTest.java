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

        Plane p1=new Plane(new Point3D(100,0,0),new Vector(-2,0,-1),new Color(102,153,200),new Material(1,1,20));
        Plane p2=new Plane(new Point3D(0,0,0),new Vector(0,0,1),new Color(0,100,0),new Material(1,0,20));

        Sphere middle2 = new Sphere(new Point3D(5, 0, 5),
                5,new Color(0,20,100),new Material(1,1,20));
        Sphere sun = new Sphere(new Point3D(80, 0, 80),
                40,new Color(255,255,150),new Material(0.5,0.5,20));
        Triangle triangle1=new Triangle(new Point3D(-1,0,12),new Point3D(-1,0,10),new Point3D(-1,-1,12),new Color(0,20,200),new Material(1,1,20));
        Camera camera2 = new Camera(new Point3D(-10, 0, 4),

                new Vector(1, 0, 0), new Vector(0, 1, 0));
        Scene myScene2 = new Scene("sphere in the spot light");
        myScene2.setDistance(150);
        myScene2.setCamera(camera2);
        myScene2.setBackgroundColor(new Color(0, 0, 0));
        myScene2.addGeometry(middle2);
        myScene2.addGeometry(sun);
        myScene2.addGeometry(triangle1);
        myScene2.addGeometry(p1);
        myScene2.addGeometry(p2);
        myScene2.setAmbientlight(new Color(20,20,20), 0.1);

        PointLight pointlight = new PointLight(
                new Color (255,255,255),
                new Point3D(15,0,40),
                1,0.0001,0.0001
        );

        SpotLight Spotlight = new SpotLight(
                new Color (255,255,255),new Vector(-1,0,0),
                new Point3D(30,0,20),
                1,0.00001,0.000001
        );

        SpotLight mySpotLight2 = new SpotLight(
                new Color (255,255,255),new Vector(4,0,-2),
                new Point3D(-3,0,5),
                1,0.01,0.01
                );
        //DirectionalLight dire2=new DirectionalLight(new Color(255,100,255),new Vector(1,0,-1));
        //myScene2.addLight(dire2);
        myScene2.addLight(pointlight);
        myScene2.addLight(Spotlight);
        myScene2.addLight(mySpotLight2);
        ImageWriter sceneWriter2 = new ImageWriter("sphere in the spot light",1000,1000,1000,1000);
        Render myRender2 = new Render(myScene2,sceneWriter2);

        myRender2.renderImage();

        //myRender.printGrid(100);
        myRender2.getImageWriter().writeToimage();


    }
    @Test
    public void renderimageloop() {//
        for(int i=0;i<10;i++) {
            Plane p1 = new Plane(new Point3D(100, 0, 0), new Vector(-2, 0, -1), new Color(102, 153, 255), new Material(1, 1, 20));
            Plane p2 = new Plane(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Color(0, 100, 0), new Material(1, 0, 20));

            Sphere middle2 = new Sphere(new Point3D(5, 0, 5),
                    5, new Color(0, 20, 100), new Material(1, 0.5, 20));
            Triangle triangle1 = new Triangle(new Point3D(-1, 0, 10), new Point3D(-1, 0, 5), new Point3D(-1, -3, 10), new Color(0, 20, 200), new Material(1, 1, 20));
            Camera camera2 = new Camera(new Point3D(-3, 0, i+3),

                    new Vector(1, 0, 0), new Vector(0, 1, 0));
            Scene myScene2 = new Scene("testpic"+i);
            myScene2.setDistance(100);
            myScene2.setCamera(camera2);
            myScene2.setBackgroundColor(new Color(0, 0, 0));
            myScene2.addGeometry(middle2);
            myScene2.addGeometry(triangle1);
            myScene2.addGeometry(p1);
            myScene2.addGeometry(p2);
            myScene2.setAmbientlight(new Color(20, 20, 20), 0.1);

            PointLight mySpotLight = new PointLight(new Color(255, 255, 255),
                    new Point3D(-6, 0, 10),
                    1, 0.001, 0.001
            );

            SpotLight mySpotLight2 = new SpotLight(
                    new Color(255, 255, 255), new Vector(0, 0, -2),
                    new Point3D(3, 0, 20),
                    1, 0.0001, 0.0001
            );
            //DirectionalLight dire2=new DirectionalLight(new Color(255,100,255),new Vector(1,0,-1));
            //myScene2.addLight(dire2);
            myScene2.addLight(mySpotLight);
            //myScene2.addLight(mySpotLight2);
            ImageWriter sceneWriter2 = new ImageWriter("testpic"+i, 1000, 1000, 1000, 1000);
            Render myRender2 = new Render(myScene2, sceneWriter2);

            myRender2.renderImage();

            //myRender.printGrid(100);
            myRender2.getImageWriter().writeToimage();
        }

    }
}