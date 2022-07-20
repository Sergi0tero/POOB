package world;


/**
 * Write a description of class weak here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RouteWeak extends Route
{
    /**
     * Constructor for objects of class weak
     */
    public RouteWeak(Nation nation1, Nation nation2, int cost){
        super(nation1, nation2, cost);
        route.changeColor("red");
        this.type = "weak";
    }
}
