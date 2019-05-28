package Smartsag.DTO;

import smartsag.DTO.enums.Gender;
import smartsag.DTO.Interfaces.BasicInformationInterface;
import smartsag.DTO.Interfaces.BuilderInterface;

/**
 *
 * Class that is holding basic information, to be used as a variable inside the
 * DTO.
 */
public class BasicInformation implements BasicInformationInterface {

    private int cpr;
    private String name;
    private String firstName;
    private String middleName;
    private String lastName;

    private Gender gender;

    private String email;
    private String phoneNumber;

    private BasicInformation() {
    }

    /**
     * Gets builder to be used in the construction of this class object.
     *
     * @return
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     *
     * @return cpr
     */
    @Override
    public int getCPR() {
        return this.cpr;
    }

    /**
     * Sets cpr.
     *
     * @param cpr
     */
    @Override
    public void setCPR(int cpr) {
        this.cpr = cpr;
    }

    /**
     *
     * @return name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Sets name.
     *
     * @param name
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return first name.
     */
    @Override
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName
     */
    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return middle name
     */
    @Override
    public String getMiddleName() {
        return this.middleName;
    }

    /**
     * Sets middle name.
     *
     * @param middleName
     */
    @Override
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     *
     * @return last name
     */
    @Override
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName
     */
    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return gender
     */
    @Override
    public Gender getGender() {
        return this.gender;
    }

    /**
     * Sets gender.
     *
     * @param gender
     */
    @Override
    public void setGender(String gender) {
        this.gender = Gender.valueOf(gender);
    }

    /**
     *
     * @return email
     */
    @Override
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email
     */
    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return phone number
     */
    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number.
     *
     * @param phoneNumber
     */
    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Inner class used to construct the outer BasicInformation class.
     */
    public static class Builder implements BuilderInterface<BasicInformation> {

        BasicInformation informationPersonal;

        /**
         * Finalizes the construction of the class by building it.
         *
         */
        private Builder() {
            this.informationPersonal = new BasicInformation();
        }

        /**
         * Sets name.
         *
         * @param name
         * @return
         */
        public Builder withName(String name) {
            this.informationPersonal.setName(name);
            return this;
        }

        /**
         * Sets cpr.
         *
         * @param cpr
         * @return
         */
        public Builder withCPR(int cpr) {
            this.informationPersonal.setCPR(cpr);
            return this;
        }

        /**
         * Sets first name.
         *
         * @param firstName
         * @return
         */
        public Builder withFirstName(String firstName) {
            this.informationPersonal.setFirstName(firstName);
            return this;
        }

        /**
         * Sets middle name.
         *
         * @param middleName
         * @return
         */
        public Builder withMiddleName(String middleName) {
            this.informationPersonal.setMiddleName(middleName);
            return this;
        }

        /**
         * Sets last name.
         *
         * @param lastName
         * @return
         */
        public Builder withLastName(String lastName) {
            this.informationPersonal.setLastName(lastName);
            return this;
        }

        /**
         * Sets gender.
         *
         * @param gender
         * @return
         */
        public Builder withGender(String gender) {
            this.informationPersonal.setGender(gender);
            return this;
        }

        /**
         * Sets email.
         *
         * @param email
         * @return
         */
        public Builder withEmail(String email) {
            this.informationPersonal.setEmail(email);
            return this;
        }

        /**
         * Sets phone number.
         *
         * @param phoneNumber
         * @return
         */
        public Builder withPhoneNumber(String phoneNumber) {
            this.informationPersonal.setPhoneNumber(phoneNumber);
            return this;
        }

        /**
         * Finalizes the construction of the class by building it.
         *
         * @return
         */
        public BasicInformation build() {
            return this.informationPersonal;
        }
    }
}
