package elements;

import primitives.Point3D;
import primitives.Vector;
import primitives.Ray;
/**
 * Class of Camera
 */
public class Camera {
    private Point3D _p0;
    private Vector _toward;
    private Vector _right;
    private Vector _up;
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
        _toward=front.NormalVector();
        _right=right.NormalVector();
        _up=(_toward.vectorProduct(_right)).NormalVector();
    }

    /**
     *
     * copy ctor
     * @param camera The Camera that will be copied
     */
    public Camera(Camera camera){
        this._p0=new Point3D(camera._p0);
        this._toward=new Vector(camera._toward);
        this._right=new Vector(camera._right);
        this._up=new Vector(camera._up);
    }
    //****************getter/setter*************//

    /**
     * getter
     * @return p0
     */
    public Point3D getP0() {
        return _p0;
    }

    /**
     * GETTER
     * @return Returns towards Vector
     */
    public Vector getToward() {
        return _toward;
    }

    /**
     * Right
     * @return
     */
    public Vector getRight() {
        return _right;
    }

    /**
     * get up
     * @return
     */
    public Vector getUp() {
        return _up;
    }
    //***************Operations*************************

    /**
     *
     * @param Nx - number of pixels in view plane in line X
     * @param Ny - number of pixels in view plane in line Y
     * @param i - describes the pixel we want in line x starts from 1
     * @param j - describes the pixel we want in line y starts from 1
     * @param screenDistance distance of the screen
     * @param screenWidth - width of screen
     * @param screenHeight - height of screen
     * @return Ray from _P0 to pixel
     */
    public Ray constructRayThroughPixel(int Nx, int Ny,int i, int j, double screenDistance, double screenWidth, double screenHeight) {
        return new Ray((constructRayThroughPixelhelp(Nx,Ny,i,j,screenDistance,screenWidth,screenHeight)).get_vector(),_p0);
    }
    public Ray constructRayThroughPixelhelp(int Nx, int Ny,int i, int j, double screenDistance, double screenWidth, double screenHeight){
        Point3D Pc=new Point3D(_p0.addVectorToPoint(_toward.multipliedbyScalar(screenDistance)));
        double Rx=screenWidth/Nx;
        double Ry=screenHeight/Ny;
        Point3D Pij=Pc.addVectorToPoint((_right.multipliedbyScalar(Rx*(i-((Nx+1)/2)))).add(_up.multipliedbyScalar(Ry*(((Ny+1)/2)-j))));
        return new Ray(new Vector(Pij.get_x()-_p0.get_x(),Pij.get_y()-_p0.get_y(),Pij.get_z()-_p0.get_z()),Pij);
    }
}
