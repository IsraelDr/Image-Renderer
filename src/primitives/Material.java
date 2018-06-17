package primitives;


/**
 * Material of each geometry
 */
public class Material {
    protected double _Kd;
    protected double _Ks;
    protected double _Kr;
    protected double _Kt;
    protected int _nShininess;

    //******************************CONSTRUCTORS********************************//
    /**
     * ctor
     * @param Kd Diffuse
     * @param Ks Specular
     * @param Kr reflected
     * @param Kt Transparent
     * @param Shininess Shininess
     */
    public Material(double Kd,double Ks,double Kr,double Kt,int Shininess) {
        this._Kd=Kd;
        this._Ks=Ks;
        this._Kr=Kr;
        this._Kt=Kt;
        if(Shininess%2==0)
            Shininess++;
        this._nShininess=Shininess;
    }

    /**
     * copy ctor
     * @param material Material
     */
    public Material(Material material) {
        this._Kd=material._Kd;
        this._Ks=material._Ks;
        this._Kr=material._Kr;
        this._Kt=material._Kt;
        this._nShininess=material._nShininess;
    }

    // ***************** Getters/Setters ********************** //

    public double getKd() {
        return _Kd;
    }

    public double getKs() {
        return _Ks;
    }

    public double getKr() { return _Kr; }

    public double getKt() { return _Kt; }

    public int getShininess() {
        return _nShininess;
    }
}
