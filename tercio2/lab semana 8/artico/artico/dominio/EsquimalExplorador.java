package dominio;
import java.util.*;
import java.lang.Math;

/**
 * Write a description of class EsquimalExplorador here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EsquimalExplorador extends Esquimal
{
    ArrayList<Esquimal> esquimales;
    private Artico polo;
    private int posicionX;
    private int posicionY;
    /**
     * Constructor for objects of class EsquimalExplorador
     */
    public EsquimalExplorador(Artico polo, String name, int posicionX, int posicionY, ArrayList<Esquimal> esquimales){
        super(polo, name, posicionX, posicionY);
        this.polo = polo;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.esquimales = esquimales;
        this.rojoColor();
    }
    
    public void corte(){
        double minima = Double.POSITIVE_INFINITY;
        Esquimal esquimalCercano = this;
        for (Esquimal esquimal : esquimales){
            double esquimalX = esquimal.getPosicionX();
            double esquimalY = esquimal.getPosicionY();
            double distancia = Math.sqrt((Math.pow(esquimalX - posicionX, 2)) + (Math.pow(esquimalY - posicionY, 2)));
            if (distancia < minima && (esquimalX != posicionX) && (esquimalY != posicionY)){
                minima = distancia;
                esquimalCercano = esquimal;
            }
        }
        while(posicionX != esquimalCercano.getPosicionX() || posicionY != esquimalCercano.getPosicionY()){
            if (posicionX < esquimalCercano.getPosicionX()){
                avance('E');
                posicionX += 1;
            }
            else if (posicionX > esquimalCercano.getPosicionX()){
                avance('O');
                posicionX -= 1;
            }
            if (posicionY < esquimalCercano.getPosicionY()){
                avance('N');
                posicionY += 1;
            }
            else if (posicionY > esquimalCercano.getPosicionY()){
                avance('S');
                posicionY -= 1;
            }
        }
    }
    
    public void actue(){
        while (posicionX > 0){
            avance('O');
            posicionX -= 1;
        }
        while (posicionY > 0){
            avance('S');
            posicionY -= 1;
        }
        boolean flag = true;
        for(int i = 0; i < 500; i++){
            for(int j = 0; j < 500; j++){
                if(flag){
                    avance('E');
                    posicionX += 1;
                }
                else{
                    avance('O');
                    posicionX -= 1;
                }
            }
            flag = !flag;
            avance('N');
            posicionY += 1;
        }
    }
    
    public void improvise(){
        palabras= "Estoy en la posicion: "+ getPosicionX() + ", "+ getPosicionY();
    }
}
