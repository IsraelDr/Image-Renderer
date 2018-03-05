package primitives;

public class Point2D{
    protected Coordinate _x;
    protected Coordinate _y;

    // ***************** Constructors ********************** //
    public Point2D(double x, double y){
        this._x=new Coordinate(x);
        this._y=new Coordinate(y);
    }

    // ***************** Getters/Setters ********************** //

    public double get_x() {
        return _x.get_cord();
    }

    public double get_y() {
        return _y.get_cord();
    }
    // ***************** Administration  ******************** //

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Point2D))
            return false;
        return (this.get_x()==((Point2D)obj).get_x())&&(this.get_y()==((Point2D)obj).get_y());
    }

    @Override
    public String toString() {
        return "("+this._x.toString()+","+this._y.toString()+")";
    }
    // ***************** Operations ******************** // 
}
