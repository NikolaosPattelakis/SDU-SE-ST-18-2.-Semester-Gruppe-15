package Presentation;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author Sander Knudsen
 */

public class Command {
    private String name;
    private String[] listArgumentNames;
    private Consumer<String[]> callback;
    
    public Command(String name, String[] listArgumentNames, Consumer<String[]> callback) {
        this.name = name;
        this.listArgumentNames = listArgumentNames;
        this.callback = callback;
    }
    
    public String getName() {
        return name;
    }
    
    public String[] getArgumentNames() {
        return listArgumentNames;
    }
    
    public Consumer<String[]> getCallback() {
        return callback;
    }
    
    public boolean canParseCommand(String[] args) {
        if(listArgumentNames.length != args.length)
            return false;
        else
            return true;
    }
}
