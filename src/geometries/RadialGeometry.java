package geometries;

import primitives.Color;

public abstract class RadialGeometry extends Geometry {
    protected double _radius;

    // ***************** Constructors ********************** //
    public RadialGeometry(double rad, Color emission){
        super(emission);
        this._radius=rad;
    }
    /**
     * Copy ctor
     * @param temp
     */
    public RadialGeometry(RadialGeometry temp){
        super(temp);
        this._radius=temp.get_radius();
    }

    // ***************** Getters/Setters ********************** //

    public double get_radius() {
        return _radius;
    }
    // ***************** Administration  ******************** //

    @Override
    public String toString() {
        return "Rad: "+_radius;
    }

}
