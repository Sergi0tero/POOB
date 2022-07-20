package world;


/**
 * Write a description of class weak here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RouteDealer extends Route
{
    /**
     * Constructor for objects of class weak
     */
    public RouteDealer(Nation nation1, Nation nation2, int cost){
        super(nation1, nation2, cost);
        route.changeColor("green");
        this.type = "dealer";
    }
    
    /**
     * el costo de la ruta se vuleve el doble del costo normal
     */
    public void crossed(){
        cost *= 2;
    }
}