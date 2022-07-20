package dominio;

import java.awt.Color;

/**
 * Write a description of class EsquimalPerezoso here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EsquimalExperto extends Esquimal
{
    // instance variables - replace the example below with your own
    private int x;
    private boolean sabe;
    private int posicionX;
    private int posicionY;
    private Artico polo;

    public EsquimalExperto(Artico polo, String name, int posicionX, int posicionY)
    {
        super(polo, name, posicionX, posicionY);
        sabe = false;
        this.color = Color.LIGHT_GRAY;
        this.polo = polo;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        palabras="¡Escalando!";
    }

    public void corte(){
        sabe = true;
        palabras = "Se como encontrar el polo!";
    }
    
    public void actue(){
        if (sabe){
            if (posicionX < polo.getPoloX()){
                for(int i = posicionX; i <= polo.getPoloX(); i++){
                    avance('E');
                }
                posicionX = polo.getPoloX();
            }
            else if (posicionX > polo.getPoloX()){
                for(int i = posicionX; i >= polo.getPoloX(); i--){
                    avance('O');
                }
                posicionX = polo.getPoloX();
            }
            else if (posicionY < polo.getPoloY()){
                for(int i = posicionY; i <= polo.getPoloY(); i++){
                    avance('N');
                }
                posicionY = polo.getPoloY();
            }
            else if (posicionY > polo.getPoloY()){
                for(int i = posicionY; i >= polo.getPoloY(); i--){
                    avance('S');
                }
                posicionY = polo.getPoloY();
            }
            llegoArtico();
        }
        else{
            palabras = "No se donde está el polo!";
        }
    }
    
    private void llegoArtico(){
        if (posicionX == polo.getPoloX() && posicionY == polo.getPoloY()){
            palabras = "Estoy en el polo!";
        }
        else{
            palabras = "Aun no llego al polo!";
        }
    }
    
    @Override
    public void improvise(){
        char d;
        muevase(false);
        do{
            d="NSEO".charAt((int)(Math.random() * 4));
        } while (! puedeMoverse(d));
        avance(d);
    }    
}

