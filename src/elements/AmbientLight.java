package elements;

import primitives.Color;

/**
 * Class represents the ambient light
 */
public class AmbientLight extends Light {

    protected double _Ka;

    /**
     * ctor
     * @param color
     * @param Ka
     */
    public AmbientLight(Color color,double Ka){
        this._color=new Color(color);
        this._Ka=Ka;
    }

    /**
     *  Intensity
     * @return
     */
    public Color getIntensity(){
        return new Color(Math.min((int)(_color.getColor().getRed()),255),Math.min((int)(_color.getColor().getGreen()*_Ka),255),Math.min((int)(_color.getColor().getBlue()*_Ka),255));
    }
}
