/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newcontroller.storage.database.implementation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Profile;
import newcontroller.storage.database.api.RelDAO;

/**
 *
 * @author Алёшечка
 */
public class RelEmployeeDAO extends RelDAO<Profile, Integer>{
    private String sqlSelectNameStaff = "SELECT st.id, st.name, st.lastname, sd.nickName, sd.password, sd.idRole, r.role \n"
            + "FROM staff st, staffdetails sd, roles r WHERE sd.id = st.id AND sd.idRole = r.id";
    private String sqlFindByIdStaff = "SELECT st.name, st.lastname, sd.nickName, sd.password, sd.idRole, r.role \n"
            + "FROM staff st, staffdetails sd, roles r WHERE sd.id = st.id AND sd.idRole = r.id AND st.id = ?";
    private String sqlInsertInToStaff = "INSERT INTO staff (name, lastName) VALUES (?, ?)";
    private String sqlSelectIdStaff = "SELECT staff.id FROM staff WHERE name = ? AND lastName = ?";
    private String sqlInsertInToStaffDetails = "INSERT INTO staffdetails (id, nickName, password, idRole) "
            + "VALUES (?, ?, ?, ?)";
    private String sqlDeleteProfileFromStaffdetails = "DELETE FROM staffdetails WHERE id = ?";
    private String sqlDeleteProfile = "DELETE FROM staff WHERE id = ? ";
    private String sqlGroupSelectByName = "SELECT staff.*, COUNT(orders.id) AS count  FROM orders, staff \n"
            + "WHERE orders.idManeger = staff.id GROUP BY staff.id, staff.name, staff.lastName ORDER BY COUNT(orders.id) DESC";
    private String sqlUpdateProfile = "UPDATE staff s, staffdetails sd SET s.name = ?, s.lastName = ?, sd.nickName = ?, sd.password = ?, sd.idRole = ? WHERE sd.id = s.id AND s.id = ?";
    
    public RelEmployeeDAO() {
        super();
    }
    
    @Override
    public Profile create(Profile profile) {
        try (PreparedStatement state1 = connection.prepareStatement(sqlInsertInToStaff);
                PreparedStatement state3 = connection.prepareStatement(sqlSelectIdStaff);
                PreparedStatement state2 = connection.prepareStatement(sqlInsertInToStaffDetails)) {
            //логіка додавання Profile в БД.

            state1.setString(1, profile.getName());
            state1.setString(2, profile.getLastName());
            int count = state1.executeUpdate();
            if (count == 0) {
                connection.rollback();
                return null;
            }
                        
            state3.setString(1, profile.getName());
            state3.setString(2, profile.getLastName());
            ResultSet res = state3.executeQuery();
            int id = 0;
            while(res.next()){
                id = res.getInt(1);
            }
                        
            state2.setInt(1, id);
            state2.setString(2, profile.getNickName());
            state2.setString(3, profile.getPassword());
            state2.setInt(4, profile.getRole().getIdRole());
                        
            count = state2.executeUpdate();
            if(count == 0){
                connection.rollback();
                return null;
            }
            
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
                return null;
            } catch (SQLException ex1) {
            }
        }
        return profile;
    }

    @Override
    public Profile update(Profile profile) {
        try (PreparedStatement state1 = connection.prepareStatement(sqlUpdateProfile)) {
            state1.setInt(6, profile.getId());
            state1.setString(1, profile.getName());
            state1.setString(2, profile.getLastName());
            state1.setString(3, profile.getNickName());
            state1.setString(4, profile.getPassword());
            state1.setInt(5, profile.getRole().getIdRole());

            int count = state1.executeUpdate();
            if (count == 0) {
                connection.rollback();
                return null;
            }
            connection.commit();
            return profile;
        } catch (SQLException ex) {
            try {
                connection.rollback();
                return null;
            } catch (SQLException ex1) {
            }
        }
        return profile;
    }

    @Override
    public Profile delete(Profile profile) {
        try (PreparedStatement state1 = connection.prepareStatement(sqlDeleteProfileFromStaffdetails);
                PreparedStatement state2 = connection.prepareStatement(sqlDeleteProfile)) {

            state1.setInt(1, profile.getId());

            int count = state1.executeUpdate();
            if (count == 0) {
                connection.rollback();
                return null;
            }

            state2.setInt(1, profile.getId());

            count = state2.executeUpdate();
            if (count == 0) {
                connection.rollback();
                return null;
            }

            connection.commit();
            return profile;
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex1) {
            }
            return null;
        }
    }

    @Override
    public Collection<Profile> findAll() {
        ArrayList<Profile> employees = new ArrayList<>();
        try (Statement state = connection.createStatement();
                ResultSet res = state.executeQuery(sqlSelectNameStaff);) {

            while (res.next()) {
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
        } catch (SQLException ex) {
            Logger.getLogger(RelEmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employees;
    }

    @Override
    public Profile findById(Integer key) {
        Profile profile = null;
        
        try (PreparedStatement state1 = connection.prepareStatement(sqlFindByIdStaff)){                                    
            state1.setInt(1, key);
            
            ResultSet res1 = state1.executeQuery();
            while (res1.next()) {
                String name = res1.getString(2);
                String lastName = res1.getString(3);
                String nickName = res1.getString(4);
                String password = res1.getString(5);
                int idRole = res1.getInt(6);
                String role = res1.getString(7);
                profile = new Profile(name, lastName, nickName, password, idRole, role);
            }
        } catch (SQLException ex) {
            System.out.println("sqlException");
        }
        return profile;
    }
    
    public Map <Profile, Integer> getEmployeesStatistics() throws SQLException{
        Map <Profile, Integer> employees1 = new HashMap<>();
        
        Statement state = connection.createStatement();
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
