/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.POJO.LoginInformation;

import Model.POJO.Interfaces.BuilderInterface;

/**
 *
 * @author Lupo
 */
public interface PasswordInterface<O> {
    
    BuilderInterface<O> password(String password);
}
