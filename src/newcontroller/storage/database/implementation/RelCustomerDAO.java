/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newcontroller.storage.database.implementation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;
import newcontroller.storage.database.api.RelDAO;

/**
 *
 * @author Алёшечка
 */
public class RelCustomerDAO extends RelDAO<Customer, Integer> {

    private String sqlSelectNameCustomers = "SELECT cs.id, cs.name, cs.adress, cs.phone \n"
            + "FROM customers cs";
    private String sqlInsertInToCustomers = "INSERT INTO customers (name, adress, phone) VALUES (?, ?, ?)";
    private String sqlDeleteCustomer = "DELETE FROM customers WHERE id = ? ";
    private String sqlLimitCustomers = "SELECT * FROM customers LIMIT ?, ?";
    private String sqlUpdateCustomer = "UPDATE customers SET name = ?, adress = ?, phone =? WHERE id = ?";
    private String sqlFindById = "SELECT customers.* FROM customers WHERE id = ?";

    public RelCustomerDAO() {
        super();
    }

    @Override
    public Customer create(Customer customer) {
        try (PreparedStatement state1 = connection.prepareStatement(sqlInsertInToCustomers)) {

            state1.setString(1, customer.getName());
            state1.setString(2, customer.getAdress());
            state1.setString(3, customer.getPhone());
            int count = state1.executeUpdate();
            if (count == 0) {
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
        return customer;
    }

    @Override
    public Customer update(Customer customer) {
        try (PreparedStatement state1 = connection.prepareStatement(sqlUpdateCustomer)) {
            state1.setInt(3, customer.getId());
            state1.setString(1, customer.getName());
            state1.setString(2, customer.getPhone());
            state1.setString(4, customer.getAdress());

            int count = state1.executeUpdate();
            if (count == 0) {
                connection.rollback();
                return null;
            }
            connection.commit();
            return customer;
        } catch (SQLException ex) {
            try {
                connection.rollback();
                return null;
            } catch (SQLException ex1) {
            }
        }
        return customer;
    }

    @Override
    public Customer delete(Customer customer) {
        try (PreparedStatement state1 = connection.prepareStatement(sqlDeleteCustomer)) {
            state1.setInt(1, customer.getId());
            int count = state1.executeUpdate();

            if (count == 0) {
                connection.rollback();
                return null;
            }

            connection.commit();
            return customer;
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex1) {
            }
            return null;
        }
    }

    @Override
    public Collection<Customer> findAll() {
        ArrayList<Customer> customers = new ArrayList<>();

        try (Statement state = connection.createStatement();
                ResultSet res = state.executeQuery(sqlSelectNameCustomers)) {

            while (res.next()) {
                int id = res.getInt(1);
                String name = res.getString(2);
                String adress = res.getString(3);
                String phone = res.getString(4);
                Customer customer = new Customer(id, name, adress, phone);
                customers.add(customer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RelCustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customers;
    }

    @Override
    public Customer findById(Integer key) {
        Customer customer = null;
        try (PreparedStatement state1 = connection.prepareStatement(sqlFindById)) {
            state1.setInt(1, key);

            ResultSet res1 = state1.executeQuery();
            while (res1.next()) {
                int id = res1.getInt(1);
                String name = res1.getString(2);
                String adress = res1.getString(3);
                String phone = res1.getString(4);
                customer = new Customer(id, name, adress, phone);
            }
        } catch (SQLException ex) {
            System.out.println("sqlException");
        }
        return customer;
    }

    public ArrayList<Customer> getCustomersLimit(int startIndex, int countRow) {
        ArrayList<Customer> customers = new ArrayList<>();

        try (PreparedStatement state1 = connection.prepareStatement(sqlLimitCustomers)) {
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
        }
        return customers;
    }
}
