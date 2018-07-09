/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Profile;
import newcontroller.storage.database.api.*;
import newcontroller.storage.database.implementation.*;
import view.transferObjects.TransferObjectTestProfile;

/**
 *
 * @author Алёшечка
 */
public class EmployeeController {
    private DAOFactory factory;

    public EmployeeController() throws SQLException {
        this.factory = DAOFactory.getDAOFactory(FactoryType.RELATION);
    }
    
    public Collection<Profile> getAllEmployees() throws SQLException {
        ArrayList<Profile> employees = new ArrayList<>();
        try {
            DAO<Profile, Integer> dao = factory.getDAO(Profile.class, Integer.class);
            return dao.findAll();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employees;
    }
    
    public Profile createProfile(TransferObjectTestProfile t) throws SQLException{
        try {
            //логіка додатвкової перевірки даних для створення Profile.
            Profile profile = new Profile(t.getName(), t.getLastName(), t.getNickName(),
                    t.getPassword(), t.getRole().getIdRole(), t.getRole().getRole());
            DAO<Profile, Integer> dao = factory.getDAO(Profile.class, Integer.class);
            return dao.create(profile);
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public Profile deleteProfile(Profile profile) {
        try {
            DAO<Profile, Integer> dao = factory.getDAO(Profile.class, Integer.class);
            return dao.delete(profile);
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public Map <Profile, Integer> getEmployeesName() throws SQLException{
        try {
            RelEmployeeDAO dao = (RelEmployeeDAO) factory.getDAO(Profile.class, Integer.class);
            return dao.getEmployeesStatistics();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
