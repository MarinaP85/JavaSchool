package com.sber.jdbc.connection;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSourceHelper {

    public static void createDb(Connection connection) {
        String sql;
        try {
            sql = FileUtils.readFileToString(new File(
                            DataSourceHelper.class.getResource("/data.sql").getFile()),
                    Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void clearDb(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("truncate table Fibonachi");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

