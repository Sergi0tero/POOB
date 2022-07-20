
/**
 * Write a description of class TagException here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TagException extends Exception
{
    public static final String NOT_TAGS = " tags list is empty";
    public static final String TAGGED = "The element is already associated with some tag";
    public static final String NO_MEMBERS = "Chat doesn’t have any members";
    public static final String INCOMPLETE_INFORMATION = "Tags don’t have complete information";
    public TagException(String exception){
        super(exception);
    }
}
