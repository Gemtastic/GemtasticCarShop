package application;

import org.jooq.DSLContext;

import java.sql.SQLException;

public interface DatabaseContextProvider {
    public DSLContext getDslContext(final String dsn, final String user, final String password) throws SQLException;
}
