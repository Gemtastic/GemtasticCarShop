package services.interfaces;

/**
 * Created by Gemtastic on 2015-03-06.
 */
public interface CRUDServices<T> {
    
    public T getAll();
    
    public void create();
    
    public T read(int t);
    
    public void delete(int id);
    
    public void update();
}
