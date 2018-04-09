package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Triangle extends Plane {
    protected Point3D _second;
    protected Point3D _third;


    // ***************** Constructors ********************** //

    /**
     * constuctor
     * @param first The first coordinate
     * @param second The second coordinate
     * @param third The third coordinate
     */
    public Triangle(Point3D first, Point3D second, Point3D third){
        super(first,second,third);
        _second = new Point3D(second);
        _third = new Point3D(third);
    }

    /**
     * copy constructor
     * @param other the source
     */
    public Triangle(Triangle other){
        super(other);
        _second = new Point3D(other._second);
        _third = new Point3D(other._third);
    }

// ***************** Getters/Setters ********************** //

    public Point3D get_second() {
        return _second;
    }

    public Point3D get_third() {
        return _third;
    }

    // ***************** Administration  ******************** //

    @Override
    public String toString() {
        return super.toString()+" "+_second.toString()+" "+_third.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj)
            return true;
        if (!(obj instanceof Triangle)||obj==null)
            return false;
        return super.equals(obj)&&
                (_second.equals(((Triangle) obj)._second)&&_third.equals(((Triangle) obj)._third))||
                (_second.equals(((Triangle) obj)._third)&&_third.equals(((Triangle) obj)._second));
    }
    // ***************** Operations ******************** //

    /**
     * returns NULL
     * @param temp
     * @return NULL
     */

}
