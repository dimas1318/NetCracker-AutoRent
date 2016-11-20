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
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
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
    
    public static void list(List<Object> objects, String className){
        switch(className.toLowerCase()){
            case "car":
                for (Object obj : objects) {
                    if(obj instanceof Car){
                        System.out.println("car_id: " + ((Car) obj).getCarId());
                        System.out.println("company_id: " + ((Car) obj).getCompanyId());
                        System.out.println("brand: " + ((Car) obj).getBrand());
                        System.out.println("name: " + ((Car) obj).getName());
                        System.out.println("color: " + ((Car) obj).getColor());
                        System.out.println("photo: " + ((Car) obj).getPhoto());
                        System.out.println("price: " + ((Car) obj).getPrice() 
                                + " $/hour" + "\n");
                    }
                } 
                break;
                
            case "company":
                for (Object obj : objects){
                    if(obj instanceof Company){
                        System.out.println("company_id: " + ((Company) obj).getCompanyId());
                        System.out.println("name: " + ((Company) obj).getName());
                        System.out.println("site: " + ((Company) obj).getSite());
                        System.out.println("phone_number: " + ((Company) obj).getPhoneNumber() + "\n");
                    }
                }
                break;
                
            case "customer":
                for (Object obj : objects){
                    if(obj instanceof Customer){
                        System.out.println("customer_id: " + ((Customer) obj).getCustomerId());
                        System.out.println("login: " + ((Customer) obj).getLogin());
                        System.out.println("password: " + ((Customer) obj).getPassword());
                        System.out.println("first_name: " + ((Customer) obj).getFirstName());
                        System.out.println("last_name: " + ((Customer) obj).getLastName());
                        System.out.println("birth_date: " + ((Customer) obj).getBirthDate());
                        System.out.println("phone_number: " + ((Customer) obj).getPhoneNumber());
                        System.out.println("registration_date: " + ((Customer) obj).getRegistrationDate()
                                + "\n");
                    }
                }
                break;
                
            case "driver":
                for (Object obj : objects) {
                    if(obj instanceof Driver){
                        System.out.println("driver_id: " + ((Driver) obj).getDriverId());
                        System.out.println("company_id: " + ((Driver) obj).getCompanyId());
                        System.out.println("first_name: " + ((Driver) obj).getFirstName());
                        System.out.println("last_name: " + ((Driver) obj).getLastName());
                        System.out.println("driving_experience: " + ((Driver) obj).getDrivingExperience()
                                + "\n");
                    }
                }
                break;
                
            case "location":
                for (Object obj : objects){
                    if(obj instanceof Location){
                        System.out.println("location_id: " + ((Location) obj).getLocationId());
                        System.out.println("company_id: " + ((Location) obj).getCompanyId());
                        System.out.println("address: " + ((Location) obj).getAddress() + "\n");
                    }
                }
                break;
                
            case "session":
                for (Object obj : objects){
                    if(obj instanceof Session){
                        System.out.println("session_id: " + ((Session) obj).getSessionId());
                        System.out.println("car_id: " + ((Session) obj).getCarId());
                        System.out.println("start_date: " + ((Session) obj).getStartDate());
                        System.out.println("end_date: " + ((Session) obj).getEndDate());
                        System.out.println("driver_id: " + ((Session) obj).getDriverId());
                        System.out.println("customer_id: " + ((Session) obj).getCustomerId() + "\n");
                    }
                }
                break;
                
            default:
                System.out.println("Wrong input, correct format: \n" + 
                        "| (List <Class Name>, where ClassName =)\n" +
                        "| (Car, Company, Customer, Driver, Location or Session)");
                break;         
        }        
    }
    
    public static void add(List<Object> objects, String className) throws ParseException{
        Scanner in = new Scanner(System.in);
        String str;
        DateFormat df;
        switch(className.toLowerCase()){            
            case "car":
                Car car = new Car();
                System.out.println("car_id:");
                str = in.nextLine();
                while(str.equals("")) str = in.nextLine();
                car.setCarId(Integer.parseInt(str));
                System.out.println("company_id:");
                str = in.nextLine();
                while(str.equals("")) str = in.nextLine();
                car.setCompanyId(Integer.parseInt(str));
                System.out.println("brand:");
                str = in.nextLine();
                while(str.equals("")) str = in.nextLine();
                car.setBrand(str);
                System.out.println("name:");
                str = in.nextLine();
                while(str.equals("")) str = in.nextLine();
                car.setName(str);
                System.out.println("color:");
                str = in.nextLine();
                while(str.equals("")) str = in.nextLine();
                car.setColor(str);
                System.out.println("price:");
                str = in.nextLine();
                while(str.equals("")) str = in.nextLine();
                car.setPrice(Double.parseDouble(str));
                car.setPhoto("photo" + car.getCarId());
                
                objects.add(car);
                break;
                
            case "company":
                Company company = new Company();   
                System.out.println("company_id:");
                str = in.nextLine();
                while(str.equals("")) str = in.nextLine();
                company.setCompanyId(Integer.parseInt(str));
                System.out.println("name:");
                str = in.nextLine();
                while(str.equals("")) str = in.nextLine();
                company.setName(str);
                System.out.println("site:");
                str = in.nextLine();
                while(str.equals("")) str = in.nextLine();
                company.setSite(str);
                System.out.println("phone:");
                str = in.nextLine();
                while(str.equals("")) str = in.nextLine();
                company.setPhoneNumber(str);
                
                objects.add(company);
                break;
                
            case "customer":
                Customer customer = new Customer();
                
                System.out.println("customer_id:");
                str = in.nextLine();
                while(str.equals("")) str = in.nextLine();
                customer.setCustomerId(Integer.parseInt(str));                
                System.out.println("login:");
                str = in.nextLine();
                while(str.equals("")) str = in.nextLine();
                customer.setLogin(str);
                System.out.println("password:");
                str = in.nextLine();
                while(str.equals("")) str = in.nextLine();
                customer.setPassword(str);
                System.out.println("first_name:");
                str = in.nextLine();
                while(str.equals("")) str = in.nextLine();
                customer.setFirstName(str);
                System.out.println("last_name:");
                str = in.nextLine();
                while(str.equals("")) str = in.nextLine();
                customer.setLastName(str);
                System.out.println("birth_date (dd MM yyyy):");
                str = in.nextLine();
                while(str.equals("")) str = in.nextLine();
                DateFormat df1 = new SimpleDateFormat("dd MM yyyy", Locale.ENGLISH); 
                customer.setBirthDate(df1.parse(str));
                System.out.println("phone:");
                str = in.nextLine();
                while(str.equals("")) str = in.nextLine();
                customer.setPhoneNumber(str);
                System.out.println("registration_date (MM dd hh:mm:ss yyyy):");
                str = in.nextLine();
                while(str.equals("")) str = in.nextLine();
                df = new SimpleDateFormat("MM dd hh:mm:ss yyyy", Locale.ENGLISH);
                customer.setRegistrationDate(df.parse(str));
                
                objects.add(customer);
                break;
                
            case "driver":
                Driver driver = new Driver();
                
                System.out.println("driver_id:");
                str = in.nextLine();
                while(str.equals("")) str = in.nextLine();
                driver.setDriverId(Integer.parseInt(str));
                System.out.println("company_id:");
                str = in.nextLine();
                while(str.equals("")) str = in.nextLine();
                driver.setCompanyId(Integer.parseInt(str));
                System.out.println("first_name:");
                str = in.nextLine();
                while(str.equals("")) str = in.nextLine();
                driver.setFirstName(str);
                System.out.println("last_name:");
                str = in.nextLine();
                while(str.equals("")) str = in.nextLine();
                driver.setLastName(str);
                System.out.println("driving_experience:");
                str = in.nextLine();
                while(str.equals("")) str = in.nextLine();
                driver.setDrivingExperience(Double.parseDouble(str));
                
                objects.add(driver);
                break;
                
            case "location":
                Location location = new Location();
                
                System.out.println("location_id:");
                str = in.nextLine();
                while(str.equals("")) str = in.nextLine();
                location.setLocationId(Integer.parseInt(str));
                System.out.println("company_id:");
                str = in.nextLine();
                while(str.equals("")) str = in.nextLine();
                location.setCompanyId(Integer.parseInt(str));
                System.out.println("address:");
                str = in.nextLine();
                while(str.equals("")) str = in.nextLine();
                location.setAddress(str);
                
                objects.add(location);
                break;
                
            case "session":
                Session session = new Session();
                
                System.out.println("session_id:");
                str = in.nextLine();
                while(str.equals("")) str = in.nextLine();
                session.setSessionId(Long.parseLong(str));
                System.out.println("car_id:");
                str = in.nextLine();
                while(str.equals("")) str = in.nextLine();
                session.setCarId(Integer.parseInt(str));
                System.out.println("start_date:");
                str = in.nextLine();
                while(str.equals("")) str = in.nextLine();
                df = new SimpleDateFormat("MM dd hh:mm:ss yyyy", Locale.ENGLISH);
                session.setStartDate(df.parse(str));
                System.out.println("end_date:");
                str = in.nextLine();
                while(str.equals("")) str = in.nextLine();
                session.setEndDate(df.parse(str));
                System.out.println("driver_id:");
                str = in.nextLine();
                while(str.equals("")) str = in.nextLine();
                session.setDriverId(Integer.parseInt(str));
                
                objects.add(session);
                break;
                
            default:
                System.out.println("Wrong input, correct format: \n" + 
                        "| (Add <Class Name>, where ClassName =)\n" +
                        "| (Car, Company, Customer, Driver, Location or Session)");
                break;
        }
    }
    
    public static void remove(List<Object> objects, String className, Long id){
        switch(className.toLowerCase()){
            case "car":
                for (Iterator<Object> it = objects.iterator(); it.hasNext();) {
                    Object obj = it.next();
                    if(obj instanceof Car){
                        if(((Car) obj).getCarId() == id){
                            it.remove();
                            System.out.println("Object with " + id + " id was removed");
                        }
                    }
                }
                break;
                
            case "company":
                for (Iterator<Object> it = objects.iterator(); it.hasNext();) {
                    Object obj = it.next();
                    if(obj instanceof Company){
                        if(((Company) obj).getCompanyId() == id){
                            it.remove();
                            System.out.println("Object with " + id + " id was removed");
                        }
                    }
                }
                break;
                
            case "customer":
                for (Iterator<Object> it = objects.iterator(); it.hasNext();) {
                    Object obj = it.next();
                    if(obj instanceof Customer){
                        if(((Customer) obj).getCustomerId() == id){
                            it.remove();
                            System.out.println("Object with " + id + " id was removed");
                        }
                    }
                }
                break;
                
            case "driver":
                for (Iterator<Object> it = objects.iterator(); it.hasNext();) {
                    Object obj = it.next();
                    if(obj instanceof Driver){
                        if(((Driver) obj).getDriverId() == id){
                            it.remove();
                            System.out.println("Object with " + id + " id was removed");
                        }
                    }
                }
                break;
                
            case "location":
                for (Iterator<Object> it = objects.iterator(); it.hasNext();) {
                    Object obj = it.next();
                    if(obj instanceof Location){
                        if(((Location) obj).getLocationId() == id){
                            it.remove();
                            System.out.println("Object with " + id + " id was removed");
                        }
                    }
                }
                break;
                
            case "session":
                for (Iterator<Object> it = objects.iterator(); it.hasNext();) {
                    Object obj = it.next();
                    if(obj instanceof Session){
                        if(((Session) obj).getSessionId() == id){
                            it.remove();
                            System.out.println("Object with " + id + " id was removed");
                        }
                    }
                }
                break;

            default:
                System.out.println("Wrong input, correct format: \n" + 
                        "| (Remove <Class Name> <ObjectId>, where ClassName =)\n" +
                        "| (Car, Company, Customer, Driver, Location or Session)");
                break;
        }
    }
    
    public static void modify(List<Object> objects, String className, Long id) throws ParseException{
        Scanner in = new Scanner(System.in);
        String str;
        DateFormat df;
        switch(className.toLowerCase()){
            case "car":
                for (Object obj : objects) {
                    if(obj instanceof Car){
                        if(((Car) obj).getCarId() == id){
                            System.out.println("car_id:");
                            str = in.nextLine();
                            ((Car) obj).setCarId(str.equals("") ? ((Car) obj).getCarId() : Integer.parseInt(str));
                            System.out.println("company_id:");
                            str = in.nextLine();
                            ((Car) obj).setCompanyId(str.equals("") ? ((Car) obj).getCompanyId() : Integer.parseInt(str));
                            System.out.println("brand:");
                            str = in.nextLine();
                            ((Car) obj).setBrand(str.equals("") ? ((Car) obj).getBrand() : str);
                            System.out.println("name:");
                            str = in.nextLine();
                            ((Car) obj).setName(str.equals("") ? ((Car) obj).getName() : str);
                            System.out.println("color:");
                            str = in.nextLine();
                            ((Car) obj).setColor(str.equals("") ? ((Car) obj).getColor() : str);
                            System.out.println("price:");
                            str = in.nextLine();
                            ((Car) obj).setPrice(str.equals("") ? ((Car) obj).getPrice() : Double.parseDouble(str));
                            System.out.println("Object with " + id + " id was changed");
                        }
                    }
                }
                break;
                
            case "company":
                for (Object obj : objects){
                    if(obj instanceof Company){
                        if(((Company) obj).getCompanyId() == id){
                            System.out.println("company_id:");
                            str = in.nextLine();
                            ((Company) obj).setCompanyId(str.equals("") ? ((Company) obj).getCompanyId() : Integer.parseInt(str));
                            System.out.println("name:");
                            str = in.nextLine();
                            ((Company) obj).setName(str.equals("") ? ((Company) obj).getName() : str);
                            System.out.println("site:");
                            str = in.nextLine();
                            ((Company) obj).setSite(str.equals("") ? ((Company) obj).getSite() : str);
                            System.out.println("phone:");
                            str = in.nextLine();
                            ((Company) obj).setPhoneNumber(str.equals("") ? ((Company) obj).getPhoneNumber() : str);
                            System.out.println("Object with " + id + " id was changed");
                        }
                    }
                }
                break;
                
            case "customer":
                for (Object obj : objects){
                    if(obj instanceof Customer){
                        if(((Customer) obj).getCustomerId() == id){
                            System.out.println("customer_id:");
                            str = in.nextLine();
                            ((Customer) obj).setCustomerId(str.equals("") ? ((Customer) obj).getCustomerId() : Integer.parseInt(str));                
                            System.out.println("login:");
                            str = in.nextLine();
                            ((Customer) obj).setLogin(str.equals("") ? ((Customer) obj).getLogin() : str);
                            System.out.println("password:");
                            str = in.nextLine();
                            ((Customer) obj).setPassword(str.equals("") ? ((Customer) obj).getPassword() : str);
                            System.out.println("first_name:");
                            str = in.nextLine();
                            ((Customer) obj).setFirstName(str.equals("") ? ((Customer) obj).getFirstName() : str);
                            System.out.println("last_name:");
                            str = in.nextLine();
                            ((Customer) obj).setLastName(str.equals("") ? ((Customer) obj).getLastName() : str);
                            System.out.println("birth_date (dd MM yyyy):");
                            str = in.nextLine();
                            DateFormat df1 = new SimpleDateFormat("dd MM yyyy", Locale.ENGLISH); 
                            ((Customer) obj).setBirthDate(str.equals("") ? ((Customer) obj).getBirthDate() : df1.parse(str));
                            System.out.println("phone:");
                            str = in.nextLine();
                            ((Customer) obj).setPhoneNumber(str.equals("") ? ((Customer) obj).getPhoneNumber() : str);
                            System.out.println("registration_date (MM dd hh:mm:ss yyyy):");
                            str = in.nextLine();
                            df = new SimpleDateFormat("MM dd hh:mm:ss yyyy", Locale.ENGLISH);
                            ((Customer) obj).setRegistrationDate(str.equals("") ? ((Customer) obj).getRegistrationDate() : df.parse(str));
                            System.out.println("Object with " + id + " id was changed");
                        }
                    }
                }
                break;
                
            case "driver":
                for (Object obj : objects){
                    if(obj instanceof Driver){
                        if(((Driver) obj).getDriverId() == id){
                            System.out.println("driver_id:");
                            str = in.nextLine();
                            ((Driver) obj).setDriverId(str.equals("") ? ((Driver) obj).getDriverId() : Integer.parseInt(str));
                            System.out.println("company_id:");
                            str = in.nextLine();
                            ((Driver) obj).setCompanyId(str.equals("") ? ((Driver) obj).getCompanyId() : Integer.parseInt(str));
                            System.out.println("first_name:");
                            str = in.nextLine();
                            ((Driver) obj).setFirstName(str.equals("") ? ((Driver) obj).getFirstName() : str);
                            System.out.println("last_name:");
                            str = in.nextLine();
                            ((Driver) obj).setLastName(str.equals("") ? ((Driver) obj).getLastName() : str);
                            System.out.println("driving_experience:");
                            str = in.nextLine();
                            ((Driver) obj).setDrivingExperience(str.equals("") ? ((Driver) obj).getDrivingExperience() : Double.parseDouble(str));
                            System.out.println("Object with " + id + " id was changed");
                        }
                    }
                }
                break;
                
            case "location":
                for (Object obj : objects){
                    if(obj instanceof Location){
                        if(((Location) obj).getLocationId() == id){
                            System.out.println("location_id:");
                            str = in.nextLine();
                            ((Location) obj).setLocationId(str.equals("") ? ((Location) obj).getLocationId() : Integer.parseInt(str));
                            System.out.println("company_id:");
                            str = in.nextLine();
                            ((Location) obj).setCompanyId(str.equals("") ? ((Location) obj).getCompanyId() : Integer.parseInt(str));
                            System.out.println("address:");
                            str = in.nextLine();
                            ((Location) obj).setAddress(str.equals("") ? ((Location) obj).getAddress() : str);
                            System.out.println("Object with " + id + " id was changed");
                        }
                    }
                }
                break;
                
            case "session":
                for (Object obj : objects){
                    if(obj instanceof Session){
                        if(((Session) obj).getSessionId() == id){
                            System.out.println("session_id:");
                            str = in.nextLine();
                            ((Session) obj).setSessionId(str.equals("") ? ((Session) obj).getSessionId() : Long.parseLong(str));
                            System.out.println("car_id:");
                            str = in.nextLine();
                            ((Session) obj).setCarId(str.equals("") ? ((Session) obj).getCarId() : Integer.parseInt(str));
                            System.out.println("start_date:");
                            str = in.nextLine();
                            df = new SimpleDateFormat("MM dd hh:mm:ss yyyy", Locale.ENGLISH);
                            ((Session) obj).setStartDate(str.equals("") ? ((Session) obj).getStartDate() : df.parse(str));
                            System.out.println("end_date:");
                            str = in.nextLine();
                            ((Session) obj).setEndDate(str.equals("") ? ((Session) obj).getEndDate() : df.parse(str));
                            System.out.println("driver_id:");
                            str = in.nextLine();
                            ((Session) obj).setDriverId(str.equals("") ? ((Session) obj).getDriverId() : Integer.parseInt(str));
                            System.out.println("Object with " + id + " id was changed");
                        }
                    }
                }
                break;
                
            default:
                System.out.println("Wrong input, correct format: \n" + 
                        "| (Modify <Class Name> <ObjectId>, where ClassName =)\n" +
                        "| (Car, Company, Customer, Driver, Location or Session)");
                break;
        }
    }
}
