import java.util.*;
import PortaAviones;
/**
 * Write a description of class Flota here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Flota
{
    public int puntaje;
    public static String flota;

    private int longi;
    private int posiX;
    private int posiY;
    private int tripulantes;
    private int deltaLongitud;
    private int deltaLatitud;

    /**
     * Constructor for objects of class Flota
     */
    public Flota()
    {
        ArrayList<PortaAviones> portaAviones = new ArrayList();
        ArrayList<Barcos> barcos = new ArrayList();
        ArrayList<Aviones> enemigos = new ArrayList();
        ArrayList<Aviones> amigos = new ArrayList();
        ArrayList<Aviones> aviones = new ArrayList();
        longi = 200;
        posiX = -100;
        posiY = 100;
    }

    /**
     * @return numero de nuevos aviones que podrían cargarse a los portaaviones 
     */
    public int disponibilidadEnPortaAviones(){
        int sum = 0;
        for(int i = 0; i < portaAviones.size(); i++){
            sum += portaAviones.get(i).getDisponible();
        }
        return sum;
    }
    
    /**
     * @return     la placa de los aviones enemigos que están en el aire 
     */
    public ArrayList<String> enAire()
    {
        String placa;
        for (int i; i < aviones.size(); i++){
            placa = aviones.get(i).enAire();
            if (!aviones.get(i).esAmigo(placa)){
                enemigos.add(placa);
            }
            else{
                amigos.add(placa);
            }
        }
        return enemigos;
    }
    
    /**
     * @param deltaLongitud Movimiento en x
     * @param deltaLatitud Movimiento en Y
     */
    public void muevase(int deltaLongitud, int deltaLatitud)
    {
        if ((posiX + deltaLongitud) < longi && (posiX + deltaLatitud) < longi){
            for (int i = 0; i<barcos.size(); i++){
                barcos.get(i).moveHorizontal(deltaLongitud);
                barcos.get(i).moveVertical(deltaLatitud);
            }
            posiX += deltaLongitud;
            posiY += deltaLatitud;
        }
    }
    
    /**
     * 
     * @return     si se pueden confundir aviones amigos con enemigos
     */
    public boolean problemaEnAire()
    {
        for (int i; i < aviones.size(); i++){
            placa = aviones.get(i).enAire();
            if (enemigos.contain(placa) && amigos.contain(placa)){
                return True;
            }
        }
        return False;
    }
    
    /**
     * incompleta
     * 
     * @param  longitud   longitud de la explosion
     * @param  latitud   latitud de la explosion
     * @return     quienes se destruiran
     */
    public ArrayList<Object> seranDestruidas(int longitud, int latitud)
    {
        for (int i = 0; i<barcos.size(); i++){
                barcos.get(i).getX();
                barcos.get(i).getY();
            }
    }
}
