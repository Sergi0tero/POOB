public class Inscription {

    private LocalDate date;

    private String appUrl;

    private SwFactory swFactory;

    private Team team;

    private Project project;

    private Evaluation evaluation;

    private Project projectt;
    
    /*
     * notifica a los developers sobre el proyecto eliminado
     */
    public boolean notifyDevelopers(){
        return team.notifyDevelopers();
    }
}
