package dominio;

import java.awt.Color;

public class HuecoPesca implements EnArtico{
    private int posicionX;
    private int posicionY;
    private Artico polo;
    private Color color;
    private String name;
    private String mensaje = "";
    private boolean sellado;
    
    public HuecoPesca(Artico polo, String name, int posicionX, int posicionY){
        color = Color.BLUE;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.polo = polo;
        this.name = name;
        sellado = false;
        mensaje = "Pesca";
    }

    public String getName(){
        return name;
    }

    public String mensaje(){
        return mensaje;
    }
    
    public void corte(){
        if(!sellado){
            color = Color.BLUE;
            mensaje = "Pesca";
        }
    }
    
    public void accion(){
        if(!sellado){
            color = Color.CYAN;
            mensaje = "Cerrado";
        }
    }
    
    public void improvise(){
        color = Color.WHITE;
        mensaje = "Sellado";
        sellado = true;
    }
    
    @Override
    public String forma(){
        return FORMAS[2];
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
