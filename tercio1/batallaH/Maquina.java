public class Maquina {
    private Ubicacion ubicacion;
    private int longi;
    private int lati;
    public boolean destruido = false;
    private String cause = null;
    
    public Maquina(){
        
    }
    
    public void move(int dLon, int dLat){
        int x = ubicacion.getLongitud();
        int y = ubicacion.getLatitud();
        ubicacion.setLongitud(x + dLon);
        ubicacion.setLatitud(y + dLat);
    }
    
    public boolean ubi(int longitud, int latitud){
        return (longitud == longi && latitud == lati);
    }
    
    public boolean debilitado(){
        return this.debilitado();
    }
    
    public void causa(String cas){
        cause = cas;
    }
    
    public String getCausa(){
        return cause;
    }
}
