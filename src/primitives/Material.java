package primitives;


/**
 * Material of each geometry
 */
public class Material {
    protected double _Kd;
    protected double _Ks;
    protected int _nShininess;

    /**
     * ctor
     * @param Kd
     * @param Ks
     * @param nShininess
     */
    public Material(double Kd,double Ks,int nShininess) {
        this._Kd=Kd;
        this._Ks=Ks;
        this._nShininess=nShininess;
    }

    /**
     * copy ctor
     * @param material
     */
    public Material(Material material) {
        this._Kd=material._Kd;
        this._Ks=material._Ks;
        this._nShininess=material._nShininess;
    }
}
