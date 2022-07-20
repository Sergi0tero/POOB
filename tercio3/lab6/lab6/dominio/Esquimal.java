package dominio;

import java.awt.Color;
import java.io.Serializable;

public class Esquimal extends Persona implements EnArtico, Serializable {

    private Artico artico;   
    protected String palabras;
    private String name;

    public Esquimal(Artico artico,String name,int posicionx, int posiciony){
        super(name,posicionx,posiciony);
        this.artico=artico;
        palabras="¡Escalando!";
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void corte(){
        muevaBrazo('I','P'); 
        muevaPierna('I','P');
        muevaBrazo('D','P'); 
        muevaPierna('D','P');       
        palabras="";
    }

    public final void accion(){
        if (! artico.enPoloNorte(this)){
            actue();
        }
    }
    
    public void improvise(){
        if (r.nextBoolean()){
            accion();
        }else{
            corte();
        }
    }    
    
    public void actue(){
        char d;
        muevase(false);
        do{
            d="NSEO".charAt((int)(Math.random() * 4));
        } while (! puedeMoverse(d));
        avance(d);
    }

    public final String mensaje() {
        return super.mensaje()+": "+(artico.enPoloNorte(this) ? "¡ENCONTRE EL POLO NORTE! ": palabras);
    }
}

