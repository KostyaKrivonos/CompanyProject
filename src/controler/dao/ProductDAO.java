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
import model.Product;

/**
 *
 * @author Алёшечка
 */
public class ProductDAO {
    private Connection con = null;
    private String sqlSelectNameProducts = "SELECT * FROM products";
//    private String sqlDeleteIdProductFromOrderDetails = "DELETE FROM ordersdetails WHERE idOrder = ? AND idProduct = ?";
    private String sqlDeleteIdProduct = "DELETE FROM products WHERE id = ?";
    private String sqlLimitProducts = "SELECT * FROM products LIMIT ?, ?";
    private String sqlInsertInToProducts = "INSERT INTO products (name, price) VALUES (?, ?)";
    private String sqlUpdateProductNamePrice = "UPDATE products SET name = ?, price = ? WHERE id = ?";
    private String sqlUpdateProductName = "UPDATE products SET name = ? WHERE id = ?";
    private String sqlUpdateProductPrice = "UPDATE products SET price = ? WHERE id = ?";

    public ProductDAO(Connection con) {
        this.con = con;
    }
    
    public ArrayList <Product> getAllProducts() throws SQLException{
        ArrayList<Product> products = new ArrayList<>();

        Statement state = con.createStatement();
        ResultSet res = state.executeQuery(sqlSelectNameProducts);

        while (res.next()) {
            int id = res.getInt(1);
            String name = res.getString(2);
            double price = res.getDouble(3);
            Product product = new Product(id, name, price);
            products.add(product);
        }
        return products;
    }
    
    public boolean createProduct(Product product){
        try {
            PreparedStatement state1 = con.prepareStatement(sqlInsertInToProducts);
            state1.setString(1, product.getName());
            state1.setDouble(2, product.getPrice());
            
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
    
    public boolean deleteProduct(Product product){
        try {
            PreparedStatement state1 = con.prepareStatement(sqlDeleteIdProduct);
            state1.setInt(1, product.getId());
            
            int count = state1.executeUpdate();
            if (count == 0) {
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
                Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return false;
        }
    }
    
    public ArrayList<Product> getProductsLimit(int startIndex, int countRow){
        ArrayList<Product> products = new ArrayList<>();
        try {

            PreparedStatement state1 = con.prepareStatement(sqlLimitProducts);
            state1.setInt(1, (startIndex - 1) * countRow);
            state1.setInt(2, countRow);
                       
            ResultSet res = state1.executeQuery();
            
            while (res.next()){
                int id = res.getInt(1);
                String name = res.getString(2);
                Double price = res.getDouble(3);
                Product product = new Product(id, name, price);
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }
    
    public boolean updateProductNamePrice (Product product){
        try {
            PreparedStatement state1 = con.prepareStatement(sqlUpdateProductNamePrice);
            state1.setInt(3, product.getId());
            state1.setString(1, product.getName());
            state1.setDouble(2, product.getPrice());

            int count = state1.executeUpdate();
            if (count == 0) {
                con.rollback();
                return false;
            }
            con.commit();
            return true;
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
    
    public boolean updateProductName (Product product){
        try {
            PreparedStatement state1 = con.prepareStatement(sqlUpdateProductName);
            state1.setInt(2, product.getId());
            state1.setString(1, product.getName());           

            int count = state1.executeUpdate();
            if (count == 0) {
                con.rollback();
                return false;
            }
            con.commit();
            return true;
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
    
    public boolean updateProductPrice(Product product) {
        try {
            PreparedStatement state1 = con.prepareStatement(sqlUpdateProductPrice);
            state1.setInt(2, product.getId());
            state1.setDouble(1, product.getPrice());

            int count = state1.executeUpdate();
            if (count == 0) {
                con.rollback();
                return false;
            }
            con.commit();
            return true;
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
}
