package unittests;

import geometries.Tube;
import org.junit.Test;
import primitives.*;

import java.util.List;

import static org.junit.Assert.assertFalse;

public class TubeTest {


    @Test
    public void findIntersections() {
        Ray v =new Ray(new Vector(1,0,0),new Point3D(0,0,0));
        Tube a=new Tube(new Ray(new Vector(0,0,1),new Point3D(100,0,0)),50,new Color(30,30,30),new Material(1,1,20));
        List<Point3D> b=a.findIntersections(v);
        assertFalse("Is empty",b.isEmpty());
    }
}