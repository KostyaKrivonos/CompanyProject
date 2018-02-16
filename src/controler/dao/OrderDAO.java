/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;
import model.Order;
import model.OrderItem;
import model.Profile;

/**
 *
 * @author Алёшечка
 */
public class OrderDAO {
    private Connection con = null;
    private String sqlInsertInToOrders = "INSERT INTO orders (date, idCustomer, idManeger) VALUES (?, ?, ?)";
    private String sqlSelectOrder = "SELECT orders.*, customers.name FROM orders, customers \n "
            + "WHERE customers.id = orders.idCustomer AND orders.idManeger = ?";
    
    private String sqlInsertInToOrdersDetails = "INSERT INTO ordersdetails (idOrder, idProduct, quantity, paid) "
            + "VALUES (?, ?, ?, ?)";
    private String sqlSelectNameCustomers = "SELECT cs.id, cs.name, cs.adress, cs.phone \n" +
                            "FROM customers cs";
    
    public OrderDAO(Connection con) {
        this.con = con;
    }
    
    public boolean createOrder(Order order){
        try {
            PreparedStatement state1 = con.prepareStatement(sqlInsertInToOrders, PreparedStatement.RETURN_GENERATED_KEYS);
            Date date = new Date(order.getDate().getTime());
            state1.setDate (1, date);
            state1.setInt (2, order.getClient().getId());
            state1.setInt (3, order.getManager().getId());
            int count = state1.executeUpdate();
            if(count == 0){
                con.rollback();
                return false;
            }
            
            ResultSet res = state1.getGeneratedKeys();
            int id = 0;
            if(res.next()){
                id = res.getInt(1);
            }
            
            PreparedStatement state3 = con.prepareStatement(sqlInsertInToOrdersDetails);
            for (OrderItem orderItem : order.getOrderItems()) {
                state3.setInt(1, id);
                state3.setInt(2, orderItem.getProduct().getId());
                state3.setDouble(3, orderItem.getQuantity());
                state3.setBoolean(4, orderItem.isPaid());
                state3.addBatch();
            }
            //Another way to write data to the database
            
//            for (OrderItem orderItem : order.getOrderItems()) {
//                state3.setInt(1, id);
//                state3.setInt(2, orderItem.getProduct().getId());
//                state3.setDouble(3, orderItem.getQuantity());
//                state3.setBoolean(4, orderItem.isPaid());
//                int count1 = state3.executeUpdate();
//                if(count1 == 0){
//                    System.out.println("не добавилось");
//                    con.rollback();
//                    return false;
//                }                  
//            }
            
            int[] count1 = state3.executeBatch();
            System.out.println(count1.length);
            if (count1.length == 0) {
                con.rollback();
                return false;
            }
            con.commit();
        } catch (SQLException ex) {
            try {
                con.rollback();
                return false;
            } catch (SQLException ex1) {
                Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return true;
    }
    
    public ArrayList <Order> getOrdersByIdEmployee(Profile profile){
        ArrayList <Order> orders = new ArrayList<>();
        
        try {                         
            PreparedStatement state1 = con.prepareStatement(sqlSelectOrder);
            state1.setInt(1, profile.getId());
            
            ResultSet res1 = state1.executeQuery();
            while (res1.next()) {
                int id = res1.getInt(1);
                Date date = res1.getDate(2);
                int idCustomer = res1.getInt(3);
                int idManeger = res1.getInt(4);
                String nameCustomer = res1.getString(5);
                Order order = new Order(id, new Customer(idCustomer, nameCustomer), profile, date);
                orders.add(order);
            }
        } catch (SQLException ex) {
            System.out.println("sqlException");
        }
        return orders;
    }
}
