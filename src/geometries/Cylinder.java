package geometries;
import primitives.*;
public class Cylinder extends Tube {
    double _height;

    // ***************** Constructors ********************** //

    /**
     * ctor
     * @param _ray The ray value
     * @param _radius The radius value
     * @param height The height of the cylinder
     */
    public Cylinder(Ray _ray, double _radius, double height)
    {
        super(_ray,_radius);
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
     * equals for Cylinder
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Cylinder && obj != null)
          return super.equals(obj)&&this._height==((Cylinder) obj)._height;
        return false;
    }

    /**
     * To string for Cylinder
     * @return String
     */
    @Override
    public String toString() {
        return this.toString()+", Height: "+this._height;
    }




}
