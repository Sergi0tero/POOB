
/**
 * Write a description of class SwFactoryException here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SwFactoryException extends Exception
{
    public final static String PROJECT_NOT_FOUND = "Project not found";
    public final static String PROJECT_HAS_WINNER = "The project has winner";
    public SwFactoryException(String message){
        super(message);
    }
}

