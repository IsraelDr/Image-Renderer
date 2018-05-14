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
        this._color=new java.awt.Color(Math.max(Math.min(r,255),0),Math.max(Math.min(g,255),0),Math.max(Math.min(b,255),0));
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
        this._color=new java.awt.Color(Math.max(Math.min(_r,255),0),Math.max(Math.min(_g,255),0),Math.max(Math.min(_b,255),0));
    }

    /**
     * getter color
     * @return
     */
    public java.awt.Color getColor(){
        return this._color;
    }

    /**
     * multiply colors by scalar
     * @param mult number between 0 and 1
     */
    public void scale(double mult){
        int _r=(int)(_color.getRed()*mult);
        int _g=(int)(_color.getGreen()*mult);
        int _b=(int)(_color.getBlue()*mult);
        this._color = new java.awt.Color(Math.max(Math.min(_r,255),0),Math.max(Math.min(_g,255),0),Math.max(Math.min(_b,255),0));
    }

    /**
     * reduce from color
     * @param num
     */
    public void reduce(double num){
            this._color = new java.awt.Color((int)(Math.max(_color.getRed()-num,0)),(int)((Math.max(_color.getGreen()-num,0))),(int)((Math.max(_color.getBlue()-num,0))));

    }
}
