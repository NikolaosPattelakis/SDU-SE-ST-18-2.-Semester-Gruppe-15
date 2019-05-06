/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.POJO.BasicInformation;

import Model.POJO.Interfaces.BuilderInterface;

/**
 *
 * @author Lupo
 */
public interface GenderInterface<O> {
    
    BuilderInterface<O> gender(String gender);
}
