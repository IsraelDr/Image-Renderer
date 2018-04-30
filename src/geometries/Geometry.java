package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
import primitives.Ray;
import java.util.List;
import java.util.Map;

public abstract class Geometry {

    protected Color _emission;

    // ***************** Constructors ********************** //
    /**
     * ctor
     */
    public Geometry(Color emission){
        this._emission=new Color(emission);
    }

    /**
     * Copy Ctor
     * @param temp
     */
    public Geometry(Geometry temp) {
        this._emission=new Color(temp._emission);
    }
    //***************** GETTER/SETTER*******************//

    /**
     * Getter
     * @return emission color
     */
    public Color getEmission() {
        return _emission;
    }

    // ***************** Operations ******************** //

    /**
     *
     * @param temp
     * @return Returns The orthogonal Vector
     */
    public Vector getNormal(Point3D temp) {return null; };
    public abstract List<Point3D> findIntersections(Ray ray);
}
