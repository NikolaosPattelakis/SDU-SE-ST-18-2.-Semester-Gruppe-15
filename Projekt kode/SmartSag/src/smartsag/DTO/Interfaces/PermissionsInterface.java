/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartsag.DTO.Interfaces;

/**
 *
 * @author Lupo
 */
public interface PermissionsInterface {
    
    public boolean canCreate();
    public void setCanCreate(boolean canCreate);
    
    public boolean canEdit();
    public void setCanEdit(boolean canEdit);
    
    public boolean canRead();
    public void setCanRead(boolean canReadFull);
    
    public boolean canDelete();
    public void setCanDelete(boolean canDelete);
}
