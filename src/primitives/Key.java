package primitives;

public class Key {
    protected int _x;
    protected int _y;
    protected int _z;

    public Key(int x,int y,int z){
        this._x=x;
        this._y=y;
        this._z=z;
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
