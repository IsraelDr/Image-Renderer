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
        int _r=this._color.getRed();
        int _g=this._color.getGreen();
        int _b=this._color.getBlue();
        for (Color _c:params){
            _r+=_c._color.getRed();
            _g+=_c._color.getGreen();
            _b+=_c._color.getBlue();
        }
        this._color=new java.awt.Color(_r,_g,_b);
    }

    /**
     * getter color
     * @return
     */
    public java.awt.Color getColor(){
        return _color;
    }
}
