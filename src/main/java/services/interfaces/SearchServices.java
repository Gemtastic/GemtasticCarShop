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
