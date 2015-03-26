package services.interfaces;

/**
 * Created by Gemtastic on 2015-03-06.
 * @param <Record>
 */
public interface CRUDServices<T> {
    
    public T create(T t);
    
    public T read(int t);
    
    public void delete(int id);
    
    public boolean update(T t);
}
