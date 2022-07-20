import java.util.Collection;

public class Requirement {

    private String urlDetail;

    private int maxPoints;

    private Project project;

    private Project project;

    private Collection<Evaluator> evaluators;
    
    public int evaluate(Requirement requirement, String appUrl){
        for (Evaluator evaluator: evaluators){
            return evaluator.evaluate(requirement, appUrl);
        }
    }
}
