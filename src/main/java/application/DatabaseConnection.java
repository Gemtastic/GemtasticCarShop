package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

/**
 * Created by Gemtastic on 2015-03-15.
 */
public class DatabaseConnection {
    private final String dbusername = "postgres";
    private final String dbpassword = "g3mt45t1c";
    private final String url = "jdbc:postgresql:postgres";
    
    private DSLContext create;
    
    public DSLContext connect()
    {
        try(Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)){
            create = DSL.using(connection, SQLDialect.POSTGRES);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return create;
    }
    
    
    
    
}
