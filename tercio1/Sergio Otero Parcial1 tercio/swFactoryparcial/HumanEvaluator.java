import java.util.*;

public class HumanEvaluator {

    private Project project;

    private ArrayList<AcceptanceTest> acceptanceTests;

    /*
     * hace la evaluacion del proyecto
     */
    public int evaluate(Requirement r, String appUrl) {
        return 0;
    }
    
    /*
     * elimina un evaluador
     */
    public boolean deleteEvaluator(){
        for (int at = 0; at < acceptanceTests.size(); at++){
            return acceptanceTests.get(at).deleteATest();
        }
        return false;
    }
}
