package com.orion.labreservationapp.controller;

import com.orion.labreservationapp.model.ServerDO;
import com.orion.labreservationapp.service.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class PageController {
    @Autowired(required = false)
    AdminServiceImpl adminService;
    @GetMapping("/servers")
public ResponseEntity<List<ServerDO>>GetAllServers(@RequestParam(required = false) String serverName) {
    try {
        List<ServerDO> servers = new ArrayList<ServerDO>();
        if(serverName==null)
            servers.addAll(adminService.findAll());
        else
            servers.addAll(adminService.findByServerName(serverName));
        if (servers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(servers, HttpStatus.OK);
    }
    catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
@GetMapping("/servers/{id}")
public ResponseEntity<ServerDO> getServerById(@PathVariable("id") int id){
    ServerDO serverDO= adminService.findById(id);
    if(serverDO!=null)
        return new ResponseEntity<>(HttpStatus.OK);
    else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}
@PostMapping("/servers")
public ResponseEntity<ServerDO> addServer(@RequestBody ServerDO serverDO){
    try {
        adminService.save(serverDO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
@PutMapping("/servers/{id}")
        public ResponseEntity<String>updateServer(@PathVariable("id") int id, @RequestBody ServerDO serverDO){
        ServerDO _serverDO=adminService.findById(id);
        if (_serverDO!=null){
            _serverDO.setId(id);
            _serverDO.setServerName(serverDO.getServerName());
            _serverDO.setServerIP(serverDO.getServerIP());
            _serverDO.setServerLocation(serverDO.getServerLocation());
            _serverDO.setSerialNumber(serverDO.getSerialNumber());
            _serverDO.setServerType(serverDO.getServerType());
            _serverDO.setIsHost(serverDO.getIsHost());
adminService.update(_serverDO);
            return new ResponseEntity<>("Server updated successfully", HttpStatus.OK);
        }
        else return new ResponseEntity<>("Server not found", HttpStatus.NOT_FOUND);
    }

@DeleteMapping("/servers/{id}")
        public ResponseEntity<String>deleteServer(@PathVariable("id") int id){
        try {
            int _id=adminService.findById(id).getId();
            if(_id==id){
                adminService.deleteById(id);
                return new ResponseEntity<>("Server deleted successfully", HttpStatus.OK);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>("Server not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Server not found", HttpStatus.NOT_FOUND);
    }
@DeleteMapping("/servers")
        public ResponseEntity<String>deleteAllServers(){
        try {
            adminService.deleteAll();
            return new ResponseEntity<>("All servers deleted successfully", HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Server not found", HttpStatus.NOT_FOUND);
        }
    }
}



