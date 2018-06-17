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

    public int getX() {
        return _x;
    }

    public void setX(int _x) {
        this._x = _x;
    }

    public void setY(int _y) {
        this._y = _y;
    }

    public void setZ(int _z) {
        this._z = _z;
    }

    public int getY() {
        return _y;
    }

    public int getZ() {
        return _z;
    }
    public Boolean isgreater(Key max){
        return this.getX()>max.getX()||this.getY()>max.getY()||this.getZ()>max.getZ();
    }
}
