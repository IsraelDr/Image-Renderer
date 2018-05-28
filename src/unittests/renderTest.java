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



        Sphere middle2 = new Sphere(new Point3D(50, 0, 0),
                49,new Color(0,20,100),new Material(1,1,20));

        Camera camera2 = new Camera(new Point3D(0, 0, 0),

                new Vector(1, 0, 0), new Vector(0, 1, 0));
        Scene myScene2 = new Scene("sphere in the spot light");
        myScene2.setDistance(50);
        myScene2.setCamera(camera2);
        myScene2.setBackgroundColor(new Color(0, 0, 0));
        myScene2.addGeometry(middle2);
        myScene2.setAmbientlight(new Color(20,20,20), 0.1);

        PointLight mySpotLight = new PointLight(new Color (255,255,255),
                new Point3D(-0.5,-2,-2),
                1,0.0001,0.001
                );
        /*SpotLight mySpotLight = new SpotLight(
                new Color (255,255,255),new Vector(1,1,0),
                new Point3D(-0.5,-2,0),
                1,0.001,0.001
                );*/
        //DirectionalLight dire2=new DirectionalLight(new Color(250,255,250),new Vector(0,0,-1));
        //myScene2.addLight(dire2);
        myScene2.addLight(mySpotLight);

        ImageWriter sceneWriter2 = new ImageWriter("sphere in the spot light",1000,1000,1000,1000);
        Render myRender2 = new Render(myScene2,sceneWriter2);

        myRender2.renderImage();

        //myRender.printGrid(100);
        myRender2.getImageWriter().writeToimage();


    }
}