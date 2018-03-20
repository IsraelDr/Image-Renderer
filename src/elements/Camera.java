package elements;

import primitives.Point3D;
import primitives.Vector;
import primitives.Ray;
/**
 * Class of Camera
 */
public class Camera {
    Point3D _p0;
    Vector _toward;
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
                    throw new Exception("The two vectors are not orthonormal");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return;
            }
        _p0=place;
        _toward=front;
        _right=right;
        _up=_toward.vectorProduct(_right);
    }

    //***************Operations*************************
    public Ray constructRayThroughPixel(int Nx, int Ny,int i, int j, double screenDistance, double screenWidth, double screenHeight){

        return null;
    }
}
