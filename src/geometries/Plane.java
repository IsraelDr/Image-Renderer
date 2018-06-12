package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that defines a Plane. It contains a Point3D and a Vector
 */
public class Plane extends Geometry {
    private Point3D _point;
    private Vector _vector;


    // ***************** Constructors ********************** //

    /**
     * Constructor
     * @param point
     * @param vector
     */
    public Plane(Point3D point, Vector vector, Color emission,Material material){
        super(emission,material);
        this._point=new Point3D(point);
        this._vector=new Vector(vector.NormalVector());
    }

    /**
     * ctor Plane three points
     * @param p1
     * @param p2
     * @param p3
     */
    public Plane(Point3D p1, Point3D p2, Point3D p3, Color emission, Material material){
        super(emission,material);
        try {
            if(p1.equals(p2)||p1.equals(p3)||p2.equals(p3))
                throw new Exception("Two of the choosen points are equal!");
            if(p1.vectorSubstract(p2).NormalVector().equals(p1.vectorSubstract(p3).NormalVector()))
                throw new Exception("The points are on one line!");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return;
        }
        this._point=new Point3D(p1);
        Vector norm=new Vector(((p2.vectorSubstract(p1)).vectorProduct((p3.vectorSubstract(p1))).NormalVector()));
        this._vector=new Vector(norm);
    }

    /**
     * copy ctor
     * @param temp
     */
    public Plane(Plane temp){
        super(temp);
        this._point=new Point3D(temp._point);
        this._vector=new Vector(temp._vector);
    }
    // ***************** Getters/Setters ********************** //

    public Point3D get_point() {
        return _point;
    }
    public Vector get_vector() {
        return _vector;
    }
    // ***************** Administration  ******************** //
    @Override
    public String toString() {
        return _point.toString()+" "+_vector.toString();
    }
    // ***************** Operations ******************** //

    @Override

    public Vector getNormal(Point3D temp) {
        return get_vector();
    }

    @Override
    /**
     * finds all intersections of ray with plane
     */
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> points=new ArrayList<Point3D>();
        if(ray.get_vector().ScalarProduct(this._vector)==0)
            return points;
        double t=(this._vector.ScalarProduct(new Vector(this._point.vectorSubstract(ray.get_point()))))/
                this._vector.ScalarProduct(ray.get_vector());
        if(t>0)
            points.add(ray.get_point().addVectorToPoint(ray.get_vector().multipliedbyScalar(t)));
        return points;
    }
}
