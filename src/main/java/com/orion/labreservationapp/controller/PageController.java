package com.orion.labreservationapp.controller;

import com.orion.labreservationapp.service.AdminServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

    @Autowired(required=false)
    private AdminServiceIF adminServiceIF;
@RequestMapping(value="/", method= RequestMethod.GET)
    public String getHome() {
    adminServiceIF.findAll();

    return "hello";
    }
}
