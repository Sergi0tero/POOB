import java.awt.*;

/**
 * Write a description of class line here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Route
{
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int cost;
    private Line route;
    private Line secondLine;
    private String color;
    private boolean visible;
    
    /**
     * Constructor for objects of class line
     */
    public Route(int x1, int y1, int x2, int y2, int cost){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.cost = cost;
        route = new Line(x1, y1, x2, y2);
        doubleWay();
        visible = false;
    }
    
    public Route(Nation nation1, Nation nation2){
        this.x1 = nation1.getCenterx();
        this.y1 = nation1.getCentery();
        this.x2 = nation2.getCenterx();
        this.y2 = nation2.getCentery();
        this.cost = cost;
        route = new Line(x1, y1, x2, y2);
        doubleWay();
        visible = false;
    }
    
    private void doubleWay(){
        secondLine = new Line(x2, y2, x1, y1);
    }
    
    /**
     * Make this rectangle visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        route.makeVisible();
        secondLine.makeVisible();
    }
    
    /**
     * Make this rectangle invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        route.makeInvisible();
        secondLine.makeInvisible();
    }
    
    public int get(int cost){
        return cost;
    }
    
    public boolean isVisible(){
        return visible;
    }
    
    public int getX1(){
        return x1;
    }
    
    public int getY1(){
        return y1;
    }
    
    public int getX2(){
        return x2;
    }
    
    public int getY2(){
        return y2;
    }
    
    public int getCost(){
        return cost;
    }
    
    public boolean isRoute(int X1, int Y1, int X2, int Y2){
        if (X1 == x1 && X2 == x2 && Y1 == y1 && Y2 == y2){
            return true;
        }
        if (X1 == x2 && Y1 == y2 && X2 == x1 && Y2 == y1){
            return true;
        }
        return false;
    }
}
