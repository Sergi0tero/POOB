import java.util.Collection;

public class AutomaticEvaluator extends Evaluator {

    private Collection<UnitTest> unitTests;
    
    public int evaluate(Requirement r, String appUrl){
        for (UnitTest ut : unitTests){
            runTest(appUrl, r);
        }
    }
}
