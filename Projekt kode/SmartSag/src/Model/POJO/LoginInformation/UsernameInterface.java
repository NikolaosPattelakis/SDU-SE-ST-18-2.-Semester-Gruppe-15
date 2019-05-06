/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.POJO.LoginInformation;

/**
 *
 * @author Lupo
 */
public interface UsernameInterface<O> {
    
    PasswordInterface<O> username(String username);
}
