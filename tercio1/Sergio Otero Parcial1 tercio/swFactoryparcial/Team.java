import java.util.*;

public class Team {

    private String nickname;
    
    private Project project;

    private SwFactory swFactory;

    private ArrayList<Developer> members;

    private Inscription inscription;
    
    /*
     * notifica a los developers sobre el proyecto eliminado
     */
    public boolean notifyDevelopers(){
        return true;
    }
    
    /*
     * Devuelve lo correspondiente a cada miembro del equipo de las ganancias totales del proyecto
     */
    public int beneficios(){
        int beneficio = project.ganancias() / members.size();
        return beneficio;
    }
}
