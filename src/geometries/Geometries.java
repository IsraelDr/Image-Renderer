package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

public class Geometries extends Geometry {
    protected List<Geometry> _geometries = new ArrayList<Geometry>();

    public void addGeometry(Geometry g) {
        _geometries.add(g);
    }

    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> points = new ArrayList<Point3D>();

        for (Geometry g : _geometries) {
            for (Point3D p : g.findIntersections(ray))
                points.add(p);
        }
        return points;
    }

    public List<Geometry> getGeometries() {
        return _geometries;
    }
}
