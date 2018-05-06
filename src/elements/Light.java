package elements;

import primitives.Color;

/**
 * basic abstract Light
 */
public abstract class Light {
    protected Color _color;

    public abstract Color getIntensity();
}
