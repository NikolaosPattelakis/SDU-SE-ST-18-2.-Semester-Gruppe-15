/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Smartsag.DTO;

import smartsag.DTO.Interfaces.BuilderInterface;
import smartsag.DTO.Interfaces.CaseInformationInterface;

/**
 *
 * @author sande
 */
public class CaseInformation implements CaseInformationInterface {

    private String description;
    
    private CaseInformation() {}

    public static CaseInformation.Builder builder() {
        return new CaseInformation.Builder();
    }
    
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }
    
    public static class Builder implements BuilderInterface<CaseInformation> {

        CaseInformation informationPersonal;

        private Builder() {
            this.informationPersonal = new CaseInformation();
        }

        public Builder withDescription(String description) {
            this.informationPersonal.setDescription(description);
            return this;
        }

        public CaseInformation build() {
            return this.informationPersonal;
        }
    }
    
}
