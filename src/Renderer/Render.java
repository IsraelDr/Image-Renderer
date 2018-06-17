package Renderer;


import elements.LightSource;
import elements.PointLight;
import geometries.Geometry;
import primitives.*;
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
    private final int MAX_CALC_COLOR_LEVEL = 3;

    //****************Constructor****************//
    public Render(Scene scene, ImageWriter imageWriter) {
        this._scene = scene;
        this._imageWriter = new ImageWriter(imageWriter);
    }

    //********************Operations*************************//
    public void renderImage() {
        int k;
        for (int i = 1; i < _imageWriter.getNx(); i++) {
            for (int j = 1; j < _imageWriter.getNy(); j++) {
                if (i == 500 && j >= 492) {
                    k = 5;
                    //this._imageWriter.writePixel(i, j, new java.awt.Color(255,0,0));
                    //continue;
                }
                Ray ray = _scene.getCamera().constructRayThroughPixel(_imageWriter.getNx(), _imageWriter.getNy(), i, j, _scene.getDistance(), _imageWriter.getWidth(), _imageWriter.getHeight());
                Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(ray);
                if (intersectionPoints.isEmpty())
                    _imageWriter.writePixel(i, j, _scene.getBackgroundColor());
                else {
                    Map<Geometry, Point3D> closestPoint = getClosestPoint(intersectionPoints);
                    Map.Entry<Geometry, Point3D> entry = closestPoint.entrySet().iterator().next();
                    this._imageWriter.writePixel(i, j, calcColor(entry.getKey(), entry.getValue(), ray).getColor());
                }
            }
        }
    }

    /**
     * PrintGrid by interval
     *
     * @param interval
     */
    public void printGrid(int interval) {
        for (int i = 0; i < 500; i += interval) {
            for (int j = 0; j < 500; j++) {
                _imageWriter.writePixel(i, j, new java.awt.Color(255, 255, 255));
                _imageWriter.writePixel(j, i, new java.awt.Color(255, 255, 255));
            }
        }
    }
    //******************************GETTERS********************************//

    /**
     * getter
     *
     * @return imagewriter
     */
    public ImageWriter getImageWriter() {
        return _imageWriter;
    }

    // ************************** Operations ***************************** //
    private Color calcColor(Geometry geometry, Point3D point, Ray inRay) {
        return calcColor(geometry, point, inRay, MAX_CALC_COLOR_LEVEL, 1.0);
    }

    /**
     * Calculates the color
     *
     * @param point
     * @return
     */
    private Color calcColor(Geometry geometry, Point3D point, Ray inRay, int level, double k) {
        if (level == 0 || Coordinate.isZero(k)) return Color.BLACK;
        Color color = _scene.getAmbientlight().getIntensity();
        color.add(geometry.getEmission());
        Vector v = inRay.get_vector();
        Vector n = geometry.getNormal(point);
        int nShininess = geometry.getMaterial().getShininess();
        double kd = geometry.getMaterial().getKd();
        double ks = geometry.getMaterial().getKs();
        for (LightSource lightsource : _scene.getLights()) {
            Vector l = lightsource.getL(point);
            if ((l.ScalarProduct(n) * v.ScalarProduct(n) > 0)) {
                double o = occluded(l, lightsource, geometry, point);
                if (!Coordinate.isZero(o * k)) {
                    Color lightIntensity = new Color(lightsource.getIntensity(point));
                    lightIntensity.scale(o);
                    color.add(calcDiffusive(kd, l, n, lightIntensity), calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }
            }
        }
        //Recursive call for a reflected ray
        Ray reflectedRay = constructReflectedRay(n, point, inRay);
        Map<Geometry, List<Point3D>> reflectedgeometry = getSceneRayIntersections(reflectedRay);
        Map<Geometry, Point3D> reflectedPoint = getClosestPoint(reflectedgeometry, point);
        if (!reflectedPoint.isEmpty()) {
            Map.Entry<Geometry, Point3D> entryreflected = reflectedPoint.entrySet().iterator().next();
            //double kr2 = reflectedPoint.entrySet().iterator().next().getKey().getMaterial().getKr();
            double kr = geometry.getMaterial().getKr();
            Color reflectedlight = calcColor(entryreflected.getKey(), entryreflected.getValue(), reflectedRay, level - 1, k * kr);
            reflectedlight.scale(kr);
            color.add(reflectedlight);
        }

        Ray refractedRay = constructRefractedRay(point, inRay);
        Map<Geometry, List<Point3D>> refractedgeometry = getSceneRayIntersections(refractedRay);
        //refractedgeometry.entrySet().iterator().next().getValue().remove(point);
        Map<Geometry, Point3D> refractedPoint = getClosestPoint(refractedgeometry, point);
        if (!refractedPoint.isEmpty()) {
            Map.Entry<Geometry, Point3D> entryrefracted = refractedPoint.entrySet().iterator().next();
            //double kt = reflectedPoint.entrySet().iterator().next().getKey().getMaterial().getKt();
            double kt = geometry.getMaterial().getKt();
            Color refractedlight = calcColor(entryrefracted.getKey(), entryrefracted.getValue(), refractedRay, level - 1, k * kt);
            refractedlight.scale(kt);
            color.add(refractedlight);
        }

        return color;

    }

    /**
     * Refracted
     *
     * @param point
     * @param inRay
     * @return
     */
    private Ray constructRefractedRay(Point3D point, Ray inRay) {
        //Vector epsVector= normal.multipliedbyScalar(normal.ScalarProduct(inRay.get_vector())>0?2:-2);
        // geoPoint= point.addVectorToPiont(epsVector);
        return new Ray(inRay.get_vector(), point);
    }

    /**
     * Reflectd
     *
     * @param n
     * @param point
     * @param inRay
     * @return
     */
    private Ray constructReflectedRay(Vector n, Point3D point, Ray inRay) {
        return new Ray((inRay.get_vector().add(n.multipliedbyScalar(-2 * inRay.get_vector().ScalarProduct(n)))).NormalVector(), point);
    }

    /**
     * return Calc of Diffusion
     *
     * @param kd
     * @param l
     * @param n
     * @param lightIntensity
     * @return
     */
    private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
        Color result = new Color(lightIntensity);
        double scalingFactor = kd * (Math.abs(l.ScalarProduct(n)));//needs to be checked with teacher todo
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
     *
     * @param ks
     * @param n
     * @param v
     * @param nShininess
     * @param lightIntensity
     * @return
     */
    private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
        Color result = new Color(lightIntensity);
        //double temp = -2 * l.ScalarProduct(n);
        //Vector nComponent = n.multipliedbyScalar(temp);
        Vector r = l.add(n.multipliedbyScalar(-2 * l.ScalarProduct(n)));
        double mvr = -v.ScalarProduct(r);
        if (mvr > 0) {
            double scalingFactor = ks * Math.pow(mvr, nShininess);
            //if ((l.multipliedbyScalar(-1).ScalarProduct(n) > 0 &&v.ScalarProduct(n) > 0)|| (l.multipliedbyScalar(-1).ScalarProduct(n) < 0 && v.ScalarProduct(n) < 0)) {
            result.scale(scalingFactor);
            return result;
        } else {
            return Color.BLACK;
        }
    }

    /**
     * getClosestPoint
     *
     * @param intersectionpoints Intersection points
     * @return Returns the closest point
     */
    private Map<Geometry, Point3D> getClosestPoint(Map<Geometry, List<Point3D>> intersectionpoints) {
        double distance = Double.MAX_VALUE;
        Point3D p0 = _scene.getCamera().getP0();
        Map<Geometry, Point3D> minDistancePoint = new HashMap<Geometry, Point3D>();
        for (Map.Entry<Geometry, List<Point3D>> a : intersectionpoints.entrySet()) {
            for (Point3D p : a.getValue()) {
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
     * getClosestPoint
     *
     * @param intersectionpoints Intersection points
     * @return Returns the closest point
     */
    private Map<Geometry, Point3D> getClosestPoint(Map<Geometry, List<Point3D>> intersectionpoints, Point3D p0) {
        double distance = Double.MAX_VALUE;
        Map<Geometry, Point3D> minDistancePoint = new HashMap<Geometry, Point3D>();
        for (Map.Entry<Geometry, List<Point3D>> a : intersectionpoints.entrySet()) {
            for (Point3D p : a.getValue()) {
                if (p0.distance(p) < distance && p0.distance(p) > 0) {
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
     *
     * @return map with geometry and list of points
     */
    private Map<Geometry, List<Point3D>> getSceneRayIntersections(Ray ray) {
        Map<Geometry, List<Point3D>> intersectionPoints = new HashMap<Geometry, List<Point3D>>();
        List<Point3D> geometryIntersectionPoints = new ArrayList<Point3D>();
        for (Geometry geometry : _scene.getGeometries()) {
            geometryIntersectionPoints = geometry.findIntersections(ray);
            if (!geometryIntersectionPoints.isEmpty())
                intersectionPoints.put(geometry, geometryIntersectionPoints);
        }
        return intersectionPoints;
    }

    /**
     * occluded checks if has to be shdow rays
     *
     * @param l
     * @param geometry
     * @param point
     * @return
     */
    private double occluded(Vector l, LightSource lightsource, Geometry geometry, Point3D point) {
        Vector lightDirection = l.multipliedbyScalar(-1); // from point to light source
        Ray lightRay = new Ray(lightDirection, point);
        Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(lightRay);
        intersectionPoints.remove(geometry);
        int count;
        double shadowK = 1;
        for (Map.Entry<Geometry, List<Point3D>> entry : intersectionPoints.entrySet()) {
            count = 0;
            for (Point3D p : entry.getValue()) {
                if (lightsource instanceof PointLight && p.distance(point) > ((PointLight)lightsource).getPosition().distance(point))
                    count++;
            }
            if (lightsource instanceof PointLight && count >= entry.getValue().size())
                continue;
            shadowK *= entry.getKey().getMaterial().getKt();
        }
        return shadowK;
        /*if(intersectionPoints.isEmpty())
            return false;
        Map<Geometry,Point3D> closestPoint = getClosestPoint(intersectionPoints);
        Map.Entry<Geometry,Point3D>entry=closestPoint.entrySet().iterator().next();
        return (entry.getValue().distance(point)<lightpoint.distance(point));
        */
    }

    /**
     *
     * @param point
     * @param source
     * @param Dx
     * @param Dy
     * @param Dz
     * @return
     */
    public static Key getKeyofPoint(Point3D point,Point3D source,double Dx,double Dy,double Dz){
        return new Key((int)((point.get_x()-source.get_x())/Dx),(int)((point.get_y()-source.get_y())/Dy),(int)((point.get_z()-source.get_z()/Dz)));
    }
}
