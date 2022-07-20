package dominio;


import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ComboTest{
   
    
    public ComboTest(){
    }
    
    @Test
    public void deberiaAdicionar(){
        try{
            ComboOfertas c = new ComboOfertas();
            c.adicione("Pizza Master", "20", "Pizza\nGaseosa\nHelado");
            assertEquals("Pizza Master", (String)c.busque("Pizza Master").get(0).nombre());
        }catch (ComboOfertasExcepcion e){
            fail("Lanzó excepcion");
        }
    }
    
    public void deberiaListar(){
        try{
            ComboOfertas c = new ComboOfertas();
            c.adicione("Pizza Master", "20", "Pizza\nGaseosa\nHelado");
            assertEquals("Pizza Master", (String)c.busque("Pizza Master").get(0).nombre());
        }catch (ComboOfertasExcepcion e){
            fail("Lanzó excepcion");
        }
    }
    
    public void deberiaNoAñadirNombreRepetido(){
        try{
            ComboOfertas c = new ComboOfertas();
            c.adicione("Pizza Master", "20", "Pizza\nGaseosa\nHelado");
            c.adicione("Pizza Master", "20", "Pizza\nGaseosa\nHelado");
            assertEquals("Combo1", (String)c.busque("Combo1").get(0).nombre());
        }catch (ComboOfertasExcepcion e){
            fail("Lanzó excepcion");
        }
    }
    
    @Test
    public void deberiaCalcularElPrecioDeUnCombo(){
        Combo c = new Combo("Rapido", 10);
        c.adProducto(new Producto("Coca Cola", 1000));
        c.adProducto(new Producto("Hamburguesa",8000));
        c.adProducto(new Producto("Papas", 1000));
        try {
           assertEquals(9000,c.precio());
        } catch (ComboOfertasExcepcion e){
            fail("Lanzó excepcion");
        }    
    }    
    
    @Test
    public void deberiaCambiarSiNoTieneNombre(){
        try{
            ComboOfertas c = new ComboOfertas();
            c.adicione("", "25", "Pizza\nEnsalada\nVino\nFlan");
            assertEquals("Combo1", (String)c.busque("Combo1").get(0).nombre());
        }
        catch (ComboOfertasExcepcion e){
            fail("Lanzó excepcion");
        }
    }
    
    @Test
    public void deberiaCrearElComboOferta(){
        try{
            ComboOfertas italian = new ComboOfertas();
            italian.adicione("Italiano", "25", "Pizza\nEnsalada\nVino\nFlan");
            assertNotEquals(null,italian.consulte("Italiano"));
        }catch (ComboOfertasExcepcion e){
            fail("Lanzó excepcion");
        }
    }
    
    @Test
    public void deberiaLanzarExcepcionSiElComboNoTieneProductos(){
        Combo c = new Combo("Rapido", 10);
        try { 
           int precio=c.precio();
           fail("No lanzó excepcion");
        } catch (ComboOfertasExcepcion e) {
            assertEquals(ComboOfertasExcepcion.COMBO_VACIO,e.getMessage());
        }    
    }    
    
    @Test
    public void deberiaLanzarExcepcionSiNoSeConoceElPrecioDeUnProducto(){
        Combo c = new Combo("Rapido", 10);
        c.adProducto(new Producto("Coca Cola", 1000));
        c.adProducto(new Producto("Hamburguesa",8000));
        c.adProducto(new Producto("Papas", -1000));
        try { 
           int precio=c.precio();
           fail("No lanza la excepcion");
        } catch (ComboOfertasExcepcion e) {
            assertEquals(ComboOfertasExcepcion.PRODUCTO_ERROR_PRECIO,e.getMessage());
        }    
    }     
    
    @Test
    public void deberiaLanzarExcepcionSiNoSeConoceUnProducto(){
        Combo c = new Combo("Rapido", 10);
        c.adProducto(new Producto("Coca Cola", 1000));
        c.adProducto(new Producto("Hamburguesa",null));
        c.adProducto(new Producto("Papas", -1000));
        try { 
           int precio=c.precio();
           fail("No lanza la excepcion");
        } catch (ComboOfertasExcepcion e) {
            assertEquals(ComboOfertasExcepcion.PRODUCTO_SIN_PRECIO,e.getMessage());
        }    
    }  
    
    @Test
    public void deberiaSumarPrecioBase(){
        Combo c = new Combo("Rapido", 10);
        c.adProducto(new Producto("Coca Cola", 1000));
        c.adProducto(new Producto("Hamburguesa",8000));
        c.adProducto(new Producto("Papas", -1000));
        try {
            assertEquals(17100,c.precioOmision());
        } catch (ComboOfertasExcepcion e){
            fail("Lanzó excepcion");
        }   
    }
    
    // @Test
    // public void deberiaLanzarExcepcionSiNoSeConocenLosPrecios(){
        // Combo c = new Combo("Rapido", 10);
        // c.adProducto(new Producto("Coca Cola", 0));
        // c.adProducto(new Producto("Hamburguesa",0));
        // c.adProducto(new Producto("Papas", 0));
        // try {
            // fail("No lanzó excepcion");
        // } catch (ComboOfertasExcepcion e){
            // fail("Lanzó excepcion");
        // }   
    // }
}
