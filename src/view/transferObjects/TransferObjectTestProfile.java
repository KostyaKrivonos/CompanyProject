/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.transferObjects;

import model.Role;

/**
 *
 * @author Алёшечка
 */
public class TransferObjectTestProfile {
    private String name;
    private String lastName;
    private String nickName;
    private String password;
    private Role role;

    public TransferObjectTestProfile(String name, String lastName, String nickName, 
            String password, Role role) {
        this.name = name;
        this.lastName = lastName;
        this.nickName = nickName;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    
    
    
    
}
