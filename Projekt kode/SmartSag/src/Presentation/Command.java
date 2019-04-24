package Presentation;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author Sander Knudsen
 */

public class Command {
    private String name;
    private List<CommandArgument> arguments;
    private Consumer<String[]> callback;
    
    public Command(String name, List<CommandArgument> arguments, Consumer<String[]> callback) {
        this.name = name;
        this.arguments = arguments;
        this.callback = callback;
    }
    
    public String getName() {
        return name;
    }
    
    public Consumer<String[]> getCallback() {
        return callback;
    }
    
    public List<CommandArgument> getArguments() {
        return arguments;
    }
    
    public boolean parseCommand(String[] args) {
        if(arguments.size() != args.length)
            return false;
        
        for(int i = 0; i < args.length; i++) {
            arguments.get(i).setValue(args[i]);
        }
        
        return true;
    }
}
