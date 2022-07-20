package dominio;

public class ArticoExcepcion extends Exception{
    public final static String EN_CONSTRUCCION = "Opción en construcción";
    public ArticoExcepcion(String message){
        super(message);
    }
}
