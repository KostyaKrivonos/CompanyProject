/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Order;
import model.Profile;
import newcontroller.storage.database.api.*;
import newcontroller.storage.database.implementation.*;
import view.transferObjects.TransferObjectOrder;

/**
 *
 * @author Алёшечка
 */
public class OrderController {
    private DAOFactory factory;

    public OrderController() throws SQLException {
        this.factory = DAOFactory.getDAOFactory(FactoryType.RELATION);
    }
    
    public Order createOrder(TransferObjectOrder t){
        try {
            //можуть відбуватись додаткові перевірки данних переданих з view
            Order order1 = new Order(t.getCustomer(), t.getProfile(), t.getItemList(), t.getDate());
            // додаткова бізнес логіка (відправка sms, Email тощо.)
            DAO<Order,Integer> dao = factory.getDAO(Order.class, Integer.class);
            return dao.create(order1);
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }       
    }
    public ArrayList <Order> getOrdersByIdEmployee(Profile profile){
        try {
            RelOrderDAO dao = (RelOrderDAO)factory.getDAO(Order.class, Integer.class);
            return dao.getOrdersByIdEmployee(profile);
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }   
}
