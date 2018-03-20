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

    /**
     *
     * @param Nx - number of pixels in view plane in line X
     * @param Ny - number of pixels in view plane in line Y
     * @param i - describes the pixel we want in line x
     * @param j - describes the pixel we want in line y
     * @param screenDistance distance of the screen
     * @param screenWidth - width of screen
     * @param screenHeight - height of screen
     * @return Ray from _P0 to pixel
     */
    public Ray constructRayThroughPixel(int Nx, int Ny,int i, int j, double screenDistance, double screenWidth, double screenHeight){
        Point3D Pc=new Point3D(_p0.addVectorToPiont(_toward.multipliedbyScalar(screenDistance)));
        double Rx=screenWidth/Nx;
        double Ry=screenHeight/Ny;
        Point3D Pij=Pc.addVectorToPiont((_right.multipliedbyScalar(Rx*(i-((Nx+1)/2)))).add(_up.multipliedbyScalar(Ry*((Ny+1)/2)-j)));
        return new Ray(new Vector(Pij.get_x()-_p0.get_x(),Pij.get_y()-_p0.get_y(),Pij.get_z()-_p0.get_z()),_p0);
    }
}
