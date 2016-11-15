package com.netcracker.edu.parshin.autorent;

import java.util.Scanner;

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
    
    public static void main(String[] args) {
        Company company = new Company();
        Car car = new Car();
        Customer customer = new Customer();
        Driver driver = new Driver();
        Location location = new Location();
        Session session = new Session();
        
        Scanner in = new Scanner(System.in);
        
        String command = args[0].toLowerCase();
        switch (command){
	        case "list":
	        	if(args.length == 2){
                            switch(args[1].toLowerCase()){
                                case "car":
                                    car.listCar();
                                    break;
                                case "company":
                                    company.listCompany();
                                    break;
                                case "customer":
                                    customer.listCustomer();
                                    break;
                                case "driver":
                                    driver.listDriver();
                                    break;
                                case "location":
                                    location.listLocation();
                                    break;
                                case "session":
                                    session.listSession();
                                    break;
                            }
	        	} else {
	        		wrongInput();
	        	}
	        	break;	        	
	        case "add":
                    if(args.length == 2){
                            switch(args[1].toLowerCase()){
                                case "car":
                                    System.out.println("car_id:");
                                    car.setCarId(in.nextInt());
                                    System.out.println("company_id:");
                                    car.setCompanyId(in.nextInt());
                                    in.nextLine();
                                    System.out.println("brand:");
                                    car.setBrand(in.nextLine());
                                    System.out.println("name:");
                                    car.setName(in.nextLine());
                                    System.out.println("color:");
                                    car.setColor(in.nextLine());
                                    System.out.println("price:");
                                    car.setPrice(in.nextDouble());
                                    car.addCar();
                                    break;
                                case "company":
                                    System.out.println("company_id:");
                                    company.setCompanyId(in.nextInt());
                                    in.nextLine();
                                    System.out.println("name:");
                                    company.setName(in.nextLine());
                                    System.out.println("site:");
                                    company.setSite(in.nextLine());
                                    System.out.println("phone:");
                                    company.setPhoneNumber(in.nextLine());
                                    company.addCompany();
                                    break;
                                case "customer":
                                    System.out.println("customer_id:");
                                    customer.setCustomerId(in.nextInt());
                                    System.out.println("login:");
                                    customer.setLogin(in.nextLine());
                                    System.out.println("password:");
                                    customer.setPassword(in.nextLine());
                                    System.out.println("first_name:");
                                    customer.setFirstName(in.nextLine());
                                    System.out.println("last_name:");
                                    customer.setLastName(in.nextLine());
                                    //System.out.println("birth_date:");
                                    System.out.println("phone_number:");
                                    customer.setPhoneNumber(in.nextLine());
                                    customer.addCustomer();
                                    break;
                                case "driver":
                                    System.out.println("driver_id:");
                                    driver.setDriverId(in.nextInt());
                                    System.out.println("company_id:");
                                    driver.setCompanyId(in.nextInt());
                                    in.nextLine();
                                    System.out.println("first_name:");
                                    driver.setFirstName(in.nextLine());
                                    System.out.println("last_name:");
                                    driver.setLastName(in.nextLine());
                                    System.out.println("driving_experience:");
                                    driver.setDrivingExperience(in.nextDouble());
                                    driver.addDriver();
                                    break;
                                case "location":
                                    System.out.println("location_id:");
                                    location.setLocationId(in.nextInt());
                                    System.out.println("comapany_id:");
                                    location.setCompanyId(in.nextInt());
                                    in.nextLine();
                                    System.out.println("address:");
                                    location.setAddress(in.nextLine());
                                    location.addLocation();
                                    break;
                                case "session":
                                    System.out.println("session_id:");
                                    session.setSessionId(in.nextLong());
                                    System.out.println("car_id:");
                                    session.setCarId(in.nextInt());
                                    System.out.println("driver_id:");
                                    session.setDriverId(in.nextInt());
                                    System.out.println("customer_id:");
                                    session.setCustomerId(in.nextInt());
                                    session.addSession();
                                    break;
                            }
	        	} else {
	        		wrongInput();
	        	}
	        	break;	
                case "remove":
                    if(args.length == 3){
                            switch(args[1].toLowerCase()){
                                case "car":
                                    car.setCarId(Integer.parseInt(args[2]));
                                    car.removeCar();
                                    break;
                                case "company":
                                    company.setCompanyId(Integer.parseInt(args[2]));
                                    company.removeCompany();
                                    break;
                                case "customer":
                                    customer.setCustomerId(Integer.parseInt(args[2]));
                                    customer.removeCustomer();
                                    break;
                                case "driver":
                                    driver.setDriverId(Integer.parseInt(args[2]));
                                    driver.removeDriver();
                                    break;
                                case "location":
                                    location.setLocationId(Integer.parseInt(args[2]));
                                    location.removeLocation();
                                    break;
                                case "session":
                                    session.setSessionId(Integer.parseInt(args[2]));
                                    session.removeSession();
                                    break;
                            }
	        	} else {
	        		wrongInput();
	        	}
	        	break;	
                case "modify":
                    if(args.length == 3){
                            switch(args[1].toLowerCase()){
                                case "car":
                                    car.setCarId(Integer.parseInt(args[2]));
                                    car.modifyCar();
                                    break;
                                case "company":
                                    company.setCompanyId(Integer.parseInt(args[2]));
                                    company.removeCompany();
                                    break;
                                case "customer":
                                    customer.setCustomerId(Integer.parseInt(args[2]));
                                    customer.removeCustomer();
                                    break;
                                case "driver":
                                    driver.setDriverId(Integer.parseInt(args[2]));
                                    driver.removeDriver();
                                    break;
                                case "location":
                                    location.setLocationId(Integer.parseInt(args[2]));
                                    location.removeLocation();
                                    break;
                                case "session":
                                    session.setSessionId(Integer.parseInt(args[2]));
                                    session.removeSession();
                                    break;
                            }
	        	} else {
	        		wrongInput();
	        	}
	        	break;	
                default:
                    wrongInput();
                    break;
        }
    }  
}
