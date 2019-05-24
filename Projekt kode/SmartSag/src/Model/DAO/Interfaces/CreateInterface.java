package Model.DAO.Interfaces;

import Smartsag.DTO.DTO;

/**
 * 
 * An interface with the create method that can be used by a DAO.
 */
public interface CreateInterface {
    
    public void create(DTO objectToSave);
    
}
