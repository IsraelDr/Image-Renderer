package scene;


import elements.Camera;
import geometries.*;
import primitives.*;

/**
 * Class that defines the Scene
 */
public class Scene {
    protected String _name;
    protected Color _background;
    protected Geometries _geometries;
    protected Camera _camera;
    protected double _distance;

    //****************Constructor****************//

    /**
     * ctor
     * @param name of scene
     */
    public Scene(String name){
        _name=name;
        _background=new Color(1,1,1);
        _geometries=new Geometries();
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
        return _background;
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
    public void setCamera(Camera camera) {
        this._camera=new Camera(camera);
    }

    /**
     * Setter
     * @param color Sets the color of the scene
     */
    public void set_color(Color color) {
        this._background = new Color(color);
    }

    /**
     * Setter
     * @param distance Sets the distance between the Camera and the view plane
     */
    public void set_distance(double distance) {
        this._distance = distance;
    }

    //********************Operations*************************//

    /**
     * adding geometry to scene
     * @param geometry, one of the geometries
     */
    public void addGeometry(Geometry geometry) {
        this._geometries.addGeometry(geometry);
    }
}
