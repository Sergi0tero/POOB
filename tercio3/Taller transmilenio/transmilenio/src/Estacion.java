import java.util.*;

/**
 * Write a description of class Estaciones here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Estacion
{
    public static String nombre;
    public static String nivelOcupacion;
    public static int espera;

    private ArrayList<Ruta> rutas = new ArrayList<Ruta>();

    /**
     * Constructor for objects of class Estaciones
     */
    public Estacion()
    {

    }

    /**
     * Devuelve las paradas de una ruta de la estacion actual a una estacion2
     *
     * @param  'String' ruta(nombre)
     * @param 'String' estacion2 (nombre)
     * @return  int numero de paradas de la ruta
     */
    public int getParadas(String ruta, String estacion2)
    {
        int paradas  = 0;
        for(Ruta r: rutas){
            if(r.nombre == ruta){
                paradas = r.getParadas(nombre, estacion2);
            }
        }
        return paradas;
    }

    

}
