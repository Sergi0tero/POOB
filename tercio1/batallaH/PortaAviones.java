import java.util.ArrayList;
public class PortaAviones extends Barco{
    private int capacidad;
    private ArrayList<Avion> aviones;
    private ArrayList<Marino> marinos;
    private boolean debilitado;
    public PortaAviones(){
        
    }
    
    @Override
    public boolean debilitado(){
        boolean avionDebil = false;
        debilitado = false;
        for (Avion avion : aviones){
            if (avion.debilitado() == true){
                avionDebil = true;
            }
        }
        if (marinos.size() < 5 && avionDebil){
            debilitado = true;
        }
        return debilitado;
    }
}
