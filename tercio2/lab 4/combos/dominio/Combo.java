package dominio; 
 
import java.util.ArrayList;

public class Combo{
    public final static int PRECIO_BASE=10000;
   
    private String nombre;
    private int descuento;
    private ArrayList<Producto> productos;
    
    /**
     * Crea un nuevo combo
     * @param nombre 
     * @param descuento
     */
    public Combo(String nombre, int descuento){
        this.nombre=nombre;
        this.descuento=descuento;
        productos= new ArrayList<Producto>();
    }

    public String nombre(){
        return nombre;
    }

    public void adProducto(Producto p){
        productos.add(p);
    }
    
    /**
     * Calcula el precio por omision de un combo.
     * @return precio Precio del producto
     * @throws ComboOfertasExcepcion COMBO_VACIO, si no tiene productos
     */
    public int precio() throws ComboOfertasExcepcion{
        int precio = 0;
        if (productos.isEmpty())throw new ComboOfertasExcepcion(ComboOfertasExcepcion.COMBO_VACIO);
        for (Producto p : productos){
            precio += p.precio();
        }
        precio -= precio * descuento / 100;
        return precio;
    }
    
    /**
     * Calcula el precio por omision de un combo.
     * Para los productos cuyo valor se desconoce se asume el PRECIO_BASE
     * @return 
     * @throws ComboOfertasExcepcion COMBO_VACIO, si no tiene productos
     */
    public int precioOmision() throws ComboOfertasExcepcion{
        int precio = 0;
        if (productos.isEmpty())throw new ComboOfertasExcepcion(ComboOfertasExcepcion.COMBO_VACIO);
        for (Producto p : productos){
            try{
                precio += p.precio();
            } catch(ComboOfertasExcepcion e){
                precio += PRECIO_BASE;
            }
        }
        precio -= precio * descuento / 100;
        return precio;
    }
    
    /**
     * Calcula el precio estimado
     * Se asume que el precio de los productos con precio desconocido es el precio mayor de los conocidos
     * Se asume que el precio de los productos con un precio erroneo es el precio menor de los conocidos
     * @return el precio estimado de un combo
     * @throws ComboOfertasExcepcion COMBO_VACIO si no hay productos o COMBO_SIN_PRECIOS no se conocen los precios de ninguno
     */
    public int precioEstimado() throws ComboOfertasExcepcion{
        int mayor = 0, menor = 0;
        int precio = 0;
        for (Producto p : productos){
            if (p.precio() > mayor)mayor = p.precio();
        }
        for (Producto p : productos){
            if (p.precio() < menor)menor = p.precio();
        }
        if (mayor == 0)throw new ComboOfertasExcepcion(ComboOfertasExcepcion.COMBO_VACIO);
        for (Producto p : productos){
            if (p.precio() == 0)p.setPrecio(mayor);
            try{
                precio += p.precio();
            } catch(ComboOfertasExcepcion e){
                precio += menor;
            }
        }
        precio -= precio * descuento / 100;
        return precio;
    }   
    
    /**
     * Calcula el precio de la oferta
     * El precio de oferta es el total, si se conoce. O el mayor entre el asumido y el estimado, si se conocen los dos. 
     * Se asume que el precio de los productos con un precio erroneo es el precio menor de los conocidos
     * @return el precio de la oferta de un combo
     * @throws ComboOfertasExcepcion COMBO_VACIO si no tiene productos y COMBO_NO_VIGENTE si no tiene precio de oferta
     */    
    public int precioOferta() throws ComboOfertasExcepcion{
        int precio = 0;
        if (productos.isEmpty())throw new ComboOfertasExcepcion(ComboOfertasExcepcion.COMBO_VACIO);
        try{
            precio = precio();
        }catch(ComboOfertasExcepcion e){
            int omision = precioOmision();
            int estimado = precioEstimado();
            if (estimado > omision){
                precio = estimado;
            }
            else{
                precio = omision;
            }
        }
        if (precio == 0) throw new ComboOfertasExcepcion(ComboOfertasExcepcion.PRODUCTO_SIN_PRECIO);
        return precio;
    } 
    
    public String oferta() throws ComboOfertasExcepcion{
        StringBuffer oferta=new StringBuffer();
        oferta.append(nombre+" : "+ precioOferta());
        oferta.append('\n');
        for(Producto p: productos) {
            oferta.append("\t"+p.nombre()+ " : \t" + p.precio());
            oferta.append('\n');
        }
        return oferta.toString();
    }
}
