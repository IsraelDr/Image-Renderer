package scene;


import elements.Camera;
import geometries.Geometry;
import primitives.Point3D;
import primitives.Vector;

import java.awt.*;
import java.util.ArrayList;

/**
 * Class that defines the Scene
 */
public class Scene {
    protected String _name;
    protected Color _color;
    protected ArrayList<Geometry> _geometries;
    protected Camera _camera;
    protected double _distance;


    //****************Constructor****************//

    /**
     * ctor
     * @param name of scene
     */
    public Scene(String name){
        _name=name;
        _color=new Color(1,1,1);
        _geometries=new ArrayList<Geometry>();
        _camera=new Camera(new Point3D(0,0,0),new Vector(1,0,0),new Vector(0,1,0));
        _distance=1;
    }

    //*************************Setter/Getter**********//

    /**
     * getter
     * @return Returns the camera that takes the scene
     */
    public Camera get_camera() {
        return _camera;
    }

    /**
     * getter
     * @return Returns the color of the scene
     */
    public Color get_color() {
        return _color;
    }

    /**
     * getter
     * @return Returns the distance between the Camera and the view plane
     */
    public double get_distance() {
        return _distance;
    }

    /**
     * Setter
     * @param camera Sets the camera that takes the scene
     */
    public void set_camera(Camera camera) {
        this._camera=new Camera(camera);
    }

    /**
     * Setter
     * @param color Sets the color of the scene
     */
    public void set_color(Color color) {
        this._color = new Color(color.getRed(),color.getGreen(),color.getBlue());
    }

    /**
     * Seeter
     * @param _distance Sets the distance between the Camera and the view plane
     */
    public void set_distance(double _distance) {
        this._distance = _distance;
    }
}
