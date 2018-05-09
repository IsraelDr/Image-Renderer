package primitives;


/**
 * Material of each geometry
 */
public class Material {
    protected double _Kd;
    protected double _Ks;
    protected int _nShininess;

    //******************************CONSTRUCTORS********************************//
    /**
     * ctor
     * @param Kd Kd
     * @param Ks Ks
     * @param Shininess Shininess
     */
    public Material(double Kd,double Ks,int Shininess) {
        this._Kd=Kd;
        this._Ks=Ks;
        this._nShininess=Shininess;
    }

    /**
     * copy ctor
     * @param material Material
     */
    public Material(Material material) {
        this._Kd=material._Kd;
        this._Ks=material._Ks;
        this._nShininess=material._nShininess;
    }

    // ***************** Getters/Setters ********************** //

    public double getKd() {
        return _Kd;
    }

    public double getKs() {
        return _Ks;
    }

    public int getShininess() {
        return _nShininess;
    }
}
