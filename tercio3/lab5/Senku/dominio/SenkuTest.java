package dominio;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Color;

/**
 * The test class SenkuTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SenkuTest
{
    private Senku senku;
    private Senku senkuInvalido;

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        senku = new Senku(7, Color.white, Color.black);
        senkuInvalido = new Senku(8, Color.white, Color.black);
    }
    
    @Test
    public void DeberiaCrearSenku(){
        assertTrue(senku.creado());
    }
    
    @Test
    public void NoDeberiaCrearSenku(){
        assertFalse(senkuInvalido.creado());
    }
    
    @Test
    public void DeberiaMover() {
        try{
            senku.verificarBotones(2,2,2,0);
        }
        catch (dominio.SenkuException se){
            fail();
        }
    }
    
    @Test
    public void NoDeberiaMover() throws SenkuException{
        try{
            senku.verificarBotones(3,3,6,1);
            fail("No lanzo excepcion");
        }catch(SenkuException e){
            if(e.getMessage() == SenkuException.FICHA_INVALIDA)assertEquals(e.getMessage(),SenkuException.FICHA_INVALIDA);
        }
        try{
            senku.verificarBotones(1,4,4,4);
            fail("No lanzo excepcion");
        }catch(SenkuException e){
            if(e.getMessage() == SenkuException.MOVIMIENTO_INVALIDO)assertEquals(e.getMessage(),SenkuException.MOVIMIENTO_INVALIDO);
        }
    }
    
    @Test
    public void DeberiaContarMovimientos(){
        int temp[] = new int[2];
        temp[0] = 2;
        temp[1] = 3;
        int temp1[] = new int[2];
        temp1[0] = 1;
        temp1[1] = 2;
        senku.move(1,3,3,3, temp);
        senku.move(2,2,0,2, temp);
        assertEquals(2,senku.movimientos());
    }
    
    
    @Test
    public void NoDebeContarMovimientos(){
        int temp[] = new int[2];
        temp[0] = 2;
        temp[1] = 3;
        int temp1[] = new int[2];
        temp1[0] = 1;
        temp1[1] = 2;
        senku.move(1,3,3,3, temp);
        senku.move(2,2,0,2, temp);
        assertNotEquals(3,senku.movimientos());
    }
    
    @Test
    public void DeberiaContarCapturadas(){
        int temp[] = new int[2];
        temp[0] = 2;
        temp[1] = 3;
        int temp1[] = new int[2];
        temp1[0] = 1;
        temp1[1] = 2;
        senku.move(1,3,3,3, temp);
        senku.move(2,2,0,2, temp);
        assertEquals(2,senku.capturados());
    }
    
    @Test
    public void NoDeberiaContarCapturadas(){
        int temp[] = new int[2];
        temp[0] = 2;
        temp[1] = 3;
        int temp1[] = new int[2];
        temp1[0] = 1;
        temp1[1] = 2;
        senku.move(1,3,3,3, temp);
        senku.move(2,2,0,2, temp);
        assertNotEquals(3,senku.capturados());
    }
    
    @Test
    public void DeberiaCambiarColorFichas(){
        senku.cambieColorFichas(Color.blue);
        assertEquals(senku.getColorFichas(), Color.blue);
    }
    
    @Test
    public void DeberiaCambiarColorHuecos(){
        senku.cambieColorHuecos(Color.green);
        assertEquals(senku.getColorHuecos(), Color.green);
    }
    
    @AfterEach
    public void tearDown(){}
}