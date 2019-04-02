/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartsag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * Handles the flow of data between XML files and Domain
 */
public class DataHandler {

    private String xPathExpression;
    private final String filepath;

    private final String nameTag = "Name";
    private final String roleCanCreateTag = "RoleCanCreate";
    private final String roleCanEditTag = "RoleCanEdit";
    private final String roleCanReadTag = "RoleCanRead";
    private final String roleCanDeleteTag = "RoleCanDelete";

    private final String userCanCreateTag = "UserCanCreate";
    private final String userCanEditTag = "UserCanEdit";
    private final String userCanReadTag = "UserCanRead";
    private final String userCanDeleteTag = "UserCanDelete";

    private final String caseCanCreateTag = "CaseCanCreate";
    private final String caseCanEditTag = "CaseCanEdit";
    private final String caseCanReadTag = "CaseCanRead";
    private final String caseCanDeleteTag = "CaseCanDelete";

    protected DataHandler(String filepath) {
        this.filepath = filepath;
    }

    /**
     * *Method that creates a document, given a String filepath
     *
     * @return Document
     * @throws Exception
     */
    private Document getDocument() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(filepath);
        document.getDocumentElement().normalize();

        return document;
    }

    /**
     * After editing parameters and elements in file, saves the file
     *
     * @param document
     * @throws Exception
     */
    private void saveFile(Document document) throws Exception {
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(filepath);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(source, result);
    }

    /**
     * Creates an XPathExpression given a String input
     *
     * @param xPathExpression
     * @return
     * @throws Exception
     */
    private XPathExpression getXPathExpression(String xPathExpression) throws Exception {
        XPathFactory xpathFactory = XPathFactory.newInstance();

        XPath xpath = xpathFactory.newXPath();

        XPathExpression expression = xpath.compile(xPathExpression);
        return expression;
    }

    /**
     * Checks if an ID exists, based on its path
     *
     * @param ID
     * @return boolean
     * @throws Exception
     */
    public boolean checkIfIDExists(int ID) throws Exception {

        //XPath, checks for specific RoleID.
        xPathExpression = "/roles/role[contains(@RoleID,'" + ID + "')]/Name/text()";

        //Calls method to check whether the node exists
        return checkIfNodeExists(getNodeList(xPathExpression, getDocument())) == true;
    }

    /**
     * Creates a List of nodes, based on XPathExpression
     *
     * @param xpathExpression
     * @param document
     * @return NodeList
     * @throws Exception
     */
    private NodeList getNodeList(String xpathExpression, Document document) throws Exception {

        NodeList nodesList = (NodeList) getXPathExpression(xpathExpression).evaluate(document, XPathConstants.NODESET);
        return nodesList;
    }

    /**
     * Creates a node, based on XPathExpression
     *
     * @param xPathExpression
     * @param document
     * @return Node
     * @throws Exception
     */
    private Node getNode(String xPathExpression, Document document) throws Exception {

        Node node = (Node) getXPathExpression(xPathExpression).evaluate(document, XPathConstants.NODE);
        return node;
    }

    /**
     * All nodes have attributes. Therefore we check whether they exist, to
     * verify the nodes existence
     *
     * @param newNode
     * @return
     * @throws Exception
     */
    private boolean checkIfNodeExists(NodeList nodes) throws Exception {

        return (nodes != null && nodes.getLength() > 0) == true;
    }

    /**
     * Gets multiple values based on a search query Query is of XPathExpression
     * form
     *
     * @param document
     * @param xPathExpression
     * @return
     * @throws Exception
     */
    private List<String> getMultipleValues(Document document, String xPathExpression) throws Exception {

        List<String> values = new ArrayList<>();

        NodeList nodes = getNodeList(xPathExpression, document);

        for (int i = 0; i < nodes.getLength(); i++) {
            values.add(nodes.item(i).getNodeValue());

        }
        return values;
    }

    /**
     * Creates a new role and inserts it into an XML file
     *
     * @param roleInput
     * @throws Exception
     */
    public void createRolesXML(Role roleInput) throws Exception {
        Role newRole = roleInput;

        //Calls method to create document
        Document document = this.getDocument();

        Element root = document.getDocumentElement();

        Element xmlRole = document.createElement("role");

        Attr roleID = document.createAttribute("RoleID");
        Element name = document.createElement("Name");

        Element roleCanCreate = document.createElement(this.roleCanCreateTag);
        Element roleCanEdit = document.createElement(this.roleCanEditTag);
        Element roleCanRead = document.createElement(this.roleCanEditTag);
        Element roleCanDelete = document.createElement(this.caseCanDeleteTag);

        Element userCanCreate = document.createElement(this.userCanCreateTag);
        Element userCanEdit = document.createElement(this.userCanEditTag);
        Element userCanRead = document.createElement(this.userCanReadTag);
        Element userCanDelete = document.createElement(this.userCanDeleteTag);

        Element caseCanCreate = document.createElement(this.caseCanCreateTag);
        Element caseCanEdit = document.createElement(this.caseCanEditTag);
        Element caseCanRead = document.createElement(this.caseCanReadTag);
        Element caseCanDelete = document.createElement(this.caseCanDeleteTag);

        roleID.setValue(Integer.toString(newRole.getRoleID()));
        name.appendChild(document.createTextNode(newRole.getName()));

        roleCanCreate.appendChild(document.createTextNode(Boolean.toString(newRole.getRoleCanCreate())));
        roleCanEdit.appendChild(document.createTextNode(Boolean.toString(newRole.getRoleCanEdit())));
        roleCanRead.appendChild(document.createTextNode(Boolean.toString(newRole.getRoleCanRead())));
        roleCanDelete.appendChild(document.createTextNode(Boolean.toString(newRole.getRoleCanDelete())));

        userCanCreate.appendChild(document.createTextNode(Boolean.toString(newRole.getUserCanCreate())));
        userCanEdit.appendChild(document.createTextNode(Boolean.toString(newRole.getUserCanEdit())));
        userCanRead.appendChild(document.createTextNode(Boolean.toString(newRole.getUserCanRead())));
        userCanDelete.appendChild(document.createTextNode(Boolean.toString(newRole.getUserCanDelete())));

        caseCanCreate.appendChild(document.createTextNode(Boolean.toString(newRole.getCaseCanCreate())));
        caseCanEdit.appendChild(document.createTextNode(Boolean.toString(newRole.getCaseCanEdit())));
        caseCanRead.appendChild(document.createTextNode(Boolean.toString(newRole.getCaseCanRead())));
        caseCanDelete.appendChild(document.createTextNode(Boolean.toString(newRole.getCaseCanDelete())));

        xmlRole.setAttributeNode(roleID);
        xmlRole.appendChild(name);

        xmlRole.appendChild(roleCanCreate);
        xmlRole.appendChild(roleCanEdit);
        xmlRole.appendChild(roleCanRead);
        xmlRole.appendChild(roleCanDelete);

        xmlRole.appendChild(userCanCreate);
        xmlRole.appendChild(userCanEdit);
        xmlRole.appendChild(userCanRead);
        xmlRole.appendChild(userCanDelete);

        xmlRole.appendChild(caseCanCreate);
        xmlRole.appendChild(caseCanEdit);
        xmlRole.appendChild(caseCanRead);
        xmlRole.appendChild(caseCanDelete);

        root.appendChild(xmlRole);

        saveFile(document);
    }

    /**
     * Gets all the specific roles of a XML file Returns them as an ArrayList
     *
     * @param tag
     * @return
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public List<Role> getRolesXML(String tag) throws Exception {
        List<Role> roles = new ArrayList<>();
        Role role;

        Document document = getDocument();

        NodeList nodesList = document.getElementsByTagName(tag);
        for (int i = 0; i < nodesList.getLength(); i++) {
            Node node = nodesList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;

                role = new Role();
                role.setID(Integer.parseInt(eElement.getAttribute("RoleID")));
                role.setName(eElement.getElementsByTagName("Name").item(0).getTextContent());

                role.setRoleCanCreate(Boolean.parseBoolean(eElement.getElementsByTagName(this.roleCanCreateTag).item(0).getTextContent()));
                role.setRoleCanEdit(Boolean.parseBoolean(eElement.getElementsByTagName(this.roleCanEditTag).item(0).getTextContent()));
                role.setRoleCanRead(Boolean.parseBoolean(eElement.getElementsByTagName(this.roleCanReadTag).item(0).getTextContent()));
                role.setRoleCanDelete(Boolean.parseBoolean(eElement.getElementsByTagName(this.roleCanDeleteTag).item(0).getTextContent()));

                role.setUserCanCreate(Boolean.parseBoolean(eElement.getElementsByTagName(this.userCanCreateTag).item(0).getTextContent()));
                role.setUserCanEdit(Boolean.parseBoolean(eElement.getElementsByTagName(this.userCanEditTag).item(0).getTextContent()));
                role.setUserCanRead(Boolean.parseBoolean(eElement.getElementsByTagName(this.userCanReadTag).item(0).getTextContent()));
                role.setUserCanDelete(Boolean.parseBoolean(eElement.getElementsByTagName(this.userCanDeleteTag).item(0).getTextContent()));

                role.setCaseCanCreate(Boolean.parseBoolean(eElement.getElementsByTagName(this.caseCanCreateTag).item(0).getTextContent()));
                role.setCaseCanEdit(Boolean.parseBoolean(eElement.getElementsByTagName(this.caseCanEditTag).item(0).getTextContent()));
                role.setCaseCanRead(Boolean.parseBoolean(eElement.getElementsByTagName(this.caseCanReadTag).item(0).getTextContent()));
                role.setCaseCanDelete(Boolean.parseBoolean(eElement.getElementsByTagName(this.caseCanDeleteTag).item(0).getTextContent()));

                roles.add(role);
            }
        }
        return roles;
    }

    /**
     * Reads and return a single role from an XML file based on its ID
     *
     * @param roleID
     * @return
     * @throws Exception
     */
    public Role getRole(int roleID) throws Exception {
        Role role;

        role = new Role();
        
        role.setID(roleID);
        role.setName(getValue(roleID, this.nameTag));
        
        role.setRoleCanCreate(Boolean.parseBoolean(getValue(roleID, this.roleCanCreateTag)));
        role.setRoleCanEdit(Boolean.parseBoolean(getValue(roleID, this.roleCanEditTag)));
        role.setRoleCanRead(Boolean.parseBoolean(getValue(roleID, this.roleCanReadTag)));
        role.setRoleCanDelete(Boolean.parseBoolean(getValue(roleID, this.roleCanDeleteTag)));
        
        role.setUserCanCreate(Boolean.parseBoolean(getValue(roleID, this.userCanCreateTag)));
        role.setUserCanEdit(Boolean.parseBoolean(getValue(roleID, this.userCanEditTag)));
        role.setUserCanRead(Boolean.parseBoolean(getValue(roleID, this.userCanReadTag)));
        role.setUserCanDelete(Boolean.parseBoolean(getValue(roleID, this.userCanDeleteTag)));
        
        role.setCaseCanCreate(Boolean.parseBoolean(getValue(roleID, this.caseCanCreateTag)));
        role.setCaseCanEdit(Boolean.parseBoolean(getValue(roleID, this.caseCanEditTag)));
        role.setCaseCanRead(Boolean.parseBoolean(getValue(roleID, this.caseCanReadTag)));
        role.setCaseCanDelete(Boolean.parseBoolean(getValue(roleID, this.caseCanDeleteTag)));
        

        return role;
    }

    /**
     * Obtains the value of a specific element Element is of a specific node,
     * based on its ID
     *
     * @param filepath
     * @param roleID
     * @param element
     * @return String value
     * @throws Exception
     */
    private String getValue(int roleID, String element) throws Exception {

        xPathExpression = "/roles/role[@RoleID='" + roleID + "']/" + element + "/text()";

        //Calls method to create document
        Document document = getDocument();

        return getNode(xPathExpression, document).getNodeValue();
    }

    /**
     * Finds the last ID of the file and returns it
     *
     * @return String ID
     * @throws Exception
     */
    public String getLastID() throws Exception {

        xPathExpression = "//role[last()]/@RoleID";
        //Calls method to create document
        Document document = getDocument();

        XPath xPath = XPathFactory.newInstance().newXPath();
        return xPath.evaluate(xPathExpression, document);
    }

    /**
     * Edits a specific element of a specific node, based on its ID
     *
     * @param roleID
     * @param element
     * @param newValue
     * @throws Exception
     */
    public void editNode(int roleID, String element, String newValue) throws Exception {
        //Calls method to create document
        Document document = getDocument();

        //Locate specific node
        XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList nodes = (NodeList) xPath.evaluate("/roles/role[@RoleID='"
                + roleID + "']/"
                + element
                + "/text()", document, XPathConstants.NODESET);
        for (int i = 0; i < nodes.getLength(); i++) {
            nodes.item(i).setTextContent(newValue);
        }
        saveFile(document);
    }

    /**
     * Deletes a specific node based on its id
     *
     * @param ID
     * @throws Exception
     */
    public void deleteNode(int ID) throws Exception {

        xPathExpression = "/roles/role[@RoleID='" + ID + "']";

        //Calls method to create document
        Document document = getDocument();

        Node node = getNode(xPathExpression, document);

        if (checkIfNodeExists(getNodeList(xPathExpression, document)) == true) {
            node.getParentNode().removeChild(node);
            saveFile(document);
        }
    }
}
