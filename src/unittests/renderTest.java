package unittests;

import Renderer.ImageWriter;
import Renderer.Render;
import Renderer.RenderParalell;
import elements.Camera;
import elements.DirectionalLight;
import elements.PointLight;
import elements.SpotLight;
import geometries.*;
import org.junit.Test;
import primitives.*;
import scene.Scene;

public class renderTest {

    @Test
    public void renderImage() throws InterruptedException {
        Scene pScene = new Scene("Project Scene");
        Camera pCamera = new Camera(Point3D.construct(-225, 0, 0), new Vector(1, 0, 0), new Vector(0, 1, 0));
        pScene.setCamera(pCamera);
        pScene.setDistance(1000);
        pScene.setBackgroundColor(Color.GREEN);
/*
        for (int i = -100; i < 100; i += 4) {
            int mod = 0;
            if (Math.abs(i % 8) == 0)
                mod = 0;
            else
                mod = 1;
            for (int j = 200; j > 0; j -= 20) {
                int jZ = -100 + 2*j;
                if (Math.abs(j / 10 % 2) == mod)
                    /*pScene.addGeometry(new Quadrilateral(
                            Point3D.construct(j, i, jZ),
                            Point3D.construct(j, i + 4, jZ),
                            Point3D.construct(j + 10, i, jZ + 10),
                            Point3D.construct(j + 10, i + 4, jZ + 10),
                            Color.DARK_GRAY,
                            new Material(1, 1, 0.5, 0, 19)));*/
                    //pScene.addGeometry(new Sphere(Point3D.construct(j, i, jZ), 3, Color.RED, new Material(0.5, 0.5, 0, 0, 3)));
                //else
                    /*pScene.addGeometry(new Quadrilateral(
                            Point3D.construct(j, i, jZ),
                            Point3D.construct(j, i + 4, jZ),
                            Point3D.construct(j + 10, i, jZ + 10),
                            Point3D.construct(j + 10, i + 4, jZ + 10),
                            Color.YELLOW,
                            new Material(1, 1, 0.5, 0, 19)));
                    pScene.addGeometry(new Sphere(Point3D.construct(j, i, jZ), 3, Color.BLUE, new Material(0.5, 0.5, 0, 0, 3)));
            }
        }*/
        SpotLight pSpot = new SpotLight(
                new Color(255, 255, 255), new Vector(1, 0, -1),
                new Point3D(10, 0, 30),
                1, 0.001, 0.0001
        );
        PointLight pPointLight = new PointLight(
                new Color(255, 255, 255),
                new Point3D(100, 0, 45),
                1, 0.0001, 0.0001
        );

        pScene.addGeometry(new Sphere(Point3D.construct(100, 0, 0), 30, Color.RED, new Material(0.5, 0.5, 0, 0, 3)));
        //pScene.addGeometry(new Sphere(Point3D.construct(50, -100, -100), 30, Color.GREEN, new Material(0.5, 0.5, 0, 0, 3)));
        //pScene.addGeometry(new Sphere(Point3D.construct(100, -100, 100), 30, Color.DARK_GRAY, new Material(0.5, 0.5, 0, 0, 3)));
        //pScene.addGeometry(new Sphere(Point3D.construct(50, 100, 100), 30, Color.BLACK, new Material(0.5, 0.5, 0, 0, 3)));
        //pScene.addGeometry(new Sphere(Point3D.construct(100, 100, -100), 30, Color.ORANGE, new Material(0.5, 0.5, 0, 0, 3)));
        /*pScene.setGeometries(new Cube(
                Point3D.construct(70, -30, 30),
                Point3D.construct(70, 30, 30),
                Point3D.construct(70, 30, -30),
                Point3D.construct(70, -30, -30),
                Point3D.construct(130, -30, 30),
                Point3D.construct(130, 30, 30),
                Point3D.construct(130, 30, -30),
                Point3D.construct(130, -30, -30),
                Color.BLACK,
                Color.YELLOW,
                Color.YELLOW,
                Color.RED,
                Color.BLACK,
                Color.BLACK,
                new Material(1, 0.5, 0, 0.88, 8)));*/
       /* pScene.addLight(pSpot);
        pScene.addLight(pPointLight);

        ImageWriter pSceneWriter = new ImageWriter("Project", 1000, 1000, 1000, 1000);
        Render pRender = new Render(pScene, pSceneWriter);
        pRender.renderImage();
        pRender.getImageWriter().writeToimage();
/*
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
        ImageWriter sceneWriter2 = new ImageWriter("sphere in the spot lightX", 1000, 1000, 1000, 1000);
        RenderParalell myRender2 = new RenderParalell(myScene2, sceneWriter2);
        myRender2.renderImage();
        //myRender.printGrid(100);
        myRender2.getImageWriter().writeToimage();

        //Eile Mit Weile
        Geometries eileMitWeile = new Geometries();
        Scene eSceneEMW = new Scene("EileMitWeile");
        Camera eCamera = new Camera(Point3D.construct(-50, 0, 3), new Vector(1, 0, 0), new Vector(0, 1, 0));
        eSceneEMW.setCamera(eCamera);
        eSceneEMW.setDistance(750);
        eileMitWeile.addGeometry(new Quadrilateral(Point3D.construct(19, 9, 24), Point3D.construct(19, 24, 24), Point3D.construct(10, 9, 10), Point3D.construct(10, 24, 10), Color.DARK_GRAY, new Material(1, 1, 0.5, 0, 19)));
        eileMitWeile.addGeometry(new Quadrilateral(Point3D.construct(-1, -24, -6.5), Point3D.construct(-1, -7, -6.5), Point3D.construct(-10, -24, -19), Point3D.construct(-10, -7, -19), Color.DARK_GRAY, new Material(1, 1, 0.5, 0, 19)));
        eileMitWeile.addGeometry(new Quadrilateral(Point3D.construct(-1, 7, -6.5), Point3D.construct(-1, 24, -6.5), Point3D.construct(-10, 7, -19), Point3D.construct(-10, 24, -19), Color.DARK_GRAY, new Material(1, 1, 0.5, 0, 19)));
        eileMitWeile.addGeometry(new Sphere(Point3D.construct(18, -20, 20), 2.5, Color.BLUE, new Material(0.5, 0.5, 0, 0, 3)));
        eileMitWeile.addGeometry(new Sphere(Point3D.construct(18, -13, 20), 2.5, Color.BLUE, new Material(0.5, 0.5, 0, 0, 3)));
        eileMitWeile.addGeometry(new Sphere(Point3D.construct(14, -20, 13), 2.85, Color.BLUE, new Material(0.5, 0.5, 0, 0, 3)));
        eileMitWeile.addGeometry(new Sphere(Point3D.construct(14, -13, 13), 2.85, Color.BLUE, new Material(0.5, 0.5, 0, 0, 3)));
        eileMitWeile.addGeometry(new Sphere(Point3D.construct(18, 20, 20), 2.5, Color.RED, new Material(0.5, 0.5, 0, 0, 3)));
        eileMitWeile.addGeometry(new Sphere(Point3D.construct(18, 13, 20), 2.5, Color.RED, new Material(0.5, 0.5, 0, 0, 3)));
        eileMitWeile.addGeometry(new Sphere(Point3D.construct(14, 20, 13), 2.85, Color.RED, new Material(0.5, 0.5, 0, 0, 3)));
        eileMitWeile.addGeometry(new Sphere(Point3D.construct(14, 13, 13), 2.85, Color.RED, new Material(0.5, 0.5, 0, 0, 3)));
        eileMitWeile.addGeometry(new Sphere(Point3D.construct(-3, 19.75, -10), 2.5, Color.YELLOW, new Material(0.5, 0.5, 0, 0, 3)));
        eileMitWeile.addGeometry(new Sphere(Point3D.construct(-3, 11.75, -10), 2.5, Color.YELLOW, new Material(0.5, 0.5, 0, 0, 3)));
        eileMitWeile.addGeometry(new Sphere(Point3D.construct(-6.3, 19.75, -16.3), 2.85, Color.YELLOW, new Material(0.5, 0.5, 0, 0, 3)));
        eileMitWeile.addGeometry(new Sphere(Point3D.construct(-6.3, 11.75, -16.3), 2.85, Color.YELLOW, new Material(0.5, 0.5, 0, 0, 3)));
        eileMitWeile.addGeometry(new Sphere(Point3D.construct(-3, -19.75, -10), 2.5, Color.GREEN, new Material(0.5, 0.5, 0, 0, 3)));
        eileMitWeile.addGeometry(new Sphere(Point3D.construct(-3, -11.75, -10), 2.5, Color.GREEN, new Material(0.5, 0.5, 0, 0, 3)));
        eileMitWeile.addGeometry(new Sphere(Point3D.construct(-6.3, -19.75, -16.3), 2.85, Color.GREEN, new Material(0.5, 0.5, 0, 0, 3)));
        eileMitWeile.addGeometry(new Sphere(Point3D.construct(-6.3, -11.75, -16.3), 2.85, Color.GREEN, new Material(0.5, 0.5, 0, 0, 3)));
        eileMitWeile.addGeometry(new Quadrilateral(Point3D.construct(20, -25, 25), Point3D.construct(20, 25, 25), Point3D.construct(-10, -25, -20), Point3D.construct(-10, 25, -20), new Color(213, 43, 30), new Material(1, 1, 1, 0, 19)));
        eileMitWeile.addGeometry(new Quadrilateral(Point3D.construct(19, -24, 24), Point3D.construct(19, -9, 24), Point3D.construct(10, -24, 10), Point3D.construct(10, -9, 10), Color.DARK_GRAY, new Material(1, 1, 0.5, 0, 19)));
        eileMitWeile.addGeometry(new Sphere(Point3D.construct(6, 0, 0.5), 8, Color.MAGENTA, new Material(0, 0, 0, 0, 2)));
        eSceneEMW.setGeometries(eileMitWeile);
        ImageWriter eSceneWriter = new ImageWriter("Eile mit Weile", 1000, 1000, 1000, 1000);
        RenderParalell eRenderEMW = new RenderParalell(eSceneEMW, eSceneWriter);
        eRenderEMW.renderImage();
        eRenderEMW.getImageWriter().writeToimage();*/

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
        Camera camera2 = new Camera(new Point3D(-10, 0, 15),
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
    @Test
    public void FinalProject() {
        Plane p1 = new Plane(new Point3D(0, 0, -100), new Vector(0, 0, 1), new Color(0, 100, 0), new Material(1, 1, 0, 0, 20));
        Plane p2 = new Plane(new Point3D(25, 0, 0), new Vector(-1, 0, 0), new Color(42, 41, 70), new Material(0.3, 0, 0, 0, 20));
        Sphere middle2 = new Sphere(new Point3D(5, 10, 2),
                5, new Color(0, 20, 100), new Material(1, 1, 0, 0.5, 20));
        Sphere sun = new Sphere(new Point3D(80, 0, 60),
                40, new Color(255, 255, 150), new Material(0.5, 0.5, 1, 1, 20));
        Camera camera2 = new Camera(new Point3D(-1000, 0, 50),
                new Vector(1, 0, 0), new Vector(0, 1, 0));
        Tube tube = new Tube(new Ray(new Vector(0, 1, 1), new Point3D(40, 0, 0)), 10, new Color(0, 0, 255), new Material(1, 1, 1, 1, 20));
        Cylinder foot=new Cylinder(new Ray( new Vector(0,0,1),new Point3D(0,0,-100)),50,150,new Color(100,0,0),new Material(1,1,0,0,20));
        Scene myScene2 = new Scene("Biliard");
        myScene2.setDistance(1000);
        myScene2.setCamera(camera2);
        myScene2.setBackgroundColor(new Color(0, 0, 0));
        //myScene2.addGeometry(middle2);
        myScene2.addGeometry(foot);
        myScene2.addGeometry(p1);
        //myScene2.addGeometry(p2);
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
        ImageWriter sceneWriter2 = new ImageWriter("Biliard", 1000, 1000, 1000, 1000);
        Render myRender = new Render(myScene2, sceneWriter2);

        myRender.renderImage();

        //myRender.printGrid(100);
        myRender.getImageWriter().writeToimage();
    }
}