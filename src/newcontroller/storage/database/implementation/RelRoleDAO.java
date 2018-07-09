/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newcontroller.storage.database.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Role;
import newcontroller.storage.database.api.RelDAO;

/**
 *
 * @author Алёшечка
 */
public class RelRoleDAO extends RelDAO<Role, Integer>{
    private String sql = "SELECT * FROM roles";
    
    public RelRoleDAO() {
        super();
    }
    
    @Override
    public Collection<Role> findAll() {
        ArrayList <Role> roles = new ArrayList<>();
        try (Statement state = connection.createStatement();
            ResultSet res = state.executeQuery(sql)){

            while(res.next()){
                int idRole = res.getInt(1);
                String role = res.getString(2);
                Role role1 = new Role(idRole, role);
                roles.add(role1);
            }            
        } catch (SQLException ex) {
            Logger.getLogger(RelRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return roles;
    }
    
    /*
    The remaining methods will be implemented as needed.
    */   
    @Override
    public Role create(Role entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Role update(Role entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Role delete(Role entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Role findById(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
