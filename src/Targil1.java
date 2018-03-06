import primitives.*;
import geometries.*;

import java.util.prefs.Preferences;

public class Targil1 {

    public static void main(String[] args) {
        Vector vector1 = new Vector(1,2,3);
        Vector vector2 = new Vector(6.987,2.98,10);
        Vector vector3 = new Vector(123,0,-3);
        Vector vector4 = new Vector(-0.5,-2,-7);
        Vector vector5 = new Vector(-1.1,2,6);
        Vector vector6 = new Vector(0,0,0);
        Vector vector7 = new Vector(1,2,3);
        System.out.println(vector1.equals(vector7));
        System.out.println(vector1.toString());
        System.out.println(vector2.toString());
        System.out.println("ScalarProduct" + vector1.ScalarProduct(vector2));
        System.out.println("VectorProduct" + vector1.VectorProduct(vector2));
        System.out.println("vector5.multipliedbyScalar" +vector5.multipliedbyScalar(8.5));


    }
}
