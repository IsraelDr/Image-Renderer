package primitives;
public class Coordinate {
    protected double _cord;
    private final double EPSILON = 0.000001;


    // ***************** Constructors ********************** //
    public Coordinate(double cord){
        _cord = cord;
    }


    Coordinate(Coordinate source){
        _cord = source.get_cord();
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

    /**
     *
     * @param other The Coordinate that will be added to the current Coordinate
     * @return Returns a new Coordinate with the value of the two Coordinate
     */
    public Coordinate add(Coordinate other){
        return new Coordinate(other.get_cord()+get_cord()) ;
    }

    /**
     *
     * @param other The Coordinate that will be substracted from the current Coordinate
     * @return The current Coordinate - the secont Coordinate
     */
    public Coordinate substract(Coordinate other){
        return new Coordinate(get_cord()-other.get_cord());
    }

}
