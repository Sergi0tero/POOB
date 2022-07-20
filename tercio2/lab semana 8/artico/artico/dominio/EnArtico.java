package dominio;
import java.awt.Color;
import java.util.Random;

public interface EnArtico{
    String[] FORMAS = new String[]{"Persona", "Circulo", "Cuadrado"};
    Random r = new Random(1);

    int getPosicionX();
    int getPosicionY();
    Color getColor();
    void accion();
    
    default void corte(){
    }

    default void improvise(){
        if (r.nextBoolean()){
            accion();
        }else{
            corte();
        }
    }     
    
    default String forma(){
        return FORMAS[0];
    }

    default String mensaje(){
        return toString();
    }

    default boolean puedeMoverse(char direccion) {
        boolean puede=false;
        int posX = getPosicionX();
        int posY = getPosicionY();
        switch(direccion){
            case 'S' : puede = (posY+1 < Artico.MAXIMO);
            break;
            case 'E' : puede = (posX+1 < Artico.MAXIMO);
            break;
            case 'N' : puede = (posY-1 >= 0);
            break;
            case 'O' : puede = (posX-1 >= 0);
            break; 
        } 
        return puede;
    }   
}
