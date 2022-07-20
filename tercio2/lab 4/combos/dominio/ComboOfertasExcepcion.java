package dominio;


/**
 * Clase de excepcion de Combo
 * 
 * @author Archila Otero
 */
public class ComboOfertasExcepcion extends Exception
{
    /*Errores del combo*/
    public final static String COMBO_VACIO = "Este combo esta vacio";
    
    /*Errores del producto*/
    public final static String PRODUCTO_SIN_PRECIO = "Este producto no tiene precio";
    public final static String PRODUCTO_ERROR_PRECIO = "Se produjo un error en el precio";
    
    /*Errores de comboOferta*/
    public final static String COMBOOFERTA_SIN_NOMBRE = "No se ingresó un nombre del combo";
    public final static String COMBOOFERTA_NOMBRE_REPETIDO = "El nombre ingresado ya pertenece a otro combo";
    public ComboOfertasExcepcion(String message){
        super(message);
    }
}
