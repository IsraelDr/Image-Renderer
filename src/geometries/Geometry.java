package geometries;

import primitives.Point3D;
import primitives.Vector;

public abstract class Geometry {

    // ***************** Constructors ********************** //

    /**
     * ctor
     */
    public Geometry(){}

    /**
     * Copy Ctor
     * @param temp
     */
    public Geometry(Geometry temp) {

    }
    // ***************** Operations ******************** //
    public abstract Vector getNormal(Point3D temp);
}
