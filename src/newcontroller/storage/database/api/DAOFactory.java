/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newcontroller.storage.database.api;

import newcontroller.storage.database.implementation.*;


//enum FactoryType {RELATION, OBJECT, XML}
public abstract class DAOFactory{
    private static DAOFactory daoFactory;

    

    public static DAOFactory getDAOFactory(FactoryType type) {
        if(daoFactory != null)
            return daoFactory;           
        switch(type){
            case RELATION :
                daoFactory = new RelDAOFactory(); 
                return daoFactory;
            case OBJECT :
                return new ObjDAOFactory();
            case XML :
               return new XmlDAOFactory();
            default: return new RelDAOFactory();
        }
    }
    //This is a universal method, in which I use generics, which reduces the amount of code;
    public abstract <T, K> DAO<T, K> getDAO(Class <T> c, Class <K> k) throws InstantiationException, IllegalAccessException; 
    
}
