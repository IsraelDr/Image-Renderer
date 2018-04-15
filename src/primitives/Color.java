package primitives;

/**
 * class that represents color
 */
public class Color {
    protected java.awt.Color _color;

    //**************************Constructor**********************//
    /**
     * ctor
     * @param r red
     * @param g green
     * @param b blue
     */
    public Color(int r,int g,int b){
        this._color=new java.awt.Color(Math.min(r,255),Math.min(g,255),Math.min(b,255));
    }

    /**
     * copy ctor
     * @param color
     */
    public Color(Color color){
        this._color=new java.awt.Color(color._color.getRed(),color._color.getGreen(),color._color.getBlue());
    }

    /**
     * add Array of Colors
     * @param params
     */
    public void add(Color... params){
        for (Color _c:params){

        }
    }

    /**
     * multiply colors by scalar
     * @param mult number between 0 and 1
     */
    public void scale(double mult){
        if(mult<1&&mult >=0)
            this._color = new java.awt.Color((int)(_color.getRed()*mult),(int)(_color.getGreen()*mult),(int)(_color.getBlue()*mult));

    }
}
