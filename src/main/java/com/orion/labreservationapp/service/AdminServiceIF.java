package com.orion.labreservationapp.service;

import com.orion.labreservationapp.model.ServerDO;

import java.sql.SQLException;
import java.util.List;

public interface AdminServiceIF {
    public int getServerCount() throws SQLException;
    public List<ServerDO> getServer() throws SQLException;
    public void createServer() throws SQLException;
    public int updateServer(ServerDO server) throws SQLException;
    public int deleteServerByID(ServerDO server) throws SQLException;
    public List<ServerDO> deleteServer() throws SQLException;
}



