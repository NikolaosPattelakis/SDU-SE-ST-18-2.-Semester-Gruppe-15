package Model.DAO.Interfaces;

import Smartsag.DTO.DTO;
import java.util.List;
/**
 * 
 * An interface with the read all method that can be used by a DAO.
 */
public interface ReadAllInterface {
    
    public List<DTO> readAll();
}
