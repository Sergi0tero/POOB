public class Developer {

    private int code;

    private String name;

    private Team team;
    
    
    /*
     * Devuelve los beneficios adquiridos por el Developer gracias al proyecto
     */
    public int beneficios(){
        return team.beneficios();
    }
}
