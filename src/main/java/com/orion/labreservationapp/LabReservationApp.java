package com.orion.labreservationapp;

import com.orion.labreservationapp.DBC.DatabaseManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@SpringBootApplication
@RestController
@ComponentScan({"com.orion.labreservationapp.controller","com.orion.labreservationapp.repository","c"})
public class LabReservationApp {

	public static void main(String[] args) throws SQLException {
		DatabaseManager.run();
		SpringApplication.run(LabReservationApp.class, args);

	}
}