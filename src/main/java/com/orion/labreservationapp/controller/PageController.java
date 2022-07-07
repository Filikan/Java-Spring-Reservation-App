package com.orion.labreservationapp.controller;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;


import com.orion.labreservationapp.model.ReservationDO;
import com.orion.labreservationapp.model.ServerDO;
import com.orion.labreservationapp.service.AdminServiceIF;
import com.orion.labreservationapp.service.ReservationServiceIF;
import com.orion.labreservationapp.service.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
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
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHome() throws SQLException {
        int count = adminServiceIF.getServerCount();
        return "index.html";
    }
    @RequestMapping(value = "/servers", method = RequestMethod.GET)
    public String getServer(Model model) throws SQLException {
        List<ServerDO> server = adminServiceIF.getServer();
        List<ReservationDO> reservation = reservationServiceIF.getReservation();
        model.addAttribute("reservations",reservation);
        model.addAttribute("servers", server);
        return "servers.html";
    }
    @RequestMapping(value="/servers/add", method = RequestMethod.POST)
    public String createServer() throws SQLException {
        adminServiceIF.createServer();
       return "add-server.html";
    }
    @RequestMapping(value="/servers/update", method = RequestMethod.POST)
    public String updateServer() throws SQLException {
       int result = adminServiceIF.updateServer(new ServerDO());
       return "update-server.html";
    }
    @RequestMapping(value="servers/delete/{id}", method = RequestMethod.DELETE)
    public String deleteServerByID() throws SQLException {
       int result = adminServiceIF.deleteServerByID(new ServerDO());
       return "delete-server.html";
    }
    @RequestMapping(value="servers/delete", method = RequestMethod.DELETE)
        public String deleteServer() throws SQLException {
       List <ServerDO> result = adminServiceIF.deleteServer();
       return "delete-server.html";
    }
    @RequestMapping(value = "/reservations/submit", method = RequestMethod.GET)
    public String createReservation(Model model, @RequestParam int user_id, @RequestParam int server_id,@RequestParam String date, @RequestParam boolean status, @RequestParam String reason) throws SQLException {
        reservationServiceIF.createReservation(user_id, server_id, date, status, reason);
        model.addAttribute("reservation", new ReservationDO());
        return "approved.html";
    }

    @RequestMapping(value = "/reservations/create", method = RequestMethod.GET)
        public String createReservation(Model model,@RequestParam int server_id) throws SQLException {
        model.addAttribute("reservation", new ReservationDO());
        return "createReservation.html";
    }

    @RequestMapping(value = "/reservations", method = RequestMethod.GET)
    public String getReservation(Model model) throws SQLException {
        List<ReservationDO> reservation = reservationServiceIF.getReservation();
        model.addAttribute("reservations", reservation);
        return "reservations.html";
    }
}