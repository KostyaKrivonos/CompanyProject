/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Алёшечка
 */
public class DAOFactory {
    private static DAOFactory factory;
    private Connection con = null;

    private DAOFactory() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "1584");
        con.setAutoCommit(false);
    }

    private Connection getConnection() throws SQLException {
        if (con == null) {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "1584");
            con.setAutoCommit(false);
        }
        return con;
    }

    public static DAOFactory getDAOFactory() throws SQLException {
        if (factory == null) {
            factory = new DAOFactory();
        }
        return factory;
    }
    
    public EmployeeDAO getEmployeeDAO() throws SQLException{
        EmployeeDAO employeeDAO = new EmployeeDAO(getConnection());
        return employeeDAO;
    }
    
    public RoleDAO getRoleDAO() throws SQLException{
        RoleDAO roleDAO = new RoleDAO(getConnection());
        return roleDAO;
    }
    
    public CustomerDAO getCustomerDAO() throws SQLException{
        CustomerDAO customerDAO = new CustomerDAO(getConnection());
        return customerDAO;
    }
    
    public ProductDAO getProductDAO() throws SQLException{
        ProductDAO productDAO = new ProductDAO(getConnection());
        return productDAO;
    }
    
    public OrderDAO getOrderDAO() throws SQLException{
        OrderDAO orderDAO = new OrderDAO(getConnection());
        return orderDAO;
    }
}
