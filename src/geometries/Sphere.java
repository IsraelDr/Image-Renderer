package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * class defines sphere with center and radius
 */
public class Sphere extends RadialGeometry {
    protected Point3D _point;

    // ***************** Constructors ********************** //
    public Sphere(Point3D point, double rad,Color emission){
        super(rad,emission);
        this._point=new Point3D(point);
    }
    public Sphere(Sphere temp, Color emission){
        super(temp._radius,emission);
        this._point=new Point3D(temp._point);
    }

    // ***************** Getters/Setters ********************** //
    public Point3D get_point() {
        return _point;
    }
    // ***************** Administration  ******************** //
    @Override
    public String toString() {
        return super.toString()+" "+ _point.toString();
    }

    // ***************** Operations ******************** //
    @Override
    public Vector getNormal(Point3D temp) {
        return new Vector(temp.vectorSubstract(_point).NormalVector());
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> list = new ArrayList<Point3D>();
        Vector u = this._point.vectorSubstract(ray.get_point());
        double tm = ray.get_vector().ScalarProduct(u);
        double d = Math.sqrt(Math.pow(u.size(),2)-Math.pow(tm,2));
        if (d>this.get_radius())
            return list;
        double th = Math.sqrt(Math.pow(get_radius(),2)-Math.pow(d,2));
        double t1 = tm - th;
        double t2 = tm + th;
        if (t1>0 && t2 >0){
            list.add(new Point3D(ray.get_point().addVectorToPiont(ray.get_vector().multipliedbyScalar(t1))));
            list.add(new Point3D(ray.get_point().addVectorToPiont(ray.get_vector().multipliedbyScalar(t2))));
            return list;
        }
        if (t1>0 ) {
            list.add(new Point3D(ray.get_point().addVectorToPiont(ray.get_vector().multipliedbyScalar(t1))));
            return list;
        }
        if (t2 >0) {
            list.add(new Point3D(ray.get_point().addVectorToPiont(ray.get_vector().multipliedbyScalar(t2))));
            return  list;
        }

        return list;
    }

}
