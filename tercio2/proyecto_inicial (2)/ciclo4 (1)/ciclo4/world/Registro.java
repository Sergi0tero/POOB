package world;

public class Registro
{
    String object;
    String type;
    String action;
    String color;
    String locationA;
    String locationB;
    int cost;
    int x;
    int y;
    int armies;
    
    /**
     * constructor del objeto registro 
     * @param String que tipo de objeto es
     * @param String de que subtipo es el objeto
     * @param String que accion se realizo con ese objeto
     * @param String Color de la nacion 
     * @param int coordenada x de la nacion
     * @param int coordenada y de la nacion
     * @param int cuantas armadas tiene la nacion
     * @param String nacion 1 de la ruta 
     * @param String nacion 2 de la ruta
     * @param int costo de la ruta 
     */
    public Registro(String object, String type, String action,String color, int x, int y, int armies, String locationA, String locationB, int cost)
    {
     this.object = object;
        this.type = type;
        this.action = action;
        this.color = color;
        this.x = x;
        this.y = y;
        this.armies = armies;
        this.locationA = locationA;
        this.locationB = locationB;
        this.cost = cost;
    }    
}
