
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
    protected void setExpressionAtID(String ID) {
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
     * Sets expression at a specific point, based on id, info type and data point.
     * @param id
     * @param dataPoint 
     */
    protected void setExpressionAtPoint(String id, String dataPoint){
        
        this.createXPath();
        this.expressionString = 
                XPathHandler.XPATH_AT_ID + 
                id + 
                XPathHandler.XPATH_AT_ID_CONTINUE + 
                dataPoint;
        
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
