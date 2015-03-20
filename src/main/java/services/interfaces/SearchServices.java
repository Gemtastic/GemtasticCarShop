/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.interfaces;

/**
 *
 * @author Aizic Moisen
 * @param <T>
 */
public interface SearchServices<T> {
    
    public T getAll();
    
    public T getAllWhere(String column, String constraint);
}
