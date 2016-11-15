package com.netcracker.edu.parshin.autorent;

import java.io.File;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Dmitry Parshin, 2016
 */
public class Customer {
    private int customerId;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String phoneNumber;
    private Date registrationDate;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
    
    public void listCustomer(){
        try {
            File xmlFile = new File("DataBase.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("customers");
            for(int i = 0; i < nList.getLength(); i++){
                Node nNode = nList.item(i);
                System.out.println("***" + nNode.getNodeName() + "***" + "\n");
                if(nNode.getNodeType() == Node.ELEMENT_NODE){
                    Element el = (Element) nNode;
                    NodeList companyList = el.getElementsByTagName("customer");
                    for(int j = 0; j < companyList.getLength(); j++){
                        Node node1 = companyList.item(j);
                        if(node1.getNodeType() == node1.ELEMENT_NODE){
                            Element el1 = (Element) node1;
                            System.out.println("customer_id: " 
                                    + el1.getElementsByTagName("customerId").item(0).getTextContent());
                            System.out.println("login: " 
                                    + el1.getElementsByTagName("login").item(0).getTextContent());
                            System.out.println("password: " 
                                    + el1.getElementsByTagName("password").item(0).getTextContent());
                            System.out.println("first_name: " 
                                    + el1.getElementsByTagName("firstName").item(0).getTextContent());
                            System.out.println("last_name: " 
                                    + el1.getElementsByTagName("lastName").item(0).getTextContent());
                            System.out.println("birth_date: " 
                                    + el1.getElementsByTagName("birthDate").item(0).getTextContent());
                            System.out.println("phone_number: " 
                                    + el1.getElementsByTagName("phoneNumber").item(0).getTextContent());
                            System.out.println("registration_date: " 
                                    + el1.getElementsByTagName("registrationDate").item(0).getTextContent()
                                    + "\n");
                        }
                    }
                }                    
            }
        } catch (Exception e){
            e.printStackTrace();
        } 
    }
    
    public void addCustomer(){
        try {
            File xmlFile = new File("DataBase.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("customers");
            for(int i = 0; i < nList.getLength(); i++){
                Node nNode = nList.item(i);
                Element newEl = doc.createElement("customer");
                nNode.appendChild(newEl);
                
                Element field1 = doc.createElement("customerId");
                field1.appendChild(doc.createTextNode(Integer.toString(this.customerId)));
                newEl.appendChild(field1);
                Element field2 = doc.createElement("login");
                field2.appendChild(doc.createTextNode(this.login));
                newEl.appendChild(field2);
                Element field3 = doc.createElement("password");
                field3.appendChild(doc.createTextNode(this.password));
                newEl.appendChild(field3);
                Element field4 = doc.createElement("firstName");
                field4.appendChild(doc.createTextNode(this.firstName));
                newEl.appendChild(field4);
                Element field5 = doc.createElement("lastName");
                field5.appendChild(doc.createTextNode(this.lastName));
                newEl.appendChild(field5);
                Element field6 = doc.createElement("birthDate");
                field6.appendChild(doc.createTextNode("15.11.2016"));
                newEl.appendChild(field6);
                Element field7 = doc.createElement("phoneNumber");
                field7.appendChild(doc.createTextNode(this.phoneNumber));
                newEl.appendChild(field7);
                Element field8 = doc.createElement("registrationDate");
                field8.appendChild(doc.createTextNode("15.11.2016"));
                newEl.appendChild(field8);
            }
            TransformerFactory transformerFactory =
            TransformerFactory.newInstance();
            Transformer transformer =
            transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);
        } catch (Exception e){
            e.printStackTrace();
        } 
    }
    
    public void removeCustomer(){
        try {
            File xmlFile = new File("DataBase.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("customers");
            for(int i = 0; i < nList.getLength(); i++){
                Node nNode = nList.item(i);
                if(nNode.getNodeType() == Node.ELEMENT_NODE){
                    Element el = (Element) nNode;
                    NodeList companyList = el.getElementsByTagName("customer");
                    for(int j = 0; j < companyList.getLength(); j++){
                        Node node1 = companyList.item(j);
                        if(node1.getNodeType() == node1.ELEMENT_NODE){
                            Element el1 = (Element) node1;
                            if(el1.getElementsByTagName("customerId").item(0).getTextContent().equals(Integer.toString(this.customerId))){
                                el1.getParentNode().removeChild(el1);
                            }
                        }
                    }
                }
            }
            TransformerFactory transformerFactory =
            TransformerFactory.newInstance();
            Transformer transformer =
            transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);
        } catch (Exception e){
            e.printStackTrace();
        } 
    }
}
