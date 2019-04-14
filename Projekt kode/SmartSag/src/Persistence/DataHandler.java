/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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
     * Checks whether ID exists on the file. <br>
     *
     * @param ID
     * @return boolean
     */
    public boolean checkIfIDExists(int ID) {
        boolean exists = false;

        //Calls method to set the expression for the specific ID. 
        this.setExpressionContainsID(ID);

        try {
            //Calls method to check whether the node exists on the while.
            exists = this.checkIfNodeExists(this.getNodeList(this.expressionString, this.document)) == true;
        } catch (Exception ex) {
            Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exists;
    }

    /**
     * Creates an entry on file with a specified tag.
     * @param entryInformationMap
     * @param tag 
     */
    public void createEntry(HashMap<String, String> entryInformationMap, String tag) {

        
        Element root = this.document.getDocumentElement();
        Element newEntry = this.document.createElement(tag);
        
        Attr id = this.document.createAttribute(DataHandler.TAG_ID);
        
        String lastID = this.getLastID();
        String newID = "1";
        if(!lastID.isEmpty()) {
            newID = Integer.toString(Integer.parseInt(lastID) + 1);
        }

        id.setValue(newID);
        newEntry.setAttributeNode(id);

        for (String key : entryInformationMap.keySet()) {
            Element newNode = document.createElement(key);
            newNode.appendChild(document.createTextNode(entryInformationMap.get(key)));
            newEntry.appendChild(newNode);
        }
        root.appendChild(newEntry);
        this.saveFile(document);
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
            listOfEntries.add(getEntryInformation(i));
        }
        return listOfEntries;
    }

    /**
     * Reads and return a single entry from an XML file based on its ID.
     *
     * @param ID
     * @return
     */
    public HashMap<String, String> getEntryInformation(int ID) {

        HashMap<String, String> entryInformation = new HashMap<>();

        this.setExpressionAtID(ID);

        NodeList nodes = this.getNodeList(this.expressionString, this.document);
        NodeList nodeData = nodes.item(0).getChildNodes();
        for (int i = 0; i < nodeData.getLength(); i++) {
            entryInformation.put(nodeData.item(i).getNodeName(), nodeData.item(i).getTextContent());
        }
        return entryInformation;
    }

    /**
     * Gets multiple values based on a search query. <br>
     * Query is of XPathExpression form <br>
     *
     * UNFINISHED METHOD
     * 
     * @param xPathExpression
     * @return Map<String, String>
     */
    private Map<String, String> getMultipleValues(String xPathExpression) {

        Map<String, String> values = new HashMap<>();

        NodeList nodes = this.getNodeList(xPathExpression, this.document);

        for (int i = 0; i < nodes.getLength(); i++) {
            if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                values.put(nodes.item(i).getNodeName(), nodes.item(i).getTextContent());
            }
        }
        return values;
    }

    /**
     * Deletes a specific node based on its id
     *
     * @param ID
     */
    public void deleteNode(int ID) {

        Node node;
        try {
            this.setExpressionAtID(ID);
            node = this.getNode(expressionString, document);
            if (this.checkIfNodeExists(this.getNodeList(expressionString, document)) == true) {
                node.getParentNode().removeChild(node);
                this.saveFile(document);
            }
        } catch (Exception ex) {
            Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Edits a specific element of a specific node, based on its ID
     *
     * @param ID
     * @param element
     * @param newValue
     */
    public void editNode(int ID, String element, String newValue) {

        NodeList nodes = null;
        try {
            this.setExpressionElementAtID(ID, element);
            nodes = (NodeList) this.xPath.evaluate(expressionString, this.document, XPathConstants.NODESET);
            nodes.item(0).setTextContent(newValue);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        saveFile(this.document);
    }

    /**
     * Finds the last ID of the XML file and returns it.
     *
     * @return String ID
     */
    public String getLastID() {
        String lastID = null;
        this.setExpressionAtLastID();
        try {
            lastID = this.xPath.evaluate(this.expressionString, this.document);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lastID;
    }
}
