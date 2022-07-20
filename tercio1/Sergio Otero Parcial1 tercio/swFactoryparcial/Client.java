import java.util.*;

public class Client {

    private String id;

    private String email;

    private HashMap<Project, String> projects;
    
    private Project project;

    private SwFactory swFactory;
    
    /*
     * elimina un proyecto
     * @proyectName la id del proyecto
     */
    public boolean deleteProject(String projectName){
        this.id = projectName;
        project = loadProjectByName();
        if (project != null){
            return project.deleteProject();
        }
        return false;
    }
    
    /*
     * encuentra el proyecto usando la id
     */
    public Project loadProjectByName(){
        Project proyecto = new Project();
        for (int i = 0; i < projects.size(); i++){
            if (projects.get(i) == id){
                proyecto = project;
            }
        }
        return proyecto;
    }
}
