/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newcontroller.storage.database.api;

import java.util.Collection;

/**
 *
 * @author Алёшечка
 */
public interface DAO <T, K> {
    T create(T entity);
    T update (T entity);
    T delete (T entity);
    Collection<T> findAll ();
    T findById (K key);  
}
