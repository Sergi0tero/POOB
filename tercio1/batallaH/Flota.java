import java.util.ArrayList;

public class Flota {
    private Tablero tablero;
    private String nombre;
    private ArrayList<Marino> marinos;
    private Rectangle flota;
    private ArrayList<Maquina>maquinas;
    private Maquina maquina;
    public Flota(){
        this.flota = new Rectangle();
    }
    
    public void alNorte(){
        flota.moveUp();
        for(Maquina m : maquinas){
            m.move(0, -20);
        }
    }
    
    public void avance(int dLon, int dLat){
        for(Maquina m : maquinas){
            m.move(dLon, dLat);
        }
    }
    
    public ArrayList<Maquina> seranDestruidas(int longitud, int latitud){
        ArrayList<Maquina> destruidas = new ArrayList<Maquina>();
        for(Maquina m : maquinas){
            if(m.ubi(longitud, latitud)){
                destruidas.add(m);
            }
        }
        return destruidas;
    }
    
    public ArrayList<Maquina> maquinasDebiles(){
        ArrayList<Maquina> debiles = new ArrayList<Maquina>();
        for(Maquina m : maquinas){
            if(m.debilitado()){
                debiles.add(m);
            }
        }
        return debiles;
    }
    
    public boolean esBuenAtaque(int longitud, int latitud){
        boolean res = false;
        for(Maquina m : maquinas){
            if(m.ubi(longitud, latitud)){
                res = true;
            }
        }
        return res;
    }
    
    public void ataquen(int lon, int lat){
        for(Maquina m : maquinas){
            if(!m.debilitado()){
                m.move(lon, lat);
            }
        }
    }
}
