package dominio;


/**
 * Write a description of class SenkuException here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SenkuException extends Exception
{
    
    public final static String TAMANO_INVALIDO = "El tamaño ingresado no es valido";
    public final static String MOVIMIENTO_INVALIDO  = "El movimiento no es valido";
    public final static String FICHA_INVALIDA  = "La ficha no existe";
    
    
    public SenkuException(String message){
        super(message);
    }    
}
