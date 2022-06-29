package com.orion.labreservationapp.service;

import com.orion.labreservationapp.model.ServerDO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AdminServiceIF {
List<ServerDO> findAll();
int update (ServerDO serverDO);
int deleteById (int id);
int deleteAll();
List<ServerDO> findByServerName(String serverName);
ServerDO findById(int id);


int save(ServerDO serverDO);
}



