package com.orion.labreservationapp.DBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static com.orion.labreservationapp.DBC.DBConstants.getCreateTableQueries;

public class DatabaseManager {

    public static void run() {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection(DBConstants.getConnectionURL(),
                            DBConstants.getDBUsername(), DBConstants.getDBPassword());

            for (int i = 0; i < getCreateTableQueries().size(); i++) {
                statement = connection.createStatement();
                String query = getCreateTableQueries().get(i);
                statement.executeUpdate(query);
                statement.close();
                System.out.println("Table created successfully");
            }
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}