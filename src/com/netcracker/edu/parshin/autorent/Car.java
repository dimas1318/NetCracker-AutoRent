package com.netcracker.edu.parshin.autorent;

import java.awt.Image;
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
import org.xml.sax.SAXException;

/**
 *
 * @author Dmitry Parshin, 2016
 */
public class Car {
    private int carId;
    private int companyId;
    private String brand;
    private String name;
    private String color;
    private Image photo;
    private double price;

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Image getPhoto() {
        return photo;
    }

    public void setPhoto(Image photo) {
        this.photo = photo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public void listCar(){
        try {
            File xmlFile = new File("DataBase.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("cars");
            for(int i = 0; i < nList.getLength(); i++){
                Node nNode = nList.item(i);
                System.out.println("***" + nNode.getNodeName() + "***" + "\n");
                if(nNode.getNodeType() == Node.ELEMENT_NODE){
                    Element el = (Element) nNode;
                    NodeList companyList = el.getElementsByTagName("car");
                    for(int j = 0; j < companyList.getLength(); j++){
                        Node node1 = companyList.item(j);
                        if(node1.getNodeType() == node1.ELEMENT_NODE){
                            Element el1 = (Element) node1;
                            System.out.println("car_id: " 
                                    + el1.getElementsByTagName("carId").item(0).getTextContent());
                            System.out.println("company_id: " 
                                    + el1.getElementsByTagName("companyId").item(0).getTextContent());
                            System.out.println("brand: " 
                                    + el1.getElementsByTagName("brand").item(0).getTextContent());
                            System.out.println("name: " 
                                    + el1.getElementsByTagName("name").item(0).getTextContent());
                            System.out.println("color: " 
                                    + el1.getElementsByTagName("color").item(0).getTextContent());
                            System.out.println("photo: " 
                                    + el1.getElementsByTagName("photo").item(0).getTextContent());
                            System.out.println("price: " 
                                    + el1.getElementsByTagName("price").item(0).getTextContent()
                                    + " $/hour" + "\n");
                        }
                    }
                }                    
            }
        } catch (Exception e){
            e.printStackTrace();
        } 
    }
    
    public void addCar(){
        try {
            File xmlFile = new File("DataBase.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("cars");
            for(int i = 0; i < nList.getLength(); i++){
                Node nNode = nList.item(i);
                Element newEl = doc.createElement("car");
                nNode.appendChild(newEl);
                
                Element field1 = doc.createElement("carId");
                field1.appendChild(doc.createTextNode(Integer.toString(this.carId)));
                newEl.appendChild(field1);
                Element field2 = doc.createElement("companyId");
                field2.appendChild(doc.createTextNode(Integer.toString(this.companyId)));
                newEl.appendChild(field2);
                Element field3 = doc.createElement("brand");
                field3.appendChild(doc.createTextNode(this.brand));
                newEl.appendChild(field3);
                Element field4 = doc.createElement("name");
                field4.appendChild(doc.createTextNode(this.name));
                newEl.appendChild(field4);
                Element field5 = doc.createElement("color");
                field5.appendChild(doc.createTextNode(this.color));
                newEl.appendChild(field5);
                Element field6 = doc.createElement("photo");
                field6.appendChild(doc.createTextNode("photo" + Integer.toString(this.carId)));
                newEl.appendChild(field6);
                Element field7 = doc.createElement("price");
                field7.appendChild(doc.createTextNode(Double.toString(this.price)));
                newEl.appendChild(field7);
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
    
    public void removeCar(){
        try {
            File xmlFile = new File("DataBase.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("cars");
            for(int i = 0; i < nList.getLength(); i++){
                Node nNode = nList.item(i);
                if(nNode.getNodeType() == Node.ELEMENT_NODE){
                    Element el = (Element) nNode;
                    NodeList companyList = el.getElementsByTagName("car");
                    for(int j = 0; j < companyList.getLength(); j++){
                        Node node1 = companyList.item(j);
                        if(node1.getNodeType() == node1.ELEMENT_NODE){
                            Element el1 = (Element) node1;
                            if(el1.getElementsByTagName("carId").item(0).getTextContent().equals(Integer.toString(this.carId))){
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
    
    public void modifyCar(){
        removeCar();
        this.setCarId(1000);
        addCar();
    }
}
