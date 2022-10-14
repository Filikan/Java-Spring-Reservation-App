package com.orion.labreservationapp.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.orion.labreservationapp.model.ReservationDO;
import com.orion.labreservationapp.model.ServerDO;
import com.orion.labreservationapp.model.UserDO;
import com.orion.labreservationapp.service.AdminServiceIF;
import com.orion.labreservationapp.service.ReservationServiceIF;
import com.orion.labreservationapp.service.UserServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
    @Autowired
    private AdminServiceIF adminServiceIF;
    @Autowired
    private ReservationServiceIF reservationServiceIF;
    @Autowired
    private UserServiceIF userServiceIF;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHome() throws SQLException {
        int count = adminServiceIF.getServerCount();
        return "index.html";
    }

    @RequestMapping(value = "/servers", method = RequestMethod.GET)
    public String getServer(Model model) throws SQLException {
        List<ServerDO> server = adminServiceIF.getServer();
        List<ReservationDO> reservation = reservationServiceIF.getReservation();
        model.addAttribute("reservations", reservation);
        model.addAttribute("servers", server);
        return "servers.html";
    }

    @RequestMapping(value = "/servers/submit", method = RequestMethod.GET)
    public String createServer(Model model, @RequestParam String server_name, @RequestParam String server_location,
            @RequestParam String server_ip, @RequestParam String serial_number, @RequestParam String server_type,
            @RequestParam Boolean is_host, @RequestParam int id) throws SQLException {
        adminServiceIF.createServer(server_name, server_location, server_ip, serial_number, server_type, is_host, id);
        model.addAttribute("server", new ServerDO());
        return "approved.html";
    }

    @RequestMapping(value = "/servers/add", method = RequestMethod.GET)
    public String createServer(Model model) throws SQLException {
        model.addAttribute("server", new ServerDO());
        return "addServer.html";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUser(Model model) throws SQLException {
        List<UserDO> user = userServiceIF.getUser();
        List<ReservationDO> reservation = reservationServiceIF.getReservation();
        model.addAttribute("reservations", reservation);
        model.addAttribute("users", user);
        return "users.html";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.GET)
    public String createUser(Model model) throws SQLException {
        model.addAttribute("user", new UserDO());
        return "addUser.html";
    }

    @RequestMapping(value = "/users/submit", method = RequestMethod.POST)
    public String createUser(Model model, @RequestParam String user_name, @RequestParam String first_name,
                             @RequestParam String last_name, @RequestParam String email, @RequestParam String department,
                             @RequestParam String password, @RequestParam int id) throws SQLException {
        userServiceIF.createUser(user_name,first_name, last_name, email, department, password, id);
        model.addAttribute("user", new UserDO());
        return "approved.html";
    }

    @RequestMapping(value = "/servers/update", method = RequestMethod.POST)
    public String updateServer() throws SQLException {
        int result = adminServiceIF.updateServer(new ServerDO());
        return "update-server.html";
    }

    @RequestMapping(value = "servers/delete/{id}", method = RequestMethod.DELETE)
    public String deleteServerByID() throws SQLException {
        int result = adminServiceIF.deleteServerByID(new ServerDO());
        return "delete-server.html";
    }

    @RequestMapping(value = "servers/delete", method = RequestMethod.DELETE)
    public String deleteServer() throws SQLException {
        List<ServerDO> result = adminServiceIF.deleteServer();
        return "delete-server.html";
    }

    @RequestMapping(value = "/reservations/submit", method = RequestMethod.GET)
    public String createReservation(Model model, @RequestParam int user_id, @RequestParam int server_id,
            @RequestParam String start_date, @RequestParam String end_date, @RequestParam boolean status,
            @RequestParam String reason) throws SQLException {
        reservationServiceIF.createReservation(user_id, server_id, start_date, end_date, status, reason);
        model.addAttribute("reservation", new ReservationDO());
        return "approved.html";
    }

    @RequestMapping(value = "/reservations/create", method = RequestMethod.GET)
    public String createReservation(Model model) throws SQLException {
        model.addAttribute("reservation", new ReservationDO());
        List<ServerDO> server = adminServiceIF.getServer();
        List<ReservationDO> reservation = reservationServiceIF.getReservation();
        model.addAttribute("reservations", reservation);
        model.addAttribute("servers", server);

        return "createReservation.html";
    }

    @RequestMapping(value = "/reservations", method = RequestMethod.GET)
    public String getReservation(Model model) throws SQLException {
        List<ReservationDO> reservation = reservationServiceIF.getReservation();
        model.addAttribute("reservations", reservation);
        return "reservations.html";
    }
}