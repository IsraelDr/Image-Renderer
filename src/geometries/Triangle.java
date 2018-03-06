package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Triangle extends Geometry {

    Point3D _first;
    Point3D _second;
    Point3D _third;

    // ***************** Constructors ********************** //

    /**
     * constuctor
     * @param first The first coordinate
     * @param second The second coordinate
     * @param third The third coordinate
     */
    public Triangle(Point3D first, Point3D second, Point3D third){
        super();
        _first = new Point3D(first);
        _second = new Point3D(second);
        _third = new Point3D(third);

    }

    /**
     * copy constructor
     * @param other the source
     */
    public Triangle(Triangle other){
        super(other);
        _first = new Point3D(other._first);
        _second = new Point3D(other._second);
        _third = new Point3D(other._third);
    }

// ***************** Getters/Setters ********************** //

    public Point3D get_first() {
        return _first;
    }

    public Point3D get_second() {
        return _second;
    }

    public Point3D get_third() {
        return _third;
    }

    // ***************** Operations ******************** //

    /**
     * returns NULL
     * @param temp
     * @return NULL
     */
    @Override
    public Vector getNormal(Point3D temp) {
        return null;
    }
}
