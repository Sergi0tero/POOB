package world;

import shapes.*;
import java.util.ArrayList;

/**
 * Write a description of class NationNormal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NationNormal extends Nation{
    /**
     * llama al constructor de la clase super
     */
    public NationNormal(String color, int x, int y, int armies, int index, ArrayList lista){
        super(color, x, y, armies, "normal", index, lista);
    }
    
    /**
     * crea una nueva nacion Normal
     */
    public void createLand(){
        land = new Rectangle();
        land.changeSize(50, 50);
        land.moveTo(xPosition, yPosition);
        land.changeColor(color);
    }
    
    /**
     * Suma 1 a la armada de la nacion
     */
    public void sumArmy(){
        defenseArmy.sumArmy();
    }
    
    /**
     * llama al metodo de army para que reste 1 a el ataque
     */
    public void substractAttack(){
        army.substractAttack();
    }
    
    /**
     * llama al metodo de army para que sume 1 a el ataque
     */
    public void sumAttack(){
        army.sumAttack();
    }
    
    /**
     * Resto 1 a la armada de la nacion
     */
    public void substractArmy(){
        defenseArmy.substractArmy();
    }
    
    /**
     * hace visble a la nacion
     */
    public void makeLandVisible(){
        land.makeVisible();
    }
    
    /**
     * hace invisble a la nacion
     */
    public void makeLandInvisible(){
        land.makeInvisible();
    }
}

