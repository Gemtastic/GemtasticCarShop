package services.interfaces;

/**
 * Created by Gemtastic on 2015-03-06.
 */
public interface StatisticsServices<T> {
    
    public T getAll(String s);
    
    public T getOne(String s);
    
    public int getPercent(String a, String b);
    
    public int getMonthly(String s);
    
    public int getYearly(String s);
}
