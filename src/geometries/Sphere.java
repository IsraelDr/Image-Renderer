package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * class defines sphere with center and radius
 */
public class Sphere extends RadialGeometry {
    protected Point3D _point;

    // ***************** Constructors ********************** //
    public Sphere(Point3D point, double rad){
        super(rad);
        this._point=new Point3D(point);
    }
    public Sphere(Sphere temp){
        super(temp._radius);
        this._point=new Point3D(temp._point);
    }

    // ***************** Getters/Setters ********************** //
    public Point3D get_point() {
        return _point;
    }
    // ***************** Administration  ******************** //

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj)&&_point.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString()+" "+ _point.toString();
    }

    // ***************** Operations ******************** //
    @Override
    public Vector getNormal(Point3D temp) {
        return null;
    }
}
