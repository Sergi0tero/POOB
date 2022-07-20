import java.util.*;

public class SwFactory {
    private String name;
    
    private HashMap<Team, String> teams;

    private ArrayList<Inscription> inscriptions;

    private ArrayList<Client> clients;
    
    /*
     * elimina un proyecto
     * @proyectName la id del proyecto
     */
    public boolean deleteProject(String projectName){
        boolean dltProject = false;
        for (int client = 0; client < clients.size(); client++){
            dltProject = deleteProject(projectName);
        }
        return dltProject;
    }
}
