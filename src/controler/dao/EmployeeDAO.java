/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Profile;


/**
 *
 * @author Алёшечка
 */
public class EmployeeDAO {
    private Connection con = null;
    private String sqlInsertInToStaff = "INSERT INTO staff (name, lastName) VALUES (?, ?)";
    private String sqlInsertInToStaffDetails = "INSERT INTO staffdetails (id, nickName, password, idRole) "
                                                    + "VALUES (?, ?, ?, ?)";
    private String sqlSelectIdStaff = "SELECT staff.id FROM staff WHERE name = ? AND lastName = ?";
    private String sqlSelectNameStaff = "SELECT st.id, st.name, st.lastname, sd.nickName, sd.password, sd.idRole, r.role \n" +
                "FROM staff st, staffdetails sd, roles r WHERE sd.id = st.id AND sd.idRole = r.id";
    private String sqlDeleteProfileFromStaffdetails = "DELETE FROM staffdetails WHERE id = ?";
    private String sqlDeleteProfile = "DELETE FROM staff WHERE id = ? ";
    private String sqlGroupSelectByName = "SELECT staff.*, COUNT(orders.id) AS count  FROM orders, staff \n"
            + "WHERE orders.idManeger = staff.id GROUP BY staff.id, staff.name, staff.lastName ORDER BY COUNT(orders.id) DESC";
    
    
    public EmployeeDAO(Connection con) {
        this.con = con;
    }
    
    public ArrayList <Profile> getAllEmployees() throws SQLException{
        ArrayList <Profile> employees = new ArrayList<>();
        
        Statement state = con.createStatement();
        ResultSet res = state.executeQuery(sqlSelectNameStaff);
        
        while(res.next()){
            int id = res.getInt(1);
            String name = res.getString(2);
            String lastName = res.getString(3);
            String nickName = res.getString(4);
            String password = res.getString(5);
            int idRole = res.getInt(6);
            String role = res.getString(7);
            Profile profile1 = new Profile(id, name, lastName, nickName, password, idRole, role);
            employees.add(profile1);
        }
        return employees;
    }
    
    public boolean createProfile(Profile profile){
        try {
            //логіка додавання Profile в БД.
            PreparedStatement state1 = con.prepareStatement(sqlInsertInToStaff);
            state1.setString(1, profile.getName());
            state1.setString(2, profile.getLastName());
            int count = state1.executeUpdate();
            if(count == 0){
                con.rollback();
                return false;
            }
            
            PreparedStatement state3 = con.prepareStatement(sqlSelectIdStaff);
            state3.setString(1, profile.getName());
            state3.setString(2, profile.getLastName());
            ResultSet res = state3.executeQuery();
            int id = 0;
            while(res.next()){
                id = res.getInt(1);
            }
            
            PreparedStatement state2 = con.prepareStatement(sqlInsertInToStaffDetails);
            state2.setInt(1, id);
            state2.setString(2, profile.getNickName());
            state2.setString(3, profile.getPassword());
            state2.setInt(4, profile.getRole().getIdRole());
                        
            count = state2.executeUpdate();
            if(count == 0){
                con.rollback();
                return false;
            }
            
            con.commit();
        } catch (SQLException ex) {
            try {
                con.rollback();
                return false;
            } catch (SQLException ex1) {
                Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return true;
    }
    
    public boolean deleteProfile(Profile profile){
        try {
            PreparedStatement state1 = con.prepareStatement(sqlDeleteProfileFromStaffdetails);
            state1.setInt(1, profile.getId());
//            state1.executeUpdate();
            
            int count = state1.executeUpdate();
            if(count == 0){
                con.rollback();
                return false;
            }
            
            PreparedStatement state2 = con.prepareStatement(sqlDeleteProfile);
            state2.setInt(1, profile.getId());
            
            count = state2.executeUpdate();           
            if(count == 0){
                con.rollback();
                return false;
            }
                  
            con.commit();
            return true;
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
                return false;
        }   
    }
    
    public Map <Profile, Integer> getEmployeesStatistics() throws SQLException{
        Map <Profile, Integer> employees1 = new HashMap<>();
        
        Statement state = con.createStatement();
        ResultSet res = state.executeQuery(sqlGroupSelectByName);
        
        while(res.next()){ 
            int id = res.getInt(1);
            String name = res.getString(2);
            String lastName = res.getString(3);
            int countOrders = res.getInt("count");
            Profile profile1 = new Profile(id, name, lastName);
            employees1.put(profile1, countOrders);
        }
        return employees1;
    }
}
