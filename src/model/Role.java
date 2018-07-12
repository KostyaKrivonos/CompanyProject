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
public class Role {
    private int idRole;
    private String role;

    public Role() {
    }

    public Role(int idRole, String role) {
        this.idRole = idRole;
        this.role = role;
    }

    public int getIdRole() {
        return idRole;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return role;
    }       
}
