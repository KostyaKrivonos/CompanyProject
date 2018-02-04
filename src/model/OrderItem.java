package model;

import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Алёшечка
 */
public class OrderItem {
    private Product product;
    private double quantity;
    private boolean paid;

    public OrderItem(Product goods, double quantity, boolean paid) {
        this.product = goods;
        this.quantity = quantity;
        this.paid = paid;
    }
    
    public double getTotalPrice(){
        return quantity * product.getPrice();
    }
    
    public double getTotal(double total){
        return getTotalPrice() + total;
    }

    public double getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public boolean isPaid() {
        return paid;
    }
    
    public double getPrice(){
        return product.getPrice();
    }
    
    public void addQuantity(double d){
        this.quantity = this.quantity + d;
    }
    
    @Override
    public String toString() {
        return product + " " + quantity;
    }
}
