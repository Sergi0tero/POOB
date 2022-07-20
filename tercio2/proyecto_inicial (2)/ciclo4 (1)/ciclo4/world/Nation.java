package world;
import shapes.*;
import java.util.ArrayList;

/**
 * Write a description of class Nation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Nation
{
    private boolean visible;
    private int defense;
    private int attack;
    
    protected ArmyDefense defenseArmy;
    protected int index;
    protected String type;
    protected String color;
    protected Rectangle land;
    protected Army army;
    protected int xPosition;
    protected int yPosition;

    /**
     * Constructor for objects of class Nation
     */
    public Nation(String color, int x, int y, int armies, String type, int index, ArrayList lista){
        this.color = color;
        this.defense = armies;
        this.type = type;
        this.index = index;
        xPosition = x;
        yPosition = y;
        visible = false;
        createLand();
        defenseArmy = new ArmyDefense(xPosition, yPosition, color, armies);
        army = new ArmyNormal(xPosition, yPosition, color, lista);
        armyVisible();
        if(defense == 0){
            defenseArmy.conquer();
        }
    }
    
    public abstract void createLand();
    public abstract void sumAttack();
    public abstract void sumArmy();
    public abstract void substractAttack();
    public abstract void substractArmy();
    public abstract void makeLandVisible();
    public abstract void makeLandInvisible();
    
    /**
     * setea el tipo del army nuevo que se va a insertar
     * @param String tipo del nuevo army
     * @param ArrayList de las armys que tiene actualmente la nacion
     */
    public void setArmyType(String type, ArrayList lista){
        if(type == "normal"){
            army = new ArmyNormal(xPosition, yPosition, color, lista);
        }
        else if(type == "friendly"){
            army = new ArmyFriendly(xPosition, yPosition, color, lista);
        }
        else if(type == "fearful"){
            army = new ArmyFearful(xPosition, yPosition, color, lista);
        }
    }
    
    /**
     * Devuelve la coordenada x del centro del rectangulo
     * Return int coordenada x
     */
    public int getCenterx(){
        return xPosition + 25;
    }
    
    /**
     * Devuelve la coordenada x del centro del rectangulo
     * Return int coordenada y
     */
    public int getCentery(){
        return yPosition + 25;
    }
    
    /**
     * Devuelve la cantidad de armadas que tiene de defensa la nacion
     * @return la cantidad de armadas
     */
    public int getArmy(){
        return defenseArmy.getArmy();
    }
    
    /**
     * Devuelveel tipo de la nacion
     * @return String tipo de la nacion
     */
    public String getNationtype(){
        return type;
    }
    
    /**
     * Devuelve el tipo de army
     * @return Strin tipo de army
     */
    public String gettipo(){
        return army.gettype();
    }
    
    /**
     * Devuelve el indice de la nacion
     * @return indice de la nacion
     */
    public int getIndex(){
        return index;
    }
    
    /**
     * Devuelve la cantidad de ataque que tiene la nacion
     * @return army
     */
    public int getAttack(){
        return army.getAttack();
    }
    
    /**
     * Devuelve la lista de armys de la nacion
     * @return ArrayList de los armys que tiene la nacion
     */
    public ArrayList<Rectangle> getlista(){
        try {
            return army.getlista();
        } catch(NullPointerException e){
            ArrayList newA = new ArrayList();
            return newA;
        }
    }
    
    /**
     * Devuelve el ultimo army de ataque de la nacion
     *  @return Rectangle ultimo army de ataque
     */
    public Rectangle getlast(){
        try {
            return army.getultimo();
        } catch(NullPointerException e){
            Rectangle newA = new Rectangle();
            return newA;
        }
    }
    
    /**
     * Devuelve si la nacion esta conquistada
     * @return boolean Si la nacion esta conquistada
     */
    public boolean isConquered(){
        return defenseArmy.isConquered();
    }
    
    /**
     * Devuelve la posicion x de la nacion
     * @return posicion x de la nacion
     */
    public int getX(){
        return land.getX();
    }
    
    /**
     * Devuelve la posicion y de la nacion
     * @return posicion y de la nacion
     */
    public int getY(){
        return land.getY();
    }
    
    /**
     * Devuelve el color de la nacion
     * @return color String color de la nacion
     */
    public String getColor(){
        return color;
    }
    
    /**
     * Devuelve el tipo de la nacion
     * @return tipo de la nacion
     */
    public String getType(){
        return type;
    }
    
    /**
     * Devuelve si la nacion es visible o no
     * @return visible boolean True: la nacion es visible, False: la nacion no es visible
     */
    public boolean isVisible(){
        return visible;
    }
    
    /**
     * Hace visible la armada de la nacion
     */
    private void armyVisible(){
        defenseArmy.makeVisible();
    }
    
    /**
     * Hace visibile la nacion
     */
    public void makeVisible(){
        makeLandVisible();
        defenseArmy.makeVisible();
        visible = true;
    }
    
    /**
     * Hace invisibile la nacion
     */
    public void makeInvisible(){
        makeLandInvisible();
        defenseArmy.makeInvisible();
        visible = false;
    }
}

