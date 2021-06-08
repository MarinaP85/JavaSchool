package com.sber.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2DB implements Source {
    @Override
    public Connection connection() throws SQLException {
        final Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "user", "");
        connection.setAutoCommit(true);
        return connection;
    }
}
