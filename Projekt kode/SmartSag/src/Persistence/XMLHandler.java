package Persistence;

import smartsag.Tags;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Handles data is stored and read on XML files. Extends @XPathHandler <br>
 * Implements @Tags <br>
 *
 */
class XMLHandler extends XPathHandler implements Tags {

    protected final String filepath;
    protected Document document;

    /**
     * Constructor. <br>
     * Takes an XML files path as input, that will be used on its instance.
     * Calls @initDocument to create a @Document based on the files path.
     *
     * @param filepath
     */
    protected XMLHandler(String filepath) {
        super();
        this.filepath = filepath;
        this.initDocument();
    }

    /**
     * Creates a @Document.
     */
    private void initDocument() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(filepath);
        } catch (IOException | ParserConfigurationException | SAXException ex) {
            Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        document.normalize();
    }

    /**
     * After editing parameters and elements in an XML file, saves the file.
     *
     * @param document
     */
    protected void saveFile() {
        DOMSource source = new DOMSource(this.document);
        StreamResult result = new StreamResult(this.filepath);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = transformerFactory.newTransformer();
            transformer.transform(source, result);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Gets a List of nodes, based on a XPathExpression.
     *
     * @param xpathExpression
     * @param document
     * @return NodeList
     */
    protected NodeList getNodeList() {

        NodeList nodesList = null;
        try {
            nodesList = (NodeList) this.xPath.evaluate(this.expressionString, this.document, XPathConstants.NODE);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(XMLHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nodesList;
    }

    /**
     * Gets a node, based on an XPathExpression.
     *
     * @param xPathExpression
     * @param document
     * @return Node
     */
    protected Node getNode() {

        Node node = null;
        try {
            node = (Node) this.xPath.evaluate(this.expressionString, this.document, XPathConstants.NODE);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(XMLHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return node;
    }

    /**
     * Checks whether a node exists.
     *
     * @param newNode
     * @return
     */
    protected boolean nodeExists() {
        NodeList nodeList = this.getNodeList();
        return (nodeList != null && nodeList.getLength() > 0) == true;
    }

    /**
     * Obtains a specific value of a specific entry, based on ID and info type and data point.
     *
     * @return 
     */
    protected String getStringFromXPathEvaluation() {

        String value = null;
        
        try {
            value = this.xPath.evaluate(expressionString, this.document);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(XMLHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return value;

    }
    
    /**
     * Creates and returns an element with an entry name and an assigned value
     * @param entryName
     * @param value
     * @return 
     */
    private Element createAndGetElementWithValue(String entryName, String value){
        
        Element newNode = this.document.createElement(entryName);
        newNode.appendChild(this.document.createTextNode(value));
        return newNode;
    }
    
    /**
     * Creates and returns an element with multiple entries.
     * @param map
     * @param elementName
     * @return 
     */
    protected Element createAndGetElementMap(HashMap<String, String> map, String elementName){
        Element subEntry = this.document.createElement(elementName);
        for(String key : map.keySet()){
            subEntry.appendChild(this.createAndGetElementWithValue(key, map.get(key)));
        }
        return subEntry;
    }
    
    
    /**
     * Edits a node with a new value.
     * @param newValue 
     */
    protected void editNode(String newValue){
        NodeList nodes = this.getNodeList();
        nodes.item(0).setTextContent(newValue);
    }
    
    /**
     * Deletes a specific node.
     */
    protected void deleteNode(){
        Node node = this.getNode();
        
        if(this.nodeExists()== true){
        node.getParentNode().removeChild(node);
    }
    }
}
