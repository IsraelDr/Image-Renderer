package primitives;


public class Vector {
    protected Point3D _point;

    // ***************** Constructors ********************** //

    /**
     * ctor
     * @param x
     * @param y
     * @param z
     */
    public Vector(double x, double y, double z){
        this._point = new Point3D(x,y,z);
    }

    /**
     * Copy ctor
     * @param temp - object to copy
     */
    public Vector(Vector temp){
        this._point=new Point3D(temp._point);
    }

    // ***************** Getters/Setters ********************** //

    public Point3D getPoint() {
        return _point;
    }

    // ***************** Administration  ******************** //

    @Override
    public boolean equals(Object obj) {
        if(this==obj)
            return true;
        if (!(obj instanceof Vector)||obj==null)
            return false;
        return this._point.equals(((Vector) obj)._point);
    }

    @Override
    public String toString() {
        return this._point.toString();
    }
    // ***************** Operations ******************** //

    /**
     * add same like substract with minus vector
     * @param temp
     * @return
     */
    public Vector add(Vector temp){
        return (this._point.vectorSubstract(temp.multipliedbyScalar(-1)._point));
    }

    /**
     * returns copy of vector multiplied by scalar
     * @param scalar
     * @return
     */
    public Vector multipliedbyScalar(double scalar){
        return new Vector(_point.get_x()*scalar,_point.get_y()*scalar,_point.get_z()*scalar);
    }

    /**
     * ScalarProduct
     * @param temp
     * @return
     */
    public double ScalarProduct(Vector temp){
        return _point.get_x()*temp._point.get_x()+_point.get_y()*temp._point.get_y()+_point.get_z()*temp._point.get_z();
    }

    /**
     * product of two vectors
     * @param temp
     * @return
     */
    public Vector VectorProduct(Vector temp){
        double i = this._point.get_y()*temp._point.get_z()-temp._point.get_y()*this._point.get_z();
        double j = temp._point.get_x()*this._point.get_z()-this._point.get_x()*temp._point.get_z();
        double k = this._point.get_x()*temp._point.get_y()-temp._point.get_x()*this._point.get_y();
        return new Vector(i,j,k);
    }

    /**
     * size of vector
     * assumption: all the vectors are from (0,0,0)
     * @return
     */
    public double size(){
        return this._point.distance(new Point3D(0,0,0));
    }

    /**
     *
     * @return - vector with size equals 1
     */
    public Vector NormalVector(){
        double size=this.size();
        if(size==0){
            return this;
        }
        return this.multipliedbyScalar(1/size);
    }
}
