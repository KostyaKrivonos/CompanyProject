/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import controler.dao.DAOFactory;
import controler.dao.ProductDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Product;
import view.transferObjects.TransferObjectProduct;

/**
 *
 * @author Алёшечка
 */
public class ProductController {
    private ProductDAO productDAO;

    public ProductController() throws SQLException {
        this.productDAO = DAOFactory.getDAOFactory().getProductDAO();
    }
    
    public ArrayList <Product> getAllProducts() throws SQLException{
        ArrayList <Product> products = productDAO.getAllProducts();
        return products;
    }
    
    public ArrayList<Product> getProductsLimit(int startIndex, int countRow){
        return productDAO.getProductsLimit(startIndex, countRow);
    }
    
    public boolean createProduct(TransferObjectProduct t){
        Product product = new Product(t.getName(), t.getPrice());
        return productDAO.createProduct(product);
    }
    
    public boolean deleteProduct(Product product){
        return productDAO.deleteProduct(product);
    }
    
    public boolean updateProductNamePrice(Product product) {
        return productDAO.updateProductNamePrice(product);
    }

    public boolean updateProductName(Product product) {
        return productDAO.updateProductName(product);
    }

    public boolean updateProductPrice(Product product) {
        return productDAO.updateProductPrice(product);
    }
}
