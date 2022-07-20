import java.util.Collection;
import java.util.ArrayList;

public class SwFactory {

    private String[] clients;

    private HashMap<Team> teams;

    private HashMap<int, Project> projects = new HashMap<int, Project>();

    private ArrayList<Inscription> inscriptions;

    private ArrayList<Certificates> certificates;

    private ArrayList<Feedbacker> feedbackers;
    
    Team foundTeamWinner(int idProject){
        Proyect project = loadProjectById(idProject);
        Inscription inscription = loadInscriptionByProject(idProject);
        for(Inscription i: inscriptions){
            int percentage = i.evaluateInscription();        
        }
    }
    
    Project loadProjectById(int idProject) throws SwFactoryException{
        try{
            if (projects.get(idProject) == null) throw new SwFactoryException(SwFactoryException.PROJECT_NOT_FOUND);
            Project project = projects.get(idProject);
            return project;
        } catch(SwFactoryException e){
            return null;
        }
    }
    
    Inscription loadInscriptionByProject(int idProject){
        for(Inscription i: inscriptions){
            if (i.knowsProject(idProject) != null){
                return i;
            }
        }
        return null;
    }
}
