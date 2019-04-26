
package Persistence;

/**
 * Interface that holds all the Strings to be used for the @XPathExpression
 * 
 */
interface XPathCommands {

    String XPATH_AT_ID = "/*/*[@ID='";
    String XPATH_AT_ID_END = "']";
    String XPATH_AT_ID_CONTINUE = "']/"; 
    
    String XPATH_CONTINUE = "/";
    
    String XPATH_CONTAINS_ID = "/*/*[contains(@ID,'";
    String XPATH_TO_TEXT = "/text()";
    String XPATH_CONTAINS_ID_END = "')]";
    
    String LAST_ID = "//*[last()]/@ID";
}
