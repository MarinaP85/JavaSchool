package com.sber.spring.salary.connection;

import java.sql.Connection;

public interface Source {
    Connection connection();
}
