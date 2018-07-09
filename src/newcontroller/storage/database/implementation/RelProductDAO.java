/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newcontroller.storage.database.implementation;

import controler.dao_old_version_.EmployeeDAO;
import controler.dao_old_version_.ProductDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;
import newcontroller.storage.database.api.RelDAO;

/**
 *
 * @author Алёшечка
 */
public class RelProductDAO extends RelDAO<Product, Integer> {

    private String sqlFindAllProducts = "SELECT * FROM products";
    private String sqlDeleteIdProduct = "DELETE FROM products WHERE id = ?";
    private String sqlLimitProducts = "SELECT * FROM products LIMIT ?, ?";
    private String sqlInsertInToProducts = "INSERT INTO products (name, price) VALUES (?, ?)";
    private String sqlUpdateProductNamePrice = "UPDATE products SET name = ?, price = ? WHERE id = ?";
    private String sqlUpdateProductName = "UPDATE products SET name = ? WHERE id = ?";
    private String sqlUpdateProductPrice = "UPDATE products SET price = ? WHERE id = ?";

    public RelProductDAO() {
        super();
    }

    @Override
    public Product create(Product product) {
        try (PreparedStatement state1 = connection.prepareStatement(sqlInsertInToProducts)) {

            state1.setString(1, product.getName());
            state1.setDouble(2, product.getPrice());

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
        return product;
    }

    @Override
    public Product update(Product product) {
        try (PreparedStatement state1 = connection.prepareStatement(sqlUpdateProductNamePrice)) {

            state1.setInt(3, product.getId());
            state1.setString(1, product.getName());
            state1.setDouble(2, product.getPrice());

            int count = state1.executeUpdate();
            if (count == 0) {
                connection.rollback();
                return null;
            }
            connection.commit();
            return product;
        } catch (SQLException ex) {
            try {
                connection.rollback();
                return null;
            } catch (SQLException ex1) {
            }
        }
        return product;
    }

    public Product updateProductName(Product product) {
        try (PreparedStatement state1 = connection.prepareStatement(sqlUpdateProductName)) {

            state1.setInt(2, product.getId());
            state1.setString(1, product.getName());

            int count = state1.executeUpdate();
            if (count == 0) {
                connection.rollback();
                return null;
            }
            connection.commit();
            return product;
        } catch (SQLException ex) {
            try {
                connection.rollback();
                return null;
            } catch (SQLException ex1) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return product;
    }

    public Product updateProductPrice(Product product) {
        try (PreparedStatement state1 = connection.prepareStatement(sqlUpdateProductPrice)) {

            state1.setInt(2, product.getId());
            state1.setDouble(1, product.getPrice());

            int count = state1.executeUpdate();
            if (count == 0) {
                connection.rollback();
                return null;
            }
            connection.commit();
            return product;
        } catch (SQLException ex) {
            try {
                connection.rollback();
                return null;
            } catch (SQLException ex1) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return product;
    }

    @Override
    public Product delete(Product product) {
        try (PreparedStatement state1 = connection.prepareStatement(sqlDeleteIdProduct);) {

            state1.setInt(1, product.getId());

            int count = state1.executeUpdate();
            if (count == 0) {
                connection.rollback();
                return null;
            }
            connection.commit();
            return product;

        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return null;
        }
    }

    @Override
    public Collection<Product> findAll() {
        ArrayList<Product> products = new ArrayList<>();
        try (Statement state = connection.createStatement();
                ResultSet res = state.executeQuery(sqlFindAllProducts)) {

            while (res.next()) {
                int id = res.getInt(1);
                String name = res.getString(2);
                double price = res.getDouble(3);
                Product product = new Product(id, name, price);
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RelProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    public ArrayList<Product> getProductsLimit(int startIndex, int countRow) {
        ArrayList<Product> products = new ArrayList<>();
        try (PreparedStatement state1 = connection.prepareStatement(sqlLimitProducts);) {

            state1.setInt(1, (startIndex - 1) * countRow);
            state1.setInt(2, countRow);

            ResultSet res = state1.executeQuery();

            while (res.next()) {
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

    /*
    The remaining methods will be implemented as needed.
     */
    @Override
    public Product findById(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
