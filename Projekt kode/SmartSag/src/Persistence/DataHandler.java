/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Handles how data are written and read. <br>
 * extends @XMLHandler
 *
 */
public final class DataHandler extends XMLHandler {

    /**
     * DataHandler constructor. <br>
     * Takes an XML files path as input, that will be used on its instance.
     *
     * @param filepath, String
     */
    public DataHandler(String filepath) {
        super(filepath);
    }

    /**
     * Checks whether a specific ID exists or not.
     *
     * @param ID
     * @return
     */
    public boolean checkIfIDExists(String ID) {

        //Calls method to set the expression for the specific ID. 
        this.setExpressionAtID(ID);

        //Calls method to check whether the node exists.
        boolean exists = this.nodeExists() == true;

        return exists;
    }

    /**
     * Creates an entry on file with a specified tag and a numberedID.
     *
     * @param entryInformationMap
     * @param tag
     */
    public void createNumberedIDEntry(HashMap<String, String> entryInformationMap, String tag) {

        Element root = this.document.getDocumentElement();
        Element newEntry = this.document.createElement(tag);

        Attr id = this.document.createAttribute(DataHandler.TAG_ID);
        String newID = Integer.toString(this.getLastNumberedID() + 1);

        id.setValue(newID);
        newEntry.setAttributeNode(id);

        newEntry.appendChild(this.createAndGetElementMap(entryInformationMap, tag));

        root.appendChild(newEntry);
        this.saveFile();
    }

    /**
     * Creates an entry on file with multiple lists and a specified tag and id.
     * @param mapList
     * @param tag
     * @param newID 
     */
    public void createEntryWithMultipleMaps(
            HashMap<String, HashMap<String, String>> mapList,
            String tag, 
            String newID) {

        
        if (this.checkIfIDExists(newID) == false) {
            Element root = this.document.getDocumentElement();
            Element newEntry = this.document.createElement(tag);

            Attr id = this.document.createAttribute(DataHandler.TAG_ID);

            id.setValue(newID);
            newEntry.setAttributeNode(id);

            for (String key : mapList.keySet()){
                Element subEntry = this.createAndGetElementMap(mapList.get(key), key);
                newEntry.appendChild(subEntry);
            }

            root.appendChild(newEntry);
            this.saveFile();
        }
    }

    /**
     * Creates an entry on file.
     *
     * @param entryInformationMap
     * @param tag
     * @param ID
     */
    public void createEntry(HashMap<String, String> entryInformationMap, String tag, String newID) {

        if (this.checkIfIDExists(newID) == false) {

            Element root = this.document.getDocumentElement();

            Element newEntry = this.document.createElement(tag);

            Attr idAttribute = this.document.createAttribute(DataHandler.TAG_ID);

            idAttribute.setValue(newID);
            newEntry.setAttributeNode(idAttribute);
            
            newEntry.appendChild(this.createAndGetElementMap(entryInformationMap, tag));
            
            root.appendChild(newEntry);
            this.saveFile();
        }
    }
    

    /**
     * Gets specific entries of an XML file based on a tag. <br>
     * Returns them as a List containing HashMaps.
     *
     * @param tag
     * @return List<HashMap<String, String>>
     */
    public List<HashMap<String, String>> getListOfEntries(String tag) {
        List<HashMap<String, String>> listOfEntries = new ArrayList<>();

        NodeList nodesList = document.getElementsByTagName(tag);
        for (int i = 0; i < nodesList.getLength(); i++) {
            listOfEntries.add(getEntryInformation(Integer.toString(i)));
        }
        return listOfEntries;
    }

    /**
     * Reads and return a single entry from an XML file based on its ID.
     *
     * @param ID
     * @return
     */
    public HashMap<String, String> getEntryInformation(String ID) {

        HashMap<String, String> entryInformation = new HashMap<>();

        this.setExpressionAtID(ID);

        NodeList nodes = this.getNodeList();
        NodeList nodeData = nodes.item(0).getChildNodes();
        for (int i = 0; i < nodeData.getLength(); i++) {
            entryInformation.put(nodeData.item(i).getNodeName(), nodeData.item(i).getTextContent());
        }
        return entryInformation;
    }

    
    /**
     * Deletes a specific node based on its id
     *
     * @param ID
     */
    public void deleteEntry(String ID) {

        this.setExpressionAtID(ID);
        this.deleteNode();
        this.saveFile();

    }

    /**
     * Edits a specific element of a specific node, based on its ID
     *
     * @param ID
     * @param infoType
     * @param dataPoint
     * @param newValue
     */
    public void editValue(String ID, String infoType, String dataPoint, String newValue) {

        this.setExpressionAtPoint(ID, infoType, dataPoint);
        this.editNode(newValue);

        this.saveFile();
    }

    /**
     * Finds the last ID of the XML file and returns it.
     *
     * @return Integer ID
     */
    public int getLastNumberedID() {

        this.setExpressionAtLastID();

        String lastIDString = this.getStringFromXPathEvaluation();

        int lastID = Integer.parseInt(lastIDString);
        return lastID;
    }

    /**
     * Obtains a string value at a specific point of an entry, based on id, info
     * type and data point.
     *
     * @param ID
     * @param infoType
     * @param dataPoint
     * @return
     */
    public String getValue(String ID, String infoType, String dataPoint) {

        this.setExpressionAtPoint(ID, infoType, dataPoint);

        String value = this.getStringFromXPathEvaluation();

        return value;
    }

}
