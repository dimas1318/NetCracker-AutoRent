package com.netcracker.edu.parshin.autorent;

/**
 *
 * @author Dmitry Parshin, 2016
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*Company company = new Company();
        company.listCompany();*/
        
        Car car = new Car();
        
        car.setCarId(999);
        car.setCompanyId(2);
        car.setBrand("priora");
        car.setName("2005");
        car.setColor("pink");
        car.setPrice(999);
        car.addCar();
        
        car.listCar();
        
        car.modifyCar();
        
        car.listCar();
        
        /*Customer customer = new Customer();
        customer.listCustomer();*/
        
        /*Driver driver = new Driver();
        driver.listDriver();*/
        
        /*Location location = new Location();
        location.listLocation();*/
        
        /*Session session = new Session();
        session.listSession();*/
    }
    
}
