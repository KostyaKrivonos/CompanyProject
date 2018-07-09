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
import model.Product;
import newcontroller.storage.database.api.*;
import newcontroller.storage.database.implementation.*;
import view.transferObjects.TransferObjectProduct;

/**
 *
 * @author Алёшечка
 */
public class ProductController {
    private DAOFactory factory;

    public ProductController() throws SQLException {
        this.factory = DAOFactory.getDAOFactory(FactoryType.RELATION);
    }
    
    public Collection <Product> getAllProducts() throws SQLException{
        try {        
            DAO<Product,Integer> dao = factory.getDAO(Product.class, Integer.class);
            return dao.findAll();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ArrayList<Product> getProductsLimit(int startIndex, int countRow) {
        try {
            RelProductDAO dao = (RelProductDAO) factory.getDAO(Product.class, Integer.class);
            return dao.getProductsLimit(startIndex, countRow);
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public Product createProduct(TransferObjectProduct t) {
        try {
            Product product = new Product(t.getName(), t.getPrice());
            DAO<Product, Integer> dao = factory.getDAO(Product.class, Integer.class);
            return dao.create(product);
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            return  null;
        }
    }
    
    public Product deleteProduct(Product product) {
        try {
            DAO<Product, Integer> dao = factory.getDAO(Product.class, Integer.class);
            return dao.delete(product);
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public Product updateProductNamePrice(Product product) {
        try {
            DAO<Product,Integer> dao = factory.getDAO(Product.class, Integer.class);
            return dao.update(product);
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Product updateProductName(Product product) {
        try {
            RelProductDAO dao = (RelProductDAO)factory.getDAO(Product.class, Integer.class);
            return dao.updateProductName(product);
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Product updateProductPrice(Product product) {
        try {
            RelProductDAO dao = (RelProductDAO) factory.getDAO(Product.class, Integer.class);
            return dao.updateProductPrice(product);
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
