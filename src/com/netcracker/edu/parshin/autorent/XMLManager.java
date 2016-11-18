/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.edu.parshin.autorent;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
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
    public static List<Object> read(String filePath){     
        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
                        
            List<Object> objects = new ArrayList<>();
            DateFormat df;
                        
            NodeList carList = ((Element) doc.getElementsByTagName("cars").item(0)).getElementsByTagName("car");
            for(int i = 0; i < carList.getLength(); i++){
                Node node = carList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element el = (Element) node;
                    Car car = new Car();
                    car.setCarId(Integer.parseInt(el.getElementsByTagName("carId").item(0).getTextContent()));
                    car.setCompanyId(Integer.parseInt(el.getElementsByTagName("companyId").item(0).getTextContent()));
                    car.setBrand(el.getElementsByTagName("brand").item(0).getTextContent());
                    car.setName(el.getElementsByTagName("name").item(0).getTextContent());
                    car.setColor(el.getElementsByTagName("color").item(0).getTextContent());
                    car.setPhoto(el.getElementsByTagName("photo").item(0).getTextContent());
                    car.setPrice(Double.parseDouble(el.getElementsByTagName("price").item(0).getTextContent()));
                    objects.add(car);
                }
            }
            
            NodeList driverList = ((Element) doc.getElementsByTagName("drivers").item(0)).getElementsByTagName("driver");
            for(int i = 0; i < driverList.getLength(); i++){
                Node node = driverList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element el = (Element) node;
                    Driver driver = new Driver();
                    driver.setDriverId(Integer.parseInt(el.getElementsByTagName("driverId").item(0).getTextContent()));
                    driver.setCompanyId(Integer.parseInt(el.getElementsByTagName("companyId").item(0).getTextContent()));
                    driver.setFirstName(el.getElementsByTagName("firstName").item(0).getTextContent());
                    driver.setLastName(el.getElementsByTagName("lastName").item(0).getTextContent());
                    driver.setDrivingExperience(Double.parseDouble(el.getElementsByTagName("drivingExperience").item(0).getTextContent()));
                    objects.add(driver);
                }
            }
            
            NodeList companyList = ((Element) doc.getElementsByTagName("companies").item(0)).getElementsByTagName("company");
            for(int i = 0; i < companyList.getLength(); i++){
                Node node = companyList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element el = (Element) node;
                    Company company = new Company();
                    company.setCompanyId(Integer.parseInt(el.getElementsByTagName("companyId").item(0).getTextContent()));
                    company.setName(el.getElementsByTagName("name").item(0).getTextContent());
                    company.setSite(el.getElementsByTagName("site").item(0).getTextContent());
                    company.setPhoneNumber(el.getElementsByTagName("phoneNumber").item(0).getTextContent());
                    objects.add(company);
                }
            }
            
            NodeList locationList = ((Element) doc.getElementsByTagName("locations").item(0)).getElementsByTagName("location");
            for(int i = 0; i < locationList.getLength(); i++){
                Node node = locationList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element el = (Element) node;
                    Location location = new Location();
                    location.setLocationId(Integer.parseInt(el.getElementsByTagName("locationId").item(0).getTextContent()));
                    location.setCompanyId(Integer.parseInt(el.getElementsByTagName("companyId").item(0).getTextContent()));
                    location.setAddress(el.getElementsByTagName("address").item(0).getTextContent());
                    objects.add(location);
                }
            }
            
            NodeList sessionList = ((Element) doc.getElementsByTagName("sessions").item(0)).getElementsByTagName("session");
            for(int i = 0; i < sessionList.getLength(); i++){
                Node node = sessionList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element el = (Element) node;
                    Session session = new Session();
                    session.setSessionId(Long.parseLong(el.getElementsByTagName("sessionId").item(0).getTextContent()));
                    session.setCarId(Integer.parseInt(el.getElementsByTagName("carId").item(0).getTextContent()));
                    df = new SimpleDateFormat("MM dd hh:mm:ss yyyy", Locale.ENGLISH); 
                    session.setStartDate(df.parse(el.getElementsByTagName("startDate").item(0).getTextContent()));
                    session.setEndDate(df.parse(el.getElementsByTagName("endDate").item(0).getTextContent()));
                    String driverId = el.getElementsByTagName("driverId").item(0).getTextContent();
                    session.setDriverId(driverId.equals("NULL") ? 0 : Integer.parseInt(driverId));
                    objects.add(session);
                }
            }
            
            NodeList customerList = ((Element) doc.getElementsByTagName("customers").item(0)).getElementsByTagName("customer");
            for(int i = 0; i < customerList.getLength(); i++){
                Node node = customerList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element el = (Element) node;
                    Customer customer = new Customer();
                    customer.setCustomerId(Integer.parseInt(el.getElementsByTagName("customerId").item(0).getTextContent()));
                    customer.setLogin(el.getElementsByTagName("login").item(0).getTextContent());
                    customer.setPassword(el.getElementsByTagName("password").item(0).getTextContent());
                    customer.setFirstName(el.getElementsByTagName("firstName").item(0).getTextContent());
                    customer.setLastName(el.getElementsByTagName("lastName").item(0).getTextContent());
                    DateFormat df1 = new SimpleDateFormat("dd MM yyyy", Locale.ENGLISH); 
                    customer.setBirthDate(df1.parse(el.getElementsByTagName("birthDate").item(0).getTextContent()));
                    customer.setPhoneNumber(el.getElementsByTagName("phoneNumber").item(0).getTextContent());
                    df = new SimpleDateFormat("MM dd hh:mm:ss yyyy", Locale.ENGLISH);
                    customer.setRegistrationDate(df.parse(el.getElementsByTagName("registrationDate").item(0).getTextContent()));
                    objects.add(customer);
                }
            }
            
            return objects;
        } catch (SAXException | IOException | ParserConfigurationException | ParseException ex) {
            Logger.getLogger(XMLManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void write(String filePath, List<Object> objects){
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
                       
            DateFormat df;            
            
            Element root = doc.createElement("class");
            doc.appendChild(root);
            
            Element cars = doc.createElement("cars"); 
            root.appendChild(cars);            
            for (Object obj : objects) {
                if(obj instanceof Car){
                    Element newEl = doc.createElement("car");
                    cars.appendChild(newEl);

                    Element field1 = doc.createElement("carId");
                    field1.appendChild(doc.createTextNode(Integer.toString(((Car) obj).getCarId())));
                    newEl.appendChild(field1);
                    Element field2 = doc.createElement("companyId");
                    field2.appendChild(doc.createTextNode(Integer.toString(((Car) obj).getCompanyId())));
                    newEl.appendChild(field2);
                    Element field3 = doc.createElement("brand");
                    field3.appendChild(doc.createTextNode(((Car) obj).getBrand()));
                    newEl.appendChild(field3);
                    Element field4 = doc.createElement("name");
                    field4.appendChild(doc.createTextNode(((Car) obj).getName()));
                    newEl.appendChild(field4);
                    Element field5 = doc.createElement("color");
                    field5.appendChild(doc.createTextNode(((Car) obj).getColor()));
                    newEl.appendChild(field5);
                    Element field6 = doc.createElement("photo");
                    field6.appendChild(doc.createTextNode("photo" + Integer.toString(((Car) obj).getCarId())));
                    newEl.appendChild(field6);
                    Element field7 = doc.createElement("price");
                    field7.appendChild(doc.createTextNode(Double.toString(((Car) obj).getPrice())));
                    newEl.appendChild(field7);
                }
            }
            
            Element drivers = doc.createElement("drivers"); 
            root.appendChild(drivers);
            for (Object obj : objects) {
                if(obj instanceof Driver){
                    Element newEl = doc.createElement("driver");
                    drivers.appendChild(newEl);

                    Element field1 = doc.createElement("driverId");
                    field1.appendChild(doc.createTextNode(Integer.toString(((Driver) obj).getDriverId())));
                    newEl.appendChild(field1);
                    Element field2 = doc.createElement("companyId");
                    field2.appendChild(doc.createTextNode(Integer.toString(((Driver) obj).getCompanyId())));
                    newEl.appendChild(field2);
                    Element field3 = doc.createElement("firstName");
                    field3.appendChild(doc.createTextNode(((Driver) obj).getFirstName()));
                    newEl.appendChild(field3);
                    Element field4 = doc.createElement("lastName");
                    field4.appendChild(doc.createTextNode(((Driver) obj).getLastName()));
                    newEl.appendChild(field4);
                    Element field5 = doc.createElement("drivingExperience");
                    field5.appendChild(doc.createTextNode(String.valueOf(((Driver) obj).getDrivingExperience())));
                    newEl.appendChild(field5);
                }
            }
            
            Element companies = doc.createElement("companies"); 
            root.appendChild(companies);
            for (Object obj : objects) {
                if(obj instanceof Company){
                    Element newEl = doc.createElement("company");
                    companies.appendChild(newEl);

                    Element field1 = doc.createElement("companyId");
                    field1.appendChild(doc.createTextNode(Integer.toString(((Company) obj).getCompanyId())));
                    newEl.appendChild(field1);
                    Element field2 = doc.createElement("name");
                    field2.appendChild(doc.createTextNode(((Company) obj).getName()));
                    newEl.appendChild(field2);
                    Element field3 = doc.createElement("site");
                    field3.appendChild(doc.createTextNode(((Company) obj).getSite()));
                    newEl.appendChild(field3);
                    Element field4 = doc.createElement("phoneNumber");
                    field4.appendChild(doc.createTextNode(((Company) obj).getPhoneNumber()));
                    newEl.appendChild(field4);
                }
            }
            
            Element locations = doc.createElement("locations"); 
            root.appendChild(locations);
            for (Object obj : objects) {
                if(obj instanceof Location){
                    Element newEl = doc.createElement("location");
                    locations.appendChild(newEl);

                    Element field1 = doc.createElement("locationId");
                    field1.appendChild(doc.createTextNode(Integer.toString(((Location) obj).getLocationId())));
                    newEl.appendChild(field1);
                    Element field2 = doc.createElement("companyId");
                    field2.appendChild(doc.createTextNode(Integer.toString(((Location) obj).getCompanyId())));
                    newEl.appendChild(field2);
                    Element field3 = doc.createElement("address");
                    field3.appendChild(doc.createTextNode(((Location) obj).getAddress()));
                    newEl.appendChild(field3);
                }
            }
            
            Element sessions = doc.createElement("sessions"); 
            root.appendChild(sessions);
            for (Object obj : objects) {
                if(obj instanceof Session){
                    Element newEl = doc.createElement("session");
                    sessions.appendChild(newEl);

                    Element field1 = doc.createElement("sessionId");
                    field1.appendChild(doc.createTextNode(Long.toString(((Session) obj).getSessionId())));
                    newEl.appendChild(field1);
                    Element field2 = doc.createElement("carId");
                    field2.appendChild(doc.createTextNode(Integer.toString(((Session) obj).getCarId())));
                    newEl.appendChild(field2);
                    df = new SimpleDateFormat("MM dd hh:mm:ss yyyy", Locale.ENGLISH);
                    Element field3 = doc.createElement("startDate");
                    field3.appendChild(doc.createTextNode(df.format(((Session) obj).getStartDate())));
                    newEl.appendChild(field3);
                    Element field4 = doc.createElement("endDate");
                    field4.appendChild(doc.createTextNode(df.format(((Session) obj).getEndDate())));
                    newEl.appendChild(field4);
                    Element field5 = doc.createElement("driverId");
                    field5.appendChild(doc.createTextNode(Integer.toString(((Session) obj).getDriverId())));
                    newEl.appendChild(field5);
                    Element field6 = doc.createElement("customerId");
                    field6.appendChild(doc.createTextNode(Integer.toString(((Session) obj).getCustomerId())));
                    newEl.appendChild(field6);
                }
            }
              
            Element customers = doc.createElement("customers"); 
            root.appendChild(customers);
            for (Object obj : objects) {
                if(obj instanceof Customer){
                    Element newEl = doc.createElement("customer");
                    customers.appendChild(newEl);

                    Element field1 = doc.createElement("customerId");
                    field1.appendChild(doc.createTextNode(Integer.toString(((Customer) obj).getCustomerId())));
                    newEl.appendChild(field1);
                    Element field2 = doc.createElement("login");
                    field2.appendChild(doc.createTextNode(((Customer) obj).getLogin()));
                    newEl.appendChild(field2);
                    Element field3 = doc.createElement("password");
                    field3.appendChild(doc.createTextNode(((Customer) obj).getPassword()));
                    newEl.appendChild(field3);
                    Element field4 = doc.createElement("firstName");
                    field4.appendChild(doc.createTextNode(((Customer) obj).getFirstName()));
                    newEl.appendChild(field4);
                    Element field5 = doc.createElement("lastName");
                    field5.appendChild(doc.createTextNode(((Customer) obj).getLastName()));
                    newEl.appendChild(field5);
                    Element field6 = doc.createElement("birthDate");
                    DateFormat df1 = new SimpleDateFormat("dd MM yyyy", Locale.ENGLISH);
                    field6.appendChild(doc.createTextNode(df1.format(((Customer) obj).getBirthDate())));
                    newEl.appendChild(field6);
                    Element field7 = doc.createElement("phoneNumber");
                    field7.appendChild(doc.createTextNode(((Customer) obj).getPhoneNumber()));
                    newEl.appendChild(field7);
                    Element field8 = doc.createElement("registrationDate");
                    df = new SimpleDateFormat("MM dd hh:mm:ss yyyy", Locale.ENGLISH);
                    field8.appendChild(doc.createTextNode(df.format(((Customer) obj).getRegistrationDate())));
                    newEl.appendChild(field8);
                }
            }
                             
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", 2);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            
            StreamResult result = new StreamResult(new File(filePath));
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, result);
        } catch (TransformerException | ParserConfigurationException ex) {
            Logger.getLogger(XMLManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
