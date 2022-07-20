
/**
 * @author ECI, 2017 v01 2021 v02
 *
 */
public class Vector{

    public static final float MAXERROR = 0.00000000000001f;
    
    private float r;
    private Angulo theta;
    private float x;
    private float y;
    
    /**
     * Constructor del vector, en coordenadas polares
     * @param d longitud del vector
     * @param a angulo del vector
     */
    public Vector (float r, Angulo t) {
        this.theta = t;
        this.r = r;
        this.x = r * (float)Math.cos(theta.grados());
        this.y = r * (float)Math.sin(theta.grados());
    }
    
    /**
     * Constructor del vector, en coordenadas cartesianas
     * @param x coordenada x del vector
     * @param y coordenada y del vector
     */
    public Vector (float x, float y) {
        this.x = x;
        this.y = y;
        r = (float)Math.sqrt((x*x) + (y*y));
        Angulo theta = new Angulo((float)(Math.atan(y/x)), 1);
    }
    
    /**
     * Retorna la coordenada X del vector
     * @return coordenada X del vector
     */
    public float coordenadaX() {
        return x;
    }

    /**
     * Retorna la coordenada Y del Vector
     * @return coordenada Y del vector
     */
    public float coordenadaY() {
        return y;
    }
    
    public void setX(float X){
        x = X;
    }
    
    public void setY(float Y){
        y = Y;
    }
    
    /**
     * Retorna el angulo del vector
     * @return angulo del vector
     */
    public Angulo angulo (){
        return theta;
    }

    /**
     * Retorna la longitud del vector
     * @return 
     */
    public float longitud() {
        return r;
    }
    
    /**
     * Retorna la distancia entre este vector y otro vector
     * @return 
     */
    public float distancia(Vector otro) {
        float x1, y1, d;
        x1 = otro.coordenadaX();
        y1 = otro.coordenadaY();
        d = (float)Math.sqrt(Math.pow(x1 - x, 2) + Math.pow(y1 - y, 2));
        return d;
    }
    
    public float productoPunto(Vector v){
        return (x*v.coordenadaX()) + (y*v.coordenadaY());
    }
    
    public float proyeccionEscalar(Vector v){
        return (this.productoPunto(v)/(float)Math.sqrt(Math.pow(coordenadaX(), 2) + Math.pow(coordenadaY(), 2)));
    }
    
    public Vector proyeccionVectorial(Vector v){
        double modulo = Math.sqrt(Math.pow(v.coordenadaX(),2) + Math.pow(v.coordenadaY(),2));
        float num = this.productoPunto(v)/(float)Math.pow(modulo,2);
        Vector newVector = new Vector(num * v.x,num * v.y);
        return newVector;
    }
    
    /**
     * Compara este vector con otro. Serán iguales si la distancia entre ellos es menor que MAXERROR
     * @param v el vector a comparar con este
     */
    private boolean equals (Vector v) {
        return (distancia(v) < MAXERROR);
    }

    /** 
     * Compara si este Vector es igual al parametro (debe ser tambien un vector)
     */
    @Override
    public boolean equals (Object o) {
            return this.equals ((Vector) o);
    }
    
    /**
     * Translada el vector, dados los desplazamientos en dx, dy
     *
     * @param dx desplazamiento en el eje x
     * @param dy desplazamiento en el eje Y
     */
    public Vector traslade (float dx, float dy) {
        Vector nuevoVector = new Vector(x + dx, y + dy);
        return nuevoVector;
    }
    
    /**
     * Calcula el producto escalar
     * @param escalar El factor de multiplicación de la distancia respecto al centro
     * @return 
     */
    public Vector productoEscalar(float escalar) {
        r *= escalar;
        return this;
    }
    
    public Vector unitario(){
        float modulo = (float)Math.sqrt(Math.pow(coordenadaX(), 2) + Math.pow(coordenadaY(), 2));
        if (modulo != 1){
            this.x /= modulo;
            this.y /= modulo;
        }
        return this;
    }
    
    /**
     * Rota el vector el angulo dado, con respecto al origen. 
     * Es decir que el angulo resultante, es la suma del angulo dado con el angulo inicial del vector, 
     * y la distancia es la misma.
     */
    public Vector rote(Angulo a) {
        Angulo newAngulo = new Angulo((a.grados() + this.angulo().grados()), 1);
        theta = newAngulo;
        return this;
    }

    public  Vector sume(Vector v){
        x += v.coordenadaX();
        y += v.coordenadaY();
        return this;
    }
    
    public Vector reste(Vector v){
        x -= v.coordenadaX();
        y -= v.coordenadaY();
        return this;
    }
    
    public Vector multiplique(Vector v){
        x *= v.coordenadaX();
        y *= v.coordenadaY();
        return this;
    }
    
    /** 
     * Retorna una cadena que describe a este vector (en coordenadas polares)
     * P(r,theta)
     */
    @Override
    public String toString () {
        String  s = ("p" + "(" + String.valueOf(r) + "," + String.valueOf(theta) + ")");
        return s;
    }

}
