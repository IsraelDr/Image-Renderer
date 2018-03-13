package elements;

import primitives.Point3D;
import primitives.Vector;

/**
 * Class of Camera
 */
public class Camera {
    Point3D _place;
    Vector _front;
    Vector _right;
    Vector _up;
    /**
     * ctor
     * @param place of Camera
     * @param front vector to front
     * @param right vector to right
     */
    public Camera(Point3D place, Vector front, Vector right){
            try {
                if(front.ScalarProduct(right)!=0||front.size()==0||right.size()==0)
                    throw new Exception("The two vectors are not orthogonal");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return;
            }
        _place=place;
        _front=front;
        _right=right;
        _up=_front.vectorProduct(_right);
    }
}
