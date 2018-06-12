package primitives;

/**
 * class that represents color
 */
public class Color {
    private java.awt.Color _color;

    //**************************Constructor**********************//

    /**
     * ctor
     *
     * @param r red
     * @param g green
     * @param b blue
     */
    public Color(int r, int g, int b) {
        this._color = new java.awt.Color(Math.max(Math.min(r, 255), 0), Math.max(Math.min(g, 255), 0), Math.max(Math.min(b, 255), 0));
    }

    /**
     * c'tor for the statics colors
     * @param RGB the RGB value
     * @param bool true if value includes the alpha
     */
    private Color(int RGB, boolean bool) {
        this._color = new java.awt.Color(RGB,bool);
    }

    /**
     * copy ctor
     *
     * @param color The Color that will copied
     */
    public Color(Color color) {
        this._color = new java.awt.Color(color._color.getRed(), color._color.getGreen(), color._color.getBlue());
    }

    /**
     * add Array of Colors
     *
     * @param params Color
     */
    public void add(Color... params) {
        int _r = this._color.getRed();
        int _g = this._color.getGreen();
        int _b = this._color.getBlue();
        for (Color _c : params) {
            _r += _c._color.getRed();
            _g += _c._color.getGreen();
            _b += _c._color.getBlue();
        }
        this._color = new java.awt.Color(Math.max(Math.min(_r, 255), 0), Math.max(Math.min(_g, 255), 0), Math.max(Math.min(_b, 255), 0));
    }

    /**
     * getter color
     *
     * @return Returns the color
     */
    public java.awt.Color getColor() {
        return this._color;
    }

    /**
     * multiply colors by scalar
     *
     * @param mult number between 0 and 1
     */
    public void scale(double mult) {
        int _r = (int) (_color.getRed() * mult);
        int _g = (int) (_color.getGreen() * mult);
        int _b = (int) (_color.getBlue() * mult);
        this._color = new java.awt.Color(Math.max(Math.min(_r, 255), 0), Math.max(Math.min(_g, 255), 0), Math.max(Math.min(_b, 255), 0));
    }

    /**
     * reduce from color
     *
     * @param num Scale factor
     */
    public void reduce(double num) {
        this._color = new java.awt.Color((int) (Math.max(_color.getRed() - num, 0)), (int) ((Math.max(_color.getGreen() - num, 0))), (int) ((Math.max(_color.getBlue() - num, 0))));

    }

    /**
     * Constant for the color white: R=255, G=255, B=255.
     */
    private static final Color white = new Color(0xffffff, false);
    /**
     * Constant for the color white: R=255, G=255, B=255.
     *
     
     */
    public static final Color WHITE = white;

    /**
     * Constant for the color light gray: R=192, G=192, B=192.
     */
    private static final Color lightGray = new Color(0xc0c0c0, false);

    /**
     * Constant for the color light gray: R=192, G=192, B=192.
     *
     
     */
    public static final Color LIGHT_GRAY = lightGray;

    /**
     * Constant for the color gray: R=128, G=128, B=128.
     */
    private static final Color gray = new Color(0x808080, false);

    /**
     * Constant for the color gray: R=128, G=128, B=128.
     *
     
     */
    public static final Color GRAY = gray;

    /**
     * Constant for the color dark gray: R=64, G=64, B=64.
     */
    private static final Color darkGray = new Color(0x404040, false);

    /**
     * Constant for the color dark gray: R=64, G=64, B=64.
     *
     
     */
    public static final Color DARK_GRAY = darkGray;

    /**
     * Constant for the color black: R=0, G=0, B=0.
     */
    private static final Color black = new Color(0x000000, false);

    /**
     * Constant for the color black: R=0, G=0, B=0.
     *
     
     */
    public static final Color BLACK = black;

    /**
     * Constant for the color red: R=255, G=0, B=0.
     */
    private static final Color red = new Color(0xff0000, false);

    /**
     * Constant for the color red: R=255, G=0, B=0.
     *
     
     */
    public static final Color RED = red;

    /**
     * Constant for the color pink: R=255, G=175, B=175.
     */
    private static final Color pink = new Color(0xffafaf, false);

    /**
     * Constant for the color pink: R=255, G=175, B=175.
     *
     
     */
    public static final Color PINK = pink;

    /**
     * Constant for the color orange: R=255, G=200, B=0.
     */
    private static final Color orange = new Color(0xffc800, false);

    /**
     * Constant for the color orange: R=255, G=200, B=0.
     *
     
     */
    public static final Color ORANGE = orange;

    /**
     * Constant for the color yellow: R=255, G=255, B=0.
     */
    private static final Color yellow = new Color(0xffff00, false);

    /**
     * Constant for the color yellow: R=255, G=255, B=0.
     *
     
     */
    public static final Color YELLOW = yellow;

    /**
     * Constant for the color green: R=0, G=255, B=0.
     */
    private static final Color green = new Color(0x00ff00, false);

    /**
     * Constant for the color green: R=0, G=255, B=0.
     *
     
     */
    public static final Color GREEN = green;

    /**
     * Constant for the color magenta: R=255, G=0, B=255.
     */
    private static final Color magenta = new Color(0xff00ff, false);

    /**
     * Constant for the color magenta: R=255, G=0, B=255.
     *
     
     */
    public static final Color MAGENTA = magenta;

    /**
     * Constant for the color cyan: R=0, G=255, B=255.
     */
    private static final Color cyan = new Color(0x00ffff, false);

    /**
     * Constant for the color cyan: R=0, G=255, B=255.
     *
     
     */
    public static final Color CYAN = cyan;

    /**
     * Constant for the color blue: R=0, G=0, B=255.
     */
    private static final Color blue = new Color(0x0000ff, false);

    /**
     * Constant for the color blue: R=0, G=0, B=255.
     *
     
     */
    public static final Color BLUE = blue;

}
