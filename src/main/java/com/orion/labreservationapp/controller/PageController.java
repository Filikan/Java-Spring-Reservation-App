package com.orion.labreservationapp.controller;
import java.rmi.ServerError;
import java.sql.SQLException;
import java.util.List;


import com.orion.labreservationapp.model.ServerDO;
import com.orion.labreservationapp.service.AdminServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class PageController {
    @Autowired
    private AdminServiceIF adminServiceIF;
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHome() throws SQLException {
        int count = adminServiceIF.getServerCount();
        return "index.html";
    }
    @RequestMapping(value = "/servers", method = RequestMethod.GET)
    public String getServers() throws SQLException {
        List<ServerDO> servers = adminServiceIF.getServer();
        return "servers.html";
    }
    @RequestMapping(value="/create-server", method = RequestMethod.POST)
    public String createServer() throws SQLException {
        adminServiceIF.createServer();
       return "create-server.html";
    }
    @RequestMapping(value="/update-server", method = RequestMethod.POST)
    public String updateServer() throws SQLException {
       int result = adminServiceIF.updateServer(new ServerDO());
       return "update-server.html";
    }
    @RequestMapping(value="/delete-server/{id}", method = RequestMethod.DELETE)
    public String deleteServerByID() throws SQLException {
       int result = adminServiceIF.deleteServerByID(new ServerDO());
       return "delete-server.html";
    }
    @RequestMapping(value="/delete-server", method = RequestMethod.DELETE)
        public String deleteServer() throws SQLException {
       List <ServerDO> result = adminServiceIF.deleteServer();
       return "delete-server.html";
    }
}