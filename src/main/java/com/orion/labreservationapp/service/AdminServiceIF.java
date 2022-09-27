package com.orion.labreservationapp.service;

import com.orion.labreservationapp.model.ServerDO;

import java.sql.SQLException;
import java.util.List;

public interface AdminServiceIF 
{
    public int getServerCount() throws SQLException;
    public List<ServerDO> getServer() throws SQLException;
    public void createServer(String server_name, String server_location, String server_ip, String serial_number, String server_type, Boolean is_host, int id) throws SQLException;
    public int updateServer(ServerDO server) throws SQLException;
    public int deleteServerByID(ServerDO server) throws SQLException;
    public List<ServerDO> deleteServer() throws SQLException;
}



