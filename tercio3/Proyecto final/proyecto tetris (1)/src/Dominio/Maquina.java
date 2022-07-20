package Dominio;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Maquina{

    private Robot robot;
    private int delay;
    private static String[] actions = {"w","a","s","d"};
    private Tetris tablero;


    /**
     * Contrtuctor de la clase maquina
     */
    public Maquina(Tetris tablero) {
        try {
            robot = new Robot();
            this.tablero = tablero;
        } catch (AWTException ex) {
            Logger.getLogger(Maquina.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     */
    public void abajo() {
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.delay(delay);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.delay(delay);
    }

    public void izquierda() {
        robot.keyPress(KeyEvent.VK_LEFT);
        robot.delay(delay);
        robot.keyRelease(KeyEvent.VK_LEFT);
        robot.delay(delay);
    }

    public void derecha() {
        robot.keyPress(KeyEvent.VK_RIGHT);
        robot.delay(delay);
        robot.keyRelease(KeyEvent.VK_RIGHT);
        robot.delay(delay);
    }

    public void rotar() {
        robot.keyPress(KeyEvent.VK_UP);
        robot.delay(delay);
        robot.keyRelease(KeyEvent.VK_UP);
        robot.delay(delay);
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }


    public void randomAction(){
        int action = (int) (Math.random() * 4);
        if(action == 1){
            izquierda();
        }
        else if(action == 2){
            derecha();
        }
        else if(action == 3){
            abajo();
        }
        else if(action == 4) {
            rotar();
        }
    }

    public  void intAction(){
        int[][] matriz = tablero.getTablero();


    }

}