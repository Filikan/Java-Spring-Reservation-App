package com.orion.labreservationapp.controller;

import com.orion.labreservationapp.model.ServerDO;
import com.orion.labreservationapp.service.AdminServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class APIController {
    @Autowired(required=false)
   private AdminServiceIF adminService;

    //private AdminServiceImpl svc = new AdminServiceImpl();
    @RequestMapping(value="/servers", method=RequestMethod.GET)
public ResponseEntity<List<ServerDO>>GetAllServers(){
       try {
           List<ServerDO> servers = new ArrayList<ServerDO>();
           //if(server_name==null){
              // adminService.findAll().forEach(servers::add);
           //svc.findAll().forEach(servers::add);
           //}else{
            //   adminService.findByServerName(server_name).forEach(servers::add);
          // }
          // if (servers.isEmpty()){
            //   return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          // }
           return new ResponseEntity<>(servers, HttpStatus.OK);
       }catch (Exception e){
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
            _serverDO.setServer_name(serverDO.getServer_name());
            _serverDO.setServer_ip(serverDO.getServer_ip());
            _serverDO.setServer_location(serverDO.getServer_location());
            _serverDO.setSerial_number(serverDO.getSerial_number());
            _serverDO.setServer_type(serverDO.getServer_type());
            _serverDO.setIs_host(serverDO.getIs_host());
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



