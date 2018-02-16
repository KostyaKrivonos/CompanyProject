/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import controler.dao.DAOFactory;
import controler.dao.EmployeeDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import model.Profile;
import view.transferObjects.TransferObjectTestProfile;

/**
 *
 * @author Алёшечка
 */
public class EmployeeController {
    private EmployeeDAO employeeDAO;

    public EmployeeController() throws SQLException {
        employeeDAO = DAOFactory.getDAOFactory().getEmployeeDAO();
    }
    
    public ArrayList <Profile> getAllEmployees() throws SQLException{
        ArrayList <Profile> employees = employeeDAO.getAllEmployees();
        return employees;
    }
    
    public boolean createProfile(TransferObjectTestProfile t) throws SQLException{
         //логіка додатвкової перевірки даних для створення Profile.
        Profile profile = new Profile(t.getName(), t.getLastName(), t.getNickName(),
                    t.getPassword(), t.getRole().getIdRole(), t.getRole().getRole());
        return employeeDAO.createProfile(profile);
    }
    
    public boolean deleteProfile(Profile profile){
        return employeeDAO.deleteProfile(profile);
    }
    
    public Map <Profile, Integer> getEmployeesName() throws SQLException{
         return employeeDAO.getEmployeesStatistics();        
    }
}
