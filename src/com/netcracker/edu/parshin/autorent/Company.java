package com.netcracker.edu.parshin.autorent;

import java.io.File;
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
public class Company {
    private int companyId;
    private String name;
    private String site;
    private String phoneNumber;

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public void listCompany(){
        try {
            File xmlFile = new File("DataBase.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("companies");
            for(int i = 0; i < nList.getLength(); i++){
                Node nNode = nList.item(i);
                System.out.println("***" + nNode.getNodeName() + "***" + "\n");
                if(nNode.getNodeType() == Node.ELEMENT_NODE){
                    Element el = (Element) nNode;
                    NodeList companyList = el.getElementsByTagName("company");
                    for(int j = 0; j < companyList.getLength(); j++){
                        Node node1 = companyList.item(j);
                        if(node1.getNodeType() == node1.ELEMENT_NODE){
                            Element el1 = (Element) node1;
                            System.out.println("company_id: " 
                                    + el1.getElementsByTagName("companyId").item(0).getTextContent());
                            System.out.println("name: " 
                                    + el1.getElementsByTagName("name").item(0).getTextContent());
                            System.out.println("site: " 
                                    + el1.getElementsByTagName("site").item(0).getTextContent());
                            System.out.println("phone_number: " 
                                    + el1.getElementsByTagName("phoneNumber").item(0).getTextContent() + "\n");
                        }
                    }
                }                    
            }
        } catch (Exception e){
            e.printStackTrace();
        } 
    }
    
    public void addCompany(){
        try {
            File xmlFile = new File("DataBase.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("companies");
            for(int i = 0; i < nList.getLength(); i++){
                Node nNode = nList.item(i);
                Element newEl = doc.createElement("company");
                nNode.appendChild(newEl);
                
                Element field1 = doc.createElement("companyId");
                field1.appendChild(doc.createTextNode(Integer.toString(this.companyId)));
                newEl.appendChild(field1);
                Element field2 = doc.createElement("name");
                field2.appendChild(doc.createTextNode(this.name));
                newEl.appendChild(field2);
                Element field3 = doc.createElement("site");
                field3.appendChild(doc.createTextNode(this.site));
                newEl.appendChild(field3);
                Element field4 = doc.createElement("phoneNumber");
                field4.appendChild(doc.createTextNode(this.phoneNumber));
                newEl.appendChild(field4);
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
    
    public void removeCompany(){
        try {
            File xmlFile = new File("DataBase.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("companies");
            for(int i = 0; i < nList.getLength(); i++){
                Node nNode = nList.item(i);
                if(nNode.getNodeType() == Node.ELEMENT_NODE){
                    Element el = (Element) nNode;
                    NodeList companyList = el.getElementsByTagName("company");
                    for(int j = 0; j < companyList.getLength(); j++){
                        Node node1 = companyList.item(j);
                        if(node1.getNodeType() == node1.ELEMENT_NODE){
                            Element el1 = (Element) node1;
                            if(el1.getElementsByTagName("companyId").item(0).getTextContent().equals(Integer.toString(this.companyId))){
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
