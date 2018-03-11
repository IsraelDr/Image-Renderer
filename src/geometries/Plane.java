package geometries;

import primitives.Point3D;
import primitives.Vector;

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
        this._vector=new Vector(_vector);
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
        return _point.equals(obj)&&_vector.equals(obj);
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

}
