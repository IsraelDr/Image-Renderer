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

    /**
     * add same like substract with minus vector
     * @param temp
     * @return
     */
    public Vector add(Vector temp){
        return (this.point.vectorSubstract(temp.multipliedbyScalar(-1).point));
    }

    /**
     * returns copy of vector multiplied by scalar
     * @param scalar
     * @return
     */
    public Vector multipliedbyScalar(double scalar){
        return new Vector(point.get_x()*scalar,point.get_y()*scalar,point.get_z()*scalar);
    }

    /**
     * ScalarProduct
     * @param temp
     * @return
     */
    public double ScalarProduct(Vector temp){
        return point.get_x()*temp.point.get_x()+point.get_y()*temp.point.get_y()+point.get_z()*temp.point.get_z();
    }

    /**
     * product of two vectors
     * @param temp
     * @return
     */
    public Vector VectorProduct(Vector temp){
        double i = this.point.get_y()*temp.point.get_z()-temp.point.get_y()*this.point.get_z();
        double j = temp.point.get_x()*this.point.get_z()-this.point.get_x()*temp.point.get_z();
        double k = this.point.get_x()*temp.point.get_y()-temp.point.get_x()*this.point.get_y();
        return new Vector(i,j,k);
    }

    /**
     * size of vector
     * assumption: all the vectors are from (0,0,0)
     * @return
     */
    public double size(){
        return this.point.distance(new Point3D(0,0,0));
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
