package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;

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
        return (_point.equals(((Triangle) obj)._point)&&_second.equals(((Triangle) obj)._second)&&_third.equals(((Triangle) obj)._third))||
                (_point.equals(((Triangle) obj)._point)&&_second.equals(((Triangle) obj)._third)&&_third.equals(((Triangle) obj)._second))||
                (_point.equals(((Triangle) obj)._second)&&_second.equals(((Triangle) obj)._third)&&_third.equals(((Triangle) obj)._point))||
                (_point.equals(((Triangle) obj)._second)&&_second.equals(((Triangle) obj)._point)&&_third.equals(((Triangle) obj)._third))||
                (_point.equals(((Triangle) obj)._third)&&_second.equals(((Triangle) obj)._second)&&_third.equals(((Triangle) obj)._point))||
                (_point.equals(((Triangle) obj)._third)&&_second.equals(((Triangle) obj)._point)&&_third.equals(((Triangle) obj)._second));

    }
    // ***************** Operations ******************** //

    /**
     *
     * @param ray
     * @return arraylist of intersections
     */
    @Override
    public ArrayList<Point3D> findIntersections(Ray ray) {
        ArrayList<Point3D> points= super.findIntersections(ray);
        Vector v1=_point.vectorSubstract(ray.get_point());
        Vector v2=_second.vectorSubstract(ray.get_point());
        Vector v3=_third.vectorSubstract(ray.get_point());
        Vector N1=v1.vectorProduct(v2).NormalVector();
        Vector N2=v2.vectorProduct(v3).NormalVector();
        Vector N3=v3.vectorProduct(v1).NormalVector();
        if((v1.ScalarProduct(N1)>0&&v2.ScalarProduct(N2)>0&&v3.ScalarProduct(N3)>0)||
                (v1.ScalarProduct(N1)<0&&v2.ScalarProduct(N2)<0&&v3.ScalarProduct(N3)<0))
            return points;
        else
            return null;
    }
}
