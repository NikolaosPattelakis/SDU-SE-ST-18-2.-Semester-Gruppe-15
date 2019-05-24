package Smartsag.DTO;

import smartsag.DTO.Interfaces.BuilderInterface;
import smartsag.DTO.Interfaces.CaseInformationInterface;

/**
 *
 * Class that is holding case information, to be used as a variable inside the DTO.
 */
public class CaseInformation implements CaseInformationInterface {

    private String description;
    
    private CaseInformation() {}

    /**
     * Gets builder to be used in the construction of this class object.
     *
     * @return
     */
    public static CaseInformation.Builder builder() {
        return new CaseInformation.Builder();
    }
    
    /**
     * 
     * @return case description.
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Sets case description.
     * @param description 
     */
    @Override
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Inner class used to construct the outer CaseInformation class.
     */
    public static class Builder implements BuilderInterface<CaseInformation> {

        CaseInformation informationPersonal;

        private Builder() {
            this.informationPersonal = new CaseInformation();
        }

        /**
         * Sets case description.
         * @param description
         * @return 
         */
        public Builder withDescription(String description) {
            this.informationPersonal.setDescription(description);
            return this;
        }

        /**
         * Finalizes the construction of the class by building it.
         * @return 
         */
        public CaseInformation build() {
            return this.informationPersonal;
        }
    }
    
}
