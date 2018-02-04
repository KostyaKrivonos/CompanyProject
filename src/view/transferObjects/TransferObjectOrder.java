/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.transferObjects;

import java.util.ArrayList;
import java.util.Date;
import model.Customer;
import model.OrderItem;
import model.Profile;

/**
 *
 * @author Алёшечка
 */
public class TransferObjectOrder {
    private Customer customer;
    private Profile profile;
    private ArrayList<OrderItem> itemList;
    private Date date;

    public TransferObjectOrder(Customer customer, Profile profile, ArrayList<OrderItem> itemList, Date date) {
        this.customer = customer;
        this.profile = profile;
        this.itemList = itemList;
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Profile getProfile() {
        return profile;
    }

    public ArrayList<OrderItem> getItemList() {
        return itemList;
    }

    public Date getDate() {
        return date;
    }
    
}
