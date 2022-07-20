package dominio;



import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

/**
 * The test class ArticoTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ArticoTest
{
    Artico artico1;
    Artico artico2;
    private Artico artico;
    private Esquimal aEsquimal, bEsquimal;
    private int flag1, flag2, flag3, flag4;
    
    private Artico artico3;
    private EsquimalSordo cEsquimal, dEsquimal;
    private String flag5, flag6;
    private int flag7, flag8;
    
    private Artico artico4;
    private EsquimalExplorador fEsquimal, sEsquimal;
    private int flag9, flag10,flag11, flag12;
    private ArrayList<Esquimal> esquimales = new ArrayList<Esquimal>();
    
    private Artico artico5;
    private Iglu giglu, higlu;
    private String flag13, flag14;
    
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        artico1 = Artico.demeArtico();
        
        artico = artico.demeArtico();
        aEsquimal = new Esquimal(artico, "aaa", 100,100);
        bEsquimal = new Esquimal(artico, "bbb", 200,200);
        aEsquimal.corte();
        bEsquimal.corte();
        flag1 = aEsquimal.getPosicionPierna('I');
        flag2 = aEsquimal.getPosicionPierna('D');
        aEsquimal.accion();
        bEsquimal.accion();
        flag3 = aEsquimal.getPosicionBrazo('D');
        flag4 = aEsquimal.getPosicionBrazo('D');
        
        artico3 = artico.demeArtico();
        cEsquimal = new EsquimalSordo(artico3, "aaa", 100,100);
        dEsquimal = new EsquimalSordo(artico3, "bbb", 200,200);
        cEsquimal.improvise();
        dEsquimal.improvise();
        flag5 = cEsquimal.mensaje();
        flag6 = dEsquimal.mensaje();
        cEsquimal.accion();
        dEsquimal.accion();
        flag7 = cEsquimal.getPosicionBrazo('I');
        flag8 = dEsquimal.getPosicionBrazo('D');
        
        artico4 = artico.demeArtico();
        fEsquimal = new EsquimalExplorador(artico4, "aaa", 100,100, esquimales);
        sEsquimal = new EsquimalExplorador(artico4, "bbb", 200,200, esquimales);
        fEsquimal.accion();
        sEsquimal.accion();
        flag9 = fEsquimal.getPosicionX();
        flag10 = fEsquimal.getPosicionY();
        flag11 = sEsquimal.getPosicionX();
        flag12 = sEsquimal.getPosicionY();
        
        artico5 = artico.demeArtico();
        giglu = new Iglu(artico5, "inferiorIzquierda", 0, 0);
        higlu = new Iglu(artico5, "inferiorDerecha", 500, 0);
        giglu.corte();
        higlu.corte();
        flag13 = giglu.mensaje();
        flag14 = higlu.mensaje();
    }
    
    @Test
    public void deberiaSerIgual(){
        assertEquals(artico1, artico1.demeArtico());
    }
    
    @Test
    public void deberiaSerDiferente(){
        assertNotEquals(artico1, artico2);
    }
    
    @Test
    public void deberiacompararpospiernas(){
        assertEquals(2,flag1);
        assertEquals(2,flag2);
    }

    @Test
    public void deberiacompararbrazoigual(){
        assertEquals(flag1, flag2);
    }
    
    @Test
    public void deberiacompararmensaje(){
        assertEquals("Soy aaa: Qué qué?" ,flag5);
        assertEquals("Soy bbb: Qué qué?" ,flag6);
    }
    
    @Test
    public void deberiarcompararbrazos(){
        assertEquals(flag7, flag8);
    }
    
    @Test
    public void deberiacompararmensajes(){
        assertEquals("Soy iglu: CERRADO", flag13);
        assertEquals("Soy iglu: CERRADO", flag14);
    }
    
    @Test
    public void deberiacompararposiciones(){
        assertEquals(flag9,flag11);
        assertEquals(flag10,flag12);
    }
}
