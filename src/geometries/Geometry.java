package geometries;

import primitives.*;

import java.util.List;
import java.util.Map;

public abstract class Geometry {

    protected Color _emission;
    protected Material _material;

    // ***************** Constructors ********************** //
    /**
     * ctor
     */
    public Geometry(Color emission,Material material){
        this._emission=new Color(emission);
        this._material=new Material(material);
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

    /**
     * Get mAterial
     * @return
     */
    public Material getMaterial() {
        return _material;
    }


    // ***************** Operations ******************** //

    /**
     *
     * @param temp
     * @return Returns The orthogonal Vector
     */
    public abstract Vector getNormal(Point3D temp);
    public abstract List<Point3D> findIntersections(Ray ray);

}
