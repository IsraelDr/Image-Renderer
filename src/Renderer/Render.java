package Renderer;


import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

import java.util.List;


/**
 * Render
 */
public class Render {
    protected Scene _scene;
    protected ImageWriter _imageWriter;

    public Render(Scene scene,ImageWriter imageWriter) {
        this._scene=scene;
        this._imageWriter=new ImageWriter(imageWriter);
    }
    public void renderImage(){
        int k;
        for (int i = 1; i < 500; i++) {
            for (int j = 1; j < 500; j++) {
                if(i==250&&j==250)
                    k=0;
                Ray ray=_scene.getCamera().constructRayThroughPixel(_imageWriter.getNx(),_imageWriter.getNy(),i,j,_scene.getDistance(),_imageWriter.getWidth(),_imageWriter.getHeight());
                List<Point3D> intersectionPoints=_scene.getGeometries().findIntersections(ray);
                if(intersectionPoints.isEmpty())
                    _imageWriter.writePixel(i,j,_scene.getBackgroundColor());
                else {
                    Point3D closestPoint = getClosestPoint(intersectionPoints);
                    _imageWriter.writePixel(i,j,calcColor(closestPoint).getColor());
                }
            }
        }
        //this.printGrid(50);
        //_imageWriter.writeToimage();
    }

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

    private Color calcColor(Point3D point){
        return _scene.getAmbientlight().getIntensity();

    }
    private Point3D getClosestPoint(List<Point3D> points){
        double distance=0;
        Point3D temp=null;
        for (Point3D p:points) {
            if(temp==null)
            {
                temp=p;
                distance=_scene.getCamera().getP0().distance(p);
                continue;
            }
            if (_scene.getCamera().getP0().distance(p) < distance) {
                distance = _scene.getCamera().getP0().distance(p);
                temp=p;
            }
        }
        return temp;
    }
}
