package Presentacion;


/**
* Clase de excepciones para tetirs
*
* @author (your name)
 **/
public class tetrisException extends Exception{

    //Importar & Exportar
    public static final String NO_ABRIO = "Ocurrio un error al abrir el archivo.";
    public static final String NO_GUARDO = "Ocurrio un error al guardar el archivo.";
    public static final String NO_SE_SELECCIONO_ARCHIVO = "No se ha seleccionado ningun archivo.";

    //Configuracion juego
    public static final String NO_SELECCIONO_BUFO = "Por favor, seleccione al menos un buffo.";
    public static final String NO_SELECCIONO_VELOCIDAD = "Por favor, elija una velocidad.";

    //Ingame
    public static final String ABRIR_INGAME = "No se puede abrir una partida guardada en medio del juego,\npruebe en otro momento.";

    //En construccion
    public static final String EN_CONSTRUCCION = "Funcion en construccion";

    /**
     * Constructor de las excepciones
     **/
    public tetrisException(String msg){
        super(msg);
    }
}