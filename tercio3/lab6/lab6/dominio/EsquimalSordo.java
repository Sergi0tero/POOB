package dominio;


/**
 * Write a description of class EsquimalSordo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EsquimalSordo extends Esquimal
{
    private Artico polo;
    private int posicionX;
    private int posicionY;

    /**
     * Constructor for objects of class EsquimalSordo
     */
    public EsquimalSordo(Artico polo, String name, int posicionX, int posicionY)
    {
        super(polo, name, posicionX, posicionY);
        this.polo = polo;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        palabras = "Qué qué?";
        this.greenColor();
    }

    public void corte(){
        for (int i = 0; i < 5; i++){
            avance('N');
        }
    }

    public void actue(){
        char d;
        muevase(true);
        do{
            d="NSEO".charAt((int)(Math.random() * 4));
        } while (! puedeMoverse(d));
        avance(d);
    }
    
    public void improvise(){
        this.yellowColor();
        corte();
    }    
}
