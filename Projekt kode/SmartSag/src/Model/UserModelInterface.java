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
public interface UserModelInterface {
    
    public POJO getUser();
    public void createUser(POJO newUser);
    public void editUser();
    public void readFullUser();
    public void readPartialUser();
    public void deleteUser();
}
