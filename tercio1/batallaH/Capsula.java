import java.util.ArrayList;
/**
 * Write a description of class Capsula here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Capsula extends Maquina
{
    private boolean nodriza = false;
    private ArrayList<Capsula> capsulas;
    private int profundidad;
    /**
     * Constructor for objects of class Capsula
     */
    public Capsula()
    {
        
    }
    
    public void autoDestruir(){
        destruido = true;
        this.causa();
        for (Capsula c : capsulas){
            c.autoDestruir();
        }
    }
    
    public void makeNodriza(ArrayList<Capsula> capsulas){
        this.capsulas = capsulas;
    }
}
