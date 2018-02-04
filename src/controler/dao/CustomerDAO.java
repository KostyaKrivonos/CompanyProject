/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;

/**
 *
 * @author Алёшечка
 */
public class CustomerDAO {
    private Connection con = null;
    private String sqlSelectNameCustomers = "SELECT cs.id, cs.name, cs.adress, cs.phone \n" +
                            "FROM customers cs";
    private String sqlLimitCustomers = "SELECT * FROM customers LIMIT ?, ?";
    private String sqlInsertInToCustomers = "INSERT INTO customers (name, adress, phone) VALUES (?, ?, ?)";
    private String sqlDeleteCustomer = "DELETE FROM customers WHERE id = ? ";
    
    public CustomerDAO(Connection con) {
        this.con = con;
    }
    
    public ArrayList <Customer> getAllCustomers() throws SQLException{
        ArrayList <Customer> customers = new ArrayList<>();
        
        Statement state = con.createStatement();
        ResultSet res = state.executeQuery(sqlSelectNameCustomers);
        
        while(res.next()){
            int id = res.getInt(1);
            String name = res.getString(2);
            String adress = res.getString(3);
            String phone = res.getString(4);
            Customer customer = new Customer(id, name, adress, phone);
            customers.add(customer);
        }
        return customers;
    }
    
    public boolean createCustomer(Customer customer){
        try {
            PreparedStatement state1 = con.prepareStatement(sqlInsertInToCustomers);
            state1.setString(1, customer.getName());
            state1.setString(2, customer.getAdress());
            state1.setString(3, customer.getPhone());
            int count = state1.executeUpdate();
            if(count == 0){
                con.rollback();
                return false;
            }
            con.commit();
        } catch (SQLException ex) {
            try {
                con.rollback();
                return false;
            } catch (SQLException ex1) {
                Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return true;
    }
    
    public boolean deleteCustomer(Customer customer){
        try {
            PreparedStatement state1 = con.prepareStatement(sqlDeleteCustomer);
            state1.setInt(1, customer.getId());
            int count = state1.executeUpdate();
            
            if(count == 0){
                con.rollback();
                return false;
            }
                  
            con.commit();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
                return false;
        }
    }
    
    public ArrayList<Customer> getCustomersLimit(int startIndex, int countRow){
        ArrayList<Customer> customers = new ArrayList<>();
        
        try {
            PreparedStatement state1 = con.prepareStatement(sqlLimitCustomers);
            state1.setInt(1, (startIndex - 1) * countRow);
            state1.setInt(2, countRow);
                       
            ResultSet res = state1.executeQuery();

            while (res.next()) {
                int id = res.getInt(1);
                String name = res.getString(2);
                String adress = res.getString(3);
                String phone = res.getString(4);
                Customer customer = new Customer(id, name, adress, phone);
                customers.add(customer);
            }            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return customers;
    }
}
