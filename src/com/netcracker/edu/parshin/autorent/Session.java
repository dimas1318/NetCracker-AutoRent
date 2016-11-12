package com.netcracker.edu.parshin.autorent;

import java.io.File;
import java.util.Date;
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
public class Session {
    private long sessionId;
    private int carId;
    private Date startDate;
    private Date endDate;
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
}
