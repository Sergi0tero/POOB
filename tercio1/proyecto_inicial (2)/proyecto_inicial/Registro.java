

public class Registro
{
    String object;
    String action;
    String color;
    String locationA;
    String locationB;
    int cost;
    int x;
    int y;
    int armies;
    
    public Registro(String object, String action,String color, int x, int y, int armies, String locationA, String locationB, int cost)
    {
     this.object = object;
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
