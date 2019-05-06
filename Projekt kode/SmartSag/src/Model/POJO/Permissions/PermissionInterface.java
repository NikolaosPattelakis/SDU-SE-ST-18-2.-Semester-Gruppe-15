/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.POJO.Permissions;

/**
 *
 * @author Lupo
 */
public interface PermissionInterface {
    
    public boolean canCreate();
    public void setCanCreate(boolean canCreate);
    
    public boolean canEdit();
    public void setCanEdit(boolean canEdit);
    
    public boolean canReadFull();
    public void setCanReadFull(boolean canReadFull);
    
    public boolean canReadPartial();
    public void setCanReadPartial(boolean canReadPartial);
    
    public boolean canDelete();
    public void setCanDelete(boolean canDelete);
}
