/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Profile;

/**
 *
 * @author Алёшечка
 */
public class LoginController {
    private static LoginController loginControler;
    private Connection con = null;
    private String sql = "SELECT st.id, st.name, st.lastname, sd.nickName, sd.password, sd.idRole, r.role\n" +
                "FROM staff st, staffdetails sd, roles r \n" +
                "WHERE sd.id = st.id AND sd.idRole = r.id AND sd.nickName = ? \n" +
                "        AND sd.password = ?";
 
    private LoginController() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "1584");       
    }
    
    public static  LoginController getLoginControler() throws SQLException{
        if(loginControler == null){
            loginControler = new LoginController();
        }
        return loginControler;
    }
    
    public Profile checkUser(String login, String password) throws SQLException{
        PreparedStatement state = getConnection().prepareStatement(sql);
        state.setString(1, login);
        state.setString(2, password);
        ResultSet res = state.executeQuery();    
        if(res.next()){
            int id = res.getInt(1);
            String name = res.getString(2);
            String lastName = res.getString(3);
            String nickName = res.getString(4);
            String password1 = res.getString(5);
            int idRole = res.getInt(6);
            String role = res.getString(7);
            Profile profile = new Profile(id, name, lastName, nickName, password1, idRole, role);
            return profile;
        }
        else return null;   
    }
    
    private Connection getConnection() throws SQLException{
        if(con == null){
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", 
                "root", "1584");           
        }
        return con;
    }
}
