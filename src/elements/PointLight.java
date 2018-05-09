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
    //******************************CONSTRUCTORS********************************//
    /**
     * Ctor
     * @param color The color of the PointLight
     * @param position Location of the PointLight
     * @param Kc Kc
     * @param Kl Kl
     * @param Kq Kq
     */
    PointLight(Color color, Point3D position,double Kc,double Kl,double Kq){
        _color = new Color(color);
        _position = new Point3D(position);
        _Kc = Kc;
        _Kl = Kl;
        _Kq = Kq;
    }

    /**
     * copy ctor
     * @param pointLight
     */
    PointLight(PointLight pointLight){
        _color = new Color(pointLight.getColor());
        _position = new Point3D(pointLight.getPosition());
        _Kc = pointLight.getKc();
        _Kl = pointLight.getKl();
        _Kq = pointLight.getKq();
    }
    //******************************GETTERS********************************//
    public Point3D getPosition() {
        return _position;
    }

    public double getKc() {
        return _Kc;
    }

    public double getKl() {
        return _Kl;
    }

    public double getKq() {
        return _Kq;
    }
    //******************************OPERATIONS********************************//
    /**
     *  Intensity
     * @return
     */
    @Override
    public Color getIntensity() {
        return new Color(Math.min((int)(_color.getColor().getRed()),255),Math.min((int)(_color.getColor().getGreen()),255),Math.min((int)(_color.getColor().getBlue()),255));

    }

    /**
     * GetIntensity of Point
     *
     * @param point
     * @return
     */
    @Override
    public Color getIntensity(Point3D point) {
        double d = point.distance(_position);//Distance between PointLight and Geomatry
        java.awt.Color i0 = getIntensity().getColor();
        int kkk = (int) (_Kc +_Kl*d + _Kq * Math.pow(d,2));
        Color il = new Color(i0.getRed()/kkk,i0.getGreen()/kkk,i0.getBlue()/kkk);
        return il;
    }

    /**
     * Vector between LightSource and Point
     *
     * @param point The spotlight location
     * @return Normal vector between the spotlight and  point
     */
    @Override
    public Vector getL(Point3D point) {
        Vector l = new Vector(point.vectorSubstract(_position).NormalVector());
        return l;
    }

    /**
     * Vector of strongest light from spotlight
     *
     * @param point
     * @return Returns always null
     */
    @Override
    public Vector getD(Point3D point) {
        return null;
    }
}
