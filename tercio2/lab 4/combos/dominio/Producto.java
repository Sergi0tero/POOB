package dominio; 

public class Producto{
    private String nombre;
    private Integer precio;

    public Producto(String nombre, Integer precio){
        this.nombre=nombre;
        this.precio=precio;
    }    
    
    public void setPrecio(int precio){
        this.precio = precio;
    }

    public String nombre(){
        return nombre;
    }
    
    public int precio() throws ComboOfertasExcepcion{
       if (precio == null) throw new ComboOfertasExcepcion(ComboOfertasExcepcion.PRODUCTO_SIN_PRECIO);
       if (precio < 1) throw new ComboOfertasExcepcion(ComboOfertasExcepcion.PRODUCTO_ERROR_PRECIO);
       return precio;
    }
}
