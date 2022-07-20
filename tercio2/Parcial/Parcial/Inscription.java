import java.time.LocalDate;
import java.util.Collection;

public class Inscription {

    private LocalDate date;

    private String appUrl;

    private SwFactory swFactory;

    private Team team;

    private Project project;

    private Collection<Evaluation> evaluations;

    private Project project;

    private Collection<Notification> notifications;
    
    public int evaluateInscription() throws SwFactoryException{
        int percentage = 0;
        if(project.winner != null) throw new SwFactoryException(SwFactoryException.PROJECT_HAS_WINNER);
        percentage = project.evaluateProject(appUrl);
        if(percentage > 0){
            evaluation = new Evaluation(new Date(), percentage);
        }
        return percentage;
    }
    
    public Inscription knowsProject(int idProject){
        this.project.id = idProject;
        return this;
    }
}
