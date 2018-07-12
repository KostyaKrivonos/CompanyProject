/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newcontroller.storage.database.implementation;

import newcontroller.storage.database.api.*;
/**
 *
 * @author Алёшечка
 */
public class XmlDAOFactory extends DAOFactory{
    //This class defines the type of the database, which means that the database is used from an xml file;
    @Override
    public <T, K> DAO<T, K> getDAO(Class<T> c, Class<K> k) throws InstantiationException, IllegalAccessException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
