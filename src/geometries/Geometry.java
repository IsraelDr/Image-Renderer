package geometries;

import primitives.Point3D;
import primitives.Vector;
import primitives.Ray;
import java.util.ArrayList;

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

    /**
     *
     * @param temp
     * @return Returns The orthogonal Vector
     */
    public abstract Vector getNormal(Point3D temp);
    public abstract ArrayList<Point3D> findIntersections(Ray ray);
}
