/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartsag;

import java.util.HashMap;

/**
 * Abstract class, holds the variables that hold the basic data information.
 * 
 */
abstract class InformationData {

    private int id;
    private HashMap<String, String> information;

    InformationData(){
        this.information = new HashMap<>();
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the information
     */
    public HashMap<String, String> getInformation() {
        return information;
    }

    /**
     * @param information the information to set
     */
    public void setInformation(HashMap<String, String> information) {
        this.information = information;
    }
    
    /**
     * Adds information on the HashMap.
     * @param keyTag
     * @param valueTag 
     */
    public void addInformation(String keyTag, String valueTag){
        
        information.put(keyTag, valueTag);
        
    }
    
    /**
     * Removes information from the HashMap
     * @param keyTag 
     */
    protected void removeInformation(String keyTag){
        if(this.hasInformation(keyTag)== true){
        information.remove(keyTag);
        }
    }
    
    /**
     * Checks whether the HashMap contains an information based on @Tag
     * @param tag
     * @return boolean
     */
    public boolean hasInformation(String tag){
        boolean hasInformation = false;
        if(information.containsKey(tag) == true){
            hasInformation = true;
        }
        return hasInformation;
    }
    
    /*
    *@return String
    */
    @Override
    public String toString(){
        return this.getInformation().toString();
    }
}
