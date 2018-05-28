package Renderer;


import elements.DirectionalLight;
import elements.LightSource;
import geometries.Geometry;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Render
 */
public class Render {
    private Scene _scene;
    private ImageWriter _imageWriter;
    public Render(Scene scene,ImageWriter imageWriter) {
        this._scene=scene;
        this._imageWriter=new ImageWriter(imageWriter);
    }
    public void renderImage(){
        int k;
        for (int i = 1; i < _imageWriter.getNx(); i++) {
            for (int j = 1; j < _imageWriter.getNy(); j++) {
                if(i==740&&j>=500) {
                    k = 5;
                    //this._imageWriter.writePixel(i, j, new java.awt.Color(0,0,0));
                    //continue;
                }
                Ray ray=_scene.getCamera().constructRayThroughPixel(_imageWriter.getNx(),_imageWriter.getNy(),i,j,_scene.getDistance(),_imageWriter.getWidth(),_imageWriter.getHeight());
                Map<Geometry,List<Point3D>> intersectionPoints=getSceneRayIntersections(ray);
                if(intersectionPoints.isEmpty())
                    _imageWriter.writePixel(i,j,_scene.getBackgroundColor());
                else {
                    Map<Geometry,Point3D> closestPoint = getClosestPoint(intersectionPoints);
                    Map.Entry<Geometry,Point3D>entry=closestPoint.entrySet().iterator().next();
                    this._imageWriter.writePixel(i, j, calcColor(entry.getKey(), entry.getValue()).getColor());
                }
            }
        }
    }

    /**
     * PrintGrid by interval
     * @param interval
     */
    public void printGrid(int interval){
        for (int i = 0; i < 500; i+=interval) {
            for (int j = 0; j < 500; j++) {
                _imageWriter.writePixel(i,j,new java.awt.Color(255,255,255));
                _imageWriter.writePixel(j,i,new java.awt.Color(255,255,255));
            }
        }
    }
    //******************************GETTERS********************************//

    /**
     * getter
     * @return imagewriter
     */
    public ImageWriter getImageWriter() {
        return _imageWriter;
    }

    // ************************** Operations ***************************** //
    /**
     * Calculates the color
     * @param point
     * @return
     */
    private Color calcColor(Geometry geometry, Point3D point){
        Color color=_scene.getAmbientlight().getIntensity();
        color.add(geometry.getEmission());
        Vector n =geometry.getNormal(point);
        int nShininess=geometry.getMaterial().getShininess();
        double kd=geometry.getMaterial().getKd();
        double ks=geometry.getMaterial().getKs();
        for (LightSource lightsource:_scene.getLights()) {
            if(lightsource instanceof DirectionalLight){
                Vector normal=geometry.getNormal(point);
                if(normal.ScalarProduct(lightsource.getD(point).multipliedbyScalar(-1))>0)
                    color.add(lightsource.getIntensity(point));
            }
            else {
                Color lightIntensity = lightsource.getIntensity(point);
                Vector l = lightsource.getL(point);
                Vector v = (point.vectorSubstract(_scene.getCamera().getP0())).NormalVector();
                if (l.ScalarProduct(n)*v.ScalarProduct(n) > 0)
                    color.add(calcDiffusive(kd, l, n, lightIntensity), calcSpecular(ks, l, n, v, nShininess, lightIntensity));
            }
        }

        return color;

    }

    /**
     * return Calc of Diffusion
     * @param kd
     * @param l
     * @param n
     * @param lightIntensity
     * @return
     */
    private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
        Color result = new Color(lightIntensity);
        double scalingFactor = kd *(Math.abs(l.ScalarProduct(n)));//needs to be checked with teacher todo
        //if(scalingFactor > 0 ) {
            result.scale(scalingFactor);
            return result;
        //}
        //else{
        //    return new Color(0,0,0);
        //}
    }

    /**
     * return Calc of Specular
     * @param ks
     * @param n
     * @param v
     * @param nShininess
     * @param lightIntensity
     * @return
     */
    private Color calcSpecular(double ks,Vector l,Vector n,Vector v,int nShininess,Color lightIntensity) {
        Color result = new Color(lightIntensity);
        //double temp = -2 * l.ScalarProduct(n);
        //Vector nComponent = n.multipliedbyScalar(temp);
        Vector r = l.add(n.multipliedbyScalar(-2 * l.ScalarProduct(n)));
        double scalingFactor = ks * Math.pow(v.multipliedbyScalar(-1).ScalarProduct(r),nShininess);
        //if ((l.multipliedbyScalar(-1).ScalarProduct(n) > 0 &&v.ScalarProduct(n) > 0)|| (l.multipliedbyScalar(-1).ScalarProduct(n) < 0 && v.ScalarProduct(n) < 0)) {
            result.scale(scalingFactor);
            return result;
        //}
        //else{
        //    return new Color(0,0,0);
        //}
    }

    /**
     * getClosestPoint
     * @param intersectionpoints Intersection points
     * @return Returns the closest point
     */
    private Map<Geometry,Point3D> getClosestPoint(Map<Geometry, List<Point3D>> intersectionpoints){
        double distance = Double.MAX_VALUE;
        Point3D p0 = _scene.getCamera().getP0();
        Map<Geometry, Point3D> minDistancePoint = new HashMap<Geometry, Point3D>();
        for(Map.Entry<Geometry,List<Point3D>> a:intersectionpoints.entrySet()){
            for (Point3D p:a.getValue()) {
                if (p0.distance(p) < distance) {
                    minDistancePoint.clear(); // make it empty
                    minDistancePoint.put(a.getKey(), new Point3D(p));
                    distance = p0.distance(p);
                }
            }
        }
        return minDistancePoint;
    }

    /**
     * getScene Intersections
     * @return map with geometry and list of points
     */
    private Map<Geometry,List<Point3D>> getSceneRayIntersections(Ray ray){
        Map<Geometry, List<Point3D>> intersectionPoints = new HashMap<Geometry, List<Point3D>>();
        List<Point3D> geometryIntersectionPoints=new ArrayList<Point3D>();
        for (Geometry geometry : _scene.getGeometries()) {
            geometryIntersectionPoints = geometry.findIntersections(ray);
            if(!geometryIntersectionPoints.isEmpty())
                intersectionPoints.put(geometry,geometryIntersectionPoints);
        }
        return intersectionPoints;
    }
}
