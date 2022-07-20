package dominio;
import java.io.File;
import java.util.*;

public class Artico{
    public static final int MAXIMO = 500;
    private static Artico polo = null;

    public static Artico demeArtico() {
        if (polo==null){
            polo=new Artico();
        }
        return polo;
    }

    private static void nuevoArtico() {
        polo=new Artico();
    }   

    public static void cambieArtico(Artico d) {
        polo=d;
    }       

    private ArrayList<EnArtico> elementos;
    private int poloNorteX;
    private int poloNorteY;
    private boolean enPoloNorte;

    private Artico() {
        elementos = new ArrayList<EnArtico>();
        poloNorteX = (int)(Math.random() * MAXIMO);
        poloNorteY = (int)(Math.random() * MAXIMO);
        enPoloNorte=false;
    }
    
    private int randomubi(){
        return (int)(Math.random() * MAXIMO);
    }
    
    public int getPoloX(){
        return poloNorteX;
    }
    
    public int getPoloY(){
        return poloNorteY;
    }

    public void algunosEnArtico(){
         ArrayList<Esquimal> esquimales = new ArrayList<Esquimal>();
         Esquimal aaju = new Esquimal(polo, "aaju", randomubi(), randomubi());
         Esquimal alek = new Esquimal(polo, "alek", randomubi(),randomubi());
         EsquimalSordo aguu = new EsquimalSordo(polo, "aguu", randomubi(), randomubi());
         EsquimalSordo ivanna = new EsquimalSordo(polo, "ivanna", randomubi(), randomubi());
         elementos.add(aaju);
         elementos.add(alek);
         elementos.add(aguu);
         elementos.add(ivanna);
         EsquimalExperto abdul = new EsquimalExperto(polo, "abdul", randomubi(), randomubi());
         elementos.add(abdul);
         esquimales.add(aaju);
         esquimales.add(alek);
         esquimales.add(aguu);
         esquimales.add(ivanna);
         esquimales.add(abdul);
         EsquimalExplorador nanuk = new EsquimalExplorador(polo, "nanuk", randomubi(), randomubi(), esquimales);
         esquimales.add(nanuk);
         elementos.add(nanuk);
         EsquimalExplorador sialuk = new EsquimalExplorador(polo, "sialuk", randomubi(), randomubi(), esquimales);
         esquimales.add(sialuk);
         elementos.add(sialuk);
         HuecoPesca pescaArriba = new HuecoPesca(polo, "pescaArriba", 250, 500);
         HuecoPesca pescaAbajo = new HuecoPesca(polo, "pescaAbajo", 250, 0);
         elementos.add(pescaArriba);
         elementos.add(pescaAbajo);
         Iglu superiorDerecha = new Iglu(polo, "superiorDerecha", 500, 500);
         Iglu superiorIzquierda = new Iglu(polo, "superiorIzquierda", 0, 500);
         Iglu inferiorIzquierda = new Iglu(polo, "inferiorIzquierda", 0, 0);
         Iglu inferiorDerecha = new Iglu(polo, "inferiorDerecha", 500, 0);
         elementos.add(superiorDerecha);
         elementos.add(superiorIzquierda);
         elementos.add(inferiorIzquierda);
         elementos.add(inferiorDerecha);
    }
    
    public EnArtico demeEnArtico(int n){
        EnArtico h=null;
        if (1<=n && n<=elementos.size()){
            h=elementos.get(n-1);
        }    
        return h; 
    }
    
    public void adicione(EnArtico e){
        elementos.add(e);
    }

    public int numeroEnArtico(){
        return elementos.size();
    }

    public boolean enPoloNorte(EnArtico e){
        boolean ok=(poloNorteX==e.getPosicionX() && poloNorteY==e.getPosicionY());
        enPoloNorte = enPoloNorte || ok;
        return enPoloNorte;
    }

    public void nuevo(){
        nuevoArtico();
    }

    public Artico abra00(File archivo)throws ArticoExcepcion {
        throw new ArticoExcepcion(ArticoExcepcion.EN_CONSTRUCCION);
    }

    public Artico guarde00(File archivo)throws ArticoExcepcion{
        throw new ArticoExcepcion(ArticoExcepcion.EN_CONSTRUCCION);
    }

    public Artico importe(File archivo)throws ArticoExcepcion{
        throw new ArticoExcepcion(ArticoExcepcion.EN_CONSTRUCCION);
    }

    public Artico exporte(File archivo)throws ArticoExcepcion{
        throw new ArticoExcepcion(ArticoExcepcion.EN_CONSTRUCCION);
    }

    public void accion(){
        for (EnArtico elemento : elementos){
            elemento.accion();
        }
    }

    public void improvisen(){
        for (EnArtico elemento : elementos){
            elemento.improvise();
        }
    }    

    public void corten(){
        for (EnArtico elemento : elementos){
            elemento.corte();
        }
    }  
}
