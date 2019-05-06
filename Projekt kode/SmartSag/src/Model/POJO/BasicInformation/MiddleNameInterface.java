/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.POJO.BasicInformation;

/**
 *
 * @author Lupo
 * @param <O>
 */
public interface MiddleNameInterface<O> {
    
    LastNameInterface<O> middleName(String middleName);
}
