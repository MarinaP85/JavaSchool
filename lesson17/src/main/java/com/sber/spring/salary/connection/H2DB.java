package com.sber.spring.salary.connection;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class H2DB implements Source {
    @Override
    public Connection connection() {
        final Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:h2:~/test", "user", "");
            connection.setAutoCommit(true);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
