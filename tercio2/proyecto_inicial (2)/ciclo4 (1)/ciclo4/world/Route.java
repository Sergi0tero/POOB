package world;
import shapes.*;
import java.awt.*;

/**
 * Write a description of class line here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Route
{
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private String color;
    private boolean visible;
    private Nation nation1;
    private Nation nation2;
    
    protected Line route;
    protected int cost;
    protected String type;
    
    /**
     * constructor del objeto ruta
     */
    public Route(Nation nation1, Nation nation2, int cost){
        this.x1 = nation1.getCenterx();
        this.y1 = nation1.getCentery();
        this.x2 = nation2.getCenterx();
        this.y2 = nation2.getCentery();
        this.cost = cost;
        this.nation1 = nation1;
        this.nation2 = nation2;
        route = new Line(x1, y1, x2, y2);
        visible = false;
    }
    
    /**
     * Make this rectangle visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        route.makeVisible();
    }
    
    /**
     * Make this rectangle invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        route.makeInvisible();
    }
    
    public int get(int cost){
        return cost;
    }
    
    /**
     * Devuelve el objeto Nacion de la nacion1
     * @return Nation  nacion1
     */
    public Nation getNation1(){
        return nation1;
    }
    
    /**
     * Devuelve el objeto Nacion de la nacion2
     * @return Nation  nacion1
     */
    public Nation getNation2(){
        return nation2;
    }
    
    /**
     * Devuelve true si la nacion es visible
     * @return boolean visibilidad de la nacion
     */
    public boolean isVisible(){
        return visible;
    }
    
    /**
     * Devuelve la coordenada x de inicio de la ruta
     * * @return int coordenada x
     */
    public int getX1(){
        return x1;
    }
    
    /**
     * Devuelve la coordenada y de inicio de la ruta
     * * @return int coordenada y
     */
    public int getY1(){
        return y1;
    }
    
    /**
     * Devuelve la coordenada x de final de la ruta
     * * @return int coordenada x
     */
    public int getX2(){
        return x2;
    }
    
    /**
     * Devuelve la coordenada y de final de la ruta
     * @return int coordenada y
     */
    public int getY2(){
        return y2;
    }
    
    /**
     * Devuelve el typo de la ruta
     * @return String tipo de ruta
     */
    public String getType(){
        return type;    
    }
    
    /**
     * Devuelve el costo de la ruta
     * @return 
     */
    public int getCost(){
        return cost;
    }
    
    public void crossed(){
        
    }
    
    /**
     * Devuelve si la ruta es existente
     * @param coordenada x1
     * @param coordenada y1
     * @param coordenada x2
     * @param coordenada y1
     * @return boolean true si existe la ruta 
     */
    public boolean isRoute(int X1, int Y1, int X2, int Y2){
        if (X1 == x1 && X2 == x2 && Y1 == y1 && Y2 == y2){
            return true;
        }
        if (X1 == x2 && Y1 == y2 && X2 == x1 && Y2 == y1){
            return true;
        }
        return false;
    }
    
    /**
     * Devuelve si la ruta es existente
     * @param nacion 1
     * @param nacion 2
     * @return boolean true si existe la ruta 
     */
    public boolean isRoute(Nation n1, Nation n2){
        if (n1 == nation1 && n2 == nation2){
            return true;
        }
        if (n2 == nation1 && n1 == nation2){
            return true;
        }
        return false;
    }
}
