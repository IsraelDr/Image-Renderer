package geometries;
import primitives.*;

import java.util.ArrayList;
import java.util.List;

public class Cylinder extends Tube {
    double _height;
    static double k=0;

    // ***************** Constructors ********************** //

    /**
     * ctor
     * @param _ray The ray value
     * @param _radius The radius value
     * @param height The height of the cylinder
     */
    public Cylinder(Ray _ray, double _radius, double height,Color emission,Material material)
    {
        super(_ray,_radius,emission,material);
        _height = height;
    }

    public Cylinder(Cylinder other){
        super(other);
        _height = other._height;
    }

    // ***************** Getters/Setters ********************** //

    /**
     *
     * @return The heighst of the Cylinder
     */
    public double get_height() {
        return _height;
    }

    // ***************** Administration  ******************** //


    /**
     * To string for Cylinder
     * @return String
     */
    @Override
    public String toString() {
        return super.toString()+", Height: "+this._height;
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> list= super.findIntersections(ray);
        List<Point3D> temp=new ArrayList<>();
        for (Point3D p:list) {
            double m=p.vectorSubstract(ray.get_point()).ScalarProduct(this._ray.get_vector().NormalVector());
            if(m>=0&&m<=this._height)
                temp.add(p);
        }
        return temp;
    }


}
