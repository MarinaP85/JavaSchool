package com.sber.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FibonachiDaoImpl implements FibonachiDao {
    private final Connection connection;

    public FibonachiDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Integer> getFibonachi(int n) {
        List<Integer> initList = new ArrayList<>();
        try (PreparedStatement statement = connection
                .prepareStatement("SELECT distinct n, nFibonachi FROM Fibonachi where n <= ? order by n")) {
            statement.setInt(1, n);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                initList.add(resultSet.getInt("nFibonachi"));
            }
            return initList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Integer> getAllFibonachi() {
        List<Integer> initList = new ArrayList<>();
        try (PreparedStatement statement = connection
                .prepareStatement("SELECT distinct n, nFibonachi FROM Fibonachi order by n")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                initList.add(resultSet.getInt("nFibonachi"));
            }
            return initList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addFibonachi(List<Integer> initList, int beginN) {
        try (PreparedStatement statement = connection
                .prepareStatement("INSERT INTO Fibonachi(n, nFibonachi) VALUES(?, ?);")) {
            connection.setAutoCommit(false);
            for (int i = beginN; i < initList.size(); i++) {
                statement.setInt(1, i);
                statement.setInt(2, initList.get(i));
                statement.execute();
            }
            connection.commit();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
