package smartsag.DTO.Interfaces;

/**
 *
 * Interface holding the methods for getting and setting permissions information.
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
