package com.sber.jdbc.connection;

import java.sql.Connection;
import java.sql.SQLException;

public interface Source {
    Connection connection() throws SQLException;
}
