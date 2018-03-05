package primitives;

public class Coordinate {
    private double _cord;
    private final double EPSILON = 0.000001;
    // ***************** Constructors ********************** //
    public Coordinate(double cord){
        _cord = cord;
    }


    // ***************** Getters/Setters ********************** //
    public double get_cord() {
        return _cord;
    }

    // ***************** Administration  ******************** //

    @Override
    public String toString() {
        return "" + get_cord();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Coordinate))
            return false;
        if (Math.abs(get_cord()-((Coordinate)((Coordinate) obj)).get_cord())<EPSILON)
            return true;
        else
            return false;
    }
}
