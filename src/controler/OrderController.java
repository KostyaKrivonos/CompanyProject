/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import controler.dao.DAOFactory;
import controler.dao.OrderDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Order;
import model.Profile;
import view.transferObjects.TransferObjectOrder;

/**
 *
 * @author Алёшечка
 */
public class OrderController {
    private OrderDAO orderDAO;

    public OrderController() throws SQLException {
        this.orderDAO = DAOFactory.getDAOFactory().getOrderDAO();
    }
    
    public boolean createOrder(TransferObjectOrder t){
        //можуть відбуватись додаткові перевірки данних переданих з view 
        Order order1 = new Order(t.getCustomer(), t.getProfile(), t.getItemList(), t.getDate());        
        // додаткова бізнес логіка (відправка sms, Email тощо.)
        return orderDAO.createOrder(order1);
        
    }
    public ArrayList <Order> getOrdersByIdEmployee(Profile profile){
        return orderDAO.getOrdersByIdEmployee(profile);
    }
    
}
