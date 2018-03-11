package geometries;

import primitives.*;

/**
 * represents Tube with Ray and radius
 */
public class Tube extends RadialGeometry {
    protected Ray _ray;

    // ***************** Constructors ********************** //

    /**
     * ctor
     * @param ray
     * @param rad
     */
    public Tube(Ray ray,double rad){
        super(rad);
        this._ray=new Ray(ray);
    }

    /**
     * copy ctor
     * @param temp
     */
    public Tube(Tube temp){
        super(temp);
        this._ray=new Ray(temp.get_ray());
    }
    // ***************** Getters/Setters ********************** //

    public Ray get_ray() {
        return _ray;
    }
    // ***************** Administration  ******************** //

    @Override
    public boolean equals(Object obj) {
        return this._ray.equals(obj);
    }

    @Override
    public String toString() {
        return this._ray.toString();
    }
    // ***************** Operations ******************** //

    /**
     * get normal to tube at point on geometry
     * @param temp
     * @return
     */
    @Override
    public Vector getNormal(Point3D temp) {
        return null;
    }
}
