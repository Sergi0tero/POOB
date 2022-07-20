import java.util.*;

public class Project {

    private String id;

    private String urlFormulation;

    private LocalDate start;

    private LocalDate end;

    private int prize;

    private Inscription inscription;

    private ArrayList<Requirement> requirements;

    private Inscription winner;

    private Project project;

    private Project projectt;

    private Requirement requirement;

    private HumanEvaluator evaluator;

    private Client client;
    
    private int dineroGanado;
    
    /*
     * elimina un proyecto
     */
    public boolean deleteProject(){
        boolean deletedRe = false;
        for (int i = 0; i < requirements.size(); i++){
            deletedRe  = requirements.get(i).deleteRequirements();
        }
        boolean deletedEva = evaluator.deleteEvaluator();
        
        if (winner != null && deletedRe && deletedEva){
            return inscription.notifyDevelopers();
        }
        return false;
    }
    
    /*
     * Devuelve los beneficios ganados por el proyecto
     */
    public int ganancias(){
        return dineroGanado - prize;
    }
}
