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

        Plane p1 = new Plane(new Point3D(100, 0, 0), new Vector(-2, 0, -1), new Color(102, 153, 200), new Material(1, 1, 1, 1, 20));
        Plane p2 = new Plane(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Color(0, 100, 0), new Material(1, 0, 1, 1, 20));

        Sphere middle2 = new Sphere(new Point3D(5, 0, 5),
                5, new Color(0, 20, 100), new Material(1, 1, 1, 1, 20));
        Sphere sun = new Sphere(new Point3D(80, 0, 80),
                40, new Color(255, 255, 150), new Material(0.5, 0.5, 1, 1, 20));
        Triangle triangle1 = new Triangle(new Point3D(-1, 0, 12), new Point3D(-1, 0, 10), new Point3D(-1, -1, 12), new Color(0, 20, 200), new Material(1, 1, 1, 1, 20));
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
        myScene2.setAmbientlight(new Color(20, 20, 20), 0.1);

        PointLight pointlight = new PointLight(
                new Color(255, 255, 255),
                new Point3D(15, 0, 40),
                1, 0.0001, 0.0001
        );

        SpotLight Spotlight = new SpotLight(
                new Color(255, 255, 255), new Vector(-1, 0, 0),
                new Point3D(30, 0, 20),
                1, 0.00001, 0.000001
        );

        SpotLight mySpotLight2 = new SpotLight(
                new Color(255, 255, 255), new Vector(4, 0, -2),
                new Point3D(-3, 0, 5),
                1, 0.01, 0.01
        );
        //DirectionalLight dire2=new DirectionalLight(new Color(255,100,255),new Vector(1,0,-1));
        //myScene2.addLight(dire2);
        myScene2.addLight(pointlight);
        myScene2.addLight(Spotlight);
        myScene2.addLight(mySpotLight2);
        ImageWriter sceneWriter2 = new ImageWriter("sphere in the spot light", 1000, 1000, 1000, 1000);
        Render myRender2 = new Render(myScene2, sceneWriter2);
        myRender2.renderImage();

        //myRender.printGrid(100);
        myRender2.getImageWriter().writeToimage();

        //eli's test
        Scene eScene = new Scene("eScene");
        Camera eCamera = new Camera(new Point3D(-15, 0, 3), new Vector(1, 0, 0), new Vector(0, 1, 0));
        eScene.setCamera(eCamera);
        eScene.setDistance(750);
        eScene.setBackgroundColor(new Color(122, 31, 55));
        Sphere eSphere1 = new Sphere(new Point3D(-10, 1, 3.5),
                0.5, Color.BLACK, new Material(0.5, 0.75, 0, 1, 20));
        Sphere eSphere2 = new Sphere(new Point3D(-10, 1, 3.5),
                0.25, new Color(0, 0, 0), new Material(0.5, 0.7, 1, 0.05, 10));
        Triangle eTriangle = new Triangle(new Point3D(-1, 8, -3), new Point3D(-10, 1, 3), new Point3D(-1, -8, -3), Color.BLUE, new Material(1, 1, 0, 0, 4));
        Quadrilateral eQuadrilateral = new Quadrilateral(new Point3D(-2, -2, 3), new Point3D(-2, 4, 3), new Point3D(-2, -2, 7), new Point3D(-2, 4, 7), Color.MAGENTA, new Material(1, 1, 0, 0, 4));
        eScene.addGeometry(eTriangle);
        eScene.addGeometry(eQuadrilateral);
        eScene.addGeometry(eSphere1);
        eScene.addGeometry(eSphere2);
        for (int i = 0; i < 9; i++) {
            Point3D point3d = new Point3D(-2, 2 * i - 8, 11);
            //Triangle eTrianglei = new Triangle(new Point3D(-1, 8, -3), new Point3D(-i, 1-i/5, 3), new Point3D(-1, -8, -3), new Color(24*i, 70, 0), new Material(1, 1, 0, 0.95, 4+i));
            Sphere eSphere = new Sphere(point3d, 0.5, new Color(12 * i, 8 * i, 127), new Material(0.4, 0.3, 0, 0, 1));
            eScene.addGeometry(eSphere);
            //eScene.addGeometry(eTrianglei);
            point3d = new Point3D(-2, 2 * i - 8, -5);
            eSphere = new Sphere(point3d, 0.5, new Color(10 * i, 0, 127 + 4 * i), new Material(0.4, 0.8, 0, 0.1, 5));
            eScene.addGeometry(eSphere);
            //eScene.addGeometry(eTriangle);
        }
        //Plane ePlane = new Plane(new Point3D(0, -5, -12), new Vector(-10, -2, -5), new Color(102, 153, 200), new Material(1, 1, 1, 1, 20));
        //eScene.addGeometry(ePlane);
        //Light Source
        PointLight ePointlight = new PointLight(
                new Color(255, 255, 255),
                new Point3D(15, 0, 40),
                1, 0.01, 0.01
        );
        eScene.addLight(ePointlight);
        ImageWriter eSceneWriter = new ImageWriter("eli's test", 1000, 1000, 1000, 1000);
        Render eRender = new Render(eScene, eSceneWriter);
        eRender.renderImage();
        eRender.getImageWriter().writeToimage();

    }

    @Test
    public void Mirror() {
        Plane p1 = new Plane(new Point3D(0, 0, 2), new Vector(0, 0, 1), new Color(0, 100, 0), new Material(1, 1, 0, 0, 20));
        Plane p2 = new Plane(new Point3D(25, 0, 0), new Vector(-1, 0, 0), new Color(42, 41, 70), new Material(0.3, 0, 1, 0, 0));
        Sphere middle2 = new Sphere(new Point3D(5, 20, 7),
                5, new Color(0, 20, 100), new Material(1, 1, 0, 0, 20));
        Sphere sun = new Sphere(new Point3D(80, 0, 60),
                40, new Color(255, 255, 150), new Material(0.5, 0.5, 1, 1, 20));
        Triangle triangle1 = new Triangle(new Point3D(-5, 9, 2), new Point3D(-5, 0, 2), new Point3D(-5, -1, 12), new Color(100, 0, 0), new Material(1, 1, 0, 1, 20));
        Camera camera2 = new Camera(new Point3D(-10, 0, 5),
                new Vector(1, 0, 0), new Vector(0, 1, 0));
        Tube tube = new Tube(new Ray(new Vector(0, 1, 1), new Point3D(40, 0, 0)), 10, new Color(0, 0, 255), new Material(1, 1, 1, 1, 20));
        Scene myScene2 = new Scene("mirrorTest");
        myScene2.setDistance(150);
        myScene2.setCamera(camera2);
        myScene2.setBackgroundColor(new Color(0, 0, 0));
        myScene2.addGeometry(middle2);
        //myScene2.addGeometry(sun);
        myScene2.addGeometry(triangle1);
        myScene2.addGeometry(p1);
        myScene2.addGeometry(p2);
        //myScene2.addGeometry(tube);
        myScene2.setAmbientlight(new Color(0, 0, 0), 1);

        PointLight pointlight = new PointLight(
                new Color(255, 255, 255),
                new Point3D(5, 0, 5),
                1, 0.0001, 0.0001
        );

        SpotLight Spotlight = new SpotLight(
                new Color(255, 255, 255), new Vector(-0.8, 1, 0),
                new Point3D(0, 0, 10),
                1, 0.00001, 0.000001
        );

        SpotLight mySpotLight2 = new SpotLight(
                new Color(255, 255, 255), new Vector(4, 0, -2),
                new Point3D(5, 0, 5),
                1, 0.01, 0.01
        );
        DirectionalLight dire2 = new DirectionalLight(new Color(255, 100, 255), new Vector(1, 1, -1));
        myScene2.addLight(dire2);
        //myScene2.addLight(pointlight);
        //myScene2.addLight(Spotlight);
        //myScene2.addLight(mySpotLight2);
        ImageWriter sceneWriter2 = new ImageWriter("mirrorTest", 1000, 1000, 1000, 1000);
        Render myRender2 = new Render(myScene2, sceneWriter2);

        myRender2.renderImage();

        //myRender.printGrid(100);
        myRender2.getImageWriter().writeToimage();
    }

    @Test
    public void renderimageloop() {//
        for (int i = 0; i < 15; i++) {
            Plane p1 = new Plane(new Point3D(0, 0, 2), new Vector(0, 0, 1), new Color(0, 100, 0), new Material(1, 1, 0, 0, 20));
            Plane p2 = new Plane(new Point3D(25, 0, 0), new Vector(-1, 0, 0), new Color(42, 41, 70), new Material(0.3, 0, 1, 0, 0));
            Sphere middle2 = new Sphere(new Point3D(5, 20, 7),
                    5, new Color(0, 20, 100), new Material(1, 1, 0, 0, 20));
            Sphere sun = new Sphere(new Point3D(80, 0, 60),
                    40, new Color(255, 255, 150), new Material(0.5, 0.5, 1, 1, 20));
            Triangle triangle1 = new Triangle(new Point3D(-5, 9, 2), new Point3D(-5, 0, 2), new Point3D(-5, -1, 12), new Color(100, 0, 0), new Material(1, 1, 0, 1, 20));
            Camera camera2 = new Camera(new Point3D(-10, -10 + i, 10),
                    new Vector(1, 0, 0), new Vector(0, 1, 0));
            Tube tube = new Tube(new Ray(new Vector(0, 1, 1), new Point3D(40, 0, 0)), 10, new Color(0, 0, 255), new Material(1, 1, 1, 1, 20));
            Scene myScene2 = new Scene("mirror" + i);
            myScene2.setDistance(150);
            myScene2.setCamera(camera2);
            myScene2.setBackgroundColor(new Color(0, 0, 0));
            myScene2.addGeometry(middle2);
            //myScene2.addGeometry(sun);
            //myScene2.addGeometry(triangle1);
            myScene2.addGeometry(p1);
            myScene2.addGeometry(p2);
            //myScene2.addGeometry(tube);
            myScene2.setAmbientlight(new Color(0, 0, 0), 1);

            PointLight pointlight = new PointLight(
                    new Color(255, 255, 255),
                    new Point3D(5, 0, 5),
                    1, 0.0001, 0.0001
            );

            SpotLight Spotlight = new SpotLight(
                    new Color(255, 255, 255), new Vector(-0.8, 1, 0),
                    new Point3D(0, 0, 10),
                    1, 0.00001, 0.000001
            );

            SpotLight mySpotLight2 = new SpotLight(
                    new Color(255, 255, 255), new Vector(4, 0, -2),
                    new Point3D(5, 0, 5),
                    1, 0.01, 0.01
            );
            //DirectionalLight dire2=new DirectionalLight(new Color(255,100,255),new Vector(1,0,-1));
            //myScene2.addLight(dire2);
            //myScene2.addLight(pointlight);
            myScene2.addLight(Spotlight);
            //myScene2.addLight(mySpotLight2);
            ImageWriter sceneWriter2 = new ImageWriter("mirror" + i, 1000, 1000, 1000, 1000);
            Render myRender2 = new Render(myScene2, sceneWriter2);

            myRender2.renderImage();

            //myRender.printGrid(100);
            myRender2.getImageWriter().writeToimage();
        }

    }

    @Test
    public void TubeTest() {
        Plane p1 = new Plane(new Point3D(0, 0, 2), new Vector(0, 0, 1), new Color(0, 100, 0), new Material(1, 1, 0, 0, 20));
        Plane p2 = new Plane(new Point3D(25, 0, 0), new Vector(-1, 0, 0), new Color(42, 41, 70), new Material(0.3, 0, 1, 0, 0));
        Sphere middle2 = new Sphere(new Point3D(5, 20, 7),
                5, new Color(0, 20, 100), new Material(1, 1, 0, 0, 20));
        Sphere sun = new Sphere(new Point3D(80, 0, 60),
                40, new Color(255, 255, 150), new Material(0.5, 0.5, 1, 1, 20));
        Triangle triangle1 = new Triangle(new Point3D(-1, 0, 12), new Point3D(-1, 0, 10), new Point3D(-1, -1, 12), new Color(0, 20, 200), new Material(1, 1, 1, 1, 20));
        Camera camera2 = new Camera(new Point3D(-10, 0, 10),
                new Vector(1, 0, 0), new Vector(0, 1, 0));
        Tube tube = new Tube(new Ray(new Vector(0, 1, 1), new Point3D(40, 0, 0)), 10, new Color(0, 0, 255), new Material(1, 1, 1, 1, 20));
        Scene myScene2 = new Scene("sphere in the spot light2");
        myScene2.setDistance(150);
        myScene2.setCamera(camera2);
        myScene2.setBackgroundColor(new Color(0, 0, 0));
        myScene2.addGeometry(middle2);
        //myScene2.addGeometry(sun);
        //myScene2.addGeometry(triangle1);
        myScene2.addGeometry(p1);
        myScene2.addGeometry(p2);
        //myScene2.addGeometry(tube);
        myScene2.setAmbientlight(new Color(0, 0, 0), 1);

        PointLight pointlight = new PointLight(
                new Color(255, 255, 255),
                new Point3D(5, 0, 5),
                1, 0.0001, 0.0001
        );

        SpotLight Spotlight = new SpotLight(
                new Color(255, 255, 255), new Vector(-0.8, 1, 0),
                new Point3D(0, 0, 10),
                1, 0.00001, 0.000001
        );

        SpotLight mySpotLight2 = new SpotLight(
                new Color(255, 255, 255), new Vector(4, 0, -2),
                new Point3D(5, 0, 5),
                1, 0.01, 0.01
        );
        //DirectionalLight dire2=new DirectionalLight(new Color(255,100,255),new Vector(1,0,-1));
        //myScene2.addLight(dire2);
        //myScene2.addLight(pointlight);
        myScene2.addLight(Spotlight);
        //myScene2.addLight(mySpotLight2);
        ImageWriter sceneWriter2 = new ImageWriter("sphere in the spot light2", 1000, 1000, 1000, 1000);
        Render myRender2 = new Render(myScene2, sceneWriter2);

        myRender2.renderImage();

        //myRender.printGrid(100);
        myRender2.getImageWriter().writeToimage();
    }

    @Test
    public void TubeTestTransparent() {
        Plane p1 = new Plane(new Point3D(0, 0, 2), new Vector(0, 0, 1), new Color(0, 100, 0), new Material(1, 1, 0, 0.8, 20));
        Plane p2 = new Plane(new Point3D(25, 0, 0), new Vector(-1, 0, 0), new Color(42, 41, 70), new Material(0.3, 0, 0, 0, 20));
        Sphere middle2 = new Sphere(new Point3D(5, 10, 2),
                5, new Color(0, 20, 100), new Material(1, 1, 0, 0.5, 20));
        Sphere sun = new Sphere(new Point3D(80, 0, 60),
                40, new Color(255, 255, 150), new Material(0.5, 0.5, 1, 1, 20));
        Triangle triangle1 = new Triangle(new Point3D(-5, 9, 2), new Point3D(-5, 0, 2), new Point3D(-5, -1, 12), new Color(100, 0, 0), new Material(1, 1, 0, 1, 20));
        Camera camera2 = new Camera(new Point3D(-10, 0, 10),
                new Vector(1, 0, 0), new Vector(0, 1, 0));
        Tube tube = new Tube(new Ray(new Vector(0, 1, 1), new Point3D(40, 0, 0)), 10, new Color(0, 0, 255), new Material(1, 1, 1, 1, 20));
        Scene myScene2 = new Scene("Transparent");
        myScene2.setDistance(150);
        myScene2.setCamera(camera2);
        myScene2.setBackgroundColor(new Color(0, 0, 0));
        myScene2.addGeometry(middle2);
        //myScene2.addGeometry(sun);
        myScene2.addGeometry(triangle1);
        myScene2.addGeometry(p1);
        myScene2.addGeometry(p2);
        //myScene2.addGeometry(tube);
        myScene2.setAmbientlight(new Color(0, 0, 0), 1);

        PointLight pointlight = new PointLight(
                new Color(255, 255, 255),
                new Point3D(5, 0, 5),
                1, 0.0001, 0.0001
        );

        SpotLight Spotlight = new SpotLight(
                new Color(255, 255, 255), new Vector(-0.8, 1, 0),
                new Point3D(0, 0, 10),
                1, 0.00001, 0.000001
        );

        SpotLight mySpotLight2 = new SpotLight(
                new Color(255, 255, 255), new Vector(4, 0, -2),
                new Point3D(5, 0, 5),
                1, 0.01, 0.01
        );
        DirectionalLight dire2 = new DirectionalLight(new Color(100, 100, 100), new Vector(1, 1, -1));
        //myScene2.addLight(dire2);
        //myScene2.addLight(pointlight);
        myScene2.addLight(Spotlight);
        //myScene2.addLight(mySpotLight2);
        ImageWriter sceneWriter2 = new ImageWriter("Transparent", 1000, 1000, 1000, 1000);
        Render myRender2 = new Render(myScene2, sceneWriter2);

        myRender2.renderImage();

        //myRender.printGrid(100);
        myRender2.getImageWriter().writeToimage();
    }
}