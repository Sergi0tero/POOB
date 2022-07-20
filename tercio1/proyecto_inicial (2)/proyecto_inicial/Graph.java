import java.util.HashMap;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.lang.*;
import java.util.Arrays;
import java.util.*;
import java.util.Collections;
/**
 * Write a description of class Graph1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Graph
{
    private HashMap<Integer,HashMap<String,Double>> nations;
    private int vertexs[];
    private int arcs[][];
    public double gray = 0.0;
    public double white = 1.0;

    /**
     * Constructor for objects of class Graph1
     */
    public Graph(int nodos[], int arcos[][])
    {
        nations = new HashMap<Integer,HashMap<String,Double>>();
        vertexs = nodos;
        arcs = arcos;
    }

    /**
     * Metodo para inicializar el grafo
     */
    public void initializeNations(int origin)
    {
        for(int v: vertexs) {
            nations.put(v,new HashMap<String,Double>());
            nations.get(v).put("color",gray);
            nations.get(v).put("costo",Double.POSITIVE_INFINITY);
            nations.get(v).put("phi",null);
        }
        nations.get(origin).replace("color",gray);
        nations.get(origin).replace("costo",0.0);
        nations.get(origin).replace("phi",null);
    }
    
    /**
     * Relaja los arcos
     */
    public void relax(int arc[])
    {
        int u = arc[0], v = arc[1], w = arc[2];
        if (nations.get(v).get("costo") > nations.get(u).get("costo") + (double) w) {
            nations.get(v).replace("costo",nations.get(u).get("costo") + (double) w);
            nations.get(v).replace("phi", (double) u);
        }
    }
    
    /**
     * Metodo que implementa algoritmo de Bellman Ford
     * @param origin Desde que nacion quiero comparar
     */
    public HashMap bellmanFord(int origen) 
    {
        initializeNations(origen);
        for(int i=0;i < vertexs.length - 1; i++) {
            for(int[] j: arcs) {
                this.relax(j);
            }
        }
        for(int[] a: arcs) {
            int u = a[0], v = a[1], w = a[2];
            if (nations.get(v).get("costo") > nations.get(u).get("costo")+ (double) w ) {
                return null;
            }
        }
        return nations;
    }
    
    /**
     * Metodo para acceder a las valores de nations
     * @param llave, llave a la que queremos acceder
     * @return HashMap con los datos de la llave
     */
    public HashMap get(int llave) 
    {
        return nations.get(llave);
    }
    
    /**
     * Metodo para acceder a las costos de nations
     * @param destino, llave a le que accederemos
     * @param llave, String del valor que queremos encontrar
     * @return Costo, Infinity de lo contrario.
     */
    public double get(int destino, String llave) 
    {
        if (llave == "costo") {
            return nations.get(destino).get(llave);
        }
        else {
            return Double.POSITIVE_INFINITY;
        }
    }
    
    /**
     * Metodo para acceder al costo de una llave
     * @param keys ArrayList de las llaves
     * @param value Valor que queremos comparar
     * @return Indice donde se encuentra el valor, -1 si no es encontrado
     */
    public int indexCosto(ArrayList<Integer> keys, double value) 
    {
        for(int i: keys) {
            if (value == nations.get(i).get("costo")) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Metodo para remover elemento de ArrayList
     * @param keys ArrayList
     * @param ind elemento que queremos remover
     * @return ArrayList sin el elemento
     */
    public ArrayList remove(ArrayList<Integer> keys, int ind) 
    {
        for(int i=0; i<keys.size();i++) {
            if (keys.get(i) == ind) {
                keys.remove(i);
            }
        }
        return keys;
    }
    
    /**
    * Metodo para hallar los vecinos mas cercanos a la nacion origen
    * @return retorna una lista de los vecinos mas cercanos al origen de forma ordenada.
    */
    public int[] closeNeighbours() 
    {
        ArrayList<Integer> keys = new ArrayList<Integer>();
        ArrayList<Double> values = new ArrayList<Double>();
        for(int i=0;i<nations.size();i++) {
            keys.add(i);
            values.add(nations.get(i).get("costo"));
        }
        Collections.sort(values);
        int[] index = new int[nations.size()];
        int cont = 0;
        for(double j: values) {
            int ind = indexCosto(keys,j);
            index[cont] = ind;
            keys = remove(keys,ind);
            cont += 1;
        }
        return index;
    }
    
}