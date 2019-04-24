package Presentation;

/**
 * @author Sander Knudsen
 */

public class CommandArgument {
    private String value;
    private String description;
    
    public CommandArgument(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
}
