import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;
import java.util.*;

/**
 * Clase principal del taller de transmilenio.
 *
 * @Archila - Otero
 */
public class Sistema
{
    
    private ArrayList<Estacion> estaciones = new ArrayList<Estacion>();
    private ArrayList<String> nombreEstaciones = new ArrayList<String>();
    private ArrayList<Troncal> troncales = new ArrayList<Troncal>();
    
    /**
     * Constructor for objects of class Sistema
     */
    public Sistema()
    {

    }

    /**
     * Devuelve el tiempo de espera en una estacion
     *
     * @param  'String' nombre de la estacion
     * @return    tiempo de espera de la estacion
     */
    public int getEspera(String estacion)
    {
        int espera = 0;
        for(Estacion e: estaciones){
            if(e.nombre == estacion){
                espera = e.espera;
            }
        }
        return espera;
    }

    /**
     * Devuelve una lista de estaciones ordenadas alfabeticamente
     *
     * @return   ArrayList<String>  estaciones ordenadas
     */
    public ArrayList<String> getEstacionesordenadas()
    {
        for(Estacion e: estaciones){
            nombreEstaciones.add(e.nombre);
        }
        Collections.sort(nombreEstaciones);
        return nombreEstaciones;
    }

    /**
     * Devuelve la cantidad de paradas que tiene una ruta para ir de una estacion a otra estacion
     *
     * @param 'String' ruta, estacion1, estacion2(nombre)
     * @return  int numero de paradas entre una estacion y otra
     */
    public int getParadas(String ruta, String estacion1, String estacion2)
    {
        int paradas = 0; 
        for(Estacion e: estaciones){
            if(e.nombre == estacion1){
                paradas = e.getParadas(ruta, estacion2);
            }
        }
        return paradas;
    }

    /**
     * Devuelve un lista de rutas que puede tomar para ir de una estacion a otra sin transbordo
     *
     * @param  'String' estacion1, estacion2(nombre)
     * @return   ArrayList<Ruta> rutas sin transbordo
     */
    public ArrayList<Ruta> viajesintransbordo(String estacion1, String estacion2)
    {
        ArrayList<Ruta> res = new ArrayList<>();
        for(Troncal t: troncales){
            if(t.compareestacion(estacion1, estacion2)){
                res = t.getRutas(estacion1, estacion2);
            }
        }
        return res;
    }

    /**
     * Devuelve un lista de rutas que puede tomar para ir de una estacion a otra con transbordo
     *
     * @param  'String' estacion1, estacion2(nombre)
     * @return   ArrayList<Ruta> rutas sin transbordo
     */
    public ArrayList<Ruta> viajecontransbordo(String estacion1, String estacion2)
    {
        ArrayList<Ruta> res = new ArrayList<>();
        for(Troncal t: troncales){
            if(!t.compareestacion(estacion1, estacion2)){
                res = t.getRutas(estacion1, estacion2);
            }
        }
        return res;
    }

    /**
     * Devuelve el tiempo del recorrido de un palan de ruta 
     *
     * @param  'String[][]' plan de ruta, que contiene nombre de estaciones y las rutas
     * @return   int tiempo que tarda el recorrido
     */
    public int tiempoderecorrido(String[][] planderuta)
    {
        // put your code here
        return 0;
    }

    /**
     * Devuelve el mejor plan de recorrido para ir de una ruta a otra 
     *
     * @param  'String' estacion1, estacion2(nombre)
     * @return Plan, el mejor plan de ruta
     */
    public Plan mejorplanderecorrido(String estacion1, String estacion2)
    {
        // put your code here
        return null;
    }

    public void agregarRuta(String nameOfFile) throws IOException {
        BufferedReader bIn = new BufferedReader(new FileReader(nameOfFile));
        ArrayList<String> newEstaciones = new ArrayList<String>();
        String ruta = bIn.readLine();
        String line = bIn.readLine();
        while (line != null) {
            line = line.trim();
            line = bIn.readLine();
            newEstaciones.add(line);
        }
        Ruta newRuta = new Ruta(ruta);
        for (String estacion : newEstaciones){
            newRuta.newEstacion(estaciones.get(estaciones.indexOf(nombreEstaciones.get(estaciones.indexOf(estacion)))));
        }
    }

    public void exportMejorPlan(){
        //PrintWriter pw = new PrintWriter(new FileOutputStream());
    }

    public void salvarTroncal(String nombre, String direccion) throws IOException {
        for (Troncal troncal : troncales){
            if (troncal.getNombre() == nombre){
                troncal.salvarTroncal(direccion);
            }
        }
    }
}
