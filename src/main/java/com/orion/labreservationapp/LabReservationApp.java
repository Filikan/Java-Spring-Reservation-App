package com.orion.labreservationapp;

import com.orion.labreservationapp.DBC.DatabaseManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.sql.SQLException;

@SpringBootApplication
public class LabReservationApp {

    public static void main(String[] args) throws SQLException {
        DatabaseManager.run();
        SpringApplication.run(LabReservationApp.class, args);
    }
}