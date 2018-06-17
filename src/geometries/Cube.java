package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;

public class Cube extends Geometries {

    // ***************** Constructors ********************** //

    /**
     * c'tor
     *
     * @param first          first coordinate
     * @param second         second coordinate
     * @param third          third coordinate
     * @param fourth         fourth coordinate
     * @param fifth          fifth coordinate
     * @param sixth          sixth coordinate
     * @param seventh        seventh coordinate
     * @param eighth         eighth coordinate
     * @param emissionTop    emission in the top
     * @param emissionBottom emission in the bottom
     * @param emissionBack   emission in the back side
     * @param emissionFront  emission in the back front
     * @param emissionLeft   emission in the left side
     * @param emissionRight  emission in the right side
     * @param material       material
     */
    public Cube(Point3D first, Point3D second, Point3D third, Point3D fourth, Point3D fifth, Point3D sixth, Point3D seventh, Point3D eighth, Color emissionFront, Color emissionBack, Color emissionRight, Color emissionLeft, Color emissionTop, Color emissionBottom, Material material) {
        addGeometry(new Triangle(first, second, third,  emissionFront, material));
        addGeometry(new Triangle(second, third, fourth, emissionFront, material));
        addGeometry(new Triangle(fourth, fifth, seventh, emissionBack, material));
        addGeometry(new Triangle(fifth, seventh, third, emissionBack, material));
        addGeometry(new Triangle(second,third,sixth, emissionRight, material));
        addGeometry(new Triangle(third,sixth,eighth, emissionRight, material));
        addGeometry(new Triangle(first,fourth,fifth, emissionLeft, material));
        addGeometry(new Triangle(fourth,fifth,seventh, emissionLeft, material));
    }

    /**
     * c'tor
     *
     * @param first    first coordinate
     * @param second   second coordinate
     * @param third    third coordinate
     * @param fourth   fourth coordinate
     * @param fifth    fifth coordinate
     * @param sixth    sixth coordinate
     * @param seventh  seventh coordinate
     * @param eighth   eighth coordinate
     * @param emission emission color
     * @param material material
     */
    public Cube(Point3D first, Point3D second, Point3D third, Point3D fourth, Point3D fifth, Point3D sixth, Point3D seventh, Point3D eighth,
                Color emission,
                Material material) {
        addGeometry(new Quadrilateral(first, second, third, fourth, emission, material));
        addGeometry(new Quadrilateral(second, third, sixth, eighth, emission, material));
        addGeometry(new Quadrilateral(first, fourth, fifth, seventh, emission, material));
        addGeometry(new Quadrilateral(first, second, seventh, eighth, emission, material));
        addGeometry(new Quadrilateral(third, fourth, fifth, sixth, emission, material));
        addGeometry(new Quadrilateral(fifth, sixth, seventh, eighth, emission, material));
    }
}
