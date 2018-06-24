package Renderer;


import elements.LightSource;
import elements.PointLight;
import geometries.*;
import primitives.*;
import scene.Key;
import scene.Raytrace;
import scene.Scene;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Render
 */
public class Render {
    private Scene _scene;
    private ImageWriter _imageWriter;
    private final int MAX_CALC_COLOR_LEVEL = 3;
    private boolean ImprovedRenderer=true;
    //****************Constructor****************//
    public Render(Scene scene, ImageWriter imageWriter) {
        this._scene = scene;
        this._imageWriter = new ImageWriter(imageWriter);
        new Raytrace();
    }

    //********************Operations*************************//
    public void renderImage()  throws InterruptedException {
        final ThreadPoolExecutor executor = new ThreadPoolExecutor(8, 12, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        int k;
        //Plane firstplane=new Plane(_scene.getPoint3DbyKey(this._scene.MinKey()),_scene.getCamera().getToward(),new Color(0,0,0),new Material(0,0,0,0,0));
        for (int i = 1; i < _imageWriter.getNx(); i++) {
            for (int j = 1; j < _imageWriter.getNy(); j++) {
                final int ii = i;
                final int jj = j;
                if (i == 500 && j >= 650) {
                    k = 5;
                    //this._imageWriter.writePixel(i, j, new java.awt.Color(255,0,0));
                    //continue;
                }
                executor.execute(() -> {
                    Map<Geometry, List<Point3D>> intersectionPoints;
                    Ray ray;
                    if (ImprovedRenderer) {
                        ray = _scene.getCamera().constructRayThroughPixel(_imageWriter.getNx(), _imageWriter.getNy(), ii, jj, _scene.getDistance(), _imageWriter.getWidth(), _imageWriter.getHeight());
                        //List<Point3D> l=firstplane.findIntersections(ray);
                    /*if(l.size()>0)
                        ray=new Ray(ray.get_vector(),l.get(0));*/
                        intersectionPoints = getSceneRayIntersectionsImproved(ray);
                    } else {
                        ray = _scene.getCamera().constructRayThroughPixel(_imageWriter.getNx(), _imageWriter.getNy(), ii, jj, _scene.getDistance(), _imageWriter.getWidth(), _imageWriter.getHeight());
                        intersectionPoints = getSceneRayIntersections(ray);
                    }
                    if (intersectionPoints.isEmpty())
                        _imageWriter.writePixel(ii, jj, _scene.getBackgroundColor());
                    else {
                        Map<Geometry, Point3D> closestPoint = getClosestPoint(intersectionPoints);
                        Map.Entry<Geometry, Point3D> entry = closestPoint.entrySet().iterator().next();
                        this._imageWriter.writePixel(ii, jj, calcColor(entry.getKey(), entry.getValue(), ray).getColor());
                    }
                });

            }
        }
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.MINUTES);
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
     * getScene Intersections
     *
     * @return map with geometry and list of points
     */
    private Map<Geometry, List<Point3D>> getSceneRayIntersectionsImproved(Ray ray) {
        int k=0;
        Map<Geometry, List<Point3D>> intersectionPoints = new HashMap<Geometry, List<Point3D>>();
        List<Point3D> geometryIntersectionPoints = new ArrayList<Point3D>();
        List<Geometry> _geometries=getrelevantgeometries(ray,new ArrayList<>(),this._scene.MaxKey(),"front",1);
        for (Geometry geometry : _geometries) {
            geometryIntersectionPoints = geometry.findIntersections(ray);
            if (!geometryIntersectionPoints.isEmpty())
                intersectionPoints.put(geometry, geometryIntersectionPoints);
        }
        for(Geometry geo:this._scene.getGeometries())
        {
            if(!(geo instanceof Sphere || geo instanceof Triangle)) {
                geometryIntersectionPoints = geo.findIntersections(ray);
                if (!geometryIntersectionPoints.isEmpty())
                    intersectionPoints.put(geo, geometryIntersectionPoints);
            }
        }
        return intersectionPoints;
    }

    private List<Geometry> getrelevantgeometries(Ray ray,List<Geometry> geometries , Key max,String planename,int k) {
        Key mainKey=_scene.getKeybyPoint(ray.get_point().addVectorToPoint(ray.get_vector()));
        Point3D tempp=_scene.getPoint3DbyKey(mainKey);
        while(!(mainKey.isgreater(max)||k==0)) {
            List<Geometry> current = this._scene.getCubemap().get(mainKey);
            if (current != null) {
                for (Geometry g : current) {
                    geometries.add(g);
                }
            }
            Vector to = null, up = null, right = null;
            double cx = 0, cy = 0, cz = 0;
            Point3D cubep = null;
            switch (planename) {
                case "front":
                    to = this._scene.getCamera().getToward();
                    right = this._scene.getCamera().getRight();
                    up = this._scene.getCamera().getUp();
                    cx = this._scene.get_cubeDx();
                    cy = this._scene.get_cubeDy();
                    cz = this._scene.get_cubeDz();
                    cubep = new Point3D(tempp);
                    break;
                case "up":
                    to = this._scene.getCamera().getUp().multipliedbyScalar(-1);
                    right = this._scene.getCamera().getRight();
                    up = this._scene.getCamera().getToward();
                    cx = this._scene.get_cubeDz();
                    cy = this._scene.get_cubeDy();
                    cz = this._scene.get_cubeDx();
                    cubep = new Point3D(tempp.addVectorToPoint(this._scene.getCamera().getToward().multipliedbyScalar(cz)));
                    break;
                case "down":
                    to = this._scene.getCamera().getUp();
                    right = this._scene.getCamera().getRight();
                    up = this._scene.getCamera().getToward().multipliedbyScalar(-1);
                    cx = this._scene.get_cubeDz();
                    cy = this._scene.get_cubeDy();
                    cz = this._scene.get_cubeDx();
                    cubep = new Point3D(tempp.addVectorToPoint(this._scene.getCamera().getUp().multipliedbyScalar(-cz)));
                    break;
                case "right":
                    to = this._scene.getCamera().getRight().multipliedbyScalar(-1);
                    right = this._scene.getCamera().getToward();
                    up = this._scene.getCamera().getUp();
                    cx = this._scene.get_cubeDy();
                    cy = this._scene.get_cubeDx();
                    cz = this._scene.get_cubeDz();
                    cubep = new Point3D(tempp.addVectorToPoint(this._scene.getCamera().getRight().multipliedbyScalar(cx)));
                    break;
                case "left":
                    to = this._scene.getCamera().getRight();
                    right = this._scene.getCamera().getToward().multipliedbyScalar(-1);
                    up = this._scene.getCamera().getUp();
                    cx = this._scene.get_cubeDy();
                    cy = this._scene.get_cubeDx();
                    cz = this._scene.get_cubeDz();
                    cubep = new Point3D(tempp.addVectorToPoint(this._scene.getCamera().getToward().multipliedbyScalar(cy)));
                    break;
                case "end":
                    to = this._scene.getCamera().getToward().multipliedbyScalar(-1);
                    right = this._scene.getCamera().getRight().multipliedbyScalar(-1);
                    up = this._scene.getCamera().getUp();
                    cx = this._scene.get_cubeDx();
                    cy = this._scene.get_cubeDy();
                    cz = this._scene.get_cubeDz();
                    cubep = new Point3D(tempp.addVectorToPoint(this._scene.getCamera().getToward().multipliedbyScalar(cx)).addVectorToPoint(this._scene.getCamera().getToward().multipliedbyScalar(cy)));
                    break;
            }
            double isup = ray.get_vector().ScalarProduct(up);
            double isright = ray.get_vector().ScalarProduct(right);
            double isto = ray.get_vector().ScalarProduct(to);
            Point3D next = null;
            String str = "";
            List<Point3D> listpoints = new ArrayList<>();
            if (isup == 0 && isright == 0) {
                next = ray.get_point().addVectorToPoint(ray.get_vector().multipliedbyScalar(cx));
                str = "end";
            } else {
                if (isright == 0) {
                    if (isup > 0) {
                        listpoints.add(new Plane(cubep, up, Color.BLACK, new Material(0, 0, 0, 0, 0)).findIntersections(ray).get(0));
                        listpoints.add(new Plane(cubep.addVectorToPoint(to.multipliedbyScalar(cx)), to, Color.BLACK, new Material(0, 0, 0, 0, 0)).findIntersections(ray).get(0));
                        if (ray.get_point().distance(listpoints.get(0)) < ray.get_point().distance(listpoints.get(1))) {
                            next = listpoints.get(0);
                            str = "up";
                        } else {
                            next = listpoints.get(1);
                            str = "end";
                        }

                    } else {
                        listpoints.add(new Plane(cubep.addVectorToPoint(to.multipliedbyScalar(cx)), to, Color.BLACK, new Material(0, 0, 0, 0, 0)).findIntersections(ray).get(0));
                        listpoints.add(new Plane(cubep.addVectorToPoint(up.multipliedbyScalar(-cz)), up, Color.BLACK, new Material(0, 0, 0, 0, 0)).findIntersections(ray).get(0));
                        if (ray.get_point().distance(listpoints.get(0)) < ray.get_point().distance(listpoints.get(1))) {
                            next = listpoints.get(0);
                            str = "end";
                        } else {
                            next = listpoints.get(1);
                            str = "down";
                        }
                    }
                } else if (isup == 0) {
                    if (isright < 0) {
                        listpoints.add(new Plane(cubep.addVectorToPoint(to.multipliedbyScalar(cx)), to, Color.BLACK, new Material(0, 0, 0, 0, 0)).findIntersections(ray).get(0));
                        listpoints.add(new Plane(cubep, right, Color.BLACK, new Material(0, 0, 0, 0, 0)).findIntersections(ray).get(0));
                        if (ray.get_point().distance(listpoints.get(0)) < ray.get_point().distance(listpoints.get(1))) {
                            next = listpoints.get(0);
                            str = "end";
                        } else {
                            next = listpoints.get(1);
                            str = "left";
                        }
                    } else {
                        listpoints.add(new Plane(cubep.addVectorToPoint(to.multipliedbyScalar(cx)), to, Color.BLACK, new Material(0, 0, 0, 0, 0)).findIntersections(ray).get(0));
                        listpoints.add(new Plane(cubep.addVectorToPoint(right.multipliedbyScalar(cy)), right, Color.BLACK, new Material(0, 0, 0, 0, 0)).findIntersections(ray).get(0));
                        if (ray.get_point().distance(listpoints.get(0)) < ray.get_point().distance(listpoints.get(1))) {
                            next = (listpoints.get(0));
                            str = "end";
                        } else {
                            next = listpoints.get(1);
                            str = "right";
                        }
                    }
                } else {
                    Map<Point3D, String> temp = new HashMap<>();
                    List<Point3D> inters = new ArrayList<>();
                    inters = new Plane(cubep.addVectorToPoint(to.multipliedbyScalar(cx)), to, Color.BLACK, new Material(0, 0, 0, 0, 0)).findIntersections(ray);
                    if (!inters.isEmpty())
                        temp.put(inters.get(0), "end");
                    inters.clear();

                    if (isup > 0) {
                        inters = new Plane(cubep, up, Color.BLACK, new Material(0, 0, 0, 0, 0)).findIntersections(ray);
                        if (!inters.isEmpty())
                            temp.put(inters.get(0), "up");
                        inters.clear();
                    } else {
                        inters = new Plane(cubep.addVectorToPoint(up.multipliedbyScalar(-cz)), up, Color.BLACK, new Material(0, 0, 0, 0, 0)).findIntersections(ray);
                        if (!inters.isEmpty())
                            temp.put(inters.get(0), "down");
                        inters.clear();
                    }
                    if (isright < 0) {
                        inters = new Plane(cubep, right, Color.BLACK, new Material(0, 0, 0, 0, 0)).findIntersections(ray);
                        if (!inters.isEmpty())
                            temp.put(inters.get(0), "left");
                    } else {
                        inters = new Plane(cubep.addVectorToPoint(right.multipliedbyScalar(cy)), right, Color.BLACK, new Material(0, 0, 0, 0, 0)).findIntersections(ray);
                        if (!inters.isEmpty())
                            temp.put(inters.get(0), "right");

                    }
                    double dist = Double.MAX_VALUE;
                    for (Map.Entry<Point3D, String> p : temp.entrySet()) {
                        double m = p.getKey().distance(ray.get_point());
                        if (m < dist) {
                            dist = m;
                            next = p.getKey();
                            str = p.getValue();
                        }
                    }
                }
            }
            ray=new Ray(ray.get_vector(),next);
            planename=Raytrace.arr.get(planename).get(str);
            k--;
        }
        return geometries;
        //return getrelevantgeometries(new Ray(,geometries,max,,k--);
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
                if (lightsource instanceof PointLight && p.distance(point) > ((PointLight) lightsource).getPosition().distance(point))
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

}
