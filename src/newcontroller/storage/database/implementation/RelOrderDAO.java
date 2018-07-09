/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newcontroller.storage.database.implementation;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import model.Customer;
import model.Order;
import model.OrderItem;
import model.Profile;
import newcontroller.storage.database.api.RelDAO;

/**
 *
 * @author Алёшечка
 */
public class RelOrderDAO extends RelDAO<Order, Integer>{
    private String sqlInsertInToOrders = "INSERT INTO orders (date, idCustomer, idManeger) VALUES (?, ?, ?)";
    private String sqlInsertInToOrdersDetails = "INSERT INTO ordersdetails (idOrder, idProduct, quantity, paid) "
            + "VALUES (?, ?, ?, ?)";
    private String sqlFindByIdOrder = "SELECT orders.*, customers.name, staff.name, staff.lastName FROM orders, customers, staff \n "
            + "WHERE customers.id = orders.idCustomer AND staff.id = orders.idManeger AND orders.id = ?";
    private String sqlFindAllOrder = "SELECT orders.*, customers.name, staff.name, staff.lastName FROM orders, customers, staff \n "
            + "WHERE customers.id = orders.idCustomer AND staff.id = orders.idManeger";
    private String sqlSelectOrderByIdManager = "SELECT orders.*, customers.name FROM orders, customers \n "
            + "WHERE customers.id = orders.idCustomer AND orders.idManeger = ?";
    
    public RelOrderDAO() {
        super();
    }

    @Override
    public Order create(Order order) {
        try (PreparedStatement state1 = connection.prepareStatement(sqlInsertInToOrders, PreparedStatement.RETURN_GENERATED_KEYS);
                ResultSet res = state1.getGeneratedKeys(); PreparedStatement state3 = connection.prepareStatement(sqlInsertInToOrdersDetails);) {
            
            Date date = new Date(order.getDate().getTime());
            state1.setDate(1, date);
            state1.setInt(2, order.getClient().getId());
            state1.setInt(3, order.getManager().getId());
            int count = state1.executeUpdate();
            if (count == 0) {
                connection.rollback();
                return null;
            }
           
            int id = 0;
            if (res.next()) {
                id = res.getInt(1);
            }

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
                connection.rollback();
                return null;
            }
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
                return null;
            } catch (SQLException ex1) {
            }
        }
        return order;
    }

    @Override
    public Order findById(Integer key) {
        Order order = null;
        try (PreparedStatement state1 = connection.prepareStatement(sqlFindByIdOrder);
                ResultSet res1 = state1.executeQuery()) {
            state1.setInt(1, key);

            while (res1.next()) {
                int id = res1.getInt(1);
                Date date = res1.getDate(2);
                int idCustomer = res1.getInt(3);
                int idManeger = res1.getInt(4);
                String nameCustomer = res1.getString(5);
                String nameManger = res1.getString(6);
                String lastNameManeger = res1.getString(7);
                order = new Order(id, new Customer(idCustomer, nameCustomer),
                        new Profile(idManeger, nameManger, lastNameManeger), date);
            }
        } catch (SQLException ex) {
            System.out.println("sqlException");
        }
        return order;
    }
    
    @Override
    public Collection<Order> findAll() {
        ArrayList<Order> orders = new ArrayList<>();

        try (Statement state = connection.createStatement();
                ResultSet res1 = state.executeQuery(sqlFindAllOrder)) {

            while (res1.next()) {
                int id = res1.getInt(1);
                Date date = res1.getDate(2);
                int idCustomer = res1.getInt(3);
                int idManeger = res1.getInt(4);
                String nameCustomer = res1.getString(5);
                String nameManger = res1.getString(6);
                String lastNameManeger = res1.getString(7);
                Order order = new Order(id, new Customer(idCustomer, nameCustomer),
                        new Profile(idManeger, nameManger, lastNameManeger), date);
                orders.add(order);
            }
        } catch (SQLException ex) {
            System.out.println("sqlException");
        }
        return orders;
    }
    
    public ArrayList<Order> getOrdersByIdEmployee(Profile profile) {
        ArrayList<Order> orders = new ArrayList<>();

        try {
            PreparedStatement state1 = connection.prepareStatement(sqlSelectOrderByIdManager);
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
    
    /*
    The remaining methods will be implemented as needed.
    */   
    @Override
    public Order update(Order entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order delete(Order entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } 
}
