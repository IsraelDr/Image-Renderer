package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * Interface of Light Source
 */
public interface LightSource {

    /**
     * GetIntensity of Point
     * @param point
     * @return
     */
    public Color getIntensity(Point3D point);

    /**
     * Vector between LightSource and Point
     * @param point
     * @return
     */
    public Vector getL(Point3D point);

    /**
     * Vector of strongest light from spotlight
     * @param point
     * @return
     */
    public Vector getD(Point3D point);
}
