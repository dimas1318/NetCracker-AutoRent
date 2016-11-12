package com.netcracker.edu.parshin.autorent;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

/**
 *
 * @author Dmitry Parshin, 2016
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            File xmlFile = new File("DataBase.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" 
                    + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("car");
            for(int i = 0; i < nList.getLength(); i++){
                Node nNode = nList.item(i);
                System.out.println();
                if(nNode.getNodeType() == Node.ELEMENT_NODE){
                    Element el = (Element) nNode;
                    System.out.println("car_id: " 
                            + el.getElementsByTagName("carId").item(0).getTextContent());
                    System.out.println("company_id: " 
                            + el.getElementsByTagName("companyId").item(0).getTextContent());
                    System.out.println("brand: " 
                            + el.getElementsByTagName("brand").item(0).getTextContent());
                    System.out.println("name: " 
                            + el.getElementsByTagName("name").item(0).getTextContent());
                    System.out.println("color: " 
                            + el.getElementsByTagName("color").item(0).getTextContent());
                    System.out.println("photo: " 
                            + el.getElementsByTagName("photo").item(0).getTextContent());
                    System.out.println("price: " 
                            + el.getElementsByTagName("price").item(0).getTextContent()
                            + " $/hour");
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }      
    }
    
}
