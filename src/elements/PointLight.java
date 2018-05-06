package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * Class of PointLight
 */
public class PointLight extends Light implements LightSource {
    protected Point3D _position;
    protected double _Kc,_Kl,_Kq;

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
