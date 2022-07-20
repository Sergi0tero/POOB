 

public class Avion extends Maquina{
    private String placa;
    private boolean enAire;
    private Marino piloto;
    private Marino copiloto;
    private boolean debilitado;
    public Avion(){
        
    }
    
    public boolean debilitado(){
        debilitado = false;
        if (piloto != null){
            debilitado = true;
        }
        return debilitado;
    }
    
}
