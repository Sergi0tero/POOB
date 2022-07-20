package dominio;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Combo Ofertas
 * @author POOB  
 * @version ECI 2021
 */

public class ComboOfertas{
    private LinkedList <Combo> combos;
    private HashMap<String,Producto> productos;

    /**
     * Inicia un kiosko vacio
     */
    public ComboOfertas(){
        combos = new LinkedList<Combo>();
        productos= new HashMap<String,Producto>();
        adicioneEjemplares();
    }

    private void adicioneEjemplares(){
        String [][] productos = {{"Gaseosa","1000"},{"Agua","500"},{"Vino","2000"}, 
                                 {"Ensalada","5000"},{"Hamburguesa","7000"}, {"Pizza", "6000"},
                                 {"Flan","3000"},{"Helado", "2000"}, {"Fruta","1000"}};
        for (String [] p: productos){
            this.productos.put(p[0].toUpperCase(),new Producto(p[0],Integer.parseInt(p[1])));
        }
        String [][] combos = {{"Saludable", "50", "Agua\nEnsalada\nFruta"},
                              {"Festival de postres", "10", "Flan\nHelado"}};
        for (String [] c: combos){
            adicione(c[0],c[1],c[2]);
        }
    }

    /**
     * Consulta un combo
     * @param nombre El nombre del combo a buscar
     * @return 
     */
    public Combo consulte(String nombre){
        Combo c=null;
        for(int i=0;i<combos.size() && c == null;i++){
            if (combos.get(i).nombre().compareToIgnoreCase(nombre)==0) 
               c=combos.get(i);
        }
        return c;
    }

    /**
     * Adiciona un nuevo combo a la oferta
     * @param nombre 
     * @param descuento
     * @param productos
     */
    public void adicione(String nombre, String descuento, String productos){ 
        Combo combo = new Combo(nombre,Integer.parseInt(descuento));
        String [] aProductos= productos.split("\n");
        for (String p : aProductos){
            combo.adProducto(this.productos.get(p.toUpperCase()));
        } 
        int i=0;
        while ((i<combos.size()) && (combos.get(i).nombre().compareToIgnoreCase(combo.nombre())<0)){
            i++;
        }
        combos.add(i,combo);
        
    }

    /**
     * Consulta las combos que inican con un prefijo
     * @param prefijo El prefijo a buscar
     * @return Los combos encontrados
     */
    public ArrayList<Combo> busque(String prefijo){
        ArrayList<Combo> resultados=null;
        prefijo=prefijo.toUpperCase();
        for(int i=0;i<combos.size();i++){
            if(combos.get(i).nombre().toUpperCase().startsWith(prefijo)){
                resultados.add(combos.get(i));
            }   
        }
        return resultados;
    }


    /**
     * Consulta todas las ofertas
     * @return  
     */
    public String toString(){
        StringBuffer ofertas=new StringBuffer();
        for(Combo combo : combos) {
            try{
                ofertas.append(combo.oferta());
                ofertas.append("\n");
            } catch (Exception e){}
        }
        return ofertas.toString();
    }
    
    /**
     * Consulta el numero de Combos
     * @return Numero de Combos
     */
    public int numeroCombos(){
        return combos.size();
    }

}
