/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Role;

/**
 *
 * @author Алёшечка
 */
public class RoleDAO {
    private Connection con = null;
    private String sql = "SELECT * FROM roles";

    public RoleDAO(Connection con) {
        this.con = con;
    }
    
    
    public ArrayList <Role> getRoles() throws SQLException{
        ArrayList <Role> roles = new ArrayList<>();
        
        Statement state = con.createStatement();
        ResultSet res = state.executeQuery(sql);
        
        while(res.next()){
            int idRole = res.getInt(1);
            String role = res.getString(2);
            Role role1 = new Role(idRole, role);
            roles.add(role1);
        }
        
        return roles;
    }
}
