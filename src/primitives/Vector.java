package primitives;


public class Vector {
    protected Point3D point;

    // ***************** Constructors ********************** //

    /**
     * ctor
     * @param x
     * @param y
     * @param z
     */
    public Vector(double x, double y, double z){
        this.point = new Point3D(x,y,z);
    }

    /**
     * Copy ctor
     * @param temp - object to copy
     */
    public Vector(Point3D temp){
        this.point=new Point3D(temp);
    }

    // ***************** Getters/Setters ********************** //

    public Point3D getPoint() {
        return point;
    }

    // ***************** Administration  ******************** //

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Point3D))
            return false;
        return this.point.equals((Point3D)obj);
    }

    @Override
    public String toString() {
        return this.point.toString();
    }
    // ***************** Operations ******************** //
    public Vector add(Vector temp){
        return null;
    }
    public Vector multipleScalar(){
        return null;
    }
}
