package com.orion.labreservationapp.DBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TransactionManager {

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DBConstants.getConnectionURL(), DBConstants.getDBUsername(), DBConstants.getDBPassword());
            connection.setAutoCommit(false);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}