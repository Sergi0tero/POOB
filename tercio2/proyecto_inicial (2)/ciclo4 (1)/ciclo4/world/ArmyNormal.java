package world;
import shapes.*;
import java.util.*;


/**
 * Write a description of class fearful here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ArmyNormal extends Army
{
    /**
     * llama al constructor de la clase super
     */
    public ArmyNormal(int xPosition, int yPosition, String nation, ArrayList lista){
        super(xPosition, yPosition, nation,"normal");
        this.attacklist = lista;
    }
    
    /**
     * crea un nuevo Rectangulo y lo pone en el ataque de la nacion
     */
    public void sumAttack(){
        Rectangle rectan = new Rectangle();
        rectan.changeSize(5, 5);
        rectan.makeVisible();
        rectan.changeColor("magenta");
        attack += 1;
        attacklist.add(rectan);
        organice();
    }
}

