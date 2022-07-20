import java.util.ArrayList;

public class Barco extends Maquina{
    private int numero;
    private ArrayList<Marino> marinos;
    private boolean debilitado;
    private boolean nodriza = false;
    private ArrayList<Capsula> capsulas;
    public Barco(){
        
    }
    
    public void autoDestruir(){
        destruido = true;
        this.causa();
        for (Capsula c : capsulas){
            c.autoDestruir();
        }
    }
    
    public void makeNodriza(ArrayList<Capsula> capsulas){
        nodriza = true;
        this.capsulas = capsulas;
    }
    
    public boolean debilitado(){
        debilitado = false;
        if (marinos.size() < 5){
            debilitado = true;
        }
        return debilitado;
    }
}


