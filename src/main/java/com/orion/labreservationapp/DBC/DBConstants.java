package com.orion.labreservationapp.DBC;

import java.util.ArrayList;

public class DBConstants {

    private static final String DB_CONN_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_CONN_USERNAME = "postgres";
    private static final String DB_CONN_PASSWORD = "admin";
    public static String getConnectionURL() {
        return DB_CONN_URL;
    }

    public static String getDBUsername() {
        return DB_CONN_USERNAME;
    }

    public static String getDBPassword() {
        return DB_CONN_PASSWORD;
    }
    private static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS users " +
            "(ID INT PRIMARY KEY ," +
            " USER_NAME TEXT, " +
            " FIRST_NAME TEXT, "+
            " LAST_NAME TEXT, "+
            " EMAIL VARCHAR(50), " +
            " DEPARTMENT TEXT, " +
            " PASSWORD VARCHAR(50))";

    private static final String CREATE_SERVER_TABLE = "CREATE TABLE IF NOT EXISTS servers " +
            "(ID INT PRIMARY KEY ," +
            " SERVER_NAME TEXT, " +
            "SERVER_LOCATION TEXT,"+
            "SERIAL_NUMBER INT, "+
            " SERVER_IP TEXT)";

    private static final String CREATE_RESERVATİON_TABLE =" CREATE TABLE IF NOT EXISTS reservation" +
           "(ID INT PRIMARY KEY ," +
            "SERVER_ID INT ,"+
            "RELATED_GROUP TEXT," +
            "DESCRIPTION TEXT, "+
            "RESERVED_BY TEXT, "+
            "IS_AVAILABLE BOOLEAN,"+
            "DEADLINE DATE, "+
            "IS_REMINDER BOOLEAN)";

    private static final String CREATE_GROUP_TABLE = " CREATE TABLE IF NOT EXISTS groups" +
            "(ID INT PRIMARY KEY,"+
            "GROUP_NAME TEXT)";


 public static ArrayList <String> getCreateTableQueries(){
     ArrayList<String> list = new ArrayList<>();
     list.add(CREATE_GROUP_TABLE);
     list.add(CREATE_RESERVATİON_TABLE);
     list.add(CREATE_SERVER_TABLE);
     list.add(CREATE_USER_TABLE);
     return list;
 }
}