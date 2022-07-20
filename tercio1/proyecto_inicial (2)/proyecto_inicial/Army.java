
/**
 * Write a description of class Army here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Army
{
    private int xPosition;
    private int yPosition;
    private String color;
    private int qarmy;
    private Digit army;
    private String nation;
    private boolean conquered;
    private Triangle conquerTriangle;

    /**
     * Constructor for objects of class Army
     */
    public Army(int xPosition, int yPosition, String nation, int qarmy){
        this.color = color;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.nation = nation;
        this.qarmy = qarmy;
        army = new Digit(qarmy);
        setArmy(xPosition, yPosition);
        army.makeVisible();
        conquered = false;
    }
    
    public void sumArmy(){
        qarmy += 1;
        army.next();
    }
    
    public void substractArmy(){
        qarmy -= 1;
        army.prev();
        if(qarmy == 0){
            conquer();
        }
    }
    
    public int getArmy(){
        return qarmy;
    }
    
    public boolean isConquered(){
        return conquered;
    }
    
    public void conquer(){
        conquerTriangle = new Triangle();
        conquerTriangle.moveTo(xPosition + 25, yPosition);
        conquerTriangle.changeSize(49, 50);
        conquerTriangle.changeColor("black");
        conquerTriangle.makeVisible();
        conquered = true;
        army.makeInvisible();
        army.changeColor("white");
        army.makeVisible();        
    }
    
    public void deconquer(){
        conquerTriangle.makeInvisible();
        army.changeColor("black");
    }
    
    public String getNation(){
        return nation;
    }
    
    public void makeVisible(){
        army.makeVisible();
    }
    
    public void makeInvisible(){
        army.makeInvisible();
    }
    
    private void setArmy(int x, int y){
        xPosition = x;
        yPosition = y;
        army.moveTo(xPosition + 20, yPosition);
    }
}
