package scene;


import elements.AmbientLight;
import elements.Camera;
import elements.LightSource;
import geometries.Geometries;
import geometries.Geometry;
import primitives.Color;
import primitives.Key;
import primitives.Point3D;
import primitives.Vector;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Class that defines the Scene
 */
public class Scene {
    protected String _name;
    protected Color _background;
    protected Geometries _geometries;
    protected Camera _camera;
    protected double _distance;
    protected AmbientLight _ambientlight;
    protected List<LightSource> _lights;
    protected Map<Key, List<Geometry>> _cubemap;
    protected double _cubeDx = 10;
    protected double _cubeDy = 10;
    protected double _cubeDz = 10;

    //****************Constructor****************//

    /**
     * ctor
     *
     * @param name of scene
     */
    public Scene(String name) {
        _name = name;
        _background = new Color(0, 0, 0);
        _geometries = new Geometries();
        _camera = new Camera(new Point3D(0, 0, 0), new Vector(1, 0, 0), new Vector(0, 1, 0));
        _distance = 1;
        _ambientlight = new AmbientLight(new Color(100, 90, 120), 1);
        _lights = new LinkedList<>();
    }

    //*************************Setter/Getter**********//

    /**
     * getter
     *
     * @return Returns the camera that takes the scene
     */
    public Camera getCamera() {
        return _camera;
    }

    public Map<Key, List<Geometry>> getCubemap() {
        return _cubemap;
    }

    /**
     * getter
     *
     * @return geometries
     */
    public List<Geometry> getGeometries() {
        return _geometries.getGeometries();
    }

    public void setGeometries(Geometries geometries) {
        for (Geometry g : geometries.getGeometries()) {
            this._geometries.addGeometry(g);
        }
    }

    /**
     * getter
     *
     * @return Returns the color of the scene
     */
    public java.awt.Color getBackgroundColor() {
        return _background.getColor();
    }

    /**
     * getter
     *
     * @return Returns the distance between the Camera and the view plane
     */
    public double getDistance() {
        return _distance;
    }

    /**
     * getter
     *
     * @return
     */
    public AmbientLight getAmbientlight() {
        return _ambientlight;
    }

    /**
     * setter
     *
     * @param light
     */
    public void setAmbientlight(Color light, double ka) {
        this._ambientlight = new AmbientLight(light, ka);
    }

    /**
     * Setter
     *
     * @param camera Sets the camera that takes the scene
     */
    public void setCamera(Camera camera) {
        this._camera = new Camera(camera);
    }

    /**
     * Setter
     *
     * @param color Sets the color of the scene
     */
    public void setBackgroundColor(Color color) {
        this._background = new Color(color);
    }

    /**
     * Setter
     *
     * @param distance Sets the distance between the Camera and the view plane
     */
    public void setDistance(double distance) {
        this._distance = distance;
    }

    /**
     * getter lights
     *
     * @return
     */
    public List<LightSource> getLights() {
        return _lights;
    }
    //********************Operations*************************//

    /**
     * adding geometry to scene
     *
     * @param geometry, one of the geometries
     */
    public void addGeometry(Geometry geometry) {
        this._geometries.addGeometry(geometry);
        List<Point3D> boundaryPoints = geometry.getBoudaryPoints(_camera.getToward(), _camera.getRight(), _camera.getUp());
        List<Geometry> temp;
        Key k1 = Key.getKeyofPoint(boundaryPoints.get(0), this._camera.getP0().addVectorToPoint(this._camera.getToward().multipliedbyScalar(this._distance)), _cubeDx, _cubeDy, _cubeDz);
        Key k2 = Key.getKeyofPoint(boundaryPoints.get(1), this._camera.getP0().addVectorToPoint(this._camera.getToward().multipliedbyScalar(this._distance)), _cubeDx, _cubeDy, _cubeDz);
        Key k3 = Key.getKeyofPoint(boundaryPoints.get(2), this._camera.getP0().addVectorToPoint(this._camera.getToward().multipliedbyScalar(this._distance)), _cubeDx, _cubeDy, _cubeDz);
        Key k4 = Key.getKeyofPoint(boundaryPoints.get(3), this._camera.getP0().addVectorToPoint(this._camera.getToward().multipliedbyScalar(this._distance)), _cubeDx, _cubeDy, _cubeDz);
        for (int i = k1.getX(); i <= k2.getX(); i++) {
            for (int j = k1.getY(); j <= k3.getY(); j++) {
                for (int k = k1.getZ(); k <= k4.getZ(); k++) {
                    Key current = new Key(i, j, k);
                    if (_cubemap.containsKey(current))
                        temp = _cubemap.get(current);
                    else
                        temp = new ArrayList<>();
                    temp.add(geometry);
                    _cubemap.replace(current, temp);
                }
            }
        }
    }

    public void addLight(LightSource light) {
        this._lights.add(light);
    }

    public Key MaxKey() {
        Key max = new Key(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
        for (Key k : this._cubemap.keySet()) {
            max.setX(Math.max(k.getX(), max.getX()));
            max.setY(Math.max(k.getY(), max.getY()));
            max.setZ(Math.max(k.getZ(), max.getZ()));

        }
        return max;
    }
    public Key getKeybyPoint(Point3D p){
        return Key.getKeyofPoint(p,this._camera.getP0().addVectorToPoint(this._camera.getToward().multipliedbyScalar(this._distance)),this._cubeDx,this._cubeDy,this._cubeDz);
    }
}
