package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * DirectionalLight
 */
public class DirectionalLight extends Light implements LightSource {

    protected Vector _direction;
    @Override
    public Color getIntensity() {
        return null;
    }

    /**
     * GetIntensity of Point
     *
     * @param point
     * @return
     */
    @Override
    public Color getIntensity(Point3D point) {
        return null;
    }

    /**
     * Vector between LightSource and Point
     *
     * @param point
     * @return
     */
    @Override
    public Vector getL(Point3D point) {
        return null;
    }

    /**
     * Vector of strongest light from spotlight
     *
     * @param point
     * @return
     */
    @Override
    public Vector getD(Point3D point) {
        return null;
    }
}
