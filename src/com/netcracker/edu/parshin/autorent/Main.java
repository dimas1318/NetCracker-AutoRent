package com.netcracker.edu.parshin.autorent;

import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import org.w3c.dom.NodeList;

/**
 *
 * @author Dmitry Parshin, 2016
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void wrongInput(){
        System.out.println("Wrong input, correct format: \n" + 
                        "| (List <Class Name>)\n" +
                        "| (Add <Class Name>)\n" + 
                        "| (Remove <Class Name> <Object Id>)\n" +
                        "| (Modify <Class Name> <Object Id>)");
    }
    
    public static void main(String[] args) throws ParseException {        
        List<Object> list = XMLManager.read("DataBase.xml");
        Scanner in = new Scanner(System.in);
        String str;
        String id;
        String className;
        int wordCount;
        int i;
        while(true){
            wordCount = 1;
            str = in.nextLine();
            String firstWord = str.contains(" ") ? str.substring(0, str.indexOf(" ")).toLowerCase() : str.toLowerCase();
            switch(firstWord){
                case "add":
                    for(i = 0; i < str.length(); i++){
                        if(str.charAt(i) == ' '){
                            wordCount++;
                        }
                    }
                    if(wordCount != 2){
                        break;
                    }
                    
                    className = str.split("\\s+")[1];
                    XMLManager.add(list, className);
                    break;
                    
                case "list":
                    for(i = 0; i < str.length(); i++){
                        if(str.charAt(i) == ' '){
                            wordCount++;
                        }
                    }
                    if(wordCount != 2){
                        break;
                    }
                    
                    className = str.split("\\s+")[1];
                    XMLManager.list(list, className);
                    break;
                    
                case "remove":
                    for(i = 0; i < str.length(); i++){
                        if(str.charAt(i) == ' '){
                            wordCount++;
                        }
                    }
                    if(wordCount != 3){
                        break;
                    }
                    
                    id = str.substring(str.lastIndexOf(" ") + 1);
                    className = str.split("\\s+")[1];
                    XMLManager.remove(list, className, Long.parseLong(id));
                    break;
                    
                case "modify":
                    for(i = 0; i < str.length(); i++){
                        if(str.charAt(i) == ' '){
                            wordCount++;
                        }
                    }
                    if(wordCount != 3){
                        break;
                    }
                    
                    id = str.substring(str.lastIndexOf(" ") + 1);
                    className = str.split("\\s+")[1];
                    XMLManager.modify(list, className, Long.parseLong(id));
                    break;
                    
                case "exit":
                    return;
                    
                case "write":
                    for(i = 0; i < str.length(); i++){
                        if(str.charAt(i) == ' '){
                            wordCount++;
                        }
                    }
                    if(wordCount != 1){
                        break;
                    }
                    
                    String filePath = in.nextLine();
                    XMLManager.write(filePath, list);
                    break;
                    
                default:
                    wrongInput();
                    break;
            }
        }
    }  
}
