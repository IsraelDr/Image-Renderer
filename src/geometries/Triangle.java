package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;

public class Triangle extends Plane {
    private Point3D _second;
    private Point3D _third;


    // ***************** Constructors ********************** //

    /**
     * Constructor
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
     * Lazy Constructor
     *
     * @param x1        x1
     * @param y1        y1
     * @param z1        z1
     * @param x2        x2
     * @param y2        y2
     * @param z2        z2
     * @param x3        x3
     * @param y3        y3
     * @param z3        z3
     * @param red       Red value
     * @param green     Green value
     * @param blue      Blue value
     * @param Kd        Diffuse
     * @param Ks        Specular
     * @param Kr        Reflected
     * @param Kt        Transparent
     * @param shininess Shininess
     */
    public Triangle(double x1, double y1, double z1, double x2, double y2, double z2, double x3, double y3, double z3, int red, int green, int blue, double Kd, double Ks, double Kr, double Kt, int shininess) {
        super(Point3D.construct(x1, y1, z1), Point3D.construct(x2, y2, z2), Point3D.construct(x3, y3, z3), new Color(red, green, blue), new Material(Kd, Ks, Kr, Kt, shininess));
        _second = Point3D.construct(x2, y2, z2);
        _third = Point3D.construct(x3, y3, z3);
    }

    /**
     * Lazy Constructor with explicit color
     *
     * @param x1        x1
     * @param y1        y1
     * @param z1        z1
     * @param x2        x2
     * @param y2        y2
     * @param z2        z2
     * @param x3        x3
     * @param y3        y3
     * @param z3        z3
     * @param color     The color
     * @param Kd        Diffuse
     * @param Ks        Specular
     * @param Kr        Reflected
     * @param Kt        Transparent
     * @param shininess Shininess
     */
    public Triangle(double x1, double y1, double z1, double x2, double y2, double z2, double x3, double y3, double z3, Color color, double Kd, double Ks, double Kr, double Kt, int shininess) {
        super(Point3D.construct(x1, y1, z1), Point3D.construct(x2, y2, z2), Point3D.construct(x3, y3, z3), color, new Material(Kd, Ks, Kr, Kt, shininess));
        _second = Point3D.construct(x2, y2, z2);
        _third = Point3D.construct(x3, y3, z3);
    }

    /**
     * Lazy Constructor with explicit color
     *
     * @param x1       x1
     * @param y1       y1
     * @param z1       z1
     * @param x2       x2
     * @param y2       y2
     * @param z2       z2
     * @param x3       x3
     * @param y3       y3
     * @param z3       z3
     * @param color    The color
     * @param material The Material
     */
    public Triangle(double x1, double y1, double z1, double x2, double y2, double z2, double x3, double y3, double z3, Color color, Material material) {
        super(Point3D.construct(x1, y1, z1), Point3D.construct(x2, y2, z2), Point3D.construct(x3, y3, z3), color, material);
        _second = Point3D.construct(x2, y2, z2);
        _third = Point3D.construct(x3, y3, z3);
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
        List<Point3D> list = new ArrayList<>();
        Vector ca = this._third.vectorSubstract(this._point);
        Vector ba = this._second.vectorSubstract(this._point);
        Vector temp1 = ca.vectorProduct(ba.vectorProduct(ca)).multipliedbyScalar(ba.ScalarProduct(ba));
        Vector temp2 = (ba.vectorProduct(ca)).vectorProduct(ba).multipliedbyScalar(ca.ScalarProduct(ca));
        Point3D m = this._point.addVectorToPoint((temp1.add(temp2)).multipliedbyScalar(1 / (2 * Math.pow(ba.vectorProduct(ca).size(), 2))));
        double rad = m.distance(this._point);
        list.add(m.addVectorToPoint(to.multipliedbyScalar(-rad)).addVectorToPoint(up.multipliedbyScalar(rad)).addVectorToPoint(right.multipliedbyScalar(-rad)));
        list.add(m.addVectorToPoint(to.multipliedbyScalar(rad)).addVectorToPoint(up.multipliedbyScalar(rad)).addVectorToPoint(right.multipliedbyScalar(-rad)));
        list.add(m.addVectorToPoint(to.multipliedbyScalar(-rad)).addVectorToPoint(up.multipliedbyScalar(rad)).addVectorToPoint(right.multipliedbyScalar(rad)));
        list.add(m.addVectorToPoint(to.multipliedbyScalar(-rad)).addVectorToPoint(up.multipliedbyScalar(-rad)).addVectorToPoint(right.multipliedbyScalar(-rad)));
        return list;
    }
}
