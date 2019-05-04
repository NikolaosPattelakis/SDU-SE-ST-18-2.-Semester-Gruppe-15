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
import smartsag.CaseHandler;
import smartsag.Cases.Case;
import smartsag.Role;
import smartsag.RoleHandler;
import smartsag.SmartSag;

/**
 * @author Sander Knudsen
 */

public class CommandInterface {
    
    private List<Command> availableCommands;
    private boolean shouldQuit = false;
    private final CaseHandler caseHandler;
    private final RoleHandler roleHandler;
    
    public CommandInterface() {
        availableCommands = new ArrayList<Command>();
        
        initializeCommands();
        
        roleHandler = new RoleHandler(0);
        Role role = roleHandler.getRole(0);
        caseHandler = new CaseHandler(role);
        
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
        availableCommands.add(new Command("case-create", new String[] {
            "ApplicantID",
            "ResidenceID",
            "DepartmentID"
        }, x -> commandCreateCase(x)));
        availableCommands.add(new Command("case-view", new String[] {
            "CaseID"
        }, x -> commandViewCase(x)));
        availableCommands.add(new Command("case-assign", new String[] {
            "CaseID",
            "CaseWorkerID"
        }, x -> commandAssignCaseWorker(x)));
        availableCommands.add(new Command("case-delete", new String[] {
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
        
        if (roleHandler.getCurrentRole().isCanCreateCase()) {
            caseHandler.createCase(applicantID, residenceID, departmentID);
            System.out.println("Sagen er blevet oprettet!");
        }
        else {
            System.out.println("Du har ikke adgang til at oprette en sag");
        }
    }
    
    private void commandDeleteCase(String[] arguments) {
        System.out.println("Forsøger at slette sagen...");
        
        int caseID = Integer.valueOf(arguments[0]);
        
        if (roleHandler.getCurrentRole().isCanDeleteCase()) {
            caseHandler.deleteCase(caseID);
            System.out.println("Sagen er blevet slettet!");
        }
        else {
            System.out.println("Du har ikke adgang til at slette en sag");
        }
    }
    
    private void commandViewCase(String[] arguments) {
        int caseID = Integer.valueOf(arguments[0]);
        
        if (roleHandler.getCurrentRole().isCanReadCase()) {
            
            Case myCase = caseHandler.getCase(caseID);
            
            if(myCase == null) {
                System.out.println("Kunne ikke finde en case med dette ID");
                return;
            }
            
            System.out.println("[Sagsoplysninger]");
            System.out.println("ID: " + myCase.getId());
            System.out.println("Ansøger ID: " + myCase.getApplicantID());
            System.out.println("Bopæl ID: " + myCase.getResidenceID());
            System.out.println("Afdeling ID: " + myCase.getDepartmentID());
            System.out.println("Sagsbehandler ID: " + myCase.getCaseWorkerID());
            
            
            // Vis lister af data...
        }
        else {
            System.out.println("Du har ikke adgang til at se informationer om denne case");
        }
    }
    
    private void commandAssignCaseWorker(String[] arguments) {
        int caseID = Integer.valueOf(arguments[0]);
        int caseWorkerID = Integer.valueOf(arguments[1]);
        
        if (roleHandler.getCurrentRole().isCanEditCase()) {
            Case myCase = SmartSag.caseHandler.getCase(caseID);
            
            if(myCase == null) {
                System.out.println("Kunne ikke finde en case med dette ID");
                return;
            }
            
            caseHandler.assignCaseWorker(caseID, caseWorkerID);
            
            System.out.println("Sagsbehandleren er blevet ændret");
        }
        else {
            System.out.println("Du har ikke adgang til at ændre i denne case");
        }
    }
}
