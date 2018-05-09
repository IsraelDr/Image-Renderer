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
        return this.toString()+", Height: "+this._height;
    }




}
