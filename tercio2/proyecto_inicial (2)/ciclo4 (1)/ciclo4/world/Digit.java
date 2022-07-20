package world;
import shapes.*;
import java.util.Random;
/**
 * Creates a digit using rectangles that can be manipulated using canvas
 * 
 * Sergio Otero
 */
public class Digit
{
    private int value;
    private int xPosition;
    private int yPosition;
    private boolean isVisible;
    private Rectangle[] rectan;
    
    /**
     * Creates the Digit
     * @param value int Valor que toma el digito
     */
    public Digit(int value){
        this.value = value;
        isVisible = false;
        rectan  = new Rectangle[7];
        for (int i = 0; i<7; i++){
            rectan[i] = new Rectangle();
            rectan[i].changeColor("black");
        }
        orderSize();
        layout();
    }
    
    /**
     * Returns the value
     * @return value int El valor del digito
     */
    public int get(){
        return value;
    }
    
    /**
     * Converts the digit into the next one
     */
    public void next(){
        makeInvisible();
        if (value == 9){
            value = 9;
        }
        else {
            value += 1;
        }
        makeVisible();
    }
    
    /**
     * Cambia el digito al numero anterior
     */
    public void prev(){
        makeInvisible();
        if (value != 0){
            value -= 1;
        }
        makeVisible();
    }
    
    /**
     * Changes the digit for anotherone selected by the user
     * @param newValue byte Valor por el que se va a cambiar
     */
    public void change(int newValue){
        makeInvisible();
        value = newValue;
        makeVisible();
    }    
    
    /**
     * Changes the digit for another random digit
     */
    public void change(){
        makeInvisible();
        Random newDigit = new Random();
        int newD = newDigit.nextInt(10);
        if (value != newD){
            value = (byte) newD;
            makeVisible();
        }
        else{
            change();
        }
    }
    
    /**
     * Moves the whole digit to an specific position
     * @param x The x position where the digit has to be moved
     * @param y The y position where the digit has to be moved
     */
    public void moveTo(int x, int y){
        makeInvisible();
        int moveX, moveY;
        moveX = x - rectan[0].getX();
        moveY = y - rectan[0].getY();
        for (int i = 0; i < 7 ; i++){
            rectan[i].moveHorizontal(moveX);
            rectan[i].moveVertical(moveY);
        }
        makeVisible();
    }
    
    /**
     * Change the color of the digit
     * @param newColor String The new color that the digit has to be changed
     */
    public void changeColor(String newColor){
        makeInvisible();
        for (int i = 0; i < 7; i++){
            rectan[i].changeColor(newColor);
        }
        makeVisible();
    }
    
    /**
     * Makes the digit visible
     */
    public void makeVisible(){
        isVisible = true;
        if (value == 0){
            rectan[0].makeVisible();
            for (int i = 2; i < 7; i++){
                rectan[i].makeVisible();
            }
        }
        else if (value == 1){
            rectan[4].makeVisible();
            rectan[6].makeVisible();
        }
        else if (value == 2){
            for (int i = 0; i < 3; i++){
                rectan[i].makeVisible();
            }
            rectan[4].makeVisible();
            rectan[5].makeVisible();
        }
        else if (value == 3){
            for (int i = 0; i < 3; i++){
                rectan[i].makeVisible();
            }
            rectan[4].makeVisible();
            rectan[6].makeVisible();
        }
        else if (value == 4){
            rectan[1].makeVisible();
            rectan[3].makeVisible();
            rectan[4].makeVisible();
            rectan[6].makeVisible();
        }
        else if (value == 5){
            for (int i = 0; i < 4; i++){
                rectan[i].makeVisible();
            }
            rectan[6].makeVisible();
        }
        else if (value == 6){
            for (int i = 0; i < 4; i++){
                rectan[i].makeVisible();
            }
            rectan[5].makeVisible();
            rectan[6].makeVisible();
        }
        else if (value == 7){
            rectan[0].makeVisible();
            rectan[4].makeVisible();
            rectan[6].makeVisible();
        }
        else if (value == 8){
            for (int i = 0; i < 7; i++){
                rectan[i].makeVisible();
            }
        }
        else if (value == 9){
            for (int i = 0; i < 5; i++){
                rectan[i].makeVisible();
            }
            rectan[6].makeVisible();
        }
    }
    
    /**
     * Makes the digit invisible
     */
    public void makeInvisible(){
        isVisible = false;
        for (int i = 0; i<7; i++){
            rectan[i].makeInvisible();
        }    
    }
    
    /**
     * Devuelve si el digito es visible
     * @return isVisible boolean True: el digito es visible, False: el digito es invisible
     */
    private boolean visible(){
        return isVisible;
    }
    
    /**
     * Makes every rectangle of the same size
     */
    private void orderSize(){
        for (int i = 0 ;i < 3; i++){
            rectan[i].changeSize(5, 15);
        }
        
        for (int i = 3; i < 7; i++){
            rectan[i].changeSize(15, 5);
        }
    }
    
    /**
     * Creates the layout of the digit
     */
    private void layout(){
        orderSize();
        rectan[0].moveVertical(50);
        rectan[0].moveHorizontal(50);
        rectan[1].moveVertical(70);
        rectan[1].moveHorizontal(50);
        rectan[2].moveVertical(90);
        rectan[2].moveHorizontal(50);
        rectan[3].moveVertical(55);
        rectan[3].moveHorizontal(45);
        rectan[4].moveHorizontal(65);
        rectan[4].moveVertical(55);
        rectan[5].moveVertical(75);
        rectan[5].moveHorizontal(45);
        rectan[6].moveVertical(75);
        rectan[6].moveHorizontal(65);
    }
}
