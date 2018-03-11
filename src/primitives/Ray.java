package primitives;

/**
 * class defines Ray and contains Vector and start point
 */
public class Ray {
    protected Point3D _point;
    protected Vector _vector;

    // ***************** Constructors ********************** //
    /**
     * ctor
     * @param vector
     * @param point
     */
    public Ray(Vector vector,Point3D point){
        this._point=new Point3D(point);
        this._vector=new Vector(_vector);

    }
    public Ray(Ray temp){
        new Ray(temp._vector,temp._point);
    }

    // ***************** Getters/Setters ********************** //

    public Point3D get_point() {
        return _point;
    }

    public Vector get_vector() {
        return _vector;
    }
    // ***************** Administration  ******************** //


    @Override
    public String toString() {
        return ""+_point.toString()+"->"+_vector.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj)
            return true;
        if (!(obj instanceof Ray)||obj==null)
            return false;
        return _point.equals(((Ray) obj)._point)&&_vector.equals(((Ray) obj)._vector);
    }

    // ***************** Operations ******************** //
}
