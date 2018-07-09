/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newcontroller.storage.database.api;

import java.sql.Connection;

/**
 *
 * @author Алёшечка
 */
public abstract class RelDAO<T, K> implements DAO<T, K>{
    protected Connection connection;

    public RelDAO() {
        this.connection = null;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
}
