package com.orion.labreservationapp.controller;

import com.orion.labreservationapp.service.AdminServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;

@Controller
public class PageController {

    @Autowired
    private AdminServiceIF adminServiceIF;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHome() throws SQLException {
        int count = adminServiceIF.getServerCount();
        return "index.html";
    }
}
