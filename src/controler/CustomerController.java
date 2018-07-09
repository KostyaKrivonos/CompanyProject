/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;
import newcontroller.storage.database.api.*;
import newcontroller.storage.database.implementation.*;
import view.transferObjects.TransferObjectCustomer;

/**
 *
 * @author Алёшечка
 */
public class CustomerController {
    private DAOFactory factory;

    public CustomerController() throws SQLException {
        this.factory = DAOFactory.getDAOFactory(FactoryType.RELATION);
    }
    
    public Collection <Customer> getAllCustomers() {
        ArrayList <Customer> customers = new ArrayList<>();
        try {
            DAO<Customer,Integer> dao = factory.getDAO(Customer.class, Integer.class);
            return dao.findAll();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customers;
    }
    
    public Customer createCustomer(TransferObjectCustomer t){
        try {
            Customer customer = new Customer(t.getName(), t.getAdress(), t.getPhone());
            DAO<Customer,Integer> dao = factory.getDAO(Customer.class, Integer.class);
            return dao.create(customer);
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }       
    }
    
    public Customer deleteCustomer(Customer customer) {
        try {
            DAO<Customer, Integer> dao = factory.getDAO(Customer.class, Integer.class);
            return dao.delete(customer);
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }        
    }
    
    public ArrayList<Customer> getCustomersLimit(int startIndex, int countRow){              
        try {
            RelCustomerDAO dao = (RelCustomerDAO)factory.getDAO(Customer.class, Integer.class);
            return dao.getCustomersLimit(startIndex, countRow);
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }        
    }        
}
