package world;
import shapes.*;
import java.util.ArrayList;

/**
 * Write a description of class NationAggressive here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import shapes.*;
import java.util.ArrayList;

/**
 * Write a description of class NationAggressive here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NationAggressive extends Nation{
    private ArrayList<Triangle> triangles;
    
    /**
     * llama al constructor de la clase super
     */
    public NationAggressive(String color, int x, int y, int armies, int index, ArrayList lista){
        super(color, x, y, armies, "aggressive", index, lista);
    }
    
    /**
     * crea una nueva nacion Aggressive
     */
    public void createLand(){
        land = new Rectangle();
        land.changeSize(50, 50);
        land.moveTo(xPosition, yPosition);
        land.changeColor(color);
        triangles = new ArrayList<Triangle>();
        int triangleX = xPosition + 5, triangleY = yPosition + 45;
        boolean black = true;
        for (int i = 0; i < 5; i++){
            triangles.add(new Triangle());
        }
        for (Triangle triangle : triangles){
            triangle.moveTo(triangleX, triangleY);
            if (black){
                triangle.changeColor("black");
                black = false;
            }
            else {
                triangle.changeColor("white");
                black = true;
            }
            triangleX += 10;
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
    public void substractAttack(){
        army.substractAttack();
    }
    
    /**
     * llama al metodo de army para que sume 1 a el ataque
     */
    public void sumAttack(){}
    
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
        for (Triangle triangle : triangles){
            triangle.makeVisible();
        }
    }
    
    /**
     * hace invisble a la nacion
     */
    public void makeLandInvisible(){
        land.makeInvisible();
        for (Triangle triangle : triangles){
            triangle.makeInvisible();
        }
    }
}
