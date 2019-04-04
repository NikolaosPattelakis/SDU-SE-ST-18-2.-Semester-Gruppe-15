
package Persistence;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

/**
 * Handles Strings and creates XPathExpressions.
 * Expressions are used to navigate XML files.
 * implements @XPathCommands
 * 
 */
class XPathHandler implements XPathCommands {

    protected XPathExpression expressionXPath;
    protected String expressionString;
    protected XPath xPath;

    /**
     * Creates an instance of @XPath
     */
    private void createXPath() {
        this.xPath = XPathFactory.newInstance().newXPath();
    }

    /**
     * Sets variable expressionString to find a specific ID of a node.
     * 
     * @param ID 
     */
    protected void setExpressionAtID(int ID) {
        this.createXPath();
        this.expressionString = XPathHandler.XPATH_AT_ID
                + ID
                + XPathHandler.XPATH_AT_ID_END;
    }

    /**
     * Sets variable expressionString to find the last ID.
     */
    protected void setExpressionAtLastID() {
        this.createXPath();
        this.expressionString = XPathHandler.LAST_ID;
    }

    /**
     * Sets variable expressionString to find a node that contains a specific ID.
     * @param ID 
     */
    protected void setExpressionContainsID(int ID) {
        this.createXPath();
        this.expressionString = XPathHandler.XPATH_CONTAINS_ID
                + ID
                + XPathHandler.XPATH_CONTAINS_ID_END
                + XPathHandler.XPATH_TO_TEXT;

    }

    /**
     * Sets variable expressionString to find a specific element at a specific node based on ID.
     * @param ID
     * @param element 
     */
    protected void setExpressionElementAtID(int ID, String element) {
        this.createXPath();
        this.expressionString = XPathHandler.XPATH_AT_ID
                + ID
                + XPathHandler.XPATH_CONTINUE
                + element
                + XPathHandler.XPATH_TO_TEXT;
    }

    /**
     * Creates an XPathExpression given a String input
     *
     * @param xPathExpression
     * @return
     */
    protected XPathExpression getXPathExpression(String xPathExpression) {
        try {
            this.expressionXPath = this.xPath.compile(xPathExpression);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.expressionXPath;
    }
}
