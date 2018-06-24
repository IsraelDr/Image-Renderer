package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;

/**
 * represents Tube with Ray and radius
 */
public class Tube extends RadialGeometry {
    protected Ray _ray;

    // ***************** Constructors ********************** //

    /**
     * ctor
     * @param ray Ray
     * @param rad Radius
     */
    public Tube(Ray ray,double rad,Color emission,Material material){
        super(rad,emission,material);
        this._ray=new Ray(ray);
    }

    /**
     * copy ctor
     * @param temp The Tube that will be copied
     */
    public Tube(Tube temp){
        super(temp);
        this._ray=new Ray(temp.getRay());
    }
    // ***************** Getters/Setters ********************** //

    public Ray getRay() {
        return _ray;
    }
    // ***************** Administration  ******************** //
    @Override
    public String toString() {
        return this._ray.toString();
    }
    // ***************** Operations ******************** //

    /**
     * get normal to tube at point on geometrys
     * @param temp The Point
     * @return Returns the normal
     */
    @Override
    public Vector getNormal(Point3D temp) {
        Point3D pointBase =this._ray.get_point();
        Vector c=temp.vectorSubstract(pointBase);
        Vector a=this._ray.get_vector().multipliedbyScalar(c.ScalarProduct(this._ray.get_vector()));
        Vector b=c.getPoint().vectorSubstract(a.getPoint());
        return b.NormalVector();

    }
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> list = new ArrayList<>();
        Vector n=ray.get_vector().add(this._ray.get_vector().multipliedbyScalar(Math.abs(this._ray.get_vector().ScalarProduct(ray.get_vector()))));
        double A=n.ScalarProduct(n);
        Vector m=this._ray.get_point().vectorSubstract(ray.get_point());
        Vector k=m.add(this._ray.get_vector().multipliedbyScalar(Math.abs(this._ray.get_vector().ScalarProduct(m)))).multipliedbyScalar(-1);
        double B=2*(n.ScalarProduct(k));
        double C=k.ScalarProduct(k)-Math.pow(this._radius,2);
        double delta=Math.pow(B,2)-4*A*C;
        if(delta<0||A==0)
            return list;
        if(delta==0) {
            double t=-B/(2*A);
            list.add(ray.get_point().addVectorToPoint(ray.get_vector().multipliedbyScalar(t)));
        }
        else {
            double deltasquare=Math.sqrt(delta);
            double t1=(-B+deltasquare)/(2*A);
            double t2=(-B-deltasquare)/(2*A);
            list.add(ray.get_point().addVectorToPoint(ray.get_vector().multipliedbyScalar(t1)));
            list.add(ray.get_point().addVectorToPoint(ray.get_vector().multipliedbyScalar(t2)));
        }
        return list;
    }

    @Override
    public List<Point3D> getBoudaryPoints(Vector to,Vector right,Vector up) {
        return new ArrayList<>();
    }


}
