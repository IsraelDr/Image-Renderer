package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;

public class Triangle extends Plane {
    private Point3D _second;
    private Point3D _third;


    // ***************** Constructors ********************** //

    /**
     * constuctor
     *
     * @param first  The first coordinate
     * @param second The second coordinate
     * @param third  The third coordinate
     */
    public Triangle(Point3D first, Point3D second, Point3D third, Color emission, Material material) {
        super(first, second, third, emission, material);
        _second = new Point3D(second);
        _third = new Point3D(third);
    }

    /**
     * copy constructor
     *
     * @param other the source
     */
    public Triangle(Triangle other) {
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
        return super.toString() + " " + _second.toString() + " " + _third.toString();
    }
    // ***************** Operations ******************** //

    /**
     * @param ray ray
     * @return arraylist of intersections
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> points = super.findIntersections(ray);
        Vector v1 = get_point().vectorSubstract(ray.get_point());
        Vector v2 = _second.vectorSubstract(ray.get_point());
        Vector v3 = _third.vectorSubstract(ray.get_point());
        Vector N1 = v1.vectorProduct(v2).NormalVector();
        Vector N2 = v2.vectorProduct(v3).NormalVector();
        Vector N3 = v3.vectorProduct(v1).NormalVector();
        if ((ray.get_vector().ScalarProduct(N1) > 0 && ray.get_vector().ScalarProduct(N2) > 0 && ray.get_vector().ScalarProduct(N3) > 0) ||
                (ray.get_vector().ScalarProduct(N1) < 0 && ray.get_vector().ScalarProduct(N2) < 0 && ray.get_vector().ScalarProduct(N3) < 0))
            ;
        else
            points.clear();
        return points;
    }

    @Override
    public List<Point3D> getBoudaryPoints(Vector to, Vector right, Vector up) {
        List<Point3D> list= new ArrayList<>();
        Vector ca=this._third.vectorSubstract(this._point);
        Vector ba=this._second.vectorSubstract(this._point);
        Vector temp1=ca.vectorProduct(ba.vectorProduct(ca)).multipliedbyScalar(ba.ScalarProduct(ba));
        Vector temp2=(ba.vectorProduct(ca)).vectorProduct(ba).multipliedbyScalar(ca.ScalarProduct(ca));
        Point3D m=this._point.addVectorToPoint((temp1.add(temp2)).multipliedbyScalar(1/(2*Math.pow(ba.vectorProduct(ca).size(),2))));
        double rad=m.distance(this._point);
        list.add(m.addVectorToPoint(to.multipliedbyScalar(-rad)).addVectorToPoint(up.multipliedbyScalar(rad)).addVectorToPoint(right.multipliedbyScalar(-rad)));
        list.add(m.addVectorToPoint(to.multipliedbyScalar(rad)).addVectorToPoint(up.multipliedbyScalar(rad)).addVectorToPoint(right.multipliedbyScalar(-rad)));
        list.add(m.addVectorToPoint(to.multipliedbyScalar(-rad)).addVectorToPoint(up.multipliedbyScalar(rad)).addVectorToPoint(right.multipliedbyScalar(rad)));
        list.add(m.addVectorToPoint(to.multipliedbyScalar(-rad)).addVectorToPoint(up.multipliedbyScalar(-rad)).addVectorToPoint(right.multipliedbyScalar(-rad)));
        return list;
    }
}
