/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newcontroller.storage.database.implementation;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;
import newcontroller.storage.database.api.*;

/**
 *
 * @author Алёшечка
 */
public class RelDAOFactory extends DAOFactory{
    private static Connection connection = getConnection();

    public RelDAOFactory() {
        if(connection == null)
            getConnection();
    }
    
    private static Connection getConnection(){
        if(connection != null)
            return connection;
        
        Properties properties = new Properties();
        //InputStream is = getClass().getResourceAsStream("/resources/dbconfig.properties");
        try {
            InputStream is = new BufferedInputStream(new FileInputStream("/resources/dbconfig.properties"));
            properties.load(is);
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, user, password);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(RelDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    @Override
    public <T, K> DAO<T, K> getDAO(Class<T> c, Class<K> k) {
        try {
            T t = c.newInstance();
            if (t instanceof Customer) {
                RelCustomerDAO rcd = new RelCustomerDAO();
                rcd.setConnection(connection);
                return (DAO<T, K>) rcd;
            }
            if (t instanceof Profile) {
                RelEmployeeDAO red = new RelEmployeeDAO();
                red.setConnection(connection);
                return (DAO<T, K>) red;
            }
            if (t instanceof Product) {
                RelProductDAO rpd = new RelProductDAO();
                rpd.setConnection(connection);
                return (DAO<T, K>) rpd;
            }
            if (t instanceof Order) {
                RelOrderDAO rod = new RelOrderDAO();
                rod.setConnection(connection);
                return (DAO<T, K>) rod;
            }
            if (t instanceof Role) {
                RelRoleDAO rrd = new RelRoleDAO();
                rrd.setConnection(connection);
                return (DAO<T, K>) rrd;
            }
            
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(RelDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }  
}
