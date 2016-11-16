package com.netcracker.edu.parshin.autorent;

import java.io.File;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
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
public class Session {
    private long sessionId;
    private int carId;
    private Date startDate;
    private Date endDate;
    private int driverId;

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }
    private int customerId;

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    
    public void listSession(){
        try {
            File xmlFile = new File("DataBase.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("sessions");
            for(int i = 0; i < nList.getLength(); i++){
                Node nNode = nList.item(i);
                System.out.println("***" + nNode.getNodeName() + "***" + "\n");
                if(nNode.getNodeType() == Node.ELEMENT_NODE){
                    Element el = (Element) nNode;
                    NodeList companyList = el.getElementsByTagName("session");
                    for(int j = 0; j < companyList.getLength(); j++){
                        Node node1 = companyList.item(j);
                        if(node1.getNodeType() == node1.ELEMENT_NODE){
                            Element el1 = (Element) node1;
                            System.out.println("session_id: " 
                                    + el1.getElementsByTagName("sessionId").item(0).getTextContent());
                            System.out.println("car_id: " 
                                    + el1.getElementsByTagName("carId").item(0).getTextContent());
                            System.out.println("start_date: " 
                                    + el1.getElementsByTagName("startDate").item(0).getTextContent());
                            System.out.println("end_date: " 
                                    + el1.getElementsByTagName("endDate").item(0).getTextContent());
                            System.out.println("driver_id: " 
                                    + el1.getElementsByTagName("driverId").item(0).getTextContent());
                            System.out.println("customer_id: " 
                                    + el1.getElementsByTagName("customerId").item(0).getTextContent() + "\n");
                        }
                    }
                }                    
            }
        } catch (Exception e){
            e.printStackTrace();
        } 
    }
    
    public void addSession(){
        try {
            File xmlFile = new File("DataBase.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("sessions");
            for(int i = 0; i < nList.getLength(); i++){
                Node nNode = nList.item(i);
                Element newEl = doc.createElement("session");
                nNode.appendChild(newEl);
                
                Element field1 = doc.createElement("sessionId");
                field1.appendChild(doc.createTextNode(Long.toString(this.sessionId)));
                newEl.appendChild(field1);
                Element field2 = doc.createElement("carId");
                field2.appendChild(doc.createTextNode(Integer.toString(this.carId)));
                newEl.appendChild(field2);
                Element field3 = doc.createElement("startDate");
                field3.appendChild(doc.createTextNode("15.11.2016"));
                newEl.appendChild(field3);
                Element field4 = doc.createElement("endDate");
                field4.appendChild(doc.createTextNode("15.11.2016"));
                newEl.appendChild(field4);
                Element field5 = doc.createElement("driverId");
                field5.appendChild(doc.createTextNode(Integer.toString(this.driverId)));
                newEl.appendChild(field5);
                Element field6 = doc.createElement("customerId");
                field6.appendChild(doc.createTextNode(Integer.toString(this.customerId)));
                newEl.appendChild(field6);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", 2);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);
        } catch (Exception e){
            e.printStackTrace();
        } 
    }
    
    public void removeSession(){
        try {
            File xmlFile = new File("DataBase.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("sessions");
            for(int i = 0; i < nList.getLength(); i++){
                Node nNode = nList.item(i);
                if(nNode.getNodeType() == Node.ELEMENT_NODE){
                    Element el = (Element) nNode;
                    NodeList companyList = el.getElementsByTagName("session");
                    for(int j = 0; j < companyList.getLength(); j++){
                        Node node1 = companyList.item(j);
                        if(node1.getNodeType() == node1.ELEMENT_NODE){
                            Element el1 = (Element) node1;
                            if(el1.getElementsByTagName("sessionId").item(0).getTextContent().equals(Long.toString(this.sessionId))){
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
