import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Write a description of class Troncales here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Troncal
{
    private ArrayList<Estacion> estaciones = new ArrayList<Estacion>();
    private ArrayList<Ruta> rutas = new ArrayList<Ruta>();
    private String nombre;

    /**
     * Constructor for objects of class Troncales
     */
    public Troncal()
    {

    }

    /**
     * Devuelve true si las dos estaciones pertenecen a la misma troncal
     *
     * @param  'Stirng' estacion1 , estacion2 (nombre)
     * @return    'boolean' pertenecen a la misma troncal
     */
    public boolean compareestacion(String estacion1, String estacion2)
    {
        boolean flag = false;
        for(Estacion e : estaciones){
            if(e.nombre == estacion1){
                flag = true;
            }
            else if(e.nombre == estacion2){
                flag = true;
            }
        }
        return flag;
    }

    /**
     * Devuelve las rutas entre una estacion y otra
     *
     * @param 'String' estacion1, estacion2 (nombre)
     * @return ArrayList<Ruta> rutas para esas paradas
     */
    public ArrayList<Ruta> getRutas(String estacion1, String estacion2) {
        ArrayList<Ruta> res = new ArrayList<Ruta>();
        for(Ruta r: rutas){
            if(r.contains(estacion1) && r.contains(estacion2)){
                res.add(r);
            }
        }
        return res;
    }

    public String getNombre(){
        return nombre;
    }

    public void salvarTroncal(String direccion) throws IOException {
        FileWriter fichero = new FileWriter(direccion);
        PrintWriter printWriter = new PrintWriter(fichero);
        fichero.close();
    }

}
