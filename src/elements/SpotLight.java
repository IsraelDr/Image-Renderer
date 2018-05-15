package elements;
import primitives.*;

/**
 * Class that defines SpotLight that is PointLight and DirectionalLight
 */
public class SpotLight extends PointLight {
    protected Vector _direction;

//******************************CONSTRUCTORS********************************//

    /**
     * Ctor
     *
     * @param color     The color of the PointLight
     * @param direction The direction of light
     * @param position  Location of the PointLight
     * @param Kc        Kc
     * @param Kl        Kl
     * @param Kq        Kq
     */
    public SpotLight(Color color, Vector direction, Point3D position, double Kc, double Kl, double Kq) {
        super(color, position, Kc, Kl, Kq);
        _direction = new Vector(direction).NormalVector();
    }

    /**
     * copy ctor
     *
     * @param spotLight The object to be copied
     */
    public SpotLight(SpotLight spotLight) {
        super(spotLight);
        _direction = new Vector((spotLight.getDirection()));
    }

    //******************************GETTERS********************************//

    public Vector getDirection() {
        return new Vector(_direction);
    }

    //******************************OPERATIONS********************************//


    /**
     * GetIntensity of Point
     *
     * @param point
     * @return
     */
    @Override
    public Color getIntensity(Point3D point) {
        /*double dMultL = getD(point).ScalarProduct(getL(point));
        if (dMultL < 0) //The point is not lit by the flashlight
            return new Color(0, 0, 0);
        java.awt.Color Il = super.getIntensity(point).getColor();
        return new Color((int) (Il.getRed() * dMultL), (int) (Il.getGreen() * dMultL), (int) (Il.getBlue() * dMultL));
        */
        double distance = _position.distance(point);
        double denominator = _Kc + distance * _Kl + _Kq * Math.pow(distance, 2);

        //position
        Vector l = (point.vectorSubstract(_position)).NormalVector();
        double numerator = _direction.ScalarProduct(l);
        if(numerator<0)
            return new Color(0,0,0);
        //scale
        Color result = new Color(_color);
        result.scale(numerator / denominator);

        return result;
    }

    @Override
    public Vector getD(Point3D point) {
        return getDirection().NormalVector();
    }

}



