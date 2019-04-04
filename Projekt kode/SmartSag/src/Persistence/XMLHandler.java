
package Persistence;

import smartsag.Tags;
import java.io.IOException;
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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Handles data is stored and read on XML files.
 * Extends @XPathHandler <br>
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
    protected void saveFile(Document document) {
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(filepath);

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
     * Creates a List of nodes, based on a XPathExpression.
     *
     * @param xpathExpression
     * @param document
     * @return NodeList
     */
    protected NodeList getNodeList(String xpathExpression, Document document){

        NodeList nodesList = null;
        try {
            nodesList = (NodeList) this.getXPathExpression(xpathExpression).evaluate(document, XPathConstants.NODESET);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(XMLHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nodesList;
    }

    /**
     * Creates a node, based on an XPathExpression.
     *
     * @param xPathExpression
     * @param document
     * @return Node
     */
    protected Node getNode(String xPathExpression, Document document){

        Node node = null;
        try {
            node = (Node) this.getXPathExpression(xPathExpression).evaluate(document, XPathConstants.NODE);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(XMLHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return node;
    }

    /**
     *Checks whether a node exists.
     *
     * @param newNode
     * @return
     */
    protected boolean checkIfNodeExists(NodeList nodes){
        return (nodes != null && nodes.getLength() > 0) == true;
    }

    /**
     * Obtains a specific value of a specific entry, based on an ID and a tag.
     *
     * @param filepath
     * @param ID
     * @param tag
     * @return String value
     */
    protected String getValue(int ID, String tag) {
        String value = null;

        this.setExpressionElementAtID(ID, tag);
        
        try {
            value = this.getNode(this.expressionString, this.document).getNodeValue();
        } catch (Exception ex) {
            Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;
    }
    
    
}
