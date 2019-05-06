/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.POJO.POJO;

/**
 *
 * @author Lupo
 */
public interface CaseModelInterface {
    
    public void getCase();
    public void createCase(POJO caseDAO);
    public void editCase();
    public void readFullCase();
    public void readPartialCase();
    public void deleteCase();
    
}
