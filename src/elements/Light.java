package elements;

import primitives.Color;

/**
 * basic abstract Light
 */
public abstract class Light {
    protected Color _color;

    public abstract Color getIntensity();

    /**
     * Getter
     * @return Returns the color of the light
     */
    public Color getColor() {
        return new Color(_color);
    }
}
