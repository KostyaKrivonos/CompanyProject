/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Алёшечка
 */
public class Profile {
    private int id;
    private String name;
    private String lastName;
    private String nickName;
    private String password;
    private Role role;

    public Profile() {
    }

    public Profile(int id, String name, String lastName, String nickName, String password, int idRole, String role) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.nickName = nickName;
        this.password = password;
        this.role = new Role(idRole, role);       
    }
    
    public Profile(String name, String lastName, String nickName, String password, int idRole, String role) {
        this.name = name;
        this.lastName = lastName;
        this.nickName = nickName;
        this.password = password;
        this.role = new Role(idRole, role);
    }
    
    public Profile(int id, String name, String lastName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }
    
    public int getId() {
        return id;
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
    
    public int getCountOrders(){
        int count = 0;
        return count;
    }

    @Override
    public String toString() {
        return name + " " + lastName;
    }
    
}
