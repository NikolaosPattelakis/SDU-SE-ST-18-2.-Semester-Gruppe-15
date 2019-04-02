/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartsag;

public class RoleHandler {
    
    public void createRole(Role currentRole, String filepath, Role newRole){
        DataHandler dataHandler = new DataHandler(filepath);
        
        if(currentRole.getRoleCanCreate()==true){
            try{
            dataHandler.createRolesXML(newRole);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public void editRoleValue(Role currentRole, String filepath,int roleIDToEdit, String valueToEdit, String newValue){
        DataHandler dataHandler = new DataHandler(filepath);
        
        if(currentRole.getRoleCanEdit()==true){
            try{
            dataHandler.editNode(roleIDToEdit, valueToEdit, newValue);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public void getRoleInformation(Role currentRole, String filepath, int roleIDToGet){
        DataHandler dataHandler = new DataHandler(filepath);
        
        //Tbc to canReadRole
        if(currentRole.getRoleCanRead()==true){
            try{
                System.out.println(dataHandler.getRole(roleIDToGet).toString());
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public void deleteRole(Role currentRole, String filepath, int roleIDToDelete){
        DataHandler dataHandler = new DataHandler(filepath);
        
        if(currentRole.getRoleCanDelete()==true){
            try{
            dataHandler.deleteNode(roleIDToDelete);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
