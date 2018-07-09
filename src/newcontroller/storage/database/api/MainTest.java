/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newcontroller.storage.database.api;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;
import newcontroller.storage.database.implementation.FactoryType;
import newcontroller.storage.database.implementation.RelCustomerDAO;

/**
 *
 * @author Алёшечка
 */
public class MainTest {
    public static void main(String[] args) {
        DAOFactory factory = DAOFactory.getDAOFactory(FactoryType.RELATION);
        Customer c = new Customer("fed9", "www", "7777");
        int i = 6;
        try {            
            DAO<Customer, Integer> dao = factory.getDAO(Customer.class, Integer.class);            
            dao.findById(i);
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        //main1(Customer.class);
        
        //main1(Customer.class);
    }
    
//    public static <T> void main1(Class<T> c) {
//        try {
//            T t = c.newInstance();
//            System.out.println(t);
//        } catch (InstantiationException ex) {
//            Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}


