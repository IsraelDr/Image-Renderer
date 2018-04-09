package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;

/**
 * Class that defines a Plane. It contains a Point3D and a Vector
 */
public class Plane extends Geometry {
    protected Point3D _point;
    protected Vector _vector;


    // ***************** Constructors ********************** //

    /**
     * Constructor
     * @param point
     * @param vector
     */
    public Plane(Point3D point, Vector vector){
        super();
        this._point=new Point3D(point);
        this._vector=new Vector(vector.NormalVector());
    }


    public Plane(Point3D p1, Point3D p2,Point3D p3){
        super();
        try {
            if(p1.equals(p2)||p1.equals(p3)||p2.equals(p3))
                throw new Exception("Two of the choosen points are equal!");
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
    public boolean equals(Object obj) {
        if(this==obj)
            return true;
        if (!(obj instanceof Plane)||obj==null)
            return false;
        return _point.vectorSubstract(((Plane) obj)._point).ScalarProduct(_vector)==0&&_vector.equals(((Plane) obj)._vector);
    }

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
    public ArrayList<Point3D> findIntersections(Ray ray) {
        if(ray.get_vector().ScalarProduct(this._vector)==0)
            return null;
        double t=(this._vector.ScalarProduct(new Vector(this._point.vectorSubstract(ray.get_point()))))/
                this._vector.ScalarProduct(ray.get_vector());
        ArrayList<Point3D> points=new ArrayList<Point3D>();
        points.add(ray.get_point().addVectorToPiont(ray.get_vector().multipliedbyScalar(t)));
        return points;
    }
}
