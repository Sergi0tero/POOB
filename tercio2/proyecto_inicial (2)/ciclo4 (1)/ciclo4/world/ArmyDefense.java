package world;

import shapes.*;


/**
 * Write a description of class ArmyDefense here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ArmyDefense
{   
    private int xPosition;
    private int yPosition;
    private String color;
    private String nation;
    private int defense;
    protected Digit army = new Digit(0);
    private boolean conquered;
    private Triangle conquerTriangle;
    
    /**
     * Constructor de la clase armyDefense
     * @param xPosition posicion x del objeto
     * @param yPosition posicion y del objeto
     * @param nation color de la nacion donde va el objeto
     * @param qarmy cantidad de la army donde va el objeto
     */
    public ArmyDefense(int xPosition, int yPosition, String nation, int qarmy){
        this.defense = qarmy;
        army.change(qarmy);
        setArmy(xPosition, yPosition);
        army.makeVisible();
        conquered = false;
    }
    
    /**
     * suma al digito de la army
     */
    public void sumArmy(){
        defense += 1;
        army.next();
        army.makeVisible();
    }
    
    /**
     * resta al digito de la army
     */
    public void substractArmy(){
        defense -= 1;
        army.prev();
        if(defense == 0){
            conquer();
        }
    }
    
    /**
     * devuelve el valor de la defensa
     */
    public int getArmy(){
        return defense;
    }
    
    /**
     * muestra visualmente con un triangulo que ha sido conquistado
     */
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
    
    /**
     * Devuelve si esta conquistado
     * @return boolean conquered Esta conquistado o no
     */
    public boolean isConquered(){
        return conquered;
    }
    
    /**
     * pone el digito en una posicion
     * @param x posicion x
     * @param y posicion y
     * 
     */
    private void setArmy(int x, int y){
        xPosition = x;
        yPosition = y;
        army.moveTo(xPosition + 20, yPosition);
    }
    
    /**
     * Hace visible el objeto
     */
    public void makeVisible(){
        if (conquered == true) conquerTriangle.makeVisible();
        army.makeVisible();
    }
    
    /**
     * Hace invisible el objeto
     */
    public void makeInvisible(){
        army.makeInvisible();
        if (conquered == true) conquerTriangle.makeInvisible();
    }
}