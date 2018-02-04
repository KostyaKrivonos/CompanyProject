/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.transferObjects;

/**
 *
 * @author Алёшечка
 */
public class TransferObjectCustomer {
    private String name;
    private String adress;
    private String phone;

    public TransferObjectCustomer(String name, String adress, String phone) {
        this.name = name;
        this.adress = adress;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    public String getPhone() {
        return phone;
    }
    
}
