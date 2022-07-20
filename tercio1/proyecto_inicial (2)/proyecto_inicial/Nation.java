
/**
 * Write a description of class Nation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Nation
{
    private Rectangle land;
    private String color;
    private int xPosition;
    private int yPosition;
    private boolean visible;
    private int armies;
    private Army army;

    /**
     * Constructor for objects of class Nation
     */
    public Nation(String color, int x, int y, int armies){
        this.color = color;
        this.armies = armies;
        xPosition = x;
        yPosition = y;
        visible = false;
        land = new Rectangle();
        land.changeSize(50, 50);
        land.moveTo(x, y);
        land.changeColor(color);
        army = new Army(x, y, color, armies);
        armyVisible();
        if(armies == 0){
            army.conquer();
        }
    }
    
    public void deconquer(){
        army.deconquer();
    }
    
    public int getCenterx(){
        return xPosition + 25;
    }
    
    public int getCentery(){
        return yPosition + 25;
    }
    
    /**
     * Devuelve la cantidad de armadas que tiene el mundo
     * @return la cantidad de armadas
     */
    public int getArmy(){
        return army.getArmy();
    }
    
    /**
     * Suma 1 a la armada de la nacion
     */
    public void sumArmy(){
        army.sumArmy();
    }
    
    /**
     * Resto 1 a la armada de la nacion
     */
    public void substractArmy(){
        army.substractArmy();
    }
    
    /**
     * Devuelve si la nacion esta conquistada
     * @return boolean Si la nacion esta conquistada
     */
    public boolean isConquered(){
        return army.isConquered();
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
        army.makeVisible();
    }
    
    /**
     * Hace visibile la nacion
     */
    public void makeVisible(){
        land.makeVisible();
        army.makeVisible();
        visible = true;
    }
    
    /**
     * Hace invisibile la nacion
     */
    public void makeInvisible(){
        army.makeInvisible();
        land.makeInvisible();
        visible = false;
    }
    
}
