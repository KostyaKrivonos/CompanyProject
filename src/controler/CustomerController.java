/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import controler.dao.CustomerDAO;
import controler.dao.DAOFactory;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Customer;
import view.transferObjects.TransferObjectCustomer;

/**
 *
 * @author Алёшечка
 */
public class CustomerController {
    private CustomerDAO customerDAO;

    public CustomerController() throws SQLException {
        this.customerDAO = DAOFactory.getDAOFactory().getCustomerDAO();
    }

    
    public ArrayList <Customer> getAllCustomers() throws SQLException{
        ArrayList <Customer> customers = customerDAO.getAllCustomers();
        return customers;
    }
    
    public boolean createCustomer(TransferObjectCustomer t){
        Customer customer = new Customer(t.getName(), t.getAdress(), t.getPhone());
        return customerDAO.createCustomer(customer);
    }
    
    public boolean deleteCustomer(Customer customer){
        return customerDAO.deleteCustomer(customer);
    }
    
     public ArrayList<Customer> getCustomersLimit(int startIndex, int countRow){
         return customerDAO.getCustomersLimit(startIndex, countRow);
     }
}
