package Presentation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import smartsag.Cases.Case;
import smartsag.SmartSag;

/**
 * @author Sander Knudsen
 */

public class CommandInterface {
    
    private List<Command> availableCommands;
    private boolean shouldQuit = false;
    
    public CommandInterface() {
        availableCommands = new ArrayList<Command>();
        
        initializeCommands();
    }
    
    public void run() {
        printCommands();
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
        while(!shouldQuit) {
            System.out.println();
            System.out.println("Indtast kommando:");
            
            String input = "";
            try {
                input = reader.readLine();
            } catch (IOException ex) {
                Logger.getLogger(CommandInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            handleInput(input);
        }
        
        System.out.println("Afslutter programmet...");
    }
    
    private void printCommands() {
        System.out.println();
        System.out.println("[Liste af tilgængelige kommandoer]");
        for(Command command : availableCommands) {
            String strCommand = command.getName();
            
            for(String argument : command.getArgumentNames()) {
                strCommand += " [" + argument + "]";
            }
            
            System.out.println(strCommand);
        }
    }
    
    private void handleInput(String input) {
        String[] arguments = input.split(" ");
        String[] rawArguments = Arrays.copyOfRange(arguments, 1, arguments.length); // Arguments without command name
        
        Optional<Command> optCommand = availableCommands.stream()
                                           .filter(x -> x.getName().equals(arguments[0]))
                                           .findFirst();
        Command command = null;
        if(optCommand.isPresent())
            command = optCommand.get();
        
        if(command == null) {
            System.out.println("Ukendt kommando");
            return;
        }
        else {
            if(!command.canParseCommand(rawArguments)) {
                System.out.println("Forkert antal af parametre");
                return;
            }
        }
        
        try {
            command.getCallback().accept(rawArguments);
        } catch (Exception ex) {
            System.out.println("Kritisk fejl opstod under udførsel af kommando!\nFejlbesked: " + ex.toString());
        }
    }
    
    private void initializeCommands() {
        availableCommands.add(new Command("help", new String[] { }, x -> commandHelp(x)));
        availableCommands.add(new Command("quit", new String[] { }, x -> commandQuit(x)));
        availableCommands.add(new Command("create-case", new String[] {
            "ApplicantID",
            "ResidenceID",
            "DepartmentID"
        }, x -> commandCreateCase(x)));
        availableCommands.add(new Command("delete-case", new String[] {
            "CaseID"
        }, x -> commandDeleteCase(x)));
    }
    
    private void commandHelp(String[] arguments) {
        printCommands();
    }
    
    private void commandQuit(String[] arguments) {
        shouldQuit = true;
    }
    
    private void commandCreateCase(String[] arguments) {
        System.out.println("Forsøger at oprette sagen...");
        
        int applicantID = Integer.valueOf(arguments[0]);
        int residenceID = Integer.valueOf(arguments[1]);
        int departmentID = Integer.valueOf(arguments[2]);
        
        Case createdCase = SmartSag.caseHandler.createCase(applicantID, residenceID, departmentID);
        
        System.out.println("Sagen er blevet oprettet!");
    }
    
    private void commandDeleteCase(String[] arguments) {
        System.out.println("Forsøger at slette sagen...");
        
        int caseID = Integer.valueOf(arguments[0]);
        SmartSag.caseHandler.deleteCase(caseID);
        
        System.out.println("Sagen er blevet slettet!");
    }
}
