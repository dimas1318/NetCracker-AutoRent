/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.edu.parshin.autorent;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
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
 * @author 1
 */
public class XMLManager {
    public static List<Object> read(String filePath, String tagName1, String tagName2){     
        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nodes = doc.getElementsByTagName(tagName1.toLowerCase());
            
            Element root = (Element) nodes.item(0);
            
            List<Object> objects = new ArrayList<>();
            
            NodeList elementList = root.getElementsByTagName(tagName2.toLowerCase());
            DateFormat df;
            
            for(int i = 0; i < elementList.getLength(); i++){
                Node node = elementList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element el = (Element) node;
                    switch(tagName2.toLowerCase()){
                        case "car":
                            Car car = new Car();
                            car.setCarId(Integer.parseInt(el.getElementsByTagName("carId").item(0).getTextContent()));
                            car.setCompanyId(Integer.parseInt(el.getElementsByTagName("companyId").item(0).getTextContent()));
                            car.setBrand(el.getElementsByTagName("brand").item(0).getTextContent());
                            car.setName(el.getElementsByTagName("name").item(0).getTextContent());
                            car.setColor(el.getElementsByTagName("color").item(0).getTextContent());
                            car.setPhoto(el.getElementsByTagName("photo").item(0).getTextContent());
                            car.setPrice(Double.parseDouble(el.getElementsByTagName("price").item(0).getTextContent()));
                            objects.add(car);
                            break;
                        case "driver":
                            Driver driver = new Driver();
                            driver.setDriverId(Integer.parseInt(el.getElementsByTagName("driverId").item(0).getTextContent()));
                            driver.setCompanyId(Integer.parseInt(el.getElementsByTagName("companyId").item(0).getTextContent()));
                            driver.setFirstName(el.getElementsByTagName("firstName").item(0).getTextContent());
                            driver.setLastName(el.getElementsByTagName("lastName").item(0).getTextContent());
                            driver.setDrivingExperience(Double.parseDouble(el.getElementsByTagName("drivingExperience").item(0).getTextContent()));
                            objects.add(driver);
                            break;
                        case "company":
                            Company company = new Company();
                            company.setCompanyId(Integer.parseInt(el.getElementsByTagName("companyId").item(0).getTextContent()));
                            company.setName(el.getElementsByTagName("name").item(0).getTextContent());
                            company.setSite(el.getElementsByTagName("site").item(0).getTextContent());
                            company.setPhoneNumber(el.getElementsByTagName("phoneNumber").item(0).getTextContent());
                            objects.add(company);
                            break;
                        case "location":
                            Location location = new Location();
                            location.setLocationId(Integer.parseInt(el.getElementsByTagName("locationId").item(0).getTextContent()));
                            location.setCompanyId(Integer.parseInt(el.getElementsByTagName("companyId").item(0).getTextContent()));
                            location.setAddress(el.getElementsByTagName("address").item(0).getTextContent());
                            objects.add(location);
                            break;
                        case "session":
                            Session session = new Session();
                            session.setSessionId(Long.parseLong(el.getElementsByTagName("sessionId").item(0).getTextContent()));
                            session.setCarId(Integer.parseInt(el.getElementsByTagName("carId").item(0).getTextContent()));
                            df = new SimpleDateFormat("MM dd hh:mm:ss yyyy"); 
                            session.setStartDate(df.parse(el.getElementsByTagName("startDate").item(0).getTextContent()));
                            session.setEndDate(df.parse(el.getElementsByTagName("endDate").item(0).getTextContent()));
                            session.setDriverId(Integer.parseInt(el.getElementsByTagName("driverId").item(0).getTextContent()));
                            objects.add(session);
                            break;
                        case "customer":
                            Customer customer = new Customer();
                            customer.setCustomerId(Integer.parseInt(el.getElementsByTagName("customerId").item(0).getTextContent()));
                            customer.setLogin(el.getElementsByTagName("login").item(0).getTextContent());
                            customer.setPassword(el.getElementsByTagName("password").item(0).getTextContent());
                            customer.setFirstName(el.getElementsByTagName("firstName").item(0).getTextContent());
                            customer.setLastName(el.getElementsByTagName("lastName").item(0).getTextContent());
                            DateFormat df1 = new SimpleDateFormat("dd MM yyyy"); 
                            customer.setBirthDate(df1.parse(el.getElementsByTagName("birthDate").item(0).getTextContent()));
                            customer.setPhoneNumber(el.getElementsByTagName("phoneNumber").item(0).getTextContent());
                            df = new SimpleDateFormat("MM dd hh:mm:ss yyyy");
                            customer.setRegistrationDate(df.parse(el.getElementsByTagName("registrationDate").item(0).getTextContent()));
                            objects.add(customer);
                            break;
                        default:
                            break;
                    }
                   
                }
            }
            return objects;
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            Logger.getLogger(XMLManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(XMLManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void write(String filePath, NodeList elementList, String tagName1, String tagName2){
        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
                        
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", 2);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            
            StreamResult result = new StreamResult(xmlFile);
            
            /*for(int i = 0; i < elementList.getLength(); i++){
                Node node = elementList.item(i);
                doc.appendChild(node);
            }*/
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, result);
        } catch (Exception e){
            e.printStackTrace();
        } 
    }
}
