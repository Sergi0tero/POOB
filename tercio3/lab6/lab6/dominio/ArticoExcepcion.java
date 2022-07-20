package dominio;

public class ArticoExcepcion extends Exception{

    // Abrir
    public final static String NO_ABRIO = "No se pudo abrir el archivo.";
    public final static String ARCHIVO_NO_ENCONTRADO = "No se pudo encontrar el archivo";
    public final static String ARCHIVO_CORRUPTO = "Archivo corrupto";
    public final static String CLASE_NO_ENCONTRADA = "No se pudo encontrar la clase";
    public final static String CLASE_NO_PERMITIDA = "La clase no es permitida";
    public final static String ENTRADA_SALIDA = "Algo salio mal";

    // Guarde
    public final static String NO_GUARDO = "No se pudo guardar el archivo.";

    //En construccion
    public final static String EN_CONSTRUCCION = "Opción en construcción";

    //Exportar
    public final static String NO_EXPORTO = "No se pudo exportar";

    //Importar
    public final static String NO_IMPORTO = "No se pudo importar";
    public final static String IMPORTAR_DETALLES = "Para mas detalles revise 'articoErr.txt'";


    public ArticoExcepcion(String message){
        super(message);
    }
}
