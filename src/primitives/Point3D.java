package primitives;

public class Point3D extends Point2D {

    protected Coordinate _z;

    // ***************** Constructors ********************** //
    public Point3D(double x, double y, double z){
        super(x,y);
        _z = new Coordinate(z);
    }

    public Point3D(Point3D other){
        super(other);
        _z = new Coordinate(other._z);

    }
}
