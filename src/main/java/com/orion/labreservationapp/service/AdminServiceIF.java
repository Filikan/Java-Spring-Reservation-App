package com.orion.labreservationapp.service;

import com.orion.labreservationapp.model.ServerDO;

import java.util.List;

public interface AdminServiceIF {
List<ServerDO> findAll();
int update (ServerDO serverDO);
int deleteById (int id);
int deleteAll();
List<ServerDO> findByServerName(String server_name);
ServerDO findById(int id);


int save(ServerDO serverDO);

}



