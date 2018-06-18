package scene;

import primitives.Point3D;
import primitives.Vector;

public class Key {
    protected int _x;
    protected int _y;
    protected int _z;

    public Key(int x,int y,int z){
        this._x=x;
        this._y=y;
        this._z=z;
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
