package geometries;

import primitives.*;

import java.util.List;

public class Quadrilateral extends Geometry {
    private Triangle triangle1;
    private Triangle triangle2;

    // ***************** Constructors ********************** //

    /**
     * c'tor
     *
     * @param first    first coordinate
     * @param second   second coordinate
     * @param third    third coordinate
     * @param fourth   fourth coordinate
     * @param emission emission
     * @param material material
     */
    public Quadrilateral(Point3D first, Point3D second, Point3D third, Point3D fourth, Color emission, Material material) {
        super(emission, material);
        triangle1 = new Triangle(first, second, third, _emission, _material);
        triangle2 = new Triangle(second, third, fourth, _emission, _material);
    }

    /**
     * copy c'tor
     * @param quadrilateral The Quadrilateral that will be copied
     */
    public Quadrilateral(Quadrilateral quadrilateral){
        super(quadrilateral);
        triangle1 = new Triangle(quadrilateral.getTriangle1());
        triangle2 = new Triangle(quadrilateral.getTriangle2());
    }
    // ***************** Getters/Setters ********************** //
    public Point3D get_first() {
        return triangle1.get_point();
    }

    public Point3D get_second() {
        return triangle1.get_second();
    }

    public Point3D get_third() {
        return triangle1.get_third();
    }

    public Point3D get_fourth() {
        return triangle2.get_third();
    }
    private Triangle getTriangle1(){
        return new Triangle(get_first(),get_second(),get_third(),triangle1.getEmission(),triangle1.getMaterial());
    }
    private Triangle getTriangle2(){
        return new Triangle(get_second(),get_third(),get_fourth(),triangle2.getEmission(),triangle2.getMaterial());
    }
    // ***************** Operations ******************** //

    /**
     * @param ray ray
     * @return arraylist of intersections
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> points = triangle1.findIntersections(ray);
        List<Point3D> points2 = triangle2.findIntersections(ray);
        if (points.size() == 0)
            return points2;
        if (points2.size() > 0)
            points.addAll(points2);
        return points;
    }

    @Override
    public Vector getNormal(Point3D temp) {
        Vector vector = new Vector(temp.vectorSubstract(triangle1.get_point()));
        if (vector.ScalarProduct(triangle1.get_vector()) == 0)
            return triangle1.getNormal(temp);
        return triangle2.getNormal(temp);
    }
}
