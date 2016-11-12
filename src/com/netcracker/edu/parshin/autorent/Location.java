package com.netcracker.edu.parshin.autorent;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Dmitry Parshin, 2016
 */
public class Location {
    private int locationId;
    private int companyId;
    private String address;

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void listLocation(){
        try {
            File xmlFile = new File("DataBase.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("locations");
            for(int i = 0; i < nList.getLength(); i++){
                Node nNode = nList.item(i);
                System.out.println("***" + nNode.getNodeName() + "***" + "\n");
                if(nNode.getNodeType() == Node.ELEMENT_NODE){
                    Element el = (Element) nNode;
                    NodeList companyList = el.getElementsByTagName("location");
                    for(int j = 0; j < companyList.getLength(); j++){
                        Node node1 = companyList.item(j);
                        if(node1.getNodeType() == node1.ELEMENT_NODE){
                            Element el1 = (Element) node1;
                            System.out.println("location_id: " 
                                    + el1.getElementsByTagName("locationId").item(0).getTextContent());
                            System.out.println("company_id: " 
                                    + el1.getElementsByTagName("companyId").item(0).getTextContent());
                            System.out.println("address: " 
                                    + el1.getElementsByTagName("address").item(0).getTextContent() + "\n");
                        }
                    }
                }                    
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
