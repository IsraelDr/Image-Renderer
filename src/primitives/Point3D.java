package primitives;




public class Point3D extends Point2D {

    private Coordinate _z;

    // ***************** Constructors ********************** //
    public Point3D(double x, double y, double z){
        super(x,y);
        this._z = new Coordinate(z);
    }

    /**
     * copy constructor
     * @param other The Point3D that will be copied

     */
    public Point3D(Point3D other){
        super(other);
        this._z = new Coordinate(other._z);

    }


    // ***************** Getters/Setters ********************** //

    public double get_z() {
        return _z.get_cord();
    }


    // ***************** Administration  ******************** //
    /**
     *
     * @return Returns a string in this format: (x,y,z)
     */
    @Override
    public String toString() {
        return "{"+this._x.toString()+", "+this._y.toString()+", "+_z.toString()+"}" ;
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj)
            return true;
        if (!(obj instanceof Point3D)/*||obj==null*/)
            return false;
        return  super.equals(obj)&& this._z.equals(((Point3D) obj)._z);
    }


    // ***************** Operations ********************
    /**
     *Calculate the distance between two points in R3
     * @param other The secont point value
     * @return Returns the distance between the two points
     */

    public double distance(Point3D other){
        double x= this.get_x() - other.get_x();
        double y= this.get_y() - other.get_y();
        double z= this.get_z() - other.get_z();
        return Math.sqrt(Math.pow(x,2)+Math.pow(y,2)+Math.pow(z,2));
    }


    /**
     *
     * @param vector The end point from the vector will be calculate
     * @return Returns the sum of the current point with the end point of the vector
     */
    public Point3D addVectorToPoint(Vector vector){
        return new Point3D(get_x()+vector._point.get_x(),get_y()+vector._point.get_y(),+get_z()+vector._point.get_z());
    }

    /**
     *
     * @param other The Coordinates that will be substracted from the current Coordinates
     * @return Returns a new vector
     */
    public Vector vectorSubstract(Point3D other){
        double x = get_x()- other.get_x();
        double y =get_y()- other.get_y();
        double z = get_z()-other.get_z();
        return new Vector(x,y,z);
    }

}


