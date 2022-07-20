package dominio;

import java.awt.Color;

public class Iglu implements EnArtico{
    private int posicionX;
    private int posicionY;
    private Artico polo;
    private Color color;
    private String name;
    private String palabra;
    private String lastAction;
    
    public Iglu(Artico polo, String name, int posicionX, int posicionY){
        color = Color.BLACK;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.polo = polo;
        this.name = name;
        palabra = "DISPONIBLE"; 
    }
    
    public String mensaje(){
        return ("Soy iglu: " + palabra);
    }
    
    public void corte(){
        color = Color.WHITE;
        lastAction = "c";
        palabra = "CERRADO";
    }
    
    public void accion(){
        color = Color.BLACK;
        lastAction = "a";
        palabra = "DISPONIBLE";
    }
    
    public void improvise(){
        color = Color.BLACK;
        if (lastAction == "a"){
            accion();
        }
        else if (lastAction == "c"){
            corte();
        }
        palabra = "CERRADO";
    }
    
    @Override
    public String forma(){
        return FORMAS[1];
    }
    
    public int getPosicionX(){
        return posicionX;
    }
    
    public int getPosicionY(){
        return posicionY;
    }
    
    public Color getColor(){
        return color;
    }
}
