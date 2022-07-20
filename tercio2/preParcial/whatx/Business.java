import java.util.ArrayList;

public class Business extends User{

    private String description;
    private String adress;
    private String email;
    private ArrayList<Tag> configTags;
    
    /**
     * Create a new tag for user business
     * @param name - Tag name
     * @param color - Tag color
     */
    public void createTag(String name, String color) {
        configTags.add(new Tag(name, color));
    }
    
    public void tag(){
        if (configTags.isEmpty()){
            
        }
    }
}
