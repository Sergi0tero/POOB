import java.util.*;

/**
 * Write a description of class Rutas here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Ruta
{
    public String nombre;
    private ArrayList<Estacion> estaciones = new ArrayList<Estacion>();

    /**
     * Constructor for objects of class Rutas
     */
    public Ruta(String nombre)
    {
        this.nombre = nombre;
    }


    /**
     * Devuelve la cantidad de paradas de la ruta de una estacion a otra
     *
     * @param  'String' estacion1 (nombre)
     * @param 'String' estacion2 (nombre)
     * @return  int numero de paradas 
     */
    public int getParadas(String estacion1, String estacion2)
    {
        int paradas = 0;
        boolean encontrada = false;
        for(Estacion e: estaciones){
            if(e.nombre == estacion1){
                encontrada  = !encontrada;
            }
            else if(e.nombre == estacion2){
                encontrada = !encontrada;
            }
            if(encontrada){
                paradas += 1;
            }
        }
        return paradas;
    }

    public void newEstacion(Estacion nueva){
        estaciones.add(nueva);
    }

    /**
     * Devuelve si la ruta contiene la estacion
     *
     * @param estacion1 (nombre)
     * @return  boolean contiene la estacion
     */
    public boolean contains(String estacion1) {
        return estaciones.contains(estacion1);
    }
}


