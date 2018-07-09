package model;


import java.util.ArrayList;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Алёшечка
 */
public class Order {
    private ArrayList <OrderItem> orderItems;
    private Date date;
    private int id;
    private Customer client;
    private Profile manager;

    

    public Order(Customer client, Profile profile, ArrayList<OrderItem> itemList, Date date) {
        this.client = client;
        this.date = date;
        this.manager = profile;        
        this.orderItems = itemList;       
    }

    public Order(int id, Customer client, Profile profile, Date date) {
        this.id = id;
        this.client = client;
        this.date = date;
        this.manager = profile;
    }
        
    public void addOrderItem(OrderItem orderItem){               
        orderItems.add(orderItem);
    }

    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }

    public Date getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public Customer getClient() {
        return client;
    }

    public Profile getManager() {
        return manager;
    }

    @Override
    public String toString() {
        return date + " " + id + " " + client.getName();
    }  
}
    