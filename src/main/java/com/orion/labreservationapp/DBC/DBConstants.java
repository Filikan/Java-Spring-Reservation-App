package com.orion.labreservationapp.DBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConstants {

    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "admin";

    private static final String createUserTable = "CREATE TABLE users " +
            "(ID INT PRIMARY KEY ," +
            " USER_NAME TEXT, " +
            " FIRST_NAME TEXT, "+
            " LAST_NAME TEXT, "+
            " EMAIL VARCHAR(50), " +
            " DEPARTMENT TEXT, " +
            " PASSWORD VARCHAR(50))";

    private static final String createServerTable = "CREATE TABLE servers " +
            "(ID INT PRIMARY KEY ," +
            " SERVER_NAME TEXT, " +
            "SERVER_LOCATION TEXT,"+
            "SERIAL_NUMBER INT, "+
            " SERVER_IP TEXT)";

    private static final String createReservationTable =" CREATE TABLE reservation" +
           "(ID INT PRIMARY KEY ," +
            "SERVER_ID INT ,"+
            "RELATED_GROUP TEXT," +
            "DESCRIPTION TEXT, "+
            "RESERVED_BY TEXT, "+
            "IS_AVAILABLE BOOLEAN,"+
            "DEADLINE DATE, "+
            "IS_REMINDER BOOLEAN)";

    private static final String createGroupTable = " CREATE TABLE groups" +
            "(ID INT PRIMARY KEY,"+
            "GROUP_NAME TEXT)";

    public static void main(String[] argv) throws SQLException {
        DBConstants createTableExample = new DBConstants();
        createTableExample.createTable();
    }

    public void createTable() throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();) {
            statement.execute(createServerTable);
            statement.execute(createUserTable);
            statement.execute(createReservationTable);
            statement.execute(createGroupTable);
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}