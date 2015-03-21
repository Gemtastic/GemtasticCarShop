package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;

/**
 * Created by Gemtastic on 2015-03-15.
 */
public class CachedPostgresContextProvider implements DatabaseContextProvider {
    private Map<String, DSLContext> connections = new HashMap<>();

    public DSLContext getDslContext(final String dsn, final String user, final String password) throws SQLException {
        final DSLContext cachedContext = connections.get(user + "@" + dsn);
        if (cachedContext != null) {
            return cachedContext;
        }
        final Connection connection = DriverManager.getConnection(dsn, user, password);
        final DSLContext create = DSL.using(connection);
        connections.put(user + "@" + dsn, create);
        return create;
    }

}
