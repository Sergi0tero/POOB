package world;

import shapes.*;
import java.util.ArrayList;

/**
 * Write a description of class NationWalled here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NationWalled extends Nation{
    private ArrayList<Circle> circles;
    
    /**
     * llama al constructor de la clase super
     */
    public NationWalled(String color, int x, int y, int armies, int index, ArrayList lista){
        super(color, x, y, armies, "walled", index, lista);
    }
    
    /**
     * crea una nueva nacion Walled
     */
    public void createLand(){
        land = new Rectangle();
        land.changeSize(50, 50);
        land.moveTo(xPosition, yPosition);
        land.changeColor(color);
        circles = new ArrayList<Circle>();
        int circleX = xPosition, circleY = yPosition - 5;
        for (int i = 0; i < 5; i++){
            circles.add(new Circle());
        }
        for (Circle circle : circles){
            circle.moveTo(circleX, circleY);
            circle.changeColor(color);
            circleX += 10;
        }
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
    public void substractAttack(){}
    
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
        for (Circle circle : circles){
            circle.makeVisible();
        }
    }
    
    /**
     * hace invisble a la nacion
     */
    public void makeLandInvisible(){
        land.makeInvisible();
        for (Circle circle : circles){
            circle.makeInvisible();
        }
    }
}
