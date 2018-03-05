package primitives;

import java.util.Vector;

public class Point3D extends Point2D {

    protected Coordinate _z;

    // ***************** Constructors ********************** //
    public Point3D(double x, double y, double z){
        super(x,y);
        _z = new Coordinate(z);
    }

    /**
     * copy constructor
     * @param other The Point3D that will be copied

     */
    public Point3D(Point3D other){
        super(other);
        _z = new Coordinate(other._z);

    }


    // ***************** Getters/Setters ********************** //

    public double get_z() {
        return _z.get_cord();
    }


    // ***************** Administration  ******************** //
    public Vector vectorSubstract(Point3D other){
        return new Vector(get_x()-other.get_x(),get_y()-other.get_y(),get_z()-other.get_z());
    }

    public double distance
}
