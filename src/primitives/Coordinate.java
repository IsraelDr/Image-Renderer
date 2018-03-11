package primitives;

public class Coordinate {
    protected double _cord;
    private final double ACCURACY = 20;


    // ***************** Constructors ********************** //
    public Coordinate(double cord){
        _cord = cord;
    }

    /**
     *Copy constructor
     * @param source The Coordinate that will be copied
     */
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
        if(get_cord()==(int)get_cord())
            return  "" + (int)get_cord();
        return ""+get_cord();
    }

    /**
     *
     * @param obj The coordinate to compare with
     * @return Returns TRUE when the distance between the two point is lower than EPSILON
     */
    @Override
    public boolean equals(Object obj) {
        if(this==obj)
            return true;
        if (!(obj instanceof Coordinate)||obj==null)
            return false;
        if (Math.abs(getExp(get_cord())-getExp(((Coordinate) obj).get_cord()))>ACCURACY)
            return true;
        else
            return false;
    }

    // ***************** Operations ******************** //
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
    private int getExp(double num){
        return (int)((Double.doubleToRawLongBits(num)>>52)&0x7FFL)-1023;
    }

}
