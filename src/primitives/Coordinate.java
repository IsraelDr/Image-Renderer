package primitives;

public class Coordinate {
    protected double _cord;
    private final double ACCURACY = -20;


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
        if(get_cord()==(int)get_cord()) //checked if the double is an int
            return  "" + (int)get_cord();
        return ""+get_cord();
    }

    /**
     *
     * @param obj The coordinate to compare with
     * @return Returns TRUE when the distance between the two point is lower than EPSILON
     */
    /*@Override
    public boolean equals(Object obj) {
        if(this==obj)
            return true;
        if (!(obj instanceof Coordinate)||obj==null)
            return false;
        if (Math.abs(getExp(get_cord())-getExp(((Coordinate) obj).get_cord()))>ACCURACY)
            return true;
        else
            return false;
    }*/

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Coordinate)) return false;

        Coordinate other = (Coordinate) obj;
        return subtract(other._cord) == 0.0;
    }


    // ***************** Operations ******************** //

    /**
     *
     * @param other The Coordinate that will be subtracted from the current Coordinate
     * @return The current Coordinate - the secont Coordinate
     */
    public Coordinate subtract(Coordinate other) {
        return new Coordinate(subtract(other._cord));
    }

    /**
     *
     * @param other The Coordinate that will be added to the current Coordinate
     * @return Returns a new Coordinate with the value of the two Coordinate
     */
    public Coordinate add(Coordinate other) {
        return new Coordinate(add(other._cord));
    }

    private int getExp(double num){
        return (int)((Double.doubleToRawLongBits(num)>>52)&0x7FFL)-1023;
    }

    private double subtract(double other) {
        int otherExp = getExp(other);
        int thisExp = getExp(_cord);

        // if other is too small relatively to our coordinate
        // return the original coordinate
        if (otherExp - thisExp < ACCURACY)
            return _cord;

        // if our coordinate is too small relatively to other
        // return negative of other coordinate
        if (thisExp - otherExp < ACCURACY)
            return -other;

        double result = _cord - other;
        // if the result is too small tell that it is zero
        int resultExp = getExp(result);
        return resultExp < ACCURACY ? 0.0 : result;
    }

    private double add(double other) {
        int otherExp = getExp(_cord);
        int thisExp = getExp(_cord);

        // if other is too small relatively to our coordinate
        // return the original coordinate
        if (otherExp - thisExp < ACCURACY)
            return _cord;

        // if our coordinate is too small relatively to other
        // return other coordinate
        if (thisExp - otherExp < ACCURACY)
            return other;

        double result = _cord + other;
        // if the result is too small tell that it is zero
        int resultExp = getExp(result);
        return resultExp < ACCURACY ? 0.0 : result;
    }


}
